/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelChart.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelChart.
 * 
 * ExcelChart is used to create different chart types.
 * <br>
 * This annotation is composed by:<br>
 * <ul>
 * <li>id - is the chart grouping identifier</li>
 * <li>FieldName - chart identifier</li>
 * <li>Function - is the chart function</li>
 * <li>ChartTypes - to set the chart type</li>
 * <li>SizeRow - to set height in terms number of rows</li>
 * <li>SizeColumn - to set width in terms number of columns</li>
 * <li>LegendPosition - to configure the legend position</li>
 * <li>CategoryAxis - to set the category position</li>
 * <li>ValueAxis - to set the value position</li>
 * <li>xAxis - to set the axis categories description</li>
 * <li>Group - to group series</li>
 * </ul>
 * 
 * It is a property of the annotation {@link bld.generator.report.excel.annotation.ExcelCharts} 
 */
@Retention(RUNTIME)
@Target({})
public @interface ExcelChart {

	
	/**
	 * Id.
	 *
	 * @return the string
	 */
	public String id();
	
	public ExcelChartCategory[] excelChartCategories();
	
	public String xAxis();
	
	/**
	 * Chart types.
	 *
	 * @return the chart types
	 */
	public ChartTypes chartTypes() default ChartTypes.LINE;

	/**
	 * Size row.
	 *
	 * @return the int
	 */
	public int sizeRow();

	/**
	 * Size column.
	 *
	 * @return the int
	 */
	public int sizeColumn();

	/**
	 * Legend position.
	 *
	 * @return the legend position
	 */
	public LegendPosition legendPosition() default LegendPosition.BOTTOM;

	/**
	 * Category axis.
	 *
	 * @return the axis position
	 */
	public AxisPosition categoryAxis() default AxisPosition.BOTTOM;

	/**
	 * Value axis.
	 *
	 * @return the axis position
	 */
	public AxisPosition valueAxis() default AxisPosition.LEFT;
	
	
	/**
	 * Group.
	 *
	 * @return true, if successful
	 */
	public boolean group() default false;
	
	
	/**
	 * Title.
	 *
	 * @return the string
	 */
	public String title()default "";
	

	/**
	 * Line color.
	 *
	 * @return the preset color
	 */
	public PresetColor[] lineColor() default PresetColor.BLACK;
	
	/**
	 * Axis line color.
	 *
	 * @return the preset color
	 */
	public PresetColor axisLineColor() default PresetColor.BLACK;
	
	/**
	 * Grid line color.
	 *
	 * @return the preset color
	 */
	public PresetColor gridLineColor() default PresetColor.GRAY;
	
	/**
	 * Crosses.
	 *
	 * @return the axis crosses
	 */
	public AxisCrosses crosses () default AxisCrosses.AUTO_ZERO;
	
	/**
	 * Cross between.
	 *
	 * @return the axis cross between
	 */
	public AxisCrossBetween crossBetween() default AxisCrossBetween.BETWEEN;
	

	
	public boolean showLeaderLines() default true;
	
	public ExcelChartDataLabel excelChartDataLabel() default @ExcelChartDataLabel;
	
	public boolean smooth() default true;
	
}
