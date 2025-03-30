/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.csv;

import com.bld.read.report.csv.domain.CsvRead;
import com.bld.read.report.excel.domain.RowSheetRead;

/**
 * The Interface ReadCsv.
 */
public interface ReadCsv {


	
	/**
	 * Convert csv to entity.
	 *
	 * @param <T>     the generic type
	 * @param csvRead the csv read
	 * @param classT  the class T
	 * @return the list
	 * @throws Exception the exception
	 */
	public abstract <T extends RowSheetRead> CsvRead<T>  convertCsvToEntity(CsvRead<T> csvRead,Class<T> classT) throws Exception;

	

	
}
