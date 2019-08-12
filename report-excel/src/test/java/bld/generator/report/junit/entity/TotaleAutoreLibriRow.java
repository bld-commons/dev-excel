package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;

@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
				excelColumn = @ExcelColumn(indexColumn = 2, nameColumn = "Totale per matricola"), 
				excelFunction = @ExcelFunction(function = "sumif(${matricolaRowStart}:${matricolaRowEnd},${totMatricola},${prezzoRowStart}:${prezzoRowEnd})", nameFunction = "totalePerMatricola")) })
public class TotaleAutoreLibriRow implements RowSheet {

	@ExcelColumn(nameColumn = "Matricola", indexColumn = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer totMatricola;

	public TotaleAutoreLibriRow(Integer totMatricola) {
		super();
		this.totMatricola = totMatricola;
	}

	public Integer getTotMatricola() {
		return totMatricola;
	}

	public void setTotMatricola(Integer totMatricola) {
		this.totMatricola = totMatricola;
	}
	
	
	

}
