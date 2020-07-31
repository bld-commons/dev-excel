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
 * <li>
 * <ul>AreaRange - to define an area of cells</ul>
 * <ul>Border - to set the border type</ul>
 * </li>
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
	
}
