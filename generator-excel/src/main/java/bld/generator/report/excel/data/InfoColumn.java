/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.InfoColumn.java
*/
package bld.generator.report.excel.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import bld.generator.report.utils.ExcelUtils;

/**
 * The Class InfoColumn.
 */
public class InfoColumn {
	
	/** The key column. */
	private String keyColumn;
	
	
	
	/** The column num. */
	private int columnNum;
	
	/** The row header. */
	private int rowHeader;
	

	/**
	 * Instantiates a new info column.
	 *
	 * @param worksheet   the worksheet
	 * @param sheetHeader the sheet header
	 * @param columnNum   the column num
	 * @param rowHeader   the row header
	 */
	public InfoColumn(Sheet worksheet,SheetHeader sheetHeader, int columnNum, int rowHeader) {
		super();
		if (sheetHeader.getField() != null)
			this.keyColumn=ExcelUtils.getKeyColumn(worksheet, sheetHeader.getField().getName());
		else if (StringUtils.isNotBlank(sheetHeader.getKeyMap()))
			this.keyColumn=ExcelUtils.getKeyColumn(worksheet, sheetHeader.getKeyMap());
		else if (sheetHeader.getExcelFunction()!=null)
			this.keyColumn=ExcelUtils.getKeyColumn(worksheet, sheetHeader.getExcelFunction().nameFunction());
		this.columnNum = columnNum;
		this.rowHeader = rowHeader;
	}

	

	/**
	 * Gets the key column.
	 *
	 * @return the key column
	 */
	public String getKeyColumn() {
		return keyColumn;
	}

	/**
	 * Sets the key column.
	 *
	 * @param keyColumn the new key column
	 */
	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
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
	
	
	
	
}
