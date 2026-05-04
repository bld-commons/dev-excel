/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.read.report.excel.ReadExcel;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.annotation.ExcelReadSheet;
import com.bld.read.report.excel.constant.ExcelExceptionType;
import com.bld.read.report.excel.constant.ExcelType;
import com.bld.read.report.excel.domain.ExcelRead;
import com.bld.read.report.excel.domain.RowSheetRead;
import com.bld.read.report.excel.domain.SheetRead;
import com.bld.read.report.excel.exception.ExcelReaderException;

/**
 * Spring component that reads an Excel file and maps its content to a list of typed Java objects.
 * <p>
 * This class is stateless and safe for concurrent use as a singleton Spring bean.
 * It supports both {@code .xls} (HSSF) and {@code .xlsx} (XSSF) formats, merged cells,
 * and a wide range of field types including {@code String}, {@code Number} subtypes,
 * {@code Date}, {@code Calendar}, {@code Boolean}, and {@code Character}.
 * </p>
 * <p>
 * The mapping between Excel columns and Java fields is driven by the
 * {@link com.bld.read.report.excel.annotation.ExcelReadColumn} annotation.
 * Sheet configuration (name, start row and column) is defined via
 * {@link com.bld.read.report.excel.annotation.ExcelReadSheet}.
 * </p>
 * <p>
 * Per-class metadata (annotation lookups, field lists, setter maps) is computed once and
 * stored in a static cache keyed by sheet class. Merged-cell regions are indexed into a
 * per-sheet {@code HashMap} before the row loop, replacing a linear scan per cell.
 * </p>
 *
 * @author Francesco Baldi
 * @see com.bld.read.report.excel.ReadExcel
 * @see com.bld.read.report.excel.domain.ExcelRead
 * @see com.bld.read.report.excel.domain.SheetRead
 */
@SuppressWarnings({ "unchecked" })
@Component
public class ReadExcelImpl implements ReadExcel {

	/** The Constant SET. */
	private static final String SET = "set";

	/** The Constant log. */
	private static final Log logger = LogFactory.getLog(ReadExcelImpl.class);

	/** The Constant IGNORE_CELL_TYPE. */
	private static final List<CellType> IGNORE_CELL_TYPE = Arrays.asList(CellType.BLANK, CellType.ERROR);

	/** Per-class metadata cache — computed once per SheetRead subclass. */
	private static final ConcurrentHashMap<Class<?>, SheetMeta> SHEET_CACHE = new ConcurrentHashMap<>();

	// -------------------------------------------------------------------------
	// Cache records
	// -------------------------------------------------------------------------

	private record FieldMeta(
		ExcelReadColumn readColumn,
		String fieldName,
		Class<?> type,
		ExcelBooleanText booleanText,
		ExcelDate excelDate
	) {}

	private record SheetMeta(
		ExcelReadSheet readSheet,
		Class<?> rowClass,
		Map<String, Method> setterMap,
		List<FieldMeta> fields
	) {}

	// -------------------------------------------------------------------------
	// Public API
	// -------------------------------------------------------------------------

	/**
	 * Reads the Excel file described by the given {@link ExcelRead} object and populates
	 * each registered {@link com.bld.read.report.excel.domain.SheetRead} with the parsed rows.
	 * <p>
	 * If {@link ExcelRead#isClose()} is {@code true}, the underlying {@link java.io.InputStream}
	 * is closed after reading.
	 * </p>
	 *
	 * @param excelRead the descriptor containing the file stream, format type, and sheet mappings
	 * @return the same {@link ExcelRead} instance with all sheets populated
	 * @throws Exception if the file cannot be read, a required sheet or column is missing,
	 *                   or a field type conversion fails
	 */
	@Override
	public ExcelRead convertExcelToEntity(ExcelRead excelRead) throws Exception {
		excelRead = this.extractEntities(excelRead);
		if (excelRead.isClose() && excelRead.getReportExcel() != null)
			excelRead.getReportExcel().close();
		return excelRead;
	}

	// -------------------------------------------------------------------------
	// Core reading logic
	// -------------------------------------------------------------------------

