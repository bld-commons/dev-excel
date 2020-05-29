/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bld.read.report.excel.constant.ExcelType;

/**
 * The Class ExcelRead.
 */
public class ExcelRead {

	/** The report excel. */
	private byte[] reportExcel;
	
	/** The list class sheet. */
	private List<SheetRead<? extends RowSheetRead>> listSheetRead;
	
	/** The map sheet. */
	private Map<Class<? extends SheetRead<?>>,SheetRead<?>> mapSheet;
	
	/** The excel type. */
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
	 * @param <T>    the generic type
	 * @param classe the classe
	 * @return the sheet
	 */
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<?>> T getSheet(Class<T>classe) {
		return (T)this.mapSheet.get(classe);
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
	 * Sets the list class sheet.
	 *
	 * @param listSheetRead the new list class sheet
	 */
	public void setListSheetRead(List<SheetRead<? extends RowSheetRead>> listSheetRead) {
		this.listSheetRead = listSheetRead;
	}



	/**
	 * Gets the map sheet.
	 *
	 * @return the map sheet
	 */
	public Map<Class<? extends SheetRead<?>>, SheetRead<?>> getMapSheet() {
		return mapSheet;
	}



	/**
	 * Sets the map sheet.
	 *
	 * @param mapSheet the new map sheet
	 */
	public void setMapSheet(Map<Class<? extends SheetRead<?>>, SheetRead<?>> mapSheet) {
		this.mapSheet = mapSheet;
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
