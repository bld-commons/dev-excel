/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.ExcelRowHeight.java
*/
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelRowHeight.<br>
 * ExcelRowHeight is used to set the row height in centimeters.<br>
 * It is set on {@link com.bld.generator.report.excel.RowSheet} classes or it added on field within of {@link com.bld.generator.report.excel.SheetSummary} classes.<br>
 * 
 */
@Retention(RUNTIME)
@Target({TYPE,FIELD})
@Documented
public @interface ExcelRowHeight {

	/**
	 * Height.
	 *
	 * @return the short
	 */
	public short height() default -1;
	
}
