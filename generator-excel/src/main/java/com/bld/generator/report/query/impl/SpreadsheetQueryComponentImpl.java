package com.bld.generator.report.query.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.CalendarConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;

import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.generator.report.QuerySpreadsheetData;
import com.bld.generator.report.query.SpreadsheetDataSource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

public abstract class SpreadsheetQueryComponentImpl {
	
	/** The excel data source. */
	@Autowired(required = false)
	protected SpreadsheetDataSource spreadsheetDataSource;

	protected <T> void setParameters(QuerySpreadsheetData<T> querySpreadsheetData, Query query) {
		if (querySpreadsheetData.getMapParameters() != null) {
			for (String key : querySpreadsheetData.getMapParameters().keySet())
				query.setParameter(key, querySpreadsheetData.getMapParameters().get(key));
		}
	}

	protected <T> void reflection(T t, Map<String, Object> mapResult) throws Exception {
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
		beanUtilsBean.copyProperties(t, mapResultApp);

	}
	
	@SuppressWarnings("unchecked")
	protected <T> List<T>  nativeQuery(QuerySpreadsheetData<T> querySpreadsheetData,EntityManager entityManager,String sql) throws Exception {
		Query query = entityManager.createNativeQuery(sql, Tuple.class);
		this.setParameters(querySpreadsheetData, query);
		List<Tuple> results = query.getResultList();
		List<T> listT = new ArrayList<T>();
		for (Tuple row : results) {
			List<TupleElement<?>> elements = row.getElements();
			T t = null;
			try {
				t = querySpreadsheetData.getRowClass().getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			Map<String, Object> mapRow = new HashMap<>();
			for (TupleElement<?> element : elements) {
				Object value = row.get(element.getAlias());
				if (value != null)
					mapRow.put(element.getAlias(), value);
			}
			reflection(t, mapRow);
			listT.add(t);
		}
		
		
		return listT;
	}
}
