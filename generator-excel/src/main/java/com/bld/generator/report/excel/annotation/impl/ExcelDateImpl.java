/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.impl.ExcelDateImpl.java
*/

package com.bld.generator.report.excel.annotation.impl;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;


/**
 * The Class ExcelDateImpl.
 */
public class ExcelDateImpl extends ExcelAnnotationImpl<ExcelDate>{

	
	/** The value. */
	private ColumnDateFormat value;
	

	/**
	 * Instantiates a new excel date impl.
	 *
	 * @param value the value
	 */
	public ExcelDateImpl(ColumnDateFormat value){
		super();
		this.value = value;
	}


	/**
	 * Instantiates a new excel date impl.
	 */
	public ExcelDateImpl() {
		super();
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public ColumnDateFormat getValue() {
		return value;
	}


	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(ColumnDateFormat value) {
		this.value = value;
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
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ExcelDateImpl other = (ExcelDateImpl) obj;
		if (value != other.value)
			return false;
		return true;
	}

	
}
