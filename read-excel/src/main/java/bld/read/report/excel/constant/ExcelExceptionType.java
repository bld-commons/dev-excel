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
	SHEET_NOT_FOUND("SNF","The \""+ExcelReaderConstant.PARAMETER+"\" sheet not found"),
	
	/** The column not found. */
	COLUMN_NOT_FOUND("CNF","The \""+ExcelReaderConstant.PARAMETER+"\" column not found"),
	
	/** The max sheet name. */
	MAX_SHEET_NAME("MSN","The sheet name has exceeded the maximum length of characters"),
	
	/** The multiple sheet name. */
	MULTIPLE_SHEET_NAME("MLTSN","Multiple sheets with name \""+ExcelReaderConstant.PARAMETER+"\""),
	
	/** The character not valid. */
	CHARACTER_NOT_VALID("CNv","The \""+ExcelReaderConstant.PARAMETER+"\" field is \"character\" type"),
	
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
