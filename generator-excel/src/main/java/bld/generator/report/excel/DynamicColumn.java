package bld.generator.report.excel;

import java.util.Map;

import bld.generator.report.excel.data.ExtraColumnAnnotation;

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
	 * @param mapExtraColumnAnnotation the new map extra column annotation
	 */
	public void setMapExtraColumnAnnotation(Map<String, ExtraColumnAnnotation> mapExtraColumnAnnotation);
	
}
