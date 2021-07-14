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


	
	public abstract <T extends RowSheetRead> List<T>  convertCsvToEntity(CsvRead<T> csvRead,Class<T> classT) throws Exception;

	
	
	public abstract <T extends RowSheetRead> List<T> convertCsvToEntity(CsvRead<T> csvRead, String pathFile,Class<T> classT) throws Exception;
	
}
