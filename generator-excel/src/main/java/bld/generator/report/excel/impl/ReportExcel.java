/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */

package bld.generator.report.excel.impl;

import java.util.List;

import bld.generator.report.excel.BaseSheet;

/**
 * The Class ReportExcel.
 */
public class ReportExcel {

	/** The titolo. */
	private String titolo;

	/** The list base sheet. */
	private List<BaseSheet> listBaseSheet;

	/**
	 * Instantiates a new report excel.
	 *
	 * @param titolo        the titolo
	 * @param listBaseSheet the list base sheet
	 */
	public ReportExcel(String titolo, List<BaseSheet> listBaseSheet) {
		super();
		this.titolo = titolo;
		this.listBaseSheet = listBaseSheet;
	}

	/**
	 * Instantiates a new report excel.
	 */
	public ReportExcel() {
		super();
	}

	/**
	 * Gets the titolo.
	 *
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * Sets the titolo.
	 *
	 * @param titolo the new titolo
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * Gets the list base sheet.
	 *
	 * @return the list base sheet
	 */
	public List<BaseSheet> getListBaseSheet() {
		return listBaseSheet;
	}

	/**
	 * Sets the list base sheet.
	 *
	 * @param listBaseSheet the new list base sheet
	 */
	public void setListBaseSheet(List<BaseSheet> listBaseSheet) {
		this.listBaseSheet = listBaseSheet;
	}

}
