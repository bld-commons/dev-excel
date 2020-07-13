/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriSheet.java
*/
package bld.generator.report.junit.entity;

import javax.validation.constraints.Size;

import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelPivot;
import bld.generator.report.excel.annotation.ExcelQuery;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.constant.ExcelConstant;

/**
 * The Class AutoreLibriSheet.
 */
@ExcelSheetLayout(notMerge = false)
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelQuery(select = "select\n" + 
		"	sp.anno,sp.prezzo,l.id_libro,l.titolo,a.id_autore,a.nome,a.cognome,a.data_nascita,a.sesso,g.des_genere\n" + 
		"from\n" + 
		"	storico_prezzo sp\n" + 
		"inner join libro l on\n" + 
		"	sp.id_libro = l.id_libro\n" + 
		"inner join autore a on\n" + 
		"	a.id_autore = l.id_autore\n" + 
		"inner join genere g on\n" + 
		"	g.id_genere = l.id_genere;")
@ExcelPivot
public class AutoreLibriSheet extends QuerySheetData<AutoreLibriRow> {

	
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
	/**
	 * Gets the row class.
	 *
	 * @return the row class
	 */
	@Override
	public Class<AutoreLibriRow> getRowClass() {
		return AutoreLibriRow.class;
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
