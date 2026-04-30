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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	/**
	 * Iterates over all registered sheets, reads each row, converts cell values to the
	 * declared field types, and adds the resulting entity to the corresponding {@link com.bld.read.report.excel.domain.SheetRead}.
	 * <p>Merged cell regions are resolved so that a merged cell always returns the value
	 * of its top-left origin cell.</p>
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
			Class<? extends SheetRead<? extends RowSheetRead>> classSheet = (Class<? extends SheetRead<? extends RowSheetRead>>) sheet
					.getClass();
			ExcelReadSheet excelReadSheet = SpreadsheetUtils.getAnnotation(classSheet, ExcelReadSheet.class);
			logger.debug("Sheet: " + sheetType.getSheetName());
			if (sheetType.getSheetName().length() > SpreadsheetUtils.SHEET_NAME_SIZE)
				throw new ExcelReaderException(ExcelExceptionType.MAX_SHEET_NAME);
			Sheet worksheet = workbook.getSheet(sheetType.getSheetName());
			if (worksheet == null)
				throw new ExcelReaderException(ExcelExceptionType.SHEET_NOT_FOUND, sheetType.getSheetName());
			Row header = worksheet.getRow(excelReadSheet.startRow());
			Map<String, Integer> mapColumns = this.getMapColumns(header, excelReadSheet);
			int startRow = excelReadSheet.startRow() + 1;
			ParameterizedType classType = (ParameterizedType) classSheet.getGenericSuperclass();
			Class<T> genericClassType = (Class<T>) classType.getActualTypeArguments()[0];
			Map<String, Method> mapMethod = new HashMap<>();
			setMapMethod(mapMethod, genericClassType.getSuperclass().getMethods());
			setMapMethod(mapMethod, genericClassType.getMethods());
			logger.debug("Generic class type: " + genericClassType.getName());
			int rowSize = worksheet.getPhysicalNumberOfRows();
			for (int indexRow = startRow; indexRow <= rowSize; indexRow++) {
				T rowSheetRead = genericClassType.getDeclaredConstructor().newInstance();
				Row row = worksheet.getRow(indexRow);
				if (row != null) {
					Set<Field> listField = SpreadsheetUtils.getListField(rowSheetRead.getClass());
					boolean rowEmpty = true;
					for (Field field : listField) {
						if (field.isAnnotationPresent(ExcelReadColumn.class)) {
							ExcelReadColumn excelReadColumn = field.getAnnotation(ExcelReadColumn.class);
							if (!mapColumns.containsKey(excelReadColumn.value()))
								throw new ExcelReaderException(ExcelExceptionType.COLUMN_NOT_FOUND,
										excelReadColumn.value());
							int indexColumn = mapColumns.get(excelReadColumn.value());
							Cell cell = row.getCell(indexColumn);
							for (int indexRegion = 0; indexRegion < worksheet.getNumMergedRegions(); indexRegion++) {
								CellRangeAddress mergedCell = worksheet.getMergedRegion(indexRegion);
								if (mergedCell.isInRange(indexRow, indexColumn)) {
									cell = worksheet.getRow(mergedCell.getFirstRow()).getCell(indexColumn);
									break;
								}

							}
							if (cell != null && !IGNORE_CELL_TYPE.contains(cell.getCellType())) {
								String nameMethod = SET + ("" + field.getName().charAt(0)).toUpperCase()
										+ field.getName().substring(1);
								logger.debug("Set Function: " + nameMethod);
								Class<?> classField = field.getType();
								logger.debug("The field " + field.getName() + " is of " + classField.getSimpleName()
										+ " type");
								Object value = null;
								ExcelBooleanText excelBooleanText = null;
								if (field.isAnnotationPresent(ExcelBooleanText.class))
									excelBooleanText = field.getAnnotation(ExcelBooleanText.class);
								if (excelBooleanText != null && !Boolean.class.isAssignableFrom(classField))
									throw new ExcelReaderException(
											"The \"ExcelBooleanText\" annotation can only be assigned to boolean fields");
								try {
									if (excelBooleanText != null) {
										String stringValue = cell.getStringCellValue();
										if (excelBooleanText.enable().equalsIgnoreCase(stringValue))
											value = true;
										else if (excelBooleanText.disable().equalsIgnoreCase(stringValue))
											value = false;
									}
									if (excelReadColumn.ignoreCellTypeString()
											&& CellType.STRING.equals(cell.getCellType())
											&& !String.class.isAssignableFrom(classField)) {
										String stringValue = cell.getStringCellValue();
										if (Number.class.isAssignableFrom(classField))
											value = this.getNumberValue(stringValue, classField);
										else if (Calendar.class.isAssignableFrom(classField)) {
											Date dateValue = convertStringToDate(stringValue, field);
											if (dateValue != null) {
												Calendar calendar = Calendar.getInstance();
												calendar.setTime(dateValue);
												value = calendar;
											}
										} else if (Date.class.isAssignableFrom(classField))
											value = convertStringToDate(stringValue, field);
										else if (Boolean.class.isAssignableFrom(classField))
											value = Boolean.parseBoolean(stringValue);
										else if (Character.class.isAssignableFrom(classField)) {
											if (StringUtils.isNotEmpty(stringValue)) {
												stringValue = stringValue.trim();
												if (stringValue.length() > 1)
													throw new ExcelReaderException(
															ExcelExceptionType.CHARACTER_NOT_VALID, field.getName());
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
									} else if (Boolean.class.isAssignableFrom(classField)) {
										value = cell.getBooleanCellValue();
									} else if (Character.class.isAssignableFrom(classField)) {
										String stringValue = cell.getStringCellValue();
										if (StringUtils.isNotEmpty(stringValue)) {
											stringValue = stringValue.trim();
											if (stringValue.length() > 1)
												throw new ExcelReaderException(ExcelExceptionType.CHARACTER_NOT_VALID,
														field.getName());
											value = stringValue.charAt(0);
										}
									} else {
										logger.debug(
												"The type \"" + field.getType().getSimpleName() + "\" is not manage");
									}
								} catch (Exception e) {
									logger.error("The \"" + field.getName() + "\" field throw exception");
									if (!CellType.FORMULA.equals(cell.getCellType()))
										throw e;
									else
										logger.warn("The formula cell returns a null value");
								}

								if (value != null) {
									String nameColumn = ("" + field.getName().charAt(0)).toUpperCase()
											+ field.getName().substring(1);
									logger.debug(nameColumn + ": " + value);
									Method method = mapMethod.get(SET + nameColumn);
									method.invoke(rowSheetRead, value);
									rowEmpty = false;
								}
							}
						}
					}
					if (rowEmpty)
						break;
					else
						sheetType.addRowSheet(rowSheetRead);
				}

			}
		}

		return excelRead;
	}

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
				value=numberValue;
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

	private Date convertStringToDate(String textDate, Field field) throws Exception {
		ExcelDate excelDate = SpreadsheetUtils.getAnnotation(field, ExcelDate.class);
		String date = textDate.replace(".", "/").replace("-", "/");
		SimpleDateFormat sdf = new SimpleDateFormat(excelDate.value().getValue());
		return sdf.parse(date);
	}

}
