/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelChartImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.util.Arrays;
import java.util.Objects;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;

import bld.generator.report.excel.annotation.ExcelChart;
import bld.generator.report.excel.annotation.ExcelChartCategory;
import bld.generator.report.excel.annotation.ExcelChartDataLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelChartImpl.
 */
public class ExcelChartImpl extends ExcelAnnotationImpl<ExcelChart> {

	/** The id. */
	private String id;

	/** The excel chart categories. */
	private ExcelChartCategory[] excelChartCategories;

	/** The chart types. */
	private ChartTypes chartTypes;

	/** The size row. */
	private int sizeRow;

	/** The size column. */
	private int sizeColumn;

	/** The legend position. */
	private LegendPosition legendPosition;

	/** The category axis. */
	private AxisPosition categoryAxis;

	/** The value axis. */
	private AxisPosition valueAxis;



	/** The x axis. */
	private String xAxis;

	/** The group. */
	private boolean group;

	/** The title. */
	private String title;

	/** The line color. */
	private PresetColor[] lineColor;

	/** The axis line color. */
	private PresetColor axisLineColor;

	/** The grid line color. */
	private PresetColor gridLineColor;

	/** The crosses. */
	private AxisCrosses crosses;

	/** The cross between. */
	private AxisCrossBetween crossBetween;
	
	/** The show leader lines. */
	private boolean showLeaderLines;

	/** The excel chart data label. */
	private ExcelChartDataLabel excelChartDataLabel;
	
	/** The smooth. */
	private boolean smooth;

	/** The spreadsheet version. */
	private SpreadsheetVersion spreadsheetVersion;
	
	
	

	/**
	 * Instantiates a new excel chart impl.
	 *
	 * @param id the id
	 * @param excelChartCategories the excel chart categories
	 * @param chartTypes the chart types
	 * @param sizeRow the size row
	 * @param sizeColumn the size column
	 * @param legendPosition the legend position
	 * @param categoryAxis the category axis
	 * @param valueAxis the value axis
	 * @param xAxis the x axis
	 * @param group the group
	 * @param title the title
	 * @param lineColor the line color
	 * @param axisLineColor the axis line color
	 * @param gridLineColor the grid line color
	 * @param crosses the crosses
	 * @param crossBetween the cross between
	 * @param showLeaderLines the show leader lines
	 * @param excelChartDataLabel the excel chart data label
	 * @param smooth the smooth
	 * @param spreadsheetVersion the spreadsheet version
	 */
	public ExcelChartImpl(String id, ExcelChartCategory[] excelChartCategories, ChartTypes chartTypes, int sizeRow, int sizeColumn, LegendPosition legendPosition, AxisPosition categoryAxis, AxisPosition valueAxis, String xAxis, boolean group,
			String title, PresetColor[] lineColor, PresetColor axisLineColor, PresetColor gridLineColor, AxisCrosses crosses, AxisCrossBetween crossBetween, boolean showLeaderLines, ExcelChartDataLabel excelChartDataLabel, boolean smooth,
			SpreadsheetVersion spreadsheetVersion) {
		super();
		this.id = id;
		this.excelChartCategories = excelChartCategories;
		this.chartTypes = chartTypes;
		this.sizeRow = sizeRow;
		this.sizeColumn = sizeColumn;
		this.legendPosition = legendPosition;
		this.categoryAxis = categoryAxis;
		this.valueAxis = valueAxis;
		this.xAxis = xAxis;
		this.group = group;
		this.title = title;
		this.lineColor = lineColor;
		this.axisLineColor = axisLineColor;
		this.gridLineColor = gridLineColor;
		this.crosses = crosses;
		this.crossBetween = crossBetween;
		this.showLeaderLines = showLeaderLines;
		this.excelChartDataLabel = excelChartDataLabel;
		this.smooth = smooth;
		this.spreadsheetVersion = spreadsheetVersion;
	}

	/**
	 * Gets the spreadsheet version.
	 *
	 * @return the spreadsheet version
	 */
	public SpreadsheetVersion getSpreadsheetVersion() {
		return spreadsheetVersion;
	}

	/**
	 * Sets the spreadsheet version.
	 *
	 * @param spreadsheetVersion the new spreadsheet version
	 */
	public void setSpreadsheetVersion(SpreadsheetVersion spreadsheetVersion) {
		this.spreadsheetVersion = spreadsheetVersion;
	}

	/**
	 * Gets the excel chart categories.
	 *
	 * @return the excel chart categories
	 */
	public ExcelChartCategory[] getExcelChartCategories() {
		return excelChartCategories;
	}

