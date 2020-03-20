package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelFreezePane;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
@ExcelFreezePane(rowFreez = 3, columnFreez = 1)
public class AutoreLibriSheet extends SheetData<AutoreLibriRow>{

	public AutoreLibriSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		
	}
	
	

	public AutoreLibriSheet(@Size(max = 30) String nameSheet, String label) {
		super(nameSheet);
		this.label = label;
	}



	@Override
	public Class<AutoreLibriRow> getRowClass() {
		return AutoreLibriRow.class;
	}

	@ExcelLabel
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
}
