/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelQueryComponentImpl.java
 */
package bld.generator.report.excel.query.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.config.EnableExcelGeneratorConfiguration;
import bld.generator.report.excel.query.ExcelQueryComponent;
import bld.generator.report.query.SpreadsheetDataSource;
import bld.generator.report.query.impl.SpreadsheetQueryComponentImpl;

/**
 * The Class ExcelQueryComponentImpl.<br>
 * ExcelQueryComponentImpl is the class that manages the
 * {@link bld.generator.report.excel.QuerySheetData} classes and the annotation
 * {@link bld.generator.report.excel.annotation.ExcelQuery}. <br>
 * It gets the {@link bld.generator.report.excel.RowSheet} list through the
 * query within the annotation
 * {@link bld.generator.report.excel.annotation.ExcelQuery}.<br>
 */
@Transactional
@Component
@ConditionalOnExpression(value = "${"+SpreadsheetDataSource.MULTIPLE_DATASOURCE+":false} or !T(org.springframework.util.StringUtils).isEmpty('${"+EnableExcelGeneratorConfiguration.SPRING_DATASOURCE_URL+":}')")
public class ExcelQueryComponentImpl extends SpreadsheetQueryComponentImpl implements ExcelQueryComponent {

	private final static Logger logger=LoggerFactory.getLogger(ExcelQueryComponentImpl.class);

	/** The multiple datasource. */
	@Value("${" + SpreadsheetDataSource.MULTIPLE_DATASOURCE + ":false}")
	private boolean multipleDatasource;

	/**
	 * Execute query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @throws Exception the exception
	 */
	@Override
	public <T extends RowSheet> void executeQuery(QuerySheetData<T> querySheetData) throws Exception {
		if (CollectionUtils.isEmpty(querySheetData.getListRowSheet())) {
			Date start=new Date();
			ExcelQuery excelQuery = SpreadsheetUtils.getAnnotation(querySheetData.getClass(), ExcelQuery.class);
			querySheetData.setListRowSheet(excelQuery.nativeQuery() ? nativeQuery(querySheetData, excelQuery) : jpaQuery(querySheetData, excelQuery));
			Date end=new Date();
			logger.info("Time query: "+(((double)(end.getTime()-start.getTime()))/1000)+"s");
		}

	}

	/**
	 * Gets the result list native query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @param excelQuery     the excel query
	 * @return the result list native query
	 * @throws Exception the exception
	 */
	private <T extends RowSheet> List<T> nativeQuery(QuerySheetData<T> querySheetData, ExcelQuery excelQuery) throws Exception {
		EntityManager entityManager = this.spreadsheetDataSource.getEntityManager(excelQuery.entityManager());
		return super.nativeQuery(querySheetData, entityManager, excelQuery.value());
	}



	/**
	 * Gets the result list query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @param excelQuery     the excel query
	 * @return the result list query
	 */
	private <T extends RowSheet> List<T> jpaQuery(QuerySheetData<T> querySheetData, ExcelQuery excelQuery) {
		EntityManager entityManager = this.spreadsheetDataSource.getEntityManager(excelQuery.entityManager());
		TypedQuery<T> query = entityManager.createQuery(excelQuery.value(), querySheetData.getRowClass());
		setParameters(querySheetData, query);
		List<T> result = query.getResultList();
		return result;
	}



}
