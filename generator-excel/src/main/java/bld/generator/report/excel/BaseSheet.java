/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.BaseSheet.java
*/

package bld.generator.report.excel;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * The Class BaseSheet.
 * <br>
 * Each generated Sheet will be of BaseSheet type 
 */
@Data
public  abstract class BaseSheet{
	
	/** The name sheet. */
	@Size(max = 30)
	private String sheetName;

	/**
	 * Instantiates a new base sheet.
	 *
	 * @param sheetName the name sheet
	 */
	public BaseSheet(@Size(max = 30) String sheetName) {
		super();
		this.sheetName = sheetName;
	}

	
	
}
