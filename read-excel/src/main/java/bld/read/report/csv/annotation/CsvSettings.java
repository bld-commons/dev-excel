/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.csv.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface CsvSettings.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface CsvSettings {

	/**
	 * Delimiter.
	 *
	 * @return the char
	 */
	public char delimiter() default ',';

	/**
	 * Quote char.
	 *
	 * @return the char
	 */
	public char quoteChar() default '"';

	/**
	 * Ignore empty lines.
	 *
	 * @return true, if successful
	 */
	public boolean ignoreEmptyLines() default true;

	/**
	 * Record separator.
	 *
	 * @return the string
	 */
	public String recordSeparator() default "\r\n";

	/**
	 * Skip header record.
	 *
	 * @return true, if successful
	 */
	public boolean skipHeaderRecord() default true;

	/**
	 * Trim.
	 *
	 * @return true, if successful
	 */
	public boolean trim() default true;

}
