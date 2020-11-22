/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelDateImpl.java
*/

package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.constant.ColumnDateFormat;

/**
 * The Class ExcelDateImpl.
 */
public class ExcelDateImpl extends ExcelAnnotationImpl<ExcelDate>{

	
	/** The format. */
	private ColumnDateFormat format;
	
	/**
	 * Instantiates a new excel date impl.
	 *
	 * @param format the format
	 */
	public ExcelDateImpl(ColumnDateFormat format){
		super();
		this.format = format;
	}

	/**
	 * Instantiates a new excel date impl.
	 */
	public ExcelDateImpl() {
		super();
	}

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public ColumnDateFormat getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(ColumnDateFormat format) {
		this.format = format;
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
		result = prime * result + ((format == null) ? 0 : format.hashCode());
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
		if (format != other.format)
			return false;
		return true;
	}

	
}
