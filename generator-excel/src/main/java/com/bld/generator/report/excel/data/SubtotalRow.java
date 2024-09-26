/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.data.SubtotalRow.java
 * 
 */
package com.bld.generator.report.excel.data;

/**
 * The Class SubtotalRow.
 */
public class SubtotalRow {

	
	/** The empty row. */
	private Integer emptyRow;
	
	/** The label. */
	private String label;
	
	private String fieldName;
	
	private Integer firstRow;
	
	private Integer lastRow;

	/**
	 * Instantiates a new subtotal row.
	 *
	 * @param emptyRow the empty row
	 */
	public SubtotalRow(Integer emptyRow) {
		super();
		this.emptyRow = emptyRow;
	}


	public SubtotalRow(Integer emptyRow, String label) {
		super();
		this.emptyRow = emptyRow;
		this.label = label;

	}




	public SubtotalRow(Integer emptyRow, String label, String fieldName, Integer firstRow, Integer lastRow) {
		super();
		this.emptyRow = emptyRow;
		this.label = label;
		this.fieldName = fieldName;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
	}


	/**
	 * Gets the empty row.
	 *
	 * @return the empty row
	 */
	public Integer getEmptyRow() {
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
	public void setEmptyRow(Integer emptyRow) {
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

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}


	public Integer getFirstRow() {
		return firstRow;
	}


	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}


	public Integer getLastRow() {
		return lastRow;
	}


	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}
	
	

}
