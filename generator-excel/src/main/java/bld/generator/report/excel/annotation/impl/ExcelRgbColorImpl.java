/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelRgbColorImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelRgbColor;
import lombok.Data;

/**
 * The Class ExcelRgbColorImpl.
 */
@Data
public class ExcelRgbColorImpl implements Cloneable{

	/** The blue. */
	protected byte blue;
	
	/** The red. */
	protected byte red;
	
	/** The green. */
	protected byte green;
	
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

	

}
