/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.ReportTest.java
*/
package bld.report.junit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bld.commons.connection.client.RestClientConnection;
import com.bld.commons.connection.config.annotation.EnableRestConnection;
import com.bld.commons.connection.model.ObjectRequest;
import com.bld.read.report.csv.ReadCsv;
import com.bld.read.report.csv.domain.CsvRead;
import com.bld.read.report.excel.ReadExcel;
import com.bld.read.report.excel.config.annotation.EnableExcelRead;
import com.bld.read.report.excel.constant.ExcelType;
import com.bld.read.report.excel.domain.ExcelRead;

import bld.report.controller.entity.ReadAutoreLibriRow;
import bld.report.controller.entity.ReadAutoreLibriSheet;
import bld.report.controller.entity.ReadGenereRow;
import bld.report.controller.entity.ReadGenereSheet;
import bld.report.controller.input.ExcelModel;
import bld.report.generator.junit.entity.EmployeeRow;
import bld.report.generator.junit.entity.ProductRow;
import bld.report.generator.junit.entity.TestDataGenerator;
import bld.report.read.junit.entity.DataMeteoRow;
import bld.report.read.junit.entity.DataMeteoSheet;
import bld.report.read.junit.entity.ReadEmployeeCsvRow;
import bld.report.read.junit.entity.ReadEmployeeRow;
import bld.report.read.junit.entity.ReadEmployeeSheet;
import bld.report.read.junit.entity.ReadProductRow;
import bld.report.read.junit.entity.ReadProductSheet;
import bld.report.read.junit.entity.RendicontazioneMassivaImportColumn;

/**
 * The Class ReportTest.
 */

@SpringBootTest
//@EnableExcelGenerator
@EnableExcelRead
@EnableTransactionManagement
@EnableRestConnection
public class ReadReportTest {

	private final static Log logger = LogFactory.getLog(ReadReportTest.class);


	@Autowired
	private ReadCsv readCsv;
	

	/** The read excel. */
	@Autowired
	private ReadExcel readExcel;
	
