/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.utils;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * The Class ExcelUtils.
 */
@SuppressWarnings("unchecked")
@Component
public class ExcelUtils implements ApplicationContextAware {

	/** The Constant BLD_GENERATOR. */
	private static final String BLD_GENERATOR = "bld.generator";

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ExcelUtils.class);
	
	/** The Constant ANNOTATIONS. */
	public static final String ANNOTATIONS = "annotations";
	
	/** The Constant ANNOTATION_DATA. */
	public static final String ANNOTATION_DATA = "annotationData";
	
	/** The application context. */
	private static ApplicationContext applicationContext;

	/**
	 * Gets the annotation.
	 *
	 * @param <T>             the generic type
	 * @param classExcel      the class excel
	 * @param classAnnotation the class annotation
	 * @return the annotation
	 * @throws Exception the exception
	 */
	public static <T extends Annotation> T getAnnotation(Class<?> classExcel, Class<T> classAnnotation) throws Exception {
		if (!classExcel.isAnnotationPresent(classAnnotation))
			throw new Exception("Annotation " + classAnnotation.getSimpleName() + " is not presented on " + classExcel.getSimpleName());
		else
			return classExcel.getAnnotation(classAnnotation);
	}

	/**
	 * Gets the annotation.
	 *
	 * @param <T>             the generic type
	 * @param field           the field
	 * @param classAnnotation the class annotation
	 * @return the annotation
	 * @throws Exception the exception
	 */
	public static <T extends Annotation> T getAnnotation(Field field, Class<T> classAnnotation) throws Exception {
		if (!field.isAnnotationPresent(classAnnotation))
			throw new Exception("Annotation " + classAnnotation.getSimpleName() + " is not presented on " + field.getName());
		else
			return field.getAnnotation(classAnnotation);
	}

	/**
	 * Reflection annotation.
	 *
	 * @param <T>        the generic type
	 * @param <K>        the key type
	 * @param entity     the entity
	 * @param annotation the annotation
	 * @return the t
	 */
	public static <T, K extends Annotation> T reflectionAnnotation(T entity, K annotation) {
		
		
		List<Field> listField = new ArrayList<>();
		Class<?>classT=entity.getClass();
		do {
			listField.addAll(Arrays.asList(classT.getDeclaredFields()));
			classT=classT.getSuperclass();
		}while(classT.getSuperclass()!=null && !classT.getName().equals(Object.class.getName()));
		Map<String, Field> mapField = new HashMap<>();
		for (Field field : listField)
			mapField.put(field.getName(), field);
		Class<K> classAnnotation = (Class<K>) annotation.getClass();
		Class<T> classEntity = (Class<T>) entity.getClass();
		List<Method> listMethod = Arrays.asList(classAnnotation.getMethods());
		for (Method method : listMethod) {
			if (mapField.containsKey(method.getName())) {
				Field field = mapField.get(method.getName());
				Object value = null;
				String nameField = field.getName();
				String setMethod = "set" + ("" + nameField.charAt(0)).toUpperCase() + nameField.substring(1);
				Class<?> classField = field.getType();
				try {
					value = method.invoke(annotation);
					try {
						classEntity.getMethod(setMethod, classField).invoke(entity, value);
					} catch (Exception e) {
						if (Annotation.class.isAssignableFrom(value.getClass()) && field.getType().getName().startsWith(BLD_GENERATOR)) {
							Annotation fieldAnnotation = (Annotation) value;
							value = reflectionAnnotation(classField.newInstance(), fieldAnnotation);
							classEntity.getMethod(setMethod, classField).invoke(entity, value);

						}
					}

				} catch (Exception e) {
					logger.debug("The field " + nameField + " does not exist in annotation " + classAnnotation.getSimpleName());
				}
			}
		}

		return entity;
	}
	
	/**
	 * Adds the annotation.
	 *
	 * @param classe          the classe
	 * @param classAnnotation the class annotation
	 * @param annotation      the annotation
	 * @throws Exception the exception
	 */
	public static void addAnnotation(Class<?>classe,Class<? extends Annotation>classAnnotation,Annotation annotation) throws Exception {
		Field annotationFieldData = Class.class.getDeclaredField(ANNOTATION_DATA);
		annotationFieldData.setAccessible(true);
		Object annotationData = annotationFieldData.get(classe);
		Field annotationsField = annotationData.getClass().getDeclaredField(ANNOTATIONS);
		annotationsField.setAccessible(true);
		Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) annotationsField.get(annotationData);
		annotations.put(classAnnotation, annotation);
	}

	/**
	 * Sets the application context.
	 *
	 * @param ac the new application context
	 */
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext=ac;
	}
	
	/**
	 * Gets the application context.
	 *
	 * @return the application context
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	
	/**
	 * Gets the name parameter.
	 *
	 * @param parameter the parameter
	 * @return the name parameter
	 */
	public static String getNameParameter(String parameter) {
		parameter=WordUtils.capitalize(parameter.replace("_", " ")).replace(" ", "");
		return (parameter.charAt(0)+"").toLowerCase()+parameter.substring(1);
	}
	
	/**
	 * Write to file.
	 *
	 * @param pathFile the path file
	 * @param fileName the file name
	 * @param typeFile the type file
	 * @param dati     the dati
	 */
	public static void writeToFile(String pathFile,String fileName, String typeFile, byte[] dati) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(pathFile + fileName + typeFile);
			fos.write(dati);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Calcolo coordinate function.
	 *
	 * @param row    the row
	 * @param column the column
	 * @return the string
	 */
	public static String calcoloCoordinateFunction(int row, int column) {
		int mod = 0;
		int div = column;
		String coordinata = "";
		do {
			mod = div % 26;
			mod += 65;
			div = (div / 26) - 1;
			coordinata = Character.toString((char) mod) + coordinata;
		} while (div >= 0);
		coordinata = coordinata + row;

		return coordinata;
	}


}
