/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import bld.generator.report.excel.data.ExtraColumnAnnotation;

/**
 * The Class SheetDynamicFunctionTotal.
 *
 * @param <T> the generic type
 */
public abstract class SheetDynamicFunctionTotal<T extends DynamicRowSheet> extends SheetFunctionTotal<T> implements DynamicColumn {

	/** The map extra column annotation. */
	private Map<String, ExtraColumnAnnotation> mapExtraColumnAnnotation;

	/**
	 * Instantiates a new sheet dynamic function total.
	 */
	public SheetDynamicFunctionTotal() {
		super();
		this.mapExtraColumnAnnotation = new HashMap<>();
	}

	/**
	 * Gets the map extra column annotation.
	 *
	 * @return the map extra column annotation
	 */
	public Map<String, ExtraColumnAnnotation> getMapExtraColumnAnnotation() {
		return mapExtraColumnAnnotation;
	}

	/**
	 * Sets the map extra column annotation.
	 *
	 * @param mapExtraColumnAnnotation the new map extra column annotation
	 */
	public void setMapExtraColumnAnnotation(Map<String, ExtraColumnAnnotation> mapExtraColumnAnnotation) {
		this.mapExtraColumnAnnotation = mapExtraColumnAnnotation;
	}

}
