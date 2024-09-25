/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelChartCategory.java
 * 
 */

package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;


/**
 * The Interface ExcelChartCategory.
 */
@Documented
@Retention(RUNTIME)
public @interface ExcelChartCategory {

	/**
	 * Field name.
	 *
	 * @return the string
	 */
	public String fieldName();
	
	/**
	 * Function.
	 *
	 * @return the string
	 */
	public String function();
	
	/**
	 * Row regex.
	 * This regex must to return the row number 
	 * @return the string
	 */
	public String rowRegex() default "";
}
