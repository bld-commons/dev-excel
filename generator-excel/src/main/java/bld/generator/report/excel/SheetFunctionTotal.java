/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel;

// TODO: Auto-generated Javadoc
/**
 * The Class SheetFunctionTotal.
 *
 * @param <T> the generic type
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

	
	
}
