/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelFunctionRows.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelFunctionRows.<br>
 * ExcelFunctionRows is used to manage 2 lists:
 * <ol>
 * <li>excelFunctions</li>
 * <li>excelFunctionMerges</li>
 * </ol> 
 * 
 * It must be managed on {@link com.bld.generator.report.excel.RowSheet} classes.
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface ExcelFunctionRows {

	/**
	 * Excel functions.
	 *
	 * @return the excel function row[]
	 */
	public ExcelFunctionRow[] excelFunctions() default {};
	
	/**
	 * Excel function merges.
	 *
	 * @return the excel function merge row[]
	 */
	public ExcelFunctionMergeRow[] excelFunctionMerges() default {};
	
	
}
