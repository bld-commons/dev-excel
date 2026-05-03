/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.report.generator.junit.entity.ProductRow
 */
package bld.report.generator.junit.entity;

import java.util.Date;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;

/**
 * Test entity representing a product catalogue row for Excel generation.
 * <p>
 * Implements {@link com.bld.generator.report.excel.RowSheet}.
 * Fields include product code, description, category, supplier, price, quantity,
 * creation date, and availability flag.  The {@code disponibile} boolean field is
 * rendered as "S&igrave;" / "No" via
 * {@link com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText}.
 * </p>
 */
public class ProductRow implements RowSheet {

	@ExcelColumn(name = "Codice", index = 1)
	@ExcelCellLayout
	private String codice;

	@ExcelColumn(name = "Descrizione", index = 2)
	@ExcelCellLayout
	private String descrizione;

	@ExcelColumn(name = "Categoria", index = 3)
	@ExcelCellLayout
	private String categoria;

	@ExcelColumn(name = "Fornitore", index = 4)
	@ExcelCellLayout
	private String fornitore;

	@ExcelColumn(name = "Prezzo", index = 5)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT, precision = 2)
	private Double prezzo;

	@ExcelColumn(name = "Quantita", index = 6)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer quantita;

	@ExcelColumn(name = "Data Creazione", index = 7)
	@ExcelDate(value = ColumnDateFormat.DD_MM_YYYY)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	private Date dataCreazione;

	@ExcelColumn(name = "Disponibile", index = 8)
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.CENTER)
	@ExcelBooleanText(enable = "Sì", disable = "No")
	private Boolean disponibile;

	public ProductRow() {
		super();
	}

	public ProductRow(String codice, String descrizione, String categoria, String fornitore,
			Double prezzo, Integer quantita, Date dataCreazione, Boolean disponibile) {
		super();
		this.codice = codice;
		this.descrizione = descrizione;
		this.categoria = categoria;
		this.fornitore = fornitore;
		this.prezzo = prezzo;
		this.quantita = quantita;
		this.dataCreazione = dataCreazione;
		this.disponibile = disponibile;
	}

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
		return "ProductRow [codice=" + codice + ", descrizione=" + descrizione + ", categoria=" + categoria
				+ ", fornitore=" + fornitore + ", prezzo=" + prezzo + ", quantita=" + quantita
				+ ", dataCreazione=" + dataCreazione + ", disponibile=" + disponibile + "]";
	}
}
