/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel;

import java.util.Map;

import javax.validation.constraints.Size;

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
	 * Sets the map field column.
	 *
	 * @param mapFieldColumn the map field column
	 */
	@Override
	public void setMapFieldColumn(Map<String, Integer> mapFieldColumn) {
		super.setMapFieldColumn(mapFieldColumn);
		
	}

	/**
	 * Instantiates a new sheet function total.
	 *
	 * @param nameSheet the name sheet
	 */
	public SheetFunctionTotal(@Size(max = 30) String nameSheet) {
		super(nameSheet);
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
