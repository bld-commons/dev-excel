package bld.generator.report.comparator;

import java.util.Comparator;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.common.spreadsheet.utils.ValueProps;
import bld.generator.report.csv.CsvHeader;

public class CsvColumnComparator  implements Comparator<CsvHeader> {

	@Override
	public int compare(CsvHeader csv1, CsvHeader csv2) {
		int compare = 0;
		if (csv1.getIndex() == csv2.getIndex()) {
			ValueProps valueProps=(ValueProps) SpreadsheetUtils.getApplicationContext().getBean("valuePropsImpl");
			compare = valueProps.valueProps(csv1.getName()).compareTo(valueProps.valueProps(csv2.getName()));
		}
		else if (csv1.getIndex() > csv2.getIndex())
			compare = 1;
		else
			compare = -1;
		return compare;
	}

}
