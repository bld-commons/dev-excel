/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.GenereSheet.java
*/
package bld.generator.report.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.QuerySheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelQuery;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

import bld.generator.report.config.Db1DatabaseConfiguration;


/**
 * The Class GenereSheet.
 */
@ExcelHeaderLayout
@ExcelSheetLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
@ExcelQuery(value = "select des_genere as genere from genere",entityManager = Db1DatabaseConfiguration.DB1_ENTITY_MANAGER)
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
