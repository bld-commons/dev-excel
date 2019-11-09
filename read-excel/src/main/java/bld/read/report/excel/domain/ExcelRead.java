/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bld.read.report.utils.ExcelType;

/**
 * The Class ExcelRead.
 */
public class ExcelRead {

	/** The report excel. */
	private byte[] reportExcel;
	
	/** The list class sheet. */
	private List<Class<? extends SheetRead<?>>> listClassSheet;
	
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
		this.listClassSheet=new ArrayList<>();
		this.mapSheet=new HashMap<>();
		
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
	public List<Class<? extends SheetRead<?>>> getListClassSheet() {
		return listClassSheet;
	}

	/**
	 * Sets the list class sheet.
	 *
	 * @param listClassSheet the new list class sheet
	 */
	public void setListClassSheet(List<Class<? extends SheetRead<?>>> listClassSheet) {
		this.listClassSheet = listClassSheet;
	}

	/**
	 * Gets the map sheet.
	 *
	 * @return the map sheet
	 */
	public Map<Class<? extends SheetRead<?>>, SheetRead<?>> getMapSheet() {
		return mapSheet;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<?>> T getSheet(Class<T>classe) {
		return (T)this.mapSheet.get(classe);
	}
	

	/**
	 * Sets the map sheet.
	 *
	 * @param mapSheet the map sheet
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

	
}
