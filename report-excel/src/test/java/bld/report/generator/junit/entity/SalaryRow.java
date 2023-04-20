package bld.report.generator.junit.entity;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelSubtotal;
import bld.generator.report.excel.annotation.ExcelSubtotals;

@ExcelSubtotals(labelTotalGroup = "Totale dipendenti",endLabel = "total")
public class SalaryRow implements RowSheet {

	@ExcelColumn(columnName = "Name", indexColumn = 0)
	@ExcelCellLayout
	private String name;
	@ExcelColumn(columnName = "Amount", indexColumn = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM,excelCellLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,font=@ExcelFont(bold = true)))
	private Double amount;
	
	public SalaryRow() {
		super();
	}
	public SalaryRow(String name, Double amount) {
		super();
		this.name = name;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
