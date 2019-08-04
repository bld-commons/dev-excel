package com.bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import it.bld.generator.report.excel.SheetData;
import it.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import it.bld.generator.report.excel.annotation.ExcelMarginSheet;
import it.bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout(cmWidthCell=5)
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class AutoreLibriSheet extends SheetData<AutoreLibriRow>{

	public AutoreLibriSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		
	}

	@Override
	public Class<AutoreLibriRow> getRowClass() {
		return AutoreLibriRow.class;
	}

	
	
}
