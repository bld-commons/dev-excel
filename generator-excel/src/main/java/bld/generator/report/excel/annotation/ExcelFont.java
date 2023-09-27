/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelFont.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.FontType;
import bld.generator.report.excel.constant.UnderlineType;


/**
 * The Interface ExcelFont.<br>
 * ExcelFont defines the following properties:
 * <ul>
 * <li>Font - to set font type</li>
 * <li>Bold - to enable/disable bold</li>
 * <li>Italic - to enable/disable italic</li>
 * <li>Underline - to set underline type</li>
 * <li>Size - to set font size</li>
 * </ul>
 * <br>
 * It is a property of the following annotations:
 * <ul>
 * <li>{@link bld.generator.report.excel.annotation.ExcelCellLayout}</li>
 * <li>{@link bld.generator.report.excel.annotation.ExcelHeaderCellLayout}</li>
 * </ul>
 */
@Documented
@Retention(RUNTIME)
@Target({})
public @interface ExcelFont {

	/**
	 * Font.
	 *
	 * @return the font type
	 */
	public FontType font() default FontType.CALIBRI;

	/**
	 * Bold.
	 *
	 * @return true, if successful
	 */
	public boolean bold() default false;
	
	/**
	 * Italic.
	 *
	 * @return true, if successful
	 */
	public boolean italic() default false;
	
	/**
	 * Underline.
	 *
	 * @return the underline type
	 */
	public UnderlineType underline() default UnderlineType.NONE;
	
	/**
	 * Size.
	 *
	 * @return the short
	 */
	public short size() default 11;

}
