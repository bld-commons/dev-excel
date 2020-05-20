/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelMergeRow.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelMergeRow.<br>
 * ExcelMergeRow works like this:<br>
 * If referenceField is not empty then ExcelMergeRow checks the change another field, if the field changes then it must to change cell.<br>
 * ReferenceField is empty only to the field identifier of the table and it must be in the first column
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface ExcelMergeRow {

	/**
	 * Reference field.
	 *
	 * @return the string
	 */
	public String referenceField();	
}
