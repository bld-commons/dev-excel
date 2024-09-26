package bld.generator.report.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.QuerySheetData;
import com.bld.generator.report.excel.annotation.ExcelChart;
import com.bld.generator.report.excel.annotation.ExcelChartCategory;
import com.bld.generator.report.excel.annotation.ExcelCharts;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelQuery;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;



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
