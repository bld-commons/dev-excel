/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionMergeImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunctionMerge;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import lombok.Data;

/**
 * The Class ExcelFunctionMergeImpl.
 */
@Data
public class ExcelFunctionMergeImpl implements Cloneable{

	
	/** The excel cells layout. */
	protected ExcelCellLayout excelCellsLayout;
	
	/** The excel column. */
	protected ExcelColumn excelColumn;
	
	/** The function. */
	protected String function;
	
	/** The excel merge row. */
	protected ExcelMergeRow excelMergeRow;
	
	/** The name function. */
	protected String nameFunction;
	
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
	public ExcelFunctionMerge getExcelFunctionMerge() {
		return new ExcelFunctionMerge() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionMerge.class;
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
			public String function() {
				return function;
			}

			@Override
			public ExcelMergeRow excelMergeRow() {
				return excelMergeRow;
			}

			@Override
			public String nameFunction() {
				return nameFunction;
			}};
	}

	/**
	 * Instantiates a new excel function merge impl.
	 *
	 * @param excelCellsLayout the excel cells layout
	 * @param excelColumn      the excel column
	 * @param function         the function
	 * @param excelMergeRow    the excel merge row
	 * @param nameFunction     the name function
	 */
	public ExcelFunctionMergeImpl(ExcelCellLayout excelCellsLayout, ExcelColumn excelColumn, String function, ExcelMergeRow excelMergeRow, String nameFunction) {
		super();
		this.excelCellsLayout = excelCellsLayout;
		this.excelColumn = excelColumn;
		this.function = function;
		this.excelMergeRow = excelMergeRow;
		this.nameFunction = nameFunction;
	}

	
	
	
}
