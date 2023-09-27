/*
 * 
 */
package bld.generator.report.excel.dropdown;

import bld.generator.report.excel.constant.BoxStyle;


/**
 * The Class BoxMessage.
 */
public class BoxMessage {

	/** The show. */
	private boolean show;
	
	/** The box style. */
	private BoxStyle boxStyle;
	
	/** The title. */
	private String title;
	
	/** The message. */
	private String message;

	
	
	
	/**
	 * Instantiates a new box message.
	 */
	public BoxMessage() {
		super();
		this.show=true;
		this.boxStyle=BoxStyle.STOP;
		this.title="Error";
		this.message="The value is not valid";
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
	
	
}
