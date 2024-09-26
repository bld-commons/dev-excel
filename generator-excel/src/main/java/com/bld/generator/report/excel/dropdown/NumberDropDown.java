/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.dropdown.NumberDropDown.java
 */
package com.bld.generator.report.excel.dropdown;

import java.util.List;


/**
 * The Class NumberDropDown.
 *
 * @param <T> the generic type
 */
public abstract class NumberDropDown<T extends Number> extends DropDown<T> {

	/**
	 * Instantiates a new number drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public NumberDropDown(T value, List<T> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new number drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public NumberDropDown(T value, List<T> list) {
		super(value, list);
	}

	/**
	 * Instantiates a new number drop down.
	 */
	public NumberDropDown() {
		super();
	}

	/**
	 * Instantiates a new number drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public NumberDropDown(T value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new number drop down.
	 *
	 * @param value the value
	 */
	public NumberDropDown(T value) {
		super(value);
	}

	

}