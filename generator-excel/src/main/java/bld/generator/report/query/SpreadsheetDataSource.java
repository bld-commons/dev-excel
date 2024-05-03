/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.ExcelDataSource.java
 */
package bld.generator.report.query;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import jakarta.persistence.EntityManager;

/**
 * The Interface ExcelDataSource.
 */
public interface SpreadsheetDataSource {

	/** The Constant MULTIPLE_DATASOURCE. */
	public final static String MULTIPLE_DATASOURCE="bld.commons.multiple.datasource";
	
	/**
	 * Gets the entity manager.
	 *
	 * @param unitName the unit name
	 * @return the entity manager
	 */
	public EntityManager getEntityManager(String unitName);
	
	/**
	 * Gets the named parameter jdbc template.
	 *
	 * @param namedParameterJdbcTemplate the named parameter jdbc template
	 * @return the named parameter jdbc template
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String namedParameterJdbcTemplate);
}
