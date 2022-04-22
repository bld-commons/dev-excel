/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelBoxMessage.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import bld.generator.report.excel.constant.BoxStyle;

/**
 * The Interface ExcelBoxMessage.
 */
@Retention(RUNTIME)
public @interface ExcelBoxMessage {

	/**
	 * Show.
	 *
	 * @return true, if successful
	 */
	public boolean show() default true;

	/**
	 * Box style.
	 *
	 * @return the box style
	 */
	public BoxStyle boxStyle();

	/**
	 * Title.
	 *
	 * @return the string
	 */
	public String title();

	/**
	 * Message.
	 *
	 * @return the string
	 */
	public String message();

}
