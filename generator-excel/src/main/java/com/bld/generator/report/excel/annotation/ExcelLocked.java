/*
 * @auth Francesco Baldi
 * @class com.bld.generatorator.report.excel.annotation.ExcelLocked.java
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface ExcelLocked.<br>
 * ExcelLocked is used to protect a sheet with a password.<br>
 * Place this annotation directly on the sheet class to enable protection.<br>
 * The "value" parameter holds the password (supports Spring property placeholders).<br>
 * If the value resolves to blank the sheet is locked without a password.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelLocked {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value() default "${bld.commons.sheet.password:}";

}
