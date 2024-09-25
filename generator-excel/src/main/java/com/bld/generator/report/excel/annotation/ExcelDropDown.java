/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelDropDown.java
 */

package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.bld.generator.report.excel.constant.BoxStyle;


/**
 * The Interface ExcelDropDown.<br>
 * Excel Drop Down is used to create a drop down list that references another table within the Excel file. <br>
 * It is composed by:
 * <ul>
 * <li>AreaRange - to set the list by formula function</li>
 * <li>SuppressDropDownArrow - to show/hide  the values into a drop down list</li>
 * </ul>
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelDropDown {

	/**
	 * Area range.
	 *
	 * @return the string
	 */
	public String areaRange();
	
	/**
	 * Suppress drop down arrow.
	 *
	 * @return true, if successful
	 */
	public boolean suppressDropDownArrow() default true;
	
	
	/**
	 * Alias.
	 *
	 * @return the excel formula alias[]
	 */
	public ExcelFormulaAlias[] alias() default {};
	
	
	/**
	 * Error box.
	 *
	 * @return the excel box message
	 */
	public ExcelBoxMessage errorBox()default @ExcelBoxMessage(boxStyle = BoxStyle.STOP, message = "The value is not valid", title = "Error");
	
}
