/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.constant;

/**
 * The Enum ExcelExceptionType.<br>
 * ExcelExceptionType is a list of type of exception message. 
 */
public enum ExcelExceptionType {
	
	
	/** The sheet not found. */
	SHEET_NOT_FOUND("SNF","The sheet \""+ExcelReaderConstant.PARAMETER+"\" not found"),
	
	/** The column not found. */
	COLUMN_NOT_FOUND("CNF","The column \""+ExcelReaderConstant.PARAMETER+"\" not found"),
	
	/** The max sheet name. */
	MAX_SHEET_NAME("MSN","The sheet name has exceeded the maximum length of characters"),
	;
	
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new excel exception type.
	 *
	 * @param code    the code
	 * @param message the message
	 */
	private ExcelExceptionType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
	
	
	

}
