package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Sheet;

public class DropDownCell {

	private Sheet sheet; 
	private SheetHeader sheetHeader;
	private int firstRow;
	private int lastRow;
	private int firstCol;
	private int lastCol;
	
	
	
	public DropDownCell(Sheet sheet, SheetHeader sheetHeader, int firstRow, int lastRow, int firstCol, int lastCol) {
		super();
		this.sheet = sheet;
		this.sheetHeader = sheetHeader;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
	}

	public DropDownCell() {
	}

	public Sheet getSheet() {
		return sheet;
	}

	public SheetHeader getSheetHeader() {
		return sheetHeader;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public int getFirstCol() {
		return firstCol;
	}

	public int getLastCol() {
		return lastCol;
	}

	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	public void setSheetHeader(SheetHeader sheetHeader) {
		this.sheetHeader = sheetHeader;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public void setFirstCol(int firstCol) {
		this.firstCol = firstCol;
	}

	public void setLastCol(int lastCol) {
		this.lastCol = lastCol;
	}

	
	
}
