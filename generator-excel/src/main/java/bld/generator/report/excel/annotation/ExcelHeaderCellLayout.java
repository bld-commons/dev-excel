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

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelHeaderCellLayout.
 * <br>
 * ExcelHeaderCellLayout is used to define the header cell style.<br>
 * It is set on fields of {@link bld.generator.report.excel.RowSheet} classes or it is a property of the annotation {@link bld.generator.report.excel.annotation.ExcelHeaderLayout}.<br>
 * The header cell style is composed by: <br>
 * <ul>
 * <li>Border - to set borders types</li>
 * <li>HorizontalAlignment - to set horizontal alignment</li>
 * <li>VerticalAlignment - to set vertical alignment</li>
 * <li>Font - to set font type</li>
 * <li>wrap - to enable and disable wrap text into cell</li>
 * <li>rgbFont - to set the font color</li>
 * <li>rgbForeground - to set the background color</li>
 * <li>fillPatternType - to set fill pattern style</li>
 * <li>precision - to set the precision number, if the value is not a number then it can be ignored</li>
 * <li>locked - to enable and disable cell editable</li>
 * <li>rotation - to set the cell rotation</li>
 * </ul>
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
	
	
	/**
	 * Locked.
	 *
	 * @return true, if successful
	 */
	public boolean locked()default false;
	
		
}
