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

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicChart.
 *
 * @param <T> the generic type
 */
public abstract class DynamicChart<T extends DynamicRowSheet> extends SheetDynamicData<T> {

	/** The list excel chart. */
	private List<ExcelChart> listExcelChart;
	

	/**
	 * Instantiates a new dynamic chart.
	 *
	 * @param nameSheet the name sheet
	 */
	public DynamicChart(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		this.listExcelChart=new ArrayList<>();
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
	 * Adds the excel chart.
	 *
	 * @param excelChartImpl the excel chart impl
	 */
	public void addExcelChart(ExcelChartImpl excelChartImpl) {
		if (excelChartImpl != null)
			this.listExcelChart.add(excelChartImpl.getExcelChart());
	}

}
