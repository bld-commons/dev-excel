package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.annotation.ExcelChart;
import bld.generator.report.excel.annotation.ExcelChartCategory;
import bld.generator.report.excel.annotation.ExcelCharts;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;



@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(top=1.5,bottom = 1.5,right = 1.5,left = 1.5)
@ExcelQuery("select n.des_nazione,c.anno,c.num_nascite,c.num_decessi \n"
		+ "from nazione n inner join censimento c  on n.id_nazione = c.id_nazione\n"
		+ "order by n.des_nazione,c.anno")

@ExcelCharts(
		excelCharts = { @ExcelChart(id="nasicte",excelChartCategories = @ExcelChartCategory(fieldName = "${desNazione}", function = "${numNascite[start]}:${numNascite[end]}"), sizeColumn = 10, sizeRow = 20, xAxis = "${anno[start]}:${anno[end]}",group=true),
		@ExcelChart(id="decessi",excelChartCategories = @ExcelChartCategory(fieldName = "${desNazione}", function = "${numDecessiRowStart}:${numDecessiRowEnd}"), sizeColumn = 10, sizeRow = 20, xAxis = "${annoRowStart}:${annoRowEnd}",group=false)}
		)
public class CensimentoSheet extends QuerySheetData<CensimentoRow> {

	public CensimentoSheet(@Size(max = 31) String sheetName) {
		super(sheetName);
	}

}
