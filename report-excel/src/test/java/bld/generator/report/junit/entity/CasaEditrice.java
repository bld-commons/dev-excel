/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.CasaEditrice.java
*/
package bld.generator.report.junit.entity;

import java.util.Calendar;

import javax.validation.constraints.Size;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSummary;

/**
 * The Class CasaEditrice.
 */
@ExcelSheetLayout
@ExcelSummary(title = "Casa Editrice")
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class CasaEditrice extends SheetSummary {

	
	/** The nome. */
	@ExcelColumn(nameColumn = "Nome", indexColumn = 1,comment = "Test comment")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String nome;
	
	/** The data di nascita. */
	@ExcelColumn(nameColumn = "Data di nascita", indexColumn = 2)
	@ExcelDate	
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Calendar dataDiNascita;
	
	/** The citta. */
	@ExcelColumn(nameColumn = "Città", indexColumn = 2)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String citta;

	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	

	/**
	 * Gets the data di nascita.
	 *
	 * @return the data di nascita
	 */
	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * Sets the data di nascita.
	 *
	 * @param dataDiNascita the new data di nascita
	 */
	public void setDataDiNascita(Calendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * Gets the citta.
	 *
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Sets the citta.
	 *
	 * @param citta the new citta
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	/**
	 * Instantiates a new casa editrice.
	 *
	 * @param nome          the nome
	 * @param dataDiNascita the data di nascita
	 * @param citta         the citta
	 * @param nameSheet     the name sheet
	 */
	public CasaEditrice(String nome, Calendar dataDiNascita, String citta,String nameSheet) {
		super(nameSheet);
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.citta = citta;
	}

	/**
	 * Instantiates a new casa editrice.
	 *
	 * @param nameSheet the name sheet
	 */
	public CasaEditrice(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	
	
}
