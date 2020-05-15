/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.DynamicRowSheet.java
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * The Class DynamicRowSheet.
 * 
 * DynamicRowSheet is used by classes of type SheetDynamicData, it manages dynamic columns through the "mapValue" field.
 * 
 * mapValue and mapExtraColumnAnnotation(is a field of SheetDynamicData) have the same keys
 * 
 */
@Data
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


}
