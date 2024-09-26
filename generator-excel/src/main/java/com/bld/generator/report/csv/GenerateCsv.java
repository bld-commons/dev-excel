/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.csv.GenerateCsv.java
 */
package com.bld.generator.report.csv;


/**
 * The Interface GenerateCsv.
 */
public interface GenerateCsv {

	/**
	 * Generate csv.
	 *
	 * @param <T> the generic type
	 * @param csvSheet the csv sheet
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	public <T extends CsvRow> byte[] generateCsv(CsvData<T> csvSheet) throws Exception;
	
}
