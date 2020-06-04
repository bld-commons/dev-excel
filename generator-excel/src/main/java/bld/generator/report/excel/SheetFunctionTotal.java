/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetFunctionTotal.java
*/
package bld.generator.report.excel;

// TODO: Auto-generated Javadoc
/**
 * The Class SheetFunctionTotal.
 * <br>
 * SheetFunctionTotal is the object that represent the table for totals of the functions.
 * 
 * @param <T> the generic type
 * 
 * 
 */
public abstract class SheetFunctionTotal<T extends RowSheet> extends SheetData<T> {
	

	/**
	 * Instantiates a new sheet function total.
	 */
	public SheetFunctionTotal() {
		super("");
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}


	
	
}
