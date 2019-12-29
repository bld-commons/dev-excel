/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.data;

import javax.validation.constraints.NotNull;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import bld.generator.report.excel.annotation.impl.ExcelDateImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtraColumnAnnotation.
 */
public class ExtraColumnAnnotation {

	/** The excel column. */
	@NotNull
	private ExcelColumn excelColumn;

	/** The excel cell layout. */
	@NotNull
	private ExcelCellLayout excelCellLayout;

	/** The excel date. */
	private ExcelDate excelDate;

	/** The excel merge row. */
	private ExcelMergeRow excelMergeRow;

	/** The excel function. */
	public ExcelFunction excelFunction;

	/**
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
		return excelCellLayout;
	}

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayoutImpl the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayoutImpl excelCellLayoutImpl) {
		if (excelCellLayoutImpl != null)
			this.excelCellLayout = excelCellLayoutImpl.getExcelCellLayout();
	}

	/**
	 * Gets the excel date.
	 *
	 * @return the excel date
	 */
	public ExcelDate getExcelDate() {
		return excelDate;
	}

	/**
	 * Sets the excel date.
	 *
	 * @param excelDateImpl the new excel date
	 */
	public void setExcelDate(ExcelDateImpl excelDateImpl) {
		if (excelDateImpl != null)
			this.excelDate = excelDateImpl.getExcelDate();
	}

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		return excelColumn;
	}

	/**
	 * Sets the excel column.
	 *
	 * @param excelColumnImpl the new excel column
	 */
	public void setExcelColumn(ExcelColumnImpl excelColumnImpl) {
		if (excelColumnImpl != null)
			this.excelColumn = excelColumnImpl.getExcelColumn();
	}

	/**
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction getExcelFunction() {
		return excelFunction;
	}

	/**
	 * Sets the excel function.
	 *
	 * @param excelFunctionImpl the new excel function
	 */
	public void setExcelFunction(ExcelFunctionImpl excelFunctionImpl) {
		if (excelFunctionImpl != null)
			this.excelFunction = excelFunctionImpl.getExcelFunction();
	}

	/**
	 * Gets the excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow getExcelMergeRow() {
		return excelMergeRow;
	}

	/**
	 * Sets the excel merge row.
	 *
	 * @param excelMergeRowImpl the new excel merge row
	 */
	public void setExcelMergeRow(ExcelMergeRowImpl excelMergeRowImpl) {
		if (excelMergeRowImpl != null)
			this.excelMergeRow = excelMergeRowImpl.getExcelMergeRow();
	}

}
