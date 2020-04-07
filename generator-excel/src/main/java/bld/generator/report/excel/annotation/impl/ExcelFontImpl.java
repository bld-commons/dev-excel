/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFontImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.constant.FontType;
import bld.generator.report.excel.constant.UnderlineType;
import lombok.Data;

/**
 * The Class ExcelFontImpl.
 */
@Data
public class ExcelFontImpl implements Cloneable{

	
	/** The underline. */
	protected UnderlineType underline;
	
	/** The size. */
	protected short size;
	
	/** The italic. */
	protected boolean italic;
	
	/** The font. */
	protected FontType font;
	
	/** The bold. */
	protected boolean bold;
	
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
	 * Gets the excel font.
	 *
	 * @return the excel font
	 */
	public ExcelFont getExcelFont() {
		ExcelFont excelFont=null;
		if(underline!=null && font!=null)
		excelFont=new ExcelFont() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFont.class;
			}
			
			@Override
			public UnderlineType underline() {
				return underline;
			}
			
			@Override
			public short size() {
				return size;
			}
			
			@Override
			public boolean italic() {
				return italic;
			}
			
			@Override
			public FontType font() {
				return font;
			}
			
			@Override
			public boolean bold() {
				return bold;
			}
		}; 
		return excelFont;
	}

	/**
	 * Instantiates a new excel font impl.
	 *
	 * @param underline the underline
	 * @param size      the size
	 * @param italic    the italic
	 * @param font      the font
	 * @param bold      the bold
	 */
	public ExcelFontImpl(UnderlineType underline, short size, boolean italic, FontType font, boolean bold) {
		super();
		this.underline = underline;
		this.size = size;
		this.italic = italic;
		this.font = font;
		this.bold = bold;
	}
	
	/**
	 * Instantiates a new excel font impl.
	 */
	public ExcelFontImpl() {
		super();
		this.underline = UnderlineType.NONE;
		this.size = 11;
		this.italic = false;
		this.font = FontType.CALIBRI;
		this.bold = false;
	}


	
}
