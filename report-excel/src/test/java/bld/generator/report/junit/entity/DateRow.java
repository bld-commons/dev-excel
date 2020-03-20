package bld.generator.report.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelRowHeight;

@ExcelRowHeight(height = 5)
public class DateRow implements RowSheet {

	@ExcelColumn(indexColumn = 0, nameColumn = "Data da")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dataDa;
	
	
	@ExcelColumn(indexColumn = 1, nameColumn = "Data a")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dataA;
	
	
	


	public DateRow(Date dataDa, Date dataA) {
		super();
		this.dataDa = dataDa;
		this.dataA = dataA;
	}


	public Date getDataDa() {
		return dataDa;
	}


	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}


	public Date getDataA() {
		return dataA;
	}


	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}
	
	
	
}