	/**
	 * Iterates over all registered sheets, reads each row, converts cell values to the
	 * declared field types, and adds the resulting entity to the corresponding {@link com.bld.read.report.excel.domain.SheetRead}.
	 * <p>Merged cell regions are resolved via a pre-built lookup map so that a merged cell
	 * always returns the value of its top-left origin cell.</p>
	 *
	 * @param <T>       the row entity type, must implement {@link com.bld.read.report.excel.domain.RowSheetRead}
	 * @param excelRead the descriptor containing file content and sheet configuration
	 * @return the populated {@link ExcelRead} instance
	 * @throws Exception if sheet or column resolution fails, or a type conversion error occurs
	 */
	private <T extends RowSheetRead> ExcelRead extractEntities(ExcelRead excelRead) throws Exception {
		Workbook workbook = null;
		if (ExcelType.XLS.equals(excelRead.getExcelType()))
			workbook = new HSSFWorkbook(excelRead.getReportExcel());
		else
			workbook = new XSSFWorkbook(excelRead.getReportExcel());
		for (SheetRead<? extends RowSheetRead> sheet : excelRead.getListSheetRead()) {
			SheetRead<T> sheetType = (SheetRead<T>) sheet;
			Class<? extends SheetRead<? extends RowSheetRead>> classSheet = (Class<? extends SheetRead<? extends RowSheetRead>>) sheet.getClass();
			SheetMeta meta = getSheetMeta(classSheet);
			logger.debug("Sheet: " + sheetType.getSheetName());
			if (sheetType.getSheetName().length() > SpreadsheetUtils.SHEET_NAME_SIZE)
				throw new ExcelReaderException(ExcelExceptionType.MAX_SHEET_NAME);
			Sheet worksheet = workbook.getSheet(sheetType.getSheetName());
			if (worksheet == null)
				throw new ExcelReaderException(ExcelExceptionType.SHEET_NOT_FOUND, sheetType.getSheetName());
			Row header = worksheet.getRow(meta.readSheet().startRow());
			Map<String, Integer> mapColumns = this.getMapColumns(header, meta.readSheet());
			int startRow = meta.readSheet().startRow() + 1;
			Class<T> rowClass = (Class<T>) meta.rowClass();
			logger.debug("Generic class type: " + rowClass.getName());
			Map<String, CellRangeAddress> mergedRegionMap = buildMergedRegionMap(worksheet);
			int rowSize = worksheet.getPhysicalNumberOfRows();
			for (int indexRow = startRow; indexRow <= rowSize; indexRow++) {
				T rowSheetRead = rowClass.getDeclaredConstructor().newInstance();
				Row row = worksheet.getRow(indexRow);
				if (row != null) {
					boolean rowEmpty = true;
					for (FieldMeta fm : meta.fields()) {
						if (!mapColumns.containsKey(fm.readColumn().value()))
							throw new ExcelReaderException(ExcelExceptionType.COLUMN_NOT_FOUND, fm.readColumn().value());
						int indexColumn = mapColumns.get(fm.readColumn().value());
						Cell cell = row.getCell(indexColumn);
						CellRangeAddress region = mergedRegionMap.get(indexRow + ":" + indexColumn);
						if (region != null)
							cell = worksheet.getRow(region.getFirstRow()).getCell(indexColumn);
						if (cell != null && !IGNORE_CELL_TYPE.contains(cell.getCellType())) {
							logger.debug("Set Function: " + SET + Character.toUpperCase(fm.fieldName().charAt(0)) + fm.fieldName().substring(1));
							logger.debug("The field " + fm.fieldName() + " is of " + fm.type().getSimpleName() + " type");
							Object value = null;
							Class<?> classField = fm.type();
							ExcelBooleanText excelBooleanText = fm.booleanText();
							if (excelBooleanText != null && !Boolean.class.isAssignableFrom(classField))
								throw new ExcelReaderException("The \"ExcelBooleanText\" annotation can only be assigned to boolean fields");
							try {
								if (excelBooleanText != null) {
									String stringValue = cell.getStringCellValue();
									if (excelBooleanText.enable().equalsIgnoreCase(stringValue))
										value = true;
									else if (excelBooleanText.disable().equalsIgnoreCase(stringValue))
										value = false;
								}
								if (fm.readColumn().ignoreCellTypeString()
										&& CellType.STRING.equals(cell.getCellType())
										&& !String.class.isAssignableFrom(classField)) {
									String stringValue = cell.getStringCellValue();
									if (Number.class.isAssignableFrom(classField))
										value = this.getNumberValue(stringValue, classField);
									else if (Calendar.class.isAssignableFrom(classField)) {
										Date dateValue = convertStringToDate(stringValue, fm.excelDate());
										if (dateValue != null) {
											Calendar calendar = Calendar.getInstance();
											calendar.setTime(dateValue);
											value = calendar;
										}
									} else if (Date.class.isAssignableFrom(classField))
										value = convertStringToDate(stringValue, fm.excelDate());
									else if (Boolean.class.isAssignableFrom(classField))
										value = StringUtils.isNotBlank(stringValue) ? Boolean.valueOf(stringValue.trim()) : null;
									else if (Character.class.isAssignableFrom(classField)) {
										if (StringUtils.isNotEmpty(stringValue)) {
											stringValue = stringValue.trim();
											if (stringValue.length() > 1)
												throw new ExcelReaderException(ExcelExceptionType.CHARACTER_NOT_VALID, fm.fieldName());
											value = stringValue.charAt(0);
										}
									}
								} else if (Number.class.isAssignableFrom(classField)) {
									value = getNumberValue(cell, classField);
								} else if (String.class.isAssignableFrom(classField)) {
									DataFormat fmt = workbook.createDataFormat();
									cell.getCellStyle().setDataFormat(fmt.getFormat("text"));
									DataFormatter formatter = new DataFormatter();
									if (CellType.FORMULA.equals(cell.getCellType()))
										formatter.setUseCachedValuesForFormulaCells(true);
									String stringValue = formatter.formatCellValue(cell).trim();
									value = stringValue.isEmpty() ? null : stringValue;
								} else if (Calendar.class.isAssignableFrom(classField)) {
									Date dateValue = cell.getDateCellValue();
									if (dateValue != null) {
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(dateValue);
										value = calendar;
									}
								} else if (Date.class.isAssignableFrom(classField)) {
									value = cell.getDateCellValue();
								} else if (Boolean.class.isAssignableFrom(classField) && excelBooleanText == null) {
									value = cell.getBooleanCellValue();
								} else if (Character.class.isAssignableFrom(classField)) {
									String stringValue = cell.getStringCellValue();
									if (StringUtils.isNotEmpty(stringValue)) {
										stringValue = stringValue.trim();
										if (stringValue.length() > 1)
											throw new ExcelReaderException(ExcelExceptionType.CHARACTER_NOT_VALID, fm.fieldName());
										value = stringValue.charAt(0);
									}
								} else {
									logger.debug("The type \"" + classField.getSimpleName() + "\" is not manage");
								}
							} catch (Exception e) {
								logger.error("The \"" + fm.fieldName() + "\" field throw exception");
								if (!CellType.FORMULA.equals(cell.getCellType()))
									throw e;
								else
									logger.warn("The formula cell returns a null value");
							}
							if (value != null) {
								String nameColumn = Character.toUpperCase(fm.fieldName().charAt(0)) + fm.fieldName().substring(1);
								logger.debug(nameColumn + ": " + value);
								meta.setterMap().get(SET + nameColumn).invoke(rowSheetRead, value);
								rowEmpty = false;
							}
						}
					}
					if (rowEmpty)
						break;
					else
						sheetType.addRow(rowSheetRead);
				}
			}
		}
		return excelRead;
	}

