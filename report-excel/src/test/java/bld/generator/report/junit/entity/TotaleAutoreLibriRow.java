/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.TotaleAutoreLibriRow.java
*/
package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;

/**
 * The Class TotaleAutoreLibriRow.
 */
@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
				excelColumn = @ExcelColumn(indexColumn = 2, nameColumn = "Totale per matricola"), 
				excelFunction = @ExcelFunction(function = "sumif(${matricolaRowStart}:${matricolaRowEnd},${totMatricola},${prezzoRowStart}:${prezzoRowEnd})", nameFunction = "totalePerMatricola")) })
public class TotaleAutoreLibriRow implements RowSheet {

	/** The tot matricola. */
	@ExcelColumn(nameColumn = "Matricola", indexColumn = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer totMatricola;

	/**
	 * Instantiates a new totale autore libri row.
	 *
	 * @param totMatricola the tot matricola
	 */
	public TotaleAutoreLibriRow(Integer totMatricola) {
		super();
		this.totMatricola = totMatricola;
	}

	/**
	 * Gets the tot matricola.
	 *
	 * @return the tot matricola
	 */
	public Integer getTotMatricola() {
		return totMatricola;
	}

	/**
	 * Sets the tot matricola.
	 *
	 * @param totMatricola the new tot matricola
	 */
	public void setTotMatricola(Integer totMatricola) {
		this.totMatricola = totMatricola;
	}
	
	
	

}
