/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelBoxMessage.java
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import com.bld.generator.report.excel.constant.BoxStyle;

/**
 * Defines a dialog-box message (tooltip / input message / error alert) attached to a
 * data-validation drop-down cell.
 * <p>
 * Use this annotation as the {@code boxMessage} attribute inside
 * {@link ExcelDropDown} to display a prompt or error message when the user
 * selects or enters a value in the validated cell.
 * </p>
 * <p>
 * The visual style of the box (information, warning, stop) is controlled by
 * {@link #boxStyle()}, and visibility can be toggled with {@link #show()}.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * @ExcelDropDown(
 *     boxMessage = @ExcelBoxMessage(
 *         boxStyle = BoxStyle.INFORMATION,
 *         title    = "Hint",
 *         message  = "Choose a value from the list"
 *     )
 * )
 * private String status;
 * }</pre>
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
