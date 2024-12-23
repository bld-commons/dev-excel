/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.DynamicColumn.java
*/
package com.bld.generator.report.excel;

import java.util.Map;

import com.bld.generator.report.excel.data.ExtraColumnAnnotation;

/**
 * The Interface DynamicColumn.
 * <br>
 * This insterface is implemented by classes that manage the dynamic columns:
 * <br>
 * <ol>
 * 	<li>{@link com.bld.generator.report.excel.SheetDynamicData}</li>
 * 	<li>{@link com.bld.generator.report.excel.SheetDynamicFunctionTotal}</li>
 * </ol>
 * 
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
