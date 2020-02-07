/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelChart.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelCharts {

	public ExcelChart[] excelCharts()default {};
	
}