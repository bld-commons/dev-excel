package bld.generator.report.exception;

@SuppressWarnings("serial")
public class CsvGeneratorException extends RuntimeException {

	public CsvGeneratorException() {
		super();
	}

	public CsvGeneratorException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CsvGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public CsvGeneratorException(String message) {
		super(message);
	}

	public CsvGeneratorException(Throwable cause) {
		super(cause);
	}

	
	
}
