package bld.read.report.csv.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.ColumnDateFormat;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface CsvDateFormat {

	public ColumnDateFormat value() default ColumnDateFormat.DD_MM_YYYY;
	
	public String separator() default "/";
	
	
}
