/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.DateRow.java
*/
package bld.generator.report.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelRowHeight;

/**
 * The Class DateRow.
 */
@ExcelRowHeight(height = 5)
public class DateRow implements RowSheet {

	/** The data da. */
	@ExcelColumn(index = 0, name = "Data da")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dataDa;
	
	
	/** The data A. */
	@ExcelColumn(index = 1, name = "Data a")
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
