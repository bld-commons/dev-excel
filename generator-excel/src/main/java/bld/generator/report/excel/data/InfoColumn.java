package bld.generator.report.excel.data;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import bld.generator.report.utils.ExcelUtils;

public class InfoColumn {
	
	private String keyColumn;
	
	
	
	private int columnNum;
	
	private int rowHeader;
	

	public InfoColumn(Sheet worksheet,SheetHeader sheetHeader, int columnNum, int rowHeader) {
		super();
		if (sheetHeader.getField() != null)
			this.keyColumn=ExcelUtils.getKeyColumn(worksheet, sheetHeader.getField().getName());
		else if (StringUtils.isNotBlank(sheetHeader.getKeyMap()))
			this.keyColumn=ExcelUtils.getKeyColumn(worksheet, sheetHeader.getKeyMap());
		else if (StringUtils.isNotBlank(sheetHeader.getNameFunction()))
			this.keyColumn=ExcelUtils.getKeyColumn(worksheet, sheetHeader.getNameFunction());
		this.columnNum = columnNum;
		this.rowHeader = rowHeader;
	}

	

	/**
	 * @return the keyColumn
	 */
	public String getKeyColumn() {
		return keyColumn;
	}

	/**
	 * @param keyColumn the keyColumn to set
	 */
	public void setKeyColumn(String keyColumn) {
		this.keyColumn = keyColumn;
	}

	/**
	 * @return the columnNum
	 */
	public int getColumnNum() {
		return columnNum;
	}

	/**
	 * @param columnNum the columnNum to set
	 */
	public void setColumnNum(int columnNum) {
		this.columnNum = columnNum;
	}

	/**
	 * @return the rowHeader
	 */
	public int getRowHeader() {
		return rowHeader;
	}

	/**
	 * @param rowHeader the rowHeader to set
	 */
	public void setRowHeader(int rowHeader) {
		this.rowHeader = rowHeader;
	}
	
	
	
	
}
