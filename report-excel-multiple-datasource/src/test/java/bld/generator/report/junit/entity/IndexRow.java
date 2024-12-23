/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.IndexRow.java
*/
package bld.generator.report.junit.entity;

import com.bld.generator.report.excel.ExcelHyperlink;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelFont;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;
import com.bld.generator.report.excel.constant.UnderlineType;

/**
 * The Class IndexRow.
 */
public class IndexRow implements RowSheet {

	/** The excel hyperlink. */
	@ExcelColumn(index = 0, name = "Indice")
	@ExcelCellLayout(font = @ExcelFont(underline=UnderlineType.SINGLE, italic = true), rgbFont=@ExcelRgbColor(red=0,green=0,blue=(byte)255))
	private ExcelHyperlink excelHyperlink;
	
	/** The descrizione. */
	@ExcelColumn(index = 1, name = "Descrizione")
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
