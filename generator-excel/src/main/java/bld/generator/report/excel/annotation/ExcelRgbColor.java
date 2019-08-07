/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface RGBColor.
 */
@Retention(RUNTIME)
@Target({ })
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