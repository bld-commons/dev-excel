/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionRowsImpl.
 */
public class ExcelFunctionRowsImpl {

	
	/** The excel functions. */
	protected ExcelFunctionRow[] excelFunctions;
	
	/** The excel function merges. */
	protected ExcelFunctionMergeRow[] excelFunctionMerges;

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
	 * @param excelFunctions the excel functions
	 * @param excelFunctionMerges the excel function merges
	 */
	public ExcelFunctionRowsImpl(ExcelFunctionRow[] excelFunctions, ExcelFunctionMergeRow[] excelFunctionMerges) {
		super();
		this.excelFunctions = excelFunctions;
		this.excelFunctionMerges = excelFunctionMerges;
	}

	/**
	 * Gets the excel functions.
	 *
	 * @return the excel functions
	 */
	public ExcelFunctionRow[] getExcelFunctions() {
		return excelFunctions;
	}

	/**
	 * Sets the excel functions.
	 *
	 * @param excelFunctions the new excel functions
	 */
	public void setExcelFunctions(ExcelFunctionRow[] excelFunctions) {
		this.excelFunctions = excelFunctions;
	}

	/**
	 * Gets the excel function merges.
	 *
	 * @return the excel function merges
	 */
	public ExcelFunctionMergeRow[] getExcelFunctionMerges() {
		return excelFunctionMerges;
	}

	/**
	 * Sets the excel function merges.
	 *
	 * @param excelFunctionMerges the new excel function merges
	 */
	public void setExcelFunctionMerges(ExcelFunctionMergeRow[] excelFunctionMerges) {
		this.excelFunctionMerges = excelFunctionMerges;
	}
	
	
}
