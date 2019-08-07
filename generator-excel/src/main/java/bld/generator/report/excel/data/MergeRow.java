package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

// TODO: Auto-generated Javadoc
/**
 * The Class MergeRow.
 */
public class MergeRow {

	/** The row from. */
	private int rowFrom;

	/** The row to. */
	private int rowTo;

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
	 * Gets the row from.
	 *
	 * @return the row from
	 */
	public int getRowFrom() {
		return rowFrom;
	}

	/**
	 * Sets the row from.
	 *
	 * @param rowFrom the new row from
	 */
	public void setRowFrom(int rowFrom) {
		this.rowFrom = rowFrom;
	}

	/**
	 * Gets the row to.
	 *
	 * @return the row to
	 */
	public int getRowTo() {
		return rowTo;
	}

	/**
	 * Sets the row to.
	 *
	 * @param rowTo the new row to
	 */
	public void setRowTo(int rowTo) {
		this.rowTo = rowTo;
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
		return "MergeColumn [rowFrom=" + rowFrom + ", rowTo=" + rowTo + ", columnFrom=" + columnFrom + ", columnTo=" + columnTo + "]";
	}

}
