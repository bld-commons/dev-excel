/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel;

/**
 * The Interface FunctionsTotal.
 *
 * @param <T> the generic type
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
