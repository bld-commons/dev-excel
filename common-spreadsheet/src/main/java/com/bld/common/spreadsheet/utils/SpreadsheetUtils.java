package com.bld.common.spreadsheet.utils;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.exception.SpreadsheetException;

@SuppressWarnings("unchecked")
@Component
public class SpreadsheetUtils {

	/** The Constant SHEET_NAME_SIZE. */
	public final static int SHEET_NAME_SIZE = 31;

	private static final List<String> CLASS_PACKAGES = Arrays.asList("com.bld.generator", "com.bld.common");


	/**
	 * Gets the annotation.
	 *
	 * @param <T>             the generic type
	 * @param classExcel      the class excel
	 * @param classAnnotation the class annotation
	 * @return the annotation
	 */
	public static <T extends Annotation> T getAnnotation(Class<?> classExcel, Class<T> classAnnotation) {
		if (!classExcel.isAnnotationPresent(classAnnotation))
			throw new SpreadsheetException("Annotation " + classAnnotation.getSimpleName() + " is not presented on " + classExcel.getSimpleName());
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
	 */
	public static <T extends Annotation> T getAnnotation(Field field, Class<T> classAnnotation) {
		if (!field.isAnnotationPresent(classAnnotation))
			throw new SpreadsheetException("Annotation " + classAnnotation.getSimpleName() + " is not presented on " + field.getName());
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
		Map<String, Field> mapField = getMapField(entity.getClass());
		Class<K> classAnnotation = (Class<K>) annotation.getClass();
		Class<T> classEntity = (Class<T>) entity.getClass();
		List<Method> listMethod = Arrays.asList(classAnnotation.getDeclaredMethods());
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
						Annotation fieldAnnotation = null;
						if (Annotation.class.isAssignableFrom(value.getClass()) && startsWith(field.getType().getName())) {
							fieldAnnotation = (Annotation) value;
							value = reflectionAnnotation(classField.getDeclaredConstructor().newInstance(), fieldAnnotation);
							classEntity.getMethod(setMethod, classField).invoke(entity, value);
						} else if (Annotation.class.isAssignableFrom(value.getClass()) && field.getType().isArray() && startsWith(field.getType().getComponentType().getName())) {
							fieldAnnotation = (Annotation) value;
							value = reflectionAnnotation(field.getType().getComponentType().getDeclaredConstructor().newInstance(), fieldAnnotation);
							Object[] array = (Object[]) Array.newInstance(field.getType().getComponentType(), 1);
							Array.set(array, 0, value);
							classEntity.getMethod(setMethod, classField).invoke(entity, new Object[] { array });

						} else if (value.getClass().isArray() && Annotation.class.isAssignableFrom(((Object[]) value)[0].getClass())) {
							Object[] list = (Object[]) Array.newInstance(field.getType().getComponentType(), ((Annotation[]) value).length);
							for (int i = 0; i < ((Annotation[]) value).length; i++) {
								fieldAnnotation = ((Annotation[]) value)[i];
								Object object = reflectionAnnotation(field.getType().getComponentType().getDeclaredConstructor().newInstance(), fieldAnnotation);
								list[i] = object;
							}
							classEntity.getMethod(setMethod, classField).invoke(entity, new Object[] { list });
						}
					}

				} catch (Exception e) {
					// logger.debug("The field " + nameField + " does not exist in annotation " +
					// classAnnotation.getSimpleName());
				}
			}
		}

		return entity;
	}

	private static boolean startsWith(String className) {
		for (String annotationPackage : CLASS_PACKAGES)
			if (className.startsWith(annotationPackage))
				return true;
		return false;
	}

	/**
	 * Gets the list field.
	 *
	 * @param classComponentExcel the class component excel
	 * @return the list field
	 */
	public static Set<Field> getListField(Class<?> classComponentExcel) {
		Set<Field> listField = new HashSet<>();
		Class<?> classApp = classComponentExcel;
		do {
			listField.addAll(Arrays.asList(classApp.getDeclaredFields()));
			classApp = classApp.getSuperclass();
		} while (classApp.getSuperclass() != null && !classApp.getName().equals(Object.class.getName()));
		return listField;
	}

	/**
	 * Gets the map field.
	 *
	 * @param classComponentExcel the class component excel
	 * @return the map field
	 */
	public static Map<String, Field> getMapField(Class<?> classComponentExcel) {
		Set<Field> listField = getListField(classComponentExcel);
		Map<String, Field> mapField = new HashMap<>();
		for (Field field : listField)
			mapField.put(field.getName(), field);
		return mapField;
	}


	
	
	/**
	 * Write to file.
	 *
	 * @param pathFile the path file
	 * @param fileName the file name
	 * @param typeFile the type file
	 * @param data     the dati
	 */
	public static void writeToFile(String pathFile, String fileName, String typeFile, byte[] data) {
		FileOutputStream fos=null;
		
		try {
			typeFile = startString(typeFile,".");
			fileName=startString(fileName, "/");
			fos = new FileOutputStream(pathFile + fileName + typeFile);
			fos.write(data);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

	/**
	 * Start string.
	 *
	 * @param text the text
	 * @param start the start
	 * @return the string
	 */
	private static String startString(String text,String start) {
		if(!text.startsWith(start))
			text=start+text;
		return text;
	}
}
