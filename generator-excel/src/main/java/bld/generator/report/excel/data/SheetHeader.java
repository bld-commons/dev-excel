/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.data;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.utils.ExcelUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SheetHeader.
 */
public class SheetHeader implements Cloneable {

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
	private ExcelHeaderLayout excelHeaderLayout; 
	
	/** The function. */
	private String function;
	
	/** The key map. */
	private String keyMap;
	
	/** The name function. */
	private String nameFunction;
	

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
		this.getExcelColumn();
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
		if (this.excelDate == null && (Date.class.isAssignableFrom(this.field.getType()) || Calendar.class.isAssignableFrom(this.field.getType())))
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
	 * Excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn excelColumn() {
		return this.excelColumn;
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
	 * Gets the function.
	 *
	 * @return the function
	 */
	public String getFunction() {
		return function;
	}


	/**
	 * Sets the function.
	 *
	 * @param function the new function
	 */
	public void setFunction(String function) {
		this.function = function;
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
	 * Gets the name function.
	 *
	 * @return the name function
	 */
	public String getNameFunction() {
		return nameFunction;
	}

	/**
	 * Sets the name function.
	 *
	 * @param nameFunction the new name function
	 */
	public void setNameFunction(String nameFunction) {
		this.nameFunction = nameFunction;
	}

	/**
	 * Gets the excel header layout.
	 *
	 * @return the excel header layout
	 */
	public ExcelHeaderLayout getExcelHeaderLayout() {
		return excelHeaderLayout;
	}

	/**
	 * Sets the excel header layout.
	 *
	 * @param excelHeaderLayout the new excel header layout
	 */
	public void setExcelHeaderLayout(ExcelHeaderLayout excelHeaderLayout) {
		this.excelHeaderLayout = excelHeaderLayout;
	}
	
	

}
