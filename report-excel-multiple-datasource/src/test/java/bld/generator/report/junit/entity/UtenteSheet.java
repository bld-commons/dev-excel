package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.config.Db1DatabaseConfiguration;
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
@ExcelQuery(select="select new bld.generator.report.junit.entity.UtenteRow(u.idUtente,u.nome,u.cognome,u.dataNascita,u.image,u.path) from Utente u where u.cognome=:cognome order by u.cognome,u.nome", 
nativeQuery = false, namedParameterJdbcTemplate = Db1DatabaseConfiguration.JDBC_TEMPLATE,entityManager = Db1DatabaseConfiguration.DB1_ENTITY_MANAGER)
public class UtenteSheet extends QuerySheetData<UtenteRow> {
	

	public UtenteSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

	
}
