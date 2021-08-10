package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelChartCategory;

public class ExcelChartCategoryImpl extends ExcelAnnotationImpl<ExcelChartCategory> {

	private String fieldName;
	
	private String function;
	
	

	
	public ExcelChartCategoryImpl(String fieldName, String function) {
		super();
		this.fieldName = fieldName;
		this.function = function;
	
	}

	public ExcelChartCategoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	
	
	
	
}
