/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.query.impl.ExcelBaseDataSource.java
 */
package bld.generator.report.query.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * The Class ExcelBaseDataSource.
 */
public class SpreadsheetBaseDataSource {

	/** The application context. */
	@Autowired
	protected ApplicationContext applicationContext;
	
	/**
	 * Instantiates a new excel base data source.
	 */
	public SpreadsheetBaseDataSource() {
		super();
	}

	/**
	 * Gets the named parameter jdbc template.
	 *
	 * @param namedParameterJdbcTemplate the named parameter jdbc template
	 * @return the named parameter jdbc template
	 */
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String namedParameterJdbcTemplate) {
		return (NamedParameterJdbcTemplate) this.applicationContext.getBean(namedParameterJdbcTemplate);
	}

}