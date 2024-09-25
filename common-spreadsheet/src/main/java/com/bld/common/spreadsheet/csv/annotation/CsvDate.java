/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.common.spreadsheet.csv.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;

/**
 * The Interface CsvDateFormat.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface CsvDate {

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
