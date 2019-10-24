/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelFunctionMergeRow.
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface ExcelFunctionMergeRow {
	
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
	 * Excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow excelMergeRow();
	
	/**
	 * Excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction excelFunction();
	
}
