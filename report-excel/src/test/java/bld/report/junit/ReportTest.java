/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.ReportTest.java
*/
package bld.report.junit;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.RadarStyle;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFRadarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bld.common.spreadsheet.constant.RowStartEndType;
import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.ExcelAttachment;
import bld.generator.report.excel.annotation.ExcelChartCategory;
import bld.generator.report.excel.annotation.impl.ExcelBorderImpl;
import bld.generator.report.excel.annotation.impl.ExcelCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelChartCategoryImpl;
import bld.generator.report.excel.annotation.impl.ExcelChartDataLabelImpl;
import bld.generator.report.excel.annotation.impl.ExcelChartImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnImpl;
import bld.generator.report.excel.annotation.impl.ExcelColumnWidthImpl;
import bld.generator.report.excel.annotation.impl.ExcelDataValidationImpl;
import bld.generator.report.excel.annotation.impl.ExcelFontImpl;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.annotation.impl.ExcelHeaderCellLayoutImpl;
import bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl;
import bld.generator.report.excel.annotation.impl.ExcelRgbColorImpl;
import bld.generator.report.excel.annotation.impl.ExcelSubtotalImpl;
import bld.generator.report.excel.config.annotation.EnableExcelGenerator;
import bld.generator.report.excel.constant.AttachmentType;
import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.excel.constant.FontType;
import bld.generator.report.excel.constant.UnderlineType;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.data.ReportExcel;
import bld.generator.report.excel.dropdown.CalendarDropDown;
import bld.generator.report.excel.impl.GenerateExcelImpl;
import bld.report.generator.junit.entity.AutoreLibriRow;
import bld.report.generator.junit.entity.AutoreLibriRowDynamic;
import bld.report.generator.junit.entity.AutoreLibriSheet;
import bld.report.generator.junit.entity.AutoreLibriSheetDynamic;
import bld.report.generator.junit.entity.CasaEditrice;
import bld.report.generator.junit.entity.DateRow;
import bld.report.generator.junit.entity.DateSheet;
import bld.report.generator.junit.entity.GenereRow;
import bld.report.generator.junit.entity.GenereSheet;
import bld.report.generator.junit.entity.SituazioneUfficiRow;
import bld.report.generator.junit.entity.SituazioneUfficiSheet;
import bld.report.generator.junit.entity.TotaleAutoreLibriRow;
import bld.report.generator.junit.entity.TotaleAutoreLibriSheet;



/**
 * The Class ReportTest.
 */
@SpringBootTest
@EnableExcelGenerator
@EnableTransactionManagement
public class ReportTest {

	/** The Constant PATH_FILE. */
	private static final String PATH_FILE = "/mnt/report/";

	/** The generate excel. */
	@Autowired
	private GenerateExcelImpl generateExcel;
	
	private final static Logger logger=LoggerFactory.getLogger(ReportTest.class);

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * Test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
//		IndexSheet indexSheet = new IndexSheet("Indice");
//		List<IndexRow> listIndice = new ArrayList<>();
//		listIndice.add(new IndexRow(new ExcelHyperlink("ce", "Casa Editrice", HyperlinkType.DOCUMENT, 1, "A"), "Casa Editrice"));
//		listIndice.add(new IndexRow(new ExcelHyperlink("al", "Libri d'autore", HyperlinkType.DOCUMENT, 1, "A"), "Libri d'autore"));
//		indexSheet.setListRowSheet(listIndice);
//		listBaseSheet.add(indexSheet);
//		ExcelAttachment<String> excelAttachment = ExcelAttachment.newInstance("/mnt/report/contratto-rtg-adsl-voce.pdf");
//		excelAttachment.setAttachmentType(AttachmentType.PDF);
//		excelAttachment.setFileName("test");
//		CasaEditrice casaEditrice = new CasaEditrice("Casa Editrice", "Mondadori", new GregorianCalendar(1955, Calendar.MAY, 10), "Roma", "/home/francesco/Documents/git-project/dev-excel/linux.jpg", excelAttachment);
//		casaEditrice.setTitle("Titolo di test");
//		listBaseSheet.add(casaEditrice);

		DateSheet dateSheet = new DateSheet("Test Date");
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

		AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore", "Test label");
		autoreLibriSheet.setListRowSheet(list);

