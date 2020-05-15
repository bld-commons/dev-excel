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
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * The Class DynamicChart.
 * <br>
 * DynamicChart is used to generate charts through dynamic columns
 * 
 *
 * @param <T> the generic type
 *  
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DynamicChart<T extends DynamicRowSheet> extends SheetDynamicData<T> {

	/** The list excel chart. */
	private List<ExcelChart> listExcelChart;
	

	/**
	 * Instantiates a new dynamic chart.
	 *
	 * @param sheetName the name sheet
	 */
	public DynamicChart(@Size(max = 30) String sheetName) {
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

}
