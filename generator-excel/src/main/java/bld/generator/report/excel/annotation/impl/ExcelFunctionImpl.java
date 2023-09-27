/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelFunctionImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.util.Arrays;

import bld.generator.report.excel.annotation.ExcelFormulaAlias;
import bld.generator.report.excel.annotation.ExcelFunction;


/**
 * The Class ExcelFunctionImpl.
 */
public class ExcelFunctionImpl extends ExcelAnnotationImpl<ExcelFunction> {

	/** The function. */
	private String function;

	/** The name function. */
	private String nameFunction;

	/** The another table. */
	private boolean anotherTable;
	
	/** The alias. */
	private ExcelFormulaAlias[] alias;

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
	 */
	public ExcelFunctionImpl() {
		super();
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

	/**
	 * Instantiates a new excel function impl.
	 *
	 * @param function the function
	 * @param nameFunction the name function
	 * @param anotherTable the another table
	 * @param alias the alias
	 */
	public ExcelFunctionImpl(String function, String nameFunction, boolean anotherTable, ExcelFormulaAlias[] alias) {
		super();
		this.function = function;
		this.nameFunction = nameFunction;
		this.anotherTable = anotherTable;
		this.alias = alias;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(alias);
		result = prime * result + (anotherTable ? 1231 : 1237);
		result = prime * result + ((function == null) ? 0 : function.hashCode());
		result = prime * result + ((nameFunction == null) ? 0 : nameFunction.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelFunctionImpl other = (ExcelFunctionImpl) obj;
		if (!Arrays.equals(alias, other.alias))
			return false;
		if (anotherTable != other.anotherTable)
			return false;
		if (function == null) {
			if (other.function != null)
				return false;
		} else if (!function.equals(other.function))
			return false;
		if (nameFunction == null) {
			if (other.nameFunction != null)
				return false;
		} else if (!nameFunction.equals(other.nameFunction))
			return false;
		return true;
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

	/**
	 * Checks if is another table.
	 *
	 * @return the another table
	 */
	public boolean isAnotherTable() {
		return anotherTable;
	}

	/**
	 * Sets the another table.
	 *
	 * @param anotherTable the new another table
	 */
	public void setAnotherTable(boolean anotherTable) {
		this.anotherTable = anotherTable;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public ExcelFormulaAlias[] getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias the new alias
	 */
	public void setAlias(ExcelFormulaAlias[] alias) {
		this.alias = alias;
	}

}
