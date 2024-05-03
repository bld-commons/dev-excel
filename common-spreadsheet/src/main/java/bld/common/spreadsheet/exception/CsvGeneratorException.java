/*
 * @auth Francesco Baldi
 * @class bld.common.spreadsheet.exception.CsvGeneratorException.java
 */
package bld.common.spreadsheet.exception;

/**
 * The Class CsvGeneratorException.
 */
@SuppressWarnings("serial")
public class CsvGeneratorException extends RuntimeException {

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
