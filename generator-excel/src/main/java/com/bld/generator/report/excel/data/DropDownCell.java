/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.data.DropDownCell.java
 */
package com.bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * The Class DropDownCell.
 */
public class DropDownCell {

	/** The sheet. */
	private Sheet sheet; 
	
	/** The sheet header. */
	private SheetHeader sheetHeader;
	
	/** The first row. */
	private int firstRow;
	
	/** The last row. */
	private int lastRow;
	
	/** The first col. */
	private int firstCol;
	
	/** The last col. */
	private int lastCol;
	
	private Integer indexRow;
	
	


	public DropDownCell(Sheet sheet, SheetHeader sheetHeader, int firstRow, int lastRow, int firstCol, int lastCol, Integer indexRow) {
		super();
		this.sheet = sheet;
		this.sheetHeader = sheetHeader;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
		this.indexRow = indexRow;
	}

	/**
	 * Instantiates a new drop down cell.
	 */
	public DropDownCell() {
	}

	/**
	 * Gets the sheet.
	 *
	 * @return the sheet
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * Gets the sheet header.
	 *
	 * @return the sheet header
	 */
	public SheetHeader getSheetHeader() {
		return sheetHeader;
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
	 * Gets the last row.
	 *
	 * @return the last row
	 */
	public int getLastRow() {
		return lastRow;
	}

	/**
	 * Gets the first col.
	 *
	 * @return the first col
	 */
	public int getFirstCol() {
		return firstCol;
	}

	/**
	 * Gets the last col.
	 *
	 * @return the last col
	 */
	public int getLastCol() {
		return lastCol;
	}

	/**
	 * Sets the sheet.
	 *
	 * @param sheet the new sheet
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * Sets the sheet header.
	 *
	 * @param sheetHeader the new sheet header
	 */
	public void setSheetHeader(SheetHeader sheetHeader) {
		this.sheetHeader = sheetHeader;
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
	 * Sets the last row.
	 *
	 * @param lastRow the new last row
	 */
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	/**
	 * Sets the first col.
	 *
	 * @param firstCol the new first col
	 */
	public void setFirstCol(int firstCol) {
		this.firstCol = firstCol;
	}

	/**
	 * Sets the last col.
	 *
	 * @param lastCol the new last col
	 */
	public void setLastCol(int lastCol) {
		this.lastCol = lastCol;
	}

	public Integer getIndexRow() {
		return indexRow;
	}

	public void setIndexRow(Integer indexRow) {
		this.indexRow = indexRow;
	}

	
	
}
