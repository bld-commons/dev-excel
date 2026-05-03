/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.csv.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.csv.annotation.CsvDate;
import com.bld.common.spreadsheet.csv.annotation.CsvSettings;
import com.bld.common.spreadsheet.utils.CsvUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.read.report.csv.ReadCsv;
import com.bld.read.report.csv.domain.CsvRead;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;
import com.bld.read.report.excel.exception.ExcelReaderException;

/**
 * Spring component that reads a CSV file and maps its content to a list of typed Java objects.
 * <p>
 * This class is stateless and safe for concurrent use as a singleton Spring bean.
 * Per-class reflection metadata (field names, types, {@link CsvDate} annotations, settings)
 * is computed once and stored in a static cache keyed by row class.
 * A single {@link BeanWrapperImpl} is created per CSV record instead of per field,
 * reducing object allocation overhead for large files.
 * </p>
 */
@Component
public class ReadCsvImpl implements ReadCsv {

	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(ReadCsvImpl.class);

	/** Per-class metadata cache — computed once per row class. */
	private static final ConcurrentHashMap<Class<?>, CsvClassMeta> CSV_CACHE = new ConcurrentHashMap<>();

	// -------------------------------------------------------------------------
	// Cache records
	// -------------------------------------------------------------------------

	/**
	 * Metadata for a single CSV-mapped field.
	 *
	 * @param fieldName the Java field name to which the CSV column is mapped
	 * @param type      the declared Java type of the field
	 * @param csvDate   the optional {@link CsvDate} annotation, present only for date/calendar fields;
	 *                  {@code null} for all other types
	 */
	private record CsvFieldMeta(String fieldName, Class<?> type, CsvDate csvDate) {}

	/**
	 * Immutable, per-class metadata cache entry built once per row class.
	 *
	 * @param settings the {@link CsvSettings} annotation found on the row class,
	 *                 carrying delimiter, quote char, parallel flag, and columns to ignore
	 * @param fieldMap an unmodifiable mapping from CSV header name to the corresponding
	 *                 {@link CsvFieldMeta}; built during the first call for each class
	 */
	private record CsvClassMeta(CsvSettings settings, Map<String, CsvFieldMeta> fieldMap) {}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	/**
	 * Converts the CSV content held in {@code csvRead} into a list of typed Java objects.
	 * <p>
	 * The {@link CsvRead} container supplies the raw {@link java.io.InputStream} and controls
	 * whether the stream should be closed after parsing (see {@link CsvRead#isClose()}).
	 * Reflection metadata for {@code classT} is resolved from the cache or computed on first use.
	 * </p>
	 *
	 * @param <T>     the row type, must implement {@link RowSheetRead}
	 * @param csvRead the CSV read container carrying the input stream and configuration
	 * @param classT  the target row class to map CSV records into
	 * @return the same {@code csvRead} instance populated with the parsed rows
	 * @throws Exception if the CSV cannot be parsed or a row instance cannot be created
	 */
	@Override
	public <T extends RowSheetRead> CsvRead<T> convertCsvToEntity(CsvRead<T> csvRead, Class<T> classT) throws Exception {
		csvRead = this.extractEntities(csvRead, classT);
		if (csvRead.isClose() && csvRead.getCsv() != null)
			csvRead.getCsv().close();
		return csvRead;
	}

	// -------------------------------------------------------------------------
	// Core reading logic
	// -------------------------------------------------------------------------

	/**
	 * Opens a {@link CSVParser} over the input stream, resolves (or builds) the class metadata,
	 * strips ignored columns, then delegates to either the parallel or sequential row extractor
	 * according to the {@link CsvSettings#parallel()} flag.
	 *
	 * @param <T>     the row type
	 * @param csvRead the CSV read container
	 * @param classT  the target row class
	 * @return the populated {@code csvRead} container
	 * @throws Exception if parsing or bean instantiation fails
	 */
	private <T extends RowSheetRead> CsvRead<T> extractEntities(CsvRead<T> csvRead, Class<T> classT) throws Exception {
		Reader csvReader = new InputStreamReader(csvRead.getCsv());
		CsvClassMeta meta = CSV_CACHE.computeIfAbsent(classT, this::buildClassMeta);
		CSVFormat csvFormat = CsvUtils.getCsvFormat(meta.settings());
		CSVParser csvParser = csvFormat.parse(csvReader);
		List<String> headers = new ArrayList<>(csvParser.getHeaderNames());
		for (String ignoreColumn : meta.settings().ignoreColumns())
			headers.remove(ignoreColumn);
		if (meta.settings().parallel())
			extractParallelRows(csvRead, classT, meta, csvParser, headers);
		else
			extractRows(csvRead, classT, meta, csvParser, headers);
		return csvRead;
	}

