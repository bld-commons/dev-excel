/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelQueryComponentImpl.java
 */
package bld.generator.report.excel.query.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.SheetDynamicData;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.query.ExcelQueryComponent;
import bld.generator.report.utils.ExcelUtils;

/**
 * The Class ExcelQueryComponentImpl.
 */
@Transactional
public class ExcelQueryComponentImpl implements ExcelQueryComponent {



	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * Execute query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @throws Exception the exception
	 */
	@Override
	public <T extends RowSheet> void executeQuery(QuerySheetData<T> querySheetData) throws Exception {

		if (querySheetData instanceof SheetDynamicData)
			throw new Exception("The interface QuerySheetData can not be used on SheetDynamicData classes");
		if(CollectionUtils.isEmpty(querySheetData.getListRowSheet())) {
			ExcelQuery excelQuery = ExcelUtils.getAnnotation(querySheetData.getClass(), ExcelQuery.class);
			querySheetData.setListRowSheet(excelQuery.nativeQuery() ? getResultListNativeQuery(querySheetData, excelQuery.select()) : getResultListQuery(querySheetData, excelQuery.select()));
		}

	}

	/**
	 * Gets the result list native query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @param select         the select
	 * @return the result list native query
	 * @throws Exception the exception
	 */
	private <T extends RowSheet> List<T> getResultListNativeQuery(QuerySheetData<T> querySheetData, String select) throws Exception {
		this.entityManager.flush();
		List<Map<String, Object>> listResult = this.namedParameterJdbcTemplate.queryForList(select, querySheetData.getMapParameters());
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
	 */
	private <T extends RowSheet> void reflection(T t, Map<String, Object> mapResult) {
		Map<String,Object>mapResultApp=new HashMap<>();
		for (String keyResult : mapResult.keySet()) {
			String nameField = keyResult.replace("_", " ");
			nameField=(WordUtils.capitalizeFully(nameField)).replace(" ", "");
			nameField = ("" + nameField.charAt(0)).toLowerCase() + nameField.substring(1);
			mapResultApp.put(nameField, mapResult.get(keyResult));
		}
		
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(t);
		wrapper.setAutoGrowNestedPaths(true);
		wrapper.setPropertyValues(mapResultApp);
	}

	/**
	 * Gets the result list query.
	 *
	 * @param <T>            the generic type
	 * @param querySheetData the query sheet data
	 * @param select         the select
	 * @return the result list query
	 */
	private <T extends RowSheet> List<T> getResultListQuery(QuerySheetData<T> querySheetData, String select) {
		TypedQuery<T> query = this.entityManager.createQuery(select, querySheetData.getRowClass());
		if (querySheetData.getMapParameters() != null) {
			for (String key : querySheetData.getMapParameters().keySet())
				query.setParameter(key, querySheetData.getMapParameters().get(key));
		}
		List<T> result = query.getResultList();
		return result;
	}

}
