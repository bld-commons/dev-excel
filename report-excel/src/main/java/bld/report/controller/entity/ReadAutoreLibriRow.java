/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.read.report.junit.entity.ReadAutoreLibriRow.java
*/
package bld.report.controller.entity;

import java.util.Calendar;

import bld.read.report.excel.annotation.ExcelReadColumn;
import bld.read.report.excel.domain.RowSheetRead;


/**
 * The Class ReadAutoreLibriRow.
 */
public class ReadAutoreLibriRow implements RowSheetRead {

	
	/** The nome. */
	@ExcelReadColumn(name = "Nome")
	private String nome;
	
	/** The cognome. */
	@ExcelReadColumn(name = "Cognome")
	private String cognome;
	
	/** The data di nascita. */
	@ExcelReadColumn(name = "Data di Nascita")
	private Calendar dataDiNascita;
	
	/** The titolo. */
	@ExcelReadColumn(name = "Titolo")
	private String titolo;
	
	/** The genere. */
	@ExcelReadColumn(name = "Genere")
	private String genere;
	
	/** The matricola. */
	@ExcelReadColumn(name = "Matricola")
	private Integer matricola;
	
	/** The prezzo. */
	@ExcelReadColumn(name = "Prezzo")
	private Double prezzo;
	
	/** The supplemento. */
	@ExcelReadColumn(name = "Anno")
	private Integer anno;

	
	
	/**
	 * Instantiates a new read autore libri row.
	 */
	public ReadAutoreLibriRow() {
		super();
	}

	/**
	 * Instantiates a new read autore libri row.
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
	public ReadAutoreLibriRow(String nome, String cognome, Calendar dataDiNascita, String titolo, String genere, Integer matricola,Double prezzo,Integer anno) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.titolo = titolo;
		this.genere = genere;
		this.matricola = matricola;
		this.prezzo=prezzo;
		this.anno=anno;
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

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}



	@Override
	public String toString() {
		return "ReadAutoreLibriRow [nome=" + nome + ", cognome=" + cognome + ", dataDiNascita=" + dataDiNascita + ", titolo=" + titolo + ", genere=" + genere + ", matricola=" + matricola + ", prezzo=" + prezzo + ", anno=" + anno + "]";
	}

	
	
	
}
