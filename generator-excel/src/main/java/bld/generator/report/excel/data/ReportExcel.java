/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.impl.ReportExcel.java
*/

package bld.generator.report.excel.data;

import java.util.List;

import bld.generator.report.excel.BaseSheet;
import lombok.Data;

/**
 * The Class ReportExcel.
 */
@Data
public class ReportExcel {

	/** The titolo. */
	private String titolo;

	/** The list base sheet. */
	private List<BaseSheet> listBaseSheet;

	/**
	 * Instantiates a new report excel.
	 *
	 * @param titolo        the titolo
	 * @param listBaseSheet the list base sheet
	 */
	public ReportExcel(String titolo, List<BaseSheet> listBaseSheet) {
		super();
		this.titolo = titolo;
		this.listBaseSheet = listBaseSheet;
	}

	/**
	 * Instantiates a new report excel.
	 */
	public ReportExcel() {
		super();
	}

	
}
