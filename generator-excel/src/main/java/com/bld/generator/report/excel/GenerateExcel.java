/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.GenerateExcel.java
*/
package com.bld.generator.report.excel;

import java.io.OutputStream;

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
	 * 
	 */
	public byte[] createFileXls(ReportExcel report);

	/**
	 * Creates the file xls.
	 *
	 * @param report       the report
	 * @param outputStream the output stream
	 * 
	 */
	public void createFileXls(ReportExcel report, OutputStream outputStream);

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * 
	 */
	public byte[] createFileXlsx(ReportExcel report);

	/**
	 * Creates the file xlsx.
	 *
	 * @param report       the report
	 * @param outputStream the output stream
	 * 
	 */
	public void createFileXlsx(ReportExcel report, OutputStream outputStream);

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * 
	 */
	public byte[] createBigDataFileXlsx(ReportExcel report);

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report       the report
	 * @param outputStream the output stream
	 * 
	 */
	public void createBigDataFileXlsx(ReportExcel report, OutputStream outputStream);

}
