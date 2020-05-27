/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelSuperHeaders.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelSuperHeaders.<br>
 * ExcelSuperHeaders manages a list of the top level header cell through superHeaders property.<br>
 * It is used on SheetData classes and it do not used on SheetDynamicData<br>
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelSuperHeaders {

	
	/**
	 * Super headers.
	 *
	 * @return the excel super header[]
	 */
	public ExcelSuperHeader[] superHeaders() default {};
}
