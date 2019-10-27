/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel;

import org.apache.poi.common.usermodel.HyperlinkType;

/**
 * The Class ExcelHyperlink.
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
	 * @param value the value
	 * @param address the address
	 * @param hyperlinkType the hyperlink type
	 * @param row the row
	 * @param column the column
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
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
	 * Gets the hyperlink type.
	 *
	 * @return the hyperlink type
	 */
	public HyperlinkType getHyperlinkType() {
		return hyperlinkType;
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
	 * Gets the column.
	 *
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((column == null) ? 0 : column.hashCode());
		result = prime * result + ((hyperlinkType == null) ? 0 : hyperlinkType.hashCode());
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelHyperlink other = (ExcelHyperlink) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (column == null) {
			if (other.column != null)
				return false;
		} else if (!column.equals(other.column))
			return false;
		if (hyperlinkType != other.hyperlinkType)
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public String getAddressDocument() {
		return "'"+this.address.replace("'", "''")+"'!"+this.column+this.row;
	}
	
	
}
