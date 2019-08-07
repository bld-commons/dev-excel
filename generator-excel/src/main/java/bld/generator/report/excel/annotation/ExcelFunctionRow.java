/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelFunctionRow.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelFunctionRow {
	
	/**
	 * Excel cells layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout excelCellsLayout() default @ExcelCellLayout;
	
	/**
	 * Excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn excelColumn();
	
	/**
	 * Excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction excelFunction();
	
	
}
