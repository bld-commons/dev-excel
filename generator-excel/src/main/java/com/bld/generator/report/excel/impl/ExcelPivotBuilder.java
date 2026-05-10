/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ExcelPivotBuilder.java
*/
package com.bld.generator.report.excel.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.generator.report.comparator.PivotColumnComparator;
import com.bld.generator.report.comparator.PivotColumnFunctionComparator;
import com.bld.generator.report.comparator.PivotRowComparator;
import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelPivot;
import com.bld.generator.report.excel.annotation.ExcelPivotColumn;
import com.bld.generator.report.excel.annotation.ExcelPivotColumnFunction;
import com.bld.generator.report.excel.annotation.ExcelPivotFilter;
import com.bld.generator.report.excel.annotation.ExcelPivotRow;
import com.bld.generator.report.excel.data.InfoColumn;

/**
 * The Class ExcelPivotBuilder.
 */
@Component
public class ExcelPivotBuilder {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ExcelPivotBuilder.class);

	/**
	 * Creates the pivot.
	 *
	 * @param sheet          the sheet
	 * @param sheetData      the sheet data
	 * @param firstRow       the first row
	 * @param firstColumn    the first column
	 * @param lastRow        the last row
	 * @param lastColumn     the last column
	 * @param indexRow       the index row
	 * @param mapFieldColumn the map field column
	 * @return the integer
	 */
	public Integer createPivot(XSSFSheet sheet, SheetData<?> sheetData, int firstRow, int firstColumn, int lastRow, int lastColumn, Integer indexRow, Map<String, InfoColumn> mapFieldColumn) {
		java.util.Set<Field> listField = SpreadsheetUtils.getListField(sheetData.getRowClass());
		String startCell = ExcelUtils.coordinateCalculation(firstRow, firstColumn, true, true);
		String endCell = ExcelUtils.coordinateCalculation(lastRow, lastColumn, true, true);
		logger.debug("start cell: " + startCell);
		logger.debug("end cell: " + endCell);
		AreaReference areaReference = new AreaReference(startCell + ":" + endCell, SpreadsheetVersion.EXCEL2007);
		ExcelPivot excelPivot = sheetData.getClass().getAnnotation(ExcelPivot.class);
		indexRow += 3;
		XSSFPivotTable pivotTable = sheet.createPivotTable(areaReference, new CellReference(indexRow, excelPivot.startColumn()));
		List<Field> listRow = new ArrayList<>();
		List<Field> listColumn = new ArrayList<>();
		List<Field> listColumnFunction = new ArrayList<>();
		for (Field field : listField) {
			int columnIndex = mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			if (field.isAnnotationPresent(ExcelPivotFilter.class))
				pivotTable.addReportFilter(columnIndex);
			if (field.isAnnotationPresent(ExcelPivotRow.class))
				listRow.add(field);
			if (field.isAnnotationPresent(ExcelPivotColumn.class))
				listColumn.add(field);
			if (field.isAnnotationPresent(ExcelPivotColumnFunction.class))
				listColumnFunction.add(field);
		}
		Collections.sort(listRow, new PivotRowComparator());
		Collections.sort(listColumn, new PivotColumnComparator());
		Collections.sort(listColumnFunction, new PivotColumnFunctionComparator());

		for (Field field : listRow) {
			int columnIndex = mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			pivotTable.addRowLabel(columnIndex);
		}
		for (Field field : listColumn) {
			int columnIndex = mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			pivotTable.addColLabel(columnIndex);
		}
		for (Field field : listColumnFunction) {
			int columnIndex = mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			ExcelPivotColumnFunction excelPivotColumnFunction = field.getAnnotation(ExcelPivotColumnFunction.class);
			for (DataConsolidateFunction dataConsolidateFunction : excelPivotColumnFunction.dataConsolidateFunction())
				pivotTable.addColumnLabel(dataConsolidateFunction, columnIndex);
		}

		return indexRow;
	}

}
