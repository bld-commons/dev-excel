/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.data.ReportExcel.java
*/

package com.bld.generator.report.excel.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.bld.common.spreadsheet.constant.ColumnDateFormat;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.annotation.ExcelSelectCell;


/**
 * The Class ReportExcel. <br>
 * ReportExcel is the class that represents the excel object. <br>
 * Fields "title" and "date" can be shown on a cover sheet. The list base sheet
 * represents a list of sheets. <br>
 * 
 */
public class ReportExcel {

	/** The title. */
	@ExcelSelectCell(cellReference = "${com.bld.commons.report.excel.title}")
	private String title;

	/** The date. */
	@ExcelSelectCell(cellReference ="${com.bld.commons.report.excel.date}")
	@ExcelDate(value = ColumnDateFormat.PARAMETER)
	private Date date;

	/** The sheets. */
	private List<BaseSheet> sheets;

	/** The ignore cover. */
	private boolean ignoreCover;

	/** The enable sheet mapping. */
	private boolean enableSheetMapping;

	/**
	 * When {@code true}, {@link org.apache.poi.ss.usermodel.FormulaEvaluator#evaluateAll()}
	 * is called after all sheets are written so that cached formula values are embedded in
	 * the file and Excel opens without recalculating. When {@code false} the workbook is
	 * saved without pre-computed values and Excel recalculates on open (current default
	 * behaviour before this flag existed).
	 */
	private boolean evaluateFormulas;

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title the title
	 */
	public ReportExcel(String title) {
		super();
		init(title, false, false,true);
	}

	/**
	 * Inits the.
	 *
	 * @param title       the title
	 * @param ignoreCover the ignore cover
	 * @param enableSheetMapping the enable sheet mapping
	 */
	private void init(String title, boolean ignoreCover, boolean enableSheetMapping,boolean evaluateFormulas) {
		this.title = title;
		this.date = new Date();
		this.ignoreCover = ignoreCover;
		this.sheets = new ArrayList<>();
		this.enableSheetMapping = enableSheetMapping;
		this.evaluateFormulas=evaluateFormulas;
	}

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title       the title
	 * @param ignoreCover the ignore cover
	 */
	public ReportExcel(String title, boolean ignoreCover) {
		super();
		init(title, ignoreCover, false,true);
	}

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title the title
	 * @param ignoreCover the ignore cover
	 * @param enableSheetMapping the enable sheet mapping
	 */
	public ReportExcel(String title, boolean ignoreCover, boolean enableSheetMapping) {
		super();
		init(title, ignoreCover, enableSheetMapping,true);
	}

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title         the title
	 * @param listBaseSheet the list base sheet
	 */
	public ReportExcel(String title, List<BaseSheet> listBaseSheet) {
		super();
		this.title = title;
		this.sheets = listBaseSheet;
		this.date = new Date();
	}

	/**
	 * Instantiates a new report excel.
	 */
	public ReportExcel() {
		super();
		this.date = new Date();
		this.sheets = new ArrayList<>();
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * Gets the sheets.
	 *
	 * @return the sheets
	 */
	public List<BaseSheet> getSheets() {
		return sheets;
	}


	/**
	 * Adds the sheets.
	 *
	 * @param baseSheets the base sheets
	 */
	public void addSheets(BaseSheet... baseSheets) {
		if (ArrayUtils.isNotEmpty(baseSheets))
			for (BaseSheet baseSheet : baseSheets)
				this.sheets.add(baseSheet);
	}


	/**
	 * Sets the sheets.
	 *
	 * @param sheets the new sheets
	 */
	public void setSheets(List<BaseSheet> sheets) {
		this.sheets = sheets;
	}

	/**
	 * Checks if is enable sheet mapping.
	 *
	 * @return true, if is enable sheet mapping
	 */
	public boolean isEnableSheetMapping() {
		return enableSheetMapping;
	}

	/**
	 * Sets the enable sheet mapping.
	 *
	 * @param enableSheetMapping the new enable sheet mapping
	 */
	public void setEnableSheetMapping(boolean enableSheetMapping) {
		this.enableSheetMapping = enableSheetMapping;
	}

	/**
	 * Checks if is ignore cover.
	 *
	 * @return true, if is ignore cover
	 */
	public boolean isIgnoreCover() {
		return ignoreCover;
	}

	/**
	 * Sets the ignore cover.
	 *
	 * @param ignoreCover the new ignore cover
	 */
	public void setIgnoreCover(boolean ignoreCover) {
		this.ignoreCover = ignoreCover;
	}

	/**
	 * Returns whether formula evaluation is performed before saving the workbook.
	 *
	 * @return {@code true} if {@link org.apache.poi.ss.usermodel.FormulaEvaluator#evaluateAll()}
	 *         will be called after all sheets are written
	 */
	public boolean isEvaluateFormulas() {
		return evaluateFormulas;
	}

	/**
	 * Sets whether formula evaluation is performed before saving the workbook.
	 *
	 * @param evaluateFormulas {@code true} to pre-compute formula values (Excel opens faster,
	 *                         no recalculation prompt); {@code false} to let Excel recalculate on open
	 */
	public void setEvaluateFormulas(boolean evaluateFormulas) {
		this.evaluateFormulas = evaluateFormulas;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (enableSheetMapping ? 1231 : 1237);
		result = prime * result + (ignoreCover ? 1231 : 1237);
		result = prime * result + ((sheets == null) ? 0 : sheets.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		ReportExcel other = (ReportExcel) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (enableSheetMapping != other.enableSheetMapping)
			return false;
		if (ignoreCover != other.ignoreCover)
			return false;
		if (sheets == null) {
			if (other.sheets != null)
				return false;
		} else if (!sheets.equals(other.sheets))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
