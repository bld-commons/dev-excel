/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.comparator.SheetColumnComparator.java
*/
package bld.generator.report.excel.comparator;

import java.util.Comparator;

import bld.generator.report.excel.data.SheetHeader;
import bld.generator.report.utils.ExcelUtils;
import bld.generator.report.utils.ValueProps;

/**
 * The Class SheetColumnComparator.
 */
public class SheetColumnComparator implements Comparator<SheetHeader> {

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
		if (sheetHeader1.excelColumn().indexColumn() == sheetHeader2.excelColumn().indexColumn()) {
			ValueProps valueProps=(ValueProps) ExcelUtils.getApplicationContext().getBean("valuePropsImpl");
			compare = valueProps.valueProps(sheetHeader1.excelColumn().columnName()).compareTo(valueProps.valueProps(sheetHeader2.excelColumn().columnName()));
		}
		else if (sheetHeader1.excelColumn().indexColumn() > sheetHeader2.excelColumn().indexColumn())
			compare = 1;
		else
			compare = -1;
		return compare;
	}

}
