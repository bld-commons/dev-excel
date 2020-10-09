package bld.generator.report.excel.query.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ExcelBaseDataSource {

	@Autowired
	protected ApplicationContext applicationContext;
	
	public ExcelBaseDataSource() {
		super();
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(String namedParameterJdbcTemplate) {
		return (NamedParameterJdbcTemplate) this.applicationContext.getBean(namedParameterJdbcTemplate);
	}

}