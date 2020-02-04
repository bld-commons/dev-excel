/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelRgbColor;

/**
 * The Class ExcelHeaderLayoutImpl.
 */
public class ExcelHeaderLayoutImpl implements Cloneable{

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
	
	/** The cm width cell. */
	private int cmWidthCell;
	
	/** The cm height cell. */
	private short cmHeightCell;
	
	/** The border. */
	private ExcelBorder border;
	
	

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

	/**
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderLayout getExcelHeaderLayout() {
		ExcelHeaderLayout excelHeaderLayout = new ExcelHeaderLayout() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelHeaderLayout.class;
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
			public int cmWidthCell() {
				return cmWidthCell;
			}

			@Override
			public short cmHeightCell() {
				return cmHeightCell;
			}

			@Override
			public ExcelBorder border() {
				return border;
			}
		};
		return excelHeaderLayout;
	}

	/**
	 * Instantiates a new excel header layout impl.
	 *
	 * @param wrap                the wrap
	 * @param verticalAlignment   the vertical alignment
	 * @param rgbForeground       the rgb foreground
	 * @param rgbFont             the rgb font
	 * @param horizontalAlignment the horizontal alignment
	 * @param font                the font
	 * @param fillPatternType     the fill pattern type
	 * @param cmWidthCell         the cm width cell
	 * @param cmHeightCell        the cm height cell
	 * @param border              the border
	 */
	public ExcelHeaderLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor rgbForeground, ExcelRgbColor rgbFont, HorizontalAlignment horizontalAlignment, ExcelFont font, FillPatternType fillPatternType, int cmWidthCell,
			short cmHeightCell, ExcelBorder border) {
		super();
		this.wrap = wrap;
		this.verticalAlignment = verticalAlignment;
		this.rgbForeground = rgbForeground;
		this.rgbFont = rgbFont;
		this.horizontalAlignment = horizontalAlignment;
		this.font = font;
		this.fillPatternType = fillPatternType;
		this.cmWidthCell = cmWidthCell;
		this.cmHeightCell = cmHeightCell;
		this.border = border;
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
	 * Gets the cm width cell.
	 *
	 * @return the cm width cell
	 */
	public int getCmWidthCell() {
		return cmWidthCell;
	}

	/**
	 * Sets the cm width cell.
	 *
	 * @param cmWidthCell the new cm width cell
	 */
	public void setCmWidthCell(int cmWidthCell) {
		this.cmWidthCell = cmWidthCell;
	}

	/**
	 * Gets the cm height cell.
	 *
	 * @return the cm height cell
	 */
	public short getCmHeightCell() {
		return cmHeightCell;
	}

	/**
	 * Sets the cm height cell.
	 *
	 * @param cmHeightCell the new cm height cell
	 */
	public void setCmHeightCell(short cmHeightCell) {
		this.cmHeightCell = cmHeightCell;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((border == null) ? 0 : border.hashCode());
		result = prime * result + cmHeightCell;
		result = prime * result + cmWidthCell;
		result = prime * result + ((fillPatternType == null) ? 0 : fillPatternType.hashCode());
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + ((horizontalAlignment == null) ? 0 : horizontalAlignment.hashCode());
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
		ExcelHeaderLayoutImpl other = (ExcelHeaderLayoutImpl) obj;
		if (border == null) {
			if (other.border != null)
				return false;
		} else if (!border.equals(other.border))
			return false;
		if (cmHeightCell != other.cmHeightCell)
			return false;
		if (cmWidthCell != other.cmWidthCell)
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
