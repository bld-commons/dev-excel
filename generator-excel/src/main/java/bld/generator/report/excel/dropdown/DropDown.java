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



	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + (suppressDropDownArrow ? 1231 : 1237);
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}



	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DropDown<?> other = (DropDown<?>) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (suppressDropDownArrow != other.suppressDropDownArrow)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
}
