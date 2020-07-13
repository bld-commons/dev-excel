package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelPivotColumnFunction {

	public double order();
	
	public DataConsolidateFunction[] dataConsolidateFunction();
}
