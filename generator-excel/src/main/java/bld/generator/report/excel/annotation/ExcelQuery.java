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
 * The Interface ExcelQuery. <br>
 * ExcelQuery is used to get the list of rows directly from the database; this functionality works if the project is configured with spring-data-jpa and if the property is set to "spring.datasource.url"<br>
 * It is used on {@link bld.generator.report.excel.QuerySheetData} classes.
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
	
	
	/**
	 * Entity manager.
	 *
	 * @return the string
	 */
	public String entityManager() default "";
	
	
	/**
	 * Named parameter jdbc template.
	 *
	 * @return the string
	 */
	public String namedParameterJdbcTemplate() default "namedParameterJdbcTemplate";	
	
	
}
