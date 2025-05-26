package bld.report.read.junit.entity;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import bld.common.spreadsheet.csv.annotation.CsvSettings;
import bld.read.report.excel.annotation.ExcelReadColumn;
import bld.read.report.excel.domain.RowSheetRead;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@CsvSettings(skipHeaderRecord = true)
public class RendicontazioneMassivaImportColumn implements RowSheetRead {

    @ExcelReadColumn(name = "CF/P.IVA")
    @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]|^$", message = " colonna CF/P.IVA non riporta un valore nel giusto formato")
    @NotNull(message = " colonna CF/P.IVA non è presente il dato")
    @NotEmpty(message = " colonna CF/P.IVA non è presente il dato")
    private String codFiscale;

    @ExcelReadColumn(name = "Cognome e Nome")
    @NotNull(message = " colonna Cognome e Nome non è presente il dato")
    @NotEmpty(message = " colonna Cognome e Nome non è presente il dato")
    @Pattern(regexp = "^[a-zA-Z\\s']{1,100}$", message = " colonna Cognome e Nome non riporta un valore nel giusto formato")
    private String nominativo;

    @ExcelReadColumn(name = "Codice Progetto")
    @NotNull(message = " colonna Codice progetto non è presente il dato")
    @NotEmpty(message = " colonna Codice progetto non è presente il dato")
    private String codProgetto;

    @ExcelReadColumn(name = "Linea")
    @NotNull(message = " colonna Linea non è presente il dato")
    @NotEmpty(message = " colonna Linea non è presente il dato")
    private String linea;

    @ExcelReadColumn(name = "Sotto Linea")
    @NotNull(message = " colonna Sotto Linea non è presente il dato")
    @NotEmpty(message = " colonna Sotto Linea non è presente il dato")
    private String sottoLinea;

    @ExcelReadColumn(name = "Mese")
    @NotNull(message = " colonna mese non è presente il dato")
    @Min(value = 1, message = " colonna Mese riporta un valore minore di 1")
    @Max(value = 12, message = " colonna Mese riporta un valore maggiore di 12")
    private Integer mese;

    @ExcelReadColumn(name = "Anno")
    @NotNull(message = " colonna Anno non è presente il dato")
    @Min(value = 1900, message = " colonna Anno riporta un valore minore di 1900")
    @Max(value = 9999, message = " colonna Anno riporta un valore con più di 4 cifre")
    private Integer anno;

    @ExcelReadColumn(name = "Giorni erogati")
    @NotNull(message = " colonna Giorni Erogati non è presente il dato")
    @Min(value = 1, message = " colonna Giorni Erogati riporta un valore minore di 1")
    @Max(value = 31, message = " colonna Giorni Erogati riporta un valore maggiore di 31")
    private Long ggErogati;

    @ExcelReadColumn(name = "Ruolo")
    @NotNull(message = " colonna Ruolo non è presente il dato")
    @NotEmpty(message = " colonna Ruolo non è presente il dato")
    private String ruolo;

    @ExcelReadColumn(name = "ID SAS")
    @NotNull(message = " colonna ID SAS non è presente il dato")
    @NotEmpty(message = " colonna ID SAS non è presente il dato")
    @Pattern(regexp = "[A-zÀ-ú0-9#_$^\\\"\\'\\-+=!*()@%&.,:;/ ]{0,100}|^$", message = " colonna ID SAS non riporta un valore nel giusto formato")
    private String identificativoSas;

    private int numeroRiga;

    public boolean checkEdit(RendicontazioneMassivaImportColumn that) {

        return Objects.equals(codFiscale, that.codFiscale) && Objects.equals(nominativo, that.nominativo)
                && Objects.equals(codProgetto, that.codProgetto) && Objects.equals(linea, that.linea)
                && Objects.equals(sottoLinea, that.sottoLinea) && Objects.equals(mese, that.mese)
                && Objects.equals(anno, that.anno) && Objects.equals(ruolo, that.ruolo)
                && Objects.equals(identificativoSas, that.identificativoSas);
    }

}