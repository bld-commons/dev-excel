
package com.bld.generator.report.csv.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface CsvQuery {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value();
	
	/**
	 * Native query.
	 *
	 * @return true, if successful
	 */
	public boolean nativeQuery() default true;
	
	
	/**
	 * Entity manager.
	 *
	 * @return the string
	 */
	public String entityManager() default "";
	
	
	
	
}
