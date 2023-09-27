/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionRowImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelColumnWidth;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelSubtotal;


/**
 * The Class ExcelFunctionRowImpl.
 */
public class ExcelFunctionRowImpl extends ExcelAnnotationImpl<ExcelFunctionRow>{

	/** The excel cells layout. */
	private ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	private ExcelColumn excelColumn;
	
	/** The excel function. */
	private ExcelFunction excelFunction;
	
	/** The excel column width. */
	private ExcelColumnWidth excelColumnWidth;
	
	/** The excel header cell layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;
	
	/** The excel subtotal. */
	private ExcelSubtotal excelSubtotal;
	
	/**
	 * Instantiates a new excel function row impl.
	 */
	public ExcelFunctionRowImpl() {
		super();
	}

	/**
	 * Instantiates a new excel function row impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn      the excel column
	 * @param excelFunction    the excel function
	 * @param excelColumnWidth the excel column width
	 */
	public ExcelFunctionRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, ExcelFunction excelFunction, ExcelColumnWidth excelColumnWidth) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelFunction = excelFunction;
		this.excelColumnWidth = excelColumnWidth;
	}
	
	
	


	/**
	 * Instantiates a new excel function row impl.
	 *
	 * @param excelCellsLayout      the excel cells layout
	 * @param excelColumn           the excel column
	 * @param excelFunction         the excel function
	 * @param excelColumnWidth      the excel column width
	 * @param excelHeaderCellLayout the excel header cell layout
	 * @param excelSubtotal         the excel subtotal
	 */
	public ExcelFunctionRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, ExcelFunction excelFunction, ExcelColumnWidth excelColumnWidth, ExcelHeaderCellLayout excelHeaderCellLayout, ExcelSubtotal excelSubtotal) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelFunction = excelFunction;
		this.excelColumnWidth = excelColumnWidth;
		this.excelHeaderCellLayout = excelHeaderCellLayout;
		this.excelSubtotal = excelSubtotal;
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
	 * Gets the excel header cell layout.
	 *
	 * @return the excel header cell layout
	 */
	public ExcelHeaderCellLayout getExcelHeaderCellLayout() {
		return excelHeaderCellLayout;
	}

	/**
	 * Sets the excel header cell layout.
	 *
	 * @param excelHeaderCellLayout the new excel header cell layout
	 */
	public void setExcelHeaderCellLayout(ExcelHeaderCellLayout excelHeaderCellLayout) {
		this.excelHeaderCellLayout = excelHeaderCellLayout;
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
	 * Gets the excel column width.
	 *
	 * @return the excel column width
	 */
	public ExcelColumnWidth getExcelColumnWidth() {
		return excelColumnWidth;
	}

	/**
	 * Sets the excel column width.
	 *
	 * @param excelColumnWidth the new excel column width
	 */
	public void setExcelColumnWidth(ExcelColumnWidth excelColumnWidth) {
		this.excelColumnWidth = excelColumnWidth;
	}

	
	
	/**
	 * Gets the excel subtotal.
	 *
	 * @return the excel subtotal
	 */
	public ExcelSubtotal getExcelSubtotal() {
		return excelSubtotal;
	}

	/**
	 * Sets the excel subtotal.
	 *
	 * @param excelSubtotal the new excel subtotal
	 */
	public void setExcelSubtotal(ExcelSubtotal excelSubtotal) {
		this.excelSubtotal = excelSubtotal;
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
		result = prime * result + ((excelColumnWidth == null) ? 0 : excelColumnWidth.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
		result = prime * result + ((excelSubtotal == null) ? 0 : excelSubtotal.hashCode());
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
		if (excelColumnWidth == null) {
			if (other.excelColumnWidth != null)
				return false;
		} else if (!excelColumnWidth.equals(other.excelColumnWidth))
			return false;
		if (excelFunction == null) {
			if (other.excelFunction != null)
				return false;
		} else if (!excelFunction.equals(other.excelFunction))
			return false;
		if (excelHeaderCellLayout == null) {
			if (other.excelHeaderCellLayout != null)
				return false;
		} else if (!excelHeaderCellLayout.equals(other.excelHeaderCellLayout))
			return false;
		if (excelSubtotal == null) {
			if (other.excelSubtotal != null)
				return false;
		} else if (!excelSubtotal.equals(other.excelSubtotal))
			return false;
		return true;
	}

	


	
}
