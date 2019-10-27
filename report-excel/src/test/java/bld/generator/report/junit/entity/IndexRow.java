package bld.generator.report.junit.entity;

import bld.generator.report.excel.ExcelHyperlink;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.constant.UnderlineType;

public class IndexRow implements RowSheet {

	@ExcelColumn(indexColumn = 0, nameColumn = "Indice")
	@ExcelCellLayout(font = @ExcelFont(underline=UnderlineType.SINGLE, italic = true), rgbFont=@ExcelRgbColor(red=0,green=0,blue=(byte)255))
	private ExcelHyperlink excelHyperlink;
	
	@ExcelColumn(indexColumn = 1, nameColumn = "Descrizione")
	@ExcelCellLayout
	private String descrizione;
	
	

	public IndexRow(ExcelHyperlink excelHyperlink, String descrizione) {
		super();
		this.excelHyperlink = excelHyperlink;
		this.descrizione = descrizione;
	}

	public ExcelHyperlink getExcelHyperlink() {
		return excelHyperlink;
	}

	public void setExcelHyperlink(ExcelHyperlink excelHyperlink) {
		this.excelHyperlink = excelHyperlink;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	
}
