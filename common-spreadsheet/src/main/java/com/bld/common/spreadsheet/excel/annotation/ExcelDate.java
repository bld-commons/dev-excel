/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelDate.java
*/
package com.bld.common.spreadsheet.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;

/**
 * The Interface ExcelDate.<br>
 * Configures date formatting and time-zone handling for date fields in Excel generation
 * and reading. Applied to fields of the following classes:
 * <ul>
 *   <li>{@code RowSheet}</li>
 *   <li>{@code SheetSummary}</li>
 *   <li>{@code RowSheetRead}</li>
 * </ul>
 *
 * <p>Supported field types: {@link java.util.Date}, {@link java.util.Calendar},
 * {@link java.sql.Timestamp}, {@link java.time.LocalDate}, {@link java.time.LocalDateTime},
 * {@link java.time.Instant}, {@link java.time.OffsetDateTime}.</p>
 *
 * <p>The {@link #timezone()} attribute accepts a Spring {@code ${...}} placeholder so
 * the time zone can be driven by application configuration without recompiling.
 * It defaults to {@code ${spring.jackson.time-zone:}} which resolves to the value of
 * {@code spring.jackson.time-zone} if set, or an empty string (falling back to the JVM
 * default zone) otherwise.</p>
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelDate {


	/**
	 * Date format pattern applied to the cell.
	 *
	 * @return the {@link ColumnDateFormat} constant; defaults to {@code DD_MM_YYYY}
	 */
	public ColumnDateFormat value() default ColumnDateFormat.DD_MM_YYYY;

	/**
	 * Time-zone identifier used when converting {@link java.time.LocalDate},
	 * {@link java.time.LocalDateTime}, or {@link java.time.OffsetDateTime} values
	 * to {@link java.util.Date} before writing to the cell.
	 *
	 * <p>Accepts a Spring property placeholder ({@code ${...}}).
	 * The default value {@code "${spring.jackson.time-zone:}"} reads from
	 * {@code spring.jackson.time-zone} in the application configuration;
	 * if the property is absent the placeholder resolves to an empty string
	 * and the JVM default zone is used.</p>
	 *
	 * <p>Has no effect on {@link java.util.Date}, {@link java.util.Calendar},
	 * {@link java.sql.Timestamp}, or {@link java.time.Instant} fields, which
	 * carry an absolute instant and require no zone conversion.</p>
	 *
	 * @return a {@link java.time.ZoneId} string (e.g. {@code "Europe/Rome"}) or a
	 *         Spring placeholder that resolves to one
	 */
	public String timezone() default "${spring.jackson.time-zone:}";


}
