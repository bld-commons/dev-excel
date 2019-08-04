/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package com.bld.generator.report.excel.annotation.impl;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;

import com.bld.generator.report.excel.annotation.ExcelColumn;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelColumnImpl.
 */
public class ExcelColumnImpl{

	/** The name column. */
	protected String nameColumn;

	/** The comment. */
	protected String comment;

	/** The index column. */
	protected double indexColumn;

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		ExcelColumn excelColumn = null;
		if (StringUtils.isNotBlank(nameColumn) )
			excelColumn = new ExcelColumn() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return ExcelColumn.class;
				}

				@Override
				public String nameColumn() {
					return nameColumn;
				}

				@Override
				public String comment() {
					return comment;
				}

				@Override
				public double indexColumn() {
					return indexColumn;
				}

			};

		return excelColumn;
	}

	/**
	 * Instantiates a new excel column impl.
	 *
	 * @param nameColumn the name column
	 * @param comment the comment
	 * @param indexColumn the index column
	 * @throws Exception the exception
	 */
	public ExcelColumnImpl(String nameColumn, String comment, double indexColumn) throws Exception {
		super();
		this.nameColumn = nameColumn;
		this.comment = comment;
		this.indexColumn = indexColumn;
	}

	/**
	 * Gets the name column.
	 *
	 * @return the name column
	 */
	public String getNameColumn() {
		return nameColumn;
	}

	/**
	 * Sets the name column.
	 *
	 * @param nameColumn the new name column
	 */
	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Gets the index column.
	 *
	 * @return the index column
	 */
	public double getIndexColumn() {
		return indexColumn;
	}

	/**
	 * Sets the index column.
	 *
	 * @param indexColumn the new index column
	 */
	public void setIndexColumn(double indexColumn) {
		this.indexColumn = indexColumn;
	}

	
	
	

}
