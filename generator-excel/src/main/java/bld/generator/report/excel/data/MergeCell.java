/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class MergeCell.
 */
public class MergeCell {

	/** The row from. */
	private int rowStart;

	/** The row to. */
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
	
	/** The cal row start. */
	private Integer calRowStart;
	
	/** The cal row end. */
	private Integer calRowEnd;
	

	/**
	 * Gets the cal row start.
	 *
	 * @return the cal row start
	 */
	public Integer getCalRowStart() {
		return calRowStart;
	}

	/**
	 * Sets the cal row start.
	 *
	 * @param calRowStart the new cal row start
	 */
	public void setCalRowStart(Integer calRowStart) {
		this.calRowStart = calRowStart;
	}

	/**
	 * Gets the cal row end.
	 *
	 * @return the cal row end
	 */
	public Integer getCalRowEnd() {
		return calRowEnd;
	}

	/**
	 * Sets the cal row end.
	 *
	 * @param calRowEnd the new cal row end
	 */
	public void setCalRowEnd(Integer calRowEnd) {
		this.calRowEnd = calRowEnd;
	}

	/**
	 * Gets the row from.
	 *
	 * @return the row from
	 */
	public int getRowStart() {
		return rowStart;
	}

	/**
	 * Sets the row from.
	 *
	 * @param rowStart the new row from
	 */
	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	/**
	 * Gets the row to.
	 *
	 * @return the row to
	 */
	public int getRowEnd() {
		return rowEnd;
	}

	/**
	 * Sets the row to.
	 *
	 * @param rowEnd the new row to
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "MergeColumn [rowFrom=" + rowStart + ", rowTo=" + rowEnd + ", columnFrom=" + columnFrom + ", columnTo=" + columnTo + "]";
	}

}
