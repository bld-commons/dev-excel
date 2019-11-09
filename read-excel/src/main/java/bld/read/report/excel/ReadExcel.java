/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel;

import bld.read.report.excel.domain.ExcelRead;

/**
 * The Interface ReadExcel.
 */
public interface ReadExcel {


	/**
	 * Convert excel to entity.
	 *
	 * @param <T>    the generic type
	 * @param entity the entity
	 * @return the t
	 * @throws Exception the exception
	 */
	public abstract <T extends ExcelRead> T convertExcelToEntity(T entity) throws Exception;

	/**
	 * Convert excel to entity.
	 *
	 * @param <T>      the generic type
	 * @param entity   the entity
	 * @param pathFile the path file
	 * @return the t
	 * @throws Exception the exception
	 */
	public abstract <T extends ExcelRead> T convertExcelToEntity(T entity, String pathFile) throws Exception;

}