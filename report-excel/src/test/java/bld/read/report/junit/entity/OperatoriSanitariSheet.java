package bld.read.report.junit.entity;

import javax.validation.constraints.Size;

import bld.read.report.excel.annotation.ExcelReadSheet;
import bld.read.report.excel.domain.SheetRead;

@ExcelReadSheet(startRow = 1)
public class OperatoriSanitariSheet extends SheetRead<OperatoriSanitariRow> {

	public OperatoriSanitariSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

}
