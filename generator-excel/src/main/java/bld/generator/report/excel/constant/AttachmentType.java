package bld.generator.report.excel.constant;

public enum AttachmentType {

	DOCX("/word.jpg",".docx"),
	DOC("/word.jpg",".doc"),
	XLS("/excel.jpg",".xls"),
	XLSX("/excel.jpg",".xlsx"),
	PPTX("/power_point.jpg",".pptx"),
	PPT("/power_point.jpg",".ppt"),
	PDF("/pdf.jpg",".pdf"),;
	
	

	private String image;
	
	private String fileExtension;

	private AttachmentType(String image, String fileExtension) {
		this.image = image;
		this.fileExtension = fileExtension;
	}

	public String getImage() {
		return image;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	
	
	
}
