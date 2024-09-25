/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelHeaderGroup.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;


/**
 * The Interface ExcelSuperHeaderCell.<br>
 * ExcelSuperHeaderCell is used to write the top level header cell.<br>
 * It is composed by:
 * <ul>
 * <li>ColumnName - to define the top level header name</li>
 * <li>ColumnRange - to set the cell range address</li>
 * <li>ExcelHeaderCellLayout - to set the style of the top level header cell</li>
 * </ul>  
 * 
 * It is a property of the annotation {@link com.bld.generator.report.excel.annotation.ExcelSuperHeader}.
 * 
 */
@Retention(RUNTIME)
public @interface ExcelSuperHeaderCell {

	
	/**
	 * Column name.
	 *
	 * @return the string
	 */
	public String columnName();
	
	/**
	 * Column range.
	 *
	 * @return the string
	 */
	public String columnRange();
	
	/**
	 * Excel header cell layout.
	 *
	 * @return the excel header cell layout
	 */
	public ExcelHeaderCellLayout excelHeaderCellLayout() default @ExcelHeaderCellLayout;
	
}
