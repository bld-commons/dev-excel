/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.common.spreadsheet.exception;

/**
 * Unchecked exception thrown when an error occurs during Excel file generation.
 * <p>
 * Common causes include invalid sheet configuration, unsupported annotation combinations,
 * and Apache POI failures during workbook construction.
 * </p>
 *
 * @author Francesco Baldi
 * @see SpreadsheetException
 */
public class ExcelGeneratorException extends RuntimeException {

	private static final long serialVersionUID = 8600159398467191244L;

	/**
	 * Instantiates a new excel generator exception with no detail message.
	 */
	public ExcelGeneratorException() {
		super();
	}

	/**
	 * Instantiates a new excel generator exception with a detail message and a cause.
	 *
	 * @param message the detail message
	 * @param cause   the original cause
	 */
	public ExcelGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new excel generator exception with a detail message.
	 *
	 * @param message the detail message
	 */
	public ExcelGeneratorException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new excel generator exception wrapping another throwable.
	 *
	 * @param cause the original cause
	 */
	public ExcelGeneratorException(Throwable cause) {
		super(cause);
	}

	
	
}
