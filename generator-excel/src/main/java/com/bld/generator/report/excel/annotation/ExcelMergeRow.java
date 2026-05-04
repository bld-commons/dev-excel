/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelMergeRow.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelMergeRow.<br>
 * Marks a field for vertical cell merging: consecutive rows whose merge-driver
 * value does not change are collapsed into a single merged cell in the output sheet.<br>
 *
 * <p><strong>Two usage modes:</strong></p>
 * <ul>
 *   <li><b>Driver column</b> — {@code @ExcelMergeRow} (empty {@code value}):<br>
 *       This column is the driver. Its own field value is compared between consecutive
 *       rows; when it changes a new merge region begins. The driver column is typically
 *       the primary key or grouping identifier and should appear in the leftmost position
 *       among the merged columns.</li>
 *   <li><b>Dependent column</b> — {@code @ExcelMergeRow("driverField")}:<br>
 *       This column follows another field named {@code driverField}. Its cells are merged
 *       whenever the driver field's value changes, regardless of this column's own value.
 *       Multiple dependent columns can reference the same driver.</li>
 * </ul>
 *
 * <p><strong>Prerequisites:</strong></p>
 * <ul>
 *   <li>The {@code notMerge} attribute of {@link com.bld.generator.report.excel.annotation.ExcelSheetLayout}
 *       must be {@code false} (the default).</li>
 *   <li>The input row list must be sorted so that rows belonging to the same merge group
 *       are contiguous.</li>
 * </ul>
 *
 * <p>For formula columns, use {@link ExcelFunctionMergeRow} which embeds an
 * {@code ExcelMergeRow} and applies the same merge logic to computed cells.</p>
 *
 * <p>This annotation targets fields of {@link com.bld.generator.report.excel.RowSheet} classes.</p>
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface ExcelMergeRow {


	/**
	 * Name of the driver field that controls when this column's cells are merged.
	 * <ul>
	 *   <li>Empty string (default) — this column is its own driver: cells are merged
	 *       while this field's value remains equal across consecutive rows.</li>
	 *   <li>Non-empty string — name of another field in the same {@code RowSheet} class
	 *       whose value change triggers the start of a new merge region for this column.</li>
	 * </ul>
	 *
	 * @return the driver field name, or {@code ""} if this column is the driver
	 */
	public String value() default "";
}
