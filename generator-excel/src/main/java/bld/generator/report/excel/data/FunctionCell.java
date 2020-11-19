/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.FunctionCell.java
*/
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 * The Class FunctionCell.
 * 
 */
public class FunctionCell {

	/** The cell. */
	private Cell cell; 
	
	
	
	/** The sheet header. */
	private SheetHeader sheetHeader;
	

	
	/** The merge row. */
	private MergeCell mergeRow;
	
	/** The formula evaluator. */
	private FormulaEvaluator formulaEvaluator;
	
	

	/**
	 * Instantiates a new function cell.
	 */
	public FunctionCell() {
		super();
	}

	
	
	/**
	 * Instantiates a new function cell.
	 *
	 * @param cell        the cell
	 * @param sheetHeader the sheet header
	 */
	public FunctionCell(Cell cell, SheetHeader sheetHeader) {
		super();
		this.cell = cell;
		this.sheetHeader = sheetHeader;
	}

	/**
	 * Instantiates a new function cell.
	 *
	 * @param cell        the cell
	 * @param sheetHeader the sheet header
	 * @param mergeRow    the merge row
	 */
	public FunctionCell(Cell cell, SheetHeader sheetHeader, MergeCell mergeRow) {
		super();
		this.cell = cell;
		this.sheetHeader = sheetHeader;
		this.mergeRow = mergeRow;
	}



	/**
	 * Gets the cell.
	 *
	 * @return the cell
	 */
	public Cell getCell() {
		return cell;
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
	 * Gets the merge row.
	 *
	 * @return the merge row
	 */
	public MergeCell getMergeRow() {
		return mergeRow;
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
	 * Sets the sheet header.
	 *
	 * @param sheetHeader the new sheet header
	 */
	public void setSheetHeader(SheetHeader sheetHeader) {
		this.sheetHeader = sheetHeader;
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
	 * Gets the formula evaluator.
	 *
	 * @return the formula evaluator
	 */
	public FormulaEvaluator getFormulaEvaluator() {
		return formulaEvaluator;
	}



	/**
	 * Sets the formula evaluator.
	 *
	 * @param formulaEvaluator the new formula evaluator
	 */
	public void setFormulaEvaluator(FormulaEvaluator formulaEvaluator) {
		this.formulaEvaluator = formulaEvaluator;
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
		result = prime * result + ((formulaEvaluator == null) ? 0 : formulaEvaluator.hashCode());
		result = prime * result + ((mergeRow == null) ? 0 : mergeRow.hashCode());
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
		FunctionCell other = (FunctionCell) obj;
		if (cell == null) {
			if (other.cell != null)
				return false;
		} else if (!cell.equals(other.cell))
			return false;
		if (formulaEvaluator == null) {
			if (other.formulaEvaluator != null)
				return false;
		} else if (!formulaEvaluator.equals(other.formulaEvaluator))
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
		return true;
	}


	
	
}
