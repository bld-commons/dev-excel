/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.read.report.junit.entity.ReadGenereRow.java
*/
package bld.report.controller.entity;

import bld.read.report.excel.annotation.ExcelReadColumn;
import bld.read.report.excel.domain.RowSheetRead;

/**
 * The Class ReadGenereRow.
 */
public class ReadGenereRow implements RowSheetRead {

	/** The genere. */
	@ExcelReadColumn(name = "Genere")
	private String genere;
	

	/**
	 * Instantiates a new read genere row.
	 *
	 * @param genere the genere
	 */
	public ReadGenereRow(String genere) {
		super();
		this.genere = genere;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReadGenereRow other = (ReadGenereRow) obj;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "ReadGenereRow [genere=" + genere + "]";
	}
	

	
	
}
