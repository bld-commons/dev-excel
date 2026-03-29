/*
 * @auth Francesco Baldi
 * @class bld.common.spreadsheet.exception.CsvGeneratorException.java
 */
package com.bld.common.spreadsheet.exception;

/**
 * The Class CsvGeneratorException.
 */
public class CsvGeneratorException extends RuntimeException {

	private static final long serialVersionUID = -3444702735598040980L;

	/**
	 * Instantiates a new csv generator exception.
	 */
	public CsvGeneratorException() {
		super();
	}

	/**
	 * Instantiates a new csv generator exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public CsvGeneratorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new csv generator exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public CsvGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new csv generator exception.
	 *
	 * @param message the message
	 */
	public CsvGeneratorException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new csv generator exception.
	 *
	 * @param cause the cause
	 */
	public CsvGeneratorException(Throwable cause) {
		super(cause);
	}

	
	
}
