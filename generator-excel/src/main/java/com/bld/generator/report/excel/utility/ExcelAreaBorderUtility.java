/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.utility.ExcelAreaBorderUtility.java
*/
package com.bld.generator.report.excel.utility;

import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelAreaBorder;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;
import com.bld.generator.report.excel.annotation.ExcelSuperHeaders;
import com.bld.generator.report.excel.constant.BorderType;
import com.bld.generator.report.excel.data.InfoColumn;

/**
 * Static utility for applying area borders defined via {@link ExcelAreaBorder} annotations.
 */
public class ExcelAreaBorderUtility {


	/**
	 * Applies all area borders declared in {@code excelSheetLayout.areaBorder()} to the sheet.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param sheetData        the sheet data
	 * @param excelSheetLayout the sheet layout annotation
	 * @param mapFieldColumn   the field-to-column mapping
	 * @param mapSheet         the sheet name mapping
	 * @throws Exception the exception
	 */
	public static void applyAreaBorders(Workbook workbook, Sheet sheet, SheetData<? extends RowSheet> sheetData, ExcelSheetLayout excelSheetLayout, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		for (ExcelAreaBorder areaBorder : excelSheetLayout.areaBorder()) {
			String areaRange = areaBorder.areaRange();
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_EMPTY, mapFieldColumn, mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_END, mapFieldColumn, mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_HEADER, mapFieldColumn, mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_START, mapFieldColumn, mapSheet);
			CellRangeAddress region = CellRangeAddress.valueOf(areaRange);
			int firstRow = region.getFirstRow();
			int firstColumn = region.getFirstColumn();
			int lastRow = region.getLastRow();
			int lastColumn = region.getLastColumn();

			int rowSuperHeader = 0;
			if (areaBorder.includeSuperHeader() && sheetData.getClass().isAnnotationPresent(ExcelSuperHeaders.class)) {
				ExcelSuperHeaders excelSuperHeaders = sheetData.getClass().getAnnotation(ExcelSuperHeaders.class);
				rowSuperHeader = excelSuperHeaders.superHeaders().length;
			}
			if (sheet.getRow(firstRow - rowSuperHeader).getCell(firstColumn) != null && sheet.getRow(firstRow - rowSuperHeader).getCell(lastColumn) != null)
				firstRow = firstRow - rowSuperHeader;

			for (int count = firstRow; count <= lastRow; count++) {
				Cell cellLeft = sheet.getRow(count).getCell(firstColumn);
				Cell cellRight = sheet.getRow(count).getCell(lastColumn);
				setBorderArea(workbook, cellLeft, areaBorder.border().left(), BorderType.LEFT);
				setBorderArea(workbook, cellRight, areaBorder.border().right(), BorderType.RIGHT);
			}

			for (int count = firstColumn; count <= lastColumn; count++) {
				Cell cellTop = sheet.getRow(firstRow).getCell(count);
				Cell cellBottom = sheet.getRow(lastRow).getCell(count);
				setBorderArea(workbook, cellTop, areaBorder.border().top(), BorderType.TOP);
				setBorderArea(workbook, cellBottom, areaBorder.border().bottom(), BorderType.BOTTOM);
			}
		}
	}

	private static void setBorderArea(Workbook workbook, Cell cell, BorderStyle borderStyle, BorderType borderType) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.cloneStyleFrom(cell.getCellStyle());
		switch (borderType) {
		case BOTTOM:
			cellStyle.setBorderBottom(borderStyle);
			break;
		case LEFT:
			cellStyle.setBorderLeft(borderStyle);
			break;
		case RIGHT:
			cellStyle.setBorderRight(borderStyle);
			break;
		case TOP:
			cellStyle.setBorderTop(borderStyle);
			break;
		default:
			break;
		}
		cell.setCellStyle(cellStyle);
	}
}
