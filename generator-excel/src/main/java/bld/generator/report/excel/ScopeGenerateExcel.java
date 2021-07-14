/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.ScopeGenerateExcel.java
*/
package bld.generator.report.excel;

import bld.generator.report.excel.data.ReportExcel;

// TODO: Auto-generated Javadoc
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
