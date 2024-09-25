package bld.generator.report.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
public class BigDataUtenteSheet extends SheetData<BigDataUtenteRow> {
	

	public BigDataUtenteSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

	
}
