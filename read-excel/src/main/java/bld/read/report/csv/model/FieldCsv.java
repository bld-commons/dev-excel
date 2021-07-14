/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.csv.model;

import java.lang.reflect.Field;

/**
 * The Class FieldCsv.
 */
public class FieldCsv {

	/** The field. */
	private Field field;
	
	/** The order. */
	private double order;
	
	/** The name. */
	private String name;
	
	

	/**
	 * Instantiates a new field csv.
	 *
	 * @param field the field
	 * @param order the order
	 * @param name  the name
	 */
	public FieldCsv(Field field, double order, String name) {
		super();
		this.field = field;
		this.order = order;
		this.name = name;
	}

	
	/**
	 * Instantiates a new field csv.
	 */
	public FieldCsv() {
		super();
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
	 * Sets the field.
	 *
	 * @param field the new field
	 */
	public void setField(Field field) {
		this.field = field;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public double getOrder() {
		return order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(double order) {
		this.order = order;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
