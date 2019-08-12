package bld.generator.report.junit;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.MergeSheet;
import bld.generator.report.excel.annotation.impl.ExcelChartImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionMergeRowImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionRowImpl;
import bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;
import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.impl.ReportExcel;
import bld.generator.report.junit.entity.AutoreLibriRow;
import bld.generator.report.junit.entity.AutoreLibriRowDynamic;
import bld.generator.report.junit.entity.AutoreLibriSheet;
import bld.generator.report.junit.entity.AutoreLibriSheetDynamic;
import bld.generator.report.junit.entity.CasaEditrice;
import bld.generator.report.junit.entity.TotaleAutoreLibriRow;
import bld.generator.report.junit.entity.TotaleAutoreLibriSheet;

@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties
public class ReportTest {

	@Autowired
	private GenerateExcel generateExcel;
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
		CasaEditrice casaEditrice = new CasaEditrice("Mondadori", new GregorianCalendar(1955, Calendar.MAY, 10), "Roma", "Casa Editrice");
		listBaseSheet.add(casaEditrice);

		List<AutoreLibriRow> list = new ArrayList<>();
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Profondo Rosso", "Thriller", 1, 25.5, 3.0));
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Complotto", "Thriller", 1, 30.0, 2.2));
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Sto Cazzo", "Comico", 1, 12.24, 1.2));
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Amore", "Sentimentale", 1, 35.455, 4.7));
		list.add(new AutoreLibriRow("Mario", "Verdi", new GregorianCalendar(1945, Calendar.JULY, 14), "Rosso", "Sentimentale", 2, 42.0, 6.94));
		list.add(new AutoreLibriRow("Mario", "Verdi", new GregorianCalendar(1945, Calendar.JULY, 14), "Arancio", "Sentimentale", 2, 23.24, 3.0));

		AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore");
		autoreLibriSheet.setListRowSheet(list);

		listBaseSheet.add(autoreLibriSheet);

		MergeSheet mergeSheet = new MergeSheet("Merge Sheet");
		mergeSheet.getListSheet().add(casaEditrice);
		mergeSheet.getListSheet().add(autoreLibriSheet);
		mergeSheet.getListSheet().add(autoreLibriSheet);
		mergeSheet.getListSheet().add(casaEditrice);

		listBaseSheet.add(mergeSheet);
		ReportExcel excel = new ReportExcel("Mondadori", listBaseSheet);

		byte[] byteReport = this.generateExcel.createFileXlsx(excel);

