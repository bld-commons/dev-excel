/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.junit.entity.TotaleAutoreLibriRow.java
*/
package bld.generator.report.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelFunctionRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRows;

/**
 * The Class TotaleAutoreLibriRow.
 */
@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2), 
				excelColumn = @ExcelColumn(index = 8, name = "Totale Prezzo"), 
				excelFunction = @ExcelFunction(function = "sum(${prezzoRowStart}:${prezzoRowEnd})", nameFunction = "totalePrezzo")) })
public class TotaleAutoreLibriRow implements RowSheet {

	@ExcelColumn(name = "Totale", index = 0)
	@ExcelCellLayout 
	private String totale;
	@ExcelColumn(name = "", index = 7)
	@ExcelCellLayout
	private Integer totAnno;	
	@ExcelColumn(name = "", index = 6)
	@ExcelCellLayout
	private String totTitolo;
	@ExcelColumn(name = "", index = 1)
	@ExcelCellLayout
	private String totNome;
	@ExcelColumn(name = "", index = 2)
	@ExcelCellLayout
	private String totCognome;
	@ExcelColumn(name = "", index = 3)
	@ExcelCellLayout
	private String totDataNascita;
	@ExcelColumn(name = "", index = 4)
	@ExcelCellLayout
	private Character totSesso;
	@ExcelColumn(name = "", index = 5)
	@ExcelCellLayout
	private String totDesGenere;
	
	public TotaleAutoreLibriRow(String totale) {
		super();
		this.totale = totale;
	}
	public String getTotale() {
		return totale;
	}
	public void setTotale(String totale) {
		this.totale = totale;
	}
	public Integer getTotAnno() {
		return totAnno;
	}
	public void setTotAnno(Integer totAnno) {
		this.totAnno = totAnno;
	}
	public String getTotTitolo() {
		return totTitolo;
	}
	public void setTotTitolo(String totTitolo) {
		this.totTitolo = totTitolo;
	}
	public String getTotNome() {
		return totNome;
	}
	public void setTotNome(String totNome) {
		this.totNome = totNome;
	}
	public String getTotCognome() {
		return totCognome;
	}
	public void setTotCognome(String totCognome) {
		this.totCognome = totCognome;
	}
	public String getTotDataNascita() {
		return totDataNascita;
	}
	public void setTotDataNascita(String totDataNascita) {
		this.totDataNascita = totDataNascita;
	}
	public Character getTotSesso() {
		return totSesso;
	}
	public void setTotSesso(Character totSesso) {
		this.totSesso = totSesso;
	}
	public String getTotDesGenere() {
		return totDesGenere;
	}
	public void setTotDesGenere(String totDesGenere) {
		this.totDesGenere = totDesGenere;
	}

	
	
	

}
