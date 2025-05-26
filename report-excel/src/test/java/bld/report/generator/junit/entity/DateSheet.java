/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.DateSheet.java
*/
package bld.report.generator.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLocked;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

/**
 * The Class DateSheet.
 */
@ExcelSheetLayout(landscape = false,locked = @ExcelLocked("abc"))
@ExcelHeaderLayout(excelHeaderCellLayout = @ExcelHeaderCellLayout(locked = true))
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class DateSheet extends SheetData<DateRow> {

	/**
	 * Instantiates a new date sheet.
	 *
	 * @param nameSheet the name sheet
	 */
	public DateSheet(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}


}