	// -------------------------------------------------------------------------
	// Cache management
	// -------------------------------------------------------------------------

	private SheetMeta getSheetMeta(Class<?> classSheet) throws Exception {
		SheetMeta meta = SHEET_CACHE.get(classSheet);
		if (meta == null) {
			meta = buildSheetMeta(classSheet);
			SHEET_CACHE.putIfAbsent(classSheet, meta);
			meta = SHEET_CACHE.get(classSheet);
		}
		return meta;
	}

	private SheetMeta buildSheetMeta(Class<?> classSheet) throws Exception {
		ExcelReadSheet ann = SpreadsheetUtils.getAnnotation(classSheet, ExcelReadSheet.class);
		Class<?> rowClass = (Class<?>) ((ParameterizedType) classSheet.getGenericSuperclass()).getActualTypeArguments()[0];
		Map<String, Method> setterMap = new HashMap<>();
		setMapMethod(setterMap, rowClass.getSuperclass().getMethods());
		setMapMethod(setterMap, rowClass.getMethods());
		List<FieldMeta> fields = new ArrayList<>();
		for (Field f : SpreadsheetUtils.getListField(rowClass)) {
			if (f.isAnnotationPresent(ExcelReadColumn.class)) {
				fields.add(new FieldMeta(
					f.getAnnotation(ExcelReadColumn.class),
					f.getName(),
					f.getType(),
					f.getAnnotation(ExcelBooleanText.class),
					f.getAnnotation(ExcelDate.class)
				));
			}
		}
		return new SheetMeta(ann, rowClass, setterMap, List.copyOf(fields));
	}

