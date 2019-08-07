package bld.generator.report.junit.entity;

import java.util.Calendar;

import javax.validation.constraints.Size;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import it.bld.generator.report.excel.SheetSummary;
import it.bld.generator.report.excel.annotation.ExcelCellLayout;
import it.bld.generator.report.excel.annotation.ExcelColumn;
import it.bld.generator.report.excel.annotation.ExcelDate;
import it.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import it.bld.generator.report.excel.annotation.ExcelMarginSheet;
import it.bld.generator.report.excel.annotation.ExcelSheetLayout;
import it.bld.generator.report.excel.annotation.ExcelSummary;

@ExcelSheetLayout
@ExcelSummary(title = "Casa Editrice")
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class CasaEditrice extends SheetSummary {

	
	@ExcelColumn(nameColumn = "Nome", indexColumn = 1,comment = "Test comment")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String nome;
	
	@ExcelColumn(nameColumn = "Data di nascita", indexColumn = 2)
	@ExcelDate	
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Calendar dataDiNascita;
	
	@ExcelColumn(nameColumn = "Città", indexColumn = 2)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String citta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Calendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * @param nome
	 * @param dataDiNascita
	 * @param citta
	 */
	public CasaEditrice(String nome, Calendar dataDiNascita, String citta,String nameSheet) {
		super(nameSheet);
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.citta = citta;
	}

	/**
	 * @param nameSheet
	 */
	public CasaEditrice(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	
	
}
