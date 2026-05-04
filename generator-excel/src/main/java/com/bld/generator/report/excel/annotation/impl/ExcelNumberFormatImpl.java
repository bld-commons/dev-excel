/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.annotation.impl.ExcelNumberFormatImpl.java
 */
package com.bld.generator.report.excel.annotation.impl;

import com.bld.generator.report.excel.annotation.ExcelNumberFormat;


/**
 * The Class ExcelNumberFormatImpl.
 */
public class ExcelNumberFormatImpl extends ExcelAnnotationImpl<ExcelNumberFormat> {

	/** The value. */
	private String value;

	/**
	 * Instantiates a new excel number format impl.
	 */
	public ExcelNumberFormatImpl() {
		super();
	}

	/**
	 * Instantiates a new excel number format impl.
	 *
	 * @param value the Excel format code (e.g. "#,##0.00", "dd/MM/yyyy", "0.00%")
	 */
	public ExcelNumberFormatImpl(String value) {
		super();
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the Excel format code
	 */
	public void setValue(String value) {
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
		ExcelNumberFormatImpl other = (ExcelNumberFormatImpl) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
