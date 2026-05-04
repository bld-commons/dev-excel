/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.read.junit.entity.ReadProductSheet
 */
package bld.report.read.junit.entity;

import com.bld.read.report.excel.annotation.ExcelReadSheet;
import com.bld.read.report.excel.domain.SheetRead;

/**
 * Sheet-read container that maps an Excel product sheet to a list of {@link ReadProductRow} rows.
 * <p>
 * Extends {@link com.bld.read.report.excel.domain.SheetRead} and is marked with
 * {@link com.bld.read.report.excel.annotation.ExcelReadSheet} for automatic discovery
 * by the read-excel engine.
 * </p>
 */
@ExcelReadSheet
public class ReadProductSheet extends SheetRead<ReadProductRow> {

	public ReadProductSheet(String sheetName) {
		super(sheetName);
	}

}
