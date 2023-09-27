/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelAreaBorder.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;


/**
 * The Interface ExcelAreaBorder.<br>
 * ExcelAreaBorder is used to set a border on an area of cells.<br>
 * ExcelAreaBorder is composed by:
 * <ul>
 * <li>AreaRange - to define an area of cells</li>
 * <li>Border - to set the border type</li>
 * <li>IncludeSuperHeader - to enable/disable the area extension on the super header</li>
 * </ul>
 */
@Documented
@Retention(RUNTIME)
public @interface ExcelAreaBorder {

	/**
	 * Area range.
	 *
	 * @return the string
	 */
	public String areaRange();
	
	/**
	 * Border.
	 *
	 * @return the excel border
	 */
	public ExcelBorder border();
	
	
	/**
	 * Include super header.
	 *
	 * @return true, if successful
	 */
	public boolean includeSuperHeader() default false;
	
}
