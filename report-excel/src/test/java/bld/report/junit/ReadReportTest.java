/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.ReportTest.java
*/
package bld.report.junit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
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
import bld.report.read.junit.entity.DataMeteoRow;
import bld.report.read.junit.entity.DataMeteoSheet;
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
			for (ReadAutoreLibriRow row : sheet.getListRowSheet())
				System.out.println(row.toString());

			ReadGenereSheet readGenereSheet = excelRead.getSheet(ReadGenereSheet.class,"Genere");
			for (ReadGenereRow row : readGenereSheet.getListRowSheet())
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
		for (DataMeteoRow row : sheet.getListRowSheet())
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
			logger.info("Size list: "+userCsvRead.getListRowSheet().size());
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
	public void readFile() throws Exception{
		FileInputStream inputStream = new FileInputStream("/mnt/report/inserimento-massivo-persona.xlsx");
		byte[] report = IOUtils.toByteArray(inputStream);
		String file=Base64.getEncoder().encodeToString(report);
		file="data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64,"+file;
		System.out.println(file);
	}
}
