/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import it.bld.generator.report.excel.constant.ColumnDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelDate.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelDate {

	/**
	 * Format.
	 *
	 * @return the column date format
	 */
	public ColumnDateFormat format() default ColumnDateFormat.DD_MM_YYYY;
	
}
