/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelHeaderCellLayout.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * The Interface ExcelHeaderCellLayout.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelHeaderCellLayout {

	/**
	 * Border.
	 *
	 * @return the excel border
	 */
	public ExcelBorder border() default @ExcelBorder(top = BorderStyle.THIN, bottom = BorderStyle.THIN, left = BorderStyle.THIN, right = BorderStyle.THIN);
	
	/**
	 * Wrap.
	 *
	 * @return true, if successful
	 */
	public boolean wrap() default true;
	
	/**
	 * Rgb font.
	 *
	 * @return the excel rgb color
	 */
	public ExcelRgbColor rgbFont() default @ExcelRgbColor(red=0,green=97,blue=0);
	
	/**
	 * Rgb foreground.
	 *
	 * @return the excel rgb color
	 */
	public ExcelRgbColor rgbForeground()default  @ExcelRgbColor(red=(byte)198,green=(byte)239,blue=(byte)206);
	
	/**
	 * Font.
	 *
	 * @return the excel font
	 */
	public ExcelFont font() default @ExcelFont(bold=true);
	
	/**
	 * Horizontal alignment.
	 *
	 * @return the horizontal alignment
	 */
	public HorizontalAlignment horizontalAlignment() default HorizontalAlignment.CENTER;
	
	/**
	 * Vertical alignment.
	 *
	 * @return the vertical alignment
	 */
	public VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;
	
	
	/**
	 * Fill pattern type.
	 *
	 * @return the fill pattern type
	 */
	public FillPatternType fillPatternType() default FillPatternType.SOLID_FOREGROUND;
	
	/**
	 * Rotation.
	 *
	 * @return the int
	 */
	public int rotation() default 0;
	
}