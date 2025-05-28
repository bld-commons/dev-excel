package com.bld.generator.report.excel.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface ExcelConditionCellLayout {

	public String[] columns();
	
	public String condition();
	
	/**
	 * Alias.
	 *
	 * @return the excel formula alias[]
	 */
	public ExcelFormulaAlias[] alias() default {};
	
	public ExcelCellLayout excelCellLayout();
	
}
