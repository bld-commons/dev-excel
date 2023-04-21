/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriSheet.java
*/
package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import org.apache.poi.ss.usermodel.BorderStyle;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.config.Db1DatabaseConfiguration;
import bld.generator.report.excel.FunctionsTotal;
import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.annotation.ExcelAreaBorder;
import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelPivot;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSuperHeader;
import bld.generator.report.excel.annotation.ExcelSuperHeaderCell;
import bld.generator.report.excel.annotation.ExcelSuperHeaders;

/**
 * The Class AutoreLibriSheet.
 */
@ExcelSheetLayout(areaBorder = {
		//@ExcelAreaBorder(areaRange = "${idAutoreRowHeader}:${prezzoRowHeader}", border = @ExcelBorder(bottom = BorderStyle.MEDIUM_DASHED, top = BorderStyle.MEDIUM_DASHED, right = BorderStyle.MEDIUM_DASHED, left = BorderStyle.MEDIUM_DASHED)),
		@ExcelAreaBorder(areaRange = "${idAutoreRowHeader}:${prezzoRowEnd}", border = @ExcelBorder(bottom = BorderStyle.MEDIUM_DASHED, top = BorderStyle.NONE, right = BorderStyle.MEDIUM_DASHED, left = BorderStyle.MEDIUM_DASHED)),
		@ExcelAreaBorder(areaRange = "${idAutoreRowHeader}:${idAutoreRowHeader}", border = @ExcelBorder(bottom = BorderStyle.NONE, top = BorderStyle.MEDIUM_DASHED, right = BorderStyle.MEDIUM_DASHED, left = BorderStyle.MEDIUM_DASHED)),
		@ExcelAreaBorder(areaRange = "${nomeRowHeader}:${sessoRowEnd}", border = @ExcelBorder(bottom = BorderStyle.MEDIUM_DASHED, top = BorderStyle.MEDIUM_DASHED, right = BorderStyle.MEDIUM_DASHED, left = BorderStyle.MEDIUM_DASHED), includeSuperHeader = true),
		@ExcelAreaBorder(areaRange = "${desGenereRowHeader}:${prezzoRowEnd}", border = @ExcelBorder(bottom = BorderStyle.MEDIUM_DASHED, top = BorderStyle.MEDIUM_DASHED, right = BorderStyle.MEDIUM_DASHED, left = BorderStyle.MEDIUM_DASHED),includeSuperHeader = true)}
,order = 1
		)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery(value="select\n" + "	sp.anno,sp.prezzo,l.id_libro,l.titolo,a.id_autore,a.nome,a.cognome,a.data_nascita,a.sesso,g.des_genere\n" + "from\n" + "	storico_prezzo sp\n" + "inner join libro l on\n" + "	sp.id_libro = l.id_libro\n"
		+ "inner join autore a on\n" + "	a.id_autore = l.id_autore\n" + "inner join genere g on\n" + "	g.id_genere = l.id_genere;",entityManager = Db1DatabaseConfiguration.DB1_ENTITY_MANAGER)
@ExcelSuperHeaders(superHeaders = @ExcelSuperHeader(headerGroups = {
		@ExcelSuperHeaderCell(columnName = "Anagrafica", columnRange = "${nome}:${sesso}"),
		@ExcelSuperHeaderCell(columnName = "Libri", columnRange = "${desGenere}:${prezzo}")
}))
@ExcelPivot
public class AutoreLibriSheet extends QuerySheetData<AutoreLibriRow> implements FunctionsTotal<TotaleAutoreLibriSheet>{
	
	
	private TotaleAutoreLibriSheet sheetFunctionsTotal;

	/**
	 * Instantiates a new autore libri sheet.
	 *
	 * @param nameSheet the name sheet
	 */
	public AutoreLibriSheet(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE) String nameSheet) {
		super(nameSheet);

	}

	/**
	 * Instantiates a new autore libri sheet.
	 *
	 * @param nameSheet the name sheet
	 * @param label     the label
	 */
	public AutoreLibriSheet(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE) String nameSheet, String label) {
		super(nameSheet);
		this.label = label;
	}

//	/**
//	 * Gets the row class.
//	 *
//	 * @return the row class
//	 */
//	@Override
//	public Class<AutoreLibriRow> getRowClass() {
//		return AutoreLibriRow.class;
//	}

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

	public TotaleAutoreLibriSheet getSheetFunctionsTotal() {
		return sheetFunctionsTotal;
	}

	public void setSheetFunctionsTotal(TotaleAutoreLibriSheet sheetFunctionsTotal) {
		this.sheetFunctionsTotal = sheetFunctionsTotal;
	}

	

}
