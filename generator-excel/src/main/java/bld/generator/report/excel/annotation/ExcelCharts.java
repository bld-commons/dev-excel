/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelCharts.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelCharts.
 * <br>
 * ExcelCharts is used to manage a list of excelCharts, it must be managed on BaseSheet classes.
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelCharts {

	/**
	 * Excel charts.
	 *
	 * @return the excel chart[]
	 */
	public ExcelChart[] excelCharts()default {};
	
}
