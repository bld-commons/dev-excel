/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelMultipleDataSource.java
 */
package bld.generator.report.query.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import bld.generator.report.query.SpreadsheetDataSource;
import jakarta.persistence.EntityManager;

/**
 * The Class ExcelMultipleDataSource.<br>
 * ExcelMultipleDataSource works if the "bld.commons.multiple.datasource" property is enabled
 */
@Component
@ConditionalOnProperty(name = SpreadsheetDataSource.MULTIPLE_DATASOURCE, havingValue = "true", matchIfMissing = false)
public class SpreadsheetMultipleDataSource extends SpreadsheetBaseDataSource implements SpreadsheetDataSource {
	
	
	
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
