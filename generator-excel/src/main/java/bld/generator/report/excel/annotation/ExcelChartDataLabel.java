package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

@Documented
@Retention(RUNTIME)
public @interface ExcelChartDataLabel {

	public boolean enable() default false;
	
	public boolean showVal() default true;
	
	public boolean showLegendKey() default true;
	
	public boolean showCatName() default true;
	
	public boolean showSerName() default true;
}
