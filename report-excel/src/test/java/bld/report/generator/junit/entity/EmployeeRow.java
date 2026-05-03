/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.generator.junit.entity.EmployeeRow
 */
package bld.report.generator.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.csv.annotation.CsvDate;
import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.csv.CsvRow;
import com.bld.generator.report.csv.annotation.CsvColumn;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelFunctionRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRows;

/**
 * Test entity representing a company employee row for both Excel and CSV export.
 * <p>
 * Implements {@link com.bld.generator.report.excel.RowSheet} for Excel generation and
 * {@link com.bld.generator.report.csv.CsvRow} for CSV export.
 * An {@link com.bld.generator.report.excel.annotation.ExcelFunctionRows} annotation adds a
 * computed <em>Tassazione</em> column (column 9) that applies a progressive Italian tax formula
 * based on the {@code stipendio} field.
 * The {@code attivo} boolean field is rendered as "S&igrave;" / "No" via
 * {@link com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText}.
 * </p>
 */
@ExcelFunctionRows(excelFunctions = {
    @ExcelFunctionRow(
        excelColumn = @ExcelColumn(index = 9, name = "Tassazione"),
        excelCellsLayout = @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2),
        excelFunction = @ExcelFunction(
            function = "IF(${stipendio}<=28000,${stipendio}*0.23,IF(${stipendio}<=50000,${stipendio}*0.35,${stipendio}*0.43))",
            nameFunction = "tassazione"
        )
    )
})
public class EmployeeRow implements RowSheet, CsvRow {

	@ExcelColumn(name = "ID", index = 1)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	@CsvColumn(name = "ID", index = 1)
	private Integer id;

	@ExcelColumn(name = "Nome", index = 2)
	@ExcelCellLayout
	@CsvColumn(name = "Nome", index = 2)
	private String nome;

	@ExcelColumn(name = "Cognome", index = 3)
	@ExcelCellLayout
	@CsvColumn(name = "Cognome", index = 3)
	private String cognome;

	@ExcelColumn(name = "Dipartimento", index = 4)
	@ExcelCellLayout
	@CsvColumn(name = "Dipartimento", index = 4)
	private String dipartimento;

	@ExcelColumn(name = "Data Assunzione", index = 5)
	@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@CsvColumn(name = "Data Assunzione", index = 5)
	@CsvDate(value = ColumnDateFormat.DD_MM_YYYY)
	private Date dataAssunzione;

	@ExcelColumn(name = "Stipendio", index = 6)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
	@CsvColumn(name = "Stipendio", index = 6)
	private Double stipendio;

	@ExcelColumn(name = "Attivo", index = 7)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelBooleanText(enable = "Sì", disable = "No")
	@CsvColumn(name = "Attivo", index = 7)
	private Boolean attivo;

	@ExcelColumn(name = "Note", index = 8)
	@ExcelCellLayout
	@CsvColumn(name = "Note", index = 8)
	private String note;

	public EmployeeRow() {
		super();
	}

	public EmployeeRow(Integer id, String nome, String cognome, String dipartimento, Date dataAssunzione,
			Double stipendio, Boolean attivo, String note) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dipartimento = dipartimento;
		this.dataAssunzione = dataAssunzione;
		this.stipendio = stipendio;
		this.attivo = attivo;
		this.note = note;
	}

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
		return "EmployeeRow [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dipartimento=" + dipartimento
				+ ", dataAssunzione=" + dataAssunzione + ", stipendio=" + stipendio + ", attivo=" + attivo + ", note=" + note + "]";
	}
}
