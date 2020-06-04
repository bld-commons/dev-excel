/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.FunctionCell.java
*/
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * The Class FunctionCell.
 * 
 */
public class FunctionCell {

	/** The cell. */
	private Cell cell; 
	
	/** The cell style. */
	private CellStyle cellStyle;
	
	/** The sheet header. */
	private SheetHeader sheetHeader;
	
	/** The index row. */
	private Integer indexRow;
	
	/** The worksheet. */
	private Sheet worksheet;
	
	/** The merge row. */
	private MergeCell mergeRow;

	/**
	 * Gets the cell.
	 *
	 * @return the cell
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Sets the cell.
	 *
	 * @param cell the new cell
	 */
	public void setCell(Cell cell) {
		this.cell = cell;
	}

	/**
	 * Gets the cell style.
	 *
	 * @return the cell style
	 */
	public CellStyle getCellStyle() {
		return cellStyle;
	}

	/**
	 * Sets the cell style.
	 *
	 * @param cellStyle the new cell style
	 */
	public void setCellStyle(CellStyle cellStyle) {
		this.cellStyle = cellStyle;
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
	 * Gets the index row.
	 *
	 * @return the index row
	 */
	public Integer getIndexRow() {
		return indexRow;
	}

	/**
	 * Sets the index row.
	 *
	 * @param indexRow the new index row
	 */
	public void setIndexRow(Integer indexRow) {
		this.indexRow = indexRow;
	}

	/**
	 * Gets the worksheet.
	 *
	 * @return the worksheet
	 */
	public Sheet getWorksheet() {
		return worksheet;
	}

	/**
	 * Sets the worksheet.
	 *
	 * @param worksheet the new worksheet
	 */
	public void setWorksheet(Sheet worksheet) {
		this.worksheet = worksheet;
	}

	/**
	 * Gets the merge row.
	 *
	 * @return the merge row
	 */
	public MergeCell getMergeRow() {
		return mergeRow;
	}

	/**
	 * Sets the merge row.
	 *
	 * @param mergeRow the new merge row
	 */
	public void setMergeRow(MergeCell mergeRow) {
		this.mergeRow = mergeRow;
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
		result = prime * result + ((cell == null) ? 0 : cell.hashCode());
		result = prime * result + ((cellStyle == null) ? 0 : cellStyle.hashCode());
		result = prime * result + ((indexRow == null) ? 0 : indexRow.hashCode());
		result = prime * result + ((mergeRow == null) ? 0 : mergeRow.hashCode());
		result = prime * result + ((sheetHeader == null) ? 0 : sheetHeader.hashCode());
		result = prime * result + ((worksheet == null) ? 0 : worksheet.hashCode());
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
		FunctionCell other = (FunctionCell) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (cellStyle == null) {
			if (other.cellStyle != null)
				return false;
		} else if (!cellStyle.equals(other.cellStyle))
			return false;
		if (indexRow == null) {
			if (other.indexRow != null)
				return false;
		} else if (!indexRow.equals(other.indexRow))
			return false;
		if (mergeRow == null) {
			if (other.mergeRow != null)
				return false;
		} else if (!mergeRow.equals(other.mergeRow))
			return false;
		if (sheetHeader == null) {
			if (other.sheetHeader != null)
				return false;
		} else if (!sheetHeader.equals(other.sheetHeader))
			return false;
		if (worksheet == null) {
			if (other.worksheet != null)
				return false;
		} else if (!worksheet.equals(other.worksheet))
			return false;
		return true;
	}
	
	
	
}
