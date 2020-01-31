package bld.generator.report.excel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.GenerateExcel;

@Component
public class GenerateExcelImpl implements GenerateExcel{
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public byte[] createFileXls(ReportExcel report) throws Exception {
		ScopeGenerateExcelImpl scopeGenerateExcelImpl=this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
		return scopeGenerateExcelImpl.createFileXls(report);
	}

	@Override
	public byte[] createFileXlsx(ReportExcel report) throws Exception {
		ScopeGenerateExcelImpl scopeGenerateExcelImpl=this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
		return scopeGenerateExcelImpl.createFileXlsx(report);
	}
	
	
}
