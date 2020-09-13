/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.FloatDropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.util.List;

/**
 * The Class FloatDropDown.
 */
public class FloatDropDown extends NumberDropDown<Float> {

	/**
	 * Instantiates a new float drop down.
	 */
	public FloatDropDown() {
	}

	/**
	 * Instantiates a new float drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public FloatDropDown(Float value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new float drop down.
	 *
	 * @param value the value
	 */
	public FloatDropDown(Float value) {
		super(value);
	}

	/**
	 * Instantiates a new float drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public FloatDropDown(Float value, List<Float> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new float drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public FloatDropDown(Float value, List<Float> list) {
		super(value, list);
	}

}
