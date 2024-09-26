/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.impl.ExcelBooleanTextImpl.java
 * 
 */
package com.bld.generator.report.excel.annotation.impl;

import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;


/**
 * The Class ExcelBooleanTextImpl.
 */
public class ExcelBooleanTextImpl extends ExcelAnnotationImpl<ExcelBooleanText>{

	/** The if true. */
	private String enable;
	
	/** The if false. */
	private String disable;
	
	


	/**
	 * Instantiates a new excel boolean text impl.
	 *
	 * @param enable the enable
	 * @param disable the disable
	 */
	public ExcelBooleanTextImpl(String enable, String disable) {
		super();
		this.enable = enable;
		this.disable = disable;
	}

	
	
	/**
	 * Instantiates a new excel boolean text impl.
	 */
	public ExcelBooleanTextImpl() {
		super();
	}



	/**
	 * Gets the enable.
	 *
	 * @return the enable
	 */
	public String getEnable() {
		return enable;
	}

	/**
	 * Sets the enable.
	 *
	 * @param ifTrue the new enable
	 */
	public void setEnable(String ifTrue) {
		this.enable = ifTrue;
	}

	/**
	 * Gets the disable.
	 *
	 * @return the disable
	 */
	public String getDisable() {
		return disable;
	}

	/**
	 * Sets the disable.
	 *
	 * @param ifFalse the new disable
	 */
	public void setDisable(String ifFalse) {
		this.disable = ifFalse;
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
		result = prime * result + ((disable == null) ? 0 : disable.hashCode());
		result = prime * result + ((enable == null) ? 0 : enable.hashCode());
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
		ExcelBooleanTextImpl other = (ExcelBooleanTextImpl) obj;
		if (disable == null) {
			if (other.disable != null)
				return false;
		} else if (!disable.equals(other.disable))
			return false;
		if (enable == null) {
			if (other.enable != null)
				return false;
		} else if (!enable.equals(other.enable))
			return false;
		return true;
	}
	
	
	
}
