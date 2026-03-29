/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.exception;

import com.bld.read.report.excel.constant.ExcelExceptionType;
import com.bld.read.report.excel.constant.ExcelReaderConstant;

/**
 * Unchecked exception thrown when an error occurs while reading an Excel file.
 * <p>
 * In addition to the standard exception message, this class carries a structured
 * {@link #code} (from {@link ExcelExceptionType}) and an optional {@link #parameter}
 * that identifies the entity involved (e.g. the missing sheet or column name).
 * This allows callers to handle specific read errors programmatically.
 * </p>
 *
 * @author Francesco Baldi
 * @see ExcelExceptionType
 */
public class ExcelReaderException extends RuntimeException {
	
	private static final long serialVersionUID = 6618285188860815977L;

	/** The code. */
	private String code;
	
	/** The parameter. */
	private String parameter;

	
	/**
	 * Instantiates a new excel reader exception from a structured error type and a contextual parameter.
	 * The parameter replaces the placeholder in the enum message (e.g. the sheet or column name).
	 *
	 * @param excelExceptionType the structured exception type carrying code and message template
	 * @param parameter          the value to substitute in the message template, or {@code null}
	 */
	public ExcelReaderException(ExcelExceptionType excelExceptionType,String parameter) {
		super(excelExceptionType.getMessage().replace(ExcelReaderConstant.PARAMETER, parameter==null?"":parameter));
		this.code=excelExceptionType.getCode();
		this.parameter=parameter;
	}

	/**
	 * Instantiates a new excel reader exception from a structured error type with no contextual parameter.
	 *
	 * @param excelExceptionType the structured exception type carrying code and message
	 */
	public ExcelReaderException(ExcelExceptionType excelExceptionType) {
		super(excelExceptionType.getMessage());
		this.code=excelExceptionType.getCode();
	}

	/**
	 * Instantiates a new excel reader exception with a free-form detail message.
	 *
	 * @param message the detail message
	 */
	public ExcelReaderException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new excel reader exception wrapping another throwable.
	 *
	 * @param cause the original cause
	 */
	public ExcelReaderException(Throwable cause) {
		super(cause);
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
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * Gets the parameter.
	 *
	 * @return the parameter
	 */
	public String getParameter() {
		return parameter;
	}


	/**
	 * Sets the parameter.
	 *
	 * @param parameter the new parameter
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	
	
	

}
