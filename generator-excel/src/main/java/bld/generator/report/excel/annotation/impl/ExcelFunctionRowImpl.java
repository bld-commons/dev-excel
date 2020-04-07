/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionRowImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionRowImpl.
 */
@Data
public class ExcelFunctionRowImpl implements Cloneable{

	/** The excel cells layout. */
	protected ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	protected ExcelColumn excelColumn;
	
	/** The excel function. */
	protected ExcelFunction excelFunction;
	
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
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunctionRow getExcelFunction() {
		return new ExcelFunctionRow() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionRow.class;
			}

			@Override
			public ExcelCellLayout excelCellsLayout() {
				return excelCellsLayout;
			}

			@Override
			public ExcelColumn excelColumn() {
				return excelColumn;
			}

			@Override
			public ExcelFunction excelFunction() {
				return excelFunction;
			}

		};
	}

	/**
	 * Instantiates a new excel function row impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn      the excel column
	 * @param excelFunction    the excel function
	 */
	public ExcelFunctionRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn,
			ExcelFunction excelFunction) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelFunction = excelFunction;
	}


	
}
