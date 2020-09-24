/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.dropdown.DropDown.java
 */
package bld.generator.report.excel.dropdown;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DropDown.
 *
 * @param <T> the generic type
 */
public abstract class DropDown<T> {

	/** The value. */
	private T value;
	
	/** The list. */
	private List<T> list;
	
	/** The suppress drop down arrow. */
	private boolean suppressDropDownArrow;
	
	/**
	 * Instantiates a new drop down.
	 */
	public DropDown() {
		this.suppressDropDownArrow=true;
		this.list=new ArrayList<>();
	}
	
	

	/**
	 * Instantiates a new drop down.
	 *
	 * @param value                 the value
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public DropDown(T value, boolean suppressDropDownArrow) {
		super();
		this.value = value;
		this.suppressDropDownArrow = suppressDropDownArrow;
	}



	/**
	 * Instantiates a new drop down.
	 *
	 * @param value the value
	 */
	public DropDown(T value) {
		super();
		this.value = value;
		this.suppressDropDownArrow=true;
		this.list=new ArrayList<>();
	}



	/**
	 * Instantiates a new drop down.
	 *
	 * @param value                 the value
	 * @param list                  the list
	 * @param suppressDropDownArrow the suppress drop down arrow
	 */
	public DropDown(T value, List<T> list, boolean suppressDropDownArrow) {
		super();
		this.value = value;
		this.list = list;
		this.suppressDropDownArrow = suppressDropDownArrow;
	}



	/**
	 * Instantiates a new drop down.
	 *
	 * @param value the value
	 * @param list  the list
	 */
	public DropDown(T value, List<T> list) {
		super();
		this.value = value;
		this.list = list;
		this.suppressDropDownArrow=true;
	}



	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	public List<T> getList() {
		return list;
	}

	/**
	 * Sets the list.
	 *
	 * @param list the new list
	 */
	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * Checks if is suppress drop down arrow.
	 *
	 * @return true, if is suppress drop down arrow
	 */
	public boolean isSuppressDropDownArrow() {
		return suppressDropDownArrow;
	}

	/**
	 * Sets the suppress drop down arrow.
	 *
	 * @param suppressDropDownArrow the new suppress drop down arrow
	 */
	public void setSuppressDropDownArrow(boolean suppressDropDownArrow) {
		this.suppressDropDownArrow = suppressDropDownArrow;
	}
	
	
}
