package bld.generator.report.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.QuerySheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelQuery;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

import bld.generator.report.config.Db2DatabaseConfiguration;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
//@ExcelQuery(select = "SELECT id_utente, nome, cognome, data_nascita "
//		+ "FROM utente "
//		+ "WHERE cognome=:cognome "
//		+ "order by cognome,nome")
@ExcelQuery(value="select new bld.generator.report.junit.entity.UtenteEntityRow(u.id,u.firstName,u.lastName,u.email) from UserEntity u  order by u.lastName,u.firstName", 
nativeQuery = false,entityManager = Db2DatabaseConfiguration.DB2_ENTITY_MANAGER)
public class UtenteEntitySheet extends QuerySheetData<UtenteEntityRow> {
	

	public UtenteEntitySheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

	
}
