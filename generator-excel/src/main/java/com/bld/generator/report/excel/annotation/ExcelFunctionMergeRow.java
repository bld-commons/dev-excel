/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelFunctionMergeRow.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;


/**
 * The Interface ExcelFunctionMergeRow.<br>
 * Defines a computed (formula) column whose cells are also merged vertically
 * based on a driver field, combining the capabilities of
 * {@link ExcelFunction} and {@link ExcelMergeRow}.<br>
 *
 * <p>Because a formula column has no bound bean field, its cell value is
 * always {@code null} at row-write time. The merge driver must therefore be
 * a <em>regular</em> field declared in the same {@code RowSheet} class via
 * {@link ExcelMergeRow#value()} on {@link #excelMergeRow()}. The merge
 * boundary is determined by changes in that driver field, not by the formula
 * result itself.</p>
 *
 * <p>Composed of:</p>
 * <ul>
 *   <li>{@link #excelCellsLayout()} — cell layout for the merged result cell</li>
 *   <li>{@link #excelColumn()} — column header name and position index</li>
 *   <li>{@link #excelMergeRow()} — merge driver configuration
 *       (use {@code @ExcelMergeRow("driverField")} to reference a driver column)</li>
 *   <li>{@link #excelFunction()} — the Excel formula written into the merged cell</li>
 *   <li>{@link #excelColumnWidth()} — column width</li>
 *   <li>{@link #excelHeaderCellLayout()} — header cell layout</li>
 *   <li>{@link #excelSubtotal()} — optional subtotal function applied below the column</li>
 *   <li>{@link #excelNumberFormat()} — number format for the result cell</li>
 * </ul>
 *
 * <p>{@code ExcelFunctionMergeRow} is used as a parameter of
 * {@link com.bld.generator.report.excel.annotation.ExcelFunctionRows}.</p>
 */
@Retention(RUNTIME)
@Target({})
@Documented
public @interface ExcelFunctionMergeRow {

	/**
	 * Excel cells layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout excelCellsLayout() default @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2,locked = true);

	/**
	 * Excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn excelColumn();

	/**
	 * Merge configuration for this formula column.
	 *
	 * <p>Since formula columns have no bean field, the merge trigger cannot be
	 * derived from the column's own value. Always specify a driver field name via
	 * {@code @ExcelMergeRow("driverField")}, where {@code driverField} is a regular
	 * field in the same {@code RowSheet} class annotated with a plain
	 * {@code @ExcelMergeRow} (empty value).</p>
	 *
	 * @return the {@link ExcelMergeRow} specifying the driver field for this formula column
	 */
	public ExcelMergeRow excelMergeRow();

	/**
	 * Excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction excelFunction();

	/**
	 * Excel column width.
	 *
	 * @return the excel column width
	 */
	public ExcelColumnWidth excelColumnWidth() default @ExcelColumnWidth;

	/**
	 * Excel header cell layout.
	 *
	 * @return the excel header cell layout
	 */
	public ExcelHeaderCellLayout excelHeaderCellLayout() default @ExcelHeaderCellLayout;
	
	/**
	 * Excel subtotal.
	 *
	 * @return the excel subtotal
	 */
	public ExcelSubtotal excelSubtotal() default @ExcelSubtotal(enable=false,dataConsolidateFunction=DataConsolidateFunction.SUM);
	
	/**
	 * Number format applied to the function-result cell of the merged row.
	 * <p>
	 * Defaults to {@link ExcelNumberFormat} with no explicit format code, which
	 * means the cell inherits the sheet's default number format.
	 * </p>
	 *
	 * @return the {@link ExcelNumberFormat} for the merged result cell
	 */
	public ExcelNumberFormat excelNumberFormat() default @ExcelNumberFormat;

}
