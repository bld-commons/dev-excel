/**
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
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMergeRow;
import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.utils.ExcelUtils;
import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class SheetHeader.
 */
public class SheetHeader implements Cloneable {
	
	@Getter
	@Setter
	private String key;

	/** The field. */
	@Getter
	@Setter
	private Field field;

	/** The value. */
	@Getter
	@Setter
	private Object value;

	/** The num column. */
	@Getter
	@Setter
	private int numColumn;

	/** The excel date. */
	@Setter
	private ExcelDate excelDate;

	/** The excel column. */
	@Setter
	private ExcelColumn excelColumn;

	/** The excel cell layout. */
	@Setter
	private ExcelCellLayout excelCellLayout;

	/** The excel merge row. */
	@Getter
	@Setter
	private ExcelMergeRow excelMergeRow;

	/** The excel header layout. */
	@Getter
	@Setter
	private ExcelHeaderLayout excelHeaderLayout;

	/** The excel function. */
	@Getter
	@Setter
	private ExcelFunction excelFunction;

	/** The excel column width. */
	@Setter
	private ExcelColumnWidth excelColumnWidth;

	/** The key map. */
	@Getter
	@Setter
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


}
