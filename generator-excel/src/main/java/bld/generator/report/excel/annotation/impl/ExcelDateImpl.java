/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelDateImpl.java
*/

package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.constant.ColumnDateFormat;

/**
 * The Class ExcelDateImpl.
 */
public class ExcelDateImpl implements Cloneable{

	
	/** The format. */
	private ColumnDateFormat format;
	
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
	 * Gets the excel date.
	 *
	 * @return the excel date
	 */
	public ExcelDate getExcelDate() {
		return new ExcelDate() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelDate.class;
			}

			@Override
			public ColumnDateFormat format() {
				return format;
			}
			
		};
	}

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
