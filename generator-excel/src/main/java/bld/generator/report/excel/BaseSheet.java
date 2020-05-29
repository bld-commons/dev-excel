/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.BaseSheet.java
*/

package bld.generator.report.excel;

import javax.validation.constraints.Size;

import bld.generator.report.excel.constant.ExcelConstant;

/**
 * The Class BaseSheet.
 * <br>
 * Each generated Sheet will be of BaseSheet type 
 */
public  abstract class BaseSheet{
	
	/** The name sheet. */
	@Size(max = ExcelConstant.SHEET_NAME_SIZE)
	private String sheetName;

	/**
	 * Instantiates a new base sheet.
	 *
	 * @param sheetName the name sheet
	 */
	public BaseSheet(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String sheetName) {
		super();
		this.sheetName = sheetName;
	}

	/**
	 * Gets the name sheet.
	 *
	 * @return the name sheet
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * Sets the name sheet.
	 *
	 * @param sheetName the new name sheet
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
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
		result = prime * result + ((sheetName == null) ? 0 : sheetName.hashCode());
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
		BaseSheet other = (BaseSheet) obj;
		if (sheetName == null) {
			if (other.sheetName != null)
				return false;
		} else if (!sheetName.equals(other.sheetName))
			return false;
		return true;
	}

	
	
}
