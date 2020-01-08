package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class DateSheet extends SheetData<DateRow> {

	public DateSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	@Override
	public Class<DateRow> getRowClass() {
		return DateRow.class;
	}

}
