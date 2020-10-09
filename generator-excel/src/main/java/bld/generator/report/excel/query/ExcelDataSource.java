package bld.generator.report.excel.query;

import javax.persistence.EntityManager;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface ExcelDataSource {

	public final static String MULTIPLE_DATASOURCE="bld.commons.multiple.datasource";
	
	public EntityManager getEntityManager(String unitName);
	
	
	
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String namedParameterJdbcTemplate);
}
