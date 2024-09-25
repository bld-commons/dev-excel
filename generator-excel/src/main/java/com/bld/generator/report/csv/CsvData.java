package com.bld.generator.report.csv;

import java.util.ArrayList;
import java.util.List;

import com.bld.common.spreadsheet.utils.ExcelUtils;

/**
 * The Class CsvData.
 *
 * @param <T> the generic type
 */
public abstract class CsvData<T extends CsvRow> {

	/** The rows. */
	private List<T> rows;

	/** The row class. */
	private Class<T> rowClass;

	/**
	 * Instantiates a new csv data.
	 */
	public CsvData() {
		super();
		this.rows=new ArrayList<>();
		this.rowClass=ExcelUtils.getTClass(this);
	}

	/**
	 * Gets the rows.
	 *
	 * @return the rows
	 */
	public List<T> getRows() {
		return rows;
	}

	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	public Class<T> getRowClass() {
		return rowClass;
	}

	/**
	 * Sets the rows.
	 *
	 * @param rows the new rows
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
