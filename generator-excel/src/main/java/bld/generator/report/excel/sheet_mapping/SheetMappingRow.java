package bld.generator.report.excel.sheet_mapping;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelColumnWidth;
import bld.generator.report.utils.ExcelUtils;

public class SheetMappingRow implements RowSheet {

	@ExcelColumn(columnName = "Sheet name", indexColumn = 1)
	@ExcelCellLayout(locked = true)
	@ExcelColumnWidth(width = 8)
	private String sheet;
	
	@ExcelColumn(columnName = "Rows number", indexColumn = 2)
	@ExcelCellLayout(locked = true, horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer rowsNumber;

	@ExcelColumn(columnName = "First row", indexColumn = 3)
	@ExcelCellLayout(locked = true, horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer firstRow;

	@ExcelColumn(columnName = "Firt column", indexColumn = 4)
	@ExcelCellLayout(locked = true, horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer firstColumn;

	@ExcelColumn(columnName = "Last row", indexColumn = 5)
	@ExcelCellLayout(locked = true, horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer lastRow;

	@ExcelColumn(columnName = "Last column", indexColumn = 6)
	@ExcelCellLayout(locked = true, horizontalAlignment = HorizontalAlignment.RIGHT)
	private Integer lastColumn;

	@ExcelColumn(columnName = "First row and column", indexColumn = 7)
	@ExcelCellLayout(locked = true)
	private String firstRowFirstColumn;

	@ExcelColumn(columnName = "First row and column with locked row", indexColumn = 8)
	@ExcelCellLayout(locked = true)
	private String firstLockedRowFirstColumn;

	@ExcelColumn(columnName = "First row and column with row and column locked", indexColumn = 9)
	@ExcelCellLayout(locked = true)
	private String firstLockedRowFirstLockedColumn;

	@ExcelColumn(columnName = "First row and column with locked column", indexColumn = 10)
	@ExcelCellLayout(locked = true)
	private String firstRowFirstLockedColumn;

	@ExcelColumn(columnName = "First row and last column", indexColumn = 11)
	@ExcelCellLayout(locked = true)
	private String firstRowLastColumn;

	@ExcelColumn(columnName = "First row and last column with locked row", indexColumn = 12)
	@ExcelCellLayout(locked = true)
	private String firstLockedRowLastColumn;

	@ExcelColumn(columnName = "First row and last column with row and column locked", indexColumn = 13)
	@ExcelCellLayout(locked = true)
	private String firstLockedRowLastLockedColumn;

	@ExcelColumn(columnName = "First row and last column with locked column", indexColumn = 14)
	@ExcelCellLayout(locked = true)
	private String firstRowLastLockedColumn;

	@ExcelColumn(columnName = "Last row and first column", indexColumn = 15)
	@ExcelCellLayout(locked = true)
	private String lastRowFirstColumn;

	@ExcelColumn(columnName = "Last row and first column with locked row", indexColumn = 16)
	@ExcelCellLayout(locked = true)
	private String lastLockedRowFirstColumn;

	@ExcelColumn(columnName = "Last row and first column with row and column locked", indexColumn = 17)
	@ExcelCellLayout(locked = true)
	private String lastLockedRowFirstLockedColumn;

	@ExcelColumn(columnName = "Last row and first column with locked column", indexColumn = 18)
	@ExcelCellLayout(locked = true)
	private String lastRowFirstLockedColumn;

	@ExcelColumn(columnName = "Last row and column", indexColumn = 19)
	@ExcelCellLayout(locked = true)
	private String lastRowLastColumn;

	@ExcelColumn(columnName = "Last row and column with locked row", indexColumn = 20)
	@ExcelCellLayout(locked = true)
	private String lastLockedRowLastColumn;

	@ExcelColumn(columnName = "Last row and column with row and column locked", indexColumn = 21)
	@ExcelCellLayout(locked = true)
	private String lastLockedRowLastLockedColumn;

	@ExcelColumn(columnName = "Last row and column with locked column", indexColumn = 22)
	@ExcelCellLayout(locked = true)
	private String lastRowLastLockedColumn;

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public Integer getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(Integer firstRow) {
		if(firstRow!=null)
			firstRow++;
		this.firstRow = firstRow;
	}

	public Integer getFirstColumn() {
		return firstColumn;
	}

	public void setFirstColumn(Integer firstColumn) {
		this.firstColumn = firstColumn;
	}

	public Integer getLastRow() {
		return lastRow;
	}

	public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}

	public Integer getLastColumn() {
		return lastColumn;
	}

	public void setLastColumn(Integer lastColumn) {
		if(lastColumn!=null)
			lastColumn--;
		this.lastColumn = lastColumn;
	}
	
	public Integer getRowsNumber() {
		return rowsNumber;
	}

	public void setRowsNumber(Integer rowsNumber) {
		this.rowsNumber = rowsNumber;
	}

	public String getFirstRowFirstColumn() {
		if (this.firstRowFirstColumn == null && this.firstRow != null && firstColumn != null)
			this.firstRowFirstColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.firstColumn, false, false);
		return firstRowFirstColumn;
	}

	public String getFirstRowLastColumn() {
		if (this.firstRowLastColumn == null && this.firstRow != null && this.lastColumn != null)
			this.firstRowLastColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.lastColumn, false, false);
		return firstRowLastColumn;
	}

	public String getLastRowFirstColumn() {
		if (this.lastRowFirstColumn == null && this.lastRow != null && this.firstColumn != null)
			this.lastRowFirstColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.firstColumn, false, false);
		return lastRowFirstColumn;
	}

