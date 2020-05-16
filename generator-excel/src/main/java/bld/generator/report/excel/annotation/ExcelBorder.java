/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelBorder.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.BorderStyle;

/**
 * The Interface ExcelBorder.
 * <br>
 * ExcelBorder is used to define borders types through enumeration BorderStyle
 * <br>
 * <ul>
 * <li>bottom</li>
 * <li>left</li>
 * <li>right</li>
 * <li>top</li>
 * <ul>
 */
@Documented
@Retention(RUNTIME)
@Target({})
public @interface ExcelBorder {

	/**
	 * Top.
	 *
	 * @return the border style
	 */
	public BorderStyle top() default BorderStyle.NONE;
	
	/**
	 * Bottom.
	 *
	 * @return the border style
	 */
	public BorderStyle bottom() default BorderStyle.NONE;
	
	/**
	 * Left.
	 *
	 * @return the border style
	 */
	public BorderStyle left() default BorderStyle.NONE;
	
	/**
	 * Right.
	 *
	 * @return the border style
	 */
	public BorderStyle right() default BorderStyle.NONE; 
	
}
