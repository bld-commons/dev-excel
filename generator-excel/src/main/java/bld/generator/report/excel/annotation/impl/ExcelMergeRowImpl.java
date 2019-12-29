/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelMergeRow;

/**
 * The Class ExcelMergeRowImpl.
 */
public class ExcelMergeRowImpl {

	
	/** The reference field. */
	protected String referenceField;

	/**
	 * Gets the excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow getExcelMergeRow() {
		return new ExcelMergeRow() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ExcelMergeRow.class;
			}

			@Override
			public String referenceField() {
				return referenceField;
			}};
	}

	/**
	 * Instantiates a new excel merge row impl.
	 *
	 * @param referenceField the reference field
	 */
	public ExcelMergeRowImpl(String referenceField) {
		super();
		this.referenceField = referenceField;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((referenceField == null) ? 0 : referenceField.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelMergeRowImpl other = (ExcelMergeRowImpl) obj;
		if (referenceField == null) {
			if (other.referenceField != null)
				return false;
		} else if (!referenceField.equals(other.referenceField))
			return false;
		return true;
	}

	/**
	 * Gets the reference field.
	 *
	 * @return the reference field
	 */
	public String getReferenceField() {
		return referenceField;
	}

	/**
	 * Sets the reference field.
	 *
	 * @param referenceField the new reference field
	 */
	public void setReferenceField(String referenceField) {
		this.referenceField = referenceField;
	}

	
	
	
	
}
