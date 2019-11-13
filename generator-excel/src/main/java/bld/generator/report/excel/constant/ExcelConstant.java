/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.constant;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import bld.generator.report.excel.annotation.impl.ExcelBorderImpl;
import bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import bld.generator.report.excel.annotation.impl.ExcelFontImpl;
import bld.generator.report.excel.annotation.impl.ExcelRgbColorImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelConstant.
 */
public class ExcelConstant {
	
	
	/** The Constant RGB_FOREGROUND. */
	public final static ExcelRgbColorImpl RGB_FOREGROUND=new ExcelRgbColorImpl((byte)255, (byte)255, (byte)255); 
	
	/** The Constant RGB_FONT. */
	public final static ExcelRgbColorImpl RGB_FONT=new ExcelRgbColorImpl((byte)0, (byte)0, (byte)0); 
	
	/** The Constant FONT. */
	public final static ExcelFontImpl FONT=new ExcelFontImpl(UnderlineType.NONE, (short)11, false, "Arial", false);
	
	/** The Constant BORDER. */
	public final static ExcelBorderImpl BORDER=new ExcelBorderImpl(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN);
	
	/** The Constant EXCEL_CELL_LAYOUT_DOUBLE. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_DOUBLE=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, RGB_FOREGROUND.getExcelRgbColor(), RGB_FONT.getExcelRgbColor(), 2, HorizontalAlignment.RIGHT, FONT.getExcelFont(), FillPatternType.SOLID_FOREGROUND, BORDER.getExcelBorder());
	
	/** The Constant EXCEL_CELL_LAYOUT_STRING. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_STRING=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, RGB_FOREGROUND.getExcelRgbColor(), RGB_FONT.getExcelRgbColor(), -1, HorizontalAlignment.LEFT, FONT.getExcelFont(), FillPatternType.SOLID_FOREGROUND, BORDER.getExcelBorder());
	
	/** The Constant EXCEL_CELL_LAYOUT_DATE. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_DATE=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, RGB_FOREGROUND.getExcelRgbColor(), RGB_FONT.getExcelRgbColor(), -1, HorizontalAlignment.CENTER, FONT.getExcelFont(), FillPatternType.SOLID_FOREGROUND, BORDER.getExcelBorder());
	
	/** The Constant EXCEL_DATE_DD_MM_YYYY. */
	public final static ExcelDateImpl EXCEL_DATE_DD_MM_YYYY=new ExcelDateImpl(ColumnDateFormat.DD_MM_YYYY);

}
