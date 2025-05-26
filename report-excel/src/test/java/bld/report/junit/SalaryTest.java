/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.ReportTest.java
*/
package bld.report.junit;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.data.ReportExcel;
import bld.report.generator.junit.entity.SalaryRow;
import bld.report.generator.junit.entity.SalarySheet;

/**
 * The Class ReportTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"bld.generator","bld.read"})
@EnableTransactionManagement
public class SalaryTest {

	/** The Constant PATH_FILE. */
	private static final String PATH_FILE = "/mnt/report/";

	/** The generate excel. */
	@Autowired
	private GenerateExcel generateExcel;
	
	private final static Logger logger=LoggerFactory.getLogger(SalaryTest.class);

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSalary() throws Exception {
		List<BaseSheet> listBaseSheet = new ArrayList<>();
		SalarySheet salarySheet=new SalarySheet("salary");
		salarySheet.getListRowSheet().add(new SalaryRow("Italy","Rome","A",32.0));
		salarySheet.getListRowSheet().add(new SalaryRow("Italy","Rome","B",25.5));
		salarySheet.getListRowSheet().add(new SalaryRow("Italy","Rome","C",12.0));
		salarySheet.getListRowSheet().add(new SalaryRow("Italy","Rome","D",25.0));
		
		
		salarySheet.getListRowSheet().add(new SalaryRow("Italy","Milan","A",21.0));
		salarySheet.getListRowSheet().add(new SalaryRow("Italy","Milan","B",12.0));
		
		
		salarySheet.getListRowSheet().add(new SalaryRow("England","London","A",31.0));
		salarySheet.getListRowSheet().add(new SalaryRow("England","London","B",21.0));
		salarySheet.getListRowSheet().add(new SalaryRow("England","London","C",25.0));
		salarySheet.getListRowSheet().add(new SalaryRow("England","London","D",46.0));
		salarySheet.getListRowSheet().add(new SalaryRow("England","London","E",55.0));
		
		
		
		salarySheet.getListRowSheet().add(new SalaryRow("England","Manchester","A",8.0));
		salarySheet.getListRowSheet().add(new SalaryRow("England","Manchester","B",5.0));
		salarySheet.getListRowSheet().add(new SalaryRow("England","Manchester","C",7.0));
		listBaseSheet.add(salarySheet);
		
		ReportExcel report=new ReportExcel("test", listBaseSheet);
		
		byte[] byteReport=null;
		try {
			byteReport = this.generateExcel.createFileXlsx(report);
			SpreadsheetUtils.writeToFile(PATH_FILE,report.getTitle(), ".xlsx", byteReport);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		
		
	}
	
	

}
