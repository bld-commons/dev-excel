/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.DateRow.java
*/
package bld.report.generator.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.common.spreadsheet.excel.annotation.ExcelDate;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelRowHeight;

/**
 * The Class DateRow.
 */
@ExcelRowHeight(height = 5)
public class DateRow implements RowSheet {

	/** The data da. */
	@ExcelColumn(indexColumn = 0, columnName = "Data da")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dataDa;
	
	
	/** The data A. */
	@ExcelColumn(indexColumn = 1, columnName = "Data a")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dataA;
	
	
	


	/**
	 * Instantiates a new date row.
	 *
	 * @param dataDa the data da
	 * @param dataA  the data A
	 */
	public DateRow(Date dataDa, Date dataA) {
		super();
		this.dataDa = dataDa;
		this.dataA = dataA;
	}


	/**
	 * Gets the data da.
	 *
	 * @return the data da
	 */
	public Date getDataDa() {
		return dataDa;
	}


	/**
	 * Sets the data da.
	 *
	 * @param dataDa the new data da
	 */
	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}


	/**
	 * Gets the data A.
	 *
	 * @return the data A
	 */
	public Date getDataA() {
		return dataA;
	}


	/**
	 * Sets the data A.
	 *
	 * @param dataA the new data A
	 */
	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}
	
	
	
}
