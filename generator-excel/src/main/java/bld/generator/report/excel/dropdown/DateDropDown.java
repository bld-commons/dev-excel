/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.DateDropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.util.Date;
import java.util.List;

/**
 * The Class DateDropDown.
 */
public class DateDropDown extends DropDown<Date> {

	/**
	 * Instantiates a new date drop down.
	 */
	public DateDropDown() {
	}

	/**
	 * Instantiates a new date drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public DateDropDown(Date value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new date drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public DateDropDown(Date value, List<Date> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new date drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public DateDropDown(Date value, List<Date> list) {
		super(value, list);
	}

	/**
	 * Instantiates a new date drop down.
	 *
	 * @param value the value
	 */
	public DateDropDown(Date value) {
		super(value);
	}

}
