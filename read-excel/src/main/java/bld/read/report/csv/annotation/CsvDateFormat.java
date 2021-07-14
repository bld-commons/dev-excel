/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.csv.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.ColumnDateFormat;

/**
 * The Interface CsvDateFormat.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface CsvDateFormat {

	/**
	 * Value.
	 *
	 * @return the column date format
	 */
	public ColumnDateFormat value() default ColumnDateFormat.DD_MM_YYYY;
	
	/**
	 * Separator.
	 *
	 * @return the string
	 */
	public String separator() default "/";
	
	
}
