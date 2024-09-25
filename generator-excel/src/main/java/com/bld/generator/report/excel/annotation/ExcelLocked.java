/*
 * @auth Francesco Baldi
 * @class com.bld.generatorator.report.excel.annotation.ExcelLocked.java
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelLocked.<br>
 * ExcelLocked is used to protect sheet with password, to insert password use the "value" parameter.<br>
 * The "locked" parameter is used to enable/disable the protection sheet.
 * 
 */
@Retention(RUNTIME)
@Target(ANNOTATION_TYPE)
public @interface ExcelLocked {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value() default "${bld.commons.sheet.password:}";
	
	/**
	 * Locked.
	 *
	 * @return true, if successful
	 */
	public boolean locked() default true;
	
}
