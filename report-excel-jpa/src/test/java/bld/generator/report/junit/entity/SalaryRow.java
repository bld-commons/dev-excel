package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelFont;
import com.bld.generator.report.excel.annotation.ExcelSubtotal;
import com.bld.generator.report.excel.annotation.ExcelSubtotals;

@ExcelSubtotals(labelTotalGroup = "Total",endLabel = "total", sumForGroup = { "name" })
public class SalaryRow implements RowSheet {

	@ExcelColumn(name = "Name", index = 0)
	@ExcelCellLayout
	private String name;
	@ExcelColumn(name = "Amount", index = 1)
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
