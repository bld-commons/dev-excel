/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelDropDownImpl.java
 */

package bld.generator.report.excel.annotation.impl;

import java.util.Arrays;

import bld.generator.report.excel.annotation.ExcelBoxMessage;
import bld.generator.report.excel.annotation.ExcelDropDown;
import bld.generator.report.excel.annotation.ExcelFormulaAlias;


/**
 * The Class ExcelDropDownImpl.
 */
public class ExcelDropDownImpl extends ExcelAnnotationImpl<ExcelDropDown> {

	/** The suppress drop down arrow. */
	private boolean suppressDropDownArrow;

	/** The area range. */
	private String areaRange;

	/** The alias. */
	private ExcelFormulaAlias[] alias;

	/** The error box. */
	private ExcelBoxMessage errorBox;
	
	/**
	 * Instantiates a new excel drop down impl.
	 */
	public ExcelDropDownImpl() {
		this.suppressDropDownArrow = true;
	}

	/**
	 * Instantiates a new excel drop down impl.
	 *
	 * @param suppressDropDownArrow the suppress drop down arrow
	 * @param areaRange             the area range
	 */
	public ExcelDropDownImpl(boolean suppressDropDownArrow, String areaRange) {
		super();
		this.suppressDropDownArrow = suppressDropDownArrow;
		this.areaRange = areaRange;
	}

	/**
	 * Instantiates a new excel drop down impl.
	 *
	 * @param suppressDropDownArrow the suppress drop down arrow
	 * @param areaRange the area range
	 * @param alias the alias
	 * @param errorBox the error box
	 */
	public ExcelDropDownImpl(boolean suppressDropDownArrow, String areaRange, ExcelFormulaAlias[] alias, ExcelBoxMessage errorBox) {
		super();
		this.suppressDropDownArrow = suppressDropDownArrow;
		this.areaRange = areaRange;
		this.alias = alias;
		this.errorBox = errorBox;
	}

	
	/**
	 * Instantiates a new excel drop down impl.
	 *
	 * @param suppressDropDownArrow the suppress drop down arrow
	 * @param areaRange the area range
	 * @param alias the alias
	 */
	public ExcelDropDownImpl(boolean suppressDropDownArrow, String areaRange, ExcelFormulaAlias[] alias) {
		super();
		this.suppressDropDownArrow = suppressDropDownArrow;
		this.areaRange = areaRange;
		this.alias = alias;
	}

	/**
	 * Checks if is suppress drop down arrow.
	 *
	 * @return the suppress drop down arrow
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
	 * Gets the area range.
	 *
	 * @return the area range
	 */
	public String getAreaRange() {
		return areaRange;
	}

	/**
	 * Sets the area range.
	 *
	 * @param areaRange the new area range
	 */
	public void setAreaRange(String areaRange) {
		this.areaRange = areaRange;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public ExcelFormulaAlias[] getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias the new alias
	 */
	public void setAlias(ExcelFormulaAlias[] alias) {
		this.alias = alias;
	}
	
	

	/**
	 * Gets the error box.
	 *
	 * @return the error box
	 */
	public ExcelBoxMessage getErrorBox() {
		return errorBox;
	}

	/**
	 * Sets the error box.
	 *
	 * @param errorBox the new error box
	 */
	public void setErrorBox(ExcelBoxMessage errorBox) {
		this.errorBox = errorBox;
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
		result = prime * result + Arrays.hashCode(alias);
		result = prime * result + ((areaRange == null) ? 0 : areaRange.hashCode());
		result = prime * result + ((errorBox == null) ? 0 : errorBox.hashCode());
		result = prime * result + (suppressDropDownArrow ? 1231 : 1237);
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
		ExcelDropDownImpl other = (ExcelDropDownImpl) obj;
		if (!Arrays.equals(alias, other.alias))
			return false;
		if (areaRange == null) {
			if (other.areaRange != null)
				return false;
		} else if (!areaRange.equals(other.areaRange))
			return false;
		if (errorBox == null) {
			if (other.errorBox != null)
				return false;
		} else if (!errorBox.equals(other.errorBox))
			return false;
		if (suppressDropDownArrow != other.suppressDropDownArrow)
			return false;
		return true;
	}

}
