package bld.read.report.excel.json.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import bld.read.report.excel.domain.RowSheetRead;
import bld.read.report.excel.domain.SheetRead;

@Retention(RUNTIME)
public @interface JsonSheet {

	public String name();
	
	public Class<? extends SheetRead<? extends RowSheetRead>> sheetClass();
	
}
