package bld.generator.report.junit.entity;

import java.util.Map;

import javax.validation.constraints.Size;

import org.apache.commons.collections4.map.HashedMap;

import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery(select = "SELECT id_utente, nome, cognome, data_nascita "
		+ "FROM utente "
		+ "WHERE cognome=:cognome "
		+ "order by cognome,nome")
public class UtenteSheet extends SheetData<UtenteRow> implements QuerySheetData<UtenteRow> {
	
	private Map<String, Object> mapParameters;

	public UtenteSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
		mapParameters=new HashedMap<>();
	}

	@Override
	public Class<UtenteRow> getRowClass() {
		return UtenteRow.class;
	}

	@Override
	public Map<String, Object> getMapParameters() {
		return mapParameters;
	}

	@Override
	public void setMapParameters(Map<String, Object> mapParameters) {
		this.mapParameters = mapParameters;
	}

	
	
}
