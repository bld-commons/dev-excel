package bld.generator.report.csv;

import java.lang.reflect.Field;

import bld.common.spreadsheet.csv.annotation.CsvDate;
import bld.generator.report.csv.annotation.CsvColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class CsvHeader.
 */
public class CsvHeader {

	/** The field. */
	private Field field;
	
	/** The name. */
	private String name;

	/** The index. */
	private Double index;
	
	/** The date format. */
	private CsvDate dateFormat;

	/**
	 * Instantiates a new csv header.
	 *
	 * @param field the field
	 */
	public CsvHeader(Field field) {
		super();
		this.field=field;
		CsvColumn column = field.getAnnotation(CsvColumn.class);
		this.name = column.name();
		this.index = column.index();
		if(field.isAnnotationPresent(CsvDate.class))
			this.dateFormat=field.getAnnotation(CsvDate.class);
	}
	
	/**
	 * Gets the field.
	 *
	 * @return the field
	 */
	public Field getField() {
		return field;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the index.
	 *
	 * @return the index
	 */
	public Double getIndex() {
		return index;
	}

	/**
	 * Gets the date format.
	 *
	 * @return the date format
	 */
	public CsvDate getDateFormat() {
		return dateFormat;
	}
	
	
}
