/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelPivotFilter.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelPivotFilter.
 * ExcelPivotFilter is used to add a filter within pivot table.<br>
 * This annotation is added on fields of the ${link bld.generator.report.excel.RowSheet} classes.<br>
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelPivotFilter {

}
