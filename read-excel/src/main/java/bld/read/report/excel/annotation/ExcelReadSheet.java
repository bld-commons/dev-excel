/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelReadSheet.
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
