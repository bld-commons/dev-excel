/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriRow.java
*/
package bld.generator.report.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.annotation.ExcelPivotColumn;
import bld.generator.report.excel.annotation.ExcelPivotColumnFunction;
import bld.generator.report.excel.annotation.ExcelPivotFilter;
import bld.generator.report.excel.annotation.ExcelPivotRow;

/**
 * The Class AutoreLibriRow.
 */
public class AutoreLibriRow implements RowSheet {

	@ExcelColumn(columnName = "Matricola", indexColumn = 0)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelPivotFilter
	@ExcelMergeRow(referenceField = "")
	private Integer idAutore;
	@ExcelColumn(columnName = "Anno", indexColumn = 7)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelPivotColumn(order = 0)
	private Integer anno;	
	@ExcelColumn(columnName = "Prezzo", indexColumn = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelPivotColumnFunction(dataConsolidateFunction = {DataConsolidateFunction.SUM,DataConsolidateFunction.AVERAGE},order = 0)
	private Double prezzo;
	@ExcelColumn(columnName = "Titolo", indexColumn = 6)
	@ExcelCellLayout
	@ExcelPivotRow(order = 3)
	private String titolo;
	@ExcelColumn(columnName = "Nome", indexColumn = 1)
	@ExcelCellLayout
	@ExcelPivotRow(order = 2)
	@ExcelMergeRow(referenceField = "idAutore")
	private String nome;
	@ExcelColumn(columnName = "Cognome", indexColumn = 2)
	@ExcelCellLayout
	@ExcelPivotRow(order = 0)
	@ExcelMergeRow(referenceField = "idAutore")
	private String cognome;
	@ExcelColumn(columnName = "Data di Nascita", indexColumn = 3)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	@ExcelMergeRow(referenceField = "idAutore")
	private Date dataNascita;
	@ExcelColumn(columnName = "Sesso", indexColumn = 4)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelMergeRow(referenceField = "idAutore")
	private Character sesso;
	@ExcelColumn(columnName = "Genere", indexColumn = 5)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "idAutore")
	private String desGenere;
	
	public Integer getIdAutore() {
		return idAutore;
	}
	public void setIdAutore(Integer idAutore) {
		this.idAutore = idAutore;
	}
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	public Double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
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
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public Character getSesso() {
		return sesso;
	}
	public void setSesso(Character sesso) {
		this.sesso = sesso;
	}
	public String getDesGenere() {
		return desGenere;
	}
	public void setDesGenere(String desGenere) {
		this.desGenere = desGenere;
	}

	
	
	
	
}
