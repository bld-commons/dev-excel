/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelRgbColor;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelRgbColorImpl.
 */
public class ExcelRgbColorImpl {

	/** The blue. */
	protected byte blue;
	
	/** The red. */
	protected byte red;
	
	/** The green. */
	protected byte green;

	/**
	 * Gets the excel rgb color.
	 *
	 * @return the excel rgb color
	 */
	public ExcelRgbColor getExcelRgbColor() {
		ExcelRgbColor excelRgbColor = null;
		excelRgbColor = new ExcelRgbColor() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelRgbColor.class;
			}

			@Override
			public byte red() {
				return red;
			}

			@Override
			public byte green() {
				return green;
			}

			@Override
			public byte blue() {
				return blue;
			}
		};
		return excelRgbColor;
	}

	/**
	 * Instantiates a new excel rgb color impl.
	 *
	 * @param blue  the blue
	 * @param red   the red
	 * @param green the green
	 */
	public ExcelRgbColorImpl(byte blue, byte red, byte green) {
		super();
		this.blue = blue;
		this.red = red;
		this.green = green;
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
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + red;
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
		ExcelRgbColorImpl other = (ExcelRgbColorImpl) obj;
		if (blue != other.blue)
			return false;
		if (green != other.green)
			return false;
		if (red != other.red)
			return false;
		return true;
	}

	/**
	 * Gets the blue.
	 *
	 * @return the blue
	 */
	public byte getBlue() {
		return blue;
	}

	/**
	 * Sets the blue.
	 *
	 * @param blue the new blue
	 */
	public void setBlue(byte blue) {
		this.blue = blue;
	}

	/**
	 * Gets the red.
	 *
	 * @return the red
	 */
	public byte getRed() {
		return red;
	}

	/**
	 * Sets the red.
	 *
	 * @param red the new red
	 */
	public void setRed(byte red) {
		this.red = red;
	}

	/**
	 * Gets the green.
	 *
	 * @return the green
	 */
	public byte getGreen() {
		return green;
	}

	/**
	 * Sets the green.
	 *
	 * @param green the new green
	 */
	public void setGreen(byte green) {
		this.green = green;
	}

}
