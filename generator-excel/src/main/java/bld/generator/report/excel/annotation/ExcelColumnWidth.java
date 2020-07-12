/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelColumnWidth.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.ExcelConstant;

/**
 * The Interface ExcelColumnWidth.
 * <br>
 * ExcelColumnWidth is used to set the column width in centimeters.<br>
 * It is set on fields of {@link bld.generator.report.excel.RowSheet} classes.
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface ExcelColumnWidth {

	/**
	 * Width.
	 *
	 * @return the int
	 */
	public int width() default ExcelConstant.DEFAULT_WIDTH_COLUMN;
	
}
