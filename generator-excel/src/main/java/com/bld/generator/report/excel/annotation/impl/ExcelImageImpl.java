/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.annotation.impl.ExcelImageImpl.java
 */
package com.bld.generator.report.excel.annotation.impl;

import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;

import com.bld.generator.report.excel.annotation.ExcelImage;


/**
 * The Class ExcelImageImpl.
 */
public class ExcelImageImpl extends ExcelAnnotationImpl<ExcelImage> {

	/** The picture type. */
	private PictureType pictureType;

	/** The anchor type. */
	private AnchorType anchorType;

	/** The resize height. */
	private double resizeHeight;

	/** The resize width. */
	private double resizeWidth;

	/**
	 * Instantiates a new excel image impl.
	 */
	public ExcelImageImpl() {
		super();
	}

	/**
	 * Instantiates a new excel image impl.
	 *
	 * @param pictureType  the picture type
	 * @param anchorType   the anchor type
	 * @param resizeHeight the resize height
	 * @param resizeWidth  the resize width
	 */
	public ExcelImageImpl(PictureType pictureType, AnchorType anchorType, double resizeHeight, double resizeWidth) {
		super();
		this.pictureType = pictureType;
		this.anchorType = anchorType;
		this.resizeHeight = resizeHeight;
		this.resizeWidth = resizeWidth;
	}

	/**
	 * Gets the picture type.
	 *
	 * @return the picture type
	 */
	public PictureType getPictureType() {
		return pictureType;
	}

	/**
	 * Sets the picture type.
	 *
	 * @param pictureType the new picture type
	 */
	public void setPictureType(PictureType pictureType) {
		this.pictureType = pictureType;
	}

	/**
	 * Gets the anchor type.
	 *
	 * @return the anchor type
	 */
	public AnchorType getAnchorType() {
		return anchorType;
	}

	/**
	 * Sets the anchor type.
	 *
	 * @param anchorType the new anchor type
	 */
	public void setAnchorType(AnchorType anchorType) {
		this.anchorType = anchorType;
	}

	/**
	 * Gets the resize height.
	 *
	 * @return the resize height
	 */
	public double getResizeHeight() {
		return resizeHeight;
	}

	/**
	 * Sets the resize height.
	 *
	 * @param resizeHeight the new resize height
	 */
	public void setResizeHeight(double resizeHeight) {
		this.resizeHeight = resizeHeight;
	}

	/**
	 * Gets the resize width.
	 *
	 * @return the resize width
	 */
	public double getResizeWidth() {
		return resizeWidth;
	}

	/**
	 * Sets the resize width.
	 *
	 * @param resizeWidth the new resize width
	 */
	public void setResizeWidth(double resizeWidth) {
		this.resizeWidth = resizeWidth;
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
		result = prime * result + ((anchorType == null) ? 0 : anchorType.hashCode());
		result = prime * result + ((pictureType == null) ? 0 : pictureType.hashCode());
		long temp;
		temp = Double.doubleToLongBits(resizeHeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(resizeWidth);
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
		ExcelImageImpl other = (ExcelImageImpl) obj;
		if (anchorType != other.anchorType)
			return false;
		if (pictureType != other.pictureType)
			return false;
		if (Double.doubleToLongBits(resizeHeight) != Double.doubleToLongBits(other.resizeHeight))
			return false;
		if (Double.doubleToLongBits(resizeWidth) != Double.doubleToLongBits(other.resizeWidth))
			return false;
		return true;
	}

}
