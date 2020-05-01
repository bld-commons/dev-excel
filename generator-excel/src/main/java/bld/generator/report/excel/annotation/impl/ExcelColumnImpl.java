/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.impl.ExcelColumnImpl.java
*/
package bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;

import bld.generator.report.excel.annotation.ExcelColumn;
import lombok.Data;

/**
 * The Class ExcelColumnImpl.
 */
@Data
public class ExcelColumnImpl implements Cloneable{

	/** The column name. */
	protected String columnName;

	/** The comment. */
	protected String comment;

	/** The index column. */
	protected double indexColumn;
	
	/** The ignore. */
	protected boolean ignore;
	
	
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
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		ExcelColumn excelColumn = null;
		if (StringUtils.isNotBlank(columnName) )
			excelColumn = new ExcelColumn() {

				@Override
				public Class<? extends Annotation> annotationType() {
					return ExcelColumn.class;
				}

				@Override
				public String columnName() {
					return columnName;
				}

				@Override
				public String comment() {
					return comment;
				}

				@Override
				public double indexColumn() {
					return indexColumn;
				}

				@Override
				public boolean ignore() {
					
					return ignore;
				}

			

			};

		return excelColumn;
	}

	
	

	/**
	 * Instantiates a new excel column impl.
	 *
	 * @param columnName  the name column
	 * @param comment     the comment
	 * @param indexColumn the index column
	 * @param ignore      the ignore
	 */
	public ExcelColumnImpl(String columnName, String comment, double indexColumn, boolean ignore) {
		super();
		this.columnName = columnName;
		this.comment = comment;
		this.indexColumn = indexColumn;
		this.ignore = ignore;
	}

	

	

}
