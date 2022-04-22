/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFontImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.constant.FontType;
import bld.generator.report.excel.constant.UnderlineType;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFontImpl.
 */
public class ExcelFontImpl extends ExcelAnnotationImpl<ExcelFont> {

	/** The underline. */
	private UnderlineType underline;

	/** The size. */
	private short size;

	/** The italic. */
	private boolean italic;

	/** The font. */
	private FontType font;

	/** The bold. */
	private boolean bold;

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
		init(underline, size, italic, font, bold);
	}

	/**
	 * Inits the.
	 *
	 * @param underline the underline
	 * @param size      the size
	 * @param italic    the italic
	 * @param font      the font
	 * @param bold      the bold
	 */
	private void init(UnderlineType underline, short size, boolean italic, FontType font, boolean bold) {
		this.size = size;
		this.underline = underline;
		this.italic = italic;
		this.font = font;
		this.bold = bold;
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
	public ExcelFontImpl(UnderlineType underline, int size, boolean italic, FontType font, boolean bold) {
		super();
		init(underline, (short) size, italic, font, bold);
	}

	/**
	 * Instantiates a new excel font impl.
	 */
	public ExcelFontImpl() {
		super();
	}

	/**
	 * Gets the underline.
	 *
	 * @return the underline
	 */
	public UnderlineType getUnderline() {
		return underline;
	}

	/**
	 * Sets the underline.
	 *
	 * @param underline the new underline
	 */
	public void setUnderline(UnderlineType underline) {
		this.underline = underline;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public short getSize() {
		return size;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(short size) {
		this.size = size;
	}

	/**
	 * Checks if is italic.
	 *
	 * @return the italic
	 */
	public boolean isItalic() {
		return italic;
	}

	/**
	 * Sets the italic.
	 *
	 * @param italic the new italic
	 */
	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	/**
	 * Gets the font.
	 *
	 * @return the font
	 */
	public FontType getFont() {
		return font;
	}

	/**
	 * Sets the font.
	 *
	 * @param font the new font
	 */
	public void setFont(FontType font) {
		this.font = font;
	}

	/**
	 * Checks if is bold.
	 *
	 * @return the bold
	 */
	public boolean isBold() {
		return bold;
	}

	/**
	 * Sets the bold.
	 *
	 * @param bold the new bold
	 */
	public void setBold(boolean bold) {
		this.bold = bold;
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
		result = prime * result + (bold ? 1231 : 1237);
		result = prime * result + ((font == null) ? 0 : font.hashCode());
		result = prime * result + (italic ? 1231 : 1237);
		result = prime * result + size;
		result = prime * result + ((underline == null) ? 0 : underline.hashCode());
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
		ExcelFontImpl other = (ExcelFontImpl) obj;
		if (bold != other.bold)
			return false;
		if (font != other.font)
			return false;
		if (italic != other.italic)
			return false;
		if (size != other.size)
			return false;
		if (underline != other.underline)
			return false;
		return true;
	}

}
