/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package com.bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelFunctionMerge;
import com.bld.generator.report.excel.annotation.ExcelMergeRow;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionMergeImpl.
 */
public class ExcelFunctionMergeImpl{

	
	/** The excel cells layout. */
	protected ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	protected ExcelColumn excelColumn;
	
	/** The function. */
	protected String function;
	
	/** The excel merge row. */
	protected ExcelMergeRow excelMergeRow;
	
	/** The name function. */
	protected String nameFunction;

	/**
	 * Gets the excel function merge.
	 *
	 * @return the excel function merge
	 */
	public ExcelFunctionMerge getExcelFunctionMerge() {
		return new ExcelFunctionMerge() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionMerge.class;
			}

			@Override
			public ExcelCellLayout excelCellsLayout() {
				return excelCellsLayout;
			}

			@Override
			public ExcelColumn excelColumn() {
				return excelColumn;
			}

			@Override
			public String function() {
				return function;
			}

			@Override
			public ExcelMergeRow excelMergeRow() {
				return excelMergeRow;
			}

			@Override
			public String nameFunction() {
				return nameFunction;
			}};
	}

	/**
	 * Instantiates a new excel function merge impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn the excel column
	 * @param function the function
	 * @param excelMergeRow the excel merge row
	 * @param nameFunction the name function
	 */
	public ExcelFunctionMergeImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, String function, ExcelMergeRow excelMergeRow, String nameFunction) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.function = function;
		this.excelMergeRow = excelMergeRow;
		this.nameFunction = nameFunction;
	}

	/**
	 * Gets the excel cells layout.
	 *
	 * @return the excel cells layout
	 */
	public ExcelCellLayout getExcelCellsLayout() {
		return excelCellsLayout;
	}

	/**
	 * Sets the excel cells layout.
	 *
	 * @param excelCellsLayout the new excel cells layout
	 */
	public void setExcelCellsLayout(ExcelCellLayout excelCellsLayout) {
		this.excelCellsLayout = excelCellsLayout;
	}

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		return excelColumn;
	}

	/**
	 * Sets the excel column.
	 *
	 * @param excelColumn the new excel column
	 */
	public void setExcelColumn(ExcelColumn excelColumn) {
		this.excelColumn = excelColumn;
	}

	/**
	 * Gets the function.
	 *
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Sets the function.
	 *
	 * @param function the new function
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * Gets the excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow getExcelMergeRow() {
		return excelMergeRow;
	}

	/**
	 * Sets the excel merge row.
	 *
	 * @param excelMergeRow the new excel merge row
	 */
	public void setExcelMergeRow(ExcelMergeRow excelMergeRow) {
		this.excelMergeRow = excelMergeRow;
	}

	/**
	 * Gets the name function.
	 *
	 * @return the name function
	 */
	public String getNameFunction() {
		return nameFunction;
	}

	/**
	 * Sets the name function.
	 *
	 * @param nameFunction the new name function
	 */
	public void setNameFunction(String nameFunction) {
		this.nameFunction = nameFunction;
	}

	
	
	
}
