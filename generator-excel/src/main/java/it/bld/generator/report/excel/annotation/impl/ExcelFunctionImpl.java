/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import it.bld.generator.report.excel.annotation.ExcelFunction;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelFunctionImpl.
 */
public class ExcelFunctionImpl {

	
	/** The function. */
	protected String function;
	
	/** The name function. */
	protected String nameFunction;

	/**
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction getExcelFunction() {
		return new ExcelFunction() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelFunction.class;
			}

			@Override
			public String function() {
				return function;
			}

			@Override
			public String nameFunction() {
				return nameFunction;
			}};
	}

	
	
	/**
	 * Instantiates a new excel function impl.
	 *
	 * @param function the function
	 * @param nameFunction the name function
	 */
	public ExcelFunctionImpl(String function, String nameFunction) {
		super();
		this.function = function;
		this.nameFunction = nameFunction;
	}



	/**
	 * Gets the function.
	 *
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Sets the function.
	 *
	 * @param function the new function
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * Gets the name function.
	 *
	 * @return the name function
	 */
	public String getNameFunction() {
		return nameFunction;
	}

	/**
	 * Sets the name function.
	 *
	 * @param nameFunction the new name function
	 */
	public void setNameFunction(String nameFunction) {
		this.nameFunction = nameFunction;
	} 
	
	
}
