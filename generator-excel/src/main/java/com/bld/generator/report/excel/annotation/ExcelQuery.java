/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.annotation.ExcelQuery.java
 */
package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelQuery. <br>
 * ExcelQuery is used to get the list of rows directly from the database; this functionality works if the project is configured with spring-data-jpa and if one of the following properties is set:
 * <ul>
 * <li>spring.datasource.url</li>
 * <li>com.bld.commons.multiple.datasourceurce</li>
 * </ul>
 * It is used on {@link com.bld.generator.report.excel.QuerySheetData} classes.<br>
 * The "entityManager" and "namedParameterJdbcTemplate" properties must be set only if there is a configuration enabled for multiple data sources.
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelQuery {

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
