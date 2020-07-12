/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import bld.read.report.excel.ReadExcel;

/**
 * The Class ExcelReadConfiguration.<br>
 * This class is for configurations.
 */
@Configuration
public class ExcelReadConfiguration {
	
	/** The read excel. */
	@Autowired
	private ReadExcel readExcel;

	/**
	 * Gets the read excel.
	 *
	 * @return the read excel
	 */
	protected ReadExcel getReadExcel() {
		return readExcel;
	}

	/**
	 * Sets the read excel.
	 *
	 * @param readExcel the new read excel
	 */
	protected void setReadExcel(ReadExcel readExcel) {
		this.readExcel = readExcel;
	}

	
	

	
	
	
}
