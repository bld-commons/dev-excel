package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.BoxStyle;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelDataValidation {

	public String value();
	
	/**
	 * Alias.
	 *
	 * @return the excel formula alias[]
	 */
	public ExcelFormulaAlias[] alias() default {};
	
	
	/**
	 * Error box.
	 *
	 * @return the excel box message
	 */
	public ExcelBoxMessage errorBox()default @ExcelBoxMessage(boxStyle = BoxStyle.STOP, message = "The value is not valid", title = "Error");
}
