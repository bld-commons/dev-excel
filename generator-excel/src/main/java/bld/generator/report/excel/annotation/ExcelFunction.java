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

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelFunction.<br>
 * ExcelFunction is used to define the excel functions through:
 * <ul>
 * <li>Function - to set function</li>
 * <li>NameFunction - function identifier</li>
 * <li>AnotherTable - it is enabled if the function is referred to another
 * table</li>
 * </ul>
 * 
 * Here are a series of examples:
 * <ul>
 * <li>How to sum the records on a column? - You must concatenate column name
 * with RowStart and RowEnd, example
 * sum(${fieldNameRowStart}:${fieldNameRowEnd}), in the same way on a merged
 * cell you can obtain the sum of a group of rows.<br>
 * The names of the two fields must be the same</li>
 * <li>How to sum the records on a row? - Here You will use 2 different fields
 * names and the function will be written as follows:
 * sum(${fieldName1}:${fiedlName2}), indexes should not be written</li>
 * </ul>
 * 
 * 
 * ExcelFunction is a property of the following annotations:
 * <ul>
 * <li>{@link bld.generator.report.excel.annotation.ExcelFunctionMergeRow}</li>
 * <li>{@link bld.generator.report.excel.annotation.ExcelFunctionRow}</li>
 * </ul>
 * 
 * 
 * Below an example to calculate functions on rows and columns.
 * <table style="width:100%;">
 * <tr>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">column
 * name 1</th>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">column
 * name 2</th>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">column
 * name 3</th>
 * <th style="width: 25%; border: 1px solid #666; text-align:
 * center;">Total</th>
 * </tr>
 * <tr>
 * <td style="width: 25%; border: 1px solid #666;">value field1</td>
 * <td style="width: 25%; border: 1px solid #666">value field2</td>
 * <td style="width: 25%; border: 1px solid #666">value field3</td>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldName1}:$fieldName3)</td>
 * </tr>
 * <tr>
 * <td style="width: 25%; border: 1px solid #666;">value field1</td>
 * <td style="width: 25%; border: 1px solid #666">value field2</td>
 * <td style="width: 25%; border: 1px solid #666">value field3</td>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldName1}:$fieldName3)</td>
 * </tr>
 * <tr>
 * <td style="width: 25%; border: 1px solid #666;">value field1</td>
 * <td style="width: 25%; border: 1px solid #666">value field2</td>
 * <td style="width: 25%; border: 1px solid #666">value field3</td>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldName1}:${fieldName3})</td>
 * </tr>
 * </table>
 *
 * <table style="width:100%;">
 * <tr>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">Total
 * column 1</th>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">Total
 * column 2</th>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">Total
 * column 3</th>
 * <th style="width: 25%; border: 1px solid #666; text-align: center;">Sum
 * Total</th>
 * </tr>
 * <tr>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldName1RowStart}:${fieldName1RowEnd})</td>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldName2RowStart}:${fieldName2RowEnd})</td>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldName3RowStart}:${fieldName3RowEnd})</td>
 * <td style="width: 25%; border: 1px solid
 * #666">sum(${fieldNameTotalRowStart}:fieldNameTotalRowEnd)</td>
 * </tr>
 * </table>
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

	/**
	 * Alias.
	 *
	 * @return the excel formula alias[]
	 */
	public ExcelFormulaAlias[] alias() default {};

}
