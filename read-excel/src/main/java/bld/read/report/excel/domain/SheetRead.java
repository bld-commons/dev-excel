/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * The Class SheetRead.
 *
 * @param <T> the generic type
 */
@Data
public abstract class SheetRead<T extends RowSheetRead> {

	/** The list row sheet. */
	private List<T>listRowSheet;
	
	private String sheetName;
	
	
	public SheetRead(String sheetName) {
		super();
		this.listRowSheet=new ArrayList<>();
		this.sheetName=sheetName;
	}
	
}
