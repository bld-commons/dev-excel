/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelBooleanText.java
 * 
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelBooleanText.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelBooleanText {

	/**
	 * If true.
	 *
	 * @return the string
	 */
	public String ifTrue() default "Yes";
	
	/**
	 * If false.
	 *
	 * @return the string
	 */
	public String ifFalse() default "No";
}
