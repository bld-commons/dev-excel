/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriSheet.java
*/
package bld.report.generator.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSuperHeader;
import bld.generator.report.excel.annotation.ExcelSuperHeaderCell;
import bld.generator.report.excel.annotation.ExcelSuperHeaders;
import bld.generator.report.excel.constant.ExcelConstant;

/**
 * The Class AutoreLibriSheet.
 */
@ExcelSheetLayout(startRow = 1,groupRow = true,notMerge = false,scale=30)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
//@ExcelFreezePane(rowFreez = 5, columnFreez = 1)
@ExcelSuperHeaders(superHeaders = { @ExcelSuperHeader(headerGroups = {
				@ExcelSuperHeaderCell(columnName = "Anagrafica", columnRange = "${matricola}:${dataDiNascita}"),
				@ExcelSuperHeaderCell(columnName = "Libri", columnRange = "${genere}:${supplemento}")
		}),
		@ExcelSuperHeader(rowHeight = -1,headerGroups = {
				@ExcelSuperHeaderCell(columnName = "Test", columnRange = "${matricola}:${cognome}"),
				@ExcelSuperHeaderCell(columnName = "test1", columnRange = "${dataDiNascita}:${test}",excelHeaderCellLayout = @ExcelHeaderCellLayout(rgbForeground = @ExcelRgbColor(red=(byte)0)))})
})
public class AutoreLibriSheet extends SheetData<AutoreLibriRow> {

	
	/**
	 * Instantiates a new autore libri sheet.
	 *
	 * @param nameSheet the name sheet
	 */
	public AutoreLibriSheet(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String nameSheet) {
		super(nameSheet);

	}

	/**
	 * Instantiates a new autore libri sheet.
	 *
	 * @param nameSheet the name sheet
	 * @param label     the label
	 */
	public AutoreLibriSheet(@Size(max = ExcelConstant.SHEET_NAME_SIZE) String nameSheet, String label) {
		super(nameSheet);
		this.label = label;
	}

	/** The label. */
	@ExcelLabel
	private String label;

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	



}
