/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelRowHeight.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelRowHeight.
 */
@Retention(RUNTIME)
@Target({TYPE,FIELD})
@Documented
public @interface ExcelRowHeight {

	/**
	 * Height.
	 *
	 * @return the short
	 */
	public short height() default -1;
	
}
