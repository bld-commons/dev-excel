/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelBorderImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import org.apache.poi.ss.usermodel.BorderStyle;

import bld.generator.report.excel.annotation.ExcelBorder;


/**
 * The Class ExcelBorderImpl.
 */
public class ExcelBorderImpl extends ExcelAnnotationImpl<ExcelBorder> {

	/** The left. */
	private BorderStyle left;

	/** The top. */
	private BorderStyle top;

	/** The right. */
	private BorderStyle right;

	/** The bottom. */
	private BorderStyle bottom;

	/**
	 * Instantiates a new excel border impl.
	 *
	 * @param left   the left
	 * @param top    the top
	 * @param right  the right
	 * @param bottom the bottom
	 */
	public ExcelBorderImpl(BorderStyle left, BorderStyle top, BorderStyle right, BorderStyle bottom) {
		super();
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	/**
	 * Instantiates a new excel border impl.
	 */
	public ExcelBorderImpl() {
		super();
	}

	/**
	 * Gets the left.
	 *
	 * @return the left
	 */
	public BorderStyle getLeft() {
		return left;
	}

	/**
	 * Sets the left.
	 *
	 * @param left the new left
	 */
	public void setLeft(BorderStyle left) {
		this.left = left;
	}

	/**
	 * Gets the top.
	 *
	 * @return the top
	 */
	public BorderStyle getTop() {
		return top;
	}

	/**
	 * Sets the top.
	 *
	 * @param top the new top
	 */
	public void setTop(BorderStyle top) {
		this.top = top;
	}

	/**
	 * Gets the right.
	 *
	 * @return the right
	 */
	public BorderStyle getRight() {
		return right;
	}

	/**
	 * Sets the right.
	 *
	 * @param right the new right
	 */
	public void setRight(BorderStyle right) {
		this.right = right;
	}

	/**
	 * Gets the bottom.
	 *
	 * @return the bottom
	 */
	public BorderStyle getBottom() {
		return bottom;
	}

	/**
	 * Sets the bottom.
	 *
	 * @param bottom the new bottom
	 */
	public void setBottom(BorderStyle bottom) {
		this.bottom = bottom;
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
		result = prime * result + ((bottom == null) ? 0 : bottom.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + ((top == null) ? 0 : top.hashCode());
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
		ExcelBorderImpl other = (ExcelBorderImpl) obj;
		if (bottom != other.bottom)
			return false;
		if (left != other.left)
			return false;
		if (right != other.right)
			return false;
		if (top != other.top)
			return false;
		return true;
	}

}
