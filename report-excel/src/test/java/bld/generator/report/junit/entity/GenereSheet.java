package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;


@ExcelHeaderLayout
@ExcelSheetLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class GenereSheet extends SheetData<GenereRow>{
	
	

	public GenereSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	@Override
	public Class<GenereRow> getRowClass() {
		return GenereRow.class;
	}

	

	
}
