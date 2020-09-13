/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelDropDownImpl.java
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelDropDown;


/**
 * The Class ExcelDropDownImpl.
 */
public class ExcelDropDownImpl {
	
	/** The suppress drop down arrow. */
	private boolean suppressDropDownArrow;
	
	/** The area range. */
	private String areaRange;



	/**
	 * Gets the excel drop down.
	 *
	 * @return the excel drop down
	 */
	public ExcelDropDown getExcelDropDown() {
		return new ExcelDropDown() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelDropDown.class;
			}
			
			@Override
			public boolean suppressDropDownArrow() {
				return suppressDropDownArrow;
			}
			
			@Override
			public String areaRange() {
				return areaRange;
			}
		};
	}

	
	/**
	 * Instantiates a new excel drop down impl.
	 */
	public ExcelDropDownImpl() {
		this.suppressDropDownArrow=true;
	}

	
	/**
	 * Instantiates a new excel drop down impl.
	 *
	 * @param suppressDropDownArrow the suppress drop down arrow
	 * @param areaRange             the area range
	 */
	public ExcelDropDownImpl(boolean suppressDropDownArrow, String areaRange) {
		super();
		this.suppressDropDownArrow = suppressDropDownArrow;
		this.areaRange = areaRange;
	}

	/**
	 * Checks if is suppress drop down arrow.
	 *
	 * @return the suppress drop down arrow
	 */
	public boolean isSuppressDropDownArrow() {
		return suppressDropDownArrow;
	}

	/**
	 * Sets the suppress drop down arrow.
	 *
	 * @param suppressDropDownArrow the new suppress drop down arrow
	 */
	public void setSuppressDropDownArrow(boolean suppressDropDownArrow) {
		this.suppressDropDownArrow = suppressDropDownArrow;
	}

	/**
	 * Gets the area range.
	 *
	 * @return the area range
	 */
	public String getAreaRange() {
		return areaRange;
	}

	/**
	 * Sets the area range.
	 *
	 * @param areaRange the new area range
	 */
	public void setAreaRange(String areaRange) {
		this.areaRange = areaRange;
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
		result = prime * result + ((areaRange == null) ? 0 : areaRange.hashCode());
		result = prime * result + (suppressDropDownArrow ? 1231 : 1237);
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
		ExcelDropDownImpl other = (ExcelDropDownImpl) obj;
		if (areaRange == null) {
			if (other.areaRange != null)
				return false;
		} else if (!areaRange.equals(other.areaRange))
			return false;
		if (suppressDropDownArrow != other.suppressDropDownArrow)
			return false;
		return true;
	}

	
}
