package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

@Retention(RUNTIME)
public @interface ExcelFunctionSubTotal {

	public boolean value() default false;
	
	/**
	 * Excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout excelCellLayout() default @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,font = @ExcelFont(bold = true));
	
}
