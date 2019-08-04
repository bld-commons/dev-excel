/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package com.bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.bld.generator.report.excel.annotation.ExcelBorder;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelFont;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelCellLayoutImpl.
 */
public class ExcelCellLayoutImpl {

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
	
	/** The fill patter type. */
	protected FillPatternType fillPatternType;
	
	/** The border. */
	protected ExcelBorder border;

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
		};
		return excelCellLayout;
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
	 * @param fillPatternType the fill patter type
	 * @param border the border
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
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ExcelCellLayoutImpl [wrap=" + wrap + ", verticalAlignment=" + verticalAlignment + ", rgbForeground=" + rgbForeground + ", rgbFont=" + rgbFont + ", precision=" + precision + ", horizontalAlignment=" + horizontalAlignment + ", font="
				+ font + ", fillPatternType=" + fillPatternType + ", border=" + border + "]";
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
		result = prime * result + precision;
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
		ExcelCellLayoutImpl other = (ExcelCellLayoutImpl) obj;
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
		if (precision != other.precision)
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

	/**
	 * Checks if is wrap.
	 *
	 * @return true, if is wrap
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

	
	
	
	
	
	
}
