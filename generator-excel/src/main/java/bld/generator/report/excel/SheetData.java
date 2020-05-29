/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetData.java
*/
package bld.generator.report.excel;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import bld.generator.report.excel.constant.ExcelConstant;


/**
 * 
 * 
 * The Class SheetData.
 * <br>
 * SheetData is used for the generation of a table with rows of type RowSheet.
 *
 * @param <T> the generic type
 * 
 * 
 */
public abstract class SheetData<T extends RowSheet> extends BaseSheet implements SheetComponent{
	

	/** The list row sheet. */
	protected List<T> listRowSheet;
	
	
		
	/**
	 * Instantiates a new sheet data.
	 *
	 * @param sheetName the name sheet
	 */
	public SheetData(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String sheetName) {
		super(sheetName);
		listRowSheet = new ArrayList<T>();
	}




	
	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	public abstract Class<T> getRowClass();





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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((listRowSheet == null) ? 0 : listRowSheet.hashCode());
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
		SheetData<?> other = (SheetData<?>) obj;
		if (listRowSheet == null) {
			if (other.listRowSheet != null)
				return false;
		} else if (!listRowSheet.equals(other.listRowSheet))
			return false;
		return true;
	}

	
	
	
}
