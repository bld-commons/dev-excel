/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelQuery.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelQuery.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelQuery {

	/**
	 * Select.
	 *
	 * @return the string
	 */
	public String select();
	
	/**
	 * Native query.
	 *
	 * @return true, if successful
	 */
	public boolean nativeQuery() default true;
	
	
	
}
