/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.ReportTest.java
*/
package bld.report.junit;

import java.util.ArrayList;
import java.util.List;

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
import bld.generator.report.utils.ExcelUtils;
import bld.report.generator.junit.entity.SalaryRow;
import bld.report.generator.junit.entity.SalarySheet;

/**
 * The Class ReportTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties
@ComponentScan(basePackages = {"bld.generator","bld.read"})
@EnableTransactionManagement
public class SalaryTest {

	/** The Constant PATH_FILE. */
	private static final String PATH_FILE = "/mnt/report/";

	/** The generate excel. */
	@Autowired
	private GenerateExcel generateExcel;

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
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("a",2.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		salarySheet.getListRowSheet().add(new SalaryRow("c",1.0));
		listBaseSheet.add(salarySheet);
		
		ReportExcel report=new ReportExcel("test", listBaseSheet);
		
		byte[] byteReport = this.generateExcel.createFileXlsx(report);
		ExcelUtils.writeToFile(PATH_FILE,report.getTitle(), ".xlsx", byteReport);
		
	}
	
	

}
