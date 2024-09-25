/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelBooleanText.java
 * 
 */
package com.bld.common.spreadsheet.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelBooleanText.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelBooleanText {

	/**
	 * Enable.
	 *
	 * @return the string
	 */
	public String enable() default "Yes";
	
	/**
	 * Disable.
	 *
	 * @return the string
	 */
	public String disable() default "No";
}
