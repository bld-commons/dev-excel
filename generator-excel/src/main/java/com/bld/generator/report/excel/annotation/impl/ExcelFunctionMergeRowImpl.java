/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.annotation.impl.ExcelFunctionMergeRowImpl.java
*/
package com.bld.generator.report.excel.annotation.impl;

import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelColumnWidth;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelMergeRow;
import com.bld.generator.report.excel.annotation.ExcelNumberFormat;
import com.bld.generator.report.excel.annotation.ExcelSubtotal;


/**
 * The Class ExcelFunctionMergeRowImpl.
 */
public class ExcelFunctionMergeRowImpl extends ExcelAnnotationImpl<ExcelFunctionMergeRow> {

	/** The excel cells layout. */
	private ExcelCellLayout excelCellsLayout;

	/** The excel column. */
	private ExcelColumn excelColumn;

	/** The excel merge row. */
	private ExcelMergeRow excelMergeRow;

	/** The excel function. */
	private ExcelFunction excelFunction;

	/** The excel column width. */
	private ExcelColumnWidth excelColumnWidth;

	/** The excel header cell layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;

	/** The excel subtotal. */
	private ExcelSubtotal excelSubtotal;
	
	private ExcelNumberFormat excelNumberFormat;

	/**
	 * Instantiates a new excel function merge row impl.
	 */
	public ExcelFunctionMergeRowImpl() {
		super();
	}

	/**
	 * Instantiates a new excel function merge row impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn      the excel column
	 * @param excelMergeRow    the excel merge row
	 * @param excelFunction    the excel function
	 * @param excelColumnWidth the excel column width
	 */
	public ExcelFunctionMergeRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, ExcelMergeRow excelMergeRow, ExcelFunction excelFunction, ExcelColumnWidth excelColumnWidth) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelMergeRow = excelMergeRow;
		this.excelFunction = excelFunction;
		this.excelColumnWidth = excelColumnWidth;
	}

	/**
	 * Instantiates a new excel function merge row impl.
	 *
	 * @param excelCellsLayout      the excel cells layout
	 * @param excelColumn           the excel column
	 * @param excelMergeRow         the excel merge row
	 * @param excelFunction         the excel function
	 * @param excelColumnWidth      the excel column width
	 * @param excelHeaderCellLayout the excel header cell layout
	 * @param excelSubtotal         the excel subtotal
	 */
	public ExcelFunctionMergeRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, ExcelMergeRow excelMergeRow, ExcelFunction excelFunction, ExcelColumnWidth excelColumnWidth, ExcelHeaderCellLayout excelHeaderCellLayout,
			ExcelSubtotal excelSubtotal) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelMergeRow = excelMergeRow;
		this.excelFunction = excelFunction;
		this.excelColumnWidth = excelColumnWidth;
		this.excelHeaderCellLayout = excelHeaderCellLayout;
		this.excelSubtotal = excelSubtotal;
	}

	public ExcelFunctionMergeRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, ExcelMergeRow excelMergeRow, ExcelFunction excelFunction, ExcelColumnWidth excelColumnWidth, ExcelHeaderCellLayout excelHeaderCellLayout,
			ExcelSubtotal excelSubtotal, ExcelNumberFormat excelNumberFormat) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelMergeRow = excelMergeRow;
		this.excelFunction = excelFunction;
		this.excelColumnWidth = excelColumnWidth;
		this.excelHeaderCellLayout = excelHeaderCellLayout;
		this.excelSubtotal = excelSubtotal;
		this.excelNumberFormat = excelNumberFormat;
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

	public ExcelNumberFormat getExcelNumberFormat() {
		return excelNumberFormat;
	}

	public void setExcelNumberFormat(ExcelNumberFormat excelNumberFormat) {
		this.excelNumberFormat = excelNumberFormat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelCellsLayout == null) ? 0 : excelCellsLayout.hashCode());
		result = prime * result + ((excelColumn == null) ? 0 : excelColumn.hashCode());
		result = prime * result + ((excelColumnWidth == null) ? 0 : excelColumnWidth.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
		result = prime * result + ((excelMergeRow == null) ? 0 : excelMergeRow.hashCode());
		result = prime * result + ((excelNumberFormat == null) ? 0 : excelNumberFormat.hashCode());
		result = prime * result + ((excelSubtotal == null) ? 0 : excelSubtotal.hashCode());
		return result;
	}

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
		if (excelMergeRow == null) {
			if (other.excelMergeRow != null)
				return false;
		} else if (!excelMergeRow.equals(other.excelMergeRow))
			return false;
		if (excelNumberFormat == null) {
			if (other.excelNumberFormat != null)
				return false;
		} else if (!excelNumberFormat.equals(other.excelNumberFormat))
			return false;
		if (excelSubtotal == null) {
			if (other.excelSubtotal != null)
				return false;
		} else if (!excelSubtotal.equals(other.excelSubtotal))
			return false;
		return true;
	}

}
