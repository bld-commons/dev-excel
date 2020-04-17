/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.FunctionCell.java
*/
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;

import lombok.Data;

/**
 * The Class FunctionCell.
 */

/**
 * Instantiates a new function cell.
 */
@Data
public class FunctionCell {

	/** The cell. */
	private Cell cell; 
	
	/** The cell style. */
	private CellStyle cellStyle;
	
	/** The sheet header. */
	private SheetHeader sheetHeader;
	
	/** The index row. */
	private Integer indexRow;
	
	/** The worksheet. */
	private Sheet worksheet;
	
	/** The merge row. */
	private MergeCell mergeRow;
	
	
}
