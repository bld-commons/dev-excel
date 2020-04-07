/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import bld.generator.report.excel.annotation.ExcelMergeRow;
import lombok.Data;

/**
 * The Class ExcelMergeRowImpl.
 */
@Data
public class ExcelMergeRowImpl implements Cloneable{

	
	/** The reference field. */
	protected String referenceField;
	
	/**
	 * Clone.
	 *
	 * @return the object
	 * @throws CloneNotSupportedException the clone not supported exception
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

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

	
}
