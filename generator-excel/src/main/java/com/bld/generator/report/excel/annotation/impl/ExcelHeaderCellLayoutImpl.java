/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl.java
*/
package com.bld.generator.report.excel.annotation.impl;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.bld.generator.report.excel.annotation.ExcelBorder;
import com.bld.generator.report.excel.annotation.ExcelFont;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;


/**
 * The Class ExcelHeaderCellLayoutImpl.
 */
public class ExcelHeaderCellLayoutImpl extends ExcelAnnotationImpl<ExcelHeaderCellLayout> {

	/** The wrap. */
	private boolean wrap;

	/** The vertical alignment. */
	private VerticalAlignment verticalAlignment;

	/** The rgb foreground. */
	private ExcelRgbColor rgbForeground;

	/** The rgb font. */
	private ExcelRgbColor rgbFont;

	/** The horizontal alignment. */
	private HorizontalAlignment horizontalAlignment;

	/** The font. */
	private ExcelFont font;

	/** The fill pattern type. */
	private FillPatternType fillPatternType;

	/** The border. */
	private ExcelBorder border;

	/** The rotation. */
	private int rotation;

	/** The locked. */
	private boolean locked;


	/**
	 * Instantiates a new excel header cell layout impl.
	 */
	public ExcelHeaderCellLayoutImpl() {
		super();
	}

	/**
	 * Instantiates a new excel header cell layout impl.
	 *
	 * @param wrap                the wrap
	 * @param verticalAlignment   the vertical alignment
	 * @param rgbForeground       the rgb foreground
	 * @param rgbFont             the rgb font
	 * @param horizontalAlignment the horizontal alignment
	 * @param font                the font
	 * @param fillPatternType     the fill pattern type
	 * @param border              the border
	 * @param rotation            the rotation
	 * @param locked              the locked
	 */
	public ExcelHeaderCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor rgbForeground,
			ExcelRgbColor rgbFont, HorizontalAlignment horizontalAlignment, ExcelFont font,
			FillPatternType fillPatternType, ExcelBorder border, int rotation, boolean locked) {
		super();
		this.wrap = wrap;
		this.verticalAlignment = verticalAlignment;
		this.rgbForeground = rgbForeground;
		this.rgbFont = rgbFont;
		this.horizontalAlignment = horizontalAlignment;
		this.font = font;
		this.fillPatternType = fillPatternType;
		this.border = border;
		this.rotation = rotation;
		this.locked = locked;
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
	public ExcelRgbColor getRgbForeground() {
		return rgbForeground;
	}

	/**
	 * Sets the rgb foreground.
	 *
	 * @param rgbForeground the new rgb foreground
	 */
	public void setRgbForeground(ExcelRgbColor rgbForeground) {
		this.rgbForeground = rgbForeground;
	}

	/**
	 * Gets the rgb font.
	 *
	 * @return the rgb font
	 */
	public ExcelRgbColor getRgbFont() {
		return rgbFont;
	}

	/**
	 * Sets the rgb font.
	 *
	 * @param rgbFont the new rgb font
	 */
	public void setRgbFont(ExcelRgbColor rgbFont) {
		this.rgbFont = rgbFont;
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
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 * Sets the rotation.
	 *
	 * @param rotation the new rotation
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((border == null) ? 0 : border.hashCode());
		result = prime * result + ((fillPatternType == null) ? 0 : fillPatternType.hashCode());
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + ((horizontalAlignment == null) ? 0 : horizontalAlignment.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((rgbFont == null) ? 0 : rgbFont.hashCode());
		result = prime * result + ((rgbForeground == null) ? 0 : rgbForeground.hashCode());
		result = prime * result + rotation;
		result = prime * result + ((verticalAlignment == null) ? 0 : verticalAlignment.hashCode());
		result = prime * result + (wrap ? 1231 : 1237);
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
		ExcelHeaderCellLayoutImpl other = (ExcelHeaderCellLayoutImpl) obj;
		if (border == null) {
			if (other.border != null)
				return false;
		} else if (!border.equals(other.border))
			return false;
		if (fillPatternType != other.fillPatternType)
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (horizontalAlignment != other.horizontalAlignment)
			return false;
		if (locked != other.locked)
			return false;
		if (rgbFont == null) {
			if (other.rgbFont != null)
				return false;
		} else if (!rgbFont.equals(other.rgbFont))
			return false;
		if (rgbForeground == null) {
			if (other.rgbForeground != null)
				return false;
		} else if (!rgbForeground.equals(other.rgbForeground))
			return false;
		if (rotation != other.rotation)
			return false;
		if (verticalAlignment != other.verticalAlignment)
			return false;
		if (wrap != other.wrap)
			return false;
		return true;
	}

	
	
}
