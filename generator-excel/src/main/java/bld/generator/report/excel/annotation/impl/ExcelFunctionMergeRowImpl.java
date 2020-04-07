/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionMergeRowImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionMergeRowImpl.
 */
@Data
public class ExcelFunctionMergeRowImpl implements Cloneable{

	/** The excel cells layout. */
	protected ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	protected ExcelColumn excelColumn;
	
	/** The excel merge row. */
	protected ExcelMergeRow excelMergeRow;
	
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
	 * Gets the excel function merge.
	 *
	 * @return the excel function merge
	 */
	public ExcelFunctionMergeRow getExcelFunctionMerge() {
		return new ExcelFunctionMergeRow() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionMergeRow.class;
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
			public ExcelMergeRow excelMergeRow() {
				return excelMergeRow;
			}

			@Override
			public ExcelFunction excelFunction() {
				return excelFunction;
			}

		};
	}

	/**
	 * Instantiates a new excel function merge row impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn      the excel column
	 * @param excelMergeRow    the excel merge row
	 * @param excelFunction    the excel function
	 */
	public ExcelFunctionMergeRowImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn,
			ExcelMergeRow excelMergeRow, ExcelFunction excelFunction) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.excelMergeRow = excelMergeRow;
		this.excelFunction = excelFunction;
	}

}
