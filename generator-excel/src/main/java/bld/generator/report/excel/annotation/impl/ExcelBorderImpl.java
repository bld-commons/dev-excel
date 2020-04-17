/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelBorderImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.BorderStyle;

import bld.generator.report.excel.annotation.ExcelBorder;
import lombok.Data;

/**
 * The Class ExcelBorderImpl.
 */
@Data
public class ExcelBorderImpl implements Cloneable{

	
	/** The left. */
	protected BorderStyle left;
	
	/** The top. */
	protected BorderStyle top;
	
	/** The right. */
	protected BorderStyle right;
	
	/** The bottom. */
	protected BorderStyle bottom;

	/**
	 * Gets the excel border.
	 *
	 * @return the excel border
	 */
	public ExcelBorder getExcelBorder() {
		ExcelBorder excelBorder=new ExcelBorder() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelBorder.class;
			}
			
			@Override
			public BorderStyle top() {
				return top;
			}
			
			@Override
			public BorderStyle right() {
				return right;
			}
			
			@Override
			public BorderStyle left() {
				return left;
			}
			
			@Override
			public BorderStyle bottom() {
				return bottom;
			}
		};
		return excelBorder;
	}

	/**
	 * Instantiates a new excel border impl.
	 *
	 * @param left   the left
	 * @param top    the top
	 * @param right  the right
	 * @param bottom the bottom
	 */
	public ExcelBorderImpl(BorderStyle left, BorderStyle top, BorderStyle right, BorderStyle bottom){
		super();
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	/**
	 * Instantiates a new excel border impl.
	 */
	public ExcelBorderImpl(){
		super();
		this.left = BorderStyle.THIN;
		this.top = BorderStyle.THIN;
		this.right = BorderStyle.THIN;
		this.bottom = BorderStyle.THIN;
	}


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


	
}
