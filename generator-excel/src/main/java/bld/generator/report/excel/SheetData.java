/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetData.java
*/
package bld.generator.report.excel;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;


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
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SheetData<T extends RowSheet> extends BaseSheet implements SheetComponent{
	

	/** The list row sheet. */
	protected List<T> listRowSheet;
	
	
		
	/**
	 * Instantiates a new sheet data.
	 *
	 * @param sheetName the name sheet
	 */
	public SheetData(@Size(max = 30) String sheetName) {
		super(sheetName);
		listRowSheet = new ArrayList<T>();
	}




	
	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	public abstract Class<T> getRowClass();

	
	
	
}
