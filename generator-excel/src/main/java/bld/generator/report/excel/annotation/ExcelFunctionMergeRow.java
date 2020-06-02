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

/**
 * The Interface ExcelFunctionMergeRow.<br>
 * ExcelFunctionMergeRow is used to configure the cell layout merged and define the excel function.<br>
 * It is composed from:
 * <ul>
 * <li>ExcelCellsLayout - to define cell layout</li>
 * <li>ExcelColumn - to define the header of column</li>
 * <li>ExcelMergeRow - to check another field if the field changes then it must to change cell</li>
 * <li>ExcelFunction - to define the function</li>
 * </ul>
 *  
 *  It is parameter of the annotation ExcelFunctionRows.
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
	
	
}
