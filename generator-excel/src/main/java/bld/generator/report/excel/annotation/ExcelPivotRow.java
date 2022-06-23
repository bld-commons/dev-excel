/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelPivotRow.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelPivotRow.<br> 
 * ExcelPivotRow is used to add the rows in the pivot table.<br>
 * This annotation is added on fields of the ${link bld.generator.report.excel.RowSheet} classes.<br>
 * You can set the order of the rows through the property "order".
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelPivotRow {

	/**
	 * Order.
	 *
	 * @return the double
	 */
	public double order();

}
