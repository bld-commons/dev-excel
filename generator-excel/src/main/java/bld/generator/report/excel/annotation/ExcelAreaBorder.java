package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Documented
@Retention(RUNTIME)
public @interface ExcelAreaBorder {

	public String areaRange();
	
	public ExcelBorder border();
	
}
