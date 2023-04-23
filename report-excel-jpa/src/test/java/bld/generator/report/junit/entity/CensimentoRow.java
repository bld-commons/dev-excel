package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelMergeRow;

public class CensimentoRow implements RowSheet {

	@ExcelColumn(name = "Nazione", index = 0)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "")
	private String desNazione;
	
	@ExcelColumn(name = "Anno", index = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	private Integer anno;
	
	@ExcelColumn(name = "Nascite", index = 2)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer numNascite;
	
	@ExcelColumn(name = "Decessi", index = 3)
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
