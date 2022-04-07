package bld.read.report.excel.json.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import bld.read.report.excel.json.deserialier.ExcelReadDeserialaizer;



@Retention(RUNTIME)
@Target({METHOD,FIELD,PARAMETER})
@JacksonAnnotationsInside
@JsonDeserialize(using = ExcelReadDeserialaizer.class)
public @interface JsonExcel {

	public JsonSheet[] value();
		
}
