package bld.read.report.junit.entity;

import java.util.Calendar;

import bld.read.report.excel.annotation.ExcelReadColumn;
import bld.read.report.excel.domain.RowSheetRead;


public class ReadAutoreLibriRow implements RowSheetRead {

	
	@ExcelReadColumn(name = "Nome")
	private String nome;
	
	@ExcelReadColumn(name = "Cognome")
	private String cognome;
	
	@ExcelReadColumn(name = "Data di Nascita")
	private Calendar dataDiNascita;
	
	@ExcelReadColumn(name = "Titolo")
	private String titolo;
	
	@ExcelReadColumn(name = "Genere")
	private String genere;
	
	@ExcelReadColumn(name = "Matricola")
	private Integer matricola;
	
	@ExcelReadColumn(name = "Prezzo")
	private Double prezzo;
	
	@ExcelReadColumn(name = "Supplemento")
	private Double supplemento;

	
	
	/**
	 * 
	 */
	public ReadAutoreLibriRow() {
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
	public ReadAutoreLibriRow(String nome, String cognome, Calendar dataDiNascita, String titolo, String genere, Integer matricola,Double prezzo,Double supplemento) {
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

	@Override
	public String toString() {
		return "ReadAutoreLibriRow [nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita
				+ ", titolo=" + titolo + ", genere=" + genere + ", matricola=" + matricola + ", prezzo=" + prezzo
				+ ", supplemento=" + supplemento + "]";
	}
	
	
	
	
}
