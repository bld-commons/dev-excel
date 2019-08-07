/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionRowImpl.
 */
public class ExcelFunctionRowImpl {

	/** The excel cells layout. */
	protected ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	protected ExcelColumn excelColumn;
	
	/** The excel function. */
	protected ExcelFunction excelFunction;

	/**
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunctionRow getExcelFunction() {
		return new ExcelFunctionRow() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionRow.class;
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
			public ExcelFunction excelFunction() {
				return excelFunction;
			}

		};
	}

	/**
	 * Instantiates a new excel function row impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn the excel column
	 * @param excelFunction the excel function
	 */
	public ExcelFunctionRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn,
			ExcelFunction excelFunction) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
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
	 * Sets the excel function.
	 *
	 * @param excelFunction the new excel function
	 */
	public void setExcelFunction(ExcelFunction excelFunction) {
		this.excelFunction = excelFunction;
	}

	
	
}
