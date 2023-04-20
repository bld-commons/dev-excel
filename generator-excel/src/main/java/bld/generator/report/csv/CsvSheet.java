package bld.generator.report.csv;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;

public class CsvSheet<T extends RowCsv> {

	private List<T> rows;

	private CSVFormat format;
	
	private Class<T> rowClass;

	public CsvSheet(Class<T> rowClass) {
		super();
		this.rows=new ArrayList<>();
		this.format=CSVFormat.DEFAULT;
		this.rowClass=rowClass;
	}

	public CSVFormat getFormat() {
		return format;
	}

	public void setFormat(CSVFormat format) {
		this.format = format;
	}

	public List<T> getRows() {
		return rows;
	}

	public Class<T> getRowClass() {
		return rowClass;
	}

}
