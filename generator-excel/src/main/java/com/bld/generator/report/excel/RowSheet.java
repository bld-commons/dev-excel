/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.RowSheet.java
*/

package com.bld.generator.report.excel;

/**
 * The Interface RowSheet.
 * <br>
 * RowSheet is implemented to create an object that represents the row of the sheet.<br>
 * Within the object you need to add at least the following 2 annotations for each field to write the columns of the table.
 * <ol>
 * <li>{@link com.bld.generator.report.excel.annotation.ExcelColumn} - to write the header title of the column</li>
 * <li>{@link com.bld.generator.report.excel.annotation.ExcelCellLayout} - to define the cell layout of each row</li>
 * </ol>
 * 
 * Below an example of table generated by merging {@link com.bld.generator.report.excel.SheetData} and {@link com.bld.generator.report.excel.RowSheet} classes.
 * <table style="width:100%;">
 * <tr>
 * 	<th style="width: 8%"></th>
 *   <th style="width: 23%; border: 1px solid #666; text-align: center;">column name of the field 1</th>
 *   <th style="width: 23%; border: 1px solid #666; text-align: center;">column name of the field 2</th>
 *   <th style="width: 23%; border: 1px solid #666; text-align: center;">column name of the field 3</th>
 *   <th style="width: 23%; border: 1px solid #666; text-align: center;">column name of the field ...N</th>
 * </tr>
 * <tr>
 *   <td style="width: 8%; ">row 1</td>
 *   <td style="width: 23%; border: 1px solid #666;">value of the field 1</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field 2</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field 3</td>
 *  	<td style="width: 23%; border: 1px solid #666">value of the field ...N</td>
 * </tr>
 * <tr>
 *   <td style="width: 8%; ">row 2</td>
 *   <td style="width: 23%; border: 1px solid #666;">value of the field 1</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field 2</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field 3</td>
 *  	<td style="width: 23%; border: 1px solid #666">value of the field ...N</td>
 * </tr>
 * <tr>
 *   <td style="width: 8%; ">row N</td>
 *   <td style="width: 23%; border: 1px solid #666;">value of the field 1</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field 2</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field 3</td>
 *   <td style="width: 23%; border: 1px solid #666">value of the field ...N</td>
 * </tr>
 * 
 *</table>
 * 
 */
public interface RowSheet {
	
	
}
