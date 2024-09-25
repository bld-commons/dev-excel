/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.dropdown.DoubleDropDown.java
 */
package com.bld.generator.report.excel.dropdown;

import java.util.List;


/**
 * The Class DoubleDropDown.
 */
public class DoubleDropDown extends NumberDropDown<Double> {

	/**
	 * Instantiates a new double drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public DoubleDropDown(Double value, List<Double> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new double drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public DoubleDropDown(Double value, List<Double> list) {
		super(value, list);
	}

	/**
	 * Instantiates a new double drop down.
	 */
	public DoubleDropDown() {
		super();
	}

	/**
	 * Instantiates a new double drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public DoubleDropDown(Double value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new double drop down.
	 *
	 * @param value the value
	 */
	public DoubleDropDown(Double value) {
		super(value);
	}

	
}
