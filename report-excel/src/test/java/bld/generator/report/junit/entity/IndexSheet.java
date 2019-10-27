package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class IndexSheet extends SheetData<IndexRow> {

	public IndexSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<IndexRow> getRowClass() {
		return IndexRow.class;
	}

	
	
}
