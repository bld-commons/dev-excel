/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriRowDynamic.java
*/
package bld.generator.report.junit.entity;

import java.util.Calendar;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.DynamicRowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelMergeRow;

/**
 * The Class AutoreLibriRowDynamic.
 */
@ExcelFunctionRows(
		excelFunctions = {
				@ExcelFunctionRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
						excelColumn = @ExcelColumn(indexColumn = 9, nameColumn = "Prezzo Totale"), 
						excelFunction = @ExcelFunction(function = "sum(${prezzo},${supplemento})", nameFunction = "prezzoTotale")) 
				}, 
		excelFunctionMerges = {
				@ExcelFunctionMergeRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
						excelColumn = @ExcelColumn(indexColumn = 7.1, nameColumn = "Prezzo Totale per Autore"), 
						excelMergeRow = @ExcelMergeRow(referenceField = "matricola"), excelFunction = @ExcelFunction(function = "sum(${prezzoRowStart}:${prezzoRowEnd})", nameFunction = "prezzoTotalePerAutore")) 
				}
		)
public class AutoreLibriRowDynamic extends DynamicRowSheet {

	/** The nome. */
	@ExcelColumn(nameColumn = "Nome", indexColumn = 2)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "matricola")
	private String nome;

	/** The cognome. */
	@ExcelColumn(nameColumn = "Cognome", indexColumn = 3)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "matricola")
	private String cognome;

	/** The data di nascita. */
	@ExcelColumn(nameColumn = "Data di Nascita", indexColumn = 4)
	@ExcelDate
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelMergeRow(referenceField = "matricola")
	private Calendar dataDiNascita;

	/** The titolo. */
	@ExcelColumn(nameColumn = "Titolo", indexColumn = 6)
	@ExcelCellLayout
	private String titolo;

	/** The genere. */
	@ExcelColumn(nameColumn = "Genere", indexColumn = 5)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "cognome")
	private String genere;

	/** The matricola. */
	@ExcelColumn(nameColumn = "Matricola", indexColumn = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelMergeRow(referenceField = "")
	private Integer matricola;

	/** The prezzo. */
	@ExcelColumn(nameColumn = "Prezzo", indexColumn = 7)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
	private Double prezzo;

	/** The supplemento. */
	@ExcelColumn(nameColumn = "Supplemento", indexColumn = 8)
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
	public AutoreLibriRowDynamic(String nome, String cognome, Calendar dataDiNascita, String titolo, String genere,
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

}
