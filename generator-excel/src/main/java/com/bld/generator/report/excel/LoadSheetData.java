package com.bld.generator.report.excel;

import jakarta.validation.constraints.Size;

public abstract class LoadSheetData<R extends RowSheet, S extends LoadSheetData<R, S>> extends SheetData<R> {

	protected LoadSheetFunction<R, S> loadSheetFunction;
	
	
	public LoadSheetData(@Size(max = 31) String sheetName,LoadSheetFunction<R, S> loadSheetFunction) {
		super(sheetName);
		this.loadSheetFunction=loadSheetFunction;
	}


	public LoadSheetFunction<R, S> getLoadSheetFunction() {
		return loadSheetFunction;
	}


	public abstract void initRows();


}
