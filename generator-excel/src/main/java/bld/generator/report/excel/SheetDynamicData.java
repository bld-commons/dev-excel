/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */

package bld.generator.report.excel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Size;

import bld.generator.report.excel.annotation.ExcelChart;
import bld.generator.report.excel.annotation.impl.ExcelChartImpl;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.utils.ExcelUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelDati.
 *
 * @param <T> the generic type
 */
public abstract class SheetDynamicData<T extends DynamicRowSheet> extends SheetData<T>{
		
	/** The map extra column annotation. */
	private Map<String,ExtraColumnAnnotation> mapExtraColumnAnnotation;
	
	
	/**
	 * Instantiates a new sheet dynamic data.
	 *
	 * @param nameSheet the name sheet
	 */
	public SheetDynamicData(@Size(max = 30) String nameSheet) {
		super(nameSheet);
		this.mapExtraColumnAnnotation=new HashMap<>();
	}


	/**
	 * Gets the map extra column annotation.
	 *
	 * @return the map extra column annotation
	 */
	public Map<String, ExtraColumnAnnotation> getMapExtraColumnAnnotation() {
		return mapExtraColumnAnnotation;
	}


	/**
	 * Sets the map extra column annotation.
	 *
	 * @param mapExtraColumnAnnotation the map extra column annotation
	 */
	public void setMapExtraColumnAnnotation(Map<String, ExtraColumnAnnotation> mapExtraColumnAnnotation) {
		this.mapExtraColumnAnnotation = mapExtraColumnAnnotation;
	}


	
	/**
	 * Adds the excel chart.
	 *
	 * @param excelChartImpl the excel chart impl
	 * @throws Exception the exception
	 */
	public void addExcelChart(ExcelChartImpl excelChartImpl) throws Exception {
		if (excelChartImpl!=null) {
			addAnnotation(ExcelChart.class,excelChartImpl.getExcelChart());
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
		 Method method = Class.class.getDeclaredMethod(ExcelUtils.ANNOTATION_DATA);
         method.setAccessible(true);
         //Since AnnotationData is a private class we cannot create a direct reference to it. We will have to
         //manage with just Object
         Object annotationData = method.invoke(this.getClass());
         //We now look for the map called "annotations" within AnnotationData object.
         Field annotationsField = annotationData.getClass().getDeclaredField(ExcelUtils.ANNOTATIONS);
         annotationsField.setAccessible(true);
		Map<Class<? extends Annotation>, Annotation> mapAnnotations = (Map<Class<? extends Annotation>, Annotation>) annotationsField.get(annotationData);
		mapAnnotations.put(classAnnotation, annotation);
		method.setAccessible(false);
		annotationsField.setAccessible(false);
	}
}
