package com.bld.common.spreadsheet.utils;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.exception.SpreadsheetException;

/**
 * Shared utility component providing reflection helpers used by both the generator and reader modules.
 * <p>
 * Key responsibilities:
 * </p>
 * <ul>
 *   <li>Retrieving and validating annotations on classes and fields</li>
 *   <li>Mapping annotation attribute values to Java object properties via reflection</li>
 *   <li>Collecting all declared fields from a class hierarchy</li>
 *   <li>Writing byte arrays to the file system</li>
 * </ul>
 * <p>
 * All methods are {@code static}; the class is registered as a Spring {@code @Component}
 * only to allow injection where an instance reference is required.
 * </p>
 *
 * @author Francesco Baldi
 */
@SuppressWarnings("unchecked")
@Component
public class SpreadsheetUtils {

	/** The Constant SHEET_NAME_SIZE. */
	public final static int SHEET_NAME_SIZE = 31;

	private static final List<String> CLASS_PACKAGES = Arrays.asList("com.bld.generator", "com.bld.common");

	private static final Map<Class<?>, Set<Field>> FIELD_CACHE = new ConcurrentHashMap<>();

    private static final Logger logger=LoggerFactory.getLogger(SpreadsheetUtils.class);
	/**
	 * Returns the requested annotation from the given class, throwing a {@link SpreadsheetException}
	 * if the annotation is not present.
	 *
	 * @param <T>             the annotation type
	 * @param classExcel      the class to inspect
	 * @param classAnnotation the annotation type to look for
	 * @return the annotation instance
	 * @throws SpreadsheetException if the annotation is not present on the class
	 */
	public static <T extends Annotation> T getAnnotation(Class<?> classExcel, Class<T> classAnnotation) {
		if (!classExcel.isAnnotationPresent(classAnnotation))
			throw new SpreadsheetException("Annotation " + classAnnotation.getSimpleName() + " is not presented on " + classExcel.getSimpleName());
		else
			return classExcel.getAnnotation(classAnnotation);
	}

	/**
	 * Returns the requested annotation from the given field, throwing a {@link SpreadsheetException}
	 * if the annotation is not present.
	 *
	 * @param <T>             the annotation type
	 * @param field           the field to inspect
	 * @param classAnnotation the annotation type to look for
	 * @return the annotation instance
	 * @throws SpreadsheetException if the annotation is not present on the field
	 */
	public static <T extends Annotation> T getAnnotation(Field field, Class<T> classAnnotation) {
		if (!field.isAnnotationPresent(classAnnotation))
			throw new SpreadsheetException("Annotation " + classAnnotation.getSimpleName() + " is not presented on " + field.getName());
		else
			return field.getAnnotation(classAnnotation);
	}

	/**
	 * Copies annotation attribute values onto the matching fields of the given entity using reflection.
	 * <p>
	 * For each method declared on the annotation whose name matches a field in the entity,
	 * the method return value is set on that field via its setter. Nested annotations belonging
	 * to the {@code com.bld} package hierarchy are handled recursively.
	 * </p>
	 *
	 * @param <T>        the entity type
	 * @param <K>        the annotation type
	 * @param entity     the target entity to populate
	 * @param annotation the source annotation carrying the values
	 * @return the populated entity
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
	 * Collects all declared fields from the given class and its superclass hierarchy,
	 * stopping before {@link Object}.
	 *
	 * @param classComponentExcel the class to inspect
	 * @return a {@link java.util.Set} containing all declared fields in the hierarchy
	 */
	public static Set<Field> getListField(Class<?> classComponentExcel) {
		return FIELD_CACHE.computeIfAbsent(classComponentExcel, cls -> {
			Set<Field> listField = new HashSet<>();
			Class<?> classApp = cls;
			do {
				listField.addAll(Arrays.asList(classApp.getDeclaredFields()));
				classApp = classApp.getSuperclass();
			} while (classApp.getSuperclass() != null && !classApp.getName().equals(Object.class.getName()));
			return Collections.unmodifiableSet(listField);
		});
	}

	/**
	 * Collects all declared fields from the given class hierarchy and returns them
	 * as a map keyed by field name.
	 *
	 * @param classComponentExcel the class to inspect
	 * @return a {@link java.util.Map} from field name to {@link java.lang.reflect.Field}
	 */
	public static Map<String, Field> getMapField(Class<?> classComponentExcel) {
		Set<Field> listField = getListField(classComponentExcel);
		Map<String, Field> mapField = new HashMap<>();
		for (Field field : listField)
			mapField.put(field.getName(), field);
		return mapField;
	}


	
	
	/**
	 * Writes a byte array to the file system at the given path.
	 * <p>
	 * The method ensures that {@code fileName} starts with {@code /} and {@code typeFile}
	 * starts with {@code .} before concatenating them with {@code pathFile}.
	 * Errors are logged but not propagated; callers will not receive any indication of failure.
	 * </p>
	 *
	 * @param pathFile the directory path (e.g. {@code "/tmp/"})
	 * @param fileName the file name without extension (e.g. {@code "report"})
	 * @param typeFile the file extension (e.g. {@code "xlsx"} or {@code ".xlsx"})
	 * @param data     the byte array to write
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
			logger.error(ExceptionUtils.getStackTrace(e));
		}

	}
	
	

	/**
	 * Ensures that the given text starts with the specified prefix, prepending it if absent.
	 *
	 * @param text  the string to check
	 * @param start the required prefix
	 * @return the string guaranteed to start with {@code start}
	 */
	private static String startString(String text,String start) {
		if(!text.startsWith(start))
			text=start+text;
		return text;
	}
}
