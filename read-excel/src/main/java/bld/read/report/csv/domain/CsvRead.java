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
 */
public class CsvRead<T extends RowSheetRead> {

	/** The csv. */
	private byte[] csv;
	
	private List<T>listRowSheet;
	
	

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

	public List<T> getListRowSheet() {
		return listRowSheet;
	}
	
	
	
}
