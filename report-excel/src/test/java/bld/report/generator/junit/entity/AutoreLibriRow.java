/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.AutoreLibriRow.java
*/
package bld.report.generator.junit.entity;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.common.spreadsheet.constant.ColumnDateFormat;
import bld.common.spreadsheet.excel.annotation.ExcelDate;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelColumnWidth;
import bld.generator.report.excel.annotation.ExcelDropDown;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelFormulaAlias;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.annotation.ExcelSubtotal;
import bld.generator.report.excel.annotation.ExcelSubtotals;
import bld.generator.report.excel.dropdown.CharacterDropDown;
import bld.generator.report.excel.dropdown.IntegerDropDown;

/**
 * The Class AutoreLibriRow.
 */
@ExcelFunctionRows(excelFunctions = {
		@ExcelFunctionRow(excelCellsLayout=@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2), 
					excelColumn = @ExcelColumn(index = 9, name = "Prezzo Totale"), 
					excelFunction=@ExcelFunction(function = "sum(${prezzo},${supplemento})", nameFunction = "prezzoTotale"),excelColumnWidth = @ExcelColumnWidth(width = 7)),
		@ExcelFunctionRow(excelCellsLayout=@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER), 
				excelColumn = @ExcelColumn(index = 10, name = "Test"), 
				excelFunction=@ExcelFunction(function = "${Test Date.dataA}", nameFunction = "test"))
		},
