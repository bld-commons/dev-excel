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

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelChartImpl.
 */
public class ExcelChartImpl implements Cloneable {

	/** The field title. */
	protected String fieldTitle;

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
			public String fieldTitle() {
				return fieldTitle;
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

		};
	}

	/**
	 * Instantiates a new excel chart impl.
	 *
	 * @param fieldTitle     the field title
	 * @param chartTypes     the chart types
	 * @param sizeRow        the size row
	 * @param sizeColumn     the size column
	 * @param legendPosition the legend position
	 * @param categoryAxis   the category axis
	 * @param valueAxis      the value axis
	 * @param function       the function
	 */
	public ExcelChartImpl(String fieldTitle, ChartTypes chartTypes, int sizeRow, int sizeColumn, LegendPosition legendPosition, AxisPosition categoryAxis, AxisPosition valueAxis, String function) {
		super();
		this.fieldTitle = fieldTitle;
		this.chartTypes = chartTypes;
		this.sizeRow = sizeRow;
		this.sizeColumn = sizeColumn;
		this.legendPosition = legendPosition;
		this.categoryAxis = categoryAxis;
		this.valueAxis = valueAxis;
		this.function = function;
	}

	/**
	 * Gets the field title.
	 *
	 * @return the field title
	 */
	public String getFieldTitle() {
		return fieldTitle;
	}

	/**
	 * Sets the field title.
	 *
	 * @param fieldTitle the new field title
	 */
	public void setFieldTitle(String fieldTitle) {
		this.fieldTitle = fieldTitle;
	}

	/**
	 * Gets the chart types.
	 *
	 * @return the chart types
	 */
	public ChartTypes getChartTypes() {
		return chartTypes;
	}

	/**
	 * Sets the chart types.
	 *
	 * @param chartTypes the new chart types
	 */
	public void setChartTypes(ChartTypes chartTypes) {
		this.chartTypes = chartTypes;
	}

	/**
	 * Gets the size row.
	 *
	 * @return the size row
	 */
	public int getSizeRow() {
		return sizeRow;
	}

	/**
	 * Sets the size row.
	 *
	 * @param sizeRow the new size row
	 */
	public void setSizeRow(int sizeRow) {
		this.sizeRow = sizeRow;
	}

	/**
	 * Gets the size column.
	 *
	 * @return the size column
	 */
	public int getSizeColumn() {
		return sizeColumn;
	}

	/**
	 * Sets the size column.
	 *
	 * @param sizeColumn the new size column
	 */
	public void setSizeColumn(int sizeColumn) {
		this.sizeColumn = sizeColumn;
	}

	/**
	 * Gets the legend position.
	 *
	 * @return the legend position
	 */
	public LegendPosition getLegendPosition() {
		return legendPosition;
	}

	/**
	 * Sets the legend position.
	 *
	 * @param legendPosition the new legend position
	 */
	public void setLegendPosition(LegendPosition legendPosition) {
		this.legendPosition = legendPosition;
	}

	/**
	 * Gets the category axis.
	 *
	 * @return the category axis
	 */
	public AxisPosition getCategoryAxis() {
		return categoryAxis;
	}

	/**
	 * Sets the category axis.
	 *
	 * @param categoryAxis the new category axis
	 */
	public void setCategoryAxis(AxisPosition categoryAxis) {
		this.categoryAxis = categoryAxis;
	}

	/**
	 * Gets the value axis.
	 *
	 * @return the value axis
	 */
	public AxisPosition getValueAxis() {
		return valueAxis;
	}

	/**
	 * Sets the value axis.
	 *
	 * @param valueAxis the new value axis
	 */
	public void setValueAxis(AxisPosition valueAxis) {
		this.valueAxis = valueAxis;
	}

	/**
	 * Gets the function.
	 *
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Sets the function.
	 *
	 * @param function the new function
	 */
	public void setFunction(String function) {
		this.function = function;
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
