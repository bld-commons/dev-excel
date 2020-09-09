/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.ExtraColumnAnnotation.java
*/
package bld.generator.report.excel.data;

import javax.validation.constraints.NotNull;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelColumnWidth;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelDropDownList;
import bld.generator.report.excel.annotation.ExcelDropDownReferenceList;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl;
import bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import bld.generator.report.excel.annotation.impl.ExcelDropDownListImpl;
import bld.generator.report.excel.annotation.impl.ExcelDropDownReferenceListImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;

/**
 * The Class ExtraColumnAnnotation.<br>
 * ExtraColumnAnnotation is used to configure the dynamic cells type and the
 * style.<br>
 * It must be set on mapExtraColumnAnnotation within the
 * {@link bld.generator.report.excel.SheetDynamicData} classes.
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

	/** The excel drop down list. */
	private ExcelDropDownList excelDropDownList;

	/** The excel drop down reference list. */
	private ExcelDropDownReferenceList excelDropDownReferenceList;

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayoutImpl the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayoutImpl excelCellLayoutImpl) {
		if (excelCellLayoutImpl != null)
			this.excelCellLayout = excelCellLayoutImpl.getExcelCellLayout();
	}

	/**
	 * Sets the excel date.
	 *
	 * @param excelDateImpl the new excel date
	 */
	public void setExcelDate(ExcelDateImpl excelDateImpl) {
		if (excelDateImpl != null)
			this.excelDate = excelDateImpl.getExcelDate();
	}

	/**
	 * Sets the excel column.
	 *
	 * @param excelColumnImpl the new excel column
	 */
	public void setExcelColumn(ExcelColumnImpl excelColumnImpl) {
		if (excelColumnImpl != null)
			this.excelColumn = excelColumnImpl.getExcelColumn();
	}

	/**
	 * Sets the excel function.
	 *
	 * @param excelFunctionImpl the new excel function
	 */
	public void setExcelFunction(ExcelFunctionImpl excelFunctionImpl) {
		if (excelFunctionImpl != null)
			this.excelFunction = excelFunctionImpl.getExcelFunction();
	}

	/**
	 * Sets the excel merge row.
	 *
	 * @param excelMergeRowImpl the new excel merge row
	 */
	public void setExcelMergeRow(ExcelMergeRowImpl excelMergeRowImpl) {
		if (excelMergeRowImpl != null)
			this.excelMergeRow = excelMergeRowImpl.getExcelMergeRow();
	}

	/**
	 * Sets the excel header layout.
	 *
	 * @param excelHeaderCellLayoutImpl the new excel header layout
	 */
	public void setExcelHeaderCellLayout(ExcelHeaderCellLayoutImpl excelHeaderCellLayoutImpl) {
		if (excelHeaderCellLayoutImpl != null)
			this.excelHeaderCellLayout = excelHeaderCellLayoutImpl.getExcelHeaderCellLayout();
	}

	/**
	 * Sets the excel column width.
	 *
	 * @param excelColumnWidthImpl the new excel column width
	 */
	public void setExcelColumnWidth(ExcelColumnWidthImpl excelColumnWidthImpl) {
		if (excelColumnWidthImpl != null)
			this.excelColumnWidth = excelColumnWidthImpl.getExcelColumnWidth();
	}

	/**
	 * Sets the excel drop down list.
	 *
	 * @param excelDropDownListImpl the new excel drop down list
	 */
	public void setExcelDropDownList(ExcelDropDownListImpl excelDropDownListImpl) {
		if (excelDropDownListImpl != null)
			this.excelDropDownList = excelDropDownListImpl.getExcelDropDownList();
	}

	/**
	 * Sets the excel drop down reference list.
	 *
	 * @param excelDropDownReferenceListImpl the new excel drop down reference list
	 */
	public void setExcelDropDownReferenceList(ExcelDropDownReferenceListImpl excelDropDownReferenceListImpl) {
		if (excelDropDownReferenceListImpl != null)
			this.excelDropDownReferenceList = excelDropDownReferenceListImpl.getExcelDropDownReferenceList();
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
	 * Gets the excel drop down list.
	 *
	 * @return the excel drop down list
	 */
	public ExcelDropDownList getExcelDropDownList() {
		return excelDropDownList;
	}

	/**
	 * Gets the excel drop down reference list.
	 *
	 * @return the excel drop down reference list
	 */
	public ExcelDropDownReferenceList getExcelDropDownReferenceList() {
		return excelDropDownReferenceList;
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
		result = prime * result + ((excelDropDownList == null) ? 0 : excelDropDownList.hashCode());
		result = prime * result + ((excelDropDownReferenceList == null) ? 0 : excelDropDownReferenceList.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
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
		if (excelDropDownList == null) {
			if (other.excelDropDownList != null)
				return false;
		} else if (!excelDropDownList.equals(other.excelDropDownList))
			return false;
		if (excelDropDownReferenceList == null) {
			if (other.excelDropDownReferenceList != null)
				return false;
		} else if (!excelDropDownReferenceList.equals(other.excelDropDownReferenceList))
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
		return true;
	}

}
