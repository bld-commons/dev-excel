/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.bld.common.spreadsheet.utils.SpreadsheetUtils;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Abstract representation of an Excel sheet produced by the read-excel library.
 * <p>
 * Concrete subclasses must declare the row entity type {@code T} and provide a
 * constructor accepting the sheet name. The name must not exceed
 * {@link com.bld.common.spreadsheet.utils.SpreadsheetUtils#SHEET_NAME_SIZE} characters
 * (Excel limit of 31).
 * </p>
 * <p>
 * After {@link com.bld.read.report.excel.ReadExcel#convertExcelToEntity(ExcelRead)} completes,
 * the parsed rows are available via {@link #getListRowSheet()}.
 * Override {@link #filtered(Object)} to apply custom row-level filtering during parsing.
 * </p>
 *
 * @param <T> the row entity type, must implement {@link RowSheetRead}
 * @author Francesco Baldi
 * @see RowSheetRead
 * @see MapSheetRead
 */
public abstract class SheetRead<T extends RowSheetRead> {

	/** The list row sheet. */
	private List<T> listRowSheet;

	/** The sheet name. */
	@NotNull
	private String sheetName;

	/**
	 * Instantiates a new sheet read.
	 *
	 * @param sheetName the sheet name
	 */
	public SheetRead(@Size(max = SpreadsheetUtils.SHEET_NAME_SIZE) String sheetName) {
		super();
		this.listRowSheet = new ArrayList<>();
		this.sheetName = sheetName;
	}

	/**
	 * Gets the list row sheet.
	 *
	 * @return the list row sheet
	 */
	public List<T> getListRowSheet() {
		return listRowSheet;
	}

	/**
	 * Returns the number of rows currently held by this sheet.
	 *
	 * @return the row count, or {@code 0} if the list is empty
	 */
	public int size() {
		if(CollectionUtils.isNotEmpty(this.listRowSheet))
			return this.listRowSheet.size();
		return 0;
	}
	
	/**
	 * Adds a parsed row to the sheet, provided it passes the {@link #filtered(Object)} check.
	 *
	 * @param t the row entity to add
	 * @throws Exception if an error occurs during the addition (e.g. in {@link MapSheetRead})
	 */
	public void addRowSheet(T t) throws Exception {
		if (this.filtered(t))
			this.listRowSheet.add(t);
	}

	/**
	 * Hook method called before adding each parsed row. Override to implement custom filtering logic.
	 * Returns {@code true} by default, meaning all rows are accepted.
	 *
	 * @param t the parsed row entity
	 * @return {@code true} to include the row, {@code false} to discard it
	 */
	protected boolean filtered(T t) {
		return true;
	}

	/**
	 * Gets the sheet name.
	 *
	 * @return the sheet name
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * Sets the sheet name.
	 *
	 * @param sheetName the new sheet name
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
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
		result = prime * result + ((listRowSheet == null) ? 0 : listRowSheet.hashCode());
		result = prime * result + ((sheetName == null) ? 0 : sheetName.hashCode());
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
		SheetRead<?> other = (SheetRead<?>) obj;
		if (listRowSheet == null) {
			if (other.listRowSheet != null)
				return false;
		} else if (!listRowSheet.equals(other.listRowSheet))
			return false;
		if (sheetName == null) {
			if (other.sheetName != null)
				return false;
		} else if (!sheetName.equals(other.sheetName))
			return false;
		return true;
	}

}
