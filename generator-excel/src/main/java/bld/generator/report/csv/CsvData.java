package bld.generator.report.csv;

import java.util.ArrayList;
import java.util.List;

import bld.common.spreadsheet.utils.ExcelUtils;

public abstract class CsvData<T extends CsvRow> {

	private List<T> rows;

	private Class<T> rowClass;

	public CsvData() {
		super();
		this.rows=new ArrayList<>();
		this.rowClass=ExcelUtils.getTClass(this);
	}

	public List<T> getRows() {
		return rows;
	}

	public Class<T> getRowClass() {
		return rowClass;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
