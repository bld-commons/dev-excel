/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */

package bld.generator.report.excel;

import javax.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseSheet.
 */
public  abstract class BaseSheet{
	
	/** The name sheet. */
	@Size(max = 30)
	private String nameSheet;

	/**
	 * Gets the name sheet.
	 *
	 * @return the name sheet
	 */
	public String getNameSheet() {
		return nameSheet;
	}

	/**
	 * Sets the name sheet.
	 *
	 * @param nameSheet the new name sheet
	 */
	public void setNameSheet(String nameSheet) {
		this.nameSheet = nameSheet;
	}

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
