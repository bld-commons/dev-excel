package bld.read.report.excel.exception;

import bld.read.report.excel.constant.ExcelExceptionType;
import bld.read.report.excel.constant.ExcelReaderConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class ExcelReaderException extends Exception {
	
	private String code;
	
	private String parameter;

	
	public ExcelReaderException(ExcelExceptionType excelExceptionType,String parameter) {
		super(excelExceptionType.getMessage().replace(ExcelReaderConstant.PARAMETER, parameter));
		this.code=excelExceptionType.getCode();
		this.parameter=parameter;
	}

	

}