	// -------------------------------------------------------------------------
	// Per-sheet merged region index
	// -------------------------------------------------------------------------

	/**
	 * Builds a {@code (row:col) → CellRangeAddress} lookup map for all merged regions
	 * in the given sheet. Called once per sheet before the row loop.
	 *
	 * @param sheet the worksheet to index
	 * @return map from {@code "row:col"} key to the enclosing merged region
	 */
	private Map<String, CellRangeAddress> buildMergedRegionMap(Sheet sheet) {
		Map<String, CellRangeAddress> map = new HashMap<>();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			for (int r = region.getFirstRow(); r <= region.getLastRow(); r++)
				for (int c = region.getFirstColumn(); c <= region.getLastColumn(); c++)
					map.put(r + ":" + c, region);
		}
		return map;
	}

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private Object getNumberValue(String numberValue, Class<?> classField) {
		Object value = null;
		if (numberValue != null) {
			if (Integer.class.isAssignableFrom(classField))
				value = Integer.parseInt(numberValue);
			else if (Double.class.isAssignableFrom(classField))
				value = Double.parseDouble(numberValue);
			else if (BigDecimal.class.isAssignableFrom(classField))
				value = new BigDecimal(numberValue);
			else if (Float.class.isAssignableFrom(classField))
				value = Float.parseFloat(numberValue);
			else if (Long.class.isAssignableFrom(classField))
				value = Long.parseLong(numberValue);
		}
		return value;
	}

	/**
	 * Converts the numeric value of an Excel cell to the declared Java number type.
	 *
	 * @param cell       the Excel cell containing a numeric value
	 * @param classField the target Java type ({@code Integer}, {@code BigDecimal}, {@code Float}, {@code Long}, or {@code Double})
	 * @return the converted number value, or {@code null} if the cell value is {@code null}
	 */
	private Object getNumberValue(Cell cell, Class<?> classField) {
		Object value = null;
		Double numberValue = cell.getNumericCellValue();
		if (numberValue != null) {
			if (Integer.class.isAssignableFrom(classField))
				value = numberValue.intValue();
			else if (BigDecimal.class.isAssignableFrom(classField))
				value = BigDecimal.valueOf(numberValue);
			else if (Float.class.isAssignableFrom(classField))
				value = Float.valueOf(numberValue.floatValue());
			else if (Long.class.isAssignableFrom(classField))
				value = numberValue.longValue();
			else
				value = numberValue;
		}
		return value;
	}

	/**
	 * Populates a name-to-method map from an array of {@link java.lang.reflect.Method} objects.
	 * Used to cache setter methods for efficient lookup during row population.
	 *
	 * @param mapMethod  the map to populate, keyed by method name
	 * @param listMethod the array of methods to register
	 */
	private void setMapMethod(Map<String, Method> mapMethod, Method[] listMethod) {
		for (Method method : listMethod)
			mapMethod.put(method.getName(), method);
	}

	/**
	 * Builds a map from column header name to column index by reading the header row of the sheet.
	 * Iteration stops at the first blank or null cell.
	 *
	 * @param header         the Excel row containing column headers
	 * @param excelReadSheet the sheet annotation defining the start column index
	 * @return a map where each key is a header label and each value is its zero-based column index
	 */
	private Map<String, Integer> getMapColumns(Row header, ExcelReadSheet excelReadSheet) {
		Map<String, Integer> mapColumn = new HashMap<>();
		int i = excelReadSheet.startColumn();
		Iterator<Cell> cellIterator = header.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if (cell == null || StringUtils.isEmpty(cell.getStringCellValue()))
				break;
			mapColumn.put(cell.getStringCellValue(), i);
			i++;
		}
		return mapColumn;
	}

	private Date convertStringToDate(String textDate, ExcelDate excelDate) throws Exception {
		String date = textDate.replace(".", "/").replace("-", "/");
		SimpleDateFormat sdf = new SimpleDateFormat(excelDate.value().getValue());
		return sdf.parse(date);
	}

}
