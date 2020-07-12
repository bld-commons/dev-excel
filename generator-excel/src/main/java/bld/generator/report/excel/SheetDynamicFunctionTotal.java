/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetDynamicFunctionTotal.java
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import bld.generator.report.excel.data.ExtraColumnAnnotation;

/**
 * The Class SheetDynamicFunctionTotal.
 * <br>
 * SheetDynamicFunctionTotal is the object that represent the table for totals of the functions with dynamic columns
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mapExtraColumnAnnotation == null) ? 0 : mapExtraColumnAnnotation.hashCode());
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
		SheetDynamicFunctionTotal<?> other = (SheetDynamicFunctionTotal<?>) obj;
		if (mapExtraColumnAnnotation == null) {
			if (other.mapExtraColumnAnnotation != null)
				return false;
		} else if (!mapExtraColumnAnnotation.equals(other.mapExtraColumnAnnotation))
			return false;
		return true;
	}

	

}
