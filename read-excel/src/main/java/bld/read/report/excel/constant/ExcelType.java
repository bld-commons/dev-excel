/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.constant;

/**
 * The Enum ExcelType.
 */
public enum ExcelType {

	/** The xls. */
	XLS("xls"),
	
	/** The xlsx. */
	XLSX("xlsx");
	
	/** The type. */
	private String type;

	/**
	 * Instantiates a new excel type.
	 *
	 * @param type the type
	 */
	private ExcelType(String type) {
		this.type = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	
}
