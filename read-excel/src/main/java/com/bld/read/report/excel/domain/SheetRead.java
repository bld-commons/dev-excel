/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.domain;

import java.util.ArrayList;
import java.util.List;

import com.bld.common.spreadsheet.utils.SpreadsheetUtils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * The Class SheetRead.<br>
 * SheetRead is the object that represents the excel sheet.<br>
 * It is composed by:
 * <ul>
 * <li>ListRowSheet - to get the result of the RowSheetRead list</li>
 * <lI>SheetName - to get the excel sheet through the name</li>
 * </ul>
 * @param <T> the generic type
 */
public abstract class SheetRead<T extends RowSheetRead> {

	/** The list row sheet. */
	private List<T>listRowSheet;
	
	/** The sheet name. */
	@NotNull
	private String sheetName;
	
	
	/**
	 * Instantiates a new sheet read.
	 *
	 * @param sheetName the sheet name
	 */
	public SheetRead(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE)String sheetName) {
		super();
		this.listRowSheet=new ArrayList<>();
		this.sheetName=sheetName;
	}


	/**
	 * Gets the list row sheet.
	 *
	 * @return the list row sheet
	 */
	public List<T> getListRowSheet() {
		return listRowSheet;
	}
	
	/**
	 * Adds the row sheet.
	 *
	 * @param t the t
	 * @throws Exception the exception
	 */
	public void addRowSheet(T t) throws Exception {
		this.listRowSheet.add(t);
	}


	/**
	 * Gets the sheet name.
	 *
	 * @return the sheet name
	 */
	public String getSheetName() {
		return sheetName;
	}


	/**
	 * Sets the sheet name.
	 *
	 * @param sheetName the new sheet name
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
		result = prime * result + ((listRowSheet == null) ? 0 : listRowSheet.hashCode());
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
		SheetRead<?> other = (SheetRead<?>) obj;
		if (listRowSheet == null) {
			if (other.listRowSheet != null)
				return false;
		} else if (!listRowSheet.equals(other.listRowSheet))
			return false;
		if (sheetName == null) {
			if (other.sheetName != null)
				return false;
		} else if (!sheetName.equals(other.sheetName))
			return false;
		return true;
	}
	
	
	
}
