/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelRgbColor.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelRgbColor.<br>
 * ExcelRgbColor is used to set the color by the RGB color model.
 * <ul>
 * <li>Red</li>
 * <li>Green</li>
 * <li>Blue</li>
 * </ul>
 * 
 * It is used on following annotations:
 * <ul>
 * <li>{@link com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout}</li>
 * <li>{@link com.bld.generator.report.excel.annotation.ExcelCellLayout}</li>
 * </ul>
 */
@Retention(RUNTIME)
@Target({ })
@Documented
public @interface ExcelRgbColor {

	/**
	 * Red.
	 *
	 * @return the byte
	 */
	public byte red() default (byte)255;
	
	/**
	 * Green.
	 *
	 * @return the byte
	 */
	public byte green() default (byte)255;
	
	/**
	 * Blue.
	 *
	 * @return the byte
	 */
	public byte blue() default (byte)255;
	
}
