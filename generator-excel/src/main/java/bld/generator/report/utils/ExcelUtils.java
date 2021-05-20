/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.utils.ExcelUtils.java
*/
package bld.generator.report.utils;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.text.WordUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * The Class ExcelUtils.
 * 
 */
@SuppressWarnings("unchecked")
@Component
public class ExcelUtils implements ApplicationContextAware {

	/** The Constant BLD_GENERATOR. */
	private static final String BLD_GENERATOR = "bld.generator";

	/** The Constant ANNOTATIONS. */
//	private final static Log logger = LogFactory.getLog(ExcelUtils.class);

	/** The Constant ANNOTATIONS. */
	public static final String ANNOTATIONS = "annotations";

	/** The Constant ANNOTATION_DATA. */
	public static final String ANNOTATION_DATA = "annotationData";

	/** The application context. */
	private static ApplicationContext applicationContext;

	/** The Constant AUTO_SIZE_HEIGHT. */
	public final static short AUTO_SIZE_HEIGHT = -1;

	/**
	 * Gets the annotation.
	 *
	 * @param <T>             the generic type
	 * @param classExcel      the class excel
	 * @param classAnnotation the class annotation
	 * @return the annotation
	 * @throws Exception the exception
	 */
	public static <T extends Annotation> T getAnnotation(Class<?> classExcel, Class<T> classAnnotation)
			throws Exception {
		if (!classExcel.isAnnotationPresent(classAnnotation))
			throw new Exception("Annotation " + classAnnotation.getSimpleName() + " is not presented on "
					+ classExcel.getSimpleName());
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
			throw new Exception(
					"Annotation " + classAnnotation.getSimpleName() + " is not presented on " + field.getName());
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
						if (Annotation.class.isAssignableFrom(value.getClass())
								&& field.getType().getName().startsWith(BLD_GENERATOR)) {
							fieldAnnotation = (Annotation) value;
							value = reflectionAnnotation(classField.getDeclaredConstructor().newInstance(),
									fieldAnnotation);
							classEntity.getMethod(setMethod, classField).invoke(entity, value);
						} else if (Annotation.class.isAssignableFrom(value.getClass()) && field.getType().isArray()
								&& field.getType().getComponentType().getName().startsWith(BLD_GENERATOR)) {
							fieldAnnotation = (Annotation) value;
							value = reflectionAnnotation(
									field.getType().getComponentType().getDeclaredConstructor().newInstance(),
									fieldAnnotation);
							Object[] array = (Object[]) Array.newInstance(field.getType().getComponentType(), 1);
							Array.set(array, 0, value);
							classEntity.getMethod(setMethod, classField).invoke(entity, new Object[] { array });

						} else if (value.getClass().isArray()
								&& Annotation.class.isAssignableFrom(((Object[]) value)[0].getClass())) {
							Object[] list = (Object[]) Array.newInstance(field.getType().getComponentType(),
									((Annotation[]) value).length);
							for (int i = 0; i < ((Annotation[]) value).length; i++) {
								fieldAnnotation = ((Annotation[]) value)[i];
								Object object = reflectionAnnotation(field.getType().getComponentType().getDeclaredConstructor().newInstance(),
										fieldAnnotation);
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

	/**
	 * Adds the annotation.
	 *
	 * @param classe          the classe
	 * @param classAnnotation the class annotation
	 * @param annotation      the annotation
	 * @throws Exception the exception
	 */
	public static void addAnnotation(Class<?> classe, Class<? extends Annotation> classAnnotation,
			Annotation annotation) throws Exception {
		Field annotationFieldData = Class.class.getDeclaredField(ANNOTATION_DATA);
		annotationFieldData.setAccessible(true);
		Object annotationData = annotationFieldData.get(classe);
		Field annotationsField = annotationData.getClass().getDeclaredField(ANNOTATIONS);
		annotationsField.setAccessible(true);
		Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) annotationsField
				.get(annotationData);
		annotations.put(classAnnotation, annotation);
	}

	/**
	 * Sets the application context.
	 *
	 * @param ac the new application context
	 */
	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		applicationContext = ac;
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
		parameter = WordUtils.capitalize(parameter.replace("_", " ")).replace(" ", "");
		return (parameter.charAt(0) + "").toLowerCase() + parameter.substring(1);
	}

	/**
	 * Write to file.
	 *
	 * @param pathFile the path file
	 * @param fileName the file name
	 * @param typeFile the type file
	 * @param dati     the dati
	 */
	public static void writeToFile(String pathFile, String fileName, String typeFile, byte[] dati) {
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
	public static String calcoloCoordinateFunction(int row, int column, boolean dollar) {
		int mod = 0;
		int div = column;
		String addDollar = "";
		if (dollar)
			addDollar = "$";
		String coordinata = "";
		do {
			mod = div % 26;
			mod += 65;
			div = (div / 26) - 1;
			coordinata = Character.toString((char) mod) + coordinata;
		} while (div >= 0);
		coordinata = addDollar + coordinata + addDollar + row;

		return coordinata;
	}

	/**
	 * Gets the key column.
	 *
	 * @param sheet the sheet
	 * @param key   the key
	 * @return the key column
	 */
	public static String getKeyColumn(Sheet sheet, String key) {
		if (!key.contains("."))
			key = sheet.getSheetName() + "." + key;
		return key;
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

	public static Map<String, Field> getMapField(Class<?> classComponentExcel) {
		Set<Field> listField = getListField(classComponentExcel);
		Map<String, Field> mapField = new HashMap<>();
		for (Field field : listField)
			mapField.put(field.getName(), field);
		return mapField;
	}

	/**
	 * Width column.
	 *
	 * @param widthColumn the width column
	 * @return the short
	 */
	public static short widthColumn(int widthColumn) {
		return (short) (widthColumn * 1036);
	}

	/**
	 * Hight row.
	 *
	 * @param rowHeight the hight row
	 * @return the short
	 */
	public static short rowHeight(int rowHeight) {
		if (rowHeight != AUTO_SIZE_HEIGHT)
			rowHeight = rowHeight * 568;
		return (short) rowHeight;
	}

	/**
	 * Gets the t class.
	 *
	 * @return the t class
	 */
	public static <T> Class<T> getTClass(Object entity) {
		return getTClass(entity, 0);
	}

	/**
	 * Gets the t class.
	 *
	 * @return the t class
	 */
	public static <T> Class<T> getTClass(Object entity, int i) {
		ParameterizedType parameterizedType = null;
		try {
			parameterizedType = (ParameterizedType) entity.getClass().getGenericSuperclass();
		} catch (Exception e) {
			parameterizedType = (ParameterizedType) entity.getClass().getSuperclass().getGenericSuperclass();
		}
		Class<T> classT = (Class<T>) parameterizedType.getActualTypeArguments()[i];
		return classT;
	}
	
	
	public static Double evaluate(String exprenssionIndex,String param,Number value) {
		Double evaluate=null;
		try {
			DoubleEvaluator eval = new DoubleEvaluator();
			StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
			variables.set(param, value.doubleValue());
			evaluate=eval.evaluate(exprenssionIndex, variables);
		} catch (Exception e) {
			//logger.error(ExceptionUtils.getStackTrace(e));
		}
		return evaluate;
	}

}
