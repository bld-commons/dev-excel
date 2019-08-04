package com.bld.generator.report.excel.data;

import com.bld.generator.report.excel.constant.UnderlineType;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceltFont.
 */
public class ExceltFont {

	/** The font. */
	private String font;

	/** The bold. */
	private boolean bold;
	
	/** The italic. */
	private boolean italic;
	
	/** The underline. */
	public UnderlineType underline;
	
	/** The size. */
	public short size;

	/**
	 * Gets the font.
	 *
	 * @return the font
	 */
	public String getFont() {
		return font;
	}

	/**
	 * Sets the font.
	 *
	 * @param font the new font
	 */
	public void setFont(String font) {
		this.font = font;
	}

	/**
	 * Checks if is bold.
	 *
	 * @return true, if is bold
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
	 * Checks if is italic.
	 *
	 * @return true, if is italic
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
		ExceltFont other = (ExceltFont) obj;
		if (bold != other.bold)
			return false;
		if (font == null) {
			if (other.font != null)
				return false;
		} else if (!font.equals(other.font))
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
