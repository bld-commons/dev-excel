/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package it.bld.generator.report.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import it.bld.generator.report.excel.annotation.ExcelFunctionRow;
import it.bld.generator.report.excel.annotation.ExcelFunctionRows;
import it.bld.generator.report.excel.annotation.impl.ExcelFunctionMergeRowImpl;
import it.bld.generator.report.excel.annotation.impl.ExcelFunctionRowImpl;
import it.bld.generator.report.excel.annotation.impl.ExcelFunctionRowsImpl;
import it.bld.generator.report.utils.ExcelUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class DynamicRowSheet.
 */
public abstract class DynamicRowSheet implements RowSheet {

	
	/** The map value. */
	protected Map<String, Object> mapValue;

	/**
	 * Instantiates a new dynamic row sheet.
	 */
	public DynamicRowSheet() {
		super();
		this.mapValue = new HashMap<>();
	}

	/**
	 * Gets the map value.
	 *
	 * @return the map value
	 */
	public Map<String, Object> getMapValue() {
		return mapValue;
	}

	/**
	 * Sets the map value.
	 *
	 * @param mapValue the map value
	 */
	public void setMapValue(Map<String, Object> mapValue) {
		this.mapValue = mapValue;
	}

	/**
	 * Adds the dynamic excel function.
	 *
	 * @param excelFunctionImpl the excel function impl
	 * @param excelFunctionMergeImpl the excel function merge impl
	 * @throws Exception the exception
	 */
	public void addDynamicExcelFunction(ExcelFunctionRowImpl excelFunctionImpl,ExcelFunctionMergeRowImpl excelFunctionMergeImpl) throws Exception {
		if (this.getClass().isAnnotationPresent(ExcelFunctionRows.class)) {
			ExcelFunctionRows excelFunctionRow = this.getClass().getAnnotation(ExcelFunctionRows.class);
			List<ExcelFunctionRow> listExcelFunctions = new ArrayList<>();
			listExcelFunctions.addAll(Arrays.asList(excelFunctionRow.excelFunctions()));
			if (excelFunctionImpl != null)
				listExcelFunctions.add(excelFunctionImpl.getExcelFunction());
			List<ExcelFunctionMergeRow> listExcelFunctionMerges = new ArrayList<>();
			listExcelFunctionMerges.addAll(Arrays.asList(excelFunctionRow.excelFunctionMerges()));
			if(excelFunctionMergeImpl!=null)
				listExcelFunctionMerges.add(excelFunctionMergeImpl.getExcelFunctionMerge());
			ExcelFunctionRowsImpl excelFunctionRowImpl = new ExcelFunctionRowsImpl(listExcelFunctions.toArray(new ExcelFunctionRow[listExcelFunctions.size()]), listExcelFunctionMerges.toArray(new ExcelFunctionMergeRow[listExcelFunctionMerges.size()]));
			addAnnotation(ExcelFunctionRows.class,excelFunctionRowImpl.getExcelFunctionRow());
		}

	}
	
	/**
	 * Adds the annotation.
	 *
	 * @param classAnnotation the class annotation
	 * @param annotation the annotation
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	private  void addAnnotation(Class<? extends Annotation>classAnnotation,Annotation annotation) throws Exception {
		Field annotationFieldData = Class.class.getDeclaredField(ExcelUtils.ANNOTATION_DATA);
		annotationFieldData.setAccessible(true);
		Object annotationData = annotationFieldData.get(this.getClass());
		Field annotationsField = annotationData.getClass().getDeclaredField(ExcelUtils.ANNOTATIONS);
		annotationsField.setAccessible(true);
		Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) annotationsField.get(annotationData);
		annotations.put(classAnnotation, annotation);
		annotationFieldData.setAccessible(false);
		annotationsField.setAccessible(false);
	}


}
