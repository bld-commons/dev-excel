/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.MergeCell.java
*/
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import lombok.Data;

/**
 * The Class MergeCell.
 */
@Data
public class MergeCell {

	/** The row start. */
	private int rowStart;

	/** The row end. */
	private int rowEnd;

	/** The column from. */
	private int columnFrom;

	/** The column to. */
	private int columnTo;
	
	/** The sheet header. */
	private SheetHeader sheetHeader;
	
	/** The cell from. */
	private Cell cellFrom;
	
	/** The cell style from. */
	private CellStyle cellStyleFrom;
	
}
