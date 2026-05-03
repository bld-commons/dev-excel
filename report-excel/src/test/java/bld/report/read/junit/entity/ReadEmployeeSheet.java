/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.read.junit.entity.ReadEmployeeSheet
 */
package bld.report.read.junit.entity;

import com.bld.read.report.excel.annotation.ExcelReadSheet;
import com.bld.read.report.excel.domain.SheetRead;

/**
 * Sheet-read container that maps an Excel employee sheet to a list of {@link ReadEmployeeRow} rows.
 * <p>
 * Extends {@link com.bld.read.report.excel.domain.SheetRead} and is marked with
 * {@link com.bld.read.report.excel.annotation.ExcelReadSheet} for automatic discovery
 * by the read-excel engine.
 * </p>
 */
@ExcelReadSheet
public class ReadEmployeeSheet extends SheetRead<ReadEmployeeRow> {

	public ReadEmployeeSheet(String sheetName) {
		super(sheetName);
	}

}
