package bld.read.report.excel.config.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import bld.read.report.excel.config.EnableExcelReadConfiguration;

@Configuration
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Import(EnableExcelReadConfiguration.class)
public @interface EnableExcelRead {

}
