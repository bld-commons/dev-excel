/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelReadColumn.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelReadColumn {

	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name();
	
}
