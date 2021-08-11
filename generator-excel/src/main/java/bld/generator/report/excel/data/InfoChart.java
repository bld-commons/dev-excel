/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.data.InfoChart.java
 * 
 */
package bld.generator.report.excel.data;

/**
 * The Class InfoChart.
 */
public class InfoChart {

	/** The title. */
	private String title;
	
	/** The function. */
	private String function;
	
	/** The first row. */
	private int firstRow;
	
	/** The last row. */
	private int lastRow;
	

	/**
	 * Instantiates a new info chart.
	 *
	 * @param title the title
	 * @param function the function
	 * @param firstRow the first row
	 */
	public InfoChart(String title,String function, int firstRow) {
		super();
		this.title=title;
		this.function = function;
		this.firstRow = firstRow;
		this.lastRow=firstRow;
	}

	/**
	 * Gets the function.
	 *
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}

	/**
	 * Sets the function.
	 *
	 * @param function the new function
	 */
	public void setFunction(String function) {
		this.function = function;
	}

	/**
	 * Gets the first row.
	 *
	 * @return the first row
	 */
	public int getFirstRow() {
		return firstRow;
	}

	/**
	 * Sets the first row.
	 *
	 * @param firstRow the new first row
	 */
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	/**
	 * Gets the last row.
	 *
	 * @return the last row
	 */
	public int getLastRow() {
		return lastRow;
	}

	/**
	 * Sets the last row.
	 *
	 * @param lastRow the new last row
	 */
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
}
