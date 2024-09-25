package bld.generator.report.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.QuerySheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelQuery;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelHeaderLayout
@ExcelSheetLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
@ExcelQuery("select * from dip_stato_matricolare order by id_dipendente,dt_inizio_validita")
public class StatoMatricolareSheet extends QuerySheetData<StatoMatricolareRow> {

	public StatoMatricolareSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

}
