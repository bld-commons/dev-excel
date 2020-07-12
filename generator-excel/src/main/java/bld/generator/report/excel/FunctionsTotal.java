/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.FunctionsTotal.java
*/
package bld.generator.report.excel;

// TODO: Auto-generated Javadoc
/**
 * The Interface FunctionsTotal.
 * <br>
 * FunctionsTotal can be implemented by classes of type {@link bld.generator.report.excel.SheetData}, it is used to create a table for totals of the functions.
 *
 * @param <T> the generic type
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
