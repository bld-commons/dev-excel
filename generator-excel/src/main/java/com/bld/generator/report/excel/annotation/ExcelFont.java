/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.bld.generator.report.excel.constant.UnderlineType;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelFont.
 */
@Retention(RUNTIME)
@Target({})
public @interface ExcelFont {

	/**
	 * Font.
	 *
	 * @return the string
	 */
	public String font() default "Arial";

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
