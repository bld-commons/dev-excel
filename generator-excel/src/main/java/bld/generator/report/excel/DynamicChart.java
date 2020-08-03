/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.DynamicChart.java
*/
package bld.generator.report.excel;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import bld.generator.report.excel.annotation.ExcelChart;
import bld.generator.report.excel.annotation.impl.ExcelChartImpl;
import bld.generator.report.excel.constant.ExcelConstant;

/**
 * 
 * 
 * The Class DynamicChart.
 * @param <T> the generic type
 * <br>
 * DynamicChart is used to generate charts through dynamic columns
 * 
 *
 * 
 *  
 */
public abstract class DynamicChart<T extends DynamicRowSheet> extends SheetDynamicData<T> {

	/** The list excel chart. */
	private List<ExcelChart> listExcelChart;
	

	/**
	 * Instantiates a new dynamic chart.
	 *
	 * @param sheetName the name sheet
	 */
	public DynamicChart(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String sheetName) {
		super(sheetName);
		this.listExcelChart=new ArrayList<>();
	}

	

	
	/**
	 * Adds the excel chart.
	 *
	 * @param excelChartImpl the excel chart impl
	 */
	public void addExcelChart(ExcelChartImpl excelChartImpl) {
		if (excelChartImpl != null)
			this.listExcelChart.add(excelChartImpl.getExcelChart());
	}




	/**
	 * Gets the list excel chart.
	 *
	 * @return the list excel chart
	 */
	public List<ExcelChart> getListExcelChart() {
		return listExcelChart;
	}




	/**
	 * Sets the list excel chart.
	 *
	 * @param listExcelChart the new list excel chart
	 */
	public void setListExcelChart(List<ExcelChart> listExcelChart) {
		this.listExcelChart = listExcelChart;
	}




	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listExcelChart == null) ? 0 : listExcelChart.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DynamicChart<?> other = (DynamicChart<?>) obj;
		if (listExcelChart == null) {
			if (other.listExcelChart != null)
				return false;
		} else if (!listExcelChart.equals(other.listExcelChart))
			return false;
		return true;
	}

	
	
}
