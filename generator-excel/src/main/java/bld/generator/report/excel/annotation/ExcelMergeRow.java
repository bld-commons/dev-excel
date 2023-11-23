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


// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelMergeRow.<br>
 * ExcelMergeRow works as follows:<br>
 * It is used to merge cells after checking the equality the reference field values.<br>
 * ReferenceField is empty only to the field identifier of the table and it must be in the first column.<br>
 * 
 * ExcelMergeRow works only if the property "notMerge" in {@link bld.generator.report.excel.annotation.ExcelSheetLayout} is disabled.<br>
 * 
 * ExcelMergeRow is an annotation of the fields within {@link bld.generator.report.excel.RowSheet} classes. 
 * 
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface ExcelMergeRow {


	/**
	 * Reference field.
	 *
	 * @return the string[]
	 */
	public String[] referenceField() default {};	
}
