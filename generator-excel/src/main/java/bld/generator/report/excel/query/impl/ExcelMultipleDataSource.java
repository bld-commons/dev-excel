/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelMultipleDataSource.java
 */
package bld.generator.report.excel.query.impl;

import javax.persistence.EntityManager;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.query.ExcelDataSource;

/**
 * The Class ExcelMultipleDataSource.
 */
@Component
@ConditionalOnProperty(name = ExcelDataSource.MULTIPLE_DATASOURCE, havingValue = "true", matchIfMissing = false)
public class ExcelMultipleDataSource extends ExcelBaseDataSource implements ExcelDataSource {
	
	
	
	/**
	 * Gets the entity manager.
	 *
	 * @param unitName the unit name
	 * @return the entity manager
	 */
	@Override
	public EntityManager getEntityManager(String unitName) {
		EntityManager entityManager=(EntityManager)this.applicationContext.getBean(unitName);
		return entityManager;
	}

}
