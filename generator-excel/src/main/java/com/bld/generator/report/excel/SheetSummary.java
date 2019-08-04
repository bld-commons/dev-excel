/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */

package com.bld.generator.report.excel;

import javax.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelSommario.
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
