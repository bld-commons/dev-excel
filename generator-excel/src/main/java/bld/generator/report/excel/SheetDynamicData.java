/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetDynamicData.java
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.excel.data.ExtraColumnAnnotation;

/**
 * The Class SheetDynamicData.
 * <br>
 * SheetDynamicData extends SheetData, it manages dynamic columns through the mapExtraColumnAnnotation field.
 * <br>
 * mapExtraColumnAnnotation and mapValue(is a field of DynamicRowSheet) have the same keys.
 * @param <T> the generic type
 * 
 */
public abstract class SheetDynamicData<T extends DynamicRowSheet> extends SheetData<T> implements DynamicColumn{
		
	/** The map extra column annotation. */
	private Map<String,ExtraColumnAnnotation> mapExtraColumnAnnotation;
	
	
	/**
	 * Instantiates a new sheet dynamic data.
	 *
	 * @param sheetName the name sheet
	 */
	public SheetDynamicData(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String sheetName) {
		super(sheetName);
		this.mapExtraColumnAnnotation=new HashMap<>();
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
		SheetDynamicData<?> other = (SheetDynamicData<?>) obj;
		if (mapExtraColumnAnnotation == null) {
			if (other.mapExtraColumnAnnotation != null)
				return false;
		} else if (!mapExtraColumnAnnotation.equals(other.mapExtraColumnAnnotation))
			return false;
		return true;
	}


	
	
}
