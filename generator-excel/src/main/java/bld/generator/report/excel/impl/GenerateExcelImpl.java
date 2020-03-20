/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.impl.GenerateExcelImpl.java
*/
package bld.generator.report.excel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.GenerateExcel;

/**
 * The Class GenerateExcelImpl.
 */
@Component
public class GenerateExcelImpl implements GenerateExcel{
	
	/** The application context. */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	@Override
	public byte[] createFileXls(ReportExcel report) throws Exception {
		ScopeGenerateExcelImpl scopeGenerateExcelImpl=this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
		return scopeGenerateExcelImpl.createFileXls(report);
	}

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	@Override
	public byte[] createFileXlsx(ReportExcel report) throws Exception {
		ScopeGenerateExcelImpl scopeGenerateExcelImpl=this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
		return scopeGenerateExcelImpl.createFileXlsx(report);
	}
	
	
}
