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
 * The Class ReadExcelImpl.<br>
 * ReadExcelImpl is the class that read excel file and converts it to a
 * SheetRead list.<br>
 * 
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
	 * Convert excel to entity.<br>
	 * This function read excel file by byte array.<br>
	 * 
	 * @param excelRead the excel read
	 * @return the excel read
	 * @throws Exception the exception
	 */
	@Override
	public ExcelRead convertExcelToEntity(ExcelRead excelRead) throws Exception {
		excelRead=this.extractEntities(excelRead);
		excelRead.close();
		return excelRead;
	}

	


	/**
	 * Extract entities.
	 *
	 * @param <T>       the generic type
	 * @param excelRead the excel read
	 * @return the excel read
	 * @throws Exception the exception
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
								throw new ExcelReaderException(ExcelExceptionType.COLUMN_NOT_FOUND, excelReadColumn.value());
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
								String nameMethod = SET + ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
								logger.debug("Set Function: " + nameMethod);
								Class<?> classField = field.getType();
								logger.debug("The field " + field.getName() + " is of " + classField.getSimpleName() + " type");
								Object value = null;
								ExcelBooleanText excelBooleanText=null;
								if(field.isAnnotationPresent(ExcelBooleanText.class))
									excelBooleanText=field.getAnnotation(ExcelBooleanText.class);
								if(excelBooleanText!=null && !Boolean.class.isAssignableFrom(classField))
									throw new ExcelReaderException("The \"ExcelBooleanText\" annotation can only be assigned to boolean fields");
								try {
									if(excelBooleanText!=null) {
										String stringValue = cell.getStringCellValue();
										if(excelBooleanText.enable().equalsIgnoreCase(stringValue))
											value=true;
										else if(excelBooleanText.disable().equalsIgnoreCase(stringValue))
											value=false;
									}
									if (excelReadColumn.ignoreCellTypeString() && CellType.STRING.equals(cell.getCellType()) && !String.class.isAssignableFrom(classField)) {
										String stringValue=cell.getStringCellValue();
										if (Number.class.isAssignableFrom(classField))
											value=this.getNumberValue(stringValue, classField);
										else if(Calendar.class.isAssignableFrom(classField)) {
											Date dateValue = convertStringToDate(stringValue, field);
											if(dateValue!=null) {
												Calendar calendar = Calendar.getInstance();
												calendar.setTime(dateValue);
												value = calendar;
											}
										} else if(Date.class.isAssignableFrom(classField)) 
											value = convertStringToDate(stringValue, field);
										else if (Boolean.class.isAssignableFrom(classField))
											value=Boolean.parseBoolean(stringValue);
										else if (Character.class.isAssignableFrom(classField)) {
											if (StringUtils.isNotEmpty(stringValue)) {
												stringValue = stringValue.trim();
												if (stringValue.length() > 1)
													throw new ExcelReaderException(ExcelExceptionType.CHARACTER_NOT_VALID, field.getName());
												value = stringValue.charAt(0);
											}
										}	
											
											
									}else if (Number.class.isAssignableFrom(classField)) {
										value = getNumberValue(cell, classField);
									} else if (String.class.isAssignableFrom(classField)) {
										DataFormat fmt = workbook.createDataFormat();
										cell.getCellStyle().setDataFormat(fmt.getFormat("text"));
										// cell.setCellType(CellType.STRING);
										String stringValue = cell.getStringCellValue().trim();
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
										String stringValue=cell.getStringCellValue();
										if (StringUtils.isNotEmpty(stringValue)) {
											stringValue = stringValue.trim();
											if (stringValue.length() > 1)
												throw new ExcelReaderException(ExcelExceptionType.CHARACTER_NOT_VALID, field.getName());
											value = stringValue.charAt(0);
										}
									}	 else {
										logger.debug("The type \"" + field.getType().getSimpleName() + "\" is not manage");
									}
								} catch (Exception e) {
									logger.error("The \""+field.getName()+"\" field throw exception");
									if (!CellType.FORMULA.equals(cell.getCellType()))
										throw e;
									else
										logger.warn("The formula cell returns a null value");
								}

								if (value != null) {
									String nameColumn = ("" + field.getName().charAt(0)).toUpperCase() + field.getName().substring(1);
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
	 * Gets the number value.
	 *
	 * @param cell       the cell
	 * @param classField the class field
	 * @return the number value
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
		}
		return value;
	}

	/**
	 * Sets the map method.
	 *
	 * @param mapMethod  the map method
	 * @param listMethod the list method
	 */
	private void setMapMethod(Map<String, Method> mapMethod, Method[] listMethod) {
		for (Method method : listMethod)
			mapMethod.put(method.getName(), method);
	}

	/**
	 * Gets the map columns.
	 *
	 * @param header         the header
	 * @param excelReadSheet the excel read sheet
	 * @return the map columns
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
