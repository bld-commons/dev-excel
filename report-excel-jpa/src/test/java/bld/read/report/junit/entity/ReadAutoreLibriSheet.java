/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.read.report.junit.entity.ReadAutoreLibriSheet.java
*/
package bld.read.report.junit.entity;

import com.bld.read.report.excel.annotation.ExcelReadSheet;
import com.bld.read.report.excel.domain.SheetRead;

/**
 * The Class ReadAutoreLibriSheet.
 */
@ExcelReadSheet(startRow=2)
public class ReadAutoreLibriSheet extends SheetRead<ReadAutoreLibriRow>{

	public ReadAutoreLibriSheet(String sheetName) {
		super(sheetName);
	}

	
}
