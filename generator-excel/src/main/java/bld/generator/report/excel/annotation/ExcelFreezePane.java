/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelFreezePane.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelFreezePane {

	/**
	 * Column freez.
	 *
	 * @return the int
	 */
	public int columnFreez() default 0;
	
	/**
	 * Row freez.
	 *
	 * @return the int
	 */
	public int rowFreez() default 0;
	
}
