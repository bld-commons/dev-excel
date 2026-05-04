/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl.java
*/
package com.bld.generator.report.excel.annotation.impl;

import com.bld.generator.report.excel.annotation.ExcelMergeRow;

/**
 * The Class ExcelMergeRowImpl.
 */
public class ExcelMergeRowImpl extends ExcelAnnotationImpl<ExcelMergeRow> {

	/** The reference field name of the driver column. Empty means this column is the driver. */
	private String value = "";

	/**
	 * Instantiates a new excel merge row impl.
	 */
	public ExcelMergeRowImpl() {
		super();
	}

	/**
	 * Instantiates a new excel merge row impl.
	 *
	 * @param value the reference field name
	 */
	public ExcelMergeRowImpl(String value) {
		super();
		this.setValue(value);
	}

	/**
	 * Gets the value.
	 *
	 * @return the reference field name
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the reference field name
	 */
	public void setValue(String value) {
		if (value != null)
			this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelMergeRowImpl other = (ExcelMergeRowImpl) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
