/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.csv.domain;

import java.util.ArrayList;
import java.util.List;

import bld.read.report.excel.domain.RowSheetRead;

/**
 * The Class CsvRead.
 *
 * @param <T> the generic type
 */
public class CsvRead<T extends RowSheetRead> {

	/** The csv. */
	private byte[] csv;
	
	/** The list row sheet. */
	private List<T>listRowSheet;
	
	

	/**
	 * Instantiates a new csv read.
	 */
	public CsvRead() {
		super();
		this.listRowSheet=new ArrayList<>();
	}

	/**
	 * Gets the csv.
	 *
	 * @return the csv
	 */
	public byte[] getCsv() {
		return csv;
	}

	/**
	 * Sets the csv.
	 *
	 * @param csv the new csv
	 */
	public void setCsv(byte[] csv) {
		this.csv = csv;
	}

	/**
	 * Gets the list row sheet.
	 *
	 * @return the list row sheet
	 */
	public List<T> getListRowSheet() {
		return listRowSheet;
	}
	
	
	
}
