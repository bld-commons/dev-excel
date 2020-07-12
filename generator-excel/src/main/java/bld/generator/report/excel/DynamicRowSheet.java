/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.DynamicRowSheet.java
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class DynamicRowSheet.
 * <br>
 * DynamicRowSheet is used by classes of type SheetDynamicData, it manages dynamic columns through the "mapValue" field.
 * <br>
 * mapValue and mapExtraColumnAnnotation(is a field of SheetDynamicData) have the same keys
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
	 * Sets the map value.
	 *
	 * @param mapValue the new map value
	 */
	public void setMapValue(Map<String, Object> mapValue) {
		this.mapValue = mapValue;
	}


}
