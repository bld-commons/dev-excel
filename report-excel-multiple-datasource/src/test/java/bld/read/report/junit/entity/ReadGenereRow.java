/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.read.report.junit.entity.ReadGenereRow.java
*/
package bld.read.report.junit.entity;

import bld.read.report.excel.annotation.ExcelReadColumn;
import bld.read.report.excel.domain.RowSheetRead;

/**
 * The Class ReadGenereRow.
 */
public class ReadGenereRow implements RowSheetRead {

	/** The genere. */
	@ExcelReadColumn(name = "Genere")
	private String genere;
	
	/** The count libri. */
	@ExcelReadColumn(name = "Totale Libri")
	private Integer countLibri;
	
	/**
	 * Instantiates a new read genere row.
	 *
	 * @param genere     the genere
	 * @param countLibri the count libri
	 */
	public ReadGenereRow(String genere, int countLibri) {
		super();
		this.genere = genere;
		this.countLibri = countLibri;
	}
	
	/**
	 * Instantiates a new read genere row.
	 */
	public ReadGenereRow() {
		super();
	}
	
	/**
	 * Gets the genere.
	 *
	 * @return the genere
	 */
	public String getGenere() {
		return genere;
	}
	
	/**
	 * Sets the genere.
	 *
	 * @param genere the new genere
	 */
	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	/**
	 * Gets the count libri.
	 *
	 * @return the count libri
	 */
	public Integer getCountLibri() {
		return countLibri;
	}
	
	/**
	 * Sets the count libri.
	 *
	 * @param countLibri the new count libri
	 */
	public void setCountLibri(Integer countLibri) {
		this.countLibri = countLibri;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ReadGenereRow [genere=" + genere + ", countLibri=" + countLibri + "]";
	}
	
	
}
