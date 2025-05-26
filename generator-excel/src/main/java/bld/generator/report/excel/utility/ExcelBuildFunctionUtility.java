/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.utility.ExcelBuildFunctionUtility.java
 */
package bld.generator.report.excel.utility;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bld.common.spreadsheet.constant.RowStartEndType;
import bld.common.spreadsheet.exception.ExcelGeneratorException;
import bld.common.spreadsheet.utils.ExcelUtils;
import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.DynamicRowSheet;
import bld.generator.report.excel.MergeSheet;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.SheetComponent;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.SheetDynamicData;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelFormulaAlias;
import bld.generator.report.excel.data.InfoColumn;

/**
 * The Class ExcelBuildFunctionUtility.
 */
public class ExcelBuildFunctionUtility {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ExcelBuildFunctionUtility.class);

	/** The Constant PATTERN. */
	private static final String PATTERN = "\\$\\{.*?}";

	// private static final String PATTERN_QUAD = "\\[.*?\\]";

	/** The Constant $. */
	private static final String $ = "${";

	/**
	 * Builds the function.
	 *
	 * @param sheet the sheet
	 * @param indexRow the index row
	 * @param function the function
	 * @param rowStartEndType the row start end type
	 * @param alias the alias
	 * @param mapFieldColumn the map field column
	 * @param mapSheet the map sheet
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String buildFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType, ExcelFormulaAlias[] alias, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		function = function.replace(ExcelUtils.ENV_SHEET_NAME, "\"" + sheet.getSheetName() + "\"");
		Matcher matcher = ExcelUtils.matcher(PATTERN, function);
		Map<String, ExcelFormulaAlias> mapExcelFormulaAlias = new HashMap<>();
		for (ExcelFormulaAlias formulaAlias : alias)
			mapExcelFormulaAlias.put(formulaAlias.alias().replace("'", BaseSheet.APOS), formulaAlias);

		while (matcher.find()) {
			String parameter = matcher.group();
			String keyParameter = parameter.replace($, "").replace(rowStartEndType.getValue(), "").replace("}", "").replace("'", BaseSheet.APOS).trim();
			boolean blockColumn = false;
			boolean blockRow = false;
			String sheetName = sheet.getSheetName();
			if (mapExcelFormulaAlias.containsKey(keyParameter)) {
				ExcelFormulaAlias excelFormulaAlias = mapExcelFormulaAlias.get(keyParameter);
				String sheetPoint = "";
				if (StringUtils.isNotEmpty(excelFormulaAlias.sheet()))
					sheetPoint = excelFormulaAlias.sheet() + ".";
				keyParameter = sheetPoint + mapExcelFormulaAlias.get(keyParameter).coordinate();
				blockColumn = excelFormulaAlias.blockColumn();
				blockRow = excelFormulaAlias.blockRow();
				// parameter=parameter.replace(excelFormulaAlias.alias(),keyParameter);
			}

			String appFunction = buildFunction(sheet, keyParameter, indexRow, rowStartEndType, sheetName, function, parameter, blockColumn, blockRow, mapFieldColumn, mapSheet);
			if (StringUtils.isNotEmpty(appFunction))
				function = appFunction;

		}
		return function;
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet           the sheet
	 * @param keyParameter    the key parameter
	 * @param indexRow        the index row
	 * @param rowStartEndType the row start end type
	 * @param sheetName       the sheet name
	 * @param function        the function
	 * @param parameter       the parameter
	 * @param blockColumn     the block column
	 * @param blockRow        the block row
	 * @param mapFieldColumn the map field column
	 * @param mapSheet the map sheet
	 * @return the string
	 * @throws Exception the exception
	 */
	private static String buildFunction(Sheet sheet, String keyParameter, Integer indexRow, RowStartEndType rowStartEndType, String sheetName, String function, String parameter, boolean blockColumn, boolean blockRow,
			Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		String exprenssionIndex = "";
		if (keyParameter.contains("[")) {
			exprenssionIndex = keyParameter.substring(keyParameter.indexOf("[") + 1, keyParameter.length() - 1);
			keyParameter = keyParameter.substring(0, keyParameter.indexOf("["));
		}
		boolean fieldValue = keyParameter.contains(RowStartEndType.VALUE.getValue());
		if (fieldValue)
			keyParameter = keyParameter.replace(RowStartEndType.VALUE.getValue(), "");
		Integer row = null;
		InfoColumn infoColumn = null;
		if (mapFieldColumn.containsKey(ExcelUtils.getKeyColumn(sheet, keyParameter))) {
			infoColumn = (InfoColumn) mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, keyParameter));
			row = indexRow;
			Integer start = null;
			Integer end = null;
			Double evalute = null;
			if (RowStartEndType.ROW_HEADER.equals(rowStartEndType)) {
				row = infoColumn.getRowHeader();
				if (row == null)
					throw new ExcelGeneratorException("The header not exist");
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					evalute = ExcelUtils.evaluate(exprenssionIndex, "header", row);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				}
			} else if (RowStartEndType.ROW_EMPTY.equals(rowStartEndType) && !infoColumn.getMapRowMergeRow().isEmpty() && infoColumn.getMapRowMergeRow().containsKey(indexRow)) {
				row = infoColumn.getMapRowMergeRow().get(indexRow).getRowStart();
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					evalute = ExcelUtils.evaluate(exprenssionIndex, "row", row);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				}
			} else if (row == null && RowStartEndType.ROW_START.equals(rowStartEndType)) {
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					start = infoColumn.getFirstRow();
					evalute = ExcelUtils.evaluate(exprenssionIndex, "start", start);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				} else
					row = infoColumn.getFirstRow();

			} else if (row == null && RowStartEndType.ROW_END.equals(rowStartEndType)) {
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					end = infoColumn.getLastRow();
					evalute = ExcelUtils.evaluate(exprenssionIndex, "end", end);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				} else
					row = infoColumn.getLastRow();
			} else if (row != null && StringUtils.isNotEmpty(exprenssionIndex)) {
				switch (rowStartEndType) {
				case ROW_EMPTY:
					evalute = ExcelUtils.evaluate(exprenssionIndex, "row", row);
					break;
				case ROW_END:
					evalute = ExcelUtils.evaluate(exprenssionIndex, "end", row);
					break;
				case ROW_HEADER:
					row = infoColumn.getRowHeader();
					evalute = ExcelUtils.evaluate(exprenssionIndex, "header", row);
					break;
				case ROW_START:
					evalute = ExcelUtils.evaluate(exprenssionIndex, "start", row);
					break;
				default:
					break;

				}
				if (evalute != null)
					row = evalute.intValue();
				else
					return null;
			}
			try {

				if (row != null) {
					if (keyParameter.contains(".")) {

						sheetName = keyParameter.substring(0, keyParameter.lastIndexOf("."));

						if (!fieldValue) {
							sheetName = "'" + sheetName.replace("'", BaseSheet.APOS) + "'!";

							if (function.contains(sheetName))
								function = function.replace(parameter, ExcelUtils.coordinateCalculation(row + 1, infoColumn.getColumnNum(), blockColumn, blockRow));
							else
								function = function.replace(parameter, sheetName + ExcelUtils.coordinateCalculation(row + 1, infoColumn.getColumnNum(), blockColumn, blockRow));
						}
					} else if (!fieldValue)
						function = function.replace(parameter, ExcelUtils.coordinateCalculation(row + 1, infoColumn.getColumnNum(), blockColumn, blockRow));
				}

			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		if (fieldValue) {
			BaseSheet baseSheet = mapSheet.get(sheetName);
			Object value = null;
			if (baseSheet instanceof MergeSheet) {
				MergeSheet mergeSheet = (MergeSheet) baseSheet;

				for (SheetComponent bs : mergeSheet.getListSheet()) {
					value = getStaticValue(keyParameter, row != null && infoColumn != null ? row - 1 - infoColumn.getRowHeader() : null, bs);
					if (value != null)
						break;
				}

			} else
				value = getStaticValue(keyParameter, row != null && infoColumn != null ? row - 1 - infoColumn.getRowHeader() : null, (SheetComponent) baseSheet);
			if (value != null)
				function = function.replace(parameter, value.toString());
		}
		return function;
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet the sheet
	 * @param indexRow the index row
	 * @param function the function
	 * @param rowStartEndType the row start end type
	 * @param blockColumn the block column
	 * @param blockRow the block row
	 * @param mapFieldColumn the map field column
	 * @param mapSheet the map sheet
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String buildFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType, boolean blockColumn, boolean blockRow, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		function = function.replace(ExcelUtils.ENV_SHEET_NAME, "\"" + sheet.getSheetName() + "\"");
		Matcher matcher = ExcelUtils.matcher(PATTERN, function);

		while (matcher.find()) {
			String sheetName = sheet.getSheetName();
			String parameter = matcher.group();
			String keyParameter = parameter.replace($, "").replace(rowStartEndType.getValue(), "").replace("}", "").replace("'", BaseSheet.APOS).trim();
			String appFunction = buildFunction(sheet, keyParameter, indexRow, rowStartEndType, sheetName, function, parameter, blockColumn, blockRow, mapFieldColumn, mapSheet);
			if (StringUtils.isNotEmpty(appFunction))
				function = appFunction;

		}
		return function;
	}

	/**
	 * Gets the static value.
	 *
	 * @param keyParameter the key parameter
	 * @param row the row
	 * @param sheetComponent the sheet component
	 * @return the static value
	 * @throws NoSuchFieldException the no such field exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 * @throws ExcelGeneratorException the excel generator exception
	 */
	private static Object getStaticValue(String keyParameter, Integer row, SheetComponent sheetComponent) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ExcelGeneratorException {
		Object value = null;
		if (sheetComponent instanceof SheetDynamicData) {
			SheetDynamicData<?> sheetDynamicData = (SheetDynamicData<?>) sheetComponent;
			DynamicRowSheet dynamicRowShet = sheetDynamicData.getListRowSheet().get(row);
			if (sheetDynamicData.getMapExtraColumnAnnotation().containsKey(keyParameter))
				value = dynamicRowShet.getMapValue().get(keyParameter);
			else if (dynamicRowShet.getClass().getDeclaredField(keyParameter) != null)
				value = PropertyUtils.getProperty(dynamicRowShet, keyParameter);

		} else if (sheetComponent instanceof SheetData) {
			SheetData<?> sheetData = (SheetData<?>) sheetComponent;
			RowSheet rowSheet = sheetData.getListRowSheet().get(row);
			if (rowSheet.getClass().getDeclaredField(keyParameter) != null)
				value = PropertyUtils.getProperty(rowSheet, keyParameter);

		} else if (sheetComponent instanceof SheetSummary) {
			if (sheetComponent.getClass().getDeclaredField(keyParameter) != null)
				value = PropertyUtils.getProperty(sheetComponent, keyParameter);
		}

		return value;
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet the sheet
	 * @param indexRow the index row
	 * @param function the function
	 * @param rowStartEndType the row start end type
	 * @param mapFieldColumn the map field column
	 * @param mapSheet the map sheet
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String buildFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		return buildFunction(sheet, indexRow, function, rowStartEndType, false, false, mapFieldColumn, mapSheet);
	}

}
