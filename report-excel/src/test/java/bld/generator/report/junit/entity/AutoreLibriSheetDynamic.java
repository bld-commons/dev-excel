package bld.generator.report.junit.entity;

import java.util.Map;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetDynamicData;
import bld.generator.report.excel.FunctionsTotal;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class AutoreLibriSheetDynamic extends SheetDynamicData<AutoreLibriRowDynamic> implements FunctionsTotal<TotaleAutoreLibriSheet>{

	private TotaleAutoreLibriSheet sheetFunctionsTotal;
	
	public AutoreLibriSheetDynamic(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		
	}

	public TotaleAutoreLibriSheet getSheetFunctionsTotal() {
		return sheetFunctionsTotal;
	}


	public void setSheetFunctionsTotal(TotaleAutoreLibriSheet sheetFunctionsTotal) {
		this.sheetFunctionsTotal = sheetFunctionsTotal;
	}



	@Override
	public void setMapFieldColumn(Map<String, Integer> mapFieldColumn) {
		super.setMapFieldColumn(mapFieldColumn);
	}

	@Override
	public Class<AutoreLibriRowDynamic> getRowClass() {
		return AutoreLibriRowDynamic.class;
	}

	
	
}
