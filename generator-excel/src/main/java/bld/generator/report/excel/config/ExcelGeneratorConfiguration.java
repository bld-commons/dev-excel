/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.config.ExcelGeneratorConfiguration.java
*/
package bld.generator.report.excel.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bld.generator.report.excel.GenerateExcel;
import bld.generator.report.excel.annotation.impl.ExcelAnnotationImpl;
import bld.generator.report.excel.config.annotation.IgnoreCheck;
import bld.generator.report.utils.ExcelUtils;
import bld.generator.report.utils.ValueProps;

/**
 * The Class ExcelGeneratorConfiguration.<br>
 * This class is for configurations.
 */
@Configuration
public class ExcelGeneratorConfiguration {

	private static final String BLD_COMMONS_CHECK_ANNOTATION = "bld.commons.check.annotation";

	/** The Constant SPRING_DATASOURCE_URL. */
	public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ExcelGeneratorConfiguration.class);

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

	/**
	 * Check entity annotation.
	 *
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@Bean
	@ConditionalOnProperty(name = BLD_COMMONS_CHECK_ANNOTATION, havingValue = "true", matchIfMissing = false)
	public void checkEntityAnnotation() throws Exception {
		Reflections reflections = new Reflections("bld.generator.report.excel.annotation.impl");

		Set<Class<? extends ExcelAnnotationImpl>> allClasses = reflections.getSubTypesOf(ExcelAnnotationImpl.class);
		for (Class<? extends ExcelAnnotationImpl> classExcelAnnotationImpl : allClasses) {
			if (!classExcelAnnotationImpl.isAnnotationPresent(IgnoreCheck.class)) {
				logger.debug("Start Check class: " + classExcelAnnotationImpl.getName());
				ExcelAnnotationImpl<?> excelAnnotationImpl = classExcelAnnotationImpl.newInstance();
				Class<? extends Annotation> classAnnotation = excelAnnotationImpl.getClassAnnotation();
				List<Method> listMethod = Arrays.asList(classAnnotation.getDeclaredMethods());
				Map<String, Field> mapField = ExcelUtils.getMapField(classExcelAnnotationImpl);
				for (Method method : listMethod) {
					if (!mapField.containsKey(method.getName()))
						throw new Exception("The \"" + method.getName() + "\" field is not present within " + classExcelAnnotationImpl.getSimpleName() + " class");
					else if (mapField.get(method.getName()).isAnnotationPresent(IgnoreCheck.class))
						throw new Exception("The \"" + method.getName() + "\" field  can not contain the \"IgnoreCheck\" annotation within " + classExcelAnnotationImpl.getSimpleName() + " class");
					
				}
				logger.debug("The \"" + classExcelAnnotationImpl.getSimpleName() + "\" is ok");
			}

		}
	}

}
