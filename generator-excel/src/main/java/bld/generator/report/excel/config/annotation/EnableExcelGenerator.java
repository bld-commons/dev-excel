package bld.generator.report.excel.config.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import bld.generator.report.excel.config.EnableExcelGeneratorConfig;

@Configuration
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Import(EnableExcelGeneratorConfig.class)
public @interface EnableExcelGenerator {

	@AliasFor(attribute = "basePackages")
	public String[] value() default "bld.generator";

	@AliasFor(attribute = "value")
	public String[] basePackages() default "bld.generator";

}
