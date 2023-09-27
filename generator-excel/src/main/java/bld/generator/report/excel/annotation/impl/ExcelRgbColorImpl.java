/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelRgbColorImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelRgbColor;


/**
 * The Class ExcelRgbColorImpl.
 */
public class ExcelRgbColorImpl extends ExcelAnnotationImpl<ExcelRgbColor>{

	/** The blue. */
	private byte blue;
	
	/** The red. */
	private byte red;
	
	/** The green. */
	private byte green;
	
	

	/**
	 * Instantiates a new excel rgb color impl.
	 */
	public ExcelRgbColorImpl() {
		super();
	}

	/**
	 * Instantiates a new excel rgb color impl.
	 *
	 * @param red   the red
	 * @param green the green
	 * @param blue  the blue
	 */
	public ExcelRgbColorImpl(byte red, byte green,byte blue) {
		super();
		this.blue = blue;
		this.red = red;
		this.green = green;
	}
	
	/**
	 * Instantiates a new excel rgb color impl.
	 *
	 * @param red the red
	 * @param green the green
	 * @param blue the blue
	 */
	public ExcelRgbColorImpl(int red, int green,int blue) {
		super();
		this.blue = (byte)blue;
		this.red = (byte)red;
		this.green = (byte)green;
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
