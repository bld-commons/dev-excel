package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetFunctionTotal;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout(startColumn = 1)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class TotaleAutoreLibriSheet extends SheetFunctionTotal<TotaleAutoreLibriRow>{

	public TotaleAutoreLibriSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	@Override
	public Class<TotaleAutoreLibriRow> getRowClass() {
		return TotaleAutoreLibriRow.class;
	}
	

}
