/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelColumnImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;

import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.utils.ExcelUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelColumnImpl.
 */
public class ExcelColumnImpl implements Cloneable{

	/** The name column. */
	protected String nameColumn;

	/** The comment. */
	protected String comment;

	/** The index column. */
	protected double indexColumn;
	
	/** The ignore. */
	protected boolean ignore;
	
	
	/**
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		ExcelColumn excelColumn = null;
		if (StringUtils.isNotBlank(nameColumn) )
			excelColumn = new ExcelColumn() {

				@Override
				public Class<? extends Annotation> annotationType() {
					return ExcelColumn.class;
				}

				@Override
				public String nameColumn() {
					return nameColumn;
				}

				@Override
				public String comment() {
					return comment;
				}

				@Override
				public double indexColumn() {
					return indexColumn;
				}

				@Override
				public boolean ignore() {
					
					return ignore;
				}

			

			};

		return excelColumn;
	}

	
	

	/**
	 * Instantiates a new excel column impl.
	 *
	 * @param nameColumn  the name column
	 * @param comment     the comment
	 * @param indexColumn the index column
	 * @param ignore      the ignore
	 */
	public ExcelColumnImpl(String nameColumn, String comment, double indexColumn, boolean ignore) {
		super();
		this.nameColumn = nameColumn;
		this.comment = comment;
		this.indexColumn = indexColumn;
		this.ignore = ignore;
	}

	

	/**
	 * Gets the name column.
	 *
	 * @return the name column
	 */
	public String getNameColumn() {
		return nameColumn;
	}

	/**
	 * Sets the name column.
	 *
	 * @param nameColumn the new name column
	 */
	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
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
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + (ignore ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(indexColumn);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((nameColumn == null) ? 0 : nameColumn.hashCode());
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
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (ignore != other.ignore)
			return false;
		if (Double.doubleToLongBits(indexColumn) != Double.doubleToLongBits(other.indexColumn))
			return false;
		if (nameColumn == null) {
			if (other.nameColumn != null)
				return false;
		} else if (!nameColumn.equals(other.nameColumn))
			return false;
		return true;
	}

	

	

}
