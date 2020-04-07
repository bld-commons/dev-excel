/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.ExceltFont.java
*/
package bld.generator.report.excel.data;

import bld.generator.report.excel.constant.UnderlineType;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class ExceltFont.
 */
@Data
public class ExceltFont {

	/** The font. */
	private String font;

	/** The bold. */
	private boolean bold;
	
	/** The italic. */
	private boolean italic;
	
	/** The underline. */
	private UnderlineType underline;
	
	/** The size. */
	private short size;


	
	
}
