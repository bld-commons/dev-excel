/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.BorderStyle;

import bld.generator.report.excel.annotation.ExcelBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelBorderImpl.
 */
public class ExcelBorderImpl implements Cloneable{

	
	/** The left. */
	protected BorderStyle left;
	
	/** The top. */
	protected BorderStyle top;
	
	/** The right. */
	protected BorderStyle right;
	
	/** The bottom. */
	protected BorderStyle bottom;

	/**
	 * Gets the excel border.
	 *
	 * @return the excel border
	 */
	public ExcelBorder getExcelBorder() {
		ExcelBorder excelBorder=new ExcelBorder() {
			
			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelBorder.class;
			}
			
			@Override
			public BorderStyle top() {
				return top;
			}
			
			@Override
			public BorderStyle right() {
				return right;
			}
			
			@Override
			public BorderStyle left() {
				return left;
			}
			
			@Override
			public BorderStyle bottom() {
				return bottom;
			}
		};
		return excelBorder;
	}

	/**
	 * Instantiates a new excel border impl.
	 *
	 * @param left   the left
	 * @param top    the top
	 * @param right  the right
	 * @param bottom the bottom
	 */
	public ExcelBorderImpl(BorderStyle left, BorderStyle top, BorderStyle right, BorderStyle bottom){
		super();
		this.left = left;
		this.top = top;
		this.right = right;
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
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}


	
}
