package bld.generator.report.excel.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import bld.generator.report.excel.config.annotation.EnableExcelGenerator;
@SuppressWarnings("resource")
public class EnableExcelGeneratorConfig implements ImportBeanDefinitionRegistrar {

	private final static Log logger = LogFactory.getLog(EnableExcelGeneratorConfig.class);

	@Override
	public void registerBeanDefinitions(AnnotationMetadata enableAnnotationMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes enableAnnotationAttributes = new AnnotationAttributes(enableAnnotationMetadata.getAnnotationAttributes(EnableExcelGenerator.class.getName()));

		String[] basePackages = enableAnnotationAttributes.getStringArray("basePackages");
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(basePackages);
		logger.info(annotationConfigApplicationContext.getBeanDefinitionNames().length);
	}

}
