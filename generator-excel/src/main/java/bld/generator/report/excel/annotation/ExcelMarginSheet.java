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
 * The Interface ExcelMarginSheet.
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface ExcelMarginSheet {
	
	/**
	 * Top.
	 *
	 * @return the double
	 */
	public double top() default 0;
	
	/**
	 * Bottom.
	 *
	 * @return the double
	 */
	public double bottom() default 0;
	
	/**
	 * Left.
	 *
	 * @return the double
	 */
	public double left() default 0;
	
	/**
	 * Right.
	 *
	 * @return the double
	 */
	public double right	() default 0;
}
