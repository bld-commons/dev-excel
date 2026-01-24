/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.ScopeGenerateExcel.java
*/
package com.bld.generator.report.excel;

import java.io.OutputStream;

import com.bld.generator.report.excel.data.ReportExcel;


/**
 * The Interface ScopeGenerateExcel.
 */
public interface ScopeGenerateExcel {

	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createFileXls(ReportExcel report) throws Exception;
	
	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @param outputStream the output stream
	 * @throws Exception the exception
	 */
	public void createFileXls(ReportExcel report,OutputStream outputStream) throws Exception;

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createFileXlsx(ReportExcel report) throws Exception;
	
	/**
	 * Creates the file xlss.
	 *
	 * @param report the report
	 * @param outputStream the output stream
	 * @throws Exception the exception
	 */
	public void createFileXlsx(ReportExcel report,OutputStream outputStream) throws Exception;

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public byte[] createBigDataFileXlsx(ReportExcel report) throws Exception;
	
	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report the report
	 * @param outputStream the output stream
	 * @throws Exception the exception
	 */
	public void createBigDataFileXlsx(ReportExcel report,OutputStream outputStream) throws Exception;
	
	

}
