package bld.report.read.junit.entity;

import com.bld.read.report.excel.annotation.ExcelReadColumn;
import com.bld.read.report.excel.domain.RowSheetRead;

/**
 * The Class DataMeteo.
 */
public class DataMeteoRow implements RowSheetRead {

	/** The year. */
	@ExcelReadColumn(value="YY",ignoreCellTypeString = true)
	private Integer year;

	/** The month. */
	@ExcelReadColumn(value="MM",ignoreCellTypeString = true)
	private Integer month;

	/** The day. */
	@ExcelReadColumn(value="DD",ignoreCellTypeString = true)
	private Integer day;

	/** The temperatura max. */
	@ExcelReadColumn(value="Tn",ignoreCellTypeString = true)
	private Double temperaturaMax;
	
	/** The temperatura min. */
	@ExcelReadColumn(value="Tx",ignoreCellTypeString = true)
	private Double temperaturaMin;
	
	/** The direzione vento. */
	@ExcelReadColumn(value="WD",ignoreCellTypeString = true)
	private Double direzioneVento;
	
	/** The velocita vento. */
	@ExcelReadColumn(value="WS",ignoreCellTypeString = true)
	private Double velocitaVento;
	
	/** The umidita max. */
	@ExcelReadColumn(value="RHn",ignoreCellTypeString = true)
	private Double umiditaMax;
	
	/** The umidita min. */
	@ExcelReadColumn(value="RHx",ignoreCellTypeString = true)
	private Double umiditaMin;
	
	/** The precipitazione am. */
	@ExcelReadColumn(value="RR0-12",ignoreCellTypeString = true)
	private Double precipitazioneAm;
	
	/** The precipitazione pm. */
	@ExcelReadColumn(value="RR12-24",ignoreCellTypeString = true)
	private Double precipitazionePm;
	
	/** The manto nevoso. */
	@ExcelReadColumn(value="SD",ignoreCellTypeString = true)
	private Double mantoNevoso;
	
	/** The radiazione solare. */
	@ExcelReadColumn(value="SunRAD",ignoreCellTypeString = true)
	private Double radiazioneSolare;
	
	/** The durata soleggiamento. */
	@ExcelReadColumn(value="SunDUR",ignoreCellTypeString = true)
	private Double durataSoleggiamento;

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Gets the month.
	 *
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * Sets the month.
	 *
	 * @param month the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * Sets the day.
	 *
	 * @param day the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * Gets the temperatura max.
	 *
	 * @return the temperaturaMax
	 */
	public Double getTemperaturaMax() {
		return temperaturaMax;
	}

	/**
	 * Sets the temperatura max.
	 *
	 * @param temperaturaMax the temperaturaMax to set
	 */
	public void setTemperaturaMax(Double temperaturaMax) {
		this.temperaturaMax = temperaturaMax;
	}

	/**
	 * Gets the temperatura min.
	 *
	 * @return the temperaturaMin
	 */
	public Double getTemperaturaMin() {
		return temperaturaMin;
	}

	/**
	 * Sets the temperatura min.
	 *
	 * @param temperaturaMin the temperaturaMin to set
	 */
	public void setTemperaturaMin(Double temperaturaMin) {
		this.temperaturaMin = temperaturaMin;
	}

	/**
	 * Gets the direzione vento.
	 *
	 * @return the direzioneVento
	 */
	public Double getDirezioneVento() {
		return direzioneVento;
	}

	/**
	 * Sets the direzione vento.
	 *
	 * @param direzioneVento the direzioneVento to set
	 */
	public void setDirezioneVento(Double direzioneVento) {
		this.direzioneVento = direzioneVento;
	}

	/**
	 * Gets the velocita vento.
	 *
	 * @return the velocitaVento
	 */
	public Double getVelocitaVento() {
		return velocitaVento;
	}

