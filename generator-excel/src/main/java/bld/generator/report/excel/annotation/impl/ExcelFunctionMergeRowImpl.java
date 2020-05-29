/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionMergeRowImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelMergeRow;

/**
 * The Class ExcelFunctionMergeRowImpl.
 */
public class ExcelFunctionMergeRowImpl implements Cloneable{

	/** The excel cells layout. */
	private ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	private ExcelColumn excelColumn;
	
	/** The excel merge row. */
	private ExcelMergeRow excelMergeRow;
	
	/** The excel function. */
	private ExcelFunction excelFunction;
	
	/**
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

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
	 * @param excelColumn      the excel column
	 * @param excelMergeRow    the excel merge row
	 * @param excelFunction    the excel function
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelCellsLayout == null) ? 0 : excelCellsLayout.hashCode());
		result = prime * result + ((excelColumn == null) ? 0 : excelColumn.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelMergeRow == null) ? 0 : excelMergeRow.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelFunctionMergeRowImpl other = (ExcelFunctionMergeRowImpl) obj;
		if (excelCellsLayout == null) {
			if (other.excelCellsLayout != null)
				return false;
		} else if (!excelCellsLayout.equals(other.excelCellsLayout))
			return false;
		if (excelColumn == null) {
			if (other.excelColumn != null)
				return false;
		} else if (!excelColumn.equals(other.excelColumn))
			return false;
		if (excelFunction == null) {
			if (other.excelFunction != null)
				return false;
		} else if (!excelFunction.equals(other.excelFunction))
			return false;
		if (excelMergeRow == null) {
			if (other.excelMergeRow != null)
				return false;
		} else if (!excelMergeRow.equals(other.excelMergeRow))
			return false;
		return true;
	}

	
	
}
