/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.read.junit.entity.ReadEmployeeCsvRow
 */
package bld.report.read.junit.entity;

import java.util.Date;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.csv.annotation.CsvDate;
import com.bld.common.spreadsheet.csv.annotation.CsvSettings;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;

/**
 * CSV read entity for an employee row.
 * <p>
 * Implements {@link com.bld.read.report.excel.domain.RowSheetRead}.
 * The class-level {@link com.bld.common.spreadsheet.csv.annotation.CsvSettings} configures
 * comma as the delimiter and instructs the parser to skip the header record row.
 * The {@code dataAssunzione} field uses {@link com.bld.common.spreadsheet.csv.annotation.CsvDate}
 * with the {@code DD_MM_YYYY} format for date parsing.
 * </p>
 */
@CsvSettings(skipHeaderRecord = true, delimiter = ',')
public class ReadEmployeeCsvRow implements RowSheetRead {

	@ExcelReadColumn(value = "ID")
	private Integer id;

	@ExcelReadColumn(value = "Nome")
	private String nome;

	@ExcelReadColumn(value = "Cognome")
	private String cognome;

	@ExcelReadColumn(value = "Dipartimento")
	private String dipartimento;

	@ExcelReadColumn(value = "Data Assunzione")
	@CsvDate(value = ColumnDateFormat.DD_MM_YYYY)
	private Date dataAssunzione;

	@ExcelReadColumn(value = "Stipendio")
	private Double stipendio;

	@ExcelReadColumn(value = "Attivo")
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
		return "ReadEmployeeCsvRow [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dipartimento=" + dipartimento
				+ ", dataAssunzione=" + dataAssunzione + ", stipendio=" + stipendio + ", attivo=" + attivo + ", note=" + note + "]";
	}
}
