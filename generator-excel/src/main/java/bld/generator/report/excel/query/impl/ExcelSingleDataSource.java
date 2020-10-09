package bld.generator.report.excel.query.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.query.ExcelDataSource;

@Component
@ConditionalOnProperty(name = ExcelDataSource.MULTIPLE_DATASOURCE, havingValue = "false", matchIfMissing = true)
public class ExcelSingleDataSource extends ExcelBaseDataSource implements ExcelDataSource {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	@Override
	public EntityManager getEntityManager(String unitName) {
		return this.entityManager;
	}

}
