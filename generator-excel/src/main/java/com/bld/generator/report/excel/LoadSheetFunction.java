package com.bld.generator.report.excel;

import java.util.List;

@FunctionalInterface
public interface LoadSheetFunction<R extends RowSheet,S extends LoadSheetData<R,S>> {
	
	public List<R> loadSheet(S loadSheetData);

}
