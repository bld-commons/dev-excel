/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
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

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelCellLayout.
 */
@Retention(RUNTIME)
@Target({ FIELD })
public @interface ExcelCellLayout {

	/**
	 * Border.
	 *
	 * @return the excel border
	 */
	public ExcelBorder border() default @ExcelBorder(top = BorderStyle.THIN, bottom = BorderStyle.THIN, left = BorderStyle.THIN, right = BorderStyle.THIN);

	/**
	 * Horizontal alignment.
	 *
	 * @return the horizontal alignment
	 */
	public HorizontalAlignment horizontalAlignment() default HorizontalAlignment.LEFT;

	/**
	 * Vertical alignment.
	 *
	 * @return the vertical alignment
	 */
	public VerticalAlignment verticalAlignment() default VerticalAlignment.CENTER;

	/**
	 * Font.
	 *
	 * @return the excel font
	 */
	public ExcelFont font() default @ExcelFont;

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
	public ExcelRgbColor rgbFont() default @ExcelRgbColor(red = (byte) 0, green = (byte) 0, blue = (byte) 0);

	/**
	 * Rgb foreground.
	 *
	 * @return the excel rgb color
	 */
	public ExcelRgbColor rgbForeground() default @ExcelRgbColor(red = (byte)255, green = (byte)255, blue = (byte)255);
	
	/**
	 * Fill pattern type.
	 *
	 * @return the fill pattern type
	 */
	public FillPatternType fillPatternType() default FillPatternType.SOLID_FOREGROUND;
	
	/**
	 * Precision.
	 *
	 * @return the int
	 */
	public int precision() default -1;

}
