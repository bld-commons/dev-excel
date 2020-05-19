/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelChartImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;

import bld.generator.report.excel.annotation.ExcelChart;
import lombok.Data;

/**
 * The Class ExcelChartImpl.
 */
@Data
public class ExcelChartImpl implements Cloneable {

	/** The field name. */
	protected String fieldName;

	/** The chart types. */
	protected ChartTypes chartTypes;

	/** The size row. */
	protected int sizeRow;

	/** The size column. */
	protected int sizeColumn;

	/** The legend position. */
	protected LegendPosition legendPosition;

	/** The category axis. */
	protected AxisPosition categoryAxis;

	/** The value axis. */
	protected AxisPosition valueAxis;

	/** The function. */
	protected String function;
	
	/** The x axis. */
	private String xAxis;
	
	/** The group. */
	private boolean group;
	
	/** The title. */
	private String title;

	/**
	 * Gets the excel chart.
	 *
	 * @return the excel chart
	 */
	public ExcelChart getExcelChart() {
		return new ExcelChart() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelChart.class;
			}

			@Override
			public String fieldName() {
				return fieldName;
			}

			@Override
			public ChartTypes chartTypes() {
				return chartTypes;
			}

			@Override
			public int sizeRow() {
				return sizeRow;
			}

			@Override
			public int sizeColumn() {
				return sizeColumn;
			}

			@Override
			public LegendPosition legendPosition() {
				return legendPosition;
			}

			@Override
			public AxisPosition categoryAxis() {
				return categoryAxis;
			}

			@Override
			public AxisPosition valueAxis() {
				return valueAxis;
			}

			@Override
			public String function() {
				return function;
			}

			@Override
			public String xAxis() {
				return xAxis;
			}

			@Override
			public boolean group() {
				return group;
			}

			@Override
			public String title() {
				return title;
			}

		};
	}


	/**
	 * Instantiates a new excel chart impl.
	 *
	 * @param fieldName      the field name
	 * @param chartTypes     the chart types
	 * @param sizeRow        the size row
	 * @param sizeColumn     the size column
	 * @param legendPosition the legend position
	 * @param categoryAxis   the category axis
	 * @param valueAxis      the value axis
	 * @param function       the function
	 * @param xAxis          the x axis
	 * @param group          the group
	 * @param title          the title
	 */
	public ExcelChartImpl(String fieldName, ChartTypes chartTypes, int sizeRow, int sizeColumn, LegendPosition legendPosition, AxisPosition categoryAxis, AxisPosition valueAxis, String function, String xAxis, boolean group, String title) {
		super();
		this.fieldName = fieldName;
		this.chartTypes = chartTypes;
		this.sizeRow = sizeRow;
		this.sizeColumn = sizeColumn;
		this.legendPosition = legendPosition;
		this.categoryAxis = categoryAxis;
		this.valueAxis = valueAxis;
		this.function = function;
		this.xAxis = xAxis;
		this.group = group;
		this.title = title;
	}










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

}
