/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelFunction;
import lombok.Data;

/**
 * The Class ExcelFunctionImpl.
 */
@Data
public class ExcelFunctionImpl implements Cloneable{

	
	/** The function. */
	protected String function;
	
	/** The name function. */
	protected String nameFunction;
	
	/** The another table. */
	protected boolean anotherTable;
	
	/**
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

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
			}

			@Override
			public boolean anotherTable() {
				return anotherTable;
			}
			
		};
	}

	
	
	/**
	 * Instantiates a new excel function impl.
	 *
	 * @param function     the function
	 * @param nameFunction the name function
	 */
	public ExcelFunctionImpl(String function, String nameFunction) {
		super();
		this.function = function;
		this.nameFunction = nameFunction;
		this.anotherTable=true;
	}

	


	/**
	 * Instantiates a new excel function impl.
	 *
	 * @param function     the function
	 * @param nameFunction the name function
	 * @param anotherTable the another table
	 */
	public ExcelFunctionImpl(String function, String nameFunction, boolean anotherTable) {
		super();
		this.function = function;
		this.nameFunction = nameFunction;
		this.anotherTable = anotherTable;
	}

	
	
	
}
