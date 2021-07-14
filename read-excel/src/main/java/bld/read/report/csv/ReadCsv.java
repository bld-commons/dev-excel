/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.csv;

import java.util.List;

import bld.read.report.csv.domain.CsvRead;
import bld.read.report.excel.domain.RowSheetRead;

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
	public abstract <T extends RowSheetRead> List<T>  convertCsvToEntity(CsvRead<T> csvRead,Class<T> classT) throws Exception;

	
	
	/**
	 * Convert csv to entity.
	 *
	 * @param <T>      the generic type
	 * @param csvRead  the csv read
	 * @param pathFile the path file
	 * @param classT   the class T
	 * @return the list
	 * @throws Exception the exception
	 */
	public abstract <T extends RowSheetRead> List<T> convertCsvToEntity(CsvRead<T> csvRead, String pathFile,Class<T> classT) throws Exception;
	
}
