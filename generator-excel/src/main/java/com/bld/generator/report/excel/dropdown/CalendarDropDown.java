/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.dropdown.CalendarDropDown.java
 */
package com.bld.generator.report.excel.dropdown;

import java.util.Calendar;
import java.util.List;


/**
 * The Class CalendarDropDown.
 */
public class CalendarDropDown extends DropDown<Calendar> {

	/**
	 * Instantiates a new calendar drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public CalendarDropDown(Calendar value, List<Calendar> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new calendar drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public CalendarDropDown(Calendar value, List<Calendar> list) {
		super(value, list);
	}

	/**
	 * Instantiates a new calendar drop down.
	 */
	public CalendarDropDown() {
	}

	/**
	 * Instantiates a new calendar drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public CalendarDropDown(Calendar value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new calendar drop down.
	 *
	 * @param value the value
	 */
	public CalendarDropDown(Calendar value) {
		super(value);
	}

	
	
}
