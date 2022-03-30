/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelBooleanTextImpl.java
 * 
 */
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelBooleanText;

/**
 * The Class ExcelBooleanTextImpl.
 */
public class ExcelBooleanTextImpl extends ExcelAnnotationImpl<ExcelBooleanText>{

	/** The if true. */
	private String ifTrue;
	
	/** The if false. */
	private String ifFalse;
	
	

	/**
	 * Instantiates a new excel boolean text impl.
	 *
	 * @param ifTrue the if true
	 * @param ifFalse the if false
	 */
	public ExcelBooleanTextImpl(String ifTrue, String ifFalse) {
		super();
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	
	
	public ExcelBooleanTextImpl() {
		super();
	}



	/**
	 * Gets the if true.
	 *
	 * @return the if true
	 */
	public String getIfTrue() {
		return ifTrue;
	}

	/**
	 * Sets the if true.
	 *
	 * @param ifTrue the new if true
	 */
	public void setIfTrue(String ifTrue) {
		this.ifTrue = ifTrue;
	}

	/**
	 * Gets the if false.
	 *
	 * @return the if false
	 */
	public String getIfFalse() {
		return ifFalse;
	}

	/**
	 * Sets the if false.
	 *
	 * @param ifFalse the new if false
	 */
	public void setIfFalse(String ifFalse) {
		this.ifFalse = ifFalse;
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
		result = prime * result + ((ifFalse == null) ? 0 : ifFalse.hashCode());
		result = prime * result + ((ifTrue == null) ? 0 : ifTrue.hashCode());
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
		if (ifFalse == null) {
			if (other.ifFalse != null)
				return false;
		} else if (!ifFalse.equals(other.ifFalse))
			return false;
		if (ifTrue == null) {
			if (other.ifTrue != null)
				return false;
		} else if (!ifTrue.equals(other.ifTrue))
			return false;
		return true;
	}
	
	
	
}
