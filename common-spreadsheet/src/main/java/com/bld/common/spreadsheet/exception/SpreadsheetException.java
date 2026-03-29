/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.common.spreadsheet.exception;

/**
 * Base unchecked exception for the spreadsheet library.
 * <p>
 * Thrown when a configuration error is detected, such as a required annotation missing
 * on a class or field. All more specific exceptions in this library extend this class.
 * </p>
 *
 * @author Francesco Baldi
 * @see com.bld.common.spreadsheet.exception.ExcelGeneratorException
 * @see com.bld.common.spreadsheet.exception.CsvGeneratorException
 */
public class SpreadsheetException extends RuntimeException {

	private static final long serialVersionUID = 482346351416862179L;

	/**
	 * Instantiates a new spreadsheet exception with no detail message.
	 */
	public SpreadsheetException() {
		super();
	}

	/**
	 * Instantiates a new spreadsheet exception with full control over suppression and stack trace.
	 *
	 * @param message            the detail message
	 * @param cause              the cause
	 * @param enableSuppression  whether suppression is enabled or disabled
	 * @param writableStackTrace whether the stack trace should be writable
	 */
	public SpreadsheetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new spreadsheet exception with a detail message and a cause.
	 *
	 * @param message the detail message
	 * @param cause   the cause
	 */
	public SpreadsheetException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new spreadsheet exception with a detail message.
	 *
	 * @param message the detail message
	 */
	public SpreadsheetException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new spreadsheet exception wrapping another throwable.
	 *
	 * @param cause the original cause
	 */
	public SpreadsheetException(Throwable cause) {
		super(cause);
	}

	
}
