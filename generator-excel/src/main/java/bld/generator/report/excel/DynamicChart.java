/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
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

	/** The excel chart. */
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

	

	public List<ExcelChart> getListExcelChart() {
		return listExcelChart;
	}



	public void setListExcelChart(List<ExcelChart> listExcelChart) {
		this.listExcelChart = listExcelChart;
	}



	/**
	 * Sets the excel chart.
	 *
	 * @param excelChartImpl the new excel chart
	 */
	public void addExcelChart(ExcelChartImpl excelChartImpl) {
		if (excelChartImpl != null)
			this.listExcelChart.add(excelChartImpl.getExcelChart());
	}

}
