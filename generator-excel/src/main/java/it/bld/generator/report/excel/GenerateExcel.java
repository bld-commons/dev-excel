/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel;

import it.bld.generator.report.excel.impl.ReportExcel;

// TODO: Auto-generated Javadoc
/**
 * The Interface GenerateExcel.
 */
public interface GenerateExcel {

	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 */
	public byte[] createFileXls(ReportExcel report);

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 */
	public byte[] createFileXlsx(ReportExcel report);

}
