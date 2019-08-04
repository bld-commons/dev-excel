/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import it.bld.generator.report.excel.annotation.ExcelDate;
import it.bld.generator.report.excel.constant.ColumnDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelDateImpl.
 */
public class ExcelDateImpl{

	
	/** The format. */
	protected ColumnDateFormat format;

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
	 * @throws Exception the exception
	 */
	public ExcelDateImpl(ColumnDateFormat format) throws Exception {
		super();
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

	
	
}
