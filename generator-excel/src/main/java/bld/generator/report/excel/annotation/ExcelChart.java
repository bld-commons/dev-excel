/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;

/**
 * The Interface ExcelChart.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelChart {

	/**
	 * Field title.
	 *
	 * @return the string
	 */
	public String fieldTitle();
	
	/**
	 * Start key chart.
	 *
	 * @return the string
	 */
	public String startKeyChart();
	
	/**
	 * End key chart.
	 *
	 * @return the string
	 */
	public String endKeyChart();
	
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
	
	
}
