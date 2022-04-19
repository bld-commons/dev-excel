/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.ReportTest.java
*/
package bld.generator.report.junit;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.util.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.data.ReportExcel;
import bld.generator.report.junit.entity.AutoreLibriSheet;
import bld.generator.report.junit.entity.GenereSheet;
import bld.generator.report.junit.entity.TotaleAutoreLibriRow;
import bld.generator.report.junit.entity.TotaleAutoreLibriSheet;
import bld.generator.report.junit.entity.UtenteEntitySheet;
import bld.generator.report.junit.entity.UtenteSheet;
import bld.generator.report.utils.ExcelUtils;
import bld.read.report.excel.ReadExcel;
import bld.read.report.excel.constant.ExcelType;
import bld.read.report.excel.domain.ExcelRead;
import bld.read.report.junit.entity.ReadAutoreLibriRow;
import bld.read.report.junit.entity.ReadAutoreLibriSheet;
import bld.read.report.junit.entity.ReadGenereRow;
import bld.read.report.junit.entity.ReadGenereSheet;

/**
 * The Class ReportTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties
@ComponentScan(basePackages = {"bld.generator","bld.read"})
@EnableTransactionManagement
public class ReportTestMultipleDataSource {

	/** The Constant PATH_FILE. */
	private static final String PATH_FILE = "/mnt/report/";

	/** The generate excel. */
	@Autowired
	private GenerateExcel generateExcel;
	
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
	 * Test.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void test() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
		
		
		UtenteSheet utenteSheet=new UtenteSheet("Utente");
		utenteSheet.getMapParameters().put("cognome", "Rossi");
		listBaseSheet.add(utenteSheet);
		
		UtenteEntitySheet utenteEntitySheet=new UtenteEntitySheet("Utente Entity");
		listBaseSheet.add(utenteEntitySheet);
		
		GenereSheet genereSheet=new GenereSheet("Genere");
		listBaseSheet.add(genereSheet);
		
		AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore","Test label");
		TotaleAutoreLibriSheet totaleAutoreLibriSheet=new TotaleAutoreLibriSheet();
		totaleAutoreLibriSheet.getListRowSheet().add(new TotaleAutoreLibriRow("Totale"));
		autoreLibriSheet.setSheetFunctionsTotal(totaleAutoreLibriSheet);
		listBaseSheet.add(autoreLibriSheet);
		ReportExcel excel = new ReportExcel("Mondadori Multiple Datasource", listBaseSheet);

		byte[] byteReport = this.generateExcel.createFileXlsx(excel);

		ExcelUtils.writeToFile(PATH_FILE,excel.getTitle(), ".xlsx", byteReport);

	}

	

	
	/**
	 * Test read.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testRead() throws Exception{
		FileInputStream inputStream = new FileInputStream("/mnt/report/Mondadori-Dynamic.xlsx");
		byte[] report=IOUtils.toByteArray(inputStream);
		ExcelRead excelRead=new ExcelRead();
		excelRead.setReportExcel(report);
		excelRead.setExcelType(ExcelType.XLSX);
		excelRead.getListSheetRead().add(new ReadAutoreLibriSheet("Libri d'autore"));
		excelRead.getListSheetRead().add(new ReadGenereSheet("Genere"));
		excelRead=this.readExcel.convertExcelToEntity(excelRead);
		ReadAutoreLibriSheet sheet;
		try {
			sheet = excelRead.getSheet(ReadAutoreLibriSheet.class,"Libri d'autore");
			for(ReadAutoreLibriRow row:sheet.getListRowSheet()) 
				System.out.println(row.toString());
			
			ReadGenereSheet readGenereSheet = excelRead.getSheet(ReadGenereSheet.class,"Genere");
			for(ReadGenereRow row:readGenereSheet.getListRowSheet()) 
				System.out.println(row.toString());
		} catch (Exception e) {
			ExceptionUtils.getStackTrace(e);
		}
		
		
		
	}
	
	

}
