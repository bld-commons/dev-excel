package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelRgbColor;

public class ExcelHeaderLayoutImpl implements Cloneable{

	private boolean wrap;
	private VerticalAlignment verticalAlignment;
	private ExcelRgbColor rgbForeground;
	private ExcelRgbColor rgbFont;
	private HorizontalAlignment horizontalAlignment;
	private ExcelFont font;
	private FillPatternType fillPatternType;
	private int cmWidthCell;
	private short cmHeightCell;
	private ExcelBorder border;
	
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

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

	public boolean isWrap() {
		return wrap;
	}

	public void setWrap(boolean wrap) {
		this.wrap = wrap;
	}

	public VerticalAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		this.verticalAlignment = verticalAlignment;
	}

	public ExcelRgbColor getRgbForeground() {
		return rgbForeground;
	}

	public void setRgbForeground(ExcelRgbColor rgbForeground) {
		this.rgbForeground = rgbForeground;
	}

	public ExcelRgbColor getRgbFont() {
		return rgbFont;
	}

	public void setRgbFont(ExcelRgbColor rgbFont) {
		this.rgbFont = rgbFont;
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
	}

	public ExcelFont getFont() {
		return font;
	}

	public void setFont(ExcelFont font) {
		this.font = font;
	}

	public FillPatternType getFillPatternType() {
		return fillPatternType;
	}

	public void setFillPatternType(FillPatternType fillPatternType) {
		this.fillPatternType = fillPatternType;
	}

	public int getCmWidthCell() {
		return cmWidthCell;
	}

	public void setCmWidthCell(int cmWidthCell) {
		this.cmWidthCell = cmWidthCell;
	}

	public short getCmHeightCell() {
		return cmHeightCell;
	}

	public void setCmHeightCell(short cmHeightCell) {
		this.cmHeightCell = cmHeightCell;
	}

	public ExcelBorder getBorder() {
		return border;
	}

	public void setBorder(ExcelBorder border) {
		this.border = border;
	}

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
