/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetSummary.java
*/

package bld.generator.report.excel;

import javax.validation.constraints.Size;

/**
 * The Class SheetSummary.
 */
public abstract class SheetSummary extends BaseSheet implements SheetComponent{

	/**
	 * Instantiates a new sheet summary.
	 *
	 * @param nameSheet the name sheet
	 */
	public SheetSummary(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	
	
}
