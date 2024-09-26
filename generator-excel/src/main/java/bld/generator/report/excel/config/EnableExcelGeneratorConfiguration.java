/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.config.EnableExcelGeneratorConfiguration.java
 * 
 */
package bld.generator.report.excel.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import bld.common.spreadsheet.utils.SpreadsheetUtils;
import bld.generator.report.excel.annotation.impl.ExcelAnnotationImpl;
import bld.generator.report.excel.config.annotation.IgnoreCheck;
import jakarta.annotation.PostConstruct;

/**
 * The Class EnableExcelGeneratorConfiguration.
 */
@Configuration
@ComponentScan({"bld.generator","bld.common.spreadsheet"})
public class EnableExcelGeneratorConfiguration {
	
	/** The Constant BLD_COMMONS_CHECK_ANNOTATION. */
	private static final String BLD_COMMONS_CHECK_ANNOTATION = "bld.commons.check.annotation";

	/** The Constant SPRING_DATASOURCE_URL. */
	public static final String SPRING_DATASOURCE_URL = "spring.datasource.url";

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(EnableExcelGeneratorConfiguration.class);


	/**
	 * Check entity annotation.<br>
	 * <b>The property "bld.commons.check.annotation" must be false.</b><br> 
	 * @throws Exception the exception
	 */
	@SuppressWarnings("rawtypes")
	@PostConstruct
	@ConditionalOnProperty(name = BLD_COMMONS_CHECK_ANNOTATION, havingValue = "true", matchIfMissing = false)
	void checkEntityAnnotation() throws Exception {
		Reflections reflections = new Reflections("bld.generator.report.excel.annotation.impl");

		Set<Class<? extends ExcelAnnotationImpl>> allClasses = reflections.getSubTypesOf(ExcelAnnotationImpl.class);
		for (Class<? extends ExcelAnnotationImpl> classExcelAnnotationImpl : allClasses) {
			if (!classExcelAnnotationImpl.isAnnotationPresent(IgnoreCheck.class)) {
				logger.debug("Start Check class: " + classExcelAnnotationImpl.getName());
				ExcelAnnotationImpl<?> excelAnnotationImpl = classExcelAnnotationImpl.getDeclaredConstructor().newInstance();
				Class<? extends Annotation> classAnnotation = excelAnnotationImpl.getClassAnnotation();
				List<Method> listMethod = Arrays.asList(classAnnotation.getDeclaredMethods());
				Map<String, Field> mapField = SpreadsheetUtils.getMapField(classExcelAnnotationImpl);
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
