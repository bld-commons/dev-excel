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

	
	
	/**
	 * Gets the list row sheet.
	 *
	 * @return the list row sheet
	 */
	public List<T> getListRowSheet() {
		if(listRowSheet==null)
			this.listRowSheet=new ArrayList<>();
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
	
}
