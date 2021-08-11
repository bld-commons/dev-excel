/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelChartDataLabel.java
 * 
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

/**
 * The Interface ExcelChartDataLabel.
 */
@Documented
@Retention(RUNTIME)
public @interface ExcelChartDataLabel {

	/**
	 * Enable.
	 *
	 * @return true, if successful
	 */
	public boolean enable() default false;
	
	/**
	 * Show val.
	 *
	 * @return true, if successful
	 */
	public boolean showVal() default true;
	
	/**
	 * Show legend key.
	 *
	 * @return true, if successful
	 */
	public boolean showLegendKey() default true;
	
	/**
	 * Show cat name.
	 *
	 * @return true, if successful
	 */
	public boolean showCatName() default true;
	
	/**
	 * Show ser name.
	 *
	 * @return true, if successful
	 */
	public boolean showSerName() default true;
}
