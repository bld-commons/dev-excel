/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelSummary.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelSummary {

	/**
	 * Title.
	 *
	 * @return the string
	 */
	public String title();
	
	/**
	 * Comment.
	 *
	 * @return the string
	 */
	public String comment() default "";
	
	/**
	 * Layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout layout() default @ExcelCellLayout;
	
}
