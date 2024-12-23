/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.DynamicRowSheet.java
*/
package com.bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;


/**
 * The Class DynamicRowSheet.
 * <br>
 * DynamicRowSheet is used by classes of type SheetDynamicData, it manages dynamic columns through the "mapValue" field.
 * <br>
 * mapValue and mapExtraColumnAnnotation(is a field of SheetDynamicData) have the same keys.
 * <br>
 * Below an example of table generated by merging {@link com.bld.generator.report.excel.SheetDynamicData} and {@link com.bld.generator.report.excel.DynamicRowSheet} classes.
 * <table style="width:100%;">
 * <tr>
 * 	<th style="width: 4%"></th>
 *   <th style="width: 16%; border: 1px solid #666; text-align: center;">column name by field 1</th>
 *   <th style="width: 16%; border: 1px solid #666; text-align: center;">column name by field 2</th>
 *   <th style="width: 16%; border: 1px solid #666; text-align: center;">column name by mapExtraColumnAnnotation 1</th>
 *   <th style="width: 16%; border: 1px solid #666; text-align: center;">column name by mapExtraColumnAnnotation ...N</th>
 *   <th style="width: 16%; border: 1px solid #666; text-align: center;">column name by field 3</th>
 *   <th style="width: 16%; border: 1px solid #666; text-align: center;">column name by field ...N</th>
 * </tr>
 * <tr>
 *   <td style="width: 8%; ">row 1</td>
 *   <td style="width: 16%; border: 1px solid #666;">value by field 1</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field 2</td>
 *   <td style="width: 16%; border: 1px solid #666">value by mapValue 1</td>
 *   <td style="width: 16%; border: 1px solid #666">value by mapValue ...N</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field 3</td>
 *  	<td style="width: 16%; border: 1px solid #666">value by field ...N</td>
 * </tr>
 * <tr>
 *   <td style="width: 8%; ">row 2</td>
 *   <td style="width: 16%; border: 1px solid #666;">value by field 1</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field 2</td>
 *   <td style="width: 16%; border: 1px solid #666">value by mapValue 1</td>
 *   <td style="width: 16%; border: 1px solid #666">value by mapValue ...N</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field 3</td>
 *  	<td style="width: 16%; border: 1px solid #666">value by field ...N</td>
 * </tr>
 * <tr>
 *   <td style="width: 8%; ">row N</td>
 *   <td style="width: 16%; border: 1px solid #666;">value by field 1</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field 2</td>
 *   <td style="width: 16%; border: 1px solid #666">value by mapValue 1</td>
 *   <td style="width: 16%; border: 1px solid #666">value by mapValue ...N</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field 3</td>
 *   <td style="width: 16%; border: 1px solid #666">value by field ...N</td>
 * </tr>
 * 
 *</table>
 * 
 */
public abstract class DynamicRowSheet implements RowSheet {

	/** The map value. */
	protected Map<String, Object> mapValue;

	/**
	 * Instantiates a new dynamic row sheet.
	 */
	public DynamicRowSheet() {
		super();
		this.mapValue = new HashMap<>();
	}

	/**
	 * Gets the map value.
	 *
	 * @return the map value
	 */
	public Map<String, Object> getMapValue() {
		return mapValue;
	}
	
	/**
	 * Adds the value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void addValue(String key,Object value) {
		this.mapValue.put(key, value);
	}
	
	/**
	 * Adds the values.
	 *
	 * @param map the map
	 */
	public void addValues(Map<String, Object> map) {
		if(MapUtils.isNotEmpty(map)) 
			for(Entry<String,Object>entry:map.entrySet())
				this.addValue(entry.getKey(), entry.getValue());
	}

	/**
	 * Clear map.
	 */
	public void clearMap() {
		this.mapValue = new HashMap<>();
	}


}
