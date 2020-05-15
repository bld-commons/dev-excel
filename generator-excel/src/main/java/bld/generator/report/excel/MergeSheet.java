/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.MergeSheet.java
*/
package bld.generator.report.excel;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class MergeSheet.
 * <br>
 * MergeSheet is used to merge different SheetComponent type, through the field listSheet. <br>
 * SheetComponent is implemented from:<br> 
 * <ol>
 * <li>SheetData</li> 
 * <li>SheetSummary</li>
 * </ol>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MergeSheet extends BaseSheet {

	/** The list sheet. */
	private List<SheetComponent> listSheet;

	/**
	 * Instantiates a new merge sheet.
	 *
	 * @param listSheet the list sheet
	 * @param sheetName the name sheet
	 */
	public MergeSheet(List<SheetComponent> listSheet,String sheetName) {
		super(sheetName);
		this.listSheet = listSheet;
	}

	/**
	 * Instantiates a new merge sheet.
	 *
	 * @param sheetName the name sheet
	 */
	public MergeSheet(String sheetName) {
		super(sheetName);
		this.listSheet=new ArrayList<>();
	}


}
