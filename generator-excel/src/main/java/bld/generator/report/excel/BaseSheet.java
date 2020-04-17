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
 */
@Data
public  abstract class BaseSheet{
	
	/** The name sheet. */
	@Size(max = 30)
	private String nameSheet;

	/**
	 * Instantiates a new base sheet.
	 *
	 * @param nameSheet the name sheet
	 */
	public BaseSheet(@Size(max = 30) String nameSheet) {
		super();
		this.nameSheet = nameSheet;
	}

	
	
}
