/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.config.ExcelGeneratorConfiguration.java
*/
package bld.generator.report.excel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.query.ExcelDataSource;
import bld.generator.report.excel.query.ExcelQueryComponent;
import bld.generator.report.excel.query.impl.ExcelQueryComponentImpl;
import bld.generator.report.utils.ValueProps;

/**
 * The Class ExcelGeneratorConfiguration.<br>
 * This class is for configurations.
 */
@Configuration
public class ExcelGeneratorConfiguration {
	
	private static final String SPRING_DATASOURCE_URL = "spring.datasource.url";

	/** The generate excel. */
	@Autowired
	private GenerateExcel generateExcel;
	
	/** The value props. */
	@Autowired
	private ValueProps valueProps;

	/**
	 * Gets the generate excel.
	 *
	 * @return the generate excel
	 */
	protected GenerateExcel getGenerateExcel() {
		return generateExcel;
	}

	/**
	 * Gets the value props.
	 *
	 * @return the value props
	 */
	protected ValueProps getValueProps() {
		return valueProps;
	}

	
	@Bean
	@ConditionalOnProperty(value= {SPRING_DATASOURCE_URL,ExcelDataSource.MULTIPLE_DATASOURCE})
	public ExcelQueryComponent excelQueryComponent() {
		return new ExcelQueryComponentImpl();
	}
	
}
