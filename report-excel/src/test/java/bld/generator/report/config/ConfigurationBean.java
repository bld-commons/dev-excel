package bld.generator.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.impl.GenerateExcelImpl;

@Configuration
public class ConfigurationBean {

	@Bean
	public GenerateExcel generateExcel() {
		return new GenerateExcelImpl(); 
	}
	
}
