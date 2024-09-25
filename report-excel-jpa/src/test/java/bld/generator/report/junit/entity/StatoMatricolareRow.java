package bld.generator.report.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;

public class StatoMatricolareRow implements RowSheet {

	@ExcelColumn(index = 0, name = "id")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long idSeqDipMatricolare;

	@ExcelColumn(index = 1, name = "id dipendente")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long idDipendente;

	@ExcelColumn(index = 2, name = "id pratica")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long idPratica;

	@ExcelColumn(index = 3, name = "note")
	@ExcelCellLayout
	private String noteProgressione;

	@ExcelColumn(index = 4, name = "contratto nazionale")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long codRiferimentoCcn;

	@ExcelColumn(index = 5, name = "tipo contratto")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long codFormaLavoro;

	@ExcelColumn(index = 6, name = "dipartimento")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long codDipartimento;

	@ExcelColumn(index = 7, name = "area")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long codArea;

	@ExcelColumn(index = 8, name = "fascia")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long codFascia;

	@ExcelColumn(index = 9, name = "profilo")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Long codProfilo;

	@ExcelColumn(index = 10, name = "data inizio")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dtInizioValidita;

	@ExcelColumn(index = 11, name = "data fine")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelDate
	private Date dtFineValidita;

	public Long getIdSeqDipMatricolare() {
		return idSeqDipMatricolare;
	}

	public void setIdSeqDipMatricolare(Long idSeqDipMatricolare) {
		this.idSeqDipMatricolare = idSeqDipMatricolare;
	}

	public Long getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(Long idDipendente) {
		this.idDipendente = idDipendente;
	}

	public Long getIdPratica() {
		return idPratica;
	}

	public void setIdPratica(Long idPratica) {
		this.idPratica = idPratica;
	}

	public String getNoteProgressione() {
		return noteProgressione;
	}

	public void setNoteProgressione(String noteProgressione) {
		this.noteProgressione = noteProgressione;
	}

	public Long getCodRiferimentoCcn() {
		return codRiferimentoCcn;
	}

	public void setCodRiferimentoCcn(Long codRiferimentoCcn) {
		this.codRiferimentoCcn = codRiferimentoCcn;
	}

	public Long getCodFormaLavoro() {
		return codFormaLavoro;
	}

	public void setCodFormaLavoro(Long codFormaLavoro) {
		this.codFormaLavoro = codFormaLavoro;
	}

	public Long getCodDipartimento() {
		return codDipartimento;
	}

	public void setCodDipartimento(Long codDipartimento) {
		this.codDipartimento = codDipartimento;
	}

	public Long getCodArea() {
		return codArea;
	}

	public void setCodArea(Long codArea) {
		this.codArea = codArea;
	}

	public Long getCodFascia() {
		return codFascia;
	}

	public void setCodFascia(Long codFascia) {
		this.codFascia = codFascia;
	}

	public Long getCodProfilo() {
		return codProfilo;
	}

	public void setCodProfilo(Long codProfilo) {
		this.codProfilo = codProfilo;
	}

	public Date getDtInizioValidita() {
		return dtInizioValidita;
	}

	public void setDtInizioValidita(Date dtInizioValidita) {
		this.dtInizioValidita = dtInizioValidita;
	}

	public Date getDtFineValidita() {
		return dtFineValidita;
	}

	public void setDtFineValidita(Date dtFineValidita) {
		this.dtFineValidita = dtFineValidita;
	}

	
	
}
