/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.csv.impl;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.csv.annotation.CsvDate;
import com.bld.common.spreadsheet.csv.annotation.CsvSettings;
import com.bld.common.spreadsheet.utils.CsvUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.read.report.csv.ReadCsv;
import com.bld.read.report.csv.domain.CsvRead;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;

/**
 * The Class ReadCsvImpl.
 */
@Component
@SuppressWarnings("resource")
public class ReadCsvImpl implements ReadCsv {

	/** The Constant logger. */
	private static final Log logger = LogFactory.getLog(ReadCsvImpl.class);

	/**
	 * Convert csv to entity.
	 *
	 * @param <T>     the generic type
	 * @param csvRead the csv read
	 * @param classT  the class T
	 * @return the list
	 * @throws Exception the exception
	 */
	@Override
	public <T extends RowSheetRead> CsvRead<T> convertCsvToEntity(CsvRead<T> csvRead, Class<T> classT) throws Exception {
		csvRead=this.extractEntities(csvRead, classT);
		if(csvRead.isClose() && csvRead.getCsv()!=null)
			csvRead.getCsv().close();
		return csvRead;
	}



	/**
	 * Extract entities.
	 *
	 * @param <T>     the generic type
	 * @param csvRead the csv read
	 * @param classT  the class T
	 * @return the csv read
	 * @throws Exception the exception
	 */
	private <T extends RowSheetRead> CsvRead<T> extractEntities(CsvRead<T> csvRead, Class<T> classT) throws Exception {

		Reader csvReader = new InputStreamReader(csvRead.getCsv());
		Map<String, Field> mapField = new HashMap<>();
		CsvSettings csvSettings = SpreadsheetUtils.getAnnotation(classT, CsvSettings.class);
		CSVFormat csvFormat=CsvUtils.getCsvFormat(csvSettings);

		Set<Field> listField = SpreadsheetUtils.getListField(classT);
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelReadColumn.class)) {
				ExcelReadColumn excelReadColumn = field.getAnnotation(ExcelReadColumn.class);
				mapField.put(excelReadColumn.value(), field);
			}
		}
		CSVParser csvParser = new CSVParser(csvReader, csvFormat);
		for (CSVRecord csvRecord : csvParser) {
			T t = classT.getDeclaredConstructor().newInstance();
			for (String header : csvParser.getHeaderNames()) {
				logger.debug("Column Name: "+header);
				Field field = mapField.get(header);
				Object value = null;
				Class<?> classField = field.getType();
				if(StringUtils.isNotEmpty(csvRecord.get(header))) {
					if (Number.class.isAssignableFrom(classField)) {
						Double numberValue = Double.valueOf(csvRecord.get(header));
						if (Integer.class.isAssignableFrom(classField))
							value = numberValue.intValue();
						else if (BigDecimal.class.isAssignableFrom(classField))
							value = BigDecimal.valueOf(numberValue);
						else if (Float.class.isAssignableFrom(classField))
							value = Float.valueOf(numberValue.floatValue());
						else if (Long.class.isAssignableFrom(classField))
							value = numberValue.longValue();
					} else if (String.class.isAssignableFrom(classField)) {
						String stringValue = csvRecord.get(header);
						value = stringValue.isEmpty() ? null : stringValue;
					} else if (Calendar.class.isAssignableFrom(classField)) {
						Calendar calendar = Calendar.getInstance();
						Date dateValue = getDate(csvRecord.get(header), field);
						calendar.setTime(dateValue);
						value = calendar;
					} else if (Date.class.isAssignableFrom(classField)) {
						value = getDate(csvRecord.get(header), field);
					} else if (Boolean.class.isAssignableFrom(classField)) {
						value = Boolean.valueOf(csvRecord.get(header));
					} else if (Character.class.isAssignableFrom(classField)) {
						String stringValue = csvRecord.get(header);
						value = stringValue.length() > 0 ? stringValue.charAt(0) : null;
					} else {
						logger.debug("The type \"" + field.getType().getSimpleName() + "\" is not manage");
					}
					PropertyUtils.setProperty(t, field.getName(), value);
				}
			}
			logger.info(t.toString());
			csvRead.getListRowSheet().add(t);
		}
		return csvRead;
	}

	/**
	 * Gets the date.
	 *
	 * @param date  the date
	 * @param field the field
	 * @return the date
	 * @throws Exception the exception
	 */
	private Date getDate(String date, Field field) throws Exception {
		CsvDate csvDate = SpreadsheetUtils.getAnnotation(field, CsvDate.class);
		String format = csvDate.value().getValue().replace("/", csvDate.separator());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

}
