package com.bld.generator.report;

import java.util.Map;

public interface QuerySpreadsheetData<T>{

	public Map<String, Object> getMapParameters();
	
	public void addParameters(String key,Object value);
	
	public void addParameters(Map<String,Object> parameters);
	
	public void resetParameters();
	
	public Class<T> getRowClass();
}
