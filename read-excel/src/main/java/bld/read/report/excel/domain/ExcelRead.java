/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.domain;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.read.report.excel.constant.ExcelExceptionType;
import bld.read.report.excel.constant.ExcelType;
import bld.read.report.excel.exception.ExcelReaderException;

/**
 * The Class ExcelRead.<br>
 * ExcelRead is the object that represents the excel file.<br>
 * It is composed by:
 * <ul>
 * <li>ReportExcel - to set the excel file in byte array</li>
 * <li>ListSheetRead - to set a SheetRead list where each object is a new instance</li>
 * <li>MapSheet - to get result SheetRead list through class type</li>
 * <li>ExcelType - to set the xls or xlsx type</li>
 * </ul>
 */
public class ExcelRead {

	/** The report excel. */
	@NotNull
	private byte[] reportExcel;
	
	/** The list class sheet. */
	@Valid
	@NotEmpty
	private List<SheetRead<? extends RowSheetRead>> listSheetRead;
	
	
	/** The map sheet. */
	private Map<String,SheetRead<?>> mapSheet;
	
	
	
	/** The excel type. */
	@NotNull
	private ExcelType excelType;
	
	

	/**
	 * Instantiates a new excel read.
	 */
	public ExcelRead() {
		super();
		this.excelType=ExcelType.XLS;
		this.listSheetRead=new ArrayList<>();
		this.mapSheet=new HashMap<>();
	}

	
	

	/**
	 * Gets the sheet.
	 *
	 * @param <T>            the generic type
	 * @param classSheetRead the class sheet read
	 * @param sheetName      the sheet name
	 * @return the sheet
	 */
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<?>> T getSheet(Class<T>classSheetRead,String sheetName) {
		return (T)this.mapSheet.get(sheetName);
	}



	/**
	 * Gets the report excel.
	 *
	 * @return the report excel
	 */
	public byte[] getReportExcel() {
		return reportExcel;
	}



	/**
	 * Sets the report excel.
	 *
	 * @param reportExcel the new report excel
	 */
	public void setReportExcel(byte[] reportExcel) {
		this.reportExcel = reportExcel;
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
	 * Adds the sheet convertion.
	 *
	 * @param <T>            the generic type
	 * @param classSheetRead the class sheet read
	 * @param sheetName      the sheet name
	 * @param keyField       the key field
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<? extends RowSheetRead>> void addSheetConvertion(Class<T>classSheetRead,String sheetName,String keyField) throws Exception {
		Constructor<?> constructor = null;
		T sheetRead=null;
		if(MapSheetRead.class.isAssignableFrom(classSheetRead)) {
			if(StringUtils.isEmpty(keyField))
				throw new ExcelReaderException(ExcelExceptionType.KEY_FIELD_IS_NOT_NULL);
			constructor = classSheetRead.getConstructor(String.class,String.class);
			sheetRead= (T) constructor.newInstance(sheetName,keyField);
		}else {
			constructor = classSheetRead.getConstructor(String.class);
			sheetRead= (T) constructor.newInstance(sheetName);
		}
			
		
		this.listSheetRead.add(sheetRead);
		if(sheetName.length()>SpreadsheetUtils.SHEET_NAME_SIZE)
			throw new ExcelReaderException(ExcelExceptionType.MAX_SHEET_NAME, null);
		if(this.mapSheet.containsKey(sheetName))
			throw new ExcelReaderException(ExcelExceptionType.MULTIPLE_SHEET_NAME,sheetName);
		this.mapSheet.put(sheetName, sheetRead);
	}

	/**
	 * Adds the sheet convertion.
	 *
	 * @param <T>            the generic type
	 * @param classSheetRead the class sheet read
	 * @param sheetName      the sheet name
	 * @throws Exception the exception
	 */
	public <T extends SheetRead<? extends RowSheetRead>> void addSheetConvertion(Class<T>classSheetRead,String sheetName) throws Exception {
		this.addSheetConvertion(classSheetRead, sheetName,null);
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
		result = prime * result + Arrays.hashCode(reportExcel);
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
		ExcelRead other = (ExcelRead) obj;
		if (excelType != other.excelType)
			return false;
		if (listSheetRead == null) {
			if (other.listSheetRead != null)
				return false;
		} else if (!listSheetRead.equals(other.listSheetRead))
			return false;
		if (mapSheet == null) {
			if (other.mapSheet != null)
				return false;
		} else if (!mapSheet.equals(other.mapSheet))
			return false;
		if (!Arrays.equals(reportExcel, other.reportExcel))
			return false;
		return true;
	}



	

	


	
}
