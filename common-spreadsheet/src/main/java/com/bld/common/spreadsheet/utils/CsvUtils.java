/*
 * @auth Francesco Baldi
 * @class bld.common.spreadsheet.utils.CsvUtils.java
 */
package com.bld.common.spreadsheet.utils;

import org.apache.commons.csv.CSVFormat;

import com.bld.common.spreadsheet.csv.annotation.CsvSettings;

/**
 * The Class CsvUtils.
 */
public class CsvUtils {

	
	/**
	 * Gets the csv format.
	 *
	 * @param csvSettings the csv settings
	 * @param headers the headers
	 * @return the csv format
	 */
	public static CSVFormat getCsvFormat(CsvSettings csvSettings,String... headers) {
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		csvFormat=csvFormat.builder().setDelimiter(csvSettings.delimiter()).setQuote(csvSettings.quoteChar()).setIgnoreEmptyLines(csvSettings.ignoreEmptyLines()).setTrim(csvSettings.trim()).setHeader(headers).setSkipHeaderRecord(csvSettings.skipHeaderRecord()).build();
		return csvFormat;
	}
}
