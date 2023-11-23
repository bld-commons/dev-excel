package bld.report.generator.junit.entity;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelSubtotal;
import bld.generator.report.excel.annotation.ExcelSubtotals;

@ExcelSubtotals(labelTotalGroup = "Total",endLabel = "total",sumForGroup = {"city","state"})
public class SalaryRow implements RowSheet {

	
	@ExcelColumn(name = "State", index = 0)
	@ExcelCellLayout
	private String state;
	
	@ExcelColumn(name = "City", index = 0.1)
	@ExcelCellLayout
	private String city;
	
	@ExcelColumn(name = "District", index = 0.2)
	@ExcelCellLayout
	private String district;
	
	

	@ExcelColumn(name = "Amount", index = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM,excelCellLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,font=@ExcelFont(bold = true)))
	private Double amount;
	
	public SalaryRow() {
		super();
	}

	public SalaryRow(String state, String city, String district, Double amount) {
		super();
		this.state = state;
		this.city = city;
		this.district = district;
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	
	
}
