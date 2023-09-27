/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelColumnWidth;


/**
 * The Class ExcelColumnWidthImpl.
 */
public class ExcelColumnWidthImpl extends ExcelAnnotationImpl<ExcelColumnWidth>{

	
	/** The width. */
	private int width;

	/**
	 * Instantiates a new excel column width impl.
	 *
	 * @param width the width
	 */
	public ExcelColumnWidthImpl(int width) {
		super();
		this.width = width;
	}

	/**
	 * Instantiates a new excel column width impl.
	 */
	public ExcelColumnWidthImpl() {
		super();
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		this.width = width;
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
		result = prime * result + width;
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
		ExcelColumnWidthImpl other = (ExcelColumnWidthImpl) obj;
		if (width != other.width)
			return false;
		return true;
	}

	
	
	
	
}
