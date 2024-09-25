/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.data.MergeCell.java
*/
package com.bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * The Class MergeCell.
 */

/**
 * Instantiates a new merge cell.
 */
public class MergeCell {

	/** The row start. */
	private int rowStart;

	/** The row end. */
	private int rowEnd;

	/** The column from. */
	private int columnFrom;

	/** The column to. */
	private int columnTo;
	
	/** The sheet header. */
	private SheetHeader sheetHeader;
	
	/** The cell from. */
	private Cell cellFrom;
	
	/** The cell style from. */
	private CellStyle cellStyleFrom;

	/**
	 * Gets the row start.
	 *
	 * @return the row start
	 */
	public int getRowStart() {
		return rowStart;
	}

	/**
	 * Sets the row start.
	 *
	 * @param rowStart the new row start
	 */
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	/**
	 * Gets the row end.
	 *
	 * @return the row end
	 */
	public int getRowEnd() {
		return rowEnd;
	}

	/**
	 * Sets the row end.
	 *
	 * @param rowEnd the new row end
	 */
	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	/**
	 * Gets the column from.
	 *
	 * @return the column from
	 */
	public int getColumnFrom() {
		return columnFrom;
	}

	/**
	 * Sets the column from.
	 *
	 * @param columnFrom the new column from
	 */
	public void setColumnFrom(int columnFrom) {
		this.columnFrom = columnFrom;
	}

	/**
	 * Gets the column to.
	 *
	 * @return the column to
	 */
	public int getColumnTo() {
		return columnTo;
	}

	/**
	 * Sets the column to.
	 *
	 * @param columnTo the new column to
	 */
	public void setColumnTo(int columnTo) {
		this.columnTo = columnTo;
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
	 * Sets the sheet header.
	 *
	 * @param sheetHeader the new sheet header
	 */
	public void setSheetHeader(SheetHeader sheetHeader) {
		this.sheetHeader = sheetHeader;
	}

	/**
	 * Gets the cell from.
	 *
	 * @return the cell from
	 */
	public Cell getCellFrom() {
		return cellFrom;
	}

	/**
	 * Sets the cell from.
	 *
	 * @param cellFrom the new cell from
	 */
	public void setCellFrom(Cell cellFrom) {
		this.cellFrom = cellFrom;
	}

	/**
	 * Gets the cell style from.
	 *
	 * @return the cell style from
	 */
	public CellStyle getCellStyleFrom() {
		return cellStyleFrom;
	}

	/**
	 * Sets the cell style from.
	 *
	 * @param cellStyleFrom the new cell style from
	 */
	public void setCellStyleFrom(CellStyle cellStyleFrom) {
		this.cellStyleFrom = cellStyleFrom;
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
		result = prime * result + ((cellFrom == null) ? 0 : cellFrom.hashCode());
		result = prime * result + ((cellStyleFrom == null) ? 0 : cellStyleFrom.hashCode());
		result = prime * result + columnFrom;
		result = prime * result + columnTo;
		result = prime * result + rowEnd;
		result = prime * result + rowStart;
		result = prime * result + ((sheetHeader == null) ? 0 : sheetHeader.hashCode());
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
		MergeCell other = (MergeCell) obj;
		if (cellFrom == null) {
			if (other.cellFrom != null)
				return false;
		} else if (!cellFrom.equals(other.cellFrom))
			return false;
		if (cellStyleFrom == null) {
			if (other.cellStyleFrom != null)
				return false;
		} else if (!cellStyleFrom.equals(other.cellStyleFrom))
			return false;
		if (columnFrom != other.columnFrom)
			return false;
		if (columnTo != other.columnTo)
			return false;
		if (rowEnd != other.rowEnd)
			return false;
		if (rowStart != other.rowStart)
			return false;
		if (sheetHeader == null) {
			if (other.sheetHeader != null)
				return false;
		} else if (!sheetHeader.equals(other.sheetHeader))
			return false;
		return true;
	}
	
	
	
}
