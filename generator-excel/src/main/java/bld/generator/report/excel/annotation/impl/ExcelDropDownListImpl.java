/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelDropDownListImpl.java
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import bld.generator.report.excel.annotation.ExcelDropDownList;

/**
 * The Class ExcelDropDownListImpl.
 */
public class ExcelDropDownListImpl {
	
	
	/** The suppress drop down arrow. */
	private  boolean suppressDropDownArrow;
	
	/** The list. */
	private  String[] list;

	/**
	 * Gets the excel drop down list.
	 *
	 * @return the excel drop down list
	 */
	public ExcelDropDownList getExcelDropDownList() {
		return new ExcelDropDownList() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelDropDownList.class;
			}
			
			@Override
			public boolean suppressDropDownArrow() {
				return suppressDropDownArrow;
			}
			
			@Override
			public String[] list() {
				return list;
			}
		};
		
	}

	/**
	 * Instantiates a new excel drop down list impl.
	 */
	public ExcelDropDownListImpl() {
		this.suppressDropDownArrow=true;
	}

	/**
	 * Instantiates a new excel drop down list impl.
	 *
	 * @param suppressDropDownArrow the suppress drop down arrow
	 * @param list                  the list
	 */
	public ExcelDropDownListImpl(boolean suppressDropDownArrow, String[] list) {
		super();
		this.suppressDropDownArrow = suppressDropDownArrow;
		this.list = list;
	}

	/**
	 * Checks if is suppress drop down arrow.
	 *
	 * @return true, if is suppress drop down arrow
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
	 * Gets the list.
	 *
	 * @return the list
	 */
	public String[] getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(String[] list) {
		this.list = list;
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
		result = prime * result + Arrays.hashCode(list);
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
		ExcelDropDownListImpl other = (ExcelDropDownListImpl) obj;
		if (!Arrays.equals(list, other.list))
			return false;
		if (suppressDropDownArrow != other.suppressDropDownArrow)
			return false;
		return true;
	}

	
	
}
