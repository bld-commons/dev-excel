/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.GenereSheet.java
*/
package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.config.Db1DatabaseConfiguration;
import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;


/**
 * The Class GenereSheet.
 */
@ExcelHeaderLayout
@ExcelSheetLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
@ExcelQuery(select = "select des_genere as genere from genere",namedParameterJdbcTemplate = Db1DatabaseConfiguration.JDBC_TEMPLATE,entityManager = Db1DatabaseConfiguration.DB1_ENTITY_MANAGER)
public class GenereSheet extends QuerySheetData<GenereRow>{
	
	

	/**
	 * Instantiates a new genere sheet.
	 *
	 * @param nameSheet the name sheet
	 */
	public GenereSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}


	

	
}
