package bld.read.report.csv.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface CsvSettings {

	public char delimiter() default ',';

	public char quoteChar() default '"';

	public boolean ignoreEmptyLines() default true;

	public String recordSeparator() default "\r\n";

	public boolean skipHeaderRecord() default true;

	public boolean trim() default true;

}
