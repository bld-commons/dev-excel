package com.bld.generator.report.query.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.DefaultConversionService;

import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.generator.report.QuerySpreadsheetData;
import com.bld.generator.report.query.SpreadsheetDataSource;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

public abstract class SpreadsheetQueryComponentImpl {

	private static final Map<String, String> ALIAS_TO_FIELD_CACHE = new ConcurrentHashMap<>();

	private static final ClassValue<Constructor<?>> CTOR_CACHE = new ClassValue<>() {
		@Override
		protected Constructor<?> computeValue(Class<?> type) {
			try {
				Constructor<?> k = type.getDeclaredConstructor();
				k.setAccessible(true);
				return k;
			} catch (NoSuchMethodException ex) {
				throw new RuntimeException(ex);
			}
		}
	};

	private static final ClassValue<Set<String>> WRITABLE_PROPS_CACHE = new ClassValue<>() {
		@Override
		protected Set<String> computeValue(Class<?> type) {
			BeanWrapper bw = new BeanWrapperImpl(type);
			Set<String> s = new HashSet<>();
			for (PropertyDescriptor pd : bw.getPropertyDescriptors())
				if (bw.isWritableProperty(pd.getName()))
					s.add(pd.getName());
			return Set.copyOf(s);
		}
	};

	/** The excel data source. */
	@Autowired(required = false)
	protected SpreadsheetDataSource spreadsheetDataSource;

	protected <T> void setParameters(QuerySpreadsheetData<T> querySpreadsheetData, Query query) {
		Map<String, Object> params = querySpreadsheetData.getMapParameters();
		if (params != null) {
			for (Map.Entry<String, Object> e : params.entrySet())
				query.setParameter(e.getKey(), e.getValue());
		}
	}

	protected <T> void reflection(T t, Map<String, Object> mapResult) throws Exception {
		Set<String> writable = WRITABLE_PROPS_CACHE.get(t.getClass());
		BeanWrapper wrapper = new BeanWrapperImpl(t);
		wrapper.setConversionService(DefaultConversionService.getSharedInstance());
		for (Map.Entry<String, Object> entry : mapResult.entrySet()) {
			String field = ALIAS_TO_FIELD_CACHE.computeIfAbsent(entry.getKey(), ExcelUtils::getNameParameter);
			if (writable.contains(field))
				wrapper.setPropertyValue(field, entry.getValue());
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> nativeQuery(QuerySpreadsheetData<T> querySpreadsheetData, EntityManager entityManager, String sql) throws Exception {
		Query query = entityManager.createNativeQuery(sql, Tuple.class);
		this.setParameters(querySpreadsheetData, query);
		List<Tuple> results = query.getResultList();
		List<T> listT = new ArrayList<>(results.size());
		if (results.isEmpty())
			return listT;

		Class<T> rowClass = querySpreadsheetData.getRowClass();
		Constructor<T> ctor = (Constructor<T>) CTOR_CACHE.get(rowClass);
		Set<String> writable = WRITABLE_PROPS_CACHE.get(rowClass);

		List<TupleElement<?>> elements = results.get(0).getElements();
		int n = elements.size();
		String[] aliases = new String[n];
		String[] fields = new String[n];
		for (int i = 0; i < n; i++) {
			String alias = elements.get(i).getAlias();
			aliases[i] = alias;
			fields[i] = ALIAS_TO_FIELD_CACHE.computeIfAbsent(alias, ExcelUtils::getNameParameter);
		}

		for (Tuple row : results) {
			T t;
			try {
				t = ctor.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			BeanWrapper wrapper = new BeanWrapperImpl(t);
			wrapper.setConversionService(DefaultConversionService.getSharedInstance());
			for (int i = 0; i < n; i++) {
				Object value = row.get(aliases[i]);
				if (value != null && writable.contains(fields[i]))
					wrapper.setPropertyValue(fields[i], value);
			}
			listT.add(t);
		}

		return listT;
	}
}
