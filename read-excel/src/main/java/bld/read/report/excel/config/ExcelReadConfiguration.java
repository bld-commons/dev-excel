package bld.read.report.excel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import bld.read.report.excel.ReadExcel;

@Configuration
public class ExcelReadConfiguration {
	
	@Autowired
	private ReadExcel readExcel;

	protected ReadExcel getReadExcel() {
		return readExcel;
	}

	protected void setReadExcel(ReadExcel readExcel) {
		this.readExcel = readExcel;
	}

	
	

	
	
	
}
