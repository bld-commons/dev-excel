/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.constant.ExcelConstant;
import lombok.Data;

/**
 * The Class ExcelCellLayoutImpl.
 */
@Data
public class ExcelCellLayoutImpl implements Cloneable{

	/** The wrap. */
	protected boolean wrap;
	
	/** The vertical alignment. */
	protected VerticalAlignment verticalAlignment;
	
	/** The rgb foreground. */
	protected ExcelRgbColor rgbForeground;
	
	/** The rgb font. */
	protected ExcelRgbColor rgbFont;
	
	/** The precision. */
	protected int precision;
	
	/** The horizontal alignment. */
	protected HorizontalAlignment horizontalAlignment;
	
	/** The font. */
	protected ExcelFont font;
	
	/** The fill pattern type. */
	protected FillPatternType fillPatternType;
	
	/** The border. */
	protected ExcelBorder border;
	
	protected boolean locked;

	/**
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
		ExcelCellLayout excelCellLayout=new ExcelCellLayout() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelCellLayout.class;
			}
			
			@Override
			public boolean wrap() {
				return wrap;
			}
			
			@Override
			public VerticalAlignment verticalAlignment() {
				return verticalAlignment;
			}
			
			@Override
			public ExcelRgbColor rgbForeground() {
				return rgbForeground;
			}
			
			@Override
			public ExcelRgbColor rgbFont() {
				return rgbFont;
			}
			
			@Override
			public int precision() {
				return precision;
			}
			
			@Override
			public HorizontalAlignment horizontalAlignment() {
				return horizontalAlignment;
			}
			
			@Override
			public ExcelFont font() {
				return font;
			}
			
			@Override
			public FillPatternType fillPatternType() {
				return fillPatternType;
			}
			
			@Override
			public ExcelBorder border() {
				return border;
			}

			@Override
			public boolean locked() {
				return locked;
			}
		};
		return excelCellLayout;
	}

	/**
	 * Instantiates a new excel cell layout impl.
	 *
	 * @param wrap                the wrap
	 * @param verticalAlignment   the vertical alignment
	 * @param rgbForeground       the rgb foreground
	 * @param rgbFont             the rgb font
	 * @param precision           the precision
	 * @param horizontalAlignment the horizontal alignment
	 * @param font                the font
	 * @param fillPatternType     the fill pattern type
	 * @param border              the border
	 */
	public ExcelCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor rgbForeground, ExcelRgbColor rgbFont, int precision, HorizontalAlignment horizontalAlignment, ExcelFont font, FillPatternType fillPatternType,
			ExcelBorder border)  {
		super();
		this.wrap = wrap;
		this.verticalAlignment = verticalAlignment;
		this.rgbForeground = rgbForeground;
		this.rgbFont = rgbFont;
		this.precision = precision;
		this.horizontalAlignment = horizontalAlignment;
		this.font = font;
		this.fillPatternType = fillPatternType;
		this.border = border;
		this.locked=false;
	}
	
	public ExcelCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor rgbForeground, ExcelRgbColor rgbFont, int precision, HorizontalAlignment horizontalAlignment, ExcelFont font, FillPatternType fillPatternType,
			ExcelBorder border, boolean locked) {
		super();
		this.wrap = wrap;
		this.verticalAlignment = verticalAlignment;
		this.rgbForeground = rgbForeground;
		this.rgbFont = rgbFont;
		this.precision = precision;
		this.horizontalAlignment = horizontalAlignment;
		this.font = font;
		this.fillPatternType = fillPatternType;
		this.border = border;
		this.locked = locked;
	}

	/**
	 * Instantiates a new excel cell layout impl.
	 */
	public ExcelCellLayoutImpl()  {
		super();
		this.wrap = true;
		this.verticalAlignment = VerticalAlignment.CENTER;
		this.rgbForeground = ExcelConstant.RGB_FOREGROUND.getExcelRgbColor();
		this.rgbFont = ExcelConstant.RGB_FONT.getExcelRgbColor();
		this.precision = -1;
		this.horizontalAlignment = HorizontalAlignment.RIGHT;
		this.font = ExcelConstant.FONT.getExcelFont();
		this.fillPatternType = FillPatternType.SOLID_FOREGROUND;
		this.border = ExcelConstant.BORDER.getExcelBorder();
	}

	
	
	/**
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