	public String getLastRowLastColumn() {
		if (this.lastRowLastColumn == null && this.lastRow != null && this.lastColumn != null)
			this.lastRowLastColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.lastColumn, false, false);
		return lastRowLastColumn;
	}

	public String getFirstLockedRowFirstColumn() {
		if (this.firstLockedRowFirstColumn == null && this.firstRow != null && firstColumn != null)
			this.firstLockedRowFirstColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.firstColumn, false, true);
		return firstLockedRowFirstColumn;
	}

	public String getFirstLockedRowFirstLockedColumn() {
		if (this.firstLockedRowFirstLockedColumn == null && this.firstRow != null && firstColumn != null)
			this.firstLockedRowFirstLockedColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.firstColumn, true, true);
		return firstLockedRowFirstLockedColumn;
	}

	public String getFirstRowFirstLockedColumn() {
		if (this.firstRowFirstLockedColumn == null && this.firstRow != null && firstColumn != null)
			this.firstRowFirstLockedColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.firstColumn, true, false);
		return firstRowFirstLockedColumn;
	}

	public String getFirstLockedRowLastColumn() {
		if (this.firstLockedRowLastColumn == null && this.firstRow != null && lastColumn != null)
			this.firstLockedRowLastColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.lastColumn, false, true);
		return firstLockedRowLastColumn;
	}

	public String getFirstLockedRowLastLockedColumn() {
		if (this.firstLockedRowLastLockedColumn == null && this.firstRow != null && lastColumn != null)
			this.firstLockedRowLastLockedColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.lastColumn, true, true);
		return firstLockedRowLastLockedColumn;
	}

	public String getFirstRowLastLockedColumn() {
		if (this.firstRowLastLockedColumn == null && this.firstRow != null && lastColumn != null)
			this.firstRowLastLockedColumn = ExcelUtils.coordinateCalculation(this.firstRow, this.lastColumn, true, false);
		return firstRowLastLockedColumn;
	}

	public String getLastLockedRowFirstColumn() {
		if (this.lastLockedRowFirstColumn == null && this.lastRow != null && this.firstColumn != null)
			this.lastLockedRowFirstColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.firstColumn, false, false);
		return lastLockedRowFirstColumn;
	}

	public String getLastLockedRowFirstLockedColumn() {
		if (this.lastLockedRowFirstLockedColumn == null && this.lastRow != null && this.firstColumn != null)
			this.lastLockedRowFirstLockedColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.firstColumn, false, true);
		return lastLockedRowFirstLockedColumn;
	}

	public String getLastRowFirstLockedColumn() {
		if (this.lastRowFirstLockedColumn == null && this.lastRow != null && this.firstColumn != null)
			this.lastRowFirstLockedColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.firstColumn, true, true);
		return lastRowFirstLockedColumn;
	}

	public String getLastLockedRowLastColumn() {
		if (this.lastLockedRowLastColumn == null && this.lastRow != null && this.lastColumn != null)
			this.lastLockedRowLastColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.lastColumn, false, true);
		return lastLockedRowLastColumn;
	}

	public String getLastLockedRowLastLockedColumn() {
		if (this.lastLockedRowLastLockedColumn == null && this.lastRow != null && this.lastColumn != null)
			this.lastLockedRowLastLockedColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.lastColumn, true, true);
		return lastLockedRowLastLockedColumn;
	}

	public String getLastRowLastLockedColumn() {
		if (this.lastRowLastLockedColumn == null && this.lastRow != null && this.lastColumn != null)
			this.lastRowLastLockedColumn = ExcelUtils.coordinateCalculation(this.lastRow, this.lastColumn, true, false);
		return lastRowLastLockedColumn;
	}

}
