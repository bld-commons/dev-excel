/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.config.annotation.EnableExcelGenerator.java
 * 
 */
package bld.generator.report.excel.config.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import bld.generator.report.excel.config.EnableExcelGeneratorConfiguration;

/**
 * The Interface EnableExcelGenerator.
 */
@Configuration
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Import(EnableExcelGeneratorConfiguration.class)
public @interface EnableExcelGenerator {


}
