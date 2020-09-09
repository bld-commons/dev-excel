/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.data.SheetHeader.java
 */
package bld.generator.report.excel.data;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelColumnWidth;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelDropDownList;
import bld.generator.report.excel.annotation.ExcelDropDownReferenceList;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.utils.ExcelUtils;

/**
 * The Class SheetHeader.
 */
public class SheetHeader implements Cloneable {

	/** The key. */
	private String key;

	/** The field. */
	private Field field;

	/** The value. */
	private Object value;

	/** The num column. */
	private int numColumn;

	/** The excel date. */
	private ExcelDate excelDate;

	/** The excel column. */
	private ExcelColumn excelColumn;

	/** The excel cell layout. */
	private ExcelCellLayout excelCellLayout;

	/** The excel merge row. */
	private ExcelMergeRow excelMergeRow;

	/** The excel header layout. */
	private ExcelHeaderCellLayout excelHeaderCellLayout;

	/** The excel function. */
	private ExcelFunction excelFunction;

	/** The excel column width. */
	private ExcelColumnWidth excelColumnWidth;
	
	/** The excel drop down reference list. */
	private ExcelDropDownReferenceList excelDropDownReferenceList;
	
	/** The excel drop down list. */
	private ExcelDropDownList excelDropDownList;

	/** The key map. */
	private String keyMap;

	/**
	 * Instantiates a new sheet header.
	 */
	public SheetHeader() {
		super();

	}

	/**
	 * Instantiates a new sheet header.
	 *
	 * @param field the field
	 * @param value the value
	 * @throws Exception the exception
	 */
	public SheetHeader(Field field, Object value) throws Exception {
		super();
		this.field = field;
		this.value = value;
		if (field.isAnnotationPresent(ExcelMergeRow.class))
			this.setExcelMergeRow(field.getAnnotation(ExcelMergeRow.class));
		if (field.isAnnotationPresent(ExcelColumnWidth.class))
			this.setExcelColumnWidth(field.getAnnotation(ExcelColumnWidth.class));
		this.getExcelColumn();
	}

	/**
	 * Gets the excel column width.
	 *
	 * @return the excel column width
	 */
	public ExcelColumnWidth getExcelColumnWidth() {
		if (excelColumnWidth == null)
			this.excelColumnWidth = ExcelConstant.EXCEL_COLUMN_WIDTH.getExcelColumnWidth();
		return excelColumnWidth;
	}

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() throws Exception {
		if (this.excelColumn == null)
			this.excelColumn = ExcelUtils.getAnnotation(this.field, ExcelColumn.class);
		return this.excelColumn;
	}

	/**
	 * Gets the excel date.
	 *
	 * @return the excel date
	 */
	public ExcelDate getExcelDate() throws Exception {
		if (this.excelDate == null && this.field !=null && (Date.class.isAssignableFrom(this.field.getType()) || Calendar.class.isAssignableFrom(this.field.getType())))
			excelDate = ExcelUtils.getAnnotation(this.field, ExcelDate.class);
		return excelDate;
	}

	/**
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() throws Exception {
		if (this.excelCellLayout == null)
			this.excelCellLayout = ExcelUtils.getAnnotation(this.field, ExcelCellLayout.class);
		return this.excelCellLayout;
	}

	/**
	 * Excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn excelColumn() {
		return this.excelColumn;
	}

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
	 * Gets the layout cell.
	 *
	 * @return the layout cell
	 * @throws Exception the exception
	 */
	public LayoutCell getLayoutCell() throws Exception {
		LayoutCell layoutCell = null;
		if (this.getExcelCellLayout() != null)
			layoutCell = ExcelUtils.reflectionAnnotation(new LayoutCell(), this.getExcelCellLayout());
		try {
			if (this.getExcelDate() != null)
				layoutCell = ExcelUtils.reflectionAnnotation(layoutCell, this.getExcelDate());
		} catch (Exception e) {

		}
		return layoutCell;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets the num column.
	 *
	 * @return the num column
	 */
	public int getNumColumn() {
		return numColumn;
	}

	/**
	 * Sets the num column.
	 *
	 * @param numColumn the new num column
	 */
	public void setNumColumn(int numColumn) {
		this.numColumn = numColumn;
	}

	/**
	 * Gets the excel merge row.
	 *
	 * @return the excel merge row
	 */
	public ExcelMergeRow getExcelMergeRow() {
		return excelMergeRow;
	}

	/**
	 * Sets the excel merge row.
	 *
	 * @param excelMergeRow the new excel merge row
	 */
	public void setExcelMergeRow(ExcelMergeRow excelMergeRow) {
		this.excelMergeRow = excelMergeRow;
	}

	/**
	 * Gets the excel function.
	 *
	 * @return the excel function
	 */
	public ExcelFunction getExcelFunction() {
		return excelFunction;
	}

	/**
	 * Sets the excel function.
	 *
	 * @param excelFunction the new excel function
	 */
	public void setExcelFunction(ExcelFunction excelFunction) {
		this.excelFunction = excelFunction;
	}

	/**
	 * Gets the key map.
	 *
	 * @return the key map
	 */
	public String getKeyMap() {
		return keyMap;
	}

	/**
	 * Sets the key map.
	 *
	 * @param keyMap the new key map
	 */
	public void setKeyMap(String keyMap) {
		this.keyMap = keyMap;
	}

	/**
	 * Sets the excel date.
	 *
	 * @param excelDate the new excel date
	 */
	public void setExcelDate(ExcelDate excelDate) {
		this.excelDate = excelDate;
	}

	/**
	 * Sets the excel column.
	 *
	 * @param excelColumn the new excel column
	 */
	public void setExcelColumn(ExcelColumn excelColumn) {
		this.excelColumn = excelColumn;
	}

	/**
	 * Sets the excel cell layout.
	 *
	 * @param excelCellLayout the new excel cell layout
	 */
	public void setExcelCellLayout(ExcelCellLayout excelCellLayout) {
		this.excelCellLayout = excelCellLayout;
	}

	/**
	 * Sets the excel column width.
	 *
	 * @param excelColumnWidth the new excel column width
	 */
	public void setExcelColumnWidth(ExcelColumnWidth excelColumnWidth) {
		this.excelColumnWidth = excelColumnWidth;
	}

	/**
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderCellLayout getExcelHeaderCellLayout() {
		return excelHeaderCellLayout;
	}

	/**
	 * Sets the excel header layout.
	 *
	 * @param excelHeaderCellLayout the new excel header layout
	 */
	public void setExcelHeaderCellLayout(ExcelHeaderCellLayout excelHeaderCellLayout) {
		this.excelHeaderCellLayout = excelHeaderCellLayout;
	}

	/**
	 * Gets the excel drop down reference list.
	 *
	 * @return the excel drop down reference list
	 */
	public ExcelDropDownReferenceList getExcelDropDownReferenceList() {
		return excelDropDownReferenceList;
	}

	/**
	 * Sets the excel drop down reference list.
	 *
	 * @param excelDropDown the new excel drop down reference list
	 */
	public void setExcelDropDownReferenceList(ExcelDropDownReferenceList excelDropDown) {
		this.excelDropDownReferenceList = excelDropDown;
	}

	/**
	 * Gets the excel drop down list.
	 *
	 * @return the excel drop down list
	 */
	public ExcelDropDownList getExcelDropDownList() {
		return excelDropDownList;
	}

	/**
	 * Sets the excel drop down list.
	 *
	 * @param excelDropDownList the new excel drop down list
	 */
	public void setExcelDropDownList(ExcelDropDownList excelDropDownList) {
		this.excelDropDownList = excelDropDownList;
	}
	
	

}
