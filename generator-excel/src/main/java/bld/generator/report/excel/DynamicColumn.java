/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.DynamicColumn.java
*/
package bld.generator.report.excel;

import java.util.Map;

import bld.generator.report.excel.data.ExtraColumnAnnotation;

/**
 * The Interface DynamicColumn.
 */
public interface DynamicColumn {

	
	/**
	 * Gets the map extra column annotation.
	 *
	 * @return the map extra column annotation
	 */
	public Map<String, ExtraColumnAnnotation> getMapExtraColumnAnnotation();


	/**
	 * Sets the map extra column annotation.
	 *
	 * @param mapExtraColumnAnnotation the map extra column annotation
	 */
	public void setMapExtraColumnAnnotation(Map<String, ExtraColumnAnnotation> mapExtraColumnAnnotation);
	
}
