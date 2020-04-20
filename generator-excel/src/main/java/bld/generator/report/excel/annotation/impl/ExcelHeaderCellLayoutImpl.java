/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import lombok.Data;

@Data
public class ExcelHeaderCellLayoutImpl implements Cloneable{

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
	 * Gets the excel header cell layout.
	 *
	 * @return the excel header cell layout
	 */
	public ExcelHeaderCellLayout getExcelHeaderCellLayout() {
		ExcelHeaderCellLayout excelHeaderLayout = new ExcelHeaderCellLayout() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelHeaderCellLayout.class;
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
			public ExcelBorder border() {
				return border;
			}

			@Override
			public int rotation() {
				return rotation;
			}

			
		};
		return excelHeaderLayout;
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
	 */
	public ExcelHeaderCellLayoutImpl(boolean wrap, VerticalAlignment verticalAlignment, ExcelRgbColor rgbForeground, ExcelRgbColor rgbFont, HorizontalAlignment horizontalAlignment, ExcelFont font, FillPatternType fillPatternType, ExcelBorder border,int rotation) {
		super();
		this.wrap = wrap;
		this.verticalAlignment = verticalAlignment;
		this.rgbForeground = rgbForeground;
		this.rgbFont = rgbFont;
		this.horizontalAlignment = horizontalAlignment;
		this.font = font;
		this.fillPatternType = fillPatternType;
		this.border = border;
		this.rotation=rotation;
	}

}
