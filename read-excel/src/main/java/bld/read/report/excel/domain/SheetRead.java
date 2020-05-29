/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class SheetRead.
 *
 * @param <T> the generic type
 */
public abstract class SheetRead<T extends RowSheetRead> {

	/** The list row sheet. */
	private List<T>listRowSheet;
	
	/** The sheet name. */
	private String sheetName;
	
	
	/**
	 * Instantiates a new sheet read.
	 *
	 * @param sheetName the sheet name
	 */
	public SheetRead(String sheetName) {
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
	 * Sets the list row sheet.
	 *
	 * @param listRowSheet the new list row sheet
	 */
	public void setListRowSheet(List<T> listRowSheet) {
		this.listRowSheet = listRowSheet;
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
