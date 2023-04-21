package bld.generator.report.csv;

import java.lang.reflect.Field;

import bld.common.spreadsheet.csv.annotation.CsvDate;
import bld.generator.report.csv.annotation.CsvColumn;

public class CsvHeader {

	private Field field;
	
	private String name;

	private Double index;
	
	private CsvDate dateFormat;

	public CsvHeader(Field field) {
		super();
		this.field=field;
		CsvColumn column = field.getAnnotation(CsvColumn.class);
		this.name = column.name();
		this.index = column.index();
		if(field.isAnnotationPresent(CsvDate.class))
			this.dateFormat=field.getAnnotation(CsvDate.class);
	}
	
	public Field getField() {
		return field;
	}

	public String getName() {
		return name;
	}

	public Double getIndex() {
		return index;
	}

	public CsvDate getDateFormat() {
		return dateFormat;
	}
	
	
}
