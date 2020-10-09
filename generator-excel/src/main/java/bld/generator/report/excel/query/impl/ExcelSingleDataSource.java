/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelSingleDataSource.java
 */
package bld.generator.report.excel.query.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.query.ExcelDataSource;

/**
 * The Class ExcelSingleDataSource.
 */
@Component
@ConditionalOnProperty(name = ExcelDataSource.MULTIPLE_DATASOURCE, havingValue = "false", matchIfMissing = true)
public class ExcelSingleDataSource extends ExcelBaseDataSource implements ExcelDataSource {

	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	/**
	 * Gets the entity manager.
	 *
	 * @param unitName the unit name
	 * @return the entity manager
	 */
	@Override
	public EntityManager getEntityManager(String unitName) {
		return this.entityManager;
	}

}
