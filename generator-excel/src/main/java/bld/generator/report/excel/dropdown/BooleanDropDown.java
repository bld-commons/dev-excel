/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.BooleanDropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.util.List;


/**
 * The Class BooleanDropDown.
 */
public class BooleanDropDown extends DropDown<Boolean> {

	/**
	 * Instantiates a new boolean drop down.
	 */
	public BooleanDropDown() {
	}

	/**
	 * Instantiates a new boolean drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public BooleanDropDown(Boolean value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new boolean drop down.
	 *
	 * @param value the value
	 */
	public BooleanDropDown(Boolean value) {
		super(value);
	}

	/**
	 * Instantiates a new boolean drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public BooleanDropDown(Boolean value, List<Boolean> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new boolean drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public BooleanDropDown(Boolean value, List<Boolean> list) {
		super(value, list);
	}

}
