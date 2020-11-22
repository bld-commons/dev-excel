/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelSubtotalImpl.java
 */
package bld.generator.report.excel.annotation.impl;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelSubtotal;

/**
 * The Class ExcelSubtotalImpl.
 */
public class ExcelSubtotalImpl extends ExcelAnnotationImpl<ExcelSubtotal> {

	/** The excel cell layout. */
	private ExcelCellLayout excelCellLayout;

	/** The enable. */
	private boolean enable;

	/** The data consolidate function. */
	private DataConsolidateFunction dataConsolidateFunction;
	
	/**
	 * Instantiates a new excel subtotal impl.
	 */
	public ExcelSubtotalImpl() {
	}

	/**
	 * Instantiates a new excel subtotal impl.
	 *
	 * @param excelCellLayout         the excel cell layout
	 * @param enable                  the enable
	 * @param dataConsolidateFunction the data consolidate function
	 */
	public ExcelSubtotalImpl(ExcelCellLayout excelCellLayout, boolean enable, DataConsolidateFunction dataConsolidateFunction) {
		super();
		this.excelCellLayout = excelCellLayout;
		this.enable = enable;
		this.dataConsolidateFunction = dataConsolidateFunction;
	}

	/**
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
		return excelCellLayout;
	}

	/**
	 * Checks if is enable.
	 *
	 * @return true, if is enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Gets the data consolidate function.
	 *
	 * @return the data consolidate function
	 */
	public DataConsolidateFunction getDataConsolidateFunction() {
		return dataConsolidateFunction;
	}

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayout the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayout excelCellLayout) {
		this.excelCellLayout = excelCellLayout;
	}

	/**
	 * Sets the enable.
	 *
	 * @param enable the new enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Sets the data consolidate function.
	 *
	 * @param dataConsolidateFunction the new data consolidate function
	 */
	public void setDataConsolidateFunction(DataConsolidateFunction dataConsolidateFunction) {
		this.dataConsolidateFunction = dataConsolidateFunction;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataConsolidateFunction == null) ? 0 : dataConsolidateFunction.hashCode());
		result = prime * result + (enable ? 1231 : 1237);
		result = prime * result + ((excelCellLayout == null) ? 0 : excelCellLayout.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelSubtotalImpl other = (ExcelSubtotalImpl) obj;
		if (dataConsolidateFunction != other.dataConsolidateFunction)
			return false;
		if (enable != other.enable)
			return false;
		if (excelCellLayout == null) {
			if (other.excelCellLayout != null)
				return false;
		} else if (!excelCellLayout.equals(other.excelCellLayout))
			return false;
		return true;
	}

}
