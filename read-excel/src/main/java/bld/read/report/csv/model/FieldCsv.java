package bld.read.report.csv.model;

import java.lang.reflect.Field;

public class FieldCsv {

	private Field field;
	
	private double order;
	
	private String name;
	
	

	public FieldCsv(Field field, double order, String name) {
		super();
		this.field = field;
		this.order = order;
		this.name = name;
	}

	
	public FieldCsv() {
		super();
	}


	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public double getOrder() {
		return order;
	}

	public void setOrder(double order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
