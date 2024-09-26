package com.bld.generator.report.csv.query;

import com.bld.generator.report.csv.CsvRow;
import com.bld.generator.report.csv.QueryCsvData;


/**
 * The Interface CsvQueryComponent.
 */
public interface CsvQueryComponent {

	
	/**
	 * Execute query.
	 *
	 * @param <T> the generic type
	 * @param queryCsvData the query csv data
	 * @throws Exception the exception
	 */
	public <T extends CsvRow> void executeQuery(QueryCsvData<T> queryCsvData) throws Exception;

}
