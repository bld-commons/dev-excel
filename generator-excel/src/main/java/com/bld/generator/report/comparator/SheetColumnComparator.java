/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.comparator.SheetColumnComparator.java
*/
package com.bld.generator.report.comparator;

import java.util.Comparator;

import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.excel.data.SheetHeader;

/**
 * The Class SheetColumnComparator.<br>
 * SheetColumnComparator orders the columns on the table.<br>
 * Sorting is relative to the index and column name.<br>
 */
public class SheetColumnComparator implements Comparator<SheetHeader> {

	private ValueProps valueProps;
	
	public SheetColumnComparator(ValueProps valueProps) {
		super();
		this.valueProps = valueProps;
	}

	/**
	 * Compare.
	 *
	 * @param sheetHeader1 the sheet header 1
	 * @param sheetHeader2 the sheet header 2
	 * @return the int
	 */
	@Override
	public int compare(SheetHeader sheetHeader1, SheetHeader sheetHeader2) {
		int compare = 0;
		if (sheetHeader1.excelColumn().index() == sheetHeader2.excelColumn().index()) 
			compare = valueProps.valueProps(sheetHeader1.excelColumn().name()).compareTo(valueProps.valueProps(sheetHeader2.excelColumn().name()));
		else if (sheetHeader1.excelColumn().index() > sheetHeader2.excelColumn().index())
			compare = 1;
		else
			compare = -1;
		return compare;
	}

}
