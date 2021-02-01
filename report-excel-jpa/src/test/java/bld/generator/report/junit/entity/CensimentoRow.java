package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelMergeRow;

public class CensimentoRow implements RowSheet {

	@ExcelColumn(columnName = "Nazione", indexColumn = 0)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "")
	private String desNazione;
	
	@ExcelColumn(columnName = "Anno", indexColumn = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	private Integer anno;
	
	@ExcelColumn(columnName = "Nascite", indexColumn = 2)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer numNascite;
	
	@ExcelColumn(columnName = "Decessi", indexColumn = 3)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer numDecessi;

	public String getDesNazione() {
		return desNazione;
	}

	public void setDesNazione(String desNazione) {
		this.desNazione = desNazione;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getNumNascite() {
		return numNascite;
	}

	public void setNumNascite(Integer numNascite) {
		this.numNascite = numNascite;
	}

	public Integer getNumDecessi() {
		return numDecessi;
	}

	public void setNumDecessi(Integer numDecessi) {
		this.numDecessi = numDecessi;
	}
	
	
	
	
}