		listBaseSheet.add(autoreLibriSheet);

//		MergeSheet mergeSheet = new MergeSheet("Merge Sheet");
//
//		mergeSheet.getListSheet().add(autoreLibriSheet);
//		mergeSheet.getListSheet().add(autoreLibriSheet);
//		mergeSheet.getListSheet().add(dateSheet);
//
//		listBaseSheet.add(mergeSheet);
//
		GenereSheet genereSheet = new GenereSheet("Genere");
		List<GenereRow> listGenere = new ArrayList<>();
		listGenere.add(new GenereRow("Giallo", "Test remove cell 1:1", 23, "Test Remove cell 1:2"));
		listGenere.add(new GenereRow("Romanzi", "Test remove cell 2:1", 17, "Test Remove cell 2:2"));
		genereSheet.setListRowSheet(listGenere);

		listBaseSheet.add(genereSheet);

		try {
			ReportExcel excel = new ReportExcel("Mondadori", listBaseSheet);
			excel.setEnableSheetMapping(true);
			byte[] byteReport = this.generateExcel.createFileXlsx(excel);

			SpreadsheetUtils.writeToFile(PATH_FILE, excel.getTitle(), ".xlsx", byteReport);

//			byte[] byteReportPdf = this.generateExcel.exportToPdf(byteReport);
//			
//			ExcelUtils.writeToFile(PATH_FILE, excel.getTitle(), ".pdf", byteReportPdf);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	/**
	 * Test dynamic.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testDynamic() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
		ExcelAttachment<String> excelAttachment = ExcelAttachment.newInstance("/mnt/report/test.docx");
		excelAttachment.setAttachmentType(AttachmentType.DOCX);
		excelAttachment.setFileName("test");
		CasaEditrice casaEditrice = new CasaEditrice("Casa Editrice", "Mondadori", new GregorianCalendar(1955, Calendar.MAY, 10), "Roma", System.getProperty("user.home")+"/Documents/git-project/dev-excel/linux.jpg", excelAttachment);
		listBaseSheet.add(casaEditrice);

		List<Calendar> listDate = new ArrayList<>();
		listDate.add(new GregorianCalendar(1960, Calendar.JULY, 14));
		listDate.add(new GregorianCalendar(1945, Calendar.JULY, 14));

		List<AutoreLibriRowDynamic> list = new ArrayList<>();
		AutoreLibriRowDynamic autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new CalendarDropDown(new GregorianCalendar(1960, Calendar.JULY, 14), listDate), "Profondo Rosso", "Thriller", 1, 25.5, 3.0);
		autoreLibriRow.addValue("data", new Date());
		autoreLibriRow.getMapValue().put("anno1", 23.4);
		autoreLibriRow.getMapValue().put("anno2", 30.12);
		autoreLibriRow.getMapValue().put("anno3", 20.4);

		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new CalendarDropDown(new GregorianCalendar(1960, Calendar.JULY, 14), listDate), "Complotto", "Thriller", 1, 30.0, 2.2);
		autoreLibriRow.addValue("data", new Date());
		autoreLibriRow.getMapValue().put("anno1", 34);
		autoreLibriRow.getMapValue().put("ignoreColumn", "Test");
		autoreLibriRow.getMapValue().put("anno2", 37.12);
		autoreLibriRow.getMapValue().put("anno3", 44);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new CalendarDropDown(new GregorianCalendar(1960, Calendar.JULY, 14), listDate), "Sto Cazzo", "Comico", 1, 12.24, 1.2);
		autoreLibriRow.addValue("data", new Date());
		autoreLibriRow.getMapValue().put("anno1", 10.4);
		autoreLibriRow.getMapValue().put("anno2", 15.12);
		autoreLibriRow.getMapValue().put("anno3", 12.4);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Rossi", new CalendarDropDown(new GregorianCalendar(1960, Calendar.JULY, 14), listDate), "Amore", "Sentimentale", 1, 35.455, 4.7);
		autoreLibriRow.addValue("data", new Date());
		autoreLibriRow.getMapValue().put("anno1", 30.4);
		autoreLibriRow.getMapValue().put("anno2", 35.12);
		autoreLibriRow.getMapValue().put("anno3", 32.4);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Verdi", new CalendarDropDown(new GregorianCalendar(1945, Calendar.JULY, 14), listDate), "Rosso", "Sentimentale", 2, 42.0, 6.94);
		autoreLibriRow.addValue("data", new Date());
		autoreLibriRow.getMapValue().put("anno1", 40.4);
		autoreLibriRow.getMapValue().put("anno2", 45.12);
		autoreLibriRow.getMapValue().put("anno3", 42.4);
		list.add(autoreLibriRow);

		autoreLibriRow = new AutoreLibriRowDynamic("Mario", "Verdi", new CalendarDropDown(new GregorianCalendar(1945, Calendar.JULY, 14), listDate), "Arancio", "Sentimentale", 2, 23.24, 3.0);
		autoreLibriRow.addValue("data", new Date());
		autoreLibriRow.getMapValue().put("anno1", 20.4);
		autoreLibriRow.getMapValue().put("anno2", 25.12);
		autoreLibriRow.getMapValue().put("anno3", 22.4);
		list.add(autoreLibriRow);

		AutoreLibriSheetDynamic autoreLibriSheet = new AutoreLibriSheetDynamic("Libri d'autore", "Test di etichetta su report");
		ExcelCellLayoutImpl excelCellLayoutImplSubTotal = (ExcelCellLayoutImpl) ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE.clone();

		ExcelFontImpl fontSubtotal = new ExcelFontImpl(UnderlineType.NONE, 11, false, FontType.CALIBRI, true);
		excelCellLayoutImplSubTotal.setFont(fontSubtotal.getAnnotation());
		
		
		
		
		ExtraColumnAnnotation extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("Totale prezzo anni", null, 21, false));
		extraColumnAnnotation.setExcelSubtotal(new ExcelSubtotalImpl(excelCellLayoutImplSubTotal.getAnnotation(), true, DataConsolidateFunction.SUM));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl("sum(" + RowStartEndType.ROW_EMPTY.getParameter("anno1") + ":" + RowStartEndType.ROW_EMPTY.getParameter("anno3") + ")", "totalePrezzoAnni", false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("totalePrezzoAnni", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("Totale prezzo anni per Autore", null, 22, false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl("sum(" + RowStartEndType.ROW_START.getParameter("totalePrezzoAnni") + ":" + RowStartEndType.ROW_END.getParameter("totalePrezzoAnni") + ")", "totalePrezzoAnniAutore", false));
		extraColumnAnnotation.setExcelMergeRow(new ExcelMergeRowImpl("matricola"));
		extraColumnAnnotation.setExcelColumnWidth(new ExcelColumnWidthImpl(10));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("totalePrezzoAnniAutore", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelHeaderCellLayout(new ExcelHeaderCellLayoutImpl(true, VerticalAlignment.CENTER, (new ExcelRgbColorImpl(255, 0, 0)).getAnnotation(), (new ExcelRgbColorImpl(0, 0, 0)).getAnnotation(), HorizontalAlignment.CENTER,
				(new ExcelFontImpl(UnderlineType.NONE, 11, false, FontType.CALIBRI, true)).getAnnotation(), FillPatternType.SOLID_FOREGROUND,
				(new ExcelBorderImpl(BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN, BorderStyle.THIN)).getAnnotation(), 0, false));
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2015", null, 20, false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno1", extraColumnAnnotation);

		extraColumnAnnotation=new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DATE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("Date", null, 30, false));
		extraColumnAnnotation.setExcelDate(ExcelConstant.EXCEL_DATE_DD_MM_YYYY);
		extraColumnAnnotation.setExcelDataValidation(new ExcelDataValidationImpl("AND(ISNUMBER(${dataDiNascita});${dataDiNascita}=DATE(YEAR(${dataDiNascita}); MONTH(${dataDiNascita}); DAY(${dataDiNascita})))"));
		autoreLibriSheet.addExtraColumn("data", extraColumnAnnotation);
		
		
		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_STRING);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("Ignore Column", null, 20.05, true));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("ignoreColumn", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2016", null, 20.1, false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno2", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("2017", null, 20.2, false));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("anno3", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("%2015", null, 30, false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl(RowStartEndType.ROW_EMPTY.getParameter("anno1") + "/" + RowStartEndType.ROW_EMPTY.getParameter("totalePrezzoAnni"), "percAnno1"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("percAnno1", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("%2016", null, 30.1, false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl(RowStartEndType.ROW_EMPTY.getParameter("anno2") + "/" + RowStartEndType.ROW_EMPTY.getParameter("totalePrezzoAnni"), "percAnno2"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("percAnno2", extraColumnAnnotation);

		extraColumnAnnotation = new ExtraColumnAnnotation();
		extraColumnAnnotation.setExcelCellLayout(ExcelConstant.EXCEL_CELL_LAYOUT_DOUBLE);
		extraColumnAnnotation.setExcelColumn(new ExcelColumnImpl("%2017", null, 30.2, false));
		extraColumnAnnotation.setExcelFunction(new ExcelFunctionImpl(RowStartEndType.ROW_EMPTY.getParameter("anno3") + "/" + RowStartEndType.ROW_EMPTY.getParameter("totalePrezzoAnni"), "percAnno3"));
		autoreLibriSheet.getMapExtraColumnAnnotation().put("percAnno3", extraColumnAnnotation);

		autoreLibriSheet.setListRowSheet(list);
		ExcelChartDataLabelImpl excelChartDataLabel = new ExcelChartDataLabelImpl();
		ExcelChartCategoryImpl excelChartCategoryImpl = new ExcelChartCategoryImpl("titolo", RowStartEndType.ROW_EMPTY.getParameter("percAnno1") + ":" + RowStartEndType.ROW_EMPTY.getParameter("percAnno3"));
		ExcelChartImpl excelChartImpl = null;
		excelChartImpl = new ExcelChartImpl("percAnno", new ExcelChartCategory[] { excelChartCategoryImpl.getAnnotation() }, ChartTypes.LINE, 15, 20, LegendPosition.BOTTOM, AxisPosition.BOTTOM, AxisPosition.LEFT,
				RowStartEndType.ROW_HEADER.getParameter("anno1") + ":" + RowStartEndType.ROW_HEADER.getParameter("anno3"), true, null, new PresetColor[] { PresetColor.BLUE, PresetColor.RED, PresetColor.ORANGE }, PresetColor.BLACK, PresetColor.GRAY,
				AxisCrosses.AUTO_ZERO, AxisCrossBetween.BETWEEN, true, excelChartDataLabel.getAnnotation(), true, null);
		autoreLibriSheet.addExcelChart(excelChartImpl);

		excelChartCategoryImpl = new ExcelChartCategoryImpl("titolo", RowStartEndType.ROW_EMPTY.getParameter("anno1") + ":" + RowStartEndType.ROW_EMPTY.getParameter("anno3"));
		excelChartImpl = new ExcelChartImpl("prezzAnno", new ExcelChartCategory[] { excelChartCategoryImpl.getAnnotation() }, ChartTypes.RADAR, 20, 5, LegendPosition.BOTTOM, AxisPosition.BOTTOM, AxisPosition.LEFT,
				RowStartEndType.ROW_HEADER.getParameter("anno1") + ":" + RowStartEndType.ROW_HEADER.getParameter("anno3"), false, "Titoli", new PresetColor[] { PresetColor.RED }, PresetColor.GREEN, PresetColor.BLUE, AxisCrosses.AUTO_ZERO,
				AxisCrossBetween.BETWEEN, true, excelChartDataLabel.getAnnotation(), true, null);
		autoreLibriSheet.addExcelChart(excelChartImpl);

		TotaleAutoreLibriSheet totaleAutoreLibriSheet = new TotaleAutoreLibriSheet();

		TotaleAutoreLibriRow totaleAutoreLibriRow = new TotaleAutoreLibriRow(1);
		totaleAutoreLibriSheet.getListRowSheet().add(totaleAutoreLibriRow);

		totaleAutoreLibriRow = new TotaleAutoreLibriRow(2);
		totaleAutoreLibriSheet.getListRowSheet().add(totaleAutoreLibriRow);

		autoreLibriSheet.setSheetFunctionsTotal(totaleAutoreLibriSheet);

		listBaseSheet.add(autoreLibriSheet);

		ReportExcel excel = new ReportExcel("Mondadori Dynamic", listBaseSheet);

		byte[] byteReport = this.generateExcel.createFileXlsx(excel);

		SpreadsheetUtils.writeToFile(PATH_FILE, excel.getTitle(), ".xlsx", byteReport);

	}

	@Test
	public void testRadar() throws Exception {

		String[] categories = new String[] { "Cat1", "Cat2", "Cat3", "Cat4", "Cat5" };
		Double[] values = new Double[] { 1d, 2d, 3d, 4d, 5d };

		try (XSSFWorkbook wb = new XSSFWorkbook()) {

			XSSFSheet sheet = wb.createSheet("radarchart");

			Row row;
			Cell cell;
			row = sheet.createRow(0);
			cell = row.createCell(1);
			cell.setCellValue("Series");
			for (int i = 1; i <= categories.length; i++) {
				row = sheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(categories[i - 1]);
				cell = row.createCell(1);
				cell.setCellValue(values[i - 1]);
			}

			XSSFDrawing drawing = sheet.createDrawingPatriarch();
			XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 3, 0, 10, 15);

			XSSFChart chart = drawing.createChart(anchor);
			chart.setTitleText("Chart title");
			chart.setTitleOverlay(false);

			XDDFChartLegend legend = chart.getOrAddLegend();
			legend.setPosition(LegendPosition.RIGHT);

			XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
			// major gridlines of category axis are the cobweb radial lines
			XDDFShapeProperties bottomAxisGridLinesShapeProperties = bottomAxis.getOrAddMajorGridProperties();
			bottomAxisGridLinesShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLACK))));

			XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
			leftAxis.setMajorUnit(1d);
			// left axis line color is needed for radial lines
			XDDFShapeProperties leftAxisShapeProperties = leftAxis.getOrAddShapeProperties();
			leftAxisShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLACK))));
			// major gridlines of values axis are the cobweb ring lines
			XDDFShapeProperties leftAxisGridLinesShapeProperties = leftAxis.getOrAddMajorGridProperties();
			leftAxisGridLinesShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(PresetColor.GRAY))));

			leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
			leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);

			XDDFDataSource<String> cat = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(1, categories.length, 0, 0));
			XDDFNumericalDataSource<Double> val = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, categories.length, 1, 1));

			XDDFChartData data = chart.createData(ChartTypes.RADAR, bottomAxis, leftAxis);
			data.setVaryColors(false);
			XDDFChartData.Series series = data.addSeries(cat, val);
			series.setTitle(sheet.getRow(0).getCell(1).getStringCellValue(), new CellReference(sheet.getRow(0).getCell(1)));
			solidLineSeries(series, PresetColor.BLUE);
			chart.plot(data);

			// radar chart needs to have a radar style set
			XDDFRadarChartData radarChartData = (XDDFRadarChartData) data;
			radarChartData.setStyle(RadarStyle.MARKER);

			// Do not auto delete the title; is necessary for showing title in Calc
			if (chart.getCTChart().getAutoTitleDeleted() == null)
				chart.getCTChart().addNewAutoTitleDeleted();
			chart.getCTChart().getAutoTitleDeleted().setVal(false);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			wb.write(byteArrayOutputStream);
			SpreadsheetUtils.writeToFile(PATH_FILE, "Radar", ".xlsx", byteArrayOutputStream.toByteArray());
			try (FileOutputStream fileOut = new FileOutputStream("ooxml-radar-chart.xlsx")) {
				wb.write(fileOut);
			}
		}
	}

	private static void solidLineSeries(XDDFChartData.Series series, PresetColor color) {
		XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color));
		XDDFLineProperties line = new XDDFLineProperties();
		line.setFillProperties(fill);
		XDDFShapeProperties properties = series.getShapeProperties();
		if (properties == null) {
			properties = new XDDFShapeProperties();
		}
		properties.setLineProperties(line);
		series.setShapeProperties(properties);
	}

	@Test
	public void convertHtml() {
//		try {
//			InputStream inputStream = new FileInputStream("/mnt/report/MondadoriDynamic.xlsx");
//			Workbook workbook = new Workbook();
//			// Open an excel file
//			workbook.open(inputStream);
//			    
//			// Save to an pdf file
//			workbook.save("/mnt/report/ConvertExcelToPDF.pdf");
//		} catch (Throwable e) {
//			logger.error(ExceptionUtils.getStackTrace(e));
//		}
//		
//		XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
//		
//		SpreadsheetMLPackage excelMLPackage = SpreadsheetMLPackage.load(inputStream);
//		//OutputStream outputStream=new FileOutputStream("/mnt/report/test.xml");
//		//ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//		excelMLPackage.save(new File("/mnt/report/test.xml"));
//		inputStream = new FileInputStream("/mnt/report/test.xml");
//		//SpreadsheetUtils.writeToFile(PATH_FILE, "test", ".xml", outputStream.toByteArray());
//		//inputStream = new ByteArrayInputStream(outputStream.toByteArray());
//		
//		
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//		Document document = builder.parse(inputStream);
//		PDDocument pdfDocument = new PDDocument();
//		PDPage page = new PDPage();
//		pdfDocument.addPage(page);
//		PDPageContentStream contentStream = new PDPageContentStream(pdfDocument, page);
//        NodeList rowNodes = document.getElementsByTagName("row");
//        for (int i = 0; i < rowNodes.getLength(); i++) {
//            Element rowElement = (Element) rowNodes.item(i);
//            NodeList cellNodes = rowElement.getElementsByTagName("cell");
//            for (int j = 0; j < cellNodes.getLength(); j++) {
//                Element cellElement = (Element) cellNodes.item(j);
//                String cellText = cellElement.getTextContent();
//               // contentStream.setFont(PDType1Font.HELVETICA, 12);
//                contentStream.newLineAtOffset(100, 700 - (i * 20)); // Adjust coordinates as needed
//                contentStream.showText(cellText);
//            }
//        }
//		contentStream.close();
//
//        pdfDocument.save("/mnt/report/output.pdf");
//        pdfDocument.close();
		
		
		
		
//		File xsltFile = new File("/mnt/report/test.xslt");
//		StreamSource xmlSource = new StreamSource(inputStream);
//		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
//		FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
//
//		OutputStream out = new FileOutputStream("/mnt/report/output.pdf");
//
//		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
//		TransformerFactory factory = TransformerFactory.newInstance();
//		Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
//		Result res = new SAXResult(fop.getDefaultHandler());
//		transformer.transform(xmlSource, res);

	}
	
	
	
	@Test
	public void situazioneUffici() throws Exception {
		SituazioneUfficiSheet situazioneUfficiSheet = new SituazioneUfficiSheet("PROVA SOMME INTERMEDIE");
		for (int i = 0; i < 10; i++) {
			
            situazioneUfficiSheet.addRowSheets(new SituazioneUfficiRow("Tempo Indeterminato","Comparto Dirigenti", "DIRIGENTI", "PROFESSIONALE ".concat(String.valueOf(i)), BigDecimal.valueOf(100),
            		BigDecimal.valueOf(75), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,BigDecimal.ONE));
        }
        for (int i = 0; i < 10; i++) {
            situazioneUfficiSheet.addRowSheets(new SituazioneUfficiRow("Tempo Indeterminato","Funzioni Centrali", "AREA1", "PROFESSIONALE ".concat(String.valueOf(i)), BigDecimal.valueOf(65),
            		BigDecimal.valueOf(14), BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE,BigDecimal.ONE));
        }
        for (int i = 0; i < 10; i++) {
            situazioneUfficiSheet.addRowSheets(new SituazioneUfficiRow("Tempo Indeterminato","Funzioni Centrali",  "AREA2", "PROFESSIONALE ".concat(String.valueOf(i)), BigDecimal.valueOf(100),
            		BigDecimal.valueOf(33), BigDecimal.ONE,  BigDecimal.ONE, BigDecimal.ONE,BigDecimal.ONE));
        }
        for (int i = 0; i < 10; i++) {
            situazioneUfficiSheet.addRowSheets(new SituazioneUfficiRow("Tempo Indeterminato","Funzioni Centrali", "AREA3", "PROFESSIONALE ".concat(String.valueOf(i)), BigDecimal.valueOf(100),
            		BigDecimal.valueOf(24), BigDecimal.ONE,  BigDecimal.ONE, BigDecimal.ONE,BigDecimal.ONE));
        }
        for (int i = 0; i < 10; i++) {
            situazioneUfficiSheet.addRowSheets(new SituazioneUfficiRow("Tempo Determinato", "Funzioni Centrali", "TD", "PROFESSIONALE ".concat(String.valueOf(i)), BigDecimal.valueOf(100),
            		BigDecimal.valueOf(80), BigDecimal.ONE,  BigDecimal.ONE, BigDecimal.ONE,BigDecimal.ONE));
        }
		
	
        List<BaseSheet> listBaseSheets = new ArrayList<>();
        listBaseSheets.add(situazioneUfficiSheet);
        ReportExcel reportExcel = new ReportExcel("situazione_uffici", listBaseSheets);
        byte[] byteReport = this.generateExcel.createFileXlsx(reportExcel);

		SpreadsheetUtils.writeToFile(PATH_FILE, reportExcel.getTitle(), ".xlsx", byteReport);
	}

}
