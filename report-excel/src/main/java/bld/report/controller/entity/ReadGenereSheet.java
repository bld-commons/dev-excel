/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.read.report.junit.entity.ReadGenereSheet.java
*/
package bld.report.controller.entity;

import bld.read.report.excel.annotation.ExcelReadSheet;
import bld.read.report.excel.domain.SheetRead;


/**
 * The Class ReadGenereSheet.
 */
@ExcelReadSheet
public class ReadGenereSheet extends SheetRead<ReadGenereRow> {

	public ReadGenereSheet(String sheetName) {
		super(sheetName);
	}

	

}
