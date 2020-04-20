/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelHeaderGroup.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

/**
 * The Interface ExcelHeaderGroup.
 */
@Retention(RUNTIME)
public @interface ExcelHeaderGroup {

	
	/**
	 * Column name.
	 *
	 * @return the string
	 */
	public String columnName();
	
	/**
	 * Column range.
	 *
	 * @return the string
	 */
	public String columnRange();
	
	/**
	 * Excel header cell layout.
	 *
	 * @return the excel header cell layout
	 */
	public ExcelHeaderCellLayout excelHeaderCellLayout() default @ExcelHeaderCellLayout;
	
}
