/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelChartImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;

import bld.generator.report.excel.annotation.ExcelChart;

/**
 * The Class ExcelChartImpl.
 */
public class ExcelChartImpl extends ExcelAnnotationImpl<ExcelChart> {

	/** The field name. */
	private String fieldName;

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

	/** The function. */
	private String function;

	/** The x axis. */
	private String xAxis;

	/** The group. */
	private boolean group;

	/** The title. */
	private String title;

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
	 * Instantiates a new excel chart impl.
	 */
	public ExcelChartImpl() {
		super();
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName the new field name
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryAxis == null) ? 0 : categoryAxis.hashCode());
		result = prime * result + ((chartTypes == null) ? 0 : chartTypes.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + (group ? 1231 : 1237);
		result = prime * result + ((legendPosition == null) ? 0 : legendPosition.hashCode());
		result = prime * result + sizeColumn;
		result = prime * result + sizeRow;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((valueAxis == null) ? 0 : valueAxis.hashCode());
		result = prime * result + ((xAxis == null) ? 0 : xAxis.hashCode());
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
		if (categoryAxis != other.categoryAxis)
			return false;
		if (chartTypes != other.chartTypes)
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (group != other.group)
			return false;
		if (legendPosition != other.legendPosition)
			return false;
		if (sizeColumn != other.sizeColumn)
			return false;
		if (sizeRow != other.sizeRow)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (valueAxis != other.valueAxis)
			return false;
		if (xAxis == null) {
			if (other.xAxis != null)
				return false;
		} else if (!xAxis.equals(other.xAxis))
			return false;
		return true;
	}

}
