/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.ReportExcel.java
*/

package bld.generator.report.excel.data;

import java.util.Date;
import java.util.List;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelSelectCell;
import bld.generator.report.excel.constant.ColumnDateFormat;
import lombok.Data;

/**
 * The Class ReportExcel.
 */
@Data
public class ReportExcel {

	/** The titolo. */
	@ExcelSelectCell(cellReference = "${bld.commons.report.excel.titolo}")
	private String title;
	
	/** The date. */
	@ExcelSelectCell(cellReference = "${bld.commons.report.excel.date}")
	@ExcelDate(format = ColumnDateFormat.PARAMETER)
	private Date date;

	/** The list base sheet. */
	private List<BaseSheet> listBaseSheet;

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title        the titolo
	 * @param listBaseSheet the list base sheet
	 */
	public ReportExcel(String title, List<BaseSheet> listBaseSheet) {
		super();
		this.title = title;
		this.listBaseSheet = listBaseSheet;
		this.date=new Date();
	}

	/**
	 * Instantiates a new report excel.
	 */
	public ReportExcel() {
		super();
		this.date=new Date();
	}

	
}
