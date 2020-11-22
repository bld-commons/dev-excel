/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelColumnImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelColumn;

/**
 * The Class ExcelColumnImpl.
 */
public class ExcelColumnImpl extends ExcelAnnotationImpl<ExcelColumn> {

	/** The column name. */
	private String columnName;

	/** The comment. */
	private String comment;

	/** The index column. */
	private double indexColumn;

	/** The ignore. */
	private boolean ignore;

	
	
	/**
	 * Instantiates a new excel column impl.
	 */
	public ExcelColumnImpl() {
		super();
	}
	
	

	/**
	 * Instantiates a new excel column impl.
	 *
	 * @param columnName the column name
	 * @param indexColumn the index column
	 */
	public ExcelColumnImpl(String columnName, double indexColumn) {
		super();
		this.columnName = columnName;
		this.indexColumn = indexColumn;
	}



	/**
	 * Instantiates a new excel column impl.
	 *
	 * @param columnName  the name column
	 * @param comment     the comment
	 * @param indexColumn the index column
	 * @param ignore      the ignore
	 */
	public ExcelColumnImpl(String columnName, String comment, double indexColumn, boolean ignore) {
		super();
		this.columnName = columnName;
		this.comment = comment;
		this.indexColumn = indexColumn;
		this.ignore = ignore;
	}

	/**
	 * Gets the column name.
	 *
	 * @return the column name
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Sets the column name.
	 *
	 * @param columnName the new column name
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the index column.
	 *
	 * @return the index column
	 */
	public double getIndexColumn() {
		return indexColumn;
	}

	/**
	 * Sets the index column.
	 *
	 * @param indexColumn the new index column
	 */
	public void setIndexColumn(double indexColumn) {
		this.indexColumn = indexColumn;
	}

	/**
	 * Checks if is ignore.
	 *
	 * @return the ignore
	 */
	public boolean isIgnore() {
		return ignore;
	}

	/**
	 * Sets the ignore.
	 *
	 * @param ignore the new ignore
	 */
	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
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
		result = prime * result + ((columnName == null) ? 0 : columnName.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + (ignore ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(indexColumn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ExcelColumnImpl other = (ExcelColumnImpl) obj;
		if (columnName == null) {
			if (other.columnName != null)
				return false;
		} else if (!columnName.equals(other.columnName))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (ignore != other.ignore)
			return false;
		if (Double.doubleToLongBits(indexColumn) != Double.doubleToLongBits(other.indexColumn))
			return false;
		return true;
	}

}
