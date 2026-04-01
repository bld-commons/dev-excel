/*
 * @auth Francesco Baldi
 * @class bld.common.spreadsheet.utils.CsvUtils.java
 */
package com.bld.common.spreadsheet.utils;

import org.apache.commons.csv.CSVFormat;

import com.bld.common.spreadsheet.csv.annotation.CsvSettings;

/**
 * Static utility class that builds an Apache Commons CSV {@link CSVFormat} from
 * a {@link com.bld.common.spreadsheet.csv.annotation.CsvSettings} annotation.
 * <p>
 * Used internally by both the {@code generator-excel} and {@code read-excel} modules
 * to ensure consistent CSV formatting across generation and parsing.
 * All members are {@code static}; this class is not instantiable.
 * </p>
 */
public class CsvUtils {

	/**
	 * Builds a {@link CSVFormat} configured according to the given {@link CsvSettings}
	 * annotation and the provided column headers.
	 * <p>
	 * The following settings are applied: delimiter, quote character, empty-line
	 * handling, whitespace trimming, header names, and whether the header record
	 * should be skipped during parsing.
	 * </p>
	 *
	 * @param csvSettings the annotation carrying the CSV format configuration
	 * @param headers     the ordered column header names to register in the format
	 * @return a configured {@link CSVFormat} instance ready for use with a CSV printer or parser
	 */
	public static CSVFormat getCsvFormat(CsvSettings csvSettings,String... headers) {
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		csvFormat=csvFormat.builder().setDelimiter(csvSettings.delimiter()).setQuote(csvSettings.quoteChar()).setIgnoreEmptyLines(csvSettings.ignoreEmptyLines()).setTrim(csvSettings.trim()).setHeader(headers).setSkipHeaderRecord(csvSettings.skipHeaderRecord()).build();
		return csvFormat;
	}
}
