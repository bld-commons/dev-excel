/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.FunctionsTotal.java
*/
package com.bld.generator.report.excel;


/**
 * The Interface FunctionsTotal.
 * @param <T> the generic type
 * <br>
 * FunctionsTotal can be implemented by classes of type {@link com.bld.generator.report.excel.SheetData}, it is used to create a table for totals of the functions.
 * 
 */
public interface FunctionsTotal<T extends SheetFunctionTotal<? extends RowSheet>> {
	
	/**
	 * Gets the sheet functions total.
	 *
	 * @return the sheet functions total
	 */
	public T getSheetFunctionsTotal();
	
	
	/**
	 * Sets the sheet functions total.
	 *
	 * @param sheetFunctionsTotal the new sheet functions total
	 */
	public void setSheetFunctionsTotal(T sheetFunctionsTotal);
	

}
