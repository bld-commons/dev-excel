/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import bld.generator.report.excel.config.annotation.EnableExcelGenerator;
import bld.read.report.excel.config.annotation.EnableExcelRead;

/**
 * The Class ReportExcelApplication.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"bld.report","com.bld.read"})
@EnableExcelGenerator
@EnableExcelRead
public class ReportExcelApplication {

	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ReportExcelApplication.class, args);
	}
	
	
}
