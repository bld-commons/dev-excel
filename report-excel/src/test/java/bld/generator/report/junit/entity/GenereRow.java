package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;

public class GenereRow implements RowSheet {

	@ExcelColumn(indexColumn = 0, nameColumn = "Genere")
	@ExcelCellLayout
	private String genere;
	@ExcelColumn(indexColumn = 0, nameColumn = "Totale Libri")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private int countLibri;
	
	
	
	public GenereRow(String genere, int countLibri) {
		super();
		this.genere = genere;
		this.countLibri = countLibri;
	}
	public GenereRow() {
		super();
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public int getCountLibri() {
		return countLibri;
	}
	public void setCountLibri(int countLibri) {
		this.countLibri = countLibri;
	}
	
	
}
