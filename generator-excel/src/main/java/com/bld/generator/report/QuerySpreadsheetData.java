/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.QuerySpreadsheetData.java
 */
package com.bld.generator.report;

import java.util.Map;

/**
 * The Interface QuerySpreadsheetData.
 *
 * @param <T> the generic type
 */
public interface QuerySpreadsheetData<T>{

	/**
	 * Gets the map parameters.
	 *
	 * @return the map parameters
	 */
	public Map<String, Object> getMapParameters();
	
	/**
	 * Adds the parameters.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addParameters(String key,Object value);
	
	/**
	 * Adds the parameters.
	 *
	 * @param parameters the parameters
	 */
	public void addParameters(Map<String,Object> parameters);
	
	/**
	 * Reset parameters.
	 */
	public void resetParameters();
	
	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	public Class<T> getRowClass();
}
