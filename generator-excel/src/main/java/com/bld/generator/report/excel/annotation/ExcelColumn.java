/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelColumn.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelColumn.
 * <br>
 * ExcelColumn is used to define the header column
 * It is set on fields of {@link com.bld.generator.report.excel.RowSheet} classes and is composed by:
 * <ul>
 * <li>Name - to set column name on header or property name in the sheet summary</li>
 * <li>Comment - to add a comment on header</li>
 * <li>Index - to set the insertion order of the columns</li>
 * <li>Ignore - to show or hide the column</li>
 * </ul> 
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD})
public @interface ExcelColumn {

	
	public String name();
	
	/**
	 * Comment.
	 *
	 * @return the string
	 */
	public String comment() default "";
	

	public double index();
	
	/**
	 * Ignore.
	 *
	 * @return true, if successful
	 */
	public boolean ignore() default false;
	
	
	
}
