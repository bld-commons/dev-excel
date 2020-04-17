/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.ExcelBorder.java
*/
package bld.generator.report.excel.data;

import org.apache.poi.ss.usermodel.BorderStyle;

import lombok.Data;

/**
 * The Class ExcelBorder.
 */

/**
 * Instantiates a new excel border.
 */
@Data
public class ExcelBorder {

	/** The top. */
	private BorderStyle top;

	/** The bottom. */
	private BorderStyle bottom;

	/** The left. */
	private BorderStyle left;

	/** The right. */
	private BorderStyle right;
	

	

}