	@Autowired
	private RestClientConnection restClientConnection;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}



	/**
	 * Test read.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRead() throws Exception {
		FileInputStream inputStream = new FileInputStream("/mnt/report/Mondadori-JPA.xlsx");
		byte[] report = IOUtils.toByteArray(inputStream);
		ExcelRead excelRead = new ExcelRead();
		excelRead.setReportExcel(report);
		excelRead.setExcelType(ExcelType.XLSX);
		String sheetName="Libri d'autore";
		excelRead.addSheetConvertion(ReadAutoreLibriSheet.class, sheetName);
		excelRead.addSheetConvertion(ReadGenereSheet.class,"Genere");
		excelRead = this.readExcel.convertExcelToEntity(excelRead);
		try {
			ReadAutoreLibriSheet sheet = excelRead.getSheet(ReadAutoreLibriSheet.class,sheetName);
			for (ReadAutoreLibriRow row : sheet.getRows())
				System.out.println(row.toString());

			ReadGenereSheet readGenereSheet = excelRead.getSheet(ReadGenereSheet.class,"Genere");
			for (ReadGenereRow row : readGenereSheet.getRows())
				System.out.println(row.toString());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	@Test
	public void testDataMeteo() throws Exception {
		ExcelRead excelRead=new ExcelRead();
		excelRead.setExcelType(ExcelType.XLSX);
		excelRead.setReportExcel("/mnt/report/test_data_meteo.xlsx");
		excelRead.addSheetConvertion(DataMeteoSheet.class, "sheet");
		Date start=new Date();
		excelRead=this.readExcel.convertExcelToEntity(excelRead);
		Date end=new Date();
		DataMeteoSheet sheet=excelRead.getSheet(DataMeteoSheet.class, "sheet");
		for (DataMeteoRow row : sheet.getRows())
			logger.info(row.toString());
		logger.info("row size: "+sheet.size());
		logger.info("Time conversion: "+(end.getTime()-start.getTime())+"ms");
	}
	

	@Test
	public void testReadCsv() throws Exception {
		FileInputStream inputStream = new FileInputStream("/mnt/report/Test.csv");
		byte[] report = IOUtils.toByteArray(inputStream);
		CsvRead<RendicontazioneMassivaImportColumn> userCsvRead=new CsvRead<>();
		userCsvRead.setCsv(report);
		try {
			this.readCsv.convertCsvToEntity(userCsvRead,RendicontazioneMassivaImportColumn.class);
			logger.info("Size list: "+userCsvRead.getRows().size());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}
	
	@Test
	public void testJsonReadSheet() throws Exception {
		readExcelClient("http://localhost:8080/excel/sheet-read");
	}



	private void readExcelClient(String url) throws FileNotFoundException, IOException, Exception {
		FileInputStream inputStream = new FileInputStream("/mnt/report/Mondadori-JPA.xlsx");
		byte[] report = IOUtils.toByteArray(inputStream);
		String file=Base64.getEncoder().encodeToString(report);
		file="data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,"+file;
		ObjectRequest<ExcelModel>objRequest=ObjectRequest.newInstancePost(url);
		ExcelModel excelModel=new ExcelModel();
		excelModel.setExcel(file);
		excelModel.setName("test excel");
		objRequest.setData(excelModel);
		objRequest.setContentType(MediaType.APPLICATION_JSON);
		this.restClientConnection.entityRestTemplate(objRequest, Void.class);
	}


	@Test
	public void testJsonReadExcel() throws Exception {
		readExcelClient("http://localhost:8080/excel/excel-read");
	}
	
	@Test
	public void testReadEmployee() throws Exception {
		// Carica il file fuori dal timer: così misuriamo solo il mapping, non l'I/O su disco
		byte[] bytes = IOUtils.toByteArray(new FileInputStream("/mnt/report/employees.xlsx"));

		// Warmup: carica le classi JIT e riempie la cache di reflection prima della misura
		ExcelRead warmup = new ExcelRead();
		warmup.setReportExcel(bytes);
		warmup.setExcelType(ExcelType.XLSX);
		warmup.addSheetConvertion(ReadEmployeeSheet.class, "Dipendenti");
		warmup.addSheetConvertion(ReadProductSheet.class, "Prodotti");
		this.readExcel.convertExcelToEntity(warmup);

		// Run misurato
		ExcelRead excelRead = new ExcelRead();
		excelRead.setReportExcel(bytes);
		excelRead.setExcelType(ExcelType.XLSX);
		excelRead.addSheetConvertion(ReadEmployeeSheet.class, "Dipendenti");
		excelRead.addSheetConvertion(ReadProductSheet.class, "Prodotti");
		long start = System.currentTimeMillis();
		excelRead = this.readExcel.convertExcelToEntity(excelRead);
		long elapsed = System.currentTimeMillis() - start;

		ReadEmployeeSheet employeeSheet = excelRead.getSheet(ReadEmployeeSheet.class, "Dipendenti");
		ReadProductSheet productSheet = excelRead.getSheet(ReadProductSheet.class, "Prodotti");

		Assertions.assertEquals(10000, employeeSheet.size(), "Conteggio dipendenti errato");
		Assertions.assertEquals(5000, productSheet.size(), "Conteggio prodotti errato");
		logger.info("Dipendenti letti: " + employeeSheet.size());
		logger.info("Prodotti letti: " + productSheet.size());
		logger.info("Tempo mapping Excel (post-warmup, senza I/O): " + elapsed + "ms");

		// Round-trip spot check: rigenera i dati con lo stesso seed fisso e confronta
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		TestDataGenerator gen = new TestDataGenerator(TestDataGenerator.FIXED_SEED);
		List<EmployeeRow> expectedEmployees = gen.generateEmployees(10000);
		List<ProductRow> expectedProducts = gen.generateProducts(5000);

		int[] checkRows = {0, 1, 2, 4999, 9997, 9998, 9999};
		for (int i : checkRows) {
			ReadEmployeeRow actual = employeeSheet.getRows().get(i);
			EmployeeRow exp = expectedEmployees.get(i);
			Assertions.assertEquals(exp.getId(),           actual.getId(),           "ID mismatch riga " + i);
			Assertions.assertEquals(exp.getNome(),         actual.getNome(),         "Nome mismatch riga " + i);
			Assertions.assertEquals(exp.getCognome(),      actual.getCognome(),      "Cognome mismatch riga " + i);
			Assertions.assertEquals(exp.getDipartimento(), actual.getDipartimento(), "Dipartimento mismatch riga " + i);
			Assertions.assertEquals(exp.getStipendio(),    actual.getStipendio(), 0.01, "Stipendio mismatch riga " + i);
			Assertions.assertEquals(exp.getAttivo(),       actual.getAttivo(),       "Attivo mismatch riga " + i);
			Assertions.assertEquals(sdf.format(exp.getDataAssunzione()), sdf.format(actual.getDataAssunzione()), "DataAssunzione mismatch riga " + i);
			Assertions.assertEquals(Objects.toString(exp.getNote(), ""), Objects.toString(actual.getNote(), ""), "Note mismatch riga " + i);
		}

		int[] checkProductRows = {0, 1, 2, 2499, 4997, 4998, 4999};
		for (int i : checkProductRows) {
			ReadProductRow actual = productSheet.getRows().get(i);
			ProductRow exp = expectedProducts.get(i);
			Assertions.assertEquals(exp.getCodice(),     actual.getCodice(),     "Codice mismatch riga " + i);
			Assertions.assertEquals(exp.getCategoria(),  actual.getCategoria(),  "Categoria mismatch riga " + i);
			Assertions.assertEquals(exp.getFornitore(),  actual.getFornitore(),  "Fornitore mismatch riga " + i);
			Assertions.assertEquals(exp.getPrezzo(),     actual.getPrezzo(), 0.01, "Prezzo mismatch riga " + i);
			Assertions.assertEquals(exp.getQuantita(),   actual.getQuantita(),   "Quantita mismatch riga " + i);
			Assertions.assertEquals(exp.getDisponibile(), actual.getDisponibile(), "Disponibile mismatch riga " + i);
			Assertions.assertEquals(sdf.format(exp.getDataCreazione()), sdf.format(actual.getDataCreazione()), "DataCreazione mismatch riga " + i);
		}
		logger.info("Round-trip spot check OK: " + checkRows.length + " employee rows + " + checkProductRows.length + " product rows verificate");
	}

	@Test
	public void testReadEmployeeCsv() throws Exception {
		CsvRead<ReadEmployeeCsvRow> csvRead = new CsvRead<>();
		csvRead.setCsv("/mnt/report/employees.csv");
		long start = System.currentTimeMillis();
		this.readCsv.convertCsvToEntity(csvRead, ReadEmployeeCsvRow.class);
		long elapsed = System.currentTimeMillis() - start;
		Assertions.assertEquals(10000, csvRead.getRows().size(), "Conteggio dipendenti CSV errato");
		logger.info("Dipendenti CSV letti: " + csvRead.getRows().size());
		logger.info("Tempo lettura CSV: " + elapsed + "ms");
	}

	@Test
	public void readFile() throws Exception{
		FileInputStream inputStream = new FileInputStream("/mnt/report/inserimento-massivo-persona.xlsx");
		byte[] report = IOUtils.toByteArray(inputStream);
		String file=Base64.getEncoder().encodeToString(report);
		file="data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,"+file;
		System.out.println(file);
	}
}
