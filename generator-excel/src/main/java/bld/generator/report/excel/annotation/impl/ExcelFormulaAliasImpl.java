/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelFormulaAliasImpl.java
 * 
 */
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelFormulaAlias;

/**
 * The Class ExcelFormulaAliasImpl.
 */
public class ExcelFormulaAliasImpl extends ExcelAnnotationImpl<ExcelFormulaAlias>{

	/** The alias. */
	private String alias;
	
	/** The coordinate. */
	private String coordinate;
	
	/** The sheet. */
	private String sheet;
	
	/** The block column. */
	private boolean blockColumn;
	
	/** The block row. */
	private boolean blockRow;
	
	

	
	/**
	 * Instantiates a new excel formula alias impl.
	 *
	 * @param alias the alias
	 * @param coordinate the coordinate
	 * @param sheet the sheet
	 * @param blockColumn the block column
	 * @param blockRow the block row
	 */
	public ExcelFormulaAliasImpl(String alias, String coordinate, String sheet, boolean blockColumn, boolean blockRow) {
		super();
		this.alias = alias;
		this.coordinate = coordinate;
		this.sheet = sheet;
		this.blockColumn = blockColumn;
		this.blockRow = blockRow;
	}

	/**
	 * Instantiates a new excel formula alias impl.
	 */
	public ExcelFormulaAliasImpl() {
		super();
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the coordinate.
	 *
	 * @return the coordinate
	 */
	public String getCoordinate() {
		return coordinate;
	}

	/**
	 * Sets the coordinate.
	 *
	 * @param coordinate the new coordinate
	 */
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Checks if is block column.
	 *
	 * @return true, if is block column
	 */
	public boolean isBlockColumn() {
		return blockColumn;
	}

	/**
	 * Sets the block column.
	 *
	 * @param blockColumn the new block column
	 */
	public void setBlockColumn(boolean blockColumn) {
		this.blockColumn = blockColumn;
	}

	/**
	 * Checks if is block row.
	 *
	 * @return true, if is block row
	 */
	public boolean isBlockRow() {
		return blockRow;
	}

	/**
	 * Sets the block row.
	 *
	 * @param blockRow the new block row
	 */
	public void setBlockRow(boolean blockRow) {
		this.blockRow = blockRow;
	}
	
	

	/**
	 * Gets the sheet.
	 *
	 * @return the sheet
	 */
	public String getSheet() {
		return sheet;
	}

	/**
	 * Sets the sheet.
	 *
	 * @param sheet the new sheet
	 */
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + (blockColumn ? 1231 : 1237);
		result = prime * result + (blockRow ? 1231 : 1237);
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + ((sheet == null) ? 0 : sheet.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelFormulaAliasImpl other = (ExcelFormulaAliasImpl) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (blockColumn != other.blockColumn)
			return false;
		if (blockRow != other.blockRow)
			return false;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (sheet == null) {
			if (other.sheet != null)
				return false;
		} else if (!sheet.equals(other.sheet))
			return false;
		return true;
	}
	
	
	
}
