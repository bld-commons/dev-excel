/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.config.annotation.IgnoreCheck.java
 */
package com.bld.generator.report.excel.config.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The Interface IgnoreCheck.<br>
 * IgnoreCheck is used to ignore some checks when starting the application and running some processes.
 */
@Documented
@Retention(RUNTIME)
@Target({ TYPE, FIELD })
public @interface IgnoreCheck {

}
