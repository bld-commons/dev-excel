/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.GenerateExcel.java
*/
package com.bld.generator.report.excel;

import com.bld.generator.report.excel.data.ReportExcel;

/**
 * The Interface GenerateExcel. <br>
 * GemerateExcel is an interface for generating Excel reports.
 */
public interface GenerateExcel {

	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createFileXls(ReportExcel report) throws Exception;

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createFileXlsx(ReportExcel report) throws Exception;

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createBigDataFileXlsx(ReportExcel report) throws Exception;
	
	
	
	
}
