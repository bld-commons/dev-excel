/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelSummary.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.ExcelConstant;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelSummary.
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
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
	
	public int widthColumn1() default ExcelConstant.DEFAULT_WIDTH_COLUMN;
	
	public int widthColumn2() default ExcelConstant.DEFAULT_WIDTH_COLUMN;
	
}
