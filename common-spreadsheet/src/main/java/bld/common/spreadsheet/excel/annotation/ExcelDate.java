/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelDate.java
*/
package bld.common.spreadsheet.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.common.spreadsheet.constant.ColumnDateFormat;

/**
 * The Interface ExcelDate.<br>
 * ExcelDate is used to set the date format and it is set on fields of the classes:
 * <ul>
 * 	<li>bld.generator.report.excel.RowSheet</li>
 * 	<li>bld.generator.report.excel.SheetSummary</li> 
 * </ul>
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelDate {


	/**
	 * Value.
	 *
	 * @return the column date format
	 */
	public ColumnDateFormat value() default ColumnDateFormat.DD_MM_YYYY;
	
	
	
	
}
