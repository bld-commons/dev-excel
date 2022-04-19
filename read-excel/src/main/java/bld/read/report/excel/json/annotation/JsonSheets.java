/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.json.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bld.read.report.excel.json.deserialier.SheetDeserialaizer;



/**
 * The Interface JsonExcel.
 */
@Retention(RUNTIME)
@Target({FIELD,PARAMETER,METHOD})
@JacksonAnnotationsInside
@JsonDeserialize(using = SheetDeserialaizer.class)
public @interface JsonSheets {

	/**
	 * Value.
	 *
	 * @return the json sheet[]
	 */
	public JsonSheet[] value();
	
		
}