	/**
	 * Sets the velocita vento.
	 *
	 * @param velocitaVento the velocitaVento to set
	 */
	public void setVelocitaVento(Double velocitaVento) {
		this.velocitaVento = velocitaVento;
	}

	/**
	 * Gets the umidita max.
	 *
	 * @return the umiditaMax
	 */
	public Double getUmiditaMax() {
		return umiditaMax;
	}

	/**
	 * Sets the umidita max.
	 *
	 * @param umiditaMax the umiditaMax to set
	 */
	public void setUmiditaMax(Double umiditaMax) {
		this.umiditaMax = umiditaMax;
	}

	/**
	 * Gets the umidita min.
	 *
	 * @return the umiditaMin
	 */
	public Double getUmiditaMin() {
		return umiditaMin;
	}

	/**
	 * Sets the umidita min.
	 *
	 * @param umiditaMin the umiditaMin to set
	 */
	public void setUmiditaMin(Double umiditaMin) {
		this.umiditaMin = umiditaMin;
	}

	/**
	 * Gets the precipitazione am.
	 *
	 * @return the precipitazioneAm
	 */
	public Double getPrecipitazioneAm() {
		return precipitazioneAm;
	}

	/**
	 * Sets the precipitazione am.
	 *
	 * @param precipitazioneAm the precipitazioneAm to set
	 */
	public void setPrecipitazioneAm(Double precipitazioneAm) {
		this.precipitazioneAm = precipitazioneAm;
	}

	/**
	 * Gets the precipitazione pm.
	 *
	 * @return the precipitazionePm
	 */
	public Double getPrecipitazionePm() {
		return precipitazionePm;
	}

	/**
	 * Sets the precipitazione pm.
	 *
	 * @param precipitazionePm the precipitazionePm to set
	 */
	public void setPrecipitazionePm(Double precipitazionePm) {
		this.precipitazionePm = precipitazionePm;
	}

	/**
	 * Gets the manto nevoso.
	 *
	 * @return the mantoNevoso
	 */
	public Double getMantoNevoso() {
		return mantoNevoso;
	}

	/**
	 * Sets the manto nevoso.
	 *
	 * @param mantoNevoso the mantoNevoso to set
	 */
	public void setMantoNevoso(Double mantoNevoso) {
		this.mantoNevoso = mantoNevoso;
	}

	/**
	 * Gets the radiazione solare.
	 *
	 * @return the radiazioneSolare
	 */
	public Double getRadiazioneSolare() {
		return radiazioneSolare;
	}

	/**
	 * Sets the radiazione solare.
	 *
	 * @param radiazioneSolare the radiazioneSolare to set
	 */
	public void setRadiazioneSolare(Double radiazioneSolare) {
		this.radiazioneSolare = radiazioneSolare;
	}

	/**
	 * Gets the durata soleggiamento.
	 *
	 * @return the durataSoleggiamento
	 */
	public Double getDurataSoleggiamento() {
		return durataSoleggiamento;
	}

	/**
	 * Sets the durata soleggiamento.
	 *
	 * @param durataSoleggiamento the durataSoleggiamento to set
	 */
	public void setDurataSoleggiamento(Double durataSoleggiamento) {
		this.durataSoleggiamento = durataSoleggiamento;
	}

	@Override
	public String toString() {
		return "DataMeteoRow [year=" + year + ", month=" + month + ", day=" + day + ", temperaturaMax=" + temperaturaMax + ", temperaturaMin=" + temperaturaMin + ", direzioneVento=" + direzioneVento + ", velocitaVento=" + velocitaVento
				+ ", umiditaMax=" + umiditaMax + ", umiditaMin=" + umiditaMin + ", precipitazioneAm=" + precipitazioneAm + ", precipitazionePm=" + precipitazionePm + ", mantoNevoso=" + mantoNevoso + ", radiazioneSolare=" + radiazioneSolare
				+ ", durataSoleggiamento=" + durataSoleggiamento + "]";
	}

}
