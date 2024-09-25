/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.ExcelAttachment.java
 */
package com.bld.generator.report.excel;

import com.bld.generator.report.excel.constant.AttachmentType;

/**
 * The Class ExcelAttachment.<br>
 * ExcelAttachment is used to attachment files within file excel.<br>
 * It is composed by:
 * <ul>
 * <li>Attachment - to define the file</li>
 * <li>AttachmentType - to set type file</li>
 * <li>FileName - to define file name</li>
 * </ul>
 * The "attachment" field can be a byte array or a string, the string type represent the file path.<br>
 * It is used by {@link com.bld.generator.report.excel.RowSheet} classes.<br>
 * @param <T> the generic type
 */
public class ExcelAttachment<T> {

	/** The attachment. */
	private T attachment;
	
	/** The attachment type. */
	private AttachmentType attachmentType;
	
	/** The file name. */
	private String fileName;
	
	/**
	 * New instance.
	 *
	 * @param attachment the attachment
	 * @return the excel attachment
	 */
	public static ExcelAttachment<byte[]> newInstance(byte[] attachment){
		return new ExcelAttachment<byte[]>(attachment);
	}
	
	/**
	 * New instance.
	 *
	 * @param attachment the attachment
	 * @return the excel attachment
	 */
	public static ExcelAttachment<String> newInstance(String attachment){
		return new ExcelAttachment<String>(attachment);
	}
	
	/**
	 * Instantiates a new excel attachment.
	 *
	 * @param attachment the attachment
	 */
	private ExcelAttachment(T attachment){
		this.attachment=attachment;
	}

	/**
	 * Gets the attachment.
	 *
	 * @return the attachment
	 */
	public T getAttachment() {
		return attachment;
	}

	/**
	 * Sets the attachment.
	 *
	 * @param attachment the new attachment
	 */
	public void setAttachment(T attachment) {
		this.attachment = attachment;
	}

	/**
	 * Gets the attachment type.
	 *
	 * @return the attachment type
	 */
	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the attachment type.
	 *
	 * @param attachmentType the new attachment type
	 */
	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	/**
	 * Sets the file name.
	 *
	 * @param nomeFile the new file name
	 */
	public void setFileName(String nomeFile) {
		this.fileName = nomeFile;
	}
	
	
	

}
