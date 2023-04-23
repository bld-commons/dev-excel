/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriSheetDynamic.java
*/
package bld.report.generator.junit.entity;

import javax.validation.constraints.Size;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.excel.DynamicChart;
import bld.generator.report.excel.FunctionsTotal;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelSheetLayout;

/**
 * The Class AutoreLibriSheetDynamic.
 */
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5,left = 1.5,right = 1.5,top = 1.5)
public class AutoreLibriSheetDynamic extends DynamicChart<AutoreLibriRowDynamic> implements FunctionsTotal<TotaleAutoreLibriSheet>{

	/** The sheet functions total. */
	private TotaleAutoreLibriSheet sheetFunctionsTotal;
	
	/**
	 * Instantiates a new autore libri sheet dynamic.
	 *
	 * @param nameSheet the name sheet
	 */
	public AutoreLibriSheetDynamic(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE) String nameSheet) {
		super(nameSheet);
		
	}

	
	/**
	 * Instantiates a new autore libri sheet dynamic.
	 *
	 * @param nameSheet the name sheet
	 * @param etichetta the etichetta
	 */
	public AutoreLibriSheetDynamic(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE) String nameSheet,String etichetta) {
		super(nameSheet);
		this.etichetta = etichetta;
	}



	/**
	 * Gets the sheet functions total.
	 *
	 * @return the sheet functions total
	 */
	public TotaleAutoreLibriSheet getSheetFunctionsTotal() {
		return sheetFunctionsTotal;
	}


	/**
	 * Sets the sheet functions total.
	 *
	 * @param sheetFunctionsTotal the new sheet functions total
	 */
	public void setSheetFunctionsTotal(TotaleAutoreLibriSheet sheetFunctionsTotal) {
		this.sheetFunctionsTotal = sheetFunctionsTotal;
	}

	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	@Override
	public Class<AutoreLibriRowDynamic> getRowClass() {
		return AutoreLibriRowDynamic.class;
	}

	/** The etichetta. */
	@ExcelLabel(columnMerge = 4)
	private String etichetta;

	/**
	 * Gets the etichetta.
	 *
	 * @return the etichetta
	 */
	public String getEtichetta() {
		return etichetta;
	}

	/**
	 * Sets the etichetta.
	 *
	 * @param etichetta the new etichetta
	 */
	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}
	
	
	
}
