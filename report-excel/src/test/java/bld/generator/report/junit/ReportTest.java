package bld.generator.report.junit;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.ExcelHyperlink;
import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.MergeSheet;
import bld.generator.report.excel.annotation.impl.ExcelChartImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;
import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.excel.constant.RowStartEndType;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.impl.ReportExcel;
import bld.generator.report.junit.entity.AutoreLibriRow;
import bld.generator.report.junit.entity.AutoreLibriRowDynamic;
import bld.generator.report.junit.entity.AutoreLibriSheet;
import bld.generator.report.junit.entity.AutoreLibriSheetDynamic;
import bld.generator.report.junit.entity.CasaEditrice;
import bld.generator.report.junit.entity.DateRow;
import bld.generator.report.junit.entity.DateSheet;
import bld.generator.report.junit.entity.GenereRow;
import bld.generator.report.junit.entity.GenereSheet;
import bld.generator.report.junit.entity.IndexRow;
import bld.generator.report.junit.entity.IndexSheet;
import bld.generator.report.junit.entity.TotaleAutoreLibriRow;
import bld.generator.report.junit.entity.TotaleAutoreLibriSheet;
import bld.generator.report.utils.ExcelUtils;
import bld.read.report.excel.ReadExcel;
import bld.read.report.excel.domain.ExcelRead;
import bld.read.report.junit.entity.ReadAutoreLibriRow;
import bld.read.report.junit.entity.ReadAutoreLibriSheet;
import bld.read.report.junit.entity.ReadGenereRow;
import bld.read.report.junit.entity.ReadGenereSheet;
import bld.read.report.utils.ExcelType;

@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties
@ComponentScan(basePackages = {"bld.generator","bld.read"})
//@EnableAutoConfiguration(exclude=ExcelConfiguration.class)
public class ReportTest {

	private static final String PATH_FILE = "/mnt/report/";

	@Autowired
	private GenerateExcel generateExcel;
	
	@Autowired
	private ReadExcel readExcel;
	

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
		IndexSheet indexSheet=new IndexSheet("Indice");
		List<IndexRow> listIndice=new ArrayList<>();
		listIndice.add(new IndexRow(new ExcelHyperlink("ce", "Casa Editrice", HyperlinkType.DOCUMENT, 1, "A"), "Casa Editrice"));
		listIndice.add(new IndexRow(new ExcelHyperlink("al", "Libri d'autore", HyperlinkType.DOCUMENT, 1, "A"), "Libri d'autore"));
		indexSheet.setListRowSheet(listIndice);
		listBaseSheet.add(indexSheet);
		
		CasaEditrice casaEditrice = new CasaEditrice("Mondadori", new GregorianCalendar(1955, Calendar.MAY, 10), "Roma", "Casa Editrice");
		listBaseSheet.add(casaEditrice);

		DateSheet dateSheet=new DateSheet("Test Date");
		dateSheet.getListRowSheet().add(new DateRow(null, new Date()));
		dateSheet.getListRowSheet().add(new DateRow(null, new Date()));
		dateSheet.getListRowSheet().add(new DateRow(null, new Date()));
		
		listBaseSheet.add(dateSheet);
		
		
		List<AutoreLibriRow> list = new ArrayList<>();
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Profondo Rosso questo è un test per verificare che la cella va a capo automaticamente", "Thriller", 1, 25.5, 3.0));
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Complotto", "Thriller", 1, 30.0, 2.2));
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Sto Cazzo", "Comico", 1, 12.24, 1.2));
		list.add(new AutoreLibriRow("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Amore", "Sentimentale", 1, 35.455, 4.7));
		list.add(new AutoreLibriRow("Mario", "Verdi", new GregorianCalendar(1945, Calendar.JULY, 14), "Rosso", "Sentimentale", 2, 42.0, 6.94));
		list.add(new AutoreLibriRow("Mario", "Verdi", new GregorianCalendar(1945, Calendar.JULY, 14), "Arancio", "Sentimentale", 2, 23.24, 3.0));

		AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore","Test label");
		autoreLibriSheet.setListRowSheet(list);

		listBaseSheet.add(autoreLibriSheet);

		MergeSheet mergeSheet = new MergeSheet("Merge Sheet");
		mergeSheet.getListSheet().add(casaEditrice);
		mergeSheet.getListSheet().add(autoreLibriSheet);
		mergeSheet.getListSheet().add(autoreLibriSheet);
		mergeSheet.getListSheet().add(casaEditrice);

		listBaseSheet.add(mergeSheet);
		
		GenereSheet genereSheet=new GenereSheet("Genere");
		List<GenereRow>listGenere=new ArrayList<>();
		listGenere.add(new GenereRow("Giallo","Test remove cell 1:1",23,"Test Remove cell 1:2"));
		listGenere.add(new GenereRow("Romanzi","Test remove cell 2:1",17,"Test Remove cell 2:2"));
		genereSheet.setListRowSheet(listGenere);
		
		listBaseSheet.add(genereSheet);
		
	
		
		ReportExcel excel = new ReportExcel("Mondadori", listBaseSheet);

		byte[] byteReport = this.generateExcel.createFileXlsx(excel);

