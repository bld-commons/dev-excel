/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionRowsImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import lombok.Data;

/**
 * The Class ExcelFunctionRowsImpl.
 */
@Data
public class ExcelFunctionRowsImpl implements Cloneable{

	
	/** The excel functions. */
	protected ExcelFunctionRow[] excelFunctions;
	
	/** The excel function merges. */
	protected ExcelFunctionMergeRow[] excelFunctionMerges;
	
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
	 * Gets the excel function row.
	 *
	 * @return the excel function row
	 */
	public ExcelFunctionRows getExcelFunctionRow() {
		return new ExcelFunctionRows() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunctionRows.class;
			}

			@Override
			public ExcelFunctionRow[] excelFunctions() {
				return excelFunctions;
			}

			@Override
			public ExcelFunctionMergeRow[] excelFunctionMerges() {
				return excelFunctionMerges;
			}};
	}

	/**
	 * Instantiates a new excel function rows impl.
	 *
	 * @param excelFunctions      the excel functions
	 * @param excelFunctionMerges the excel function merges
	 */
	public ExcelFunctionRowsImpl(ExcelFunctionRow[] excelFunctions, ExcelFunctionMergeRow[] excelFunctionMerges) {
		super();
		this.excelFunctions = excelFunctions;
		this.excelFunctionMerges = excelFunctionMerges;
	}

}
