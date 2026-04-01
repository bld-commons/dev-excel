package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marker annotation that instructs the generator to clear all existing data rows
 * from the sheet before writing new content.
 * <p>
 * Apply this annotation to a {@link com.bld.generator.report.excel.RowSheet} class
 * when you want to ensure that any previously rendered rows are removed before the
 * current dataset is written.  This is useful when the same sheet template is reused
 * across multiple generation cycles and stale data must not be carried over.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * @ExcelClearRows
 * public class SalaryRow implements RowSheet {
 *     @ExcelColumn(name = "Name", index = 0)
 *     private String name;
 *     // ...
 * }
 * }</pre>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelClearRows {

}
