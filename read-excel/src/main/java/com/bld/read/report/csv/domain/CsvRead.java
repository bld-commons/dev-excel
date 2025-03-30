/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.csv.domain;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.bld.read.report.excel.domain.RowSheetRead;
import com.bld.read.report.excel.exception.ExcelReaderException;

import io.micrometer.common.util.StringUtils;

/**
 * The Class CsvRead.
 *
 * @param <T> the generic type
 */
public class CsvRead<T extends RowSheetRead> {

	/** The csv. */
	private InputStream csv;

	/** The list row sheet. */
	private List<T> listRowSheet;

	/**
	 * Instantiates a new csv read.
	 */
	public CsvRead() {
		super();
		this.listRowSheet = new ArrayList<>();
	}

	/**
	 * Gets the csv.
	 *
	 * @return the csv
	 */
	public InputStream getCsv() {
		return csv;
	}

	/**
	 * Sets the csv.
	 *
	 * @param csv the new csv
	 */
	public void setCsv(byte[] csv) {
		if (ArrayUtils.isNotEmpty(csv))
			this.csv = new ByteArrayInputStream(csv);
	}

	/**
	 * Sets the csv.
	 *
	 * @param csv the new csv
	 */
	public void setCsv(InputStream csv) {
		this.csv = csv;
	}

	/**
	 * Sets the csv.
	 *
	 * @param pathFile the new csv
	 */
	public void setCsv(String pathFile) {
		if (StringUtils.isNotBlank(pathFile))
			try {
				this.csv = new FileInputStream(pathFile);
			} catch (FileNotFoundException e) {
				throw new ExcelReaderException(e);
			}
	}

	/**
	 * Close.
	 */
	public void close() {
		if (this.csv != null)
			try {
				this.csv.close();
			} catch (IOException e) {
				throw new ExcelReaderException(e);
			}
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
