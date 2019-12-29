/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import bld.generator.report.excel.data.ExtraColumnAnnotation;

// TODO: Auto-generated Javadoc
/**
 * The Class SheetDynamicData.
 *
 * @param <T> the generic type
 */
public abstract class SheetDynamicData<T extends DynamicRowSheet> extends SheetData<T>{
		
	/** The map extra column annotation. */
	private Map<String,ExtraColumnAnnotation> mapExtraColumnAnnotation;
	
	
	/**
	 * Instantiates a new sheet dynamic data.
	 *
	 * @param nameSheet the name sheet
	 */
	public SheetDynamicData(@Size(max = 30) String nameSheet) {
		super(nameSheet);
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


	
	
}
