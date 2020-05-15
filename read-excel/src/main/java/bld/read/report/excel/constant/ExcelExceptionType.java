package bld.read.report.excel.constant;

import lombok.Getter;

public enum ExcelExceptionType {
	
	
	SHEET_NOT_FOUND("SNF","The sheet \""+ExcelReaderConstant.PARAMETER+"\" not found"),
	COLUMN_NOT_FOUND("CNF","The column \""+ExcelReaderConstant.PARAMETER+"\" not found");
	
	@Getter
	private String code;
	
	@Getter
	private String message;

	private ExcelExceptionType(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	

}
