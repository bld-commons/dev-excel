package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelHeaderLayout
@ExcelSheetLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
@ExcelQuery("select * from dip_stato_matricolare order by id_dipendente,dt_inizio_validita")
public class StatoMatricolareSheet extends QuerySheetData<StatoMatricolareRow> {

	public StatoMatricolareSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

}
