/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelFunction.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelFunction.<br>
 * ExcelFunction is used to define the excel functions through:
 * <ul>
 * <li>Function - to set function</li>
 * <li>NameFunction - function identifier</li>
 * <li>AnotherTable - it is enabled if the function references another table</li>
 * </ul>
 * 
 * it is a property of the following annotations:
 * <ul>
 * <li>{@link bld.generator.report.excel.annotation.ExcelFunctionMergeRow}</li>
 * <li>{@link bld.generator.report.excel.annotation.ExcelFunctionRow}</li>
 * </ul>
 * or it is set on field of SheetField classes
 */
@Documented
@Retention(RUNTIME)
@Target({})
public @interface ExcelFunction {

	/**
	 * Function.
	 *
	 * @return the string
	 */
	public String function();
	
	/**
	 * Name function.
	 *
	 * @return the string
	 */
	public String nameFunction();
	
	/**
	 * Another table.
	 *
	 * @return true, if successful
	 */
	public boolean anotherTable() default true;
	
}
