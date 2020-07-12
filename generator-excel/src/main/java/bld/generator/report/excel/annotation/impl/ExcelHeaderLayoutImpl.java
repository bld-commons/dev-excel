/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelHeaderLayoutImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;

/**
 * The Class ExcelHeaderLayoutImpl.
 */
public class ExcelHeaderLayoutImpl implements Cloneable{

	/** The row height. */
	private short rowHeight;
	
	/** The excel header cell layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;
	
	
	

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
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderLayout getExcelHeaderLayout() {
		ExcelHeaderLayout excelHeaderLayout = new ExcelHeaderLayout() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelHeaderLayout.class;
			}

			
			@Override
			public short rowHeight() {
				return rowHeight;
			}

			@Override
			public ExcelHeaderCellLayout excelHeaderCellLayout() {
				return this.excelHeaderCellLayout();
			}
		};
		return excelHeaderLayout;
	}

	/**
	 * Instantiates a new excel header layout impl.
	 *
	 * @param rowHeight             the row height
	 * @param excelHeaderCellLayout the excel header cell layout
	 */
	public ExcelHeaderLayoutImpl(short rowHeight, ExcelHeaderCellLayout excelHeaderCellLayout) {
		super();
		this.rowHeight = rowHeight;
		this.excelHeaderCellLayout = excelHeaderCellLayout;
	}

	/**
	 * Gets the row height.
	 *
	 * @return the row height
	 */
	public short getRowHeight() {
		return rowHeight;
	}

	/**
	 * Sets the row height.
	 *
	 * @param rowHeight the new row height
	 */
	public void setRowHeight(short rowHeight) {
		this.rowHeight = rowHeight;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
		result = prime * result + rowHeight;
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
		ExcelHeaderLayoutImpl other = (ExcelHeaderLayoutImpl) obj;
		if (excelHeaderCellLayout == null) {
			if (other.excelHeaderCellLayout != null)
				return false;
		} else if (!excelHeaderCellLayout.equals(other.excelHeaderCellLayout))
			return false;
		if (rowHeight != other.rowHeight)
			return false;
		return true;
	}

	
	
	
}
