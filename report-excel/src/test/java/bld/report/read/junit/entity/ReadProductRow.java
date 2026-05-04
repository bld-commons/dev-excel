/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.read.junit.entity.ReadProductRow
 */
package bld.report.read.junit.entity;

import java.util.Date;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;

/**
 * Read entity for a product row read back from an Excel sheet.
 * <p>
 * Implements {@link com.bld.read.report.excel.domain.RowSheetRead}.
 * Each field is bound to its Excel column via {@link com.bld.read.report.excel.annotation.ExcelReadColumn}.
 * Numeric columns ({@code prezzo}, {@code quantita}) use {@code ignoreCellTypeString = true}
 * to tolerate cells formatted as text.  The {@code disponibile} boolean field is resolved
 * from the text values "S&igrave;" / "No" via
 * {@link com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText}.
 * </p>
 */
public class ReadProductRow implements RowSheetRead {

	@ExcelReadColumn(value = "Codice")
	private String codice;

	@ExcelReadColumn(value = "Descrizione")
	private String descrizione;

	@ExcelReadColumn(value = "Categoria")
	private String categoria;

	@ExcelReadColumn(value = "Fornitore")
	private String fornitore;

	@ExcelReadColumn(value = "Prezzo", ignoreCellTypeString = true)
	private Double prezzo;

	@ExcelReadColumn(value = "Quantita", ignoreCellTypeString = true)
	private Integer quantita;

	@ExcelReadColumn(value = "Data Creazione")
	@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY)
	private Date dataCreazione;

	@ExcelReadColumn(value = "Disponibile")
	@ExcelBooleanText(enable = "Sì", disable = "No")
	private Boolean disponibile;

	public String getCodice() { return codice; }
	public void setCodice(String codice) { this.codice = codice; }

	public String getDescrizione() { return descrizione; }
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

	public String getCategoria() { return categoria; }
	public void setCategoria(String categoria) { this.categoria = categoria; }

	public String getFornitore() { return fornitore; }
	public void setFornitore(String fornitore) { this.fornitore = fornitore; }

	public Double getPrezzo() { return prezzo; }
	public void setPrezzo(Double prezzo) { this.prezzo = prezzo; }

	public Integer getQuantita() { return quantita; }
	public void setQuantita(Integer quantita) { this.quantita = quantita; }

	public Date getDataCreazione() { return dataCreazione; }
	public void setDataCreazione(Date dataCreazione) { this.dataCreazione = dataCreazione; }

	public Boolean getDisponibile() { return disponibile; }
	public void setDisponibile(Boolean disponibile) { this.disponibile = disponibile; }

	@Override
	public String toString() {
		return "ReadProductRow [codice=" + codice + ", descrizione=" + descrizione + ", categoria=" + categoria
				+ ", fornitore=" + fornitore + ", prezzo=" + prezzo + ", quantita=" + quantita
				+ ", dataCreazione=" + dataCreazione + ", disponibile=" + disponibile + "]";
	}
}
