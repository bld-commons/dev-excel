package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.config.Db2DatabaseConfiguration;
import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
//@ExcelQuery(select = "SELECT id_utente, nome, cognome, data_nascita "
//		+ "FROM utente "
//		+ "WHERE cognome=:cognome "
//		+ "order by cognome,nome")
@ExcelQuery(select="select new bld.generator.report.junit.entity.UtenteEntityRow(u.id,u.firstName,u.lastName,u.email) from UserEntity u  order by u.lastName,u.firstName", 
nativeQuery = false, namedParameterJdbcTemplate = Db2DatabaseConfiguration.JDBC_TEMPLATE,entityManager = Db2DatabaseConfiguration.DB2_ENTITY_MANAGER)
public class UtenteEntitySheet extends QuerySheetData<UtenteEntityRow> {
	

	public UtenteEntitySheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

	
}
