/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.util.Arrays;
import java.util.Objects;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelRgbColor;


/**
 * The Class ExcelCellLayoutImpl.
 */
public class ExcelCellLayoutImpl extends ExcelAnnotationImpl<ExcelCellLayout>{

	/** The wrap. */
	protected boolean wrap;
	
	/** The vertical alignment. */
	protected VerticalAlignment verticalAlignment;
	
	/** The rgb foreground. */
	protected ExcelRgbColor[] rgbForeground;
	
	/** The rgb font. */
	protected ExcelRgbColor[] rgbFont;
	
	/** The precision. */
	protected int precision;
	
	/** The horizontal alignment. */
	protected HorizontalAlignment horizontalAlignment;
	
	/** The font. */
	public ExcelFont font;
	
	/** The fill pattern type. */
	protected FillPatternType fillPatternType;
	
	/** The border. */
	protected ExcelBorder border;
	
	/** The locked. */
	protected boolean locked;

	/** The auto size column. */
	protected boolean autoSizeColumn;
	
	
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
	public ExcelCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor[] rgbForeground, ExcelRgbColor[] rgbFont, int precision, HorizontalAlignment horizontalAlignment, ExcelFont font, FillPatternType fillPatternType,
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
		this.autoSizeColumn = false;
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
	 * @param locked              the locked
	 */
	public ExcelCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor[] rgbForeground, ExcelRgbColor[] rgbFont, int precision, HorizontalAlignment horizontalAlignment, ExcelFont font, FillPatternType fillPatternType,
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
		this.autoSizeColumn = false;
	}
	

	/**
	 * Instantiates a new excel cell layout impl.
	 *
	 * @param wrap the wrap
	 * @param verticalAlignment the vertical alignment
	 * @param rgbForeground the rgb foreground
	 * @param rgbFont the rgb font
	 * @param precision the precision
	 * @param horizontalAlignment the horizontal alignment
	 * @param font the font
	 * @param fillPatternType the fill pattern type
	 * @param border the border
	 * @param locked the locked
	 * @param autoSizeColumn the auto size column
	 */
	public ExcelCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor[] rgbForeground,
			ExcelRgbColor[] rgbFont, int precision, HorizontalAlignment horizontalAlignment, ExcelFont font,
			FillPatternType fillPatternType, ExcelBorder border, boolean locked, boolean autoSizeColumn) {
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
		this.autoSizeColumn = autoSizeColumn;
	}

	/**
	 * Instantiates a new excel cell layout impl.
	 */
	public ExcelCellLayoutImpl()  {
		super();
	}


	/**
	 * Checks if is wrap.
	 *
	 * @return the wrap
	 */
	public boolean isWrap() {
		return wrap;
	}

	/**
	 * Sets the wrap.
	 *
	 * @param wrap the new wrap
	 */
	public void setWrap(boolean wrap) {
		this.wrap = wrap;
	}

	/**
	 * Gets the vertical alignment.
	 *
	 * @return the vertical alignment
	 */
	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	/**
	 * Sets the vertical alignment.
	 *
	 * @param verticalAlignment the new vertical alignment
	 */
	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	/**
	 * Gets the rgb foreground.
	 *
	 * @return the rgb foreground
	 */
	public ExcelRgbColor[] getRgbForeground() {
		return rgbForeground;
	}

	/**
	 * Sets the rgb foreground.
	 *
	 * @param rgbForeground the new rgb foreground
	 */
	public void setRgbForeground(ExcelRgbColor... rgbForeground) {
		this.rgbForeground = rgbForeground;
	}

	/**
	 * Gets the rgb font.
	 *
	 * @return the rgb font
	 */
	public ExcelRgbColor[] getRgbFont() {
		return rgbFont;
	}

	/**
	 * Sets the rgb font.
	 *
	 * @param rgbFont the new rgb font
	 */
	public void setRgbFont(ExcelRgbColor... rgbFont) {
		this.rgbFont = rgbFont;
	}

	/**
	 * Gets the precision.
	 *
	 * @return the precision
	 */
	public int getPrecision() {
		return precision;
	}

	/**
	 * Sets the precision.
	 *
	 * @param precision the new precision
	 */
	public void setPrecision(int precision) {
		this.precision = precision;
	}

	/**
	 * Gets the horizontal alignment.
	 *
	 * @return the horizontal alignment
	 */
	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	/**
	 * Sets the horizontal alignment.
	 *
	 * @param horizontalAlignment the new horizontal alignment
	 */
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	/**
	 * Gets the font.
	 *
	 * @return the font
	 */
	public ExcelFont getFont() {
		return font;
	}

	/**
	 * Sets the font.
	 *
	 * @param font the new font
	 */
	public void setFont(ExcelFont font) {
		this.font = font;
	}

	/**
	 * Gets the fill pattern type.
	 *
	 * @return the fill pattern type
	 */
	public FillPatternType getFillPatternType() {
		return fillPatternType;
	}

	/**
	 * Sets the fill pattern type.
	 *
	 * @param fillPatternType the new fill pattern type
	 */
	public void setFillPatternType(FillPatternType fillPatternType) {
		this.fillPatternType = fillPatternType;
	}

	/**
	 * Gets the border.
	 *
	 * @return the border
	 */
	public ExcelBorder getBorder() {
		return border;
	}

	/**
	 * Sets the border.
	 *
	 * @param border the new border
	 */
	public void setBorder(ExcelBorder border) {
		this.border = border;
	}

	/**
	 * Checks if is locked.
	 *
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the locked.
	 *
	 * @param locked the new locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Checks if is auto size column.
	 *
	 * @return true, if is auto size column
	 */
	public boolean isAutoSizeColumn() {
		return autoSizeColumn;
	}

	/**
	 * Sets the auto size column.
	 *
	 * @param autoSizeColumn the new auto size column
	 */
	public void setAutoSizeColumn(boolean autoSizeColumn) {
		this.autoSizeColumn = autoSizeColumn;
	}
	
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(rgbFont);
		result = prime * result + Arrays.hashCode(rgbForeground);
		result = prime * result + Objects.hash(autoSizeColumn, border, fillPatternType, font, horizontalAlignment,
				locked, precision, verticalAlignment, wrap);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelCellLayoutImpl other = (ExcelCellLayoutImpl) obj;
		return autoSizeColumn == other.autoSizeColumn && Objects.equals(border, other.border)
				&& fillPatternType == other.fillPatternType && Objects.equals(font, other.font)
				&& horizontalAlignment == other.horizontalAlignment && locked == other.locked
				&& precision == other.precision && Arrays.equals(rgbFont, other.rgbFont)
				&& Arrays.equals(rgbForeground, other.rgbForeground) && verticalAlignment == other.verticalAlignment
				&& wrap == other.wrap;
	}


	
	
	
	
}
