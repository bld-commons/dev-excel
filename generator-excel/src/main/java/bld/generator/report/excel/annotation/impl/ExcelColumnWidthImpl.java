/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelColumnWidth;
import lombok.Data;

/**
 * The Class ExcelColumnWidthImpl.
 */
@Data
public class ExcelColumnWidthImpl {

	
	/** The width. */
	protected int width;

	/**
	 * Gets the excel column width.
	 *
	 * @return the excel column width
	 */
	public ExcelColumnWidth getExcelColumnWidth() {
		ExcelColumnWidth excelColumnWidth=new ExcelColumnWidth() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelColumnWidth.class;
			}
			
			@Override
			public int width() {
				return width;
			}
		};
		return excelColumnWidth;
	}

	/**
	 * Instantiates a new excel column width impl.
	 *
	 * @param width the width
	 */
	public ExcelColumnWidthImpl(int width) {
		super();
		this.width = width;
	}

	
}
