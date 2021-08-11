/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.constant.AttachmentType.java
 * 
 */
package bld.generator.report.excel.constant;

/**
 * The Enum AttachmentType.
 */
public enum AttachmentType {

	/** The docx. */
	DOCX("/word.jpg",".docx"),
	
	/** The doc. */
	DOC("/word.jpg",".doc"),
	
	/** The xls. */
	XLS("/excel.jpg",".xls"),
	
	/** The xlsx. */
	XLSX("/excel.jpg",".xlsx"),
	
	/** The pptx. */
	PPTX("/power_point.jpg",".pptx"),
	
	/** The ppt. */
	PPT("/power_point.jpg",".ppt"),
	
	/** The pdf. */
	PDF("/pdf.jpg",".pdf"),;
	
	

	/** The image. */
	private String image;
	
	/** The file extension. */
	private String fileExtension;

	/**
	 * Instantiates a new attachment type.
	 *
	 * @param image the image
	 * @param fileExtension the file extension
	 */
	private AttachmentType(String image, String fileExtension) {
		this.image = image;
		this.fileExtension = fileExtension;
	}

	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Gets the file extension.
	 *
	 * @return the file extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	
	
	
}
