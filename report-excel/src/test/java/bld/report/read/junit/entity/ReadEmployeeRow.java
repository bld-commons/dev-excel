/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.read.junit.entity.ReadEmployeeRow
 */
package bld.report.read.junit.entity;

import java.util.Date;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;

/**
 * Read entity for an employee row read back from an Excel sheet.
 * <p>
 * Implements {@link com.bld.read.report.excel.domain.RowSheetRead}.
 * Each field is bound to its Excel column via {@link com.bld.read.report.excel.annotation.ExcelReadColumn}.
 * Numeric columns ({@code id}, {@code stipendio}) use {@code ignoreCellTypeString = true}
 * to tolerate cells formatted as text.  The {@code attivo} boolean field is resolved
 * from the text values "S&igrave;" / "No" via
 * {@link com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText}.
 * </p>
 */
public class ReadEmployeeRow implements RowSheetRead {

	@ExcelReadColumn(value = "ID", ignoreCellTypeString = true)
	private Integer id;

	@ExcelReadColumn(value = "Nome")
	private String nome;

	@ExcelReadColumn(value = "Cognome")
	private String cognome;

	@ExcelReadColumn(value = "Dipartimento")
	private String dipartimento;

	@ExcelReadColumn(value = "Data Assunzione")
	@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY)
	private Date dataAssunzione;

	@ExcelReadColumn(value = "Stipendio", ignoreCellTypeString = true)
	private Double stipendio;

	@ExcelReadColumn(value = "Attivo")
	@ExcelBooleanText(enable = "Sì", disable = "No")
	private Boolean attivo;

	@ExcelReadColumn(value = "Note")
	private String note;

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getCognome() { return cognome; }
	public void setCognome(String cognome) { this.cognome = cognome; }

	public String getDipartimento() { return dipartimento; }
	public void setDipartimento(String dipartimento) { this.dipartimento = dipartimento; }

	public Date getDataAssunzione() { return dataAssunzione; }
	public void setDataAssunzione(Date dataAssunzione) { this.dataAssunzione = dataAssunzione; }

	public Double getStipendio() { return stipendio; }
	public void setStipendio(Double stipendio) { this.stipendio = stipendio; }

	public Boolean getAttivo() { return attivo; }
	public void setAttivo(Boolean attivo) { this.attivo = attivo; }

	public String getNote() { return note; }
	public void setNote(String note) { this.note = note; }

	@Override
	public String toString() {
		return "ReadEmployeeRow [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dipartimento=" + dipartimento
				+ ", dataAssunzione=" + dataAssunzione + ", stipendio=" + stipendio + ", attivo=" + attivo + ", note=" + note + "]";
	}
}
