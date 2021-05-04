/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.IndexRow.java
*/
package bld.report.generator.junit.entity;

import bld.generator.report.excel.ExcelHyperlink;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.constant.UnderlineType;

/**
 * The Class IndexRow.
 */
public class IndexRow implements RowSheet {

	/** The excel hyperlink. */
	@ExcelColumn(indexColumn = 0, columnName = "Indice")
	@ExcelCellLayout(font = @ExcelFont(underline=UnderlineType.SINGLE, italic = true), rgbFont=@ExcelRgbColor(red=0,green=0,blue=(byte)255))
	private ExcelHyperlink excelHyperlink;
	
	/** The descrizione. */
	@ExcelColumn(indexColumn = 1, columnName = "Descrizione")
	@ExcelCellLayout
	private String descrizione;
	
	

	/**
	 * Instantiates a new index row.
	 *
	 * @param excelHyperlink the excel hyperlink
	 * @param descrizione    the descrizione
	 */
	public IndexRow(ExcelHyperlink excelHyperlink, String descrizione) {
		super();
		this.excelHyperlink = excelHyperlink;
		this.descrizione = descrizione;
	}

	/**
	 * Gets the excel hyperlink.
	 *
	 * @return the excel hyperlink
	 */
	public ExcelHyperlink getExcelHyperlink() {
		return excelHyperlink;
	}

	/**
	 * Sets the excel hyperlink.
	 *
	 * @param excelHyperlink the new excel hyperlink
	 */
	public void setExcelHyperlink(ExcelHyperlink excelHyperlink) {
		this.excelHyperlink = excelHyperlink;
	}

	/**
	 * Gets the descrizione.
	 *
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * Sets the descrizione.
	 *
	 * @param descrizione the new descrizione
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	
	
}
