/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelSingleDataSource.java
 */
package bld.generator.report.query.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.config.EnableExcelGeneratorConfiguration;
import bld.generator.report.query.SpreadsheetDataSource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * The Class ExcelSingleDataSource.<br>
 * ExcelSingleDataSource works if the following properties match this conditions:
 * <ul>
 * <li>spring.datasource.url - it is defined</li>
 * <li>bld.commons.multiple.datasource - it is disabled or missing</li>
 * </ul>
 */
@Component
@ConditionalOnExpression(value = "!${"+SpreadsheetDataSource.MULTIPLE_DATASOURCE+":false} and !T(org.springframework.util.StringUtils).isEmpty('${"+EnableExcelGeneratorConfiguration.SPRING_DATASOURCE_URL+":}')")
public class SpreadsheetSingleDataSource extends SpreadsheetBaseDataSource implements SpreadsheetDataSource {

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
