package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetDynamicData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class AutoreLibriSheetDynamic extends SheetDynamicData<AutoreLibriRowDynamic>{

	public AutoreLibriSheetDynamic(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		
	}

	@Override
	public Class<AutoreLibriRowDynamic> getRowClass() {
		return AutoreLibriRowDynamic.class;
	}

	
	
}
