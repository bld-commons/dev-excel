package bld.generator.report.excel;

public interface FunctionsTotal<T extends SheetFunctionTotal<? extends RowSheet>> {
	
	public T getSheetFunctionsTotal();
	
	
	public void setSheetFunctionsTotal(T sheetFunctionsTotal);
	

}
