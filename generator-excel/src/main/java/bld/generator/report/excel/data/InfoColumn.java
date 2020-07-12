/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.data.InfoColumn.java
 */
package bld.generator.report.excel.data;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import bld.generator.report.utils.ExcelUtils;

/**
 * The Class InfoColumn.
 * 
 */
public class InfoColumn extends InfoField {

	/** The column num. */
	private int columnNum;

	/** The row header. */
	private int rowHeader;
	
	/** The first row. */
	private Integer firstRow;
	
	/** The last row. */
	private Integer lastRow;
	
	/** The last row reference. */
	private Integer lastRowReference;
	
	/** The map row merge row. */
	private Map<Integer,MergeCell>mapRowMergeRow;
	

	/**
	 * Instantiates a new info column.
	 *
	 * @param worksheet   the worksheet
	 * @param sheetHeader the sheet header
	 * @param columnNum   the column num
	 * @param rowHeader   the row header
	 */
	public InfoColumn(Sheet worksheet, SheetHeader sheetHeader, int columnNum, int rowHeader) {
		super();
		if (sheetHeader.getField() != null)
			this.key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getField().getName());
		else if (StringUtils.isNotBlank(sheetHeader.getKeyMap()))
			this.key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getKeyMap());
		else if (sheetHeader.getExcelFunction() != null)
			this.key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getExcelFunction().nameFunction());
		this.columnNum = columnNum;
		this.rowHeader = rowHeader;
		this.mapRowMergeRow=new HashedMap<>();
	}
	
	
	
	
	/**
	 * Gets the merge cell.
	 *
	 * @return the merge cell
	 */
	public MergeCell getMergeCell() {
		MergeCell mergeCell=null;
		if(this.lastRowReference!=null && this.mapRowMergeRow.containsKey(lastRowReference))
			mergeCell=this.mapRowMergeRow.get(this.lastRowReference);
		return mergeCell;
	}




	/**
	 * Gets the column num.
	 *
	 * @return the column num
	 */
	public int getColumnNum() {
		return columnNum;
	}




	/**
	 * Sets the column num.
	 *
	 * @param columnNum the new column num
	 */
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}




	/**
	 * Gets the row header.
	 *
	 * @return the row header
	 */
	public int getRowHeader() {
		return rowHeader;
	}




	/**
	 * Sets the row header.
	 *
	 * @param rowHeader the new row header
	 */
	public void setRowHeader(int rowHeader) {
		this.rowHeader = rowHeader;
	}




	/**
	 * Gets the first row.
	 *
	 * @return the first row
	 */
	public Integer getFirstRow() {
		return firstRow;
	}




	/**
	 * Sets the first row.
	 *
	 * @param firstRow the new first row
	 */
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}




	/**
	 * Gets the last row.
	 *
	 * @return the last row
	 */
	public Integer getLastRow() {
		return lastRow;
	}




	/**
	 * Sets the last row.
	 *
	 * @param lastRow the new last row
	 */
	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}




	/**
	 * Gets the last row reference.
	 *
	 * @return the last row reference
	 */
	public Integer getLastRowReference() {
		return lastRowReference;
	}




	/**
	 * Sets the last row reference.
	 *
	 * @param lastRowReference the new last row reference
	 */
	public void setLastRowReference(Integer lastRowReference) {
		this.lastRowReference = lastRowReference;
	}




	/**
	 * Gets the map row merge row.
	 *
	 * @return the map row merge row
	 */
	public Map<Integer, MergeCell> getMapRowMergeRow() {
		return mapRowMergeRow;
	}




	/**
	 * Sets the map row merge row.
	 *
	 * @param mapRowMergeRow the new map row merge row
	 */
	public void setMapRowMergeRow(Map<Integer, MergeCell> mapRowMergeRow) {
		this.mapRowMergeRow = mapRowMergeRow;
	}




	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + columnNum;
		result = prime * result + ((firstRow == null) ? 0 : firstRow.hashCode());
		result = prime * result + ((lastRow == null) ? 0 : lastRow.hashCode());
		result = prime * result + ((lastRowReference == null) ? 0 : lastRowReference.hashCode());
		result = prime * result + ((mapRowMergeRow == null) ? 0 : mapRowMergeRow.hashCode());
		result = prime * result + rowHeader;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoColumn other = (InfoColumn) obj;
		if (columnNum != other.columnNum)
			return false;
		if (firstRow == null) {
			if (other.firstRow != null)
				return false;
		} else if (!firstRow.equals(other.firstRow))
			return false;
		if (lastRow == null) {
			if (other.lastRow != null)
				return false;
		} else if (!lastRow.equals(other.lastRow))
			return false;
		if (lastRowReference == null) {
			if (other.lastRowReference != null)
				return false;
		} else if (!lastRowReference.equals(other.lastRowReference))
			return false;
		if (mapRowMergeRow == null) {
			if (other.mapRowMergeRow != null)
				return false;
		} else if (!mapRowMergeRow.equals(other.mapRowMergeRow))
			return false;
		if (rowHeader != other.rowHeader)
			return false;
		return true;
	}
	
	

}
