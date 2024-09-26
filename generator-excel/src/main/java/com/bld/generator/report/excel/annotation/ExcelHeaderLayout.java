/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelHeaderLayout.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelHeaderLayout.<br>
 * ExcelHeaderLayout is used to configure the header of the table.<br>
 * It is composed by:
 * <ul>
 * <li>RowHeight - to set row height</li>
 * <li>ExcelHeaderCellLayout - to set the header cell style</li>
 * </ul>
 * It must be to managed in {@link com.bld.generator.report.excel.SheetData} classes.
 */
@Retention(RUNTIME)
@Target({ TYPE })
@Documented
public @interface ExcelHeaderLayout {

	/**
	 * Row height.
	 *
	 * @return the short
	 */
	public short rowHeight() default 2;

	/**
	 * Excel header cell layout.
	 *
	 * @return the excel header cell layout
	 */
	public ExcelHeaderCellLayout excelHeaderCellLayout() default @ExcelHeaderCellLayout;

}
