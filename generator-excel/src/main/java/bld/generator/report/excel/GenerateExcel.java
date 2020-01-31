package bld.generator.report.excel;

import bld.generator.report.excel.impl.ReportExcel;

public interface GenerateExcel {

	
	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception 
	 */
	public byte[] createFileXls(ReportExcel report) throws Exception;

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception 
	 */
	public byte[] createFileXlsx(ReportExcel report) throws Exception;
}
