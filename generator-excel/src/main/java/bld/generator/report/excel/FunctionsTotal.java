package bld.generator.report.excel;

public interface FunctionsTotal<K extends RowSheet,T extends SheetFunctionTotal<K>> {
	
	public T getSheetFunctionsTotal();
	
	
	public void setSheetFunctionsTotal(T sheetFunctionsTotal);
	

}
