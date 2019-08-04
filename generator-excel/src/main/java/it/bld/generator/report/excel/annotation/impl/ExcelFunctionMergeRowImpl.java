/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import it.bld.generator.report.excel.annotation.ExcelCellLayout;
import it.bld.generator.report.excel.annotation.ExcelColumn;
import it.bld.generator.report.excel.annotation.ExcelFunction;
import it.bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import it.bld.generator.report.excel.annotation.ExcelMergeRow;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionMergeRowImpl.
 */
public class ExcelFunctionMergeRowImpl {

	/** The excel cells layout. */
	protected ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	protected ExcelColumn excelColumn;
	
	/** The excel merge row. */
	protected ExcelMergeRow excelMergeRow;
	
	/** The excel function. */
	protected ExcelFunction excelFunction;

	/**
	 * Gets the excel function merge.
	 *
	 * @return the excel function merge
	 */
	public ExcelFunctionMergeRow getExcelFunctionMerge() {
		return new ExcelFunctionMergeRow() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionMergeRow.class;
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
			public ExcelMergeRow excelMergeRow() {
				return excelMergeRow;
			}

			@Override
			public ExcelFunction excelFunction() {
				return excelFunction;
			}

		};
	}

	/**
	 * Instantiates a new excel function merge row impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn the excel column
	 * @param excelMergeRow the excel merge row
	 * @param excelFunction the excel function
	 */
	public ExcelFunctionMergeRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn,
			ExcelMergeRow excelMergeRow, ExcelFunction excelFunction) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelMergeRow = excelMergeRow;
		this.excelFunction = excelFunction;
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
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction getExcelFunction() {
		return excelFunction;
	}

	/**
	 * Sets the excel function.
	 *
	 * @param excelFunction the new excel function
	 */
	public void setExcelFunction(ExcelFunction excelFunction) {
		this.excelFunction = excelFunction;
	}

}
