package bld.generator.report.junit.entity;

import java.util.Calendar;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.annotation.ExcelRgbColor;

@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelCellsLayout=@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2), 
					excelColumn = @ExcelColumn(indexColumn = 9, nameColumn = "Prezzo Totale"), 
					excelFunction=@ExcelFunction(function = "sum(${prezzo},${supplemento})", nameFunction = "prezzoTotale"))},
excelFunctionMerges = {@ExcelFunctionMergeRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2), 
						excelColumn = @ExcelColumn(indexColumn = 7.1, nameColumn = "Prezzo Totale per Autore"), 
						excelMergeRow = @ExcelMergeRow(referenceField = "matricola"), excelFunction=@ExcelFunction(function = "sum(${prezzoFrom}:${prezzoTo})",nameFunction = "prezzoTotalePerAutore"))})

public class AutoreLibriRow implements RowSheet {

	
	@ExcelColumn(nameColumn = "${autore-libri-row.nome.name-column}",indexColumn = 2)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "matricola")
	private String nome;
	
	@ExcelColumn(nameColumn = "${autore-libri-row.cognome.name-column}",indexColumn = 3)
	@ExcelCellLayout(rgbForeground = @ExcelRgbColor(green=0,red=0))
	@ExcelMergeRow(referenceField = "matricola")
	private String cognome;
	
	@ExcelColumn(nameColumn = "Data di Nascita",indexColumn = 4)
	@ExcelDate
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelMergeRow(referenceField = "matricola")
	private Calendar dataDiNascita;
	
	@ExcelColumn(nameColumn = "Titolo",indexColumn = 6)
	@ExcelCellLayout
	private String titolo;
	
	@ExcelColumn(nameColumn = "Genere",indexColumn = 5)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "cognome")
	private String genere;
	
	@ExcelColumn(nameColumn = "${autore-libri-row.matricola.name-column}",indexColumn = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelMergeRow(referenceField = "")
	private Integer matricola;
	
	@ExcelHeaderLayout(rgbForeground = @ExcelRgbColor(red=(byte)255,green=0,blue=0))
	@ExcelColumn(nameColumn = "Prezzo",indexColumn = 7)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2)
	private Double prezzo;
	
	@ExcelColumn(nameColumn = "Supplemento",indexColumn = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2)
	private Double supplemento;

	
	
	/**
	 * 
	 */
	public AutoreLibriRow() {
		super();
	}

	/**
	 * @param nome
	 * @param cognome
	 * @param dataDiNascita
	 * @param titolo
	 * @param genere
	 * @param matricola
	 */
	public AutoreLibriRow(String nome, String cognome, Calendar dataDiNascita, String titolo, String genere, Integer matricola,Double prezzo,Double supplemento) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.titolo = titolo;
		this.genere = genere;
		this.matricola = matricola;
		this.prezzo=prezzo;
		this.supplemento=supplemento;
	}

	public Double getSupplemento() {
		return supplemento;
	}

	public void setSupplemento(Double supplemento) {
		this.supplemento = supplemento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Calendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public Integer getMatricola() {
		return matricola;
	}

	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
	
	
}
