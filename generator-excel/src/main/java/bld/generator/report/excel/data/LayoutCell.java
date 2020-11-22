/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.LayoutCell.java
*/
package bld.generator.report.excel.data;

import java.util.Arrays;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.constant.ColumnDateFormat;

/**
 * The Class LayoutCell.
 */
public class LayoutCell implements Cloneable {

	/** The border. */
	private ExcelBorder border;

	/** The horizontal alignment. */
	private HorizontalAlignment horizontalAlignment;

	/** The vertical alignment. */
	private VerticalAlignment verticalAlignment;

	/** The font. */
	private ExcelFont font;

	/** The wrap. */
	private boolean wrap;

	/** The rgb font. */
	private ExcelColor[] rgbFont;

	/** The rgb foreground. */
	private ExcelColor[] rgbForeground;

	/** The fill patter type. */
	private FillPatternType fillPatterType;

	/** The precision. */
	private Integer precision;

	/** The rotation. */
	private Integer rotation;

	/** The format. */
	private ColumnDateFormat format;

	/** The locked. */
	private boolean locked;

	/** The font color. */
	private ExcelColor fontColor;

	/** The foreground color. */
	private ExcelColor foregroundColor;

	/**
	 * Gets the font color.
	 *
	 * @return the font color
	 */
	public ExcelColor getFontColor() {
		return fontColor;
	}

	/**
	 * Gets the foreground color.
	 *
	 * @return the foreground color
	 */
	public ExcelColor getForegroundColor() {
		return foregroundColor;
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
	 * Gets the rgb font.
	 *
	 * @return the rgb font
	 */
	public ExcelColor[] getRgbFont() {
		return rgbFont;
	}

	/**
	 * Sets the rgb font.
	 *
	 * @param rgbFont the new rgb font
	 */
	public void setRgbFont(ExcelColor... rgbFont) {
		this.rgbFont = rgbFont;
	}

	/**
	 * Gets the rgb foreground.
	 *
	 * @return the rgb foreground
	 */
	public ExcelColor[] getRgbForeground() {
		return rgbForeground;
	}

	/**
	 * Sets the rgb foreground.
	 *
	 * @param rgbForeground the new rgb foreground
	 */
	public void setRgbForeground(ExcelColor... rgbForeground) {
		this.rgbForeground = rgbForeground;
	}

	/**
	 * Gets the fill patter type.
	 *
	 * @return the fill patter type
	 */
	public FillPatternType getFillPatterType() {
		return fillPatterType;
	}

	/**
	 * Sets the fill patter type.
	 *
	 * @param fillPatterType the new fill patter type
	 */
	public void setFillPatterType(FillPatternType fillPatterType) {
		this.fillPatterType = fillPatterType;
	}

	/**
	 * Gets the precision.
	 *
	 * @return the precision
	 */
	public Integer getPrecision() {
		return precision;
	}

	/**
	 * Sets the precision.
	 *
	 * @param precision the new precision
	 */
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	/**
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public Integer getRotation() {
		return rotation;
	}

	/**
	 * Sets the rotation.
	 *
	 * @param rotation the new rotation
	 */
	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public ColumnDateFormat getFormat() {
		return format;
	}

	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(ColumnDateFormat format) {
		this.format = format;
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
	 * Sets the color.
	 *
	 * @param indexRow the new color
	 */
	public void setColor(int indexRow) {
		if (this.rgbFont != null) {
			int indexFont = indexRow % this.rgbFont.length;
			this.fontColor = this.rgbFont[indexFont];
		}
		if (this.rgbForeground != null) {
			int indexForeground = indexRow % this.rgbForeground.length;
			this.foregroundColor = this.rgbForeground[indexForeground];
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((border == null) ? 0 : border.hashCode());
		result = prime * result + ((fillPatterType == null) ? 0 : fillPatterType.hashCode());
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + ((fontColor == null) ? 0 : fontColor.hashCode());
		result = prime * result + ((foregroundColor == null) ? 0 : foregroundColor.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((horizontalAlignment == null) ? 0 : horizontalAlignment.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((precision == null) ? 0 : precision.hashCode());
		result = prime * result + Arrays.hashCode(rgbFont);
		result = prime * result + Arrays.hashCode(rgbForeground);
		result = prime * result + ((rotation == null) ? 0 : rotation.hashCode());
		result = prime * result + ((verticalAlignment == null) ? 0 : verticalAlignment.hashCode());
		result = prime * result + (wrap ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LayoutCell other = (LayoutCell) obj;
		if (border == null) {
			if (other.border != null)
				return false;
		} else if (!border.equals(other.border))
			return false;
		if (fillPatterType != other.fillPatterType)
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
			return false;
		if (fontColor == null) {
			if (other.fontColor != null)
				return false;
		} else if (!fontColor.equals(other.fontColor))
			return false;
		if (foregroundColor == null) {
			if (other.foregroundColor != null)
				return false;
		} else if (!foregroundColor.equals(other.foregroundColor))
			return false;
		if (format != other.format)
			return false;
		if (horizontalAlignment != other.horizontalAlignment)
			return false;
		if (locked != other.locked)
			return false;
		if (precision == null) {
			if (other.precision != null)
				return false;
		} else if (!precision.equals(other.precision))
			return false;
		if (!Arrays.equals(rgbFont, other.rgbFont))
			return false;
		if (!Arrays.equals(rgbForeground, other.rgbForeground))
			return false;
		if (rotation == null) {
			if (other.rotation != null)
				return false;
		} else if (!rotation.equals(other.rotation))
			return false;
		if (verticalAlignment != other.verticalAlignment)
			return false;
		if (wrap != other.wrap)
			return false;
		return true;
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
