/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.domain;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.read.report.excel.constant.ExcelExceptionType;
import com.bld.read.report.excel.constant.ExcelType;
import com.bld.read.report.excel.exception.ExcelReaderException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Container representing the Excel file to be read and the set of sheets to extract from it.
 * <p>
 * Before passing this object to {@link com.bld.read.report.excel.ReadExcel#convertExcelToEntity(ExcelRead)},
 * the caller must:
 * </p>
 * <ol>
 *   <li>Provide the file content via one of the {@code setReportExcel} overloads
 *       (byte array, {@link java.io.InputStream}, or file path).</li>
 *   <li>Set the file format via {@link #setExcelType(com.bld.read.report.excel.constant.ExcelType)}
 *       ({@code XLS} by default).</li>
 *   <li>Register at least one sheet via {@link #addSheetConvertion}.</li>
 * </ol>
 * <p>
 * After reading, the populated {@link SheetRead} instances can be retrieved with
 * {@link #getSheet(Class, String)}.
 * </p>
 *
 * @author Francesco Baldi
 * @see com.bld.read.report.excel.ReadExcel
 * @see SheetRead
 */
public class ExcelRead {

	/** The report excel. */
	@NotNull
	private InputStream reportExcel;

	/** The list class sheet. */
	@Valid
	@NotEmpty
	private List<SheetRead<? extends RowSheetRead>> listSheetRead;

	/** The map sheet. */
	private Map<String, SheetRead<?>> mapSheet;

	/** The excel type. */
	@NotNull
	private ExcelType excelType;
	
	private boolean close;

	/**
	 * Instantiates a new excel read.
	 */
	public ExcelRead() {
		super();
		this.excelType = ExcelType.XLS;
		this.listSheetRead = new ArrayList<>();
		this.mapSheet = new HashMap<>();
		this.close=false;
	}

	/**
	 * Returns the populated {@link SheetRead} registered under the given sheet name.
	 *
	 * @param <T>            the concrete {@link SheetRead} type
	 * @param classSheetRead the class of the expected sheet type
	 * @param sheetName      the Excel sheet name used during registration
	 * @return the populated sheet, or {@code null} if not found
	 */
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<?>> T getSheet(Class<T> classSheetRead, String sheetName) {
		return (T) this.mapSheet.get(sheetName);
	}

	/**
	 * Gets the report excel.
	 *
	 * @return the report excel
	 */
	public InputStream getReportExcel() {
		return reportExcel;
	}

	/**
	 * Sets the Excel file to read from a byte array.
	 *
	 * @param reportExcel the file content as a byte array; ignored if empty or {@code null}
	 */
	public void setReportExcel(byte[] reportExcel) {
		if (ArrayUtils.isNotEmpty(reportExcel))
			this.reportExcel = new ByteArrayInputStream(reportExcel);
	}

	/**
	 * Sets the Excel file to read from an {@link java.io.InputStream}.
	 *
	 * @param reportExcel the input stream of the file
	 */
	public void setReportExcel(InputStream reportExcel) {
		this.reportExcel = reportExcel;
	}

	/**
	 * Sets the Excel file to read from a file system path.
	 *
	 * @param pathFile the absolute or relative path to the Excel file; ignored if blank
	 * @throws com.bld.read.report.excel.exception.ExcelReaderException if the file is not found
	 */
	public void setReportExcel(String pathFile) {
		if (StringUtils.isNotBlank(pathFile))
			try {
				this.reportExcel = new FileInputStream(pathFile);
			} catch (FileNotFoundException e) {
				throw new ExcelReaderException(e);
			}
	}

	
	
	/**
	 * Returns whether the underlying {@link java.io.InputStream} should be closed after reading.
	 *
	 * @return {@code true} if the stream will be closed automatically after reading
	 */
	public boolean isClose() {
		return close;
	}

	/**
	 * Sets whether the underlying {@link java.io.InputStream} should be closed after reading.
	 *
	 * @param close {@code true} to close the stream automatically after reading
	 */
	public void setClose(boolean close) {
		this.close = close;
	}

	/**
	 * Gets the list class sheet.
	 *
	 * @return the list class sheet
	 */
	public List<SheetRead<? extends RowSheetRead>> getListSheetRead() {
		return listSheetRead;
	}

	/**
	 * Registers a sheet to be extracted from the Excel file.
	 * <p>
	 * If {@code classSheetRead} extends {@link MapSheetRead}, the {@code keyField} parameter is
	 * mandatory and must match a field name in the row entity. For plain {@link SheetRead}
	 * subclasses, {@code keyField} is ignored.
	 * </p>
	 *
	 * @param <T>            the concrete {@link SheetRead} type
	 * @param classSheetRead the class of the sheet to register
	 * @param sheetName      the name of the Excel sheet to read (max 31 characters)
	 * @param keyField       the row field name to use as map key for {@link MapSheetRead}; {@code null} otherwise
	 * @throws Exception if the sheet name is too long, already registered, or the constructor cannot be invoked
	 */
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<? extends RowSheetRead>> void addSheetConvertion(Class<T> classSheetRead, String sheetName, String keyField) throws Exception {
		Constructor<?> constructor = null;
		T sheetRead = null;
		if (MapSheetRead.class.isAssignableFrom(classSheetRead)) {
			if (StringUtils.isEmpty(keyField))
				throw new ExcelReaderException(ExcelExceptionType.KEY_FIELD_IS_NOT_NULL);
			constructor = classSheetRead.getConstructor(String.class, String.class);
			sheetRead = (T) constructor.newInstance(sheetName, keyField);
		} else {
			constructor = classSheetRead.getConstructor(String.class);
			sheetRead = (T) constructor.newInstance(sheetName);
		}

		this.listSheetRead.add(sheetRead);
		if (sheetName.length() > SpreadsheetUtils.SHEET_NAME_SIZE)
			throw new ExcelReaderException(ExcelExceptionType.MAX_SHEET_NAME, null);
		if (this.mapSheet.containsKey(sheetName))
			throw new ExcelReaderException(ExcelExceptionType.MULTIPLE_SHEET_NAME, sheetName);
		this.mapSheet.put(sheetName, sheetRead);
	}

	/**
	 * Registers a plain {@link SheetRead} sheet to be extracted from the Excel file.
	 * Convenience overload of {@link #addSheetConvertion(Class, String, String)} with no key field.
	 *
	 * @param <T>            the concrete {@link SheetRead} type
	 * @param classSheetRead the class of the sheet to register
	 * @param sheetName      the name of the Excel sheet to read (max 31 characters)
	 * @throws Exception if the sheet name is too long, already registered, or the constructor cannot be invoked
	 */
	public <T extends SheetRead<? extends RowSheetRead>> void addSheetConvertion(Class<T> classSheetRead, String sheetName) throws Exception {
		this.addSheetConvertion(classSheetRead, sheetName, null);
	}

	/**
	 * Sets the list class sheet.
	 *
	 * @param listSheetRead the new list class sheet
	 */
	public void setListSheetRead(List<SheetRead<? extends RowSheetRead>> listSheetRead) {
		this.listSheetRead = listSheetRead;
	}

	/**
	 * Gets the excel type.
	 *
	 * @return the excel type
	 */
	public ExcelType getExcelType() {
		return excelType;
	}

	/**
	 * Sets the excel type.
	 *
	 * @param excelType the new excel type
	 */
	public void setExcelType(ExcelType excelType) {
		this.excelType = excelType;
	}

	/**
	 * Gets the map sheet.
	 *
	 * @return the map sheet
	 */
	public Map<String, SheetRead<?>> getMapSheet() {
		return mapSheet;
	}

	/**
	 * Sets the map sheet.
	 *
	 * @param mapSheetBySheetName the new map sheet
	 */
	public void setMapSheet(Map<String, SheetRead<?>> mapSheetBySheetName) {
		this.mapSheet = mapSheetBySheetName;
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
		result = prime * result + ((excelType == null) ? 0 : excelType.hashCode());
		result = prime * result + ((listSheetRead == null) ? 0 : listSheetRead.hashCode());
		result = prime * result + ((mapSheet == null) ? 0 : mapSheet.hashCode());
		result = prime * result + ((reportExcel == null) ? 0 : reportExcel.hashCode());
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
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ExcelRead)) {
			return false;
		}
		ExcelRead other = (ExcelRead) obj;
		if (excelType != other.excelType) {
			return false;
		}
		if (listSheetRead == null) {
			if (other.listSheetRead != null) {
				return false;
			}
		} else if (!listSheetRead.equals(other.listSheetRead)) {
			return false;
		}
		if (mapSheet == null) {
			if (other.mapSheet != null) {
				return false;
			}
		} else if (!mapSheet.equals(other.mapSheet)) {
			return false;
		}
		if (reportExcel == null) {
			if (other.reportExcel != null) {
				return false;
			}
		} else if (!reportExcel.equals(other.reportExcel)) {
			return false;
		}
		return true;
	}

}
