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
 * The Interface ExcelSheetLayout.
 */
@Retention(RUNTIME)
@Target({TYPE})
@Documented
public @interface ExcelSheetLayout {

	/**
	 * Landscape.
	 *
	 * @return true, if successful
	 */
	public boolean landscape() default true;
	
	/**
	 * Not merge.
	 *
	 * @return true, if successful
	 */
	public boolean notMerge() default true;
	
	/**
	 * Sort and filter.
	 *
	 * @return true, if successful
	 */
	public boolean sortAndFilter() default true;
	
}
