package bld.read.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelReadSheet {

	public String nameSheet();
	
	public int startRow()default 0;
	
	public int startColumn() default 0;
	
}
