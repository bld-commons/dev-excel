/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ExcelDropDownBuilder.java
*/
package com.bld.generator.report.excel.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.data.DropDownCell;
import com.bld.generator.report.excel.data.InfoColumn;
import com.bld.generator.report.excel.data.SheetHeader;
import com.bld.generator.report.excel.dropdown.BoxMessage;
import com.bld.generator.report.excel.dropdown.DropDown;
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;

/**
 * The Class ExcelDropDownBuilder.
 */
@Component
public class ExcelDropDownBuilder {

	/** The Constant PATTERN. */
	private static final String PATTERN = "\\$\\{.*?}";

	/**
	 * Adds the drop down.
	 *
	 * @param dropDownCell   the drop down cell
	 * @param mapFieldColumn the map field column
	 * @param mapSheet       the map sheet
	 * @throws Exception the exception
	 */
	public void addDropDown(DropDownCell dropDownCell, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		SheetHeader sheetHeader = dropDownCell.getSheetHeader();
		Sheet sheet = dropDownCell.getSheet();
		DataValidationConstraint constraint = null;
		DataValidation dataValidation = null;
		DataValidationHelper validationHelper = sheet.getDataValidationHelper();
		CellRangeAddressList addressList = new CellRangeAddressList(dropDownCell.getFirstRow(), dropDownCell.getLastRow(), dropDownCell.getFirstCol(), dropDownCell.getLastCol());
		if (sheetHeader.getExcelDropDown() != null) {
			ExcelDropDown excelDropDown = sheetHeader.getExcelDropDown();
			String areaRange = excelDropDown.areaRange();
			Map<String, com.bld.generator.report.excel.data.KeyParameterAlias> mapExcelFormulaAlias = ExcelBuildFunctionUtility.mapExcelFormulaAlias(excelDropDown.alias());
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, dropDownCell.getIndexRow(), areaRange, RowStartEndType.ROW_EMPTY, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_START, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_END, mapExcelFormulaAlias, mapFieldColumn, mapSheet);

			Pattern p = Pattern.compile(PATTERN);
			Matcher m = p.matcher(areaRange);
			if (m.find())
				throw new ExcelGeneratorException("The formula '" + areaRange + "' is not valid");
			constraint = validationHelper.createFormulaListConstraint(areaRange);
			dataValidation = validationHelper.createValidation(constraint, addressList);
			dataValidation.setSuppressDropDownArrow(excelDropDown.suppressDropDownArrow());
			if (excelDropDown.errorBox().show()) {
				dataValidation.setShowErrorBox(excelDropDown.errorBox().show());
				dataValidation.createErrorBox(excelDropDown.errorBox().title(), excelDropDown.errorBox().message());
				dataValidation.setErrorStyle(excelDropDown.errorBox().boxStyle().getValue());
			}

		} else {
			DropDown<?> dropDown = (DropDown<?>) sheetHeader.getValue();
			if (CollectionUtils.isNotEmpty(dropDown.getList())) {
				String[] list = new String[dropDown.getList().size()];
				int i = 0;
				SimpleDateFormat sdf = null;
				if (sheetHeader.getExcelDate() != null)
					sdf = new SimpleDateFormat(sheetHeader.getExcelDate().value().getValue());
				for (Object item : dropDown.getList()) {
					if (item instanceof Date)
						list[i] = sdf.format((Date) item);
					else if (item instanceof Calendar)
						list[i] = sdf.format(((Calendar) item).getTime());
					else if (item instanceof Timestamp)
						list[i] = sdf.format(new Date(((Timestamp) item).getTime()));
					else
						list[i] = item.toString();

					i++;
				}
				constraint = validationHelper.createExplicitListConstraint(list);
				dataValidation = validationHelper.createValidation(constraint, addressList);
				dataValidation.setSuppressDropDownArrow(dropDown.isSuppressDropDownArrow());
				if (dropDown.getBoxMessage() != null) {
					BoxMessage boxMessage = dropDown.getBoxMessage();
					dataValidation.setShowErrorBox(boxMessage.isShow());
					dataValidation.createErrorBox(boxMessage.getTitle(), boxMessage.getMessage());
					dataValidation.setErrorStyle(boxMessage.getBoxStyle().getValue());
				}

			}

		}

		sheet.addValidationData(dataValidation);
	}

	/**
	 * Manage drop down.
	 *
	 * @param sheet          the sheet
	 * @param sheetHeader    the sheet header
	 * @param firstRow       the first row
	 * @param lastRow        the last row
	 * @param firstCol       the first col
	 * @param lastCol        the last col
	 * @param indexRow       the index row
	 * @param listDropDown   the list drop down
	 * @param mapFieldColumn the map field column
	 * @param mapSheet       the map sheet
	 */
	public void manageDropDown(Sheet sheet, SheetHeader sheetHeader, int firstRow, int lastRow, int firstCol, int lastCol, Integer indexRow, List<DropDownCell> listDropDown, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) {
		if (sheetHeader.isDropDown()) {
			DropDownCell dropDownCell = null;
			dropDownCell = new DropDownCell(sheet, sheetHeader, firstRow, lastRow, firstCol, lastCol, indexRow);
			try {
				this.addDropDown(dropDownCell, mapFieldColumn, mapSheet);
			} catch (Exception e) {
				listDropDown.add(dropDownCell);
			}
		}
	}

}
