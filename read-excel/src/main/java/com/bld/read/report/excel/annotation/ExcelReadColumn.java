/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelReadColumn.<br>
 * ExcelReadColumn is used to map the header column with the field in the {@link com.bld.read.report.excel.domain.RowSheetRead} classes.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelReadColumn {

	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String value();
	
	public boolean ignoreCellTypeString() default false;
	
}
