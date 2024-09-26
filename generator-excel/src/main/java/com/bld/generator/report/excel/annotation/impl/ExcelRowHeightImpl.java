/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.annotation.impl.ExcelRowHeightImpl.java
*/
package com.bld.generator.report.excel.annotation.impl;

import com.bld.generator.report.excel.annotation.ExcelRowHeight;


/**
 * The Class ExcelRowHeightImpl.
 */
public class ExcelRowHeightImpl extends ExcelAnnotationImpl<ExcelRowHeight>{

	/** The height. */
	private short height;

	

	/**
	 * Instantiates a new excel row height impl.
	 */
	public ExcelRowHeightImpl() {
		super();
	}

	/**
	 * Instantiates a new excel row height impl.
	 *
	 * @param height the height
	 */
	public ExcelRowHeightImpl(short height) {
		super();
		this.height = height;
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
		result = prime * result + height;
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
		ExcelRowHeightImpl other = (ExcelRowHeightImpl) obj;
		if (height != other.height)
			return false;
		return true;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public short getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 *
	 * @param height the new height
	 */
	public void setHeight(short height) {
		this.height = height;
	}

	
}
