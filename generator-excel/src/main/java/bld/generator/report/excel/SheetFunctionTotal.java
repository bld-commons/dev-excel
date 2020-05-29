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


	/**
	 * Gets the cal row start.
	 *
	 * @return the cal row start
	 */
	public Integer getCalRowStart() {
		return calRowStart;
	}


	/**
	 * Sets the cal row start.
	 *
	 * @param calRowStart the new cal row start
	 */
	public void setCalRowStart(Integer calRowStart) {
		this.calRowStart = calRowStart;
	}


	/**
	 * Gets the cal row end.
	 *
	 * @return the cal row end
	 */
	public Integer getCalRowEnd() {
		return calRowEnd;
	}


	/**
	 * Sets the cal row end.
	 *
	 * @param calRowEnd the new cal row end
	 */
	public void setCalRowEnd(Integer calRowEnd) {
		this.calRowEnd = calRowEnd;
	}


	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((calRowEnd == null) ? 0 : calRowEnd.hashCode());
		result = prime * result + ((calRowStart == null) ? 0 : calRowStart.hashCode());
		return result;
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
		SheetFunctionTotal<?> other = (SheetFunctionTotal<?>) obj;
		if (calRowEnd == null) {
			if (other.calRowEnd != null)
				return false;
		} else if (!calRowEnd.equals(other.calRowEnd))
			return false;
		if (calRowStart == null) {
			if (other.calRowStart != null)
				return false;
		} else if (!calRowStart.equals(other.calRowStart))
			return false;
		return true;
	}

	
	
}
