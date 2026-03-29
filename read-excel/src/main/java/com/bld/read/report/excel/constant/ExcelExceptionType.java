/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.constant;

/**
 * Enumeration of structured error types used by {@link com.bld.read.report.excel.exception.ExcelReaderException}.
 * <p>
 * Each constant carries a short {@link #code} (usable for programmatic error handling) and a
 * human-readable {@link #message} template. The placeholder {@code PARAM} inside a message
 * is replaced at runtime with the actual entity name (sheet name, column name, field name, etc.).
 * </p>
 *
 * @author Francesco Baldi
 * @see com.bld.read.report.excel.exception.ExcelReaderException
 */
public enum ExcelExceptionType {
	
	
	/** The requested sheet was not found in the workbook. */
	SHEET_NOT_FOUND("SNF","The \""+ExcelReaderConstant.PARAMETER+"\" sheet not found"),

	/** The mapped column header was not found in the sheet. */
	COLUMN_NOT_FOUND("CNF","The \""+ExcelReaderConstant.PARAMETER+"\" column not found"),

	/** The sheet name exceeds the Excel limit of 31 characters. */
	MAX_SHEET_NAME("MSN","The sheet name has exceeded the maximum length of characters"),

	/** Multiple sheets share the same name in a single ExcelRead. */
	MULTIPLE_SHEET_NAME("MLTSN","Multiple sheets with name \""+ExcelReaderConstant.PARAMETER+"\""),

	/** A Character field received a string with more than one character. */
	CHARACTER_NOT_VALID("CNV","The \""+ExcelReaderConstant.PARAMETER+"\" field is \"character\" type"),

	/** A MapSheetRead was registered without providing the mandatory key field name. */
	KEY_FIELD_IS_NOT_NULL("KF","The \"keyField\" field must be not null"),
	;
	
	
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates an enum constant with the given error code and message template.
	 *
	 * @param code    a short identifier for the error (e.g. {@code "SNF"})
	 * @param message a human-readable message template; may contain the {@code PARAM} placeholder
	 */
	private ExcelExceptionType(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Returns the short error code for this exception type.
	 *
	 * @return the error code (e.g. {@code "SNF"}, {@code "CNF"})
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns the message template for this exception type.
	 * The template may contain the {@code PARAM} placeholder which is replaced
	 * at runtime by {@link com.bld.read.report.excel.exception.ExcelReaderException}.
	 *
	 * @return the message template
	 */
	public String getMessage() {
		return message;
	}
	
	
	
	
	

}
