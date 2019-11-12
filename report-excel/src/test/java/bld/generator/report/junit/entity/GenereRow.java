package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;

public class GenereRow implements RowSheet {

	@ExcelColumn(indexColumn = 0, nameColumn = "Genere")
	@ExcelCellLayout
	private String genere;
	
	@ExcelColumn(indexColumn = 0.5, nameColumn = "Test Remove")
	@ExcelCellLayout
	private String test;
	
	@ExcelColumn(indexColumn = 1, nameColumn = "Totale Libri")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private int countLibri;
	
	@ExcelColumn(indexColumn = 1.5, nameColumn = "Test Remove 1")
	@ExcelCellLayout
	private String testRemove;
	

	public GenereRow(String genere, String test, int countLibri, String testRemove) {
		super();
		this.genere = genere;
		this.test = test;
		this.countLibri = countLibri;
		this.testRemove = testRemove;
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
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public String getTestRemove() {
		return testRemove;
	}
	public void setTestRemove(String testRemove) {
		this.testRemove = testRemove;
	}
	
	
}
