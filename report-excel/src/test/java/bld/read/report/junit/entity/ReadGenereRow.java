package bld.read.report.junit.entity;

import bld.read.report.excel.annotation.ExcelReadColumn;
import bld.read.report.excel.domain.RowSheetRead;

public class ReadGenereRow implements RowSheetRead {

	@ExcelReadColumn(name = "Genere")
	private String genere;
	@ExcelReadColumn(name = "Totale Libri")
	private Integer countLibri;
	
	public ReadGenereRow(String genere, int countLibri) {
		super();
		this.genere = genere;
		this.countLibri = countLibri;
	}
	public ReadGenereRow() {
		super();
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public Integer getCountLibri() {
		return countLibri;
	}
	public void setCountLibri(Integer countLibri) {
		this.countLibri = countLibri;
	}
	@Override
	public String toString() {
		return "ReadGenereRow [genere=" + genere + ", countLibri=" + countLibri + "]";
	}
	
	
}
