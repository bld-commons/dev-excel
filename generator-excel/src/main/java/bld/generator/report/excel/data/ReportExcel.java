/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.ReportExcel.java
*/

package bld.generator.report.excel.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelSelectCell;
import bld.generator.report.excel.constant.ColumnDateFormat;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportExcel. <br>
 * ReportExcel is the class that represents the excel object. <br>
 * Fields "title" and "date" can be shown on a cover sheet. The list base sheet
 * represents a list of sheets. <br>
 * 
 */
public class ReportExcel {

	/** The title. */
	@ExcelSelectCell(cellReference = "${bld.commons.report.excel.title}")
	private String title;

	/** The date. */
	@ExcelSelectCell(cellReference = "${bld.commons.report.excel.date}")
	@ExcelDate(format = ColumnDateFormat.PARAMETER)
	private Date date;

	/** The list base sheet. */
	private List<BaseSheet> listBaseSheet;

	/** The ignore cover. */
	private boolean ignoreCover;

	/** The enable sheet mapping. */
	private boolean enableSheetMapping;

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title the title
	 */
	public ReportExcel(String title) {
		super();
		init(title, false, false);
	}

	/**
	 * Inits the.
	 *
	 * @param title       the title
	 * @param ignoreCover the ignore cover
	 * @param enableSheetMapping the enable sheet mapping
	 */
	private void init(String title, boolean ignoreCover, boolean enableSheetMapping) {
		this.title = title;
		this.date = new Date();
		this.ignoreCover = ignoreCover;
		this.listBaseSheet = new ArrayList<>();
		this.enableSheetMapping = enableSheetMapping;
	}

	/**
	 * Instantiates a new report excel.
	 *
	 * @param title       the title
	 * @param ignoreCover the ignore cover
	 */
	public ReportExcel(String title, boolean ignoreCover) {
		super();
		init(title, ignoreCover, false);
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
		init(title, ignoreCover, enableSheetMapping);
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
		this.listBaseSheet = listBaseSheet;
		this.date = new Date();
	}

	/**
	 * Instantiates a new report excel.
	 */
	public ReportExcel() {
		super();
		this.date = new Date();
		this.listBaseSheet = new ArrayList<>();
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
	 * Gets the list base sheet.
	 *
	 * @return the list base sheet
	 */
	public List<BaseSheet> getListBaseSheet() {
		return listBaseSheet;
	}

	/**
	 * Adds the base sheet.
	 *
	 * @param baseSheets the base sheets
	 */
	public void addBaseSheet(BaseSheet... baseSheets) {
		if (ArrayUtils.isNotEmpty(baseSheets))
			for (BaseSheet baseSheet : baseSheets)
				this.listBaseSheet.add(baseSheet);
	}

	/**
	 * Sets the list base sheet.
	 *
	 * @param listBaseSheet the new list base sheet
	 */
	public void setListBaseSheet(List<BaseSheet> listBaseSheet) {
		this.listBaseSheet = listBaseSheet;
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
		result = prime * result + ((listBaseSheet == null) ? 0 : listBaseSheet.hashCode());
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
		if (listBaseSheet == null) {
			if (other.listBaseSheet != null)
				return false;
		} else if (!listBaseSheet.equals(other.listBaseSheet))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
