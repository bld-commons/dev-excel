/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.excel.annotation.impl.ExcelDateImpl.java
*/

package com.bld.generator.report.excel.annotation.impl;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;


/**
 * Programmatic (builder-style) implementation of {@link ExcelDate}.
 *
 * <p>Use this class when the date-format configuration cannot be expressed
 * with a static annotation — for example when building sheets dynamically at runtime.</p>
 *
 * <p>Usage example:</p>
 * <pre>
 * sheetHeader.setExcelDate(new ExcelDateImpl(ColumnDateFormat.DD_MM_YYYY));
 *
 * // With explicit time zone
 * ExcelDateImpl d = new ExcelDateImpl(ColumnDateFormat.DD_MM_YYYY);
 * d.setTimezone("Europe/Rome");
 * sheetHeader.setExcelDate(d);
 * </pre>
 */
public class ExcelDateImpl extends ExcelAnnotationImpl<ExcelDate>{

	/** Date format pattern applied to the cell. */
	private ColumnDateFormat value;

	/**
	 * Time-zone identifier used when converting zone-aware date types.
	 * Defaults to the Spring placeholder {@code ${spring.jackson.time-zone:}}
	 * so it inherits the application's Jackson time-zone setting when resolved.
	 *
	 * @see ExcelDate#timezone()
	 */
	private String timezone = "${spring.jackson.time-zone:}";


	/**
	 * Creates an {@code ExcelDateImpl} with the specified date format.
	 * The time zone defaults to {@code ${spring.jackson.time-zone:}}.
	 *
	 * @param value the date format pattern to apply to the cell
	 */
	public ExcelDateImpl(ColumnDateFormat value){
		super();
		this.value = value;
	}

	/**
	 * Creates an {@code ExcelDateImpl} with no preset format.
	 * Both {@code value} and {@code timezone} must be set before use.
	 */
	public ExcelDateImpl() {
		super();
	}


	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public ColumnDateFormat getValue() {
		return value;
	}


	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(ColumnDateFormat value) {
		this.value = value;
	}

	/**
	 * Returns the time-zone identifier used for zone-aware date conversion.
	 *
	 * @return a {@link java.time.ZoneId} string or a Spring placeholder;
	 *         never {@code null}
	 */
	public String getTimezone() {
		return timezone;
	}

	/**
	 * Sets the time-zone identifier.
	 *
	 * @param timezone a {@link java.time.ZoneId} string (e.g. {@code "Europe/Rome"}),
	 *                 a Spring placeholder, or {@code null} (ignored, keeps current value)
	 */
	public void setTimezone(String timezone) {
		if (timezone != null)
			this.timezone = timezone;
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
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ExcelDateImpl other = (ExcelDateImpl) obj;
		if (value != other.value)
			return false;
		return true;
	}

	
}
