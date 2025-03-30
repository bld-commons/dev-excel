/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel;

import com.bld.read.report.excel.domain.ExcelRead;

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



}