/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.LayoutCell.java
*/
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.constant.ColumnDateFormat;
import lombok.Data;

/**
 * The Class LayoutCell.
 */

/**
 * Instantiates a new layout cell.
 */
@Data
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

	
}
