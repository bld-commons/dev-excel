/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelColumn.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelColumn.
 * <br>
 * ExcelColumn is used to define the header column
 * It is composed from:
 * <ul>
 * <li>ColumnName - to set column name on header</li>
 * <li>Comment - to add a comment on header</li>
 * <li>IndexColumn - to set the insertion order of the columns</li>
 * <li>Ignore - to show or hide the column</li>
 * </ul> 
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD})
public @interface ExcelColumn {

	/**
	 * Column name.
	 *
	 * @return the string
	 */
	public String columnName();
	
	/**
	 * Comment.
	 *
	 * @return the string
	 */
	public String comment() default "";
	
	/**
	 * Index column.
	 *
	 * @return the double
	 */
	public double indexColumn();
	
	/**
	 * Ignore.
	 *
	 * @return true, if successful
	 */
	public boolean ignore() default false;
	
	
	
}
