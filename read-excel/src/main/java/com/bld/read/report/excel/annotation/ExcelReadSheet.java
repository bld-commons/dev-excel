/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelReadSheet.<br>
 * ExcelReadSheet is used to define the row and column start and is used in the {@link com.bld.read.report.excel.domain.SheetRead} classes.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelReadSheet {
	
	/**
	 * Start row.
	 *
	 * @return the int
	 */
	public int startRow()default 0;
	
	/**
	 * Start column.
	 *
	 * @return the int
	 */
	public int startColumn() default 0;
	
}
