package com.bld.generator.report.excel.annotation.impl;

import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelFunctionSubTotal;

public class ExcelFunctionSubTotalImpl extends ExcelAnnotationImpl<ExcelFunctionSubTotal>{

	private boolean value;
	
	private ExcelCellLayout excelCellLayout;
	

	public ExcelFunctionSubTotalImpl() {
		super();
		this.value=false;
	}


	public ExcelFunctionSubTotalImpl(boolean value, ExcelCellLayout excelCellLayout) {
		super();
		this.value = value;
		this.excelCellLayout = excelCellLayout;
	}


	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public ExcelCellLayout getExcelCellLayout() {
		return excelCellLayout;
	}

	public void setExcelCellLayout(ExcelCellLayout excelCellLayout) {
		this.excelCellLayout = excelCellLayout;
	}

	
}
