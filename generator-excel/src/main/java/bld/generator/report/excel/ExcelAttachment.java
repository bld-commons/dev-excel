package bld.generator.report.excel;

import bld.generator.report.excel.constant.AttachmentType;

public class ExcelAttachment<T> {

	private T attachment;
	
	private AttachmentType attachmentType;
	
	private String fileName;
	
	public static ExcelAttachment<byte[]> newInstance(byte[] attachment){
		return new ExcelAttachment<byte[]>(attachment);
	}
	
	public static ExcelAttachment<String> newInstance(String attachment){
		return new ExcelAttachment<String>(attachment);
	}
	
	private ExcelAttachment(T attachment){
		this.attachment=attachment;
	}

	public T getAttachment() {
		return attachment;
	}

	public void setAttachment(T attachment) {
		this.attachment = attachment;
	}

	public AttachmentType getAttachmentType() {
		return attachmentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setAttachmentType(AttachmentType attachmentType) {
		this.attachmentType = attachmentType;
	}

	public void setFileName(String nomeFile) {
		this.fileName = nomeFile;
	}
	
	
	

}
