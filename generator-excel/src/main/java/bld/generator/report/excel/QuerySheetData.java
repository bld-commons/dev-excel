/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.QuerySheetData.java
 */
package bld.generator.report.excel;

import java.util.List;
import java.util.Map;

/**
 * The Interface QuerySheetData.
 *
 * @param <T> the generic type
 */
public interface QuerySheetData<T extends RowSheet> {

	/**
	 * Gets the list row sheet.
	 *
	 * @return the list row sheet
	 */
	public List<T> getListRowSheet();

	/**
	 * Sets the list row sheet.
	 *
	 * @param listRowSheet the new list row sheet
	 */
	public void setListRowSheet(List<T> listRowSheet);
	
	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	public Class<T> getRowClass();
	
	/**
	 * Gets the map parameters.
	 *
	 * @return the map parameters
	 */
	public Map<String,Object> getMapParameters();
	
	/**
	 * Sets the map parameters.
	 *
	 * @param mapParameters the map parameters
	 */
	public void setMapParameters(Map<String,Object>mapParameters);
	
	
}
