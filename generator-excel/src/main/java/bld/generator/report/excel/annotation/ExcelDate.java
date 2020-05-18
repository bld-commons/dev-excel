/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelDate.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.ColumnDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelDate.<br>
 * ExcelDate is used to set format date
 */
@Documented
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
