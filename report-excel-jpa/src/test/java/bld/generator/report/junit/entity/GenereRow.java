/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.GenereRow.java
*/
package bld.generator.report.junit.entity;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;

/**
 * The Class GenereRow.
 */
public class GenereRow implements RowSheet {

	/** The genere. */
	@ExcelColumn(index = 0, name = "Genere")
	@ExcelCellLayout
	private String genere;

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	

	
	
}
