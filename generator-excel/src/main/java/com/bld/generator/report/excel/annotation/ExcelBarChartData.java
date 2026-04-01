/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelBarChartData.java
 * 
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.xddf.usermodel.chart.BarDirection;


/**
 * Configures the direction of a bar chart generated from a sheet's data.
 * <p>
 * Apply this annotation at class level on a {@link com.bld.generator.report.excel.SheetData}
 * subclass that is also annotated with {@link ExcelCharts} to control whether the bars
 * are rendered horizontally ({@link BarDirection#BAR}) or vertically
 * ({@link BarDirection#COL}).
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * @ExcelBarChartData(BarDirection.COL)
 * @ExcelCharts(excelCharts = { @ExcelChart(id = "sales", ...) })
 * public class SalesRow implements RowSheet { ... }
 * }</pre>
 */
@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelBarChartData {

	/**
	 * Direction of the bars in the chart.
	 * <p>
	 * Use {@link BarDirection#BAR} for horizontal bars (default) or
	 * {@link BarDirection#COL} for vertical columns.
	 * </p>
	 *
	 * @return the bar direction
	 */
	public BarDirection value() default BarDirection.BAR;
	
}
