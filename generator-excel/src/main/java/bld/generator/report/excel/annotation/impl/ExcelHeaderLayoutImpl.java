/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelHeaderLayoutImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import lombok.Data;

/**
 * The Class ExcelHeaderLayoutImpl.
 */
@Data
public class ExcelHeaderLayoutImpl implements Cloneable{

	/** The row height. */
	private short rowHeight;
	
	/** The excel header cell layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;
	
	
	

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
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderLayout getExcelHeaderLayout() {
		ExcelHeaderLayout excelHeaderLayout = new ExcelHeaderLayout() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelHeaderLayout.class;
			}

			
			@Override
			public short rowHeight() {
				return rowHeight;
			}

			@Override
			public ExcelHeaderCellLayout excelHeaderCellLayout() {
				return this.excelHeaderCellLayout();
			}
		};
		return excelHeaderLayout;
	}

	/**
	 * Instantiates a new excel header layout impl.
	 *
	 * @param rowHeight             the row height
	 * @param excelHeaderCellLayout the excel header cell layout
	 */
	public ExcelHeaderLayoutImpl(short rowHeight, ExcelHeaderCellLayout excelHeaderCellLayout) {
		super();
		this.rowHeight = rowHeight;
		this.excelHeaderCellLayout = excelHeaderCellLayout;
	}

	
	
	
}
