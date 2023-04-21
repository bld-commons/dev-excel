/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.CasaEditrice.java
*/
package bld.report.generator.junit.entity;

import java.util.Calendar;

import javax.validation.constraints.Size;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.common.spreadsheet.excel.annotation.ExcelDate;
import bld.generator.report.excel.ExcelAttachment;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelImage;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelRowHeight;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSummary;

/**
 * The Class CasaEditrice.
 */
@ExcelSheetLayout(startRow = 1,startColumn = 1,landscape = false)
@ExcelSummary(title = "${title.field-value}")
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelFunction = @ExcelFunction(function = "sum(${Libri d'autore.prezzoRowStart}:${Libri d'autore.prezzoRowEnd})", nameFunction = "sommaAutore"), excelColumn = @ExcelColumn(indexColumn = 7, columnName = "test somma"))
})
public class CasaEditrice extends SheetSummary {

	private String title;
	
	
	/** The nome. */
	@ExcelColumn(columnName = "Nome", indexColumn = 1, comment = "Test comment")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String nome;

	/** The data di nascita. */
	@ExcelColumn(columnName = "Data di nascita", indexColumn = 2)
	@ExcelDate
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Calendar dataDiNascita;

	/** The citta. */
	@ExcelColumn(columnName = "Città", indexColumn = 3)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelRowHeight(height = 5)
	private String citta;

	@ExcelColumn(columnName = "Genere A", indexColumn = 4)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer generaA;

	@ExcelColumn(columnName = "Genere B", indexColumn = 5)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer genereB;
	
	@ExcelColumn(columnName = "Image", indexColumn = 6)
	@ExcelCellLayout
	@ExcelImage
	@ExcelRowHeight(height = 5)
	private String image;
	
	@ExcelColumn(columnName = "Document", indexColumn = 7)
	@ExcelCellLayout
	@ExcelRowHeight(height = 5)
	private ExcelAttachment<String> document;


	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the data di nascita.
	 *
	 * @return the data di nascita
	 */
	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * Sets the data di nascita.
	 *
	 * @param dataDiNascita the new data di nascita
	 */
	public void setDataDiNascita(Calendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * Gets the citta.
	 *
	 * @return the citta
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Sets the citta.
	 *
	 * @param citta the new citta
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	public CasaEditrice(@Size(max = 31) String sheetName, String nome, Calendar dataDiNascita, String citta, String image, ExcelAttachment<String> document) {
		super(sheetName);
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.citta = citta;
		this.image = image;
		this.document=document;
	}
	
	
	public CasaEditrice(@Size(max = 31) String sheetName, String nome, Calendar dataDiNascita, String citta, Integer generaA, Integer genereB, String image) {
		super(sheetName);
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.citta = citta;
		this.generaA = generaA;
		this.genereB = genereB;
		this.image = image;
	}

	/**
	 * Instantiates a new casa editrice.
	 *
	 * @param nameSheet the name sheet
	 */
	public CasaEditrice(@Size(max = 30) String nameSheet) {
		super(nameSheet);
	}

	public Integer getGeneraA() {
		return generaA;
	}

	public void setGeneraA(Integer generaA) {
		this.generaA = generaA;
	}

	public Integer getGenereB() {
		return genereB;
	}

	public void setGenereB(Integer genereB) {
		this.genereB = genereB;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ExcelAttachment<String> getDocument() {
		return document;
	}

	public void setDocument(ExcelAttachment<String> document) {
		this.document = document;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
}
