package bld.common.spreadsheet.utils;

import org.apache.commons.csv.CSVFormat;

import bld.common.spreadsheet.csv.annotation.CsvSettings;

public class CsvUtils {

	
	public static CSVFormat getCsvFormat(CsvSettings csvSettings,String... headers) {
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		csvFormat=csvFormat.builder().setDelimiter(csvSettings.delimiter()).setQuote(csvSettings.quoteChar()).setIgnoreEmptyLines(csvSettings.ignoreEmptyLines()).setTrim(csvSettings.trim()).setHeader(headers).setSkipHeaderRecord(csvSettings.skipHeaderRecord()).build();
		return csvFormat;
	}
}
