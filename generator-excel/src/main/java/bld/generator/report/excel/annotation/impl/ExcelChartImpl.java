/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;

import bld.generator.report.excel.annotation.ExcelChart;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelChartImpl.
 */
public class ExcelChartImpl{

	
	/** The field title. */
	protected String fieldTitle;
	
	/** The start key chart. */
	protected String startKeyChart;
	
	/** The end key chart. */
	protected String endKeyChart;
	
	/** The chart types. */
	protected ChartTypes chartTypes;
	
	/** The size row. */
	protected int sizeRow;
	
	/** The size column. */
	protected int sizeColumn;

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
			public String startKeyChart() {
				return startKeyChart;
			}

			@Override
			public String endKeyChart() {
				return endKeyChart;
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
			
		};
	}

	

	/**
	 * Instantiates a new excel chart impl.
	 *
	 * @param fieldTitle the field title
	 * @param startKeyChart the start key chart
	 * @param endKeyChart the end key chart
	 * @param chartTypes the chart types
	 * @param sizeRow the size row
	 * @param sizeColumn the size column
	 */
	public ExcelChartImpl(String fieldTitle, String startKeyChart, String endKeyChart, ChartTypes chartTypes, int sizeRow, int sizeColumn) {
		super();
		this.fieldTitle = fieldTitle;
		this.startKeyChart = startKeyChart;
		this.endKeyChart = endKeyChart;
		this.chartTypes = chartTypes;
		this.sizeRow = sizeRow;
		this.sizeColumn = sizeColumn;
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
	 * Gets the start key chart.
	 *
	 * @return the start key chart
	 */
	public String getStartKeyChart() {
		return startKeyChart;
	}

	/**
	 * Sets the start key chart.
	 *
	 * @param startKeyChart the new start key chart
	 */
	public void setStartKeyChart(String startKeyChart) {
		this.startKeyChart = startKeyChart;
	}

	/**
	 * Gets the end key chart.
	 *
	 * @return the end key chart
	 */
	public String getEndKeyChart() {
		return endKeyChart;
	}

	/**
	 * Sets the end key chart.
	 *
	 * @param endKeyChart the new end key chart
	 */
	public void setEndKeyChart(String endKeyChart) {
		this.endKeyChart = endKeyChart;
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


	
	
	
}
