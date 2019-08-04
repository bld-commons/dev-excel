package com.bld.generator.report.excel.constant;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.bld.generator.report.excel.annotation.impl.ExcelBorderImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelFontImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelRgbColorImpl;

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

}
