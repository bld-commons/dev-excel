/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.generator.junit.entity.ProductSheet
 */
package bld.report.generator.junit.entity;

import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

/**
 * Excel sheet container for {@link ProductRow} rows.
 * <p>
 * Configured with default sheet and header layouts, and 1-inch margins on all sides.
 * </p>
 */
@ExcelSheetLayout
@ExcelHeaderLayout
@ExcelMarginSheet(top = 1.0, bottom = 1.0, left = 1.0, right = 1.0)
public class ProductSheet extends SheetData<ProductRow> {

	public ProductSheet(String sheetName) {
		super(sheetName);
	}

}
