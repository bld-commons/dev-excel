package bld.generator.report.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import bld.common.spreadsheet.excel.annotation.ExcelDate;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelImage;
import bld.generator.report.excel.annotation.ExcelRowHeight;

@ExcelRowHeight(height = 3)
public class UtenteRow implements RowSheet {
	
	@ExcelColumn(name = "Id", index = 0)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer idUtente; 
	@ExcelColumn(name = "Nome", index = 2)
	@ExcelCellLayout
	private String nome; 
	@ExcelColumn(name = "Cognome", index = 1)
	@ExcelCellLayout
	private String cognome;
	@ExcelColumn(name = "Data di nascita", index = 3)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dataNascita;
	@ExcelColumn(name = "Immagine", index = 4)
	@ExcelCellLayout
	@ExcelImage
	private byte[] image;	
	
	@ExcelColumn(name = "Path", index = 5)
	@ExcelCellLayout
	@ExcelImage(resizeHeight = 0.7, resizeWidth = 0.6)
	private String path;	
	

	@ExcelColumn(name = "Abilitato", index = 6)
	@ExcelBooleanText(enable = "Enable",disable = "Disable")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	private Boolean abilitato;
	
	
	public UtenteRow() {
	}


	public UtenteRow(Integer idUtente, String nome, String cognome, Date dataNascita) {
		super();
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
	}


	public Integer getIdUtente() {
		return idUtente;
	}


	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public Date getDataNascita() {
		return dataNascita;
	}


	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}


	public byte[] getImage() {
		return image;
	}


	public String getPath() {
		return path;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Boolean getAbilitato() {
		return abilitato;
	}


	public void setAbilitato(Boolean abilitato) {
		this.abilitato = abilitato;
	}

	
	
}
