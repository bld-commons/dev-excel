/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.data.ExtraColumnAnnotation.java
*/
package com.bld.generator.report.excel.data;

import jakarta.validation.constraints.NotNull;

import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelColumnWidth;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelMergeRow;
import com.bld.generator.report.excel.annotation.ExcelSubtotal;
import com.bld.generator.report.excel.annotation.impl.ExcelBooleanTextImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelDropDownImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelSubtotalImpl;

/**
 * The Class ExtraColumnAnnotation.<br>
 * ExtraColumnAnnotation is used to configure the dynamic cells type and the
 * style.<br>
 * It must be set on mapExtraColumnAnnotation within the
 * {@link com.bld.generator.report.excel.SheetDynamicData} classes.
 */
public class ExtraColumnAnnotation {

	/**
	 * The excel column.<br>
	 * It is not null
	 */
	@NotNull
	private ExcelColumn excelColumn;

	/**
	 * The excel cell layout. <br>
	 * it is not null.
	 */
	@NotNull
	private ExcelCellLayout excelCellLayout;

	/** The excel date. */
	private ExcelDate excelDate;

	/** The excel column width. */
	private ExcelColumnWidth excelColumnWidth;

	/** The excel merge row. */
	private ExcelMergeRow excelMergeRow;

	/** The excel function. */
	private ExcelFunction excelFunction;

	/** The excel header layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;

	/** The excel drop down. */
	private ExcelDropDown excelDropDown;

	/** The excel subtotal. */
	private ExcelSubtotal excelSubtotal;

	/** The excel boolean text. */
	private ExcelBooleanText excelBooleanText;

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayoutImpl the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayoutImpl excelCellLayoutImpl) {
		if (excelCellLayoutImpl != null)
			this.excelCellLayout = excelCellLayoutImpl.getAnnotation();
	}

	/**
	 * Sets the excel boolean text.
	 *
	 * @param excelBooleanTextImpl the new excel boolean text
	 */
	public void setExcelBooleanText(ExcelBooleanTextImpl excelBooleanTextImpl) {
		if (excelBooleanTextImpl != null)
			this.excelBooleanText = excelBooleanTextImpl.getAnnotation();
	}

	/**
	 * Sets the excel date.
	 *
	 * @param excelDateImpl the new excel date
	 */
	public void setExcelDate(ExcelDateImpl excelDateImpl) {
		if (excelDateImpl != null)
			this.excelDate = excelDateImpl.getAnnotation();
	}

	/**
	 * Sets the excel column.
	 *
	 * @param excelColumnImpl the new excel column
	 */
	public void setExcelColumn(ExcelColumnImpl excelColumnImpl) {
		if (excelColumnImpl != null)
			this.excelColumn = excelColumnImpl.getAnnotation();
	}

	/**
	 * Sets the excel function.
	 *
	 * @param excelFunctionImpl the new excel function
	 */
	public void setExcelFunction(ExcelFunctionImpl excelFunctionImpl) {
		if (excelFunctionImpl != null)
			this.excelFunction = excelFunctionImpl.getAnnotation();
	}

	/**
	 * Sets the excel merge row.
	 *
	 * @param excelMergeRowImpl the new excel merge row
	 */
	public void setExcelMergeRow(ExcelMergeRowImpl excelMergeRowImpl) {
		if (excelMergeRowImpl != null)
			this.excelMergeRow = excelMergeRowImpl.getAnnotation();
	}

	/**
	 * Sets the excel header layout.
	 *
	 * @param excelHeaderCellLayoutImpl the new excel header layout
	 */
	public void setExcelHeaderCellLayout(ExcelHeaderCellLayoutImpl excelHeaderCellLayoutImpl) {
		if (excelHeaderCellLayoutImpl != null)
			this.excelHeaderCellLayout = excelHeaderCellLayoutImpl.getAnnotation();
	}

	/**
	 * Sets the excel column width.
	 *
	 * @param excelColumnWidthImpl the new excel column width
	 */
	public void setExcelColumnWidth(ExcelColumnWidthImpl excelColumnWidthImpl) {
		if (excelColumnWidthImpl != null)
			this.excelColumnWidth = excelColumnWidthImpl.getAnnotation();
	}

	/**
	 * Sets the excel drop down.
	 *
	 * @param excelDropDown the new excel drop down
	 */
	public void setExcelDropDown(ExcelDropDownImpl excelDropDown) {
		if (excelDropDown != null)
			this.excelDropDown = excelDropDown.getAnnotation();
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
	public void setExcelSubtotal(ExcelSubtotalImpl excelSubtotal) {
		if (excelSubtotal != null)
			this.excelSubtotal = excelSubtotal.getAnnotation();
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
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
		return excelCellLayout;
	}

	/**
	 * Gets the excel date.
	 *
	 * @return the excel date
	 */
	public ExcelDate getExcelDate() {
		return excelDate;
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
	 * Gets the excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow getExcelMergeRow() {
		return excelMergeRow;
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
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderCellLayout getExcelHeaderCellLayout() {
		return excelHeaderCellLayout;
	}

	/**
	 * Gets the excel drop down.
	 *
	 * @return the excel drop down
	 */
	public ExcelDropDown getExcelDropDown() {
		return excelDropDown;
	}

	/**
	 * Gets the excel boolean text.
	 *
	 * @return the excel boolean text
	 */
	public ExcelBooleanText getExcelBooleanText() {
		return excelBooleanText;
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
		result = prime * result + ((excelCellLayout == null) ? 0 : excelCellLayout.hashCode());
		result = prime * result + ((excelColumn == null) ? 0 : excelColumn.hashCode());
		result = prime * result + ((excelColumnWidth == null) ? 0 : excelColumnWidth.hashCode());
		result = prime * result + ((excelDate == null) ? 0 : excelDate.hashCode());
		result = prime * result + ((excelDropDown == null) ? 0 : excelDropDown.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
		result = prime * result + ((excelMergeRow == null) ? 0 : excelMergeRow.hashCode());
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
		ExtraColumnAnnotation other = (ExtraColumnAnnotation) obj;
		if (excelCellLayout == null) {
			if (other.excelCellLayout != null)
				return false;
		} else if (!excelCellLayout.equals(other.excelCellLayout))
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
		if (excelDate == null) {
			if (other.excelDate != null)
				return false;
		} else if (!excelDate.equals(other.excelDate))
			return false;
		if (excelDropDown == null) {
			if (other.excelDropDown != null)
				return false;
		} else if (!excelDropDown.equals(other.excelDropDown))
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
		if (excelSubtotal == null) {
			if (other.excelSubtotal != null)
				return false;
		} else if (!excelSubtotal.equals(other.excelSubtotal))
			return false;
		return true;
	}

}
