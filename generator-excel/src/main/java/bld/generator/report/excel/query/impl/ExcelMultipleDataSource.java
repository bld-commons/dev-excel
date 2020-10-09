package bld.generator.report.excel.query.impl;

import javax.persistence.EntityManager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.query.ExcelDataSource;
@Component
@ConditionalOnProperty(name = ExcelDataSource.MULTIPLE_DATASOURCE, havingValue = "true", matchIfMissing = false)
public class ExcelMultipleDataSource extends ExcelBaseDataSource implements ExcelDataSource {
	
	
	
	@Override
	public EntityManager getEntityManager(String unitName) {
		EntityManager entityManager=(EntityManager)this.applicationContext.getBean(unitName);
		return entityManager;
	}

}
