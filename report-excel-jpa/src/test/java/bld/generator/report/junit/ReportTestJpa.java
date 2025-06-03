/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.ReportTest.java
*/
package bld.generator.report.junit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.GenerateExcel;
import com.bld.generator.report.excel.data.ReportExcel;

import bld.generator.report.junit.entity.AutoreLibriSheet;
import bld.generator.report.junit.entity.CasaEditrice;
import bld.generator.report.junit.entity.CensimentoSheet;
import bld.generator.report.junit.entity.GenereSheet;
import bld.generator.report.junit.entity.SalaryRow;
import bld.generator.report.junit.entity.SalarySheet;
import bld.generator.report.junit.entity.StatoMatricolareSheet;
import bld.generator.report.junit.entity.TotaleAutoreLibriRow;
import bld.generator.report.junit.entity.TotaleAutoreLibriSheet;
import bld.generator.report.junit.entity.UtenteSheet;

/**
 * The Class ReportTest.
 */
@SpringBootTest
@ConfigurationProperties
@ComponentScan(basePackages = {"com.bld.generator","com.bld.read"})
@EnableTransactionManagement
public class ReportTestJpa {

	/** The Constant PATH_FILE. */
	private static final String PATH_FILE = "/mnt/report/";

	/** The generate excel. */
	@Autowired
	private GenerateExcel generateExcel;
	
	
//	/** The read excel. */
//	@Autowired
//	private ReadExcel readExcel;
//	
//	@Autowired
//	private UtenteService utenteService;
	

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
		
		
		UtenteSheet utenteSheet=new UtenteSheet("Utente");
		utenteSheet.getMapParameters().put("cognome", Arrays.asList("Rossi","Bianchi"));
		listBaseSheet.add(utenteSheet);
		
		CasaEditrice casaEditrice = new CasaEditrice("Casa Editrice","Mondadori", new GregorianCalendar(1955, Calendar.MAY, 10), "Roma", System.getProperty("user.home")+"/Documents/git-project/dev-excel/linux.jpg","Drammatico");
		listBaseSheet.add(casaEditrice);
		//Libri d'autore
		
		AutoreLibriSheet autoreLibriSheet = new AutoreLibriSheet("Libri d'autore","Test label");
		TotaleAutoreLibriSheet totaleAutoreLibriSheet=new TotaleAutoreLibriSheet();
		totaleAutoreLibriSheet.getListRowSheet().add(new TotaleAutoreLibriRow("Totale"));
		autoreLibriSheet.setSheetFunctionsTotal(totaleAutoreLibriSheet);
		listBaseSheet.add(autoreLibriSheet);
		
		GenereSheet genereSheet=new GenereSheet("Genere");
		listBaseSheet.add(genereSheet);
		SalarySheet salarySheet=new SalarySheet("salary");
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		listBaseSheet.add(salarySheet);
		
		
//		List<Utente> listUtente=this.utenteService.findAllUtentes();
//		BigDataUtenteSheet bigDataUtenteSheet=new BigDataUtenteSheet("Big Data");
//		int maxRandom=listUtente.size()-1;
//		for(int i=0;i<500000;i++) {
//			BigDataUtenteRow utenteRow=new BigDataUtenteRow();
//			PropertyUtils.copyProperties(utenteRow, listUtente.get((int)(Math.random()*maxRandom)));
//			bigDataUtenteSheet.getListRowSheet().add(utenteRow);
//		}
//		listBaseSheet.add(bigDataUtenteSheet);
		
		try {
			ReportExcel excel = new ReportExcel("Mondadori JPA", listBaseSheet);

			//byte[] byteReport = this.generateExcel.createBigDataFileXlsx(excel);
			
			byte[] byteReport = this.generateExcel.createFileXlsx(excel);

			SpreadsheetUtils.writeToFile(PATH_FILE,excel.getTitle(), ".xlsx", byteReport);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	@Test
	public void testChart() {
		CensimentoSheet censimento=new CensimentoSheet("Censimento");
		ReportExcel excel=new ReportExcel();
		excel.setTitle("Censimento");
		excel.getListBaseSheet().add(censimento);
		
		byte[] byteReport=null;
		try {
			byteReport = this.generateExcel.createFileXlsx(excel);
			SpreadsheetUtils.writeToFile(PATH_FILE,excel.getTitle(), ".xlsx", byteReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	@Test
	public void bigData() {
		StatoMatricolareSheet sheet=new StatoMatricolareSheet("Stato Matricolare");
		ReportExcel excel=new ReportExcel("Stato Matricolare");
		excel.addBaseSheet(sheet);
		byte[] byteReport=null;
		try {
			byteReport = this.generateExcel.createFileXlsx(excel);
			SpreadsheetUtils.writeToFile(PATH_FILE,excel.getTitle(), ".xlsx", byteReport);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	/**
//	 * Test read.
//	 *
//	 * @throws Exception the exception
//	 */
//	@Test
//	public void testRead() throws Exception{
//		FileInputStream inputStream = new FileInputStream("/mnt/report/Mondadori-Dynamic.xlsx");
//		byte[] report=IOUtils.toByteArray(inputStream);
//		ExcelRead excelRead=new ExcelRead();
//		excelRead.setReportExcel(report);
//		excelRead.setExcelType(ExcelType.XLSX);
//		excelRead.getListSheetRead().add(new ReadAutoreLibriSheet("Libri d'autore"));
////		excelRead.getListSheetRead().add(new ReadGenereSheet("Genere"));
//		excelRead=this.readExcel.convertExcelToEntity(excelRead);
//		ReadAutoreLibriSheet sheet;
//		try {
//			sheet = excelRead.getSheet(ReadAutoreLibriSheet.class);
//			for(ReadAutoreLibriRow row:sheet.getListRowSheet()) 
//				System.out.println(row.toString());
//			
//			ReadGenereSheet readGenereSheet = excelRead.getSheet(ReadGenereSheet.class);
//			for(ReadGenereRow row:readGenereSheet.getListRowSheet()) 
//				System.out.println(row.toString());
//		} catch (Exception e) {
//			ExceptionUtils.getStackTrace(e);
//		}
//		
//		
//		
//	}
//	
//	@Test
//	public void testWriteImage() throws Exception{
//		String path="/home/francesco/Documents/git-project/dev-excel/linux.jpg";
//		InputStream inputStream=new FileInputStream(path);
//		byte[] value=IOUtils.toByteArray(inputStream);
//		this.utenteService.updateImage(value,path);
//	}
	
	

}
