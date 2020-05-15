/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.ExcelHyperlink.java
*/
package bld.generator.report.excel;

import org.apache.poi.common.usermodel.HyperlinkType;

import lombok.Data;

/**
 * The Class ExcelHyperlink.
 * <br>
 * ExcelHyperlink is used by RowSheet classes, it is used to write a hyperlink field in tables
 */
@Data
public class ExcelHyperlink {

	/** The value. */
	private String value;
	
	/** The address. */
	private String address;
	
	/** The hyperlink type. */
	private HyperlinkType hyperlinkType;
	
	/** The row. */
	private Integer row;
	
	/** The column. */
	private String column;

	/**
	 * Instantiates a new excel hyperlink.
	 *
	 * @param value         the value
	 * @param address       the address
	 * @param hyperlinkType the hyperlink type
	 * @param row           the row
	 * @param column        the column
	 */
	public ExcelHyperlink(String value, String address, HyperlinkType hyperlinkType, Integer row, String column) {
		super();
		this.value = value;
		this.address = address;
		this.hyperlinkType = hyperlinkType;
		this.row = row;
		this.column = column;
	}

	/**
	 * Gets the address document.
	 *
	 * @return the address document
	 */
	public String getAddressDocument() {
		return "'"+this.address.replace("'", "''")+"'!"+this.column+this.row;
	}
	
	
}
