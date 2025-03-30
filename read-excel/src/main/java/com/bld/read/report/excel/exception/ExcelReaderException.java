/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.exception;

import com.bld.read.report.excel.constant.ExcelExceptionType;
import com.bld.read.report.excel.constant.ExcelReaderConstant;

/**
 * The Class ExcelReaderException.<br>
 * ExcelReaderException is used to manage the exceptions when reading the excel.
 */
@SuppressWarnings("serial")
public class ExcelReaderException extends RuntimeException {
	
	/** The code. */
	private String code;
	
	/** The parameter. */
	private String parameter;

	
	/**
	 * Instantiates a new excel reader exception.
	 *
	 * @param excelExceptionType the excel exception type
	 * @param parameter          the parameter
	 */
	public ExcelReaderException(ExcelExceptionType excelExceptionType,String parameter) {
		super(excelExceptionType.getMessage().replace(ExcelReaderConstant.PARAMETER, parameter==null?"":parameter));
		this.code=excelExceptionType.getCode();
		this.parameter=parameter;
	}

	/**
	 * Instantiates a new excel reader exception.
	 *
	 * @param excelExceptionType the excel exception type
	 */
	public ExcelReaderException(ExcelExceptionType excelExceptionType) {
		super(excelExceptionType.getMessage());
		this.code=excelExceptionType.getCode();
	}

	public ExcelReaderException(String message) {
		super(message);
		
	}

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
