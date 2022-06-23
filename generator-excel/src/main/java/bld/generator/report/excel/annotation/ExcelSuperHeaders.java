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

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelSuperHeaders.<br>
 * ExcelSuperHeaders manages a list of the top level header cell through superHeaders property.<br>
 * It is used on {@link bld.generator.report.excel.SheetData} classes and it is not used on {@link bld.generator.report.excel.SheetDynamicData} classes<br>
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
