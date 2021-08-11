/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.data.SubtotalRow.java
 * 
 */
package bld.generator.report.excel.data;

/**
 * The Class SubtotalRow.
 */
public class SubtotalRow {

	
	/** The empty row. */
	private int emptyRow;
	
	/** The label. */
	private String label;
	
	/**
	 * Instantiates a new subtotal row.
	 */
	public SubtotalRow() {
	}
	
	/**
	 * Instantiates a new subtotal row.
	 *
	 * @param emptyRow the empty row
	 */
	public SubtotalRow(int emptyRow) {
		super();
		this.emptyRow = emptyRow;
	}


	/**
	 * Instantiates a new subtotal row.
	 *
	 * @param emptyRow the empty row
	 * @param label the label
	 */
	public SubtotalRow(int emptyRow, String label) {
		super();
		this.emptyRow = emptyRow;
		this.label = label;
	}

	/**
	 * Gets the empty row.
	 *
	 * @return the empty row
	 */
	public int getEmptyRow() {
		return emptyRow;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the empty row.
	 *
	 * @param emptyRow the new empty row
	 */
	public void setEmptyRow(int emptyRow) {
		this.emptyRow = emptyRow;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
