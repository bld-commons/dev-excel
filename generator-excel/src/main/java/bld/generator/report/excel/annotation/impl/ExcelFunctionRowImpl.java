/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionRowImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;

/**
 * The Class ExcelFunctionRowImpl.
 */
public class ExcelFunctionRowImpl implements Cloneable{

	/** The excel cells layout. */
	private ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	private ExcelColumn excelColumn;
	
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
	 * @param excelColumn      the excel column
	 * @param excelFunction    the excel function
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
		ExcelFunctionRowImpl other = (ExcelFunctionRowImpl) obj;
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
		return true;
	}


	
}
