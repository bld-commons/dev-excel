/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelFunctionRows.
 */
@Retention(RUNTIME)
@Target(TYPE)
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
