/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelPivot.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelPivot.<br>
 * ExcelPivot is used to create a pivot table, you can define the starting column through the property "startColumn".<br>
 * This annotation is added within ${link bld.generator.report.excel.SheetData} classes.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelPivot {

	/**
	 * Start column.
	 *
	 * @return the int
	 */
	public int startColumn() default 0;
	
}
