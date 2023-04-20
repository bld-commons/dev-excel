package bld.generator.report.comparator;

import java.util.Comparator;

import bld.generator.report.csv.CsvHeader;
import bld.generator.report.utils.ExcelUtils;
import bld.generator.report.utils.ValueProps;

public class CsvColumnComparator  implements Comparator<CsvHeader> {

	@Override
	public int compare(CsvHeader csv1, CsvHeader csv2) {
		int compare = 0;
		if (csv1.getIndex() == csv2.getIndex()) {
			ValueProps valueProps=(ValueProps) ExcelUtils.getApplicationContext().getBean("valuePropsImpl");
			compare = valueProps.valueProps(csv1.getName()).compareTo(valueProps.valueProps(csv2.getName()));
		}
		else if (csv1.getIndex() > csv2.getIndex())
			compare = 1;
		else
			compare = -1;
		return compare;
	}

}
