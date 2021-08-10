package bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.xddf.usermodel.chart.BarDirection;

@Documented
@Retention(RUNTIME)
@Target(ElementType.TYPE)
public @interface ExcelBarChartData {

	
	public BarDirection value() default BarDirection.BAR;
	
}
