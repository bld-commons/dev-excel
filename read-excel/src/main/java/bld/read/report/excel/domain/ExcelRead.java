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
import lombok.Data;

/**
 * The Class ExcelRead.
 */
@Data
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

	
	
	@SuppressWarnings("unchecked")
	public <T extends SheetRead<?>> T getSheet(Class<T>classe) {
		return (T)this.mapSheet.get(classe);
	}
	


	
}
