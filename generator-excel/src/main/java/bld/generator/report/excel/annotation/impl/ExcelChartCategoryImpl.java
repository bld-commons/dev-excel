/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelChartCategoryImpl.java
 * 
 */
package bld.generator.report.excel.annotation.impl;

import java.util.Objects;

import bld.generator.report.excel.annotation.ExcelChartCategory;

/**
 * The Class ExcelChartCategoryImpl.
 */
public class ExcelChartCategoryImpl extends ExcelAnnotationImpl<ExcelChartCategory> {

	/** The field name. */
	private String fieldName;
	
	/** The function. */
	private String function;
	
	/** The row regex. */
	private String rowRegex;


	/**
	 * Instantiates a new excel chart category impl.
	 *
	 * @param fieldName the field name
	 * @param function the function
	 * @param rowRegex the row regex
	 */
	public ExcelChartCategoryImpl(String fieldName, String function, String rowRegex) {
		super();
		this.fieldName = fieldName;
		this.function = function;
		this.rowRegex = rowRegex;
	}

	/**
	 * Gets the row regex.
	 *
	 * @return the row regex
	 */
	public String getRowRegex() {
		return rowRegex;
	}

	/**
	 * Sets the row regex.
	 *
	 * @param rowRegex the new row regex
	 */
	public void setRowRegex(String rowRegex) {
		this.rowRegex = rowRegex;
	}

	/**
	 * Instantiates a new excel chart category impl.
	 *
	 * @param fieldName the field name
	 * @param function the function
	 */
	public ExcelChartCategoryImpl(String fieldName, String function) {
		super();
		this.fieldName = fieldName;
		this.function = function;
	
	}

	/**
	 * Instantiates a new excel chart category impl.
	 */
	public ExcelChartCategoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets the field name.
	 *
	 * @return the field name
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the field name.
	 *
	 * @param fieldName the new field name
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(fieldName, function, rowRegex);
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
		ExcelChartCategoryImpl other = (ExcelChartCategoryImpl) obj;
		return Objects.equals(fieldName, other.fieldName) && Objects.equals(function, other.function) && Objects.equals(rowRegex, other.rowRegex);
	}
	
}
