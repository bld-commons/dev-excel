/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.utils.ValueProps;

/**
 * The Class ExcelGeneratorConfiguration.
 */
@Configuration
public class ExcelGeneratorConfiguration {
	
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

	
	
	
}
