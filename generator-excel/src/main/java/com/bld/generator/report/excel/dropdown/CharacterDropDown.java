/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.dropdown.CharacterDropDown.java
 */
package com.bld.generator.report.excel.dropdown;

import java.util.List;


/**
 * The Class CharacterDropDown.
 */
public class CharacterDropDown extends DropDown<Character> {

	/**
	 * Instantiates a new character drop down.
	 */
	public CharacterDropDown() {
	}

	/**
	 * Instantiates a new character drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public CharacterDropDown(Character value, boolean suppressDropDownArrow) {
		super(value, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new character drop down.
	 *
	 * @param value the value
	 */
	public CharacterDropDown(Character value) {
		super(value);
	}

	/**
	 * Instantiates a new character drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public CharacterDropDown(Character value, List<Character> list, boolean suppressDropDownArrow) {
		super(value, list, suppressDropDownArrow);
	}

	/**
	 * Instantiates a new character drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public CharacterDropDown(Character value, List<Character> list) {
		super(value, list);
	}

}
