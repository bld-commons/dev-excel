/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelFreezePane.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelFreezePane.<br>
 * ExcelFreezePane is used to freeze rows and columns.<br>
 * It is set on SheetData classes and it is composed from:
 * <ul>
 * <li>ColumnFreez - to lock the number of columns</li>
 * <li>RowFreez - to lock the number of rows</li>
 * </ul>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelFreezePane {

	/**
	 * Column freez.
	 *
	 * @return the int
	 */
	public int columnFreez() default 0;
	
	/**
	 * Row freez.
	 *
	 * @return the int
	 */
	public int rowFreez() default 0;
	
}
