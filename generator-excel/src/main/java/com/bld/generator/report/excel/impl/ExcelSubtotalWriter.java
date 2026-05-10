/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ExcelSubtotalWriter.java
*/
package com.bld.generator.report.excel.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.data.LayoutCell;
import com.bld.generator.report.excel.data.SheetHeader;
import com.bld.generator.report.excel.data.SubtotalRow;
import com.bld.generator.report.excel.utility.ExcelLayoutUtility;

/**
 * The Class ExcelSubtotalWriter.
 */
@Component
public class ExcelSubtotalWriter {

	/** The excel layout utility. */
	@Autowired
	private ExcelLayoutUtility excelLayoutUtility;

	/**
	 * Map row sub totals.
	 *
	 * @param indexRow     the index row
	 * @param lastRowSheet the last row sheet
	 * @param emptyRows    the empty rows
	 * @param fieldName    the field name
	 * @param firstRow     the first row
	 * @param lastRow      the last row
	 * @param mapSubTotals the map sub totals
	 * @return the integer
	 * @throws IllegalAccessException    the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException     the no such method exception
	 */
	public Integer mapRowSubTotals(Integer indexRow, RowSheet lastRowSheet, List<SubtotalRow> emptyRows, String fieldName, Integer firstRow, Integer lastRow, Map<String, Integer> mapSubTotals) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		BeanWrapperImpl lastBeanWrapper = new BeanWrapperImpl(lastRowSheet);
		emptyRows.add(new SubtotalRow(indexRow++, String.valueOf(lastBeanWrapper.getPropertyValue(fieldName)), fieldName, firstRow, lastRow));
		firstRow = indexRow.intValue();
		mapSubTotals.put(fieldName, firstRow);
		return indexRow;
	}

	/**
	 * Gets the cell style subtotal.
	 *
	 * @param workbook        the workbook
	 * @param indexRow        the index row
	 * @param emptyRow        the empty row
	 * @param sheetHeader     the sheet header
	 * @param excelCellLayout the excel cell layout
	 * @param mapCellStyle    the map cell style
	 * @return the cell style subtotal
	 * @throws Exception the exception
	 */
	public CellStyle getCellStyleSubtotal(Workbook workbook, Integer indexRow, SubtotalRow emptyRow, SheetHeader sheetHeader, com.bld.generator.report.excel.annotation.ExcelCellLayout excelCellLayout, Map<LayoutCell, CellStyle> mapCellStyle) throws Exception {
		CellStyle cellStyle = null;
		sheetHeader.setExcelCellLayout(excelCellLayout);
		LayoutCell layoutCell = SpreadsheetUtils.reflectionAnnotation(new LayoutCell(), sheetHeader.getExcelCellLayout());
		if (sheetHeader.getExcelNumberFormat() != null && StringUtils.isNotBlank(sheetHeader.getExcelNumberFormat().value()))
			layoutCell.setNumberFormat(sheetHeader.getExcelNumberFormat().value());
		layoutCell.setColor(indexRow);
		if (!mapCellStyle.containsKey(layoutCell)) {
			cellStyle = this.excelLayoutUtility.createCellStyle(workbook, sheetHeader.getExcelCellLayout(), null, emptyRow.getEmptyRow());
			if (sheetHeader.getExcelNumberFormat() != null && StringUtils.isNotBlank(sheetHeader.getExcelNumberFormat().value()))
				cellStyle = this.excelLayoutUtility.dateCellStyle(workbook, cellStyle, sheetHeader.getExcelNumberFormat().value());
			mapCellStyle.put(layoutCell, cellStyle);
		}

		cellStyle = mapCellStyle.get(layoutCell);
		return cellStyle;
	}

}
