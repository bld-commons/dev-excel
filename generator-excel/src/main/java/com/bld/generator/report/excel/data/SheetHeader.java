/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.data.SheetHeader.java
 */
package com.bld.generator.report.excel.data;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;

import com.bld.common.spreadsheet.excel.annotation.ExcelBooleanText;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelColumnWidth;
import com.bld.generator.report.excel.annotation.ExcelDataValidation;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelImage;
import com.bld.generator.report.excel.annotation.ExcelMergeRow;
import com.bld.generator.report.excel.annotation.ExcelNumberFormat;
import com.bld.generator.report.excel.annotation.ExcelSubtotal;
import com.bld.generator.report.excel.constant.ExcelConstant;
import com.bld.generator.report.excel.dropdown.CalendarDropDown;
import com.bld.generator.report.excel.dropdown.DateDropDown;
import com.bld.generator.report.excel.dropdown.DropDown;
import com.bld.generator.report.excel.dropdown.TimestampDropDown;

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

	/** The excel drop down. */
	private ExcelDropDown excelDropDown;

	/** The excel image. */
	private ExcelImage excelImage;

	/** The excel subtotal. */
	private ExcelSubtotal excelSubtotal;

	/** The excel boolean text. */
	private ExcelBooleanText excelBooleanText;

	private ExcelNumberFormat excelNumberFormat;
	
	private ExcelDataValidation excelDataValidation;

	/** The map layout cell. */
	private Map<Integer, LayoutCell> mapLayoutCell;

	/** The key map. */
	private String keyMap;

	/** The color size. */
	private int colorSize;

	private CellType cellType;

	/**
	 * Instantiates a new sheet header.
	 */
	public SheetHeader() {
		super();
		this.mapLayoutCell = new HashedMap<>();
		this.cellType = CellType.BLANK;
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
		this.cellType = CellType.BLANK;
		if (field.isAnnotationPresent(ExcelMergeRow.class))
			this.setExcelMergeRow(field.getAnnotation(ExcelMergeRow.class));
		if (field.isAnnotationPresent(ExcelColumnWidth.class))
			this.setExcelColumnWidth(field.getAnnotation(ExcelColumnWidth.class));
		if (field.isAnnotationPresent(ExcelImage.class))
			this.setExcelImage(field.getAnnotation(ExcelImage.class));
		if (field.isAnnotationPresent(ExcelDropDown.class))
			this.setExcelDropDown(field.getAnnotation(ExcelDropDown.class));
		if (field.isAnnotationPresent(ExcelSubtotal.class))
			this.setExcelSubtotal(field.getAnnotation(ExcelSubtotal.class));
		if (field.isAnnotationPresent(ExcelBooleanText.class))
			this.setExcelBooleanText(field.getAnnotation(ExcelBooleanText.class));
		if (field.isAnnotationPresent(ExcelDataValidation.class))
			this.setExcelDataValidation(field.getAnnotation(ExcelDataValidation.class));

		this.excelColumn = SpreadsheetUtils.getAnnotation(this.field, ExcelColumn.class);
		this.excelCellLayout = SpreadsheetUtils.getAnnotation(this.field, ExcelCellLayout.class);
		if (Date.class.isAssignableFrom(this.field.getType()) || Calendar.class.isAssignableFrom(this.field.getType()) || Timestamp.class.isAssignableFrom(this.field.getType()) || DateDropDown.class.isAssignableFrom(this.field.getType())
				|| CalendarDropDown.class.isAssignableFrom(this.field.getType()) || TimestampDropDown.class.isAssignableFrom(this.field.getType()))
			excelDate = SpreadsheetUtils.getAnnotation(this.field, ExcelDate.class);
		if (field.isAnnotationPresent(ExcelNumberFormat.class)) {
			this.setExcelNumberFormat(field.getAnnotation(ExcelNumberFormat.class));
			if (!Number.class.isAssignableFrom(this.field.getType()))
				throw new ExcelGeneratorException("The field is not Number type");
		}
		manageMapLayoutCell();

		this.getExcelColumn();
	}

	/**
	 * Manage map layout cell.
	 *
	 * @throws Exception the exception
	 */
	private void manageMapLayoutCell() throws Exception {
		this.mapLayoutCell = new HashedMap<>();
		if (this.excelCellLayout != null) {
			LayoutCell layoutCell = SpreadsheetUtils.reflectionAnnotation(new LayoutCell(), excelCellLayout);
			if (this.excelDate != null)
				layoutCell.setFormat(this.excelDate.value());
			if(this.excelNumberFormat!=null && StringUtils.isNotBlank(this.excelNumberFormat.value()))
				layoutCell.setNumberFormat(this.excelNumberFormat.value());
			this.colorSize = excelCellLayout.rgbFont().length > excelCellLayout.rgbForeground().length ? excelCellLayout.rgbFont().length : excelCellLayout.rgbForeground().length;
			for (int colorModul = 0; colorModul < colorSize; colorModul++) {
				LayoutCell layoutCellTemp = (LayoutCell) layoutCell.clone();
				layoutCellTemp.setColor(colorModul);
				this.mapLayoutCell.put(colorModul, layoutCellTemp);
			}
		}

	}

	/**
	 * Gets the excel column width.
	 *
	 * @return the excel column width
	 */
	public ExcelColumnWidth getExcelColumnWidth() {
		if (excelColumnWidth == null)
			this.excelColumnWidth = ExcelConstant.EXCEL_COLUMN_WIDTH.getAnnotation();
		return excelColumnWidth;
	}

	/**
	 * Gets the excel boolean text.
	 *
	 * @return the excel boolean text
	 */
	public ExcelBooleanText getExcelBooleanText() {
		return excelBooleanText;
	}

	/**
	 * Sets the excel boolean text.
	 *
	 * @param excelBooleanText the new excel boolean text
	 */
	public void setExcelBooleanText(ExcelBooleanText excelBooleanText) {
		this.excelBooleanText = excelBooleanText;
	}

	/**
	 * Gets the excel column.
	 *
	 * @return the excel column
	 */
	public ExcelColumn getExcelColumn() {
		return this.excelColumn;
	}

	/**
	 * Gets the excel date.
	 *
	 * @return the excel date
	 */
	public ExcelDate getExcelDate() {
		return excelDate;
	}

	/**
	 * Gets the excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout getExcelCellLayout() {
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
	 * @param indexRow the index row
	 * @return the layout cell
	 */
	public LayoutCell getLayoutCell(Integer indexRow) {
		return this.mapLayoutCell.get(indexRow % colorSize);
	}

	/**
	 * Gets the map layout cell.
	 *
	 * @return the map layout cell
	 */
	public Map<Integer, LayoutCell> getMapLayoutCell() {
		return mapLayoutCell;
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
		this.cellType = CellType.BLANK;
		if (excelFunction != null)
			this.cellType = CellType.FORMULA;
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
	 * @throws Exception the exception
	 */
	public void setExcelDate(ExcelDate excelDate) throws Exception {
		this.excelDate = excelDate;
		this.manageMapLayoutCell();
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
	 * @throws Exception the exception
	 */
	public void setExcelCellLayout(ExcelCellLayout excelCellLayout) throws Exception {
		this.excelCellLayout = excelCellLayout;
		this.manageMapLayoutCell();
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
	 * Gets the excel drop down.
	 *
	 * @return the excel drop down
	 */
	public ExcelDropDown getExcelDropDown() {
		return excelDropDown;
	}

	/**
	 * Sets the excel drop down.
	 *
	 * @param excelDropDown the new excel drop down
	 */
	public void setExcelDropDown(ExcelDropDown excelDropDown) {
		this.excelDropDown = excelDropDown;
	}

	/**
	 * Gets the excel image.
	 *
	 * @return the excel image
	 */
	public ExcelImage getExcelImage() {
		return excelImage;
	}

	/**
	 * Sets the excel image.
	 *
	 * @param excelImage the new excel image
	 */
	public void setExcelImage(ExcelImage excelImage) {
		this.excelImage = excelImage;
	}

	/**
	 * Gets the excel subtotal.
	 *
	 * @return the excel subtotal
	 */
	public ExcelSubtotal getExcelSubtotal() {
		return excelSubtotal;
	}

	/**
	 * Sets the excel subtotal.
	 *
	 * @param excelSubTotal the new excel subtotal
	 */
	public void setExcelSubtotal(ExcelSubtotal excelSubTotal) {
		this.excelSubtotal = excelSubTotal;
	}

	public ExcelNumberFormat getExcelNumberFormat() {
		return excelNumberFormat;
	}

	public void setExcelNumberFormat(ExcelNumberFormat excelNumberFormat) {
		this.excelNumberFormat = excelNumberFormat;
	}

	public CellType getCellType() {
		return cellType;
	}

	public boolean isDropDown() {
		return this.getExcelDropDown() != null || (this.getField() != null && this.getValue() != null && DropDown.class.isAssignableFrom(this.getField().getType()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cellType == null) ? 0 : cellType.hashCode());
		result = prime * result + colorSize;
		result = prime * result + ((excelBooleanText == null) ? 0 : excelBooleanText.hashCode());
		result = prime * result + ((excelCellLayout == null) ? 0 : excelCellLayout.hashCode());
		result = prime * result + ((excelColumn == null) ? 0 : excelColumn.hashCode());
		result = prime * result + ((excelColumnWidth == null) ? 0 : excelColumnWidth.hashCode());
		result = prime * result + ((excelDataValidation == null) ? 0 : excelDataValidation.hashCode());
		result = prime * result + ((excelDate == null) ? 0 : excelDate.hashCode());
		result = prime * result + ((excelDropDown == null) ? 0 : excelDropDown.hashCode());
		result = prime * result + ((excelFunction == null) ? 0 : excelFunction.hashCode());
		result = prime * result + ((excelHeaderCellLayout == null) ? 0 : excelHeaderCellLayout.hashCode());
		result = prime * result + ((excelImage == null) ? 0 : excelImage.hashCode());
		result = prime * result + ((excelMergeRow == null) ? 0 : excelMergeRow.hashCode());
		result = prime * result + ((excelNumberFormat == null) ? 0 : excelNumberFormat.hashCode());
		result = prime * result + ((excelSubtotal == null) ? 0 : excelSubtotal.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((keyMap == null) ? 0 : keyMap.hashCode());
		result = prime * result + ((mapLayoutCell == null) ? 0 : mapLayoutCell.hashCode());
		result = prime * result + numColumn;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	

	public ExcelDataValidation getExcelDataValidation() {
		return excelDataValidation;
	}

	public void setExcelDataValidation(ExcelDataValidation excelDataValidation) {
		this.excelDataValidation = excelDataValidation;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SheetHeader)) {
			return false;
		}
		SheetHeader other = (SheetHeader) obj;
		if (cellType != other.cellType) {
			return false;
		}
		if (colorSize != other.colorSize) {
			return false;
		}
		if (excelBooleanText == null) {
			if (other.excelBooleanText != null) {
				return false;
			}
		} else if (!excelBooleanText.equals(other.excelBooleanText)) {
			return false;
		}
		if (excelCellLayout == null) {
			if (other.excelCellLayout != null) {
				return false;
			}
		} else if (!excelCellLayout.equals(other.excelCellLayout)) {
			return false;
		}
		if (excelColumn == null) {
			if (other.excelColumn != null) {
				return false;
			}
		} else if (!excelColumn.equals(other.excelColumn)) {
			return false;
		}
		if (excelColumnWidth == null) {
			if (other.excelColumnWidth != null) {
				return false;
			}
		} else if (!excelColumnWidth.equals(other.excelColumnWidth)) {
			return false;
		}
		if (excelDataValidation == null) {
			if (other.excelDataValidation != null) {
				return false;
			}
		} else if (!excelDataValidation.equals(other.excelDataValidation)) {
			return false;
		}
		if (excelDate == null) {
			if (other.excelDate != null) {
				return false;
			}
		} else if (!excelDate.equals(other.excelDate)) {
			return false;
		}
		if (excelDropDown == null) {
			if (other.excelDropDown != null) {
				return false;
			}
		} else if (!excelDropDown.equals(other.excelDropDown)) {
			return false;
		}
		if (excelFunction == null) {
			if (other.excelFunction != null) {
				return false;
			}
		} else if (!excelFunction.equals(other.excelFunction)) {
			return false;
		}
		if (excelHeaderCellLayout == null) {
			if (other.excelHeaderCellLayout != null) {
				return false;
			}
		} else if (!excelHeaderCellLayout.equals(other.excelHeaderCellLayout)) {
			return false;
		}
		if (excelImage == null) {
			if (other.excelImage != null) {
				return false;
			}
		} else if (!excelImage.equals(other.excelImage)) {
			return false;
		}
		if (excelMergeRow == null) {
			if (other.excelMergeRow != null) {
				return false;
			}
		} else if (!excelMergeRow.equals(other.excelMergeRow)) {
			return false;
		}
		if (excelNumberFormat == null) {
			if (other.excelNumberFormat != null) {
				return false;
			}
		} else if (!excelNumberFormat.equals(other.excelNumberFormat)) {
			return false;
		}
		if (excelSubtotal == null) {
			if (other.excelSubtotal != null) {
				return false;
			}
		} else if (!excelSubtotal.equals(other.excelSubtotal)) {
			return false;
		}
		if (field == null) {
			if (other.field != null) {
				return false;
			}
		} else if (!field.equals(other.field)) {
			return false;
		}
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (keyMap == null) {
			if (other.keyMap != null) {
				return false;
			}
		} else if (!keyMap.equals(other.keyMap)) {
			return false;
		}
		if (mapLayoutCell == null) {
			if (other.mapLayoutCell != null) {
				return false;
			}
		} else if (!mapLayoutCell.equals(other.mapLayoutCell)) {
			return false;
		}
		if (numColumn != other.numColumn) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}