	/**
	 * Iterates the CSV records via a parallel stream, mapping each record to a row bean
	 * and adding it to {@code csvRead} in a thread-safe manner.
	 * <p>
	 * Any checked exception thrown inside the lambda is wrapped in an
	 * {@link ExcelReaderException} so it can propagate through the parallel stream.
	 * </p>
	 *
	 * @param <T>       the row type
	 * @param csvRead   the CSV read container (its {@code addRow} method must be thread-safe)
	 * @param classT    the target row class
	 * @param meta      pre-built class metadata
	 * @param csvParser the parser supplying the record stream
	 * @param headers   the ordered list of active column headers (ignored columns already removed)
	 * @throws Exception if bean instantiation or property assignment fails
	 */
	private <T extends RowSheetRead> void extractParallelRows(CsvRead<T> csvRead, Class<T> classT, CsvClassMeta meta, CSVParser csvParser, List<String> headers)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {
		csvParser.stream().parallel().forEach(csvRecord -> {
			try {
				addRowSheet(csvRead, classT, meta, headers, csvRecord);
			} catch (Exception e) {
				throw new ExcelReaderException(e);
			}
		});
	}

	/**
	 * Iterates the CSV records sequentially, mapping each record to a row bean
	 * and adding it to {@code csvRead}.
	 *
	 * @param <T>       the row type
	 * @param csvRead   the CSV read container
	 * @param classT    the target row class
	 * @param meta      pre-built class metadata
	 * @param csvParser the parser supplying the records
	 * @param headers   the ordered list of active column headers (ignored columns already removed)
	 * @throws Exception if bean instantiation or property assignment fails
	 */
	private <T extends RowSheetRead> void extractRows(CsvRead<T> csvRead, Class<T> classT, CsvClassMeta meta, CSVParser csvParser, List<String> headers)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {
		for (CSVRecord csvRecord : csvParser)
			addRowSheet(csvRead, classT, meta, headers, csvRecord);
	}

