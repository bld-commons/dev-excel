package bld.report.read.junit.entity;

import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;

public class OperatoriSanitariRow implements RowSheetRead {

	@ExcelReadColumn(value = "CodiceFiscale")
	private String codFisc;
	@ExcelReadColumn(value = "Nome")
	private String nome;
	@ExcelReadColumn(value = "Cognome")
	private String cognome;
	@ExcelReadColumn(value = "Presidio/Distretto")
	private String presidioDistretto;
	@ExcelReadColumn(value = "Struttura")
	private String struttura;
	@ExcelReadColumn(value = "NumeroMatricola")
	private String matricola;
	@ExcelReadColumn(value = "TipoSoggetto")
	private String tipoSoggetto;

	public String getCodFisc() {
		return codFisc;
	}

	public void setCodFisc(String codFisc) {
		this.codFisc = codFisc;
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

	public String getPresidioDistretto() {
		return presidioDistretto;
	}

	public void setPresidioDistretto(String presidioDistretto) {
		this.presidioDistretto = presidioDistretto;
	}

	public String getStruttura() {
		return struttura;
	}

	public void setStruttura(String struttura) {
		this.struttura = struttura;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public String getTipoSoggetto() {
		return tipoSoggetto;
	}

	public void setTipoSoggetto(String tipoSoggetto) {
		this.tipoSoggetto = tipoSoggetto;
	}

	@Override
	public String toString() {
		return "OperatoriSanitariRow [codFisc=" + codFisc + ", nome=" + nome + ", cognome=" + cognome + ", presidioDistretto=" + presidioDistretto + ", struttura=" + struttura + ", matricola=" + matricola + ", tipoSoggetto=" + tipoSoggetto + "]";
	}

}
