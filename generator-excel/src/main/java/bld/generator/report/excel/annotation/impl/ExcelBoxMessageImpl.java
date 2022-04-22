/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelBoxMessageImpl.java
 */
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelBoxMessage;
import bld.generator.report.excel.constant.BoxStyle;

/**
 * The Class ExcelBoxImpl.
 */
public class ExcelBoxMessageImpl extends ExcelAnnotationImpl<ExcelBoxMessage> {
	
	/** The show. */
	private boolean show;
	
	/** The box style. */
	private BoxStyle boxStyle;
	
	/** The title. */
	private String title;
	
	/** The message. */
	private String message;
	
	
	/**
	 * Instantiates a new excel box impl.
	 */
	public ExcelBoxMessageImpl() {
		super();
	}
	
	/**
	 * Instantiates a new excel box impl.
	 *
	 * @param show the show
	 * @param boxStyle the box style
	 * @param title the title
	 * @param message the message
	 */
	public ExcelBoxMessageImpl(boolean show, BoxStyle boxStyle, String title, String message) {
		super();
		this.show = show;
		this.boxStyle = boxStyle;
		this.title = title;
		this.message = message;
	}

	/**
	 * Checks if is show.
	 *
	 * @return true, if is show
	 */
	public boolean isShow() {
		return show;
	}
	
	/**
	 * Sets the show.
	 *
	 * @param show the new show
	 */
	public void setShow(boolean show) {
		this.show = show;
	}
	
	/**
	 * Gets the box style.
	 *
	 * @return the box style
	 */
	public BoxStyle getBoxStyle() {
		return boxStyle;
	}
	
	/**
	 * Sets the box style.
	 *
	 * @param boxStyle the new box style
	 */
	public void setBoxStyle(BoxStyle boxStyle) {
		this.boxStyle = boxStyle;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
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
		result = prime * result + ((boxStyle == null) ? 0 : boxStyle.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + (show ? 1231 : 1237);
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ExcelBoxMessageImpl other = (ExcelBoxMessageImpl) obj;
		if (boxStyle != other.boxStyle)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (show != other.show)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	

}
