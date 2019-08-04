/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;

// TODO: Auto-generated Javadoc
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
	
	
	
}
