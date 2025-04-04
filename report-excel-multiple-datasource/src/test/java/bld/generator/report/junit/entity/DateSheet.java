/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.DateSheet.java
*/
package bld.generator.report.junit.entity;

import jakarta.validation.constraints.Size;

import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

/**
 * The Class DateSheet.
 */
@ExcelSheetLayout
@ExcelHeaderLayout
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
