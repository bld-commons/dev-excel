/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelColumn.
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface ExcelColumn {

	/**
	 * Name column.
	 *
	 * @return the string
	 */
	public String nameColumn();
	
	/**
	 * Comment.
	 *
	 * @return the string
	 */
	public String comment() default "";
	
	/**
	 * Index column.
	 *
	 * @return the double
	 */
	public double indexColumn();
	
}
