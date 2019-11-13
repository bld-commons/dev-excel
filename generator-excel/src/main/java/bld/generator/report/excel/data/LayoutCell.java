/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.constant.ColumnDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class LayoutCell.
 */
public class LayoutCell {
	
	/** The border. */
	private ExcelBorder border;

	/** The horizontal alignment. */
	private HorizontalAlignment horizontalAlignment;

	/** The vertical alignment. */
	private VerticalAlignment verticalAlignment;

	/** The font. */
	private ExceltFont font;

	/** The wrap. */
	private boolean wrap;

	/** The rgb font. */
	private ExcelColor rgbFont;

	/** The rgb foreground. */
	private ExcelColor rgbForeground;
	
	/** The fill patter type. */
	private FillPatternType fillPatterType;
	
	/** The precision. */
	private Integer precision;

	
	/** The format. */
	private ColumnDateFormat format;


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
	public ExceltFont getFont() {
		return font;
	}


	/**
	 * Sets the font.
	 *
	 * @param font the new font
	 */
	public void setFont(ExceltFont font) {
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
	public ExcelColor getRgbFont() {
		return rgbFont;
	}


	/**
	 * Sets the rgb font.
	 *
	 * @param rgbFont the new rgb font
	 */
	public void setRgbFont(ExcelColor rgbFont) {
		this.rgbFont = rgbFont;
	}


	/**
	 * Gets the rgb foreground.
	 *
	 * @return the rgb foreground
	 */
	public ExcelColor getRgbForeground() {
		return rgbForeground;
	}


	/**
	 * Sets the rgb foreground.
	 *
	 * @param rgbForeground the new rgb foreground
	 */
	public void setRgbForeground(ExcelColor rgbForeground) {
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((border == null) ? 0 : border.hashCode());
		result = prime * result + ((fillPatterType == null) ? 0 : fillPatterType.hashCode());
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((horizontalAlignment == null) ? 0 : horizontalAlignment.hashCode());
		result = prime * result + ((precision == null) ? 0 : precision.hashCode());
		result = prime * result + ((rgbFont == null) ? 0 : rgbFont.hashCode());
		result = prime * result + ((rgbForeground == null) ? 0 : rgbForeground.hashCode());
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
		if (format != other.format)
			return false;
		if (horizontalAlignment != other.horizontalAlignment)
			return false;
		if (precision == null) {
			if (other.precision != null)
				return false;
		} else if (!precision.equals(other.precision))
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
		if (verticalAlignment != other.verticalAlignment)
			return false;
		if (wrap != other.wrap)
			return false;
		return true;
	}


	
	
		
	
	
	
}