		writeToFile(excel.getTitolo(), ".xlsx", byteReport);

	}

	@Test
	public void testDynamic() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
		CasaEditrice casaEditrice = new CasaEditrice("Mondadori", new GregorianCalendar(1955, Calendar.MAY, 10), "Roma", "Casa Editrice");
		listBaseSheet.add(casaEditrice);

		List<AutoreLibriRowDynamic> list = new ArrayList<>();
		AutoreLibriRowDynamic autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Profondo Rosso", "Thriller", 1, 25.5, 3.0);
		autoreLibriRow.getMapValue().put("anno1", 23.4);
		autoreLibriRow.getMapValue().put("anno2", 30.12);
		autoreLibriRow.getMapValue().put("anno3", 20.4);
		ExcelFunctionRowImpl excelFunctionImpl = new ExcelFunctionRowImpl(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE.getExcelCellLayout(), new ExcelColumnImpl("Totale prezzo anni", null, 21).getExcelColumn(), new ExcelFunctionImpl("sum(${anno1}:${anno3})", "totalePrezzoAnni").getExcelFunction());
		ExcelMergeRowImpl excelMergeRowImpl = new ExcelMergeRowImpl("matricola");
		ExcelFunctionMergeRowImpl excelFunctionMergeImpl = new ExcelFunctionMergeRowImpl(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE.getExcelCellLayout(), new ExcelColumnImpl("Totale prezzo anni per Autore", null, 22).getExcelColumn(),
				 excelMergeRowImpl.getExcelMergeRow(),new ExcelFunctionImpl("sum(${totalePrezzoAnniFrom}:${totalePrezzoAnniTo})", "totalePrezzoAnniAutore").getExcelFunction());
		
		autoreLibriRow.addDynamicExcelFunction(excelFunctionImpl, excelFunctionMergeImpl);
		
		
		
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Complotto", "Thriller", 1, 30.0, 2.2);

		autoreLibriRow.getMapValue().put("anno1", 34);
		autoreLibriRow.getMapValue().put("anno2", 37.12);
		autoreLibriRow.getMapValue().put("anno3", 44);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Sto Cazzo", "Comico", 1, 12.24, 1.2);
		autoreLibriRow.getMapValue().put("anno1", 10.4);
		autoreLibriRow.getMapValue().put("anno2", 15.12);
		autoreLibriRow.getMapValue().put("anno3", 12.4);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Amore", "Sentimentale", 1, 35.455, 4.7);
		autoreLibriRow.getMapValue().put("anno1", 30.4);
		autoreLibriRow.getMapValue().put("anno2", 35.12);
		autoreLibriRow.getMapValue().put("anno3", 32.4);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Verdi", new GregorianCalendar(1945, Calendar.JULY, 14), "Rosso", "Sentimentale", 2, 42.0, 6.94);

		autoreLibriRow.getMapValue().put("anno1", 40.4);
		autoreLibriRow.getMapValue().put("anno2", 45.12);
		autoreLibriRow.getMapValue().put("anno3", 42.4);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Verdi", new GregorianCalendar(1945, Calendar.JULY, 14), "Arancio", "Sentimentale", 2, 23.24, 3.0);
		autoreLibriRow.getMapValue().put("anno1", 20.4);
		autoreLibriRow.getMapValue().put("anno2", 25.12);
		autoreLibriRow.getMapValue().put("anno3", 22.4);
		list.add(autoreLibriRow);

		AutoreLibriSheetDynamic autoreLibriSheet = new AutoreLibriSheetDynamic("Libri d'autore");
		ExtraColumnAnnotation extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE.getExcelCellLayout());
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2015", null, 20).getExcelColumn());
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno1", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE.getExcelCellLayout());
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2016", null, 20.1).getExcelColumn());
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno2", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE.getExcelCellLayout());
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2017", null, 20.2).getExcelColumn());
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno3", extraColumnAnnotation);

		autoreLibriSheet.setListRowSheet(list);

		ExcelChartImpl excelChartImpl=new ExcelChartImpl("titolo", "anno1", "anno3", ChartTypes.LINE,10,10);
		autoreLibriSheet.addExcelChart(excelChartImpl);
		
		TotaleAutoreLibriSheet totaleAutoreLibriSheet=new TotaleAutoreLibriSheet(null);
		
		TotaleAutoreLibriRow totaleAutoreLibriRow=new TotaleAutoreLibriRow(1);
		totaleAutoreLibriSheet.getListRowSheet().add(totaleAutoreLibriRow);
		
		totaleAutoreLibriRow=new TotaleAutoreLibriRow(2);
		totaleAutoreLibriSheet.getListRowSheet().add(totaleAutoreLibriRow);
		
		autoreLibriSheet.setSheetFunctionsTotal(totaleAutoreLibriSheet);
		
		listBaseSheet.add(autoreLibriSheet);

		
		ReportExcel excel = new ReportExcel("Mondadori Dynamic", listBaseSheet);

		byte[] byteReport = this.generateExcel.createFileXlsx(excel);

		writeToFile(excel.getTitolo(), ".xlsx", byteReport);

	}

//	private static final Object[][] plotData = { { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" }, { 1, 2, 3, 10, 5, 3, 7, 10, 9, 10 },{ 12, 20, 33, 14, 15, 23, 4, 1, 29, 1 } };

//	@Test
//	public void testOneSeriePlot() throws IOException {
//		
//		
//		
//		
//		
//		
//		XSSFWorkbook wb = new XSSFWorkbook();
//		XSSFSheet sheet = (XSSFSheet) new SheetBuilder(wb, plotData).build();
//		XSSFDrawing drawing = sheet.createDrawingPatriarch();
//		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 5, 5, 10, 30);
//		XSSFChart chart = drawing.createChart(anchor);
//
//		XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
//		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
//
//		XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(sheet, CellRangeAddress.valueOf("A1:J1"));
//		XDDFNumericalDataSource<Double> ys = XDDFDataSourcesFactory.fromNumericCellRange(sheet, CellRangeAddress.valueOf("A2:J2"));
//		XDDFNumericalDataSource<Double> ys1 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, CellRangeAddress.valueOf("A3:J3"));
//		
//		XDDFChartData lineChartData = chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
//		XDDFChartData.Series series = lineChartData.addSeries(xs, ys);
//		series.setTitle("Test", null);
//		series = lineChartData.addSeries(xs, ys1);
//		series.setTitle("Test2", null);
//		chart.getOrCreateLegend();
//		chart.plot(lineChartData);
//		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//		wb.write(byteArrayOutputStream);
//		byte[] byteExcel = byteArrayOutputStream.toByteArray();
//		writeToFile("Test", ".xlsx", byteExcel);
//		wb.close();
//	}

	/**
	 * Write to file.
	 *
	 * @param filename the filename
	 * @param dati     the dati
	 */
	private void writeToFile(String fileName, String typeFile, byte[] dati) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("/mnt/report/" + fileName + typeFile);
			fos.write(dati);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
