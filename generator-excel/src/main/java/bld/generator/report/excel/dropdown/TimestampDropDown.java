/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.TimestampDropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.sql.Timestamp;
import java.util.List;


/**
 * The Class TimestampDropDown.
 */
public class TimestampDropDown extends DropDown<Timestamp> {

	/**
	 * Instantiates a new timestamp drop down.
	 */
	public TimestampDropDown() {
	}

	/**
	 * Instantiates a new timestamp drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public TimestampDropDown(Timestamp value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new timestamp drop down.
	 *
	 * @param value the value
	 */
	public TimestampDropDown(Timestamp value) {
		super(value);
	}

	/**
	 * Instantiates a new timestamp drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public TimestampDropDown(Timestamp value, List<Timestamp> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new timestamp drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public TimestampDropDown(Timestamp value, List<Timestamp> list) {
		super(value, list);
	}

}
