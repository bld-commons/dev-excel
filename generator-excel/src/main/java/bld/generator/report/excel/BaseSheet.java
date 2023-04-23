/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.BaseSheet.java
*/

package bld.generator.report.excel;

import javax.validation.constraints.Size;

import bld.common.spreadsheet.utils.SpreadsheetUtils;

/**
 * The Class BaseSheet. <br>
 * Each generated Sheet will be of BaseSheet type
 */
public abstract class BaseSheet {

	//public static final String APOS = "\u0027";
	
	/** The Constant APOS. */
	public static final String APOS = "&apos;";
	
	/** The name sheet. */
	@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE)
	private String sheetName;

	/**
	 * Instantiates a new base sheet.
	 *
	 * @param sheetName the name sheet
	 */
	public BaseSheet(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE) String sheetName) {
		super();
		sheetNameApos(sheetName);
	}

	private void sheetNameApos(String sheetName) {
		if (sheetName != null)
			this.sheetName = sheetName.replace("'", APOS);
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
		this.sheetNameApos(sheetName);;
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
