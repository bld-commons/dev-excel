/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelPivotColumnFunction.java
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;


/**
 * The Interface ExcelPivotColumnFunction.<br>
 * ExcelPivotColumnFunction is used to add a column with specific function within pivot table.<br>
 * ExcelPivotColumnFunction is composed of the following properties:
 * <ul>
 * <li>Order - to order the write of the column</li>
 * <li>DataConsolidateFunction - to define the function</li>
 * </ul>
 * This annotation is added on fields of the ${link bld.generator.report.excel.RowSheet} classes.<br>
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelPivotColumnFunction {

	/**
	 * Order.
	 *
	 * @return the double
	 */
	public double order();

	/**
	 * Data consolidate function.
	 *
	 * @return the data consolidate function[]
	 */
	public DataConsolidateFunction[] dataConsolidateFunction();
}
