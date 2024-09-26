package bld.report.read.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.read.report.excel.annotation.ExcelReadSheet;
import com.bld.read.report.excel.domain.SheetRead;

@ExcelReadSheet(startRow = 1)
public class OperatoriSanitariSheet extends SheetRead<OperatoriSanitariRow> {

	public OperatoriSanitariSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

}
