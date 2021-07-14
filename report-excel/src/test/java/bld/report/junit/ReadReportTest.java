/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.ReportTest.java
*/
package bld.report.junit;

import java.io.FileInputStream;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.util.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bld.read.report.csv.ReadCsv;
import bld.read.report.csv.domain.CsvRead;
import bld.read.report.excel.ReadExcel;
import bld.read.report.excel.config.annotation.EnableExcelRead;
import bld.read.report.excel.constant.ExcelType;
import bld.read.report.excel.domain.ExcelRead;
import bld.report.read.junit.entity.ReadAutoreLibriRow;
import bld.report.read.junit.entity.ReadAutoreLibriSheet;
import bld.report.read.junit.entity.ReadGenereRow;
import bld.report.read.junit.entity.ReadGenereSheet;
import bld.report.read.junit.entity.UserCsvRow;

/**
 * The Class ReportTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties
//@EnableExcelGenerator
@EnableExcelRead
@EnableTransactionManagement
public class ReadReportTest {

	private final static Log logger = LogFactory.getLog(ReadReportTest.class);


	@Autowired
	private ReadCsv readCsv;
	

	/** The read excel. */
	@Autowired
	private ReadExcel readExcel;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}



	/**
	 * Test read.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRead() throws Exception {
		FileInputStream inputStream = new FileInputStream("/mnt/report/Mondadori-Dynamic.xlsx");
		byte[] report = IOUtils.toByteArray(inputStream);
		ExcelRead excelRead = new ExcelRead();
		excelRead.setReportExcel(report);
		excelRead.setExcelType(ExcelType.XLSX);
		excelRead.getListSheetRead().add(new ReadAutoreLibriSheet("Libri d'autore"));
//		excelRead.getListSheetRead().add(new ReadGenereSheet("Genere"));
		excelRead = this.readExcel.convertExcelToEntity(excelRead);
		ReadAutoreLibriSheet sheet;
		try {
			sheet = excelRead.getSheet(ReadAutoreLibriSheet.class);
			for (ReadAutoreLibriRow row : sheet.getListRowSheet())
				System.out.println(row.toString());

			ReadGenereSheet readGenereSheet = excelRead.getSheet(ReadGenereSheet.class);
			for (ReadGenereRow row : readGenereSheet.getListRowSheet())
				System.out.println(row.toString());
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}

	
	
	@Test
	public void testReadCsv() throws Exception {
		FileInputStream inputStream = new FileInputStream("/mnt/report/Test.csv");
		byte[] report = IOUtils.toByteArray(inputStream);
		CsvRead<UserCsvRow> userCsvRead=new CsvRead<>();
		userCsvRead.setCsv(report);
		try {
			this.readCsv.convertCsvToEntity(userCsvRead,UserCsvRow.class);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}


}
