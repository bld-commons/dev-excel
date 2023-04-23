/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.junit.entity.GenereRow.java
*/
package bld.report.generator.junit.entity;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;

/**
 * The Class GenereRow.
 */
public class GenereRow implements RowSheet {

	/** The genere. */
	@ExcelColumn(index = 0, name = "Genere")
	@ExcelCellLayout
	private String genere;
	
	/** The test. */
	@ExcelColumn(index = 0.5, name = "Test Remove", ignore = true)
	@ExcelCellLayout
	private String test;
	
	/** The count libri. */
	@ExcelColumn(index = 1, name = "Totale Libri")
	@ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT)
	private int countLibri;
	
	/** The test remove. */
	@ExcelColumn(index = 1.5, name = "Test Remove 1",ignore=true)
	@ExcelCellLayout
	private String testRemove;
	

	/**
	 * Instantiates a new genere row.
	 *
	 * @param genere     the genere
	 * @param test       the test
	 * @param countLibri the count libri
	 * @param testRemove the test remove
	 */
	public GenereRow(String genere, String test, int countLibri, String testRemove) {
		super();
		this.genere = genere;
		this.test = test;
		this.countLibri = countLibri;
		this.testRemove = testRemove;
	}
	
	/**
	 * Instantiates a new genere row.
	 */
	public GenereRow() {
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
	public int getCountLibri() {
		return countLibri;
	}
	
	/**
	 * Sets the count libri.
	 *
	 * @param countLibri the new count libri
	 */
	public void setCountLibri(int countLibri) {
		this.countLibri = countLibri;
	}
	
	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	public String getTest() {
		return test;
	}
	
	/**
	 * Sets the test.
	 *
	 * @param test the new test
	 */
	public void setTest(String test) {
		this.test = test;
	}
	
	/**
	 * Gets the test remove.
	 *
	 * @return the test remove
	 */
	public String getTestRemove() {
		return testRemove;
	}
	
	/**
	 * Sets the test remove.
	 *
	 * @param testRemove the new test remove
	 */
	public void setTestRemove(String testRemove) {
		this.testRemove = testRemove;
	}
	
	
}
