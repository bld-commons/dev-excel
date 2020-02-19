package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.DynamicChart;
import bld.generator.report.excel.FunctionsTotal;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class AutoreLibriSheetDynamic extends DynamicChart<AutoreLibriRowDynamic> implements FunctionsTotal<TotaleAutoreLibriSheet>{

	private TotaleAutoreLibriSheet sheetFunctionsTotal;
	
	public AutoreLibriSheetDynamic(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		
	}

	
	public AutoreLibriSheetDynamic(@Size(max = 30) String nameSheet,String etichetta) {
		super(nameSheet);
		this.etichetta = etichetta;
	}



	public TotaleAutoreLibriSheet getSheetFunctionsTotal() {
		return sheetFunctionsTotal;
	}


	public void setSheetFunctionsTotal(TotaleAutoreLibriSheet sheetFunctionsTotal) {
		this.sheetFunctionsTotal = sheetFunctionsTotal;
	}

	@Override
	public Class<AutoreLibriRowDynamic> getRowClass() {
		return AutoreLibriRowDynamic.class;
	}

	@ExcelLabel(columnMerge = 4)
	private String etichetta;

	public String getEtichetta() {
		return etichetta;
	}

	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}
	
	
	
}
