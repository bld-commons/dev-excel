package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * Configures whether a subtotal row is rendered at the bottom of a function column
 * group, and defines its cell style.
 * <p>
 * Use this annotation inside {@link ExcelFunctionRow} or {@link ExcelFunctionMergeRow}
 * to add a grand-total row that aggregates all the values produced by the associated
 * Excel function across the entire data range.
 * </p>
 * <p>
 * When {@link #value()} is {@code true} the generator appends an extra row below
 * the last data row and applies the {@link #excelCellLayout()} style to it.
 * </p>
 */
@Documented
@Retention(RUNTIME)
public @interface ExcelFunctionSubTotal {

	/**
	 * Whether to render the subtotal row.
	 * <p>
	 * Set to {@code true} to append a grand-total row at the bottom of the
	 * function column; {@code false} (default) to omit it.
	 * </p>
	 *
	 * @return {@code true} if the subtotal row should be rendered
	 */
	public boolean value() default false;

	/**
	 * Cell layout applied to the subtotal row.
	 * <p>
	 * Defaults to right-aligned text with a bold font.
	 * </p>
	 *
	 * @return the {@link ExcelCellLayout} for the subtotal row
	 */
	public ExcelCellLayout excelCellLayout() default @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,font = @ExcelFont(bold = true));

}
