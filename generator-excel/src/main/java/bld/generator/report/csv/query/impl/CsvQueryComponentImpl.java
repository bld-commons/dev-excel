/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelQueryComponentImpl.java
 */
package bld.generator.report.csv.query.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.csv.CsvRow;
import bld.generator.report.csv.QueryCsvData;
import bld.generator.report.csv.annotation.CsvQuery;
import bld.generator.report.csv.query.CsvQueryComponent;
import bld.generator.report.excel.config.EnableExcelGeneratorConfiguration;
import bld.generator.report.query.SpreadsheetDataSource;
import bld.generator.report.query.impl.SpreadsheetQueryComponentImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


@Transactional
@Component
@ConditionalOnExpression(value = "${"+SpreadsheetDataSource.MULTIPLE_DATASOURCE+":false} or !T(org.springframework.util.StringUtils).isEmpty('${"+EnableExcelGeneratorConfiguration.SPRING_DATASOURCE_URL+":}')")
public class CsvQueryComponentImpl extends SpreadsheetQueryComponentImpl implements CsvQueryComponent {



	/** The multiple datasource. */
	@Value("${" + SpreadsheetDataSource.MULTIPLE_DATASOURCE + ":false}")
	private boolean multipleDatasource;

	
	@Override
	public <T extends CsvRow> void executeQuery(QueryCsvData<T> queryCsvData) throws Exception {
		if (CollectionUtils.isEmpty(queryCsvData.getRows())) {
			CsvQuery csvQuery = SpreadsheetUtils.getAnnotation(queryCsvData.getClass(), CsvQuery.class);
			queryCsvData.setRows(csvQuery.nativeQuery() ? nativeQuery(queryCsvData, csvQuery) : jpaQuery(queryCsvData, csvQuery));
		}

	}

	
	private <T extends CsvRow> List<T> nativeQuery(QueryCsvData<T> queryCsvData, CsvQuery csvQuery) throws Exception {
		EntityManager entityManager = this.spreadsheetDataSource.getEntityManager(csvQuery.entityManager());
		return super.nativeQuery(queryCsvData, entityManager, csvQuery.value());
	}



	/**
	 * Gets the result list query.
	 *
	 * @param <T>            the generic type
	 * @param queryCsvData the query sheet data
	 * @param csvQuery     the excel query
	 * @return the result list query
	 */
	private <T extends CsvRow> List<T> jpaQuery(QueryCsvData<T> queryCsvData, CsvQuery csvQuery) {
		EntityManager entityManager = this.spreadsheetDataSource.getEntityManager(csvQuery.entityManager());
		TypedQuery<T> query = entityManager.createQuery(csvQuery.value(), queryCsvData.getRowClass());
		setParameters(queryCsvData, query);
		List<T> result = query.getResultList();
		return result;
	}



}
