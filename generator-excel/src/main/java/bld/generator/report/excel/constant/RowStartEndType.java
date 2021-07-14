/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.constant.RowStartEndType.java
*/
package bld.generator.report.excel.constant;

/**
 * The Enum RowStartEndType.<br>
 * RowStartEndType is used to define the name of the variable, to write functions parameterized with the column name.<br>
 *  
 */
public enum RowStartEndType {

	/** The row start. */
	ROW_START("RowStart"),
	
	/** The row end. */
	ROW_END("RowEnd"),
	
	/** The row header. */
	ROW_HEADER("RowHeader"),
	
	/** The row empty. */
	ROW_EMPTY("");
	
	
	/** The value. */
	private String value;
	


	/**
	 * Instantiates a new row start end type.
	 *
	 * @param value the value
	 */
	private RowStartEndType(String value) {
		this.value = value;
	}

	/**
	 * Gets the parameter.
	 *
	 * @param paramter the paramter
	 * @return the parameter
	 */
	public String getParameter(String paramter) {
		return "${"+paramter+this.value+"}";
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
}
