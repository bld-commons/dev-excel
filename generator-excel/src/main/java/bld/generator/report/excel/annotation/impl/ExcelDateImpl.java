/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelDateImpl.java
*/

package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.constant.ColumnDateFormat;
import lombok.Data;

/**
 * The Class ExcelDateImpl.
 */
@Data
public class ExcelDateImpl implements Cloneable{

	
	/** The format. */
	protected ColumnDateFormat format;
	
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

	
}