	/**
	 * Maps a single {@link CSVRecord} to a new instance of {@code classT} and appends it
	 * to the result list inside {@code csvRead}.
	 * <p>
	 * A single {@link BeanWrapperImpl} is created per record (not per field) to reduce
	 * object allocation.  Each header column is resolved to the corresponding
	 * {@link CsvFieldMeta} and the raw string value is converted to the declared Java type
	 * ({@link Number} subtypes, {@link String}, {@link Date}, {@link java.util.Calendar},
	 * {@link Boolean}, {@link Character}).  Fields whose CSV cell is empty are left at their
	 * default value.
	 * </p>
	 *
	 * @param <T>       the row type
	 * @param csvRead   the CSV read container to which the new row is added
	 * @param classT    the target row class
	 * @param meta      pre-built class metadata
	 * @param headers   the ordered list of active column headers
	 * @param csvRecord the raw CSV record to convert
	 * @throws InstantiationException    if the no-arg constructor of {@code classT} is inaccessible
	 * @throws IllegalAccessException    if the no-arg constructor of {@code classT} is inaccessible
	 * @throws InvocationTargetException if the no-arg constructor throws an exception
	 * @throws NoSuchMethodException     if {@code classT} has no public no-arg constructor
	 * @throws Exception                 for any date-parsing or other conversion failure
	 */
	private <T extends RowSheetRead> void addRowSheet(CsvRead<T> csvRead, Class<T> classT, CsvClassMeta meta, List<String> headers, CSVRecord csvRecord)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, Exception {
		T t = classT.getDeclaredConstructor().newInstance();
		BeanWrapperImpl wrapper = new BeanWrapperImpl(t);
		for (String header : headers) {
			logger.debug("Column Name: " + header);
			CsvFieldMeta fm = meta.fieldMap().get(header);
			Object value = null;
			Class<?> classField = fm.type();
			if (StringUtils.isNotEmpty(csvRecord.get(header))) {
				if (Number.class.isAssignableFrom(classField)) {
					Double numberValue = Double.valueOf(csvRecord.get(header));
					value = numberValue;
					if (Integer.class.isAssignableFrom(classField))
						value = numberValue.intValue();
					else if (BigDecimal.class.isAssignableFrom(classField))
						value = BigDecimal.valueOf(numberValue);
					else if (Float.class.isAssignableFrom(classField))
						value = Float.valueOf(numberValue.floatValue());
					else if (Long.class.isAssignableFrom(classField))
						value = numberValue.longValue();
					else if (BigInteger.class.isAssignableFrom(classField))
						value = BigInteger.valueOf(numberValue.longValue());
				} else if (String.class.isAssignableFrom(classField)) {
					String stringValue = csvRecord.get(header);
					value = stringValue.isEmpty() ? null : stringValue;
				} else if (Calendar.class.isAssignableFrom(classField)) {
					Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(fm.csvDate().timezone()));
					Date dateValue = getDate(csvRecord.get(header), fm.csvDate());
					calendar.setTime(dateValue);
					value = calendar;
				} else if (Date.class.isAssignableFrom(classField)) {
					value = getDate(csvRecord.get(header), fm.csvDate());
				} else if (Boolean.class.isAssignableFrom(classField)) {
					String boolStr = csvRecord.get(header);
					value = StringUtils.isNotBlank(boolStr) ? Boolean.valueOf(boolStr.trim()) : null;
				} else if (Character.class.isAssignableFrom(classField)) {
					String stringValue = csvRecord.get(header);
					value = stringValue.length() > 0 ? stringValue.charAt(0) : null;
				} else {
					logger.debug("The type \"" + classField.getSimpleName() + "\" is not manage");
				}
				if (value != null)
					wrapper.setPropertyValue(fm.fieldName(), value);
			}
		}
		logger.debug(t.toString());
		csvRead.addRow(t);
	}

	// -------------------------------------------------------------------------
	// Cache management
	// -------------------------------------------------------------------------

	/**
	 * Builds and returns an immutable {@link CsvClassMeta} for {@code classT} by scanning
	 * all declared (and inherited) fields exactly once.
	 * <p>
	 * Only fields annotated with {@link ExcelReadColumn} are included in the resulting
	 * field map; the annotation's {@code value()} attribute is used as the CSV header key.
	 * This method is called at most once per class by
	 * {@link ConcurrentHashMap#computeIfAbsent(Object, java.util.function.Function)}.
	 * </p>
	 *
	 * @param classT the row class to introspect
	 * @return a fully populated, immutable {@link CsvClassMeta} for {@code classT}
	 */
	private CsvClassMeta buildClassMeta(Class<?> classT) {
		CsvSettings settings = SpreadsheetUtils.getAnnotation(classT, CsvSettings.class);
		Set<Field> listField = SpreadsheetUtils.getListField(classT);
		Map<String, CsvFieldMeta> fieldMap = new HashMap<>();
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelReadColumn.class)) {
				ExcelReadColumn readColumn = field.getAnnotation(ExcelReadColumn.class);
				CsvDate csvDate = field.getAnnotation(CsvDate.class);
				fieldMap.put(readColumn.value(), new CsvFieldMeta(field.getName(), field.getType(), csvDate));
			}
		}
		return new CsvClassMeta(settings, Map.copyOf(fieldMap));
	}

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	/**
	 * Parses a date string according to the format and timezone declared in a
	 * {@link CsvDate} annotation.
	 * <p>
	 * The format pattern is taken from {@link CsvDate#value()} (a {@code ColumnDateFormat}
	 * whose string representation uses {@code /} as separator) and the embedded {@code /}
	 * characters are replaced with {@link CsvDate#separator()} before parsing.
	 * </p>
	 *
	 * @param date    the raw date string read from the CSV cell
	 * @param csvDate the annotation carrying the expected format, separator, and timezone
	 * @return the parsed {@link Date}
	 * @throws Exception if the string does not match the expected format
	 */
	private Date getDate(String date, CsvDate csvDate) throws Exception {
		String format = csvDate.value().getValue().replace("/", csvDate.separator());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(csvDate.timezone()));
		return sdf.parse(date);
	}

}
