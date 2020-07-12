/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.MergeSheet.java
*/
package bld.generator.report.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MergeSheet.
 * <br>
 * MergeSheet is used to merge different {@link bld.generator.report.excel.SheetComponent} type, through the field listSheet. <br>
 * SheetComponent is implemented from:<br> 
 * <ol>
 * <li>{@link bld.generator.report.excel.SheetData}</li> 
 * <li>{@link bld.generator.report.excel.SheetSummary}</li>
 * </ol>
 */
public class MergeSheet extends BaseSheet {

	/** The list sheet. */
	private List<SheetComponent> listSheet;

	/**
	 * Instantiates a new merge sheet.
	 *
	 * @param listSheet the list sheet
	 * @param sheetName the name sheet
	 */
	public MergeSheet(List<SheetComponent> listSheet,String sheetName) {
		super(sheetName);
		this.listSheet = listSheet;
	}

	/**
	 * Instantiates a new merge sheet.
	 *
	 * @param sheetName the name sheet
	 */
	public MergeSheet(String sheetName) {
		super(sheetName);
		this.listSheet=new ArrayList<>();
	}

	/**
	 * Gets the list sheet.
	 *
	 * @return the list sheet
	 */
	public List<SheetComponent> getListSheet() {
		return listSheet;
	}

	/**
	 * Sets the list sheet.
	 *
	 * @param listSheet the new list sheet
	 */
	public void setListSheet(List<SheetComponent> listSheet) {
		this.listSheet = listSheet;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listSheet == null) ? 0 : listSheet.hashCode());
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MergeSheet other = (MergeSheet) obj;
		if (listSheet == null) {
			if (other.listSheet != null)
				return false;
		} else if (!listSheet.equals(other.listSheet))
			return false;
		return true;
	}


}
