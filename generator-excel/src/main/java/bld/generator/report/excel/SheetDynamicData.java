/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetDynamicData.java
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import bld.generator.report.excel.data.ExtraColumnAnnotation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class SheetDynamicData.
 *
 * SheetDynamicData extends SheetData, it manages dynamic columns through the mapExtraColumnAnnotation field.
 * 
 * mapExtraColumnAnnotation and mapValue(is a field of DynamicRowSheet) have the same keys.
 * @param <T> the generic type
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SheetDynamicData<T extends DynamicRowSheet> extends SheetData<T> implements DynamicColumn{
		
	/** The map extra column annotation. */
	private Map<String,ExtraColumnAnnotation> mapExtraColumnAnnotation;
	
	
	/**
	 * Instantiates a new sheet dynamic data.
	 *
	 * @param sheetName the name sheet
	 */
	public SheetDynamicData(@Size(max = 30) String sheetName) {
		super(sheetName);
		this.mapExtraColumnAnnotation=new HashMap<>();
	}


	
	
}
