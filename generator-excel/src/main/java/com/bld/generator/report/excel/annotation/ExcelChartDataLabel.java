/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelChartDataLabel.java
 * 
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;


/**
 * Controls the visibility and content of data labels rendered on a chart series.
 * <p>
 * Apply this annotation as the {@code excelChartDataLabel} attribute of
 * {@link ExcelChartCategory} to configure which label elements are shown next to
 * each data point (value, category name, series name, legend key).
 * </p>
 * <p>
 * Data labels are only rendered when {@link #enable()} is {@code true}; all other
 * flags are ignored otherwise.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * @ExcelChartCategory(
 *     fieldName = "amount",
 *     excelChartDataLabel = @ExcelChartDataLabel(
 *         enable      = true,
 *         showVal     = true,
 *         showCatName = false,
 *         showSerName = false,
 *         showLegendKey = false
 *     )
 * )
 * }</pre>
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
