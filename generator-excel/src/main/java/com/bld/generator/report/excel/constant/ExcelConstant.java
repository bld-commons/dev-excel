/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.constant.ExcelConstant.java
*/
package com.bld.generator.report.excel.constant;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;
import com.bld.generator.report.excel.annotation.impl.ExcelBorderImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelFontImpl;
import com.bld.generator.report.excel.annotation.impl.ExcelRgbColorImpl;

/**
 * The Class ExcelConstant.
 */
public class ExcelConstant {
	
	/** The Constant RGB_FOREGROUND. */
	public final static ExcelRgbColorImpl RGB_FOREGROUND=new ExcelRgbColorImpl((byte)255, (byte)255, (byte)255); 
	
	/** The Constant RGB_FONT. */
	public final static ExcelRgbColorImpl RGB_FONT=new ExcelRgbColorImpl((byte)0, (byte)0, (byte)0); 
	
	/** The Constant FONT. */
	public final static ExcelFontImpl FONT=new ExcelFontImpl(UnderlineType.NONE, (short)11, false, FontType.CALIBRI, false);
	
	/** The Constant BORDER. */
	public final static ExcelBorderImpl BORDER=new ExcelBorderImpl(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN);
	
	/** The Constant EXCEL_CELL_LAYOUT_INTEGER. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_INTEGER=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, new ExcelRgbColor[]{RGB_FOREGROUND.getAnnotation()}, new ExcelRgbColor[]{RGB_FONT.getAnnotation()}, -1, HorizontalAlignment.RIGHT, FONT.getAnnotation(), FillPatternType.SOLID_FOREGROUND, BORDER.getAnnotation());
	
	/** The Constant EXCEL_CELL_LAYOUT_DOUBLE. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_DOUBLE=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, new ExcelRgbColor[]{RGB_FOREGROUND.getAnnotation()}, new ExcelRgbColor[]{RGB_FONT.getAnnotation()}, 2, HorizontalAlignment.RIGHT, FONT.getAnnotation(), FillPatternType.SOLID_FOREGROUND, BORDER.getAnnotation());
	
	/** The Constant EXCEL_CELL_LAYOUT_STRING. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_STRING=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, new ExcelRgbColor[]{RGB_FOREGROUND.getAnnotation()}, new ExcelRgbColor[]{RGB_FONT.getAnnotation()}, -1, HorizontalAlignment.LEFT, FONT.getAnnotation(), FillPatternType.SOLID_FOREGROUND, BORDER.getAnnotation());
	
	/** The Constant EXCEL_CELL_LAYOUT_DATE. */
	public final static ExcelCellLayoutImpl EXCEL_CELL_LAYOUT_DATE=new ExcelCellLayoutImpl(true, VerticalAlignment.CENTER, new ExcelRgbColor[]{RGB_FOREGROUND.getAnnotation()}, new ExcelRgbColor[]{RGB_FONT.getAnnotation()}, -1, HorizontalAlignment.CENTER, FONT.getAnnotation(), FillPatternType.SOLID_FOREGROUND, BORDER.getAnnotation());
	
	/** The Constant EXCEL_DATE_DD_MM_YYYY. */
	public final static ExcelDateImpl EXCEL_DATE_DD_MM_YYYY=new ExcelDateImpl(ColumnDateFormat.DD_MM_YYYY);
	
	/** The Constant DEFAULT_WIDTH_COLUMN. */
	public static final int DEFAULT_WIDTH_COLUMN=5;
	
	/** The Constant EXCEL_COLUMN_WIDTH. */
	public final static ExcelColumnWidthImpl EXCEL_COLUMN_WIDTH=new ExcelColumnWidthImpl(DEFAULT_WIDTH_COLUMN);

}
