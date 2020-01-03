/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
*/
package bld.generator.report.excel;

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
	private ExcelChart excelChart;
	

	/**
	 * Instantiates a new dynamic chart.
	 *
	 * @param nameSheet the name sheet
	 */
	public DynamicChart(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the excel chart.
	 *
	 * @return the excel chart
	 */
	public ExcelChart getExcelChart() {
		return excelChart;
	}

	/**
	 * Sets the excel chart.
	 *
	 * @param excelChartImpl the new excel chart
	 */
	public void setExcelChart(ExcelChartImpl excelChartImpl) {
		if (excelChartImpl != null)
			this.excelChart = excelChartImpl.getExcelChart();
	}

}
