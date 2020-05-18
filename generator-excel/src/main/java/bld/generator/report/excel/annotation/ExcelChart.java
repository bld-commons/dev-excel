/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelChart.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;

/**
 * The Interface ExcelChart.
 * 
 * ExcelChart is used to create different chart types.
 * <br>
 * This annotation is composed from:<br>
 * <ul>
 * <li>Title - chart identifier</li>
 * <li>Function - is the chart function</li>
 * <li>ChartTypes - to set the chart type</li>
 * <li>SizeRow - to set height in terms number of rows</li>
 * <li>SizeColumn - to set width in terms number of columns</li>
 * <li>LegendPosition - to configure the legend position</li>
 * <li>CategoryAxis - to set the category position</li>
 * <li>ValueAxis - to set the value position</li>
 * <li>xAxis - to set the axis categories description</li>
 * </ul>
 * 
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelChart {

	/**
	 * Title.
	 *
	 * @return the string
	 */
	public String title();

	/**
	 * Function.
	 *
	 * @return the string
	 */
	public String function();

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
	public LegendPosition legendPosition() default LegendPosition.TOP_RIGHT;

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
	 * X axis.
	 *
	 * @return the string
	 */
	public String xAxis();

}
