/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetFunctionTotal.java
*/
package bld.generator.report.excel;

import lombok.Data;
import lombok.EqualsAndHashCode;

// TODO: Auto-generated Javadoc
/**
 * The Class SheetFunctionTotal.
 * 
 * SheetFunctionTotal is the object that represent the table for totals of the functions.
 * 
 * @param <T> the generic type
 * 
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SheetFunctionTotal<T extends RowSheet> extends SheetData<T> {
	
	/** The cal row start. */
	private Integer calRowStart;
	
	/** The cal row end. */
	private Integer calRowEnd;
	

	/**
	 * Instantiates a new sheet function total.
	 */
	public SheetFunctionTotal() {
		super("");
	}

	
	
}