	/**
	 * Sets the excel chart categories.
	 *
	 * @param excelChartCategories the new excel chart categories
	 */
	public void setExcelChartCategories(ExcelChartCategory[] excelChartCategories) {
		this.excelChartCategories = excelChartCategories;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the crosses.
	 *
	 * @return the crosses
	 */
	public AxisCrosses getCrosses() {
		return crosses;
	}

	/**
	 * Sets the crosses.
	 *
	 * @param crosses the new crosses
	 */
	public void setCrosses(AxisCrosses crosses) {
		this.crosses = crosses;
	}

	/**
	 * Gets the cross between.
	 *
	 * @return the cross between
	 */
	public AxisCrossBetween getCrossBetween() {
		return crossBetween;
	}

	/**
	 * Sets the cross between.
	 *
	 * @param crossBetween the new cross between
	 */
	public void setCrossBetween(AxisCrossBetween crossBetween) {
		this.crossBetween = crossBetween;
	}

	/**
	 * Instantiates a new excel chart impl.
	 */
	public ExcelChartImpl() {
		super();
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
	 * Gets the x axis.
	 *
	 * @return the x axis
	 */
	public String getxAxis() {
		return xAxis;
	}

	/**
	 * Sets the x axis.
	 *
	 * @param xAxis the new x axis
	 */
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}

	/**
	 * Checks if is group.
	 *
	 * @return the group
	 */
	public boolean isGroup() {
		return group;
	}

	/**
	 * Sets the group.
	 *
	 * @param group the new group
	 */
	public void setGroup(boolean group) {
		this.group = group;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the line color.
	 *
	 * @return the line color
	 */
	public PresetColor[] getLineColor() {
		return lineColor;
	}

	/**
	 * Sets the line color.
	 *
	 * @param lineColor the new line color
	 */
	public void setLineColor(PresetColor... lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * Gets the axis line color.
	 *
	 * @return the axis line color
	 */
	public PresetColor getAxisLineColor() {
		return axisLineColor;
	}

	/**
	 * Sets the axis line color.
	 *
	 * @param axisLineColor the new axis line color
	 */
	public void setAxisLineColor(PresetColor axisLineColor) {
		this.axisLineColor = axisLineColor;
	}

	/**
	 * Gets the grid line color.
	 *
	 * @return the grid line color
	 */
	public PresetColor getGridLineColor() {
		return gridLineColor;
	}

	/**
	 * Sets the grid line color.
	 *
	 * @param gridLineColor the new grid line color
	 */
	public void setGridLineColor(PresetColor gridLineColor) {
		this.gridLineColor = gridLineColor;
	}
	
	

	/**
	 * Checks if is show leader lines.
	 *
	 * @return true, if is show leader lines
	 */
	public boolean isShowLeaderLines() {
		return showLeaderLines;
	}

	/**
	 * Sets the show leader lines.
	 *
	 * @param showLeaderLines the new show leader lines
	 */
	public void setShowLeaderLines(boolean showLeaderLines) {
		this.showLeaderLines = showLeaderLines;
	}

	/**
	 * Gets the excel chart data label.
	 *
	 * @return the excel chart data label
	 */
	public ExcelChartDataLabel getExcelChartDataLabel() {
		return excelChartDataLabel;
	}

	/**
	 * Sets the excel chart data label.
	 *
	 * @param excelChartDataLabel the new excel chart data label
	 */
	public void setExcelChartDataLabel(ExcelChartDataLabel excelChartDataLabel) {
		this.excelChartDataLabel = excelChartDataLabel;
	}



	/**
	 * Checks if is smooth.
	 *
	 * @return true, if is smooth
	 */
	public boolean isSmooth() {
		return smooth;
	}

	/**
	 * Sets the smooth.
	 *
	 * @param smooth the new smooth
	 */
	public void setSmooth(boolean smooth) {
		this.smooth = smooth;
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
		result = prime * result + Arrays.hashCode(excelChartCategories);
		result = prime * result + Arrays.hashCode(lineColor);
		result = prime * result
				+ Objects.hash(axisLineColor, categoryAxis, chartTypes, crossBetween, crosses, excelChartDataLabel, gridLineColor, group, id, legendPosition, showLeaderLines, sizeColumn, sizeRow, smooth, spreadsheetVersion, title, valueAxis, xAxis);
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
		ExcelChartImpl other = (ExcelChartImpl) obj;
		return axisLineColor == other.axisLineColor && categoryAxis == other.categoryAxis && chartTypes == other.chartTypes && crossBetween == other.crossBetween && crosses == other.crosses
				&& Arrays.equals(excelChartCategories, other.excelChartCategories) && Objects.equals(excelChartDataLabel, other.excelChartDataLabel) && gridLineColor == other.gridLineColor && group == other.group && Objects.equals(id, other.id)
				&& legendPosition == other.legendPosition && Arrays.equals(lineColor, other.lineColor) && showLeaderLines == other.showLeaderLines && sizeColumn == other.sizeColumn && sizeRow == other.sizeRow && smooth == other.smooth
				&& spreadsheetVersion == other.spreadsheetVersion && Objects.equals(title, other.title) && valueAxis == other.valueAxis && Objects.equals(xAxis, other.xAxis);
	}

	


}
