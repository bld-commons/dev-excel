/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.ExcelHyperlink.java
*/
package bld.generator.report.excel;

import org.apache.poi.common.usermodel.HyperlinkType;

/**
 * The Class ExcelHyperlink.
 * <br>
 * ExcelHyperlink is used by {@link bld.generator.report.excel.RowSheet} classes, it is used to write a hyperlink field in tables.
 */
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

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the hyperlink type.
	 *
	 * @return the hyperlink type
	 */
	public HyperlinkType getHyperlinkType() {
		return hyperlinkType;
	}

	/**
	 * Sets the hyperlink type.
	 *
	 * @param hyperlinkType the new hyperlink type
	 */
	public void setHyperlinkType(HyperlinkType hyperlinkType) {
		this.hyperlinkType = hyperlinkType;
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * Gets the column.
	 *
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * Sets the column.
	 *
	 * @param column the new column
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	
	
}
