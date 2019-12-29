package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import bld.generator.report.excel.data.ExtraColumnAnnotation;

public abstract class SheetDynamicFunctionTotal<T extends DynamicRowSheet> extends SheetFunctionTotal<T> implements DynamicColumn{

	/** The map extra column annotation. */
	private Map<String,ExtraColumnAnnotation> mapExtraColumnAnnotation;
	
	public SheetDynamicFunctionTotal(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		this.mapExtraColumnAnnotation=new HashMap<>();
	}

	public Map<String, ExtraColumnAnnotation> getMapExtraColumnAnnotation() {
		return mapExtraColumnAnnotation;
	}

	public void setMapExtraColumnAnnotation(Map<String, ExtraColumnAnnotation> mapExtraColumnAnnotation) {
		this.mapExtraColumnAnnotation = mapExtraColumnAnnotation;
	}

	
	
}
