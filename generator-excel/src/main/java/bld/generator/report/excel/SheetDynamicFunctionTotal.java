/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetDynamicFunctionTotal.java
*/
package bld.generator.report.excel;

import java.util.HashMap;
import java.util.Map;

import bld.generator.report.excel.data.ExtraColumnAnnotation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class SheetDynamicFunctionTotal.
 *
 * @param <T> the generic type
 * 
 * 
 * SheetDynamicFunctionTotal is the object that represent the table for totals of the functions with dynamic columns
 */
@Data
@EqualsAndHashCode(callSuper = true)
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

	

}
