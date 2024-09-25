package com.bld.generator.report.comparator;

import java.util.Comparator;

import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.csv.CsvHeader;

public class CsvColumnComparator implements Comparator<CsvHeader> {

	private ValueProps valueProps;

	public CsvColumnComparator(ValueProps valueProps) {
		super();
		this.valueProps = valueProps;
	}

	@Override
	public int compare(CsvHeader csv1, CsvHeader csv2) {
		int compare = 0;
		if (csv1.getIndex() == csv2.getIndex())
			compare = this.valueProps.valueProps(csv1.getName()).compareTo(valueProps.valueProps(csv2.getName()));
		else if (csv1.getIndex() > csv2.getIndex())
			compare = 1;
		else
			compare = -1;
		return compare;
	}

}
