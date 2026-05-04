/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ExcelSheetHeaderBuilder.java
*/
package com.bld.generator.report.excel.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRows;
import com.bld.generator.report.comparator.SheetColumnComparator;
import com.bld.generator.report.excel.data.SheetHeader;
import com.bld.generator.report.excel.dropdown.DropDown;

/**
 * The Class ExcelSheetHeaderBuilder.
 */
@Component
public class ExcelSheetHeaderBuilder {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ExcelSheetHeaderBuilder.class);

	/** The Constant SHEET_HEADER_TEMPLATE_CACHE. */
	private static final Map<Class<?>, List<SheetHeader>> SHEET_HEADER_TEMPLATE_CACHE = new ConcurrentHashMap<>();

	/** The value props. */
	@Autowired
	private ValueProps valueProps;

	/** The excel image manager. */
	@Autowired
	private ExcelImageManager excelImageManager;

	/**
	 * Gets the list sheet header.
	 *
	 * @param classRow  the class row
	 * @param baseSheet the base sheet
	 * @return the list sheet header
	 * @throws Exception the exception
	 */
	public List<SheetHeader> getListSheetHeader(Class<?> classRow, BaseSheet baseSheet) throws Exception {
		logger.debug("Row: " + classRow.getSimpleName());
		if (baseSheet != null)
			return buildSheetHeaderList(classRow, baseSheet);
		List<SheetHeader> cached = SHEET_HEADER_TEMPLATE_CACHE.get(classRow);
		if (cached != null)
			return cloneSheetHeaderList(cached);
		List<SheetHeader> listSheetHeader = buildSheetHeaderList(classRow, null);
		SHEET_HEADER_TEMPLATE_CACHE.put(classRow, Collections.unmodifiableList(listSheetHeader));
		return listSheetHeader;
	}

	/**
	 * Builds the sheet header list.
	 *
	 * @param classRow  the class row
	 * @param baseSheet the base sheet
	 * @return the list
	 * @throws Exception the exception
	 */
	private List<SheetHeader> buildSheetHeaderList(Class<?> classRow, BaseSheet baseSheet) throws Exception {
		Set<String> listTitle = new HashSet<>();
		List<SheetHeader> listSheetHeader = new ArrayList<>();
		Set<Field> listField = SpreadsheetUtils.getListField(classRow);
		for (Field field : listField) {
			ExcelColumn column = field.getAnnotation(ExcelColumn.class);
			if (column != null && !column.ignore()) {
				Object value = null;
				if (baseSheet != null)
					value = new BeanWrapperImpl(baseSheet).getPropertyValue(field.getName());
				SheetHeader sheetHeader = new SheetHeader(field, value);
				if (value != null) {
					value = this.excelImageManager.manageExcelImage(sheetHeader, value);
					sheetHeader.setValue(value);
				}

				if (field.isAnnotationPresent(ExcelDropDown.class) && field.getClass().isAssignableFrom(DropDown.class))
					throw new ExcelGeneratorException("The following annotation @ExcelDropDown can not be assigned on fields of classes type DropDown");
				if (field.isAnnotationPresent(ExcelDropDown.class))
					sheetHeader.setExcelDropDown(field.getAnnotation(ExcelDropDown.class));
				listSheetHeader.add(sheetHeader);
				if (listTitle.contains(column.name()))
					logger.warn("Exist another equal column with columnName= \"" + column.name() + "\" for the same sheet!!!");
				listTitle.add(column.name());
			}
		}
		if (classRow.isAnnotationPresent(ExcelFunctionRows.class)) {
			ExcelFunctionRows excelFunctionRows = classRow.getAnnotation(ExcelFunctionRows.class);
			for (ExcelFunctionRow excelFunction : excelFunctionRows.excelFunctions()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunction.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunction.excelCellsLayout());
				sheetHeader.setExcelFunction(excelFunction.excelFunction());
				sheetHeader.setExcelColumnWidth(excelFunction.excelColumnWidth());
				sheetHeader.setExcelHeaderCellLayout(excelFunction.excelHeaderCellLayout());
				sheetHeader.setExcelSubtotal(excelFunction.excelSubtotal());
				sheetHeader.setExcelNumberFormat(excelFunction.excelNumberFormat());
				listSheetHeader.add(sheetHeader);
			}
			for (ExcelFunctionMergeRow excelFunctionMerge : excelFunctionRows.excelFunctionMerges()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunctionMerge.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunctionMerge.excelCellsLayout());
				sheetHeader.setExcelFunction(excelFunctionMerge.excelFunction());
				sheetHeader.setExcelMergeRow(excelFunctionMerge.excelMergeRow());
				sheetHeader.setExcelColumnWidth(excelFunctionMerge.excelColumnWidth());
				sheetHeader.setExcelHeaderCellLayout(excelFunctionMerge.excelHeaderCellLayout());
				sheetHeader.setExcelSubtotal(excelFunctionMerge.excelSubtotal());
				sheetHeader.setExcelNumberFormat(excelFunctionMerge.excelNumberFormat());
				listSheetHeader.add(sheetHeader);
			}
		}
		Collections.sort(listSheetHeader, new SheetColumnComparator(this.valueProps));
		return listSheetHeader;
	}

	/**
	 * Clone sheet header list.
	 *
	 * @param source the source
	 * @return the list
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	private List<SheetHeader> cloneSheetHeaderList(List<SheetHeader> source) throws CloneNotSupportedException {
		List<SheetHeader> result = new ArrayList<>(source.size());
		for (SheetHeader sheetHeader : source)
			result.add((SheetHeader) sheetHeader.clone());
		return result;
	}

}
