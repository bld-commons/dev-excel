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
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl;
import bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;

/**
 * The Class ExtraColumnAnnotation.<br>
 *  ExtraColumnAnnotation is used to configure the dynamic cells type and style.<br>
 *  It must be set on mapExtraColumnAnnotation within the SheetDynamicData classes. 
 */
public class ExtraColumnAnnotation {

	/** The excel column.<br> 
	 * It is not null
	 * */
	@NotNull
	private ExcelColumn excelColumn;

	/** The excel cell layout. <br>
	 * it is not null.*/
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
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
		return excelCellLayout;
	}

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayout the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayout excelCellLayout) {
		this.excelCellLayout = excelCellLayout;
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
	 * Sets the excel date.
	 *
	 * @param excelDate the new excel date
	 */
	public void setExcelDate(ExcelDate excelDate) {
		this.excelDate = excelDate;
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
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderCellLayout getExcelHeaderCellLayout() {
		return excelHeaderCellLayout;
	}

	/**
	 * Sets the excel header layout.
	 *
	 * @param excelHeaderCellLayout the new excel header layout
	 */
	public void setExcelHeaderCellLayout(ExcelHeaderCellLayout excelHeaderCellLayout) {
		this.excelHeaderCellLayout = excelHeaderCellLayout;
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
