/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelColumnImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelColumnImpl.
 */
public class ExcelColumnImpl extends ExcelAnnotationImpl<ExcelColumn> {

	/** The name. */
	private String name;

	/** The comment. */
	private String comment;

	/** The index. */
	private double index;

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
	 * @param name the name
	 * @param index the index
	 */
	public ExcelColumnImpl(String name, double index) {
		super();
		this.name = name;
		this.index = index;
	}



	/**
	 * Instantiates a new excel column impl.
	 *
	 * @param name  the name
	 * @param comment     the comment
	 * @param index the index
	 * @param ignore      the ignore
	 */
	public ExcelColumnImpl(String name, String comment, double index, boolean ignore) {
		super();
		this.name = name;
		this.comment = comment;
		this.index = index;
		this.ignore = ignore;
	}


	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
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
	 * Gets the index.
	 *
	 * @return the index
	 */
	public double getIndex() {
		return index;
	}


	/**
	 * Sets the index.
	 *
	 * @param index the new index
	 */
	public void setIndex(double index) {
		this.index = index;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + (ignore ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(index);
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (ignore != other.ignore)
			return false;
		if (Double.doubleToLongBits(index) != Double.doubleToLongBits(other.index))
			return false;
		return true;
	}

}
