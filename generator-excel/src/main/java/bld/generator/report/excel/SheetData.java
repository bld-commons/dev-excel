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
 * The Class SheetData.
 *
 * @param <T> the generic type
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class SheetData<T extends RowSheet> extends BaseSheet implements SheetComponent{
	

	/** The list row sheet. */
	protected List<T> listRowSheet;
	
	
		
	/**
	 * Instantiates a new sheet data.
	 *
	 * @param nameSheet the name sheet
	 */
	public SheetData(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		listRowSheet = new ArrayList<T>();
	}




	
	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	public abstract Class<T> getRowClass();

	
	
	
}
