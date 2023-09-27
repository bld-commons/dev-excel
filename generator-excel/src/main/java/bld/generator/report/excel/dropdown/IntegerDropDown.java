/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.IntegerDropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.util.List;


/**
 * The Class IntegerDropDown.
 */
public class IntegerDropDown extends NumberDropDown<Integer> {

	/**
	 * Instantiates a new integer drop down.
	 */
	public IntegerDropDown() {
	}

	/**
	 * Instantiates a new integer drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public IntegerDropDown(Integer value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new integer drop down.
	 *
	 * @param value the value
	 */
	public IntegerDropDown(Integer value) {
		super(value);
	}

	/**
	 * Instantiates a new integer drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public IntegerDropDown(Integer value, List<Integer> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new integer drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public IntegerDropDown(Integer value, List<Integer> list) {
		super(value, list);
	}

}
