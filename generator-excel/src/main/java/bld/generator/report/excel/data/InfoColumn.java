/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.InfoColumn.java
*/
package bld.generator.report.excel.data;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;

import bld.generator.report.utils.ExcelUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The Class InfoColumn.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InfoColumn extends InfoField {

	/** The column num. */
	private int columnNum;

	/** The row header. */
	private int rowHeader;
	
	/** The first row. */
	private Integer firstRow;
	
	/** The last row. */
	private Integer lastRow;
	
	/** The last row reference. */
	private Integer lastRowReference;
	
	/** The map row merge row. */
	private Map<Integer,MergeCell>mapRowMergeRow;
	

	/**
	 * Instantiates a new info column.
	 *
	 * @param worksheet   the worksheet
	 * @param sheetHeader the sheet header
	 * @param columnNum   the column num
	 * @param rowHeader   the row header
	 */
	public InfoColumn(Sheet worksheet, SheetHeader sheetHeader, int columnNum, int rowHeader) {
		super();
		if (sheetHeader.getField() != null)
			this.key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getField().getName());
		else if (StringUtils.isNotBlank(sheetHeader.getKeyMap()))
			this.key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getKeyMap());
		else if (sheetHeader.getExcelFunction() != null)
			this.key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getExcelFunction().nameFunction());
		this.columnNum = columnNum;
		this.rowHeader = rowHeader;
		this.mapRowMergeRow=new HashedMap<>();
	}
	
	
	
	
	/**
	 * Gets the merge cell.
	 *
	 * @return the merge cell
	 */
	public MergeCell getMergeCell() {
		MergeCell mergeCell=null;
		if(this.lastRowReference!=null && this.mapRowMergeRow.containsKey(lastRowReference))
			mergeCell=this.mapRowMergeRow.get(this.lastRowReference);
		return mergeCell;
	}

}
