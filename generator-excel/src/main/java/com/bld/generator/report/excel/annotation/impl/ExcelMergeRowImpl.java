/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.annotation.impl.ExcelMergeRowImpl.java
*/
package com.bld.generator.report.excel.annotation.impl;

import com.bld.generator.report.excel.annotation.ExcelMergeRow;

/**
 * Programmatic (builder-style) implementation of {@link ExcelMergeRow}.
 *
 * <p>Use this class when the merge configuration cannot be expressed with a static
 * annotation — for example when building sheets dynamically at runtime.</p>
 *
 * <p>Usage examples:</p>
 * <pre>
 * // Driver column (merges on its own value)
 * sheetHeader.setExcelMergeRow(new ExcelMergeRowImpl());
 *
 * // Dependent column (merges when "idAutore" changes)
 * sheetHeader.setExcelMergeRow(new ExcelMergeRowImpl("idAutore"));
 *
 * // Equivalent fluent form via lambda
 * sheetHeader.setExcelMergeRow(m -> m.setValue("idAutore"));
 * </pre>
 */
public class ExcelMergeRowImpl extends ExcelAnnotationImpl<ExcelMergeRow> {

	/**
	 * Name of the driver field. Empty string means this column is its own driver.
	 *
	 * @see ExcelMergeRow#value()
	 */
	private String value = "";

	/**
	 * Creates a driver-column merge row (this column drives its own merge on its own value).
	 */
	public ExcelMergeRowImpl() {
		super();
	}

	/**
	 * Creates a dependent-column merge row that follows the given driver field.
	 *
	 * @param value name of the driver field in the same {@code RowSheet} class;
	 *              pass {@code ""} or {@code null} to make this column its own driver
	 */
	public ExcelMergeRowImpl(String value) {
		super();
		this.setValue(value);
	}

	/**
	 * Returns the driver field name.
	 *
	 * @return {@code ""} if this column is its own driver, otherwise the name of
	 *         the field that controls when merging restarts
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the driver field name.
	 *
	 * @param value name of the driver field, or {@code ""} / {@code null} for self-driven merging
	 */
	public void setValue(String value) {
		if (value != null)
			this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelMergeRowImpl other = (ExcelMergeRowImpl) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
