/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelQueryComponentImpl.java
 */
package bld.generator.report.excel.query.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.query.ExcelDataSource;
import bld.generator.report.excel.query.ExcelQueryComponent;
import bld.generator.report.utils.ExcelUtils;

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
public class ExcelQueryComponentImpl implements ExcelQueryComponent {

//	/** The entity manager. */
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	/** The named parameter jdbc template. */
//	@Autowired
//	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired(required = false)
	private ExcelDataSource excelDataSource;

	@Value("${" + ExcelDataSource.MULTIPLE_DATASOURCE + ":false}")
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
			ExcelQuery excelQuery = ExcelUtils.getAnnotation(querySheetData.getClass(), ExcelQuery.class);
			querySheetData.setListRowSheet(excelQuery.nativeQuery() ? getResultListNativeQuery(querySheetData, excelQuery) : getResultListQuery(querySheetData, excelQuery));
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
	private <T extends RowSheet> List<T> getResultListNativeQuery(QuerySheetData<T> querySheetData, ExcelQuery excelQuery) throws Exception {
		if (!this.multipleDatasource) {
			EntityManager entityManager = this.excelDataSource.getEntityManager(excelQuery.entityManager());
			entityManager.flush();
		}
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = this.excelDataSource.getNamedParameterJdbcTemplate(excelQuery.namedParameterJdbcTemplate());
		List<Map<String, Object>> listResult = namedParameterJdbcTemplate.queryForList(excelQuery.select(), querySheetData.getMapParameters());
		List<T> listT = new ArrayList<T>();
		for (Map<String, Object> mapResult : listResult) {
			T t = querySheetData.getRowClass().newInstance();
			reflection(t, mapResult);
			listT.add(t);
		}
		return listT;
	}

	/**
	 * Reflection.
	 *
	 * @param <T>       the generic type
	 * @param t         the t
	 * @param mapResult the map result
	 * @throws Exception the exception
	 */
	private <T extends RowSheet> void reflection(T t, Map<String, Object> mapResult) throws Exception {
		Map<String, Object> mapResultApp = new HashMap<>();
		for (String keyResult : mapResult.keySet()) {
			String nameField = ExcelUtils.getNameParameter(keyResult);
			mapResultApp.put(nameField, mapResult.get(keyResult));
		}
		BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
		Converter converter = new DateConverter();
		beanUtilsBean.getConvertUtils().register(converter, Date.class);
		converter = new CalendarConverter(null);
		beanUtilsBean.getConvertUtils().register(converter, Calendar.class);
		BeanUtils.copyProperties(t, mapResultApp);
	}

	/**
	 * Gets the result list query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @param excelQuery     the excel query
	 * @return the result list query
	 */
	private <T extends RowSheet> List<T> getResultListQuery(QuerySheetData<T> querySheetData, ExcelQuery excelQuery) {
		EntityManager entityManager = this.excelDataSource.getEntityManager(excelQuery.entityManager());
		TypedQuery<T> query = entityManager.createQuery(excelQuery.select(), querySheetData.getRowClass());
		if (querySheetData.getMapParameters() != null) {
			for (String key : querySheetData.getMapParameters().keySet())
				query.setParameter(key, querySheetData.getMapParameters().get(key));
		}
		List<T> result = query.getResultList();
		return result;
	}

}
