/*
 * @auth Francesco Baldi
 * @class bld.common.spreadsheet.exception.SpreadsheetException.java
 */
package com.bld.common.spreadsheet.exception;

/**
 * The Class SpreadsheetException.
 */
public class SpreadsheetException extends RuntimeException {

	private static final long serialVersionUID = 482346351416862179L;

	/**
	 * Instantiates a new spreadsheet exception.
	 */
	public SpreadsheetException() {
		super();
	}

	/**
	 * Instantiates a new spreadsheet exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public SpreadsheetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new spreadsheet exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public SpreadsheetException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new spreadsheet exception.
	 *
	 * @param message the message
	 */
	public SpreadsheetException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new spreadsheet exception.
	 *
	 * @param cause the cause
	 */
	public SpreadsheetException(Throwable cause) {
		super(cause);
	}

	
}