excelFunctionMerges = {@ExcelFunctionMergeRow(excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2), 
						excelColumn = @ExcelColumn(index = 7.1, name = "Prezzo Totale per Autore"),
						excelSubtotal = @ExcelSubtotal(enable = true, excelCellLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2,font=@ExcelFont(bold=true)), dataConsolidateFunction = DataConsolidateFunction.SUM),
						excelMergeRow = @ExcelMergeRow(referenceField = "matricola"), excelFunction=@ExcelFunction(function = "sum(${prezzoRowStart}:${prezzoRowEnd})",anotherTable = false,nameFunction = "prezzoTotalePerAutore"),excelHeaderCellLayout = @ExcelHeaderCellLayout(rgbForeground = @ExcelRgbColor(blue=0,green=0))),
		@ExcelFunctionMergeRow( 
		excelColumn = @ExcelColumn(index = 7.2, name = "Prezzo Totale per Autore con array"),
		excelMergeRow = @ExcelMergeRow(referenceField = "matricola"), excelFunction=@ExcelFunction(function = "${prezzo.field-value[start]}+sum(${prezzo[start]}:${prezzo[end]})",anotherTable = false,nameFunction = "prezzoTotalePerAutore1"),excelHeaderCellLayout = @ExcelHeaderCellLayout(rgbForeground = @ExcelRgbColor(blue=0,green=0)))		


})
@ExcelSubtotals(startLabel = "Totale", labelTotalGroup = "Totale")
public class AutoreLibriRow implements RowSheet {

	
	/** The nome. */
	@ExcelColumn(name = "${autore-libri-row.nome.name-column}",index = 2)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "matricola")
	private String nome;
	
	/** The cognome. */
	@ExcelColumn(name = "${autore-libri-row.cognome.name-column}",index = 3)
	@ExcelCellLayout(rgbForeground = @ExcelRgbColor(green=0,red=0))
	@ExcelMergeRow(referenceField = "matricola")
	private String cognome;
	
	/** The data di nascita. */
	@ExcelColumn(name = "Data di Nascita",index = 4)
	@ExcelDate(value = ColumnDateFormat.YYYY_MM_DD)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelMergeRow(referenceField = "matricola")
	private Calendar dataDiNascita;
	
	/** The titolo. */
	@ExcelColumn(name = "Titolo",index = 6)
	@ExcelHeaderCellLayout(wrap = false)
	@ExcelCellLayout(autoSizeColumn = true)
	private String titolo;
	
	/** The genere. */
	@ExcelColumn(name = "Genere",index = 5)
	@ExcelCellLayout
	@ExcelMergeRow(referenceField = "cognome")
	@ExcelDropDown(areaRange = "${genereStart}:${genereEnd}",alias = {
			@ExcelFormulaAlias(alias = "genereStart", coordinate = "genere[start]",sheet="Genere"),
			@ExcelFormulaAlias(alias = "genereEnd", coordinate = "genere[end]",sheet="Genere")})
	private String genere;
	
	/** The matricola. */
	@ExcelColumn(name = "${autore-libri-row.matricola.name-column}",index = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,locked = false)
	@ExcelMergeRow(referenceField = "")
	private Integer matricola;
	
	/** The prezzo. */
	@ExcelHeaderCellLayout(rgbForeground = @ExcelRgbColor(red=(byte)255,green=0,blue=0))
	@ExcelColumn(name = "Prezzo",index = 7)
	@ExcelColumnWidth(width=31)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM,excelCellLayout = @ExcelCellLayout(precision = 2,font = @ExcelFont(bold=true),horizontalAlignment = HorizontalAlignment.RIGHT))
	private Double prezzo;
	
	/** The supplemento. */
	@ExcelColumn(name = "Supplemento",index = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,precision = 2)
	@ExcelSubtotal(dataConsolidateFunction = DataConsolidateFunction.SUM,excelCellLayout = @ExcelCellLayout(precision = 2,font = @ExcelFont(bold=true),horizontalAlignment = HorizontalAlignment.RIGHT))
	private Double supplemento;
	
	
	@ExcelColumn(name = "Opzione",index = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private IntegerDropDown option;
	
	
	@ExcelColumn(name = "Opzione Char",index = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private CharacterDropDown optionChar;
	
	@ExcelColumn(name = "Create Date",index = 100)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate(ColumnDateFormat.DD_MM_YYYY)
	private Date createDate;

	@ExcelColumn(name = "Create Time",index = 110)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate(ColumnDateFormat.HH_MM_SS)
	private Date createTime;
	
	
	/**
	 * Instantiates a new autore libri row.
	 */
	public AutoreLibriRow() {
		super();
		this.option=new IntegerDropDown(null,Arrays.asList(0,1,2));
		this.optionChar=new CharacterDropDown(null, Arrays.asList('A','B','C'));
		this.createDate=new Date();
		this.createTime=new Date();
	}

	/**
	 * Instantiates a new autore libri row.
	 *
	 * @param nome          the nome
	 * @param cognome       the cognome
	 * @param dataDiNascita the data di nascita
	 * @param titolo        the titolo
	 * @param genere        the genere
	 * @param matricola     the matricola
	 * @param prezzo        the prezzo
	 * @param supplemento   the supplemento
	 */
	public AutoreLibriRow(String nome, String cognome, Calendar dataDiNascita, String titolo, String genere, Integer matricola,Double prezzo,Double supplemento) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.titolo = titolo;
		this.genere = genere;
		this.matricola = matricola;
		this.prezzo=prezzo;
		this.supplemento=supplemento;
		this.option=new IntegerDropDown(null,Arrays.asList(0,1,2));
		this.optionChar=new CharacterDropDown(null, Arrays.asList('A','B','C'));
		this.createDate=new Date();
		this.createTime=new Date();
	}

	/**
	 * Gets the supplemento.
	 *
	 * @return the supplemento
	 */
	public Double getSupplemento() {
		return supplemento;
	}

	/**
	 * Sets the supplemento.
	 *
	 * @param supplemento the new supplemento
	 */
	public void setSupplemento(Double supplemento) {
		this.supplemento = supplemento;
	}

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
	 * Gets the cognome.
	 *
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * Sets the cognome.
	 *
	 * @param cognome the new cognome
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	 * Gets the titolo.
	 *
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Sets the titolo.
	 *
	 * @param titolo the new titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Gets the genere.
	 *
	 * @return the genere
	 */
	public String getGenere() {
		return genere;
	}

	/**
	 * Sets the genere.
	 *
	 * @param genere the new genere
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}

	/**
	 * Gets the matricola.
	 *
	 * @return the matricola
	 */
	public Integer getMatricola() {
		return matricola;
	}

	/**
	 * Sets the matricola.
	 *
	 * @param matricola the new matricola
	 */
	public void setMatricola(Integer matricola) {
		this.matricola = matricola;
	}

	/**
	 * Gets the prezzo.
	 *
	 * @return the prezzo
	 */
	public Double getPrezzo() {
		return prezzo;
	}

	/**
	 * Sets the prezzo.
	 *
	 * @param prezzo the new prezzo
	 */
	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public IntegerDropDown getOption() {
		return option;
	}

	public void setOption(IntegerDropDown option) {
		this.option = option;
	}

	public CharacterDropDown getOptionChar() {
		return optionChar;
	}

	public void setOptionChar(CharacterDropDown optionChar) {
		this.optionChar = optionChar;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
