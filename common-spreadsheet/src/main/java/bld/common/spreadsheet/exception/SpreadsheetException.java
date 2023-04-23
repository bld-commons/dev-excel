package bld.common.spreadsheet.exception;

@SuppressWarnings("serial")
public class SpreadsheetException extends RuntimeException {

	public SpreadsheetException() {
		super();
	}

	public SpreadsheetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SpreadsheetException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpreadsheetException(String message) {
		super(message);
	}

	public SpreadsheetException(Throwable cause) {
		super(cause);
	}

	
}
