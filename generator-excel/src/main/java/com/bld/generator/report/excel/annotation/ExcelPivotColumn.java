/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelPivotColumn.java
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelPivotColumn.<br>
 * ExcelPivotColumn is used to add the columns in the pivot table.<br>
 * This annotation is added on fields of the ${link bld.generator.report.excel.RowSheet} classes.<br>
 * You can set the order of the columns through the property "order".
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelPivotColumn {

	/**
	 * Order.
	 *
	 * @return the double
	 */
	public double order();
	
}
