/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelAnnotationImpl.java
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bld.generator.report.excel.config.annotation.IgnoreCheck;
import bld.generator.report.utils.ExcelUtils;
import io.leangen.geantyref.TypeFactory;

/**
 * The Class ExcelAnnotationImpl.
 *
 * @param <T> the generic type
 */
@IgnoreCheck
public abstract class ExcelAnnotationImpl<T extends Annotation> implements Cloneable{
	
	/** The Constant logger. */
	@IgnoreCheck
	private final static Log logger = LogFactory.getLog(ExcelAnnotationImpl.class);
	
	/** The class annotation. */
	@IgnoreCheck
	private Class<T>classAnnotation;
	

	/**
	 * Instantiates a new excel annotation impl.
	 */
	public ExcelAnnotationImpl() {
		super();
		this.classAnnotation=ExcelUtils.getTClass(this);
	}


	/**
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
	/**
	 * Gets the annotation.
	 *
	 * @return the annotation
	 */
	public T getAnnotation(){
		T annotation=null;
		try {
			Set<Field> listField = ExcelUtils.getListField(this.getClass());
			Map<String,Object>mapParameters=new HashMap<>();
			for(Field field:listField) {
				if(!field.isAnnotationPresent(IgnoreCheck.class)) {
					Object value=PropertyUtils.getProperty(this, field.getName());
					if(value!=null)
						mapParameters.put(field.getName(), value);
				}
			}
			annotation=TypeFactory.annotation(classAnnotation, mapParameters);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}
		return annotation;
	}


	/**
	 * Gets the class annotation.
	 *
	 * @return the class annotation
	 */
	public Class<T> getClassAnnotation() {
		return classAnnotation;
	}
	
	
	
	
	
}
