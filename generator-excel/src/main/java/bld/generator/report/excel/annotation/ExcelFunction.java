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
 * Here are a series of examples: 
 * <ul>
 * <li>How to sum the records on a column? - You must concatenate column name with RowStart and RowEnd, example sum(${fieldNameRowStart}:${fieldNameRowEnd}), in the same way on a merged cell you can get the sum of a group of rows.<br>The Fields names are equals</li>
 * <li>How to sum the records on a row? - Here You will use 2 different fields names and the function is written so: sum(${fieldName1}:${fiedlName2}), indexes should not be written</li>
 * </ul>
 * 
 * 
 * it is a property of the following annotations:
 * <ul>
 * <li>{@link bld.generator.report.excel.annotation.ExcelFunctionMergeRow}</li>
 * <li>{@link bld.generator.report.excel.annotation.ExcelFunctionRow}</li>
 * </ul>
 * 
 * 
 * 
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
