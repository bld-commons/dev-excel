/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.StringDropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class StringDropDown.
 */
public class StringDropDown extends DropDown<String> {

	/**
	 * Instantiates a new string drop down.
	 */
	public StringDropDown() {
		super();
	}

	/**
	 * Instantiates a new string drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public StringDropDown(String value, List<String> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new string drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public StringDropDown(String value, List<String> list) {
		super(value, list);
	}

	/**
	 * Instantiates a new string drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public StringDropDown(String value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new string drop down.
	 *
	 * @param value the value
	 */
	public StringDropDown(String value) {
		super(value);
	}

}