		ExcelUtils.writeToFile(PATH_FILE,excel.getTitolo(), ".xlsx", byteReport);

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

		
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new GregorianCalendar(1960, Calendar.JULY, 14), "Complotto", "Thriller", 1, 30.0, 2.2);

		autoreLibriRow.getMapValue().put("anno1", 34);
		autoreLibriRow.getMapValue().put("ignoreColumn", "Test");
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

		AutoreLibriSheetDynamic autoreLibriSheet = new AutoreLibriSheetDynamic("Libri d'autore","Test di etichetta su report");
		
		ExtraColumnAnnotation extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("Totale prezzo anni", null, 21,false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl("sum("+RowStartEndType.ROW_EMPTY.getParameter("anno1")+":"+RowStartEndType.ROW_EMPTY.getParameter("anno3")+")", "totalePrezzoAnni"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("totalePrezzoAnni", extraColumnAnnotation);
		
		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn( new ExcelColumnImpl("Totale prezzo anni per Autore", null, 22,false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl("sum("+RowStartEndType.ROW_START.getParameter("totalePrezzoAnni")+":"+RowStartEndType.ROW_END.getParameter("totalePrezzoAnni")+")", "totalePrezzoAnniAutore"));
		extraColumnAnnotation.setExcelMergeRow(new ExcelMergeRowImpl("matricola"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("totalePrezzoAnniAutore", extraColumnAnnotation);
		
		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2015", null, 20,false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno1", extraColumnAnnotation);
		
		
		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_STRING);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("Ignore Column", null, 20.05,true));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("ignoreColumn", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2016", null, 20.1,false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno2", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2017", null, 20.2,false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno3", extraColumnAnnotation);
		
		
		
		
		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("%2015", null, 30,false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl(RowStartEndType.ROW_EMPTY.getParameter("anno1")+"/"+RowStartEndType.ROW_EMPTY.getParameter("totalePrezzoAnni"), "percAnno1"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("percAnno1", extraColumnAnnotation);
		
		
		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("%2016", null, 30.1,false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl(RowStartEndType.ROW_EMPTY.getParameter("anno2")+"/"+RowStartEndType.ROW_EMPTY.getParameter("totalePrezzoAnni"), "percAnno2"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("percAnno2", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("%2017", null, 30.2,false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl(RowStartEndType.ROW_EMPTY.getParameter("anno3")+"/"+RowStartEndType.ROW_EMPTY.getParameter("totalePrezzoAnni"), "percAnno3"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("percAnno3", extraColumnAnnotation);

		autoreLibriSheet.setListRowSheet(list);

		ExcelChartImpl excelChartImpl=new ExcelChartImpl("titolo", "percAnno1", "percAnno3", ChartTypes.PIE,10,10,LegendPosition.TOP_RIGHT);
		autoreLibriSheet.addExcelChart(excelChartImpl);
		
		excelChartImpl=new ExcelChartImpl("titolo", "anno1", "anno3", ChartTypes.LINE,20,20,LegendPosition.TOP_RIGHT);
		autoreLibriSheet.addExcelChart(excelChartImpl);
		
		
		TotaleAutoreLibriSheet totaleAutoreLibriSheet=new TotaleAutoreLibriSheet();
		
		TotaleAutoreLibriRow totaleAutoreLibriRow=new TotaleAutoreLibriRow(1);
		totaleAutoreLibriSheet.getListRowSheet().add(totaleAutoreLibriRow);
		
		totaleAutoreLibriRow=new TotaleAutoreLibriRow(2);
		totaleAutoreLibriSheet.getListRowSheet().add(totaleAutoreLibriRow);
		
		autoreLibriSheet.setSheetFunctionsTotal(totaleAutoreLibriSheet);
		
		listBaseSheet.add(autoreLibriSheet);

		
		ReportExcel excel = new ReportExcel("Mondadori Dynamic", listBaseSheet);

		byte[] byteReport = this.generateExcel.createFileXlsx(excel);

		ExcelUtils.writeToFile(PATH_FILE,excel.getTitolo(), ".xlsx", byteReport);

	}


	
	@Test
	public void testRead() throws Exception{
		FileInputStream inputStream = new FileInputStream("/mnt/report/Mondadori.xlsx");
		byte[] report=IOUtils.toByteArray(inputStream);
		ExcelRead excelRead=new ExcelRead();
		excelRead.setReportExcel(report);
		excelRead.setExcelType(ExcelType.XLSX);
		excelRead.getListClassSheet().add(ReadAutoreLibriSheet.class);
		excelRead.getListClassSheet().add(ReadGenereSheet.class);
		excelRead=this.readExcel.convertExcelToEntity(excelRead);
		ReadAutoreLibriSheet sheet = excelRead.getSheet(ReadAutoreLibriSheet.class);
		for(ReadAutoreLibriRow row:sheet.getListRowSheet()) 
			System.out.println(row.toString());
		
		ReadGenereSheet readGenereSheet = excelRead.getSheet(ReadGenereSheet.class);
		for(ReadGenereRow row:readGenereSheet.getListRowSheet()) 
			System.out.println(row.toString());
		
		
	}
	
	

}
