/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.CasaEditrice.java
*/
package bld.generator.report.junit.entity;

import java.util.Calendar;

import jakarta.validation.constraints.Size;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.SheetSummary;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelFunctionRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRows;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelImage;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelRowHeight;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;
import com.bld.generator.report.excel.annotation.ExcelSummary;

/**
 * The Class CasaEditrice.
 */
@ExcelSheetLayout(startRow = 1,startColumn = 1)
@ExcelSummary(title = "Casa Editrice")
@ExcelHeaderLayout
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelFunction = @ExcelFunction(function = "sum(${Libri d'autore.prezzoRowStart}:${Libri d'autore.prezzoRowEnd})", nameFunction = "sommaAutore"), excelColumn = @ExcelColumn(index = 7, name = "test somma"))
})
public class CasaEditrice extends SheetSummary {


	/** The nome. */
	@ExcelColumn(name = "Nome", index = 1, comment = "Test comment")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private String nome;

	/** The data di nascita. */
	@ExcelColumn(name = "Data di nascita", index = 2)
	@ExcelDate
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Calendar dataDiNascita;

	/** The citta. */
	@ExcelColumn(name = "Città", index = 3)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelRowHeight(height = 5)
	private String citta;

	@ExcelColumn(name = "Genere A", index = 4)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer generaA;

	@ExcelColumn(name = "Genere B", index = 5)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer genereB;
	
	@ExcelColumn(name = "Image", index = 6)
	@ExcelCellLayout
	@ExcelImage
	@ExcelRowHeight(height = 5)
	private String image;
	
	@ExcelColumn(name = "Genere", index = 5.5)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@ExcelDropDown(areaRange = "${Genere.genere[start]}:${Genere.genere[end]}",suppressDropDownArrow = true)
	private String desGenere;
	


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

	public CasaEditrice(@Size(max = 31) String sheetName, String nome, Calendar dataDiNascita, String citta, String image,String desGenere) {
		super(sheetName);
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.citta = citta;
		this.image = image;
		this.desGenere=desGenere;
	}
	
	
	public CasaEditrice(@Size(max = 31) String sheetName, String nome, Calendar dataDiNascita, String citta, Integer generaA, Integer genereB, String image,String desGenere) {
		super(sheetName);
		this.nome = nome;
		this.dataDiNascita = dataDiNascita;
		this.citta = citta;
		this.generaA = generaA;
		this.genereB = genereB;
		this.image = image;
		this.desGenere=desGenere;
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

	public String getDesGenere() {
		return desGenere;
	}

	public void setDesGenere(String desGenere) {
		this.desGenere = desGenere;
	}

	
	
}
