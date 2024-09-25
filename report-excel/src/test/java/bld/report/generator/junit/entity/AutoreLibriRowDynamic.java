/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.AutoreLibriRowDynamic.java
*/
package bld.report.generator.junit.entity;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.DynamicRowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelFont;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRows;
import com.bld.generator.report.excel.annotation.ExcelMergeRow;
import com.bld.generator.report.excel.annotation.ExcelSubtotal;
import com.bld.generator.report.excel.annotation.ExcelSubtotals;
import com.bld.generator.report.excel.dropdown.CalendarDropDown;

/**
 * The Class AutoreLibriRowDynamic.
 */
@ExcelFunctionRows(
		excelFunctions = {
				@ExcelFunctionRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
						excelColumn = @ExcelColumn(index = 9, name = "Prezzo Totale"), 
						excelFunction = @ExcelFunction(function = "sum(${prezzo},${supplemento})",anotherTable = false, nameFunction = "prezzoTotale")),
				@ExcelFunctionRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
				excelColumn = @ExcelColumn(index = 10, name = "Test Sum on Merged Cell"), 
				excelSubtotal = @ExcelSubtotal(enable = true, excelCellLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2,font=@ExcelFont(bold=true)), dataConsolidateFunction = DataConsolidateFunction.SUM),
				excelFunction = @ExcelFunction(function = "sum(${prezzoTotalePerAutore},${prezzoTotale})",anotherTable = false, nameFunction = "testSumMergedCell"))
				
				}, 
		excelFunctionMerges = {
				@ExcelFunctionMergeRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
						excelColumn = @ExcelColumn(index = 7.1, name = "Prezzo Totale per Autore"), 
						excelMergeRow = @ExcelMergeRow(referenceField = "matricola"), excelFunction = @ExcelFunction(function = "sum(${prezzoRowStart}:${prezzoRowEnd})", nameFunction = "prezzoTotalePerAutore",anotherTable = false)) 
				}
		)
@ExcelSubtotals(startLabel = "Totale", labelTotalGroup = "Totale", sumForGroup = { "matricola" })
public class AutoreLibriRowDynamic extends DynamicRowSheet {

	/** The nome. */
	@ExcelColumn(name = "Nome", index = 2)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "matricola")
	private String nome;

	/** The cognome. */
	@ExcelColumn(name = "Cognome", index = 3)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "matricola")
	private String cognome;

	/** The data di nascita. */
	@ExcelColumn(name = "Data di Nascita", index = 4)
	@ExcelDate
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelMergeRow(referenceField = "matricola")
	private CalendarDropDown dataDiNascita;

	/** The titolo. */
	@ExcelColumn(name = "Titolo", index = 6)
	@ExcelCellLayout
	private String titolo;

	/** The genere. */
	@ExcelColumn(name = "Genere", index = 5)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "cognome")
	private String genere;

	/** The matricola. */
	@ExcelColumn(name = "Matricola", index = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelMergeRow(referenceField = "")
	private Integer matricola;

	/** The prezzo. */
	@ExcelColumn(name = "Prezzo", index = 7)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM,excelCellLayout = @ExcelCellLayout(precision = 2,font = @ExcelFont(bold=true),horizontalAlignment = HorizontalAlignment.RIGHT))
	private Double prezzo;

	/** The supplemento. */
	@ExcelColumn(name = "Supplemento", index = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
	private Double supplemento;

	/**
	 * Instantiates a new autore libri row dynamic.
	 */
	public AutoreLibriRowDynamic() {
		super();
	}

	/**
	 * Instantiates a new autore libri row dynamic.
	 *
	 * @param nome          the nome
	 * @param cognome       the cognome
	 * @param dataDiNascita the data di nascita
	 * @param titolo        the titolo
	 * @param genere        the genere
	 * @param matricola     the matricola
	 * @param prezzo        the prezzo
	 * @param supplemento   the supplemento
	 */
	public AutoreLibriRowDynamic(String nome, String cognome, CalendarDropDown dataDiNascita, String titolo, String genere,
			Integer matricola, Double prezzo, Double supplemento) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.titolo = titolo;
		this.genere = genere;
		this.matricola = matricola;
		this.prezzo = prezzo;
		this.supplemento = supplemento;
	}

	/**
	 * Gets the supplemento.
	 *
	 * @return the supplemento
	 */
	public Double getSupplemento() {
		return supplemento;
	}

	/**
	 * Sets the supplemento.
	 *
	 * @param supplemento the new supplemento
	 */
	public void setSupplemento(Double supplemento) {
		this.supplemento = supplemento;
	}

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
	 * Gets the cognome.
	 *
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Sets the cognome.
	 *
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	

	/**
	 * Gets the titolo.
	 *
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Sets the titolo.
	 *
	 * @param titolo the new titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Gets the genere.
	 *
	 * @return the genere
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * Sets the genere.
	 *
	 * @param genere the new genere
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * Gets the matricola.
	 *
	 * @return the matricola
	 */
	public Integer getMatricola() {
		return matricola;
	}

	/**
	 * Sets the matricola.
	 *
	 * @param matricola the new matricola
	 */
	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	/**
	 * Gets the prezzo.
	 *
	 * @return the prezzo
	 */
	public Double getPrezzo() {
		return prezzo;
	}

	/**
	 * Sets the prezzo.
	 *
	 * @param prezzo the new prezzo
	 */
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public CalendarDropDown getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(CalendarDropDown dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}
	
	

}
