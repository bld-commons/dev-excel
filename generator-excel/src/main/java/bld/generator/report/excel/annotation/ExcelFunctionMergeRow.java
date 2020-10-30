/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelFunctionMergeRow.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;

/**
 * The Interface ExcelFunctionMergeRow.<br>
 * ExcelFunctionMergeRow is used to configure the cell layout merged and define
 * the excel function.<br>
 * It is composed from:
 * <ul>
 * <li>ExcelCellsLayout - to define cell layout</li>
 * <li>ExcelColumn - to define the header description and the position of
 * column</li>
 * <li>ExcelMergeRow - it is used to merge cells after checking the equality the
 * reference field values</li>
 * <li>ExcelFunction - to define the function</li>
 * <li>ExcelColumnWidth - to set column width<7li>
 * <li>ExcelHeaderCellLayout - to define header cell layout</li>
 * </ul>
 * 
 * ExcelFunctionMergeRow is parameter of the annotation
 * {@link bld.generator.report.excel.annotation.ExcelFunctionRows}.
 * 
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
}
