/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.QuerySheetData.java
 */
package bld.generator.report.excel;

import java.util.Map;

import javax.validation.constraints.Size;

import org.apache.commons.collections4.map.HashedMap;

import bld.generator.report.excel.constant.ExcelConstant;

/**
 * The Class QuerySheetData.
 * @param <T> the generic type
 * <br>
 * QuerySheetData is used to obtain a RowSheet list through a query, to set the parameters in the query you need to insert them in the "mapParameter" field.<br>  
 * 
 */
public abstract class QuerySheetData<T extends RowSheet> extends SheetData<T>{

	

	/** The map parameters. */
	private Map<String,Object> mapParameters;
	
	
	/**
	 * Instantiates a new query sheet data.
	 *
	 * @param sheetName the sheet name
	 */
	public QuerySheetData(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String sheetName) {
		super(sheetName);
		mapParameters=new HashedMap<>();
	}


	/**
	 * Gets the map parameters.
	 *
	 * @return the map parameters
	 */
	public Map<String, Object> getMapParameters() {
		return mapParameters;
	}


	/**
	 * Sets the map parameters.
	 *
	 * @param mapParameters the map parameters
	 */
	public void setMapParameters(Map<String, Object> mapParameters) {
		this.mapParameters = mapParameters;
	}

	/**
	 * Adds the parameters.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addParameters(String key,Object value) {
		this.mapParameters.put(key, value);
	}
	

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mapParameters == null) ? 0 : mapParameters.hashCode());
		return result;
	}


	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuerySheetData<?> other = (QuerySheetData<?>) obj;
		if (mapParameters == null) {
			if (other.mapParameters != null)
				return false;
		} else if (!mapParameters.equals(other.mapParameters))
			return false;
		return true;
	}
	
	
	
}
