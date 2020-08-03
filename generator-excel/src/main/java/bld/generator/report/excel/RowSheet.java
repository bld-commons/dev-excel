/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.RowSheet.java
*/

package bld.generator.report.excel;

/**
 * The Interface RowSheet.
 * <br>
 * RowSheet is used to create an object that represents the row of the sheet.<br>
 * To add the columns in the table, you need to add at least the following 2 annotations for each field you want to write
 * <ol>
 * <li>{@link bld.generator.report.excel.annotation.ExcelColumn} - to write the header title of the column</li>
 * <li>{@link bld.generator.report.excel.annotation.ExcelCellLayout} - to define the cell layout of each row</li>
 * </ol>
 */
public interface RowSheet {
	
	
}
