/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.impl.SuperGenerateExcelImpl.java
*/
package bld.generator.report.excel.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;

import bld.generator.report.comparator.PivotColumnComparator;
import bld.generator.report.comparator.PivotColumnFunctionComparator;
import bld.generator.report.comparator.PivotRowComparator;
import bld.generator.report.comparator.SheetColumnComparator;
import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.DynamicColumn;
import bld.generator.report.excel.DynamicRowSheet;
import bld.generator.report.excel.ExcelAttachment;
import bld.generator.report.excel.ExcelHyperlink;
import bld.generator.report.excel.MergeSheet;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.SheetComponent;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.SheetDynamicData;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelDropDown;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelFormulaAlias;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelImage;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelPivot;
import bld.generator.report.excel.annotation.ExcelPivotColumn;
import bld.generator.report.excel.annotation.ExcelPivotColumnFunction;
import bld.generator.report.excel.annotation.ExcelPivotFilter;
import bld.generator.report.excel.annotation.ExcelPivotRow;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.annotation.ExcelRowHeight;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSummary;
import bld.generator.report.excel.annotation.ExcelSuperHeader;
import bld.generator.report.excel.annotation.ExcelSuperHeaderCell;
import bld.generator.report.excel.annotation.ExcelSuperHeaders;
import bld.generator.report.excel.constant.ColumnDateFormat;
import bld.generator.report.excel.constant.RowStartEndType;
import bld.generator.report.excel.data.DropDownCell;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.data.FunctionCell;
import bld.generator.report.excel.data.InfoColumn;
import bld.generator.report.excel.data.LayoutCell;
import bld.generator.report.excel.data.MergeCell;
import bld.generator.report.excel.data.SheetHeader;
import bld.generator.report.excel.dropdown.BoxMessage;
import bld.generator.report.excel.dropdown.DropDown;
import bld.generator.report.excel.sheet_mapping.SheetMappingSheet;
import bld.generator.report.exception.ExcelGeneratorException;
import bld.generator.report.utils.ExcelUtils;
import bld.generator.report.utils.ValueProps;

/**
 * The Class SuperGenerateExcelImpl.
 */
public abstract class SuperGenerateExcelImpl {

	/** The Constant FLAT_ANGLE. */
	private static final int FLAT_ANGLE = 180;

	/** The Constant PATTERN. */
	private static final String PATTERN = "\\$\\{.*?}";

	// private static final String PATTERN_QUAD = "\\[.*?\\]";

	/** The Constant $. */
	private static final String $ = "${";

	/** The Constant SDF. */
	protected static final SimpleDateFormat SDF = new SimpleDateFormat(ColumnDateFormat.DD_MM_YYYY.getValue());

	/** The merge calcolo cells. */
	protected CellStyle mergeCalcoloCells = null;

	/** The map cell style. */
	protected Map<LayoutCell, CellStyle> mapCellStyle = new HashMap<>();

	/** The map cell header style. */
	protected Map<LayoutCell, CellStyle> mapCellHeaderStyle = new HashMap<>();

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(SuperGenerateExcelImpl.class);

	/** The Constant WIDTH_CELL_STANDARD. */
	private static final int WIDTH_CELL_STANDARD = 22;

	/** The map field column. */
	protected Map<String, InfoColumn> mapFieldColumn = new HashMap<>();

	/** The map width column. */
	protected Map<Integer, Integer> mapWidthColumn = new HashMap<>();

	/** The list function cell. */
	protected List<FunctionCell> listFunctionCell = new ArrayList<>();

	/** The list drop down. */
	protected List<DropDownCell> listDropDown = new ArrayList<>();

	/** The map sheet. */
	protected Map<String, BaseSheet> mapSheet = new HashMap<>();

	protected SheetMappingSheet sheetMapping;

	/** The value props. */
	@Autowired
	protected ValueProps valueProps;

	/**
	 * Instantiates a new super generate excel impl.
	 */
	public SuperGenerateExcelImpl() {
		super();
	}

	/**
	 * Creates the cell style.
	 *
	 * @param workbook the workbook
	 * @param layout   the layout
	 * @return the cell style
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelHeaderCellLayout layout) {
		CellStyle cellStyleHeader = workbook.createCellStyle();
		ExcelRgbColor rgbFont = layout.rgbFont();
		ExcelRgbColor rgbForeground = layout.rgbForeground();
		Font font = getFont(workbook, layout.font());
		if (workbook instanceof HSSFWorkbook) {
			HSSFPalette paletteFont = ((HSSFWorkbook) workbook).getCustomPalette();
			HSSFColor colorFont = paletteFont.findSimilarColor(rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((HSSFFont) font).setColor(colorFont.getIndex());

			HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
			HSSFColor colorForeground = palette.findSimilarColor(rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			cellStyleHeader.setFillForegroundColor(colorForeground.getIndex());

		} else {
			XSSFColor colorForeground = getColor(rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			XSSFColor colorFont = getColor(rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((XSSFCellStyle) cellStyleHeader).setFillForegroundColor(colorForeground);
			((XSSFFont) font).setColor(colorFont);
		}
		cellStyleHeader.setRotation((short) (layout.rotation() % FLAT_ANGLE));
		cellStyleHeader.setFont(font);
		cellStyleHeader.setFillPattern(layout.fillPatternType());
		cellStyleHeader.setAlignment(layout.horizontalAlignment());
		cellStyleHeader.setVerticalAlignment(layout.verticalAlignment());
		cellStyleHeader.setWrapText(layout.wrap());
		cellStyleHeader.setLocked(layout.locked());
		cellStyleHeader = getBorder(cellStyleHeader, layout.border());

		return cellStyleHeader;
	}

	/**
	 * Creates the cell style.
	 *
	 * @param workbook the workbook
	 * @param layout   the layout
	 * @param indexRow the index row
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout, Integer indexRow) throws Exception {
		return createCellStyle(workbook, layout, null, indexRow);
	}

	/**
	 * Creates the cell style.
	 *
	 * @param workbook    the workbook
	 * @param layout      the layout
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout, SheetHeader sheetHeader, Integer indexRow) throws Exception {
		ExcelDate excelDate = null;
		if (sheetHeader != null)
			excelDate = sheetHeader.getExcelDate();
		CellStyle cellStyle = workbook.createCellStyle();
		int indexFont = indexRow % layout.rgbFont().length;
		int indexForeground = indexRow % layout.rgbForeground().length;
		ExcelRgbColor rgbFont = layout.rgbFont()[indexFont];
		ExcelRgbColor rgbForeground = layout.rgbForeground()[indexForeground];
		Font font = getFont(workbook, layout.font());
		if (workbook instanceof HSSFWorkbook) {
			HSSFPalette paletteFont = ((HSSFWorkbook) workbook).getCustomPalette();
			HSSFColor colorFont = paletteFont.findSimilarColor(rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((HSSFFont) font).setColor(colorFont.getIndex());

			HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
			HSSFColor colorForeground = palette.findSimilarColor(rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			cellStyle.setFillForegroundColor(colorForeground.getIndex());

		} else {
			XSSFColor colorForeground = getColor(rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			XSSFColor colorFont = getColor(rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((XSSFCellStyle) cellStyle).setFillForegroundColor(colorForeground);
			((XSSFFont) font).setColor(colorFont);
		}
		cellStyle.setFont(font);
		cellStyle.setFillPattern(layout.fillPatternType());
		cellStyle.setAlignment(layout.horizontalAlignment());
		cellStyle.setVerticalAlignment(layout.verticalAlignment());
		cellStyle = getBorder(cellStyle, layout.border());
		cellStyle.setWrapText(layout.wrap());
		cellStyle.setLocked(layout.locked());

		if (excelDate != null)
			cellStyle = dateCellStyle(workbook, cellStyle, excelDate.format().getValue());
		else if (layout.precision() > -1) {
			String format = "0.";
			for (int i = 0; i < layout.precision(); i++)
				format += "0";
			cellStyle = dateCellStyle(workbook, cellStyle, format);
		} else if (sheetHeader != null && sheetHeader.getField() != null && String.class.isAssignableFrom(sheetHeader.getField().getType())) {
			DataFormat fmt = workbook.createDataFormat();
			cellStyle.setDataFormat(fmt.getFormat("text"));
		}

		return cellStyle;
	}

	/**
	 * Gets the font.
	 *
	 * @param workbook  the workbook
	 * @param excelFont the excel font
	 * @return the font
	 */
	protected Font getFont(Workbook workbook, ExcelFont excelFont) {
		Font font = workbook.createFont();
		font.setBold(excelFont.bold());
		font.setFontName(excelFont.font().getValue());
		font.setItalic(excelFont.italic());
		font.setUnderline(excelFont.underline().getValue());
		font.setFontHeight((short) (excelFont.size() * 20));
		return font;
	}

	/**
	 * Gets the color.
	 *
	 * @param rgbColor the rgb color
	 * @return the color
	 */
	protected XSSFColor getColor(byte... rgbColor) {
		XSSFColor color = new XSSFColor();
		color.setRGB(rgbColor);
		return color;
	}

	/**
	 * Gets the border.
	 *
	 * @param cellStyle   the cell style
	 * @param excelBorder the excel border
	 * @return the border
	 */
	protected CellStyle getBorder(CellStyle cellStyle, ExcelBorder excelBorder) {
		cellStyle.setBorderLeft(excelBorder.left());
		cellStyle.setBorderRight(excelBorder.right());
		cellStyle.setBorderTop(excelBorder.top());
		cellStyle.setBorderBottom(excelBorder.bottom());
		return cellStyle;

	}

	/**
	 * Gets the list sheet header.
	 *
	 * @param classRow the class row
	 * @param entity   the entity
	 * @param sheet    the sheet
	 * @return the list sheet header
	 * @throws Exception the exception
	 */
	protected List<SheetHeader> getListSheetHeader(Class<?> classRow, Object entity, Sheet sheet) throws Exception {
		logger.debug("Row: " + classRow.getSimpleName());
		Set<String> listTitle = new HashSet<>();
		List<SheetHeader> listSheetHeader = new ArrayList<>();
		Set<Field> listField = ExcelUtils.getListField(classRow);
		for (Field field : listField) {
			ExcelColumn column = field.getAnnotation(ExcelColumn.class);
			if (column != null && !column.ignore()) {
				Object value = null;
				if (entity != null)
					value = PropertyUtils.getProperty(entity, field.getName());
				SheetHeader sheetHeader = new SheetHeader(field, value);
				if (value != null) {
					value = manageExcelImage(sheetHeader, value);
					sheetHeader.setValue(value);
				}

				if (field.isAnnotationPresent(ExcelDropDown.class) && field.getClass().isAssignableFrom(DropDown.class))
					throw new ExcelGeneratorException("The following annotation @ExcelDropDown can not be assigned on fields of classes type DropDown");
				if (field.isAnnotationPresent(ExcelDropDown.class))
					sheetHeader.setExcelDropDown(field.getAnnotation(ExcelDropDown.class));
				listSheetHeader.add(sheetHeader);
				if (listTitle.contains(column.columnName()))
					logger.warn("Exist another equal column with columnName= \"" + column.columnName() + "\" for the same sheet!!!");
				listTitle.add(column.columnName());
			}
		}
		if (classRow.isAnnotationPresent(ExcelFunctionRows.class)) {
			ExcelFunctionRows excelFunctionRows = classRow.getAnnotation(ExcelFunctionRows.class);
			for (ExcelFunctionRow excelFunction : excelFunctionRows.excelFunctions()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunction.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunction.excelCellsLayout());
				sheetHeader.setExcelFunction(excelFunction.excelFunction());
				sheetHeader.setExcelColumnWidth(excelFunction.excelColumnWidth());
				sheetHeader.setExcelHeaderCellLayout(excelFunction.excelHeaderCellLayout());
				sheetHeader.setExcelSubtotal(excelFunction.excelSubtotal());
				listSheetHeader.add(sheetHeader);

			}
			for (ExcelFunctionMergeRow excelFunctionMerge : excelFunctionRows.excelFunctionMerges()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunctionMerge.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunctionMerge.excelCellsLayout());
				sheetHeader.setExcelFunction(excelFunctionMerge.excelFunction());
				sheetHeader.setExcelMergeRow(excelFunctionMerge.excelMergeRow());
				sheetHeader.setExcelColumnWidth(excelFunctionMerge.excelColumnWidth());
				sheetHeader.setExcelHeaderCellLayout(excelFunctionMerge.excelHeaderCellLayout());
				sheetHeader.setExcelSubtotal(excelFunctionMerge.excelSubtotal());
				listSheetHeader.add(sheetHeader);

			}
		}

		Collections.sort(listSheetHeader, new SheetColumnComparator());
		return listSheetHeader;
	}

	/**
	 * Sets the cell value will merged.
	 *
	 * @param workbook    the workbook
	 * @param cellStyle   the cell style
	 * @param cell        the cell
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @param sheet       the sheet
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected boolean setCellValueWillMerged(Workbook workbook, CellStyle cellStyle, Cell cell, SheetHeader sheetHeader, Integer indexRow, Sheet sheet) throws Exception {
		this.setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow, sheet); // writeCellEmpty(workbook,
		// cellStyle,
		// cell,
		// sheetHeader);
		return false;
	}

	/**
	 * Merge row and remove map.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param indexRow         the index row
	 * @param mapMergeRow      the map merge row
	 * @param numColumn        the num column
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void mergeRowAndRemoveMap(Workbook workbook, Sheet sheet, Integer indexRow, Map<Integer, MergeCell> mapMergeRow, int numColumn, FormulaEvaluator formulaEvaluator) throws Exception {
		mergeRow(workbook, sheet, indexRow, mapMergeRow, numColumn, formulaEvaluator);
		mapMergeRow.remove(numColumn);
	}

	/**
	 * Merge row.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param indexRow         the index row
	 * @param mapMergeRow      the map merge row
	 * @param numColumn        the num column
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void mergeRow(Workbook workbook, Sheet sheet, Integer indexRow, Map<Integer, MergeCell> mapMergeRow, int numColumn, FormulaEvaluator formulaEvaluator) throws Exception {
		MergeCell mergeRow = mapMergeRow.get(numColumn);
		this.manageDropDown(sheet, mergeRow.getSheetHeader(), mergeRow.getRowStart(), mergeRow.getRowStart(), numColumn, numColumn, indexRow);
		mergeRow.setRowEnd(indexRow - 1);
		runMergeCell(workbook, sheet, mergeRow, formulaEvaluator);
	}

	/**
	 * Run merge cell.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param mergeCell        the merge cell
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void runMergeCell(Workbook workbook, Sheet sheet, MergeCell mergeCell, FormulaEvaluator formulaEvaluator) throws Exception {
		setCellValueExcel(workbook, sheet, mergeCell, formulaEvaluator);
		if (mergeCell.getRowStart() < mergeCell.getRowEnd() || mergeCell.getColumnFrom() < mergeCell.getColumnTo())
			sheet.addMergedRegion(new CellRangeAddress(mergeCell.getRowStart(), mergeCell.getRowEnd(), mergeCell.getColumnFrom(), mergeCell.getColumnTo()));
	}

	/**
	 * Adds the comment.
	 *
	 * @param workbook   the workbook
	 * @param sheet      the sheet
	 * @param row        the row
	 * @param cellHeader the cell header
	 * @param commento   the commento
	 */
	protected void addComment(Workbook workbook, Sheet sheet, Row row, Cell cellHeader, String commento) {
		Drawing<?> drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 1, 1, 1);
		anchor.setCol1(cellHeader.getColumnIndex());
		anchor.setCol2(cellHeader.getColumnIndex() + 2);
		anchor.setRow1(row.getRowNum());
		anchor.setRow2(row.getRowNum() + 2);
		Comment comment = drawing.createCellComment(anchor);

		if (comment instanceof HSSFComment)
			((HSSFComment) comment).setWrapText(WIDTH_CELL_STANDARD);
		comment.setString(workbook.getCreationHelper().createRichTextString(commento));
		cellHeader.setCellComment(comment);
	}

	/**
	 * Sets the cell summary.
	 *
	 * @param excelSheetLayout the excel sheet layout
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param sheetSummary     the sheet summary
	 * @param sheetHeader      the sheet header
	 * @param row              the row
	 * @param indexRow         the index row
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void setCellSummary(ExcelSheetLayout excelSheetLayout, Workbook workbook, Sheet sheet, SheetSummary sheetSummary, SheetHeader sheetHeader, Row row, Integer indexRow, FormulaEvaluator formulaEvaluator) throws Exception {
		ExcelSummary excelSummary = ExcelUtils.getAnnotation(sheetSummary.getClass(), ExcelSummary.class);
		LayoutCell layoutCellSummary = ExcelUtils.reflectionAnnotation(new LayoutCell(), excelSummary.layout());
		short heightRow = ExcelUtils.AUTO_SIZE_HEIGHT;
		if (sheetHeader.getField() != null && sheetHeader.getField().isAnnotationPresent(ExcelRowHeight.class)) {
			ExcelRowHeight excelRowHeight = sheetHeader.getField().getAnnotation(ExcelRowHeight.class);
			heightRow = ExcelUtils.rowHeight(excelRowHeight.height());
		}
		row.setHeight(heightRow);
		CellStyle cellStyleColumn0 = createCellStyle(workbook, excelSummary.layout(), indexRow);
		Cell cellColumn0 = row.createCell(excelSheetLayout.startColumn());
		setCellStyleExcel(cellStyleColumn0, cellColumn0, layoutCellSummary);
		cellColumn0.setCellValue(this.valueProps.valueProps(sheetHeader.getExcelColumn().columnName()));
		if (StringUtils.isNotBlank(sheetHeader.getExcelColumn().comment()))
			addComment(workbook, sheet, row, cellColumn0, sheetHeader.getExcelColumn().comment());
		ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
//		ExcelDate excelDate = null;
//		if (sheetHeader.getField() != null && (Date.class.isAssignableFrom(sheetHeader.getField().getType()) || Calendar.class.isAssignableFrom(sheetHeader.getField().getType()) || Timestamp.class.isAssignableFrom(sheetHeader.getField().getType())))
//			excelDate = sheetHeader.getExcelDate();
		CellStyle cellStyleColumn1 = this.createCellStyle(workbook, excelCellLayout, sheetHeader, indexRow);
		int column = excelSheetLayout.startColumn() + 1;
		Cell cellColumn1 = row.createCell(column);
		manageDropDown(sheet, sheetHeader, indexRow, indexRow, column, column, indexRow);
		setCellValueExcel(workbook, sheet, cellColumn1, cellStyleColumn1, sheetHeader, cellColumn1.getRowIndex(), formulaEvaluator);
		// setCellValueExcel(workbook, cellColumn1, cellStyleColumn1, sheetHeader);

	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param cell             the cell
	 * @param cellStyle        the cell style
	 * @param sheetHeader      the sheet header
	 * @param indexRow         the index row
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Workbook workbook, Sheet sheet, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, FormulaEvaluator formulaEvaluator) throws Exception {

		if (sheetHeader.getExcelFunction() != null) {
			try {
				setCellFormulaAndEvaluateCell(cell, cellStyle, sheetHeader, indexRow, sheet, formulaEvaluator);
			} catch (Exception e) {
				FunctionCell functionCell = new FunctionCell();
				functionCell.setCell(cell);
				functionCell.setSheetHeader(sheetHeader);
				functionCell.setFormulaEvaluator(formulaEvaluator);
				this.listFunctionCell.add(functionCell);
			}
		} else
			setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow, sheet);

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param cell             the cell
	 * @param cellStyle        the cell style
	 * @param sheetHeader      the sheet header
	 * @param indexRow         the index row
	 * @param sheet            the sheet
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void setCellFormulaAndEvaluateCell(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, Sheet sheet, FormulaEvaluator formulaEvaluator) throws Exception {
		setCellFormula(cell, cellStyle, sheetHeader, indexRow, sheet);
		formulaEvaluator.evaluateFormulaCell(cell);
	}

	/**
	 * Sets the cell formula.
	 *
	 * @param cell        the cell
	 * @param cellStyle   the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @param sheet       the sheet
	 * @throws Exception the exception
	 */
	protected void setCellFormula(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, Sheet sheet) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell(indexRow);
		setCellStyleExcel(cellStyle, cell, layoutCell);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = buildFunction(sheet, indexRow, function, RowStartEndType.ROW_EMPTY, excelFunction.alias());
		if (excelFunction.anotherTable()) {
			function = buildFunction(sheet, null, function, RowStartEndType.ROW_START, excelFunction.alias());
			function = buildFunction(sheet, null, function, RowStartEndType.ROW_END, excelFunction.alias());
		}
		function = buildFunction(sheet, indexRow, function, RowStartEndType.ROW_START, excelFunction.alias());
		function = buildFunction(sheet, indexRow, function, RowStartEndType.ROW_END, excelFunction.alias());
		function = buildFunction(sheet, indexRow, function, RowStartEndType.ROW_HEADER, excelFunction.alias());

		logger.debug("Function: " + function);
		if (StringUtils.isNotEmpty(function))
			cell.setCellFormula(function);
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet           the sheet
	 * @param indexRow        the index row
	 * @param function        the function
	 * @param rowStartEndType the row start end type
	 * @param alias           the alias
	 * @return the string
	 * @throws Exception the exception
	 */
	protected String buildFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType, ExcelFormulaAlias[] alias) throws Exception {
		function = function.replace(ExcelUtils.ENV_SHEET_NAME, "\"" + sheet.getSheetName() + "\"");
		Matcher matcher = ExcelUtils.matcher(PATTERN, function);
		Map<String, ExcelFormulaAlias> mapExcelFormulaAlias = new HashMap<>();
		for (ExcelFormulaAlias formulaAlias : alias)
			mapExcelFormulaAlias.put(formulaAlias.alias().replace("'", BaseSheet.APOS), formulaAlias);

		while (matcher.find()) {
			String parameter = matcher.group();
			String keyParameter = parameter.replace($, "").replace(rowStartEndType.getValue(), "").replace("}", "").replace("'", BaseSheet.APOS).trim();
			boolean blockColumn = false;
			boolean blockRow = false;
			String sheetName = sheet.getSheetName();
			if (mapExcelFormulaAlias.containsKey(keyParameter)) {
				ExcelFormulaAlias excelFormulaAlias = mapExcelFormulaAlias.get(keyParameter);
				String sheetPoint = "";
				if (StringUtils.isNotEmpty(excelFormulaAlias.sheet()))
					sheetPoint = excelFormulaAlias.sheet() + ".";
				keyParameter = sheetPoint + mapExcelFormulaAlias.get(keyParameter).coordinate();
				blockColumn = excelFormulaAlias.blockColumn();
				blockRow = excelFormulaAlias.blockRow();
				// parameter=parameter.replace(excelFormulaAlias.alias(),keyParameter);
			}

			String appFunction = buildFunction(sheet, keyParameter, indexRow, rowStartEndType, sheetName, function, parameter, blockColumn, blockRow);
			if (StringUtils.isNotEmpty(appFunction))
				function = appFunction;

		}
		return function;
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet           the sheet
	 * @param keyParameter    the key parameter
	 * @param indexRow        the index row
	 * @param rowStartEndType the row start end type
	 * @param sheetName       the sheet name
	 * @param function        the function
	 * @param parameter       the parameter
	 * @param blockColumn     the block column
	 * @param blockRow        the block row
	 * @return the string
	 * @throws Exception the exception
	 */
	private String buildFunction(Sheet sheet, String keyParameter, Integer indexRow, RowStartEndType rowStartEndType, String sheetName, String function, String parameter, boolean blockColumn, boolean blockRow) throws Exception {
		String exprenssionIndex = "";
		if (keyParameter.contains("[")) {
			exprenssionIndex = keyParameter.substring(keyParameter.indexOf("[") + 1, keyParameter.length() - 1);
			keyParameter = keyParameter.substring(0, keyParameter.indexOf("["));
		}
		boolean fieldValue = keyParameter.contains(RowStartEndType.VALUE.getValue());
		if (fieldValue)
			keyParameter = keyParameter.replace(RowStartEndType.VALUE.getValue(), "");
		Integer row = null;
		InfoColumn infoColumn = null;
		if (mapFieldColumn.containsKey(ExcelUtils.getKeyColumn(sheet, keyParameter))) {
			infoColumn = (InfoColumn) mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, keyParameter));
			row = indexRow;
			Integer start = null;
			Integer end = null;
			Double evalute = null;
			if (RowStartEndType.ROW_HEADER.equals(rowStartEndType)) {
				row = infoColumn.getRowHeader();
				if (row == null)
					throw new ExcelGeneratorException("The header not exist");
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					evalute = ExcelUtils.evaluate(exprenssionIndex, "header", row);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				}
			} else if (RowStartEndType.ROW_EMPTY.equals(rowStartEndType) && !infoColumn.getMapRowMergeRow().isEmpty() && infoColumn.getMapRowMergeRow().containsKey(indexRow)) {
				row = infoColumn.getMapRowMergeRow().get(indexRow).getRowStart();
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					evalute = ExcelUtils.evaluate(exprenssionIndex, "row", row);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				}
			} else if (row == null && RowStartEndType.ROW_START.equals(rowStartEndType)) {
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					start = infoColumn.getFirstRow();
					evalute = ExcelUtils.evaluate(exprenssionIndex, "start", start);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				} else
					row = infoColumn.getFirstRow();

			} else if (row == null && RowStartEndType.ROW_END.equals(rowStartEndType)) {
				if (StringUtils.isNotEmpty(exprenssionIndex)) {
					end = infoColumn.getLastRow();
					evalute = ExcelUtils.evaluate(exprenssionIndex, "end", end);
					if (evalute != null)
						row = evalute.intValue();
					else
						return null;
				} else
					row = infoColumn.getLastRow();
			} else if (row != null && StringUtils.isNotEmpty(exprenssionIndex)) {
				switch (rowStartEndType) {
				case ROW_EMPTY:
					evalute = ExcelUtils.evaluate(exprenssionIndex, "row", row);
					break;
				case ROW_END:
					evalute = ExcelUtils.evaluate(exprenssionIndex, "end", row);
					break;
				case ROW_HEADER:
					row = infoColumn.getRowHeader();
					evalute = ExcelUtils.evaluate(exprenssionIndex, "header", row);
					break;
				case ROW_START:
					evalute = ExcelUtils.evaluate(exprenssionIndex, "start", row);
					break;
				default:
					break;

				}
				if (evalute != null)
					row = evalute.intValue();
				else
					return null;
			}
			try {

				if (row != null) {
					if (keyParameter.contains(".")) {

						sheetName = keyParameter.substring(0, keyParameter.lastIndexOf("."));

						if (!fieldValue) {
							sheetName = "'" + sheetName.replace("'", BaseSheet.APOS) + "'!";

							if (function.contains(sheetName))
								function = function.replace(parameter, ExcelUtils.coordinateCalculation(row + 1, infoColumn.getColumnNum(), blockColumn, blockRow));
							else
								function = function.replace(parameter, sheetName + ExcelUtils.coordinateCalculation(row + 1, infoColumn.getColumnNum(), blockColumn, blockRow));
						}
					} else if (!fieldValue)
						function = function.replace(parameter, ExcelUtils.coordinateCalculation(row + 1, infoColumn.getColumnNum(), blockColumn, blockRow));
				}

			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		if (fieldValue) {
			BaseSheet baseSheet = this.mapSheet.get(sheetName);
			Object value = null;
			if (baseSheet instanceof MergeSheet) {
				MergeSheet mergeSheet = (MergeSheet) baseSheet;

				for (SheetComponent bs : mergeSheet.getListSheet()) {
					value = getStaticValue(keyParameter, row != null && infoColumn != null ? row - 1 - infoColumn.getRowHeader() : null, bs);
					if (value != null)
						break;
				}

			} else
				value = getStaticValue(keyParameter, row != null && infoColumn != null ? row - 1 - infoColumn.getRowHeader() : null, (SheetComponent) baseSheet);
			if (value != null)
				function = function.replace(parameter, value.toString());
		}
		return function;
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet           the sheet
	 * @param indexRow        the index row
	 * @param function        the function
	 * @param rowStartEndType the row start end type
	 * @param blockColumn     the dollar
	 * @param blockRow        the block row
	 * @return the string
	 * @throws Exception the exception
	 */
	protected String buildFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType, boolean blockColumn, boolean blockRow) throws Exception {
		function = function.replace(ExcelUtils.ENV_SHEET_NAME, "\"" + sheet.getSheetName() + "\"");
		Matcher matcher = ExcelUtils.matcher(PATTERN, function);

		while (matcher.find()) {
			String sheetName = sheet.getSheetName();
			String parameter = matcher.group();
			String keyParameter = parameter.replace($, "").replace(rowStartEndType.getValue(), "").replace("}", "").replace("'", BaseSheet.APOS).trim();
			String appFunction = buildFunction(sheet, keyParameter, indexRow, rowStartEndType, sheetName, function, parameter, blockColumn, blockRow);
			if (StringUtils.isNotEmpty(appFunction))
				function = appFunction;

		}
		return function;
	}

	/**
	 * Gets the static value.
	 *
	 * @param keyParameter   the key parameter
	 * @param row            the row
	 * @param sheetComponent the sheet component
	 * @return the static value
	 * @throws NoSuchFieldException      the no such field exception
	 * @throws IllegalAccessException    the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException     the no such method exception
	 * @throws ExcelGeneratorException   the excel generator exception
	 */
	private Object getStaticValue(String keyParameter, Integer row, SheetComponent sheetComponent) throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, ExcelGeneratorException {
		Object value = null;
		if (sheetComponent instanceof SheetDynamicData) {
			SheetDynamicData<?> sheetDynamicData = (SheetDynamicData<?>) sheetComponent;
			DynamicRowSheet dynamicRowShet = sheetDynamicData.getListRowSheet().get(row);
			if (sheetDynamicData.getMapExtraColumnAnnotation().containsKey(keyParameter))
				value = dynamicRowShet.getMapValue().get(keyParameter);
			else if (dynamicRowShet.getClass().getDeclaredField(keyParameter) != null)
				value = PropertyUtils.getProperty(dynamicRowShet, keyParameter);

		} else if (sheetComponent instanceof SheetData) {
			SheetData<?> sheetData = (SheetData<?>) sheetComponent;
			RowSheet rowSheet = sheetData.getListRowSheet().get(row);
			if (rowSheet.getClass().getDeclaredField(keyParameter) != null)
				value = PropertyUtils.getProperty(rowSheet, keyParameter);

		} else if (sheetComponent instanceof SheetSummary) {
			if (sheetComponent.getClass().getDeclaredField(keyParameter) != null)
				value = PropertyUtils.getProperty(sheetComponent, keyParameter);
		}

		return value;
	}

	/**
	 * Builds the function.
	 *
	 * @param sheet           the sheet
	 * @param indexRow        the index row
	 * @param function        the function
	 * @param rowStartEndType the row start end type
	 * @return the string
	 * @throws Exception the exception
	 */
	protected String buildFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType) throws Exception {
		return buildFunction(sheet, indexRow, function, rowStartEndType, false, false);
	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param mergeRow         the merge row
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	private void setCellValueExcel(Workbook workbook, Sheet sheet, MergeCell mergeRow, FormulaEvaluator formulaEvaluator) throws Exception {
		if (mergeRow.getSheetHeader().getExcelFunction() != null)
			try {
				setCellFormulaAndEvaluate(sheet, mergeRow, 0, formulaEvaluator);
			} catch (Exception e) {
				FunctionCell functionCell = new FunctionCell();
				functionCell.setMergeRow(mergeRow);
				functionCell.setFormulaEvaluator(formulaEvaluator);
				listFunctionCell.add(functionCell);
			}
		else
			setCellValueExcel(workbook, mergeRow.getCellFrom(), mergeRow.getCellStyleFrom(), mergeRow.getSheetHeader(), 0, sheet);

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param sheet            the sheet
	 * @param mergeRow         the merge row
	 * @param indexRow         the index row
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	protected void setCellFormulaAndEvaluate(Sheet sheet, MergeCell mergeRow, Integer indexRow, FormulaEvaluator formulaEvaluator) throws Exception {
		Cell cell = setCellFormula(sheet, mergeRow, indexRow);
		formulaEvaluator.evaluateFormulaCell(cell);
	}

	/**
	 * Sets the cell formula.
	 *
	 * @param sheet    the sheet
	 * @param mergeRow the merge row
	 * @param indexRow the index row
	 * @return the cell
	 * @throws Exception the exception
	 */
	protected Cell setCellFormula(Sheet sheet, MergeCell mergeRow, Integer indexRow) throws Exception {
		SheetHeader sheetHeader = mergeRow.getSheetHeader();
		CellStyle cellStyle = mergeRow.getCellStyleFrom();
		Cell cell = mergeRow.getCellFrom();
		LayoutCell layoutCell = sheetHeader.getLayoutCell(indexRow);
		setCellStyleExcel(cellStyle, cell, layoutCell);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_EMPTY, excelFunction.alias());
		if (excelFunction.anotherTable()) {
			function = buildFunction(sheet, null, function, RowStartEndType.ROW_START, excelFunction.alias());
			function = buildFunction(sheet, null, function, RowStartEndType.ROW_END, excelFunction.alias());
		}

		function = buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_START, excelFunction.alias());
		function = buildFunction(sheet, mergeRow.getRowEnd(), function, RowStartEndType.ROW_END, excelFunction.alias());
		function = buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_HEADER, excelFunction.alias());
		logger.debug("Function: " + function);
		cell.setCellFormula(function);
		return cell;
	}

//	private void formatCell(SheetHeader sheetHeader,Cell cell) {
//		if(sheetHeader.getValue()==null && String.class.isAssignableFrom(sheetHeader.getField().getType())) {
//			DataFormatter formatter=new DataFormatter();
//			//formatter.formatCellValue(cell);
//			formatter.getDefaultFormat(cell);
//			cell.setCellType(CellType.STRING);
//		}
//		
//	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param workbook    the workbook
	 * @param cell        the cell
	 * @param cellStyle   the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @param sheet       the sheet
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Workbook workbook, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, Sheet sheet) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell(indexRow);
//		if (cellStyle == null) {
//			if (this.mapCellStyle.containsKey(layoutCell))
//				cellStyle = this.mapCellStyle.get(layoutCell);
//			else
//				cellStyle = createCellStyle(workbook, sheetHeader.getExcelCellLayout(), sheetHeader.getExcelDate(), indexRow);
//		}
		if (cellStyle == null)
			cellStyle = this.mapCellStyle.get(layoutCell);
		setCellStyleExcel(cellStyle, cell, layoutCell);

		if (sheetHeader.getValue() instanceof Date)
			cell.setCellValue((Date) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Calendar)
			cell.setCellValue((Calendar) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Timestamp)
			cell.setCellValue(new Date(((Timestamp) sheetHeader.getValue()).getTime()));
		else if (sheetHeader.getValue() instanceof String || sheetHeader.getValue() instanceof Character) {
			String value = null;
			if (sheetHeader.getValue() != null)
				value = "" + sheetHeader.getValue();
			cell.setCellValue(value);
		} else if (sheetHeader.getValue() instanceof Number)
			cell.setCellValue(((Number) sheetHeader.getValue()).doubleValue());
		else if (sheetHeader.getValue() instanceof Boolean) {
			boolean value = (Boolean) sheetHeader.getValue();
			if (sheetHeader.getExcelBooleanText() == null)
				cell.setCellValue(value);
			else
				cell.setCellValue(value ? sheetHeader.getExcelBooleanText().ifTrue() : sheetHeader.getExcelBooleanText().ifFalse());

		} else if (sheetHeader.getValue() instanceof ExcelHyperlink) {
			ExcelHyperlink excelHyperlink = (ExcelHyperlink) sheetHeader.getValue();
			CreationHelper createHelper = workbook.getCreationHelper();
			if (excelHyperlink.getHyperlinkType() == null)
				throw new ExcelGeneratorException("The field hyperlinkType is null");
			if (StringUtils.isEmpty(excelHyperlink.getAddress()))
				throw new ExcelGeneratorException("The field address is null or is empty");
			Hyperlink hyperlink = createHelper.createHyperlink(excelHyperlink.getHyperlinkType());
			String address = excelHyperlink.getAddress();
			if (HyperlinkType.DOCUMENT.equals(excelHyperlink.getHyperlinkType()))
				address = excelHyperlink.getAddressDocument();
			hyperlink.setAddress(address);
			cell.setHyperlink(hyperlink);
			cell.setCellValue(excelHyperlink.getValue());
		} else if (sheetHeader.getValue() instanceof ExcelAttachment<?>) {
			this.addAttachment(workbook, sheet, sheetHeader, cell);
		} else if (sheetHeader.getValue() instanceof byte[])
			this.addImage(workbook, sheet, sheetHeader, cell);
		else if (sheetHeader.getValue() instanceof DropDown<?>) {
			DropDown<?> dropDown = (DropDown<?>) sheetHeader.getValue();
			sheetHeader.setValue(dropDown.getValue());
			setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow, sheet);
		}

	}

	/**
	 * Date cell style.
	 *
	 * @param workbook  the workbook
	 * @param cellStyle the cell style
	 * @param format    the format
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle dateCellStyle(Workbook workbook, CellStyle cellStyle, String format) throws Exception {
		CreationHelper helper = workbook.getCreationHelper();
		cellStyle.setDataFormat(helper.createDataFormat().getFormat(this.valueProps.valueProps(format)));
		return cellStyle;
	}

	/**
	 * Sets the cell style excel.
	 *
	 * @param cellStyle  the cell style
	 * @param cell       the cell
	 * @param layoutCell the layout cell
	 */
	protected void setCellStyleExcel(CellStyle cellStyle, Cell cell, LayoutCell layoutCell) {
		if (!mapCellStyle.containsKey(layoutCell))
			mapCellStyle.put(layoutCell, cellStyle);
		cell.setCellStyle(mapCellStyle.get(layoutCell));
	}

	/**
	 * Gets the cell style header.
	 *
	 * @param workbook       the workbook
	 * @param sheet          the sheet
	 * @param sheetComponent the sheet component
	 * @param rowHeader      the row header
	 * @return the cell style header
	 * @throws Exception the exception
	 */
	public CellStyle getCellStyleHeader(Workbook workbook, Sheet sheet, SheetComponent sheetComponent, Row rowHeader) throws Exception {
		ExcelHeaderLayout layoutHeader = ExcelUtils.getAnnotation(sheetComponent.getClass(), ExcelHeaderLayout.class);
		ExcelMarginSheet excelMarginSheet = ExcelUtils.getAnnotation(sheetComponent.getClass(), ExcelMarginSheet.class);
		ExcelSheetLayout layoutSheet = ExcelUtils.getAnnotation(sheetComponent.getClass(), ExcelSheetLayout.class);
		sheet.setMargin(Sheet.LeftMargin, excelMarginSheet.left());
		sheet.setMargin(Sheet.RightMargin, excelMarginSheet.right());
		sheet.setMargin(Sheet.TopMargin, excelMarginSheet.top());
		sheet.setMargin(Sheet.BottomMargin, excelMarginSheet.bottom());
		if (layoutSheet.scale() != (short) 100)
			sheet.getPrintSetup().setScale(layoutSheet.scale());
		sheet.getPrintSetup().setLandscape(layoutSheet.landscape());
		rowHeader.setHeight(ExcelUtils.rowHeight(layoutHeader.rowHeight()));

//		worksheet.setDefaultColumnWidth(5 * layoutHeader.cmWidthCell());
//		worksheet.setDefaultRowHeight((short)(layoutHeader.cmHeightCell() * 568));
		if (layoutSheet.order() > -1)
			workbook.setSheetOrder(sheet.getSheetName(), layoutSheet.order());
		return manageCellStyleHeader(workbook, layoutHeader);
	}

	/**
	 * Manage cell style header.
	 *
	 * @param workbook     the workbook
	 * @param layoutHeader the layout header
	 * @return the cell style
	 */
	private CellStyle manageCellStyleHeader(Workbook workbook, ExcelHeaderLayout layoutHeader) {
		return manageCellStyleHeader(workbook, layoutHeader.excelHeaderCellLayout());
	}

	/**
	 * Manage cell style header.
	 *
	 * @param workbook              the workbook
	 * @param excelHeaderCellLayout the excel header cell layout
	 * @return the cell style
	 */
	private CellStyle manageCellStyleHeader(Workbook workbook, ExcelHeaderCellLayout excelHeaderCellLayout) {
		LayoutCell layoutCellHeader = ExcelUtils.reflectionAnnotation(new LayoutCell(), excelHeaderCellLayout);
		if (!this.mapCellHeaderStyle.containsKey(layoutCellHeader))
			this.mapCellHeaderStyle.put(layoutCellHeader, createCellStyle(workbook, excelHeaderCellLayout));
		CellStyle cellStyleHeader = this.mapCellHeaderStyle.get(layoutCellHeader);
		return cellStyleHeader;
	}

	/**
	 * Manage cell style header.
	 *
	 * @param workbook     the workbook
	 * @param layoutHeader the layout header
	 * @return the cell style
	 */
	private CellStyle manageCellStyleHeader(Workbook workbook, ExcelSuperHeaderCell layoutHeader) {
		return manageCellStyleHeader(workbook, layoutHeader.excelHeaderCellLayout());
	}

	/**
	 * Generate header sheet data.
	 *
	 * @param <T>       the generic type
	 * @param workbook  the workbook
	 * @param sheet     the sheet
	 * @param sheetData the sheet data
	 * @param indexRow  the index row
	 * @return the list
	 * @throws Exception the exception
	 */
	protected <T extends RowSheet> List<SheetHeader> generateHeaderSheetData(Workbook workbook, Sheet sheet, SheetData<T> sheetData, Integer indexRow) throws Exception {

		ExcelSheetLayout excelSheetLayout = ExcelUtils.getAnnotation(sheetData.getClass(), ExcelSheetLayout.class);
		List<SheetHeader> listSheetHeader = this.getListSheetHeader(sheetData.getRowClass(), null, sheet);
		if (sheetData instanceof DynamicColumn) {
			DynamicColumn sheetDynamicData = (DynamicColumn) sheetData;
			for (Entry<String, ExtraColumnAnnotation> entry : sheetDynamicData.getMapExtraColumnAnnotation().entrySet()) {

				ExtraColumnAnnotation extraColumnAnnotation = entry.getValue();
				if (!extraColumnAnnotation.getExcelColumn().ignore()) {
					SheetHeader sheetHeader = new SheetHeader();
					if (extraColumnAnnotation.getExcelCellLayout() == null)
						throw new ExcelGeneratorException("Annotation " + ExcelCellLayout.class.getSimpleName() + " is not presented on " + ExtraColumnAnnotation.class.getSimpleName());
					sheetHeader.setExcelCellLayout(extraColumnAnnotation.getExcelCellLayout());
					if (extraColumnAnnotation.getExcelColumn() == null)
						throw new ExcelGeneratorException("Annotation " + ExcelColumn.class.getSimpleName() + " is not presented on " + ExtraColumnAnnotation.class.getSimpleName());
					PropertyUtils.copyProperties(sheetHeader, extraColumnAnnotation);
					sheetHeader.setKeyMap(entry.getKey());
					listSheetHeader.add(sheetHeader);
				}
			}
			Collections.sort(listSheetHeader, new SheetColumnComparator());
		}
		Integer indexStartSuperHeader = indexRow - getSizeSuperHeader(sheetData);
		Row rowHeader = null;
		CellStyle cellStyleHeader = null;
		if (excelSheetLayout.showHeader()) {
			rowHeader = sheet.createRow(indexRow);
			cellStyleHeader = getCellStyleHeader(workbook, sheet, sheetData, rowHeader);

		}
		int maxColumn = listSheetHeader.size() + excelSheetLayout.startColumn();
		for (int columnNum = excelSheetLayout.startColumn(); columnNum < maxColumn; columnNum++) {
			int indexHeader = columnNum - excelSheetLayout.startColumn();
			SheetHeader sheetHeader = listSheetHeader.get(indexHeader);
			Integer indexRowHeader = null;
			if (excelSheetLayout.showHeader()) {
				indexRowHeader = indexRow;
				Cell cellHeader = rowHeader.createCell(columnNum);
				if (sheetHeader.getField() != null && sheetHeader.getField().isAnnotationPresent(ExcelHeaderCellLayout.class) || sheetHeader.getExcelHeaderCellLayout() != null) {
					ExcelHeaderCellLayout layoutHeader = null;
					if (sheetHeader.getExcelHeaderCellLayout() != null)
						layoutHeader = sheetHeader.getExcelHeaderCellLayout();
					else
						layoutHeader = ExcelUtils.getAnnotation(sheetHeader.getField(), ExcelHeaderCellLayout.class);
					CellStyle differentCellStyleHeader = manageCellStyleHeader(workbook, layoutHeader);
					cellHeader.setCellStyle(differentCellStyleHeader);
				} else
					cellHeader.setCellStyle(cellStyleHeader);

				ExcelColumn excelColumn = listSheetHeader.get(indexHeader).getExcelColumn();
				setColumnWidth(sheet, columnNum, sheetHeader.getExcelColumnWidth().width());
				listSheetHeader.get(indexHeader).setNumColumn(columnNum);
				cellHeader.setCellValue(this.valueProps.valueProps(excelColumn.columnName()));
				if (StringUtils.isNoneBlank(excelColumn.comment()))
					addComment(workbook, sheet, rowHeader, cellHeader, excelColumn.comment());
			}
			InfoColumn infoColumn = new InfoColumn(sheet, sheetHeader, columnNum, indexRowHeader);
			String key = null;
			if (sheetHeader.getField() != null)
				key = ExcelUtils.getKeyColumn(sheet, sheetHeader.getField().getName());
			else if (StringUtils.isNotBlank(sheetHeader.getKeyMap()))
				key = ExcelUtils.getKeyColumn(sheet, sheetHeader.getKeyMap());
			else if (sheetHeader.getExcelFunction() != null)
				key = ExcelUtils.getKeyColumn(sheet, sheetHeader.getExcelFunction().nameFunction());
			sheetHeader.setKey(key);
			this.mapFieldColumn.put(key, infoColumn);
		}

		if (sheetData.getClass().isAnnotationPresent(ExcelSuperHeaders.class)) {
			ExcelSuperHeaders excelSuperHeaders = sheetData.getClass().getAnnotation(ExcelSuperHeaders.class);
			for (ExcelSuperHeader superHeader : excelSuperHeaders.superHeaders()) {
				Row rowSuperHeader = sheet.createRow(indexStartSuperHeader);
				rowSuperHeader.setHeight(ExcelUtils.rowHeight(superHeader.rowHeight()));
				for (ExcelSuperHeaderCell headerGroup : superHeader.headerGroups()) {
					String function = headerGroup.columnRange().replace("${", "").replace("}", "");
					String[] columns = function.split(":");
					Cell cellSuperHeader = null;
					boolean setCellValue = true;
					int startColumn = this.mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, columns[0])).getColumnNum();
					int endColumn = this.mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, columns[1])).getColumnNum();

					// for (String column : columns) {
					for (int columnNum = startColumn; columnNum <= endColumn; columnNum++) {
//						String key = ExcelUtils.getKeyColumn(worksheet, column);
//						int columnNum = this.mapFieldColumn.get(key).getColumnNum();
						cellSuperHeader = rowSuperHeader.createCell(columnNum);
						CellStyle cellSuperHeaderStyle = manageCellStyleHeader(workbook, headerGroup);
						cellSuperHeader.setCellStyle(cellSuperHeaderStyle);
						if (setCellValue)
							cellSuperHeader.setCellValue(this.valueProps.valueProps(headerGroup.columnName()));
						setCellValue = false;
					}

					String mergeCell = this.buildFunction(sheet, indexStartSuperHeader, headerGroup.columnRange(), RowStartEndType.ROW_EMPTY);
					sheet.addMergedRegion(CellRangeAddress.valueOf(mergeCell));

				}
				indexStartSuperHeader++;
			}
		}

		return listSheetHeader;
	}

	/**
	 * Gets the size super header.
	 *
	 * @param <T>       the generic type
	 * @param sheetData the sheet data
	 * @return the size super header
	 */
	protected <T extends RowSheet> int getSizeSuperHeader(SheetData<T> sheetData) {
		int sizeSuperHeader = 0;
		if (sheetData.getClass().isAnnotationPresent(ExcelSuperHeaders.class)) {
			ExcelSuperHeaders excelSuperHeaders = sheetData.getClass().getAnnotation(ExcelSuperHeaders.class);
			sizeSuperHeader = excelSuperHeaders.superHeaders().length;

		}
		return sizeSuperHeader;
	}

	/**
	 * Sets the column width.
	 *
	 * @param sheet       the sheet
	 * @param indexColumn the index column
	 * @param width       the width
	 * @throws Exception the exception
	 */
	protected void setColumnWidth(Sheet sheet, Integer indexColumn, Integer width) throws Exception {
		if (!this.mapWidthColumn.containsKey(indexColumn) || this.mapWidthColumn.get(indexColumn) < width) {
			this.mapWidthColumn.put(Integer.valueOf(indexColumn), Integer.valueOf(width));
			sheet.setColumnWidth(indexColumn, ExcelUtils.widthColumn(width));
		}
	}

	/**
	 * Creates the pivot.
	 *
	 * @param sheet       the sheet
	 * @param sheetData   the sheet data
	 * @param firstRow    the first row
	 * @param firstColumn the first column
	 * @param lastRow     the last row
	 * @param lastColumn  the last column
	 * @param indexRow    the index row
	 * @return the integer
	 */
	protected Integer createPivot(XSSFSheet sheet, SheetData<?> sheetData, int firstRow, int firstColumn, int lastRow, int lastColumn, Integer indexRow) {
		Set<Field> listField = ExcelUtils.getListField(sheetData.getRowClass());
		String startCell = ExcelUtils.coordinateCalculation(firstRow, firstColumn, true, true);
		String endCell = ExcelUtils.coordinateCalculation(lastRow, lastColumn, true, true);
		AreaReference areaReference = new AreaReference(startCell + ":" + endCell, SpreadsheetVersion.EXCEL2007);
		ExcelPivot excelPivot = sheetData.getClass().getAnnotation(ExcelPivot.class);
		indexRow += 3;
		XSSFPivotTable pivotTable = sheet.createPivotTable(areaReference, new CellReference(indexRow, excelPivot.startColumn()));
		List<Field> listRow = new ArrayList<>();
		List<Field> listColumn = new ArrayList<>();
		List<Field> listColumnFunction = new ArrayList<>();
		for (Field field : listField) {
			int columnIndex = this.mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			if (field.isAnnotationPresent(ExcelPivotFilter.class))
				pivotTable.addReportFilter(columnIndex);
			if (field.isAnnotationPresent(ExcelPivotRow.class))
				listRow.add(field);
			if (field.isAnnotationPresent(ExcelPivotColumn.class))
				listColumn.add(field);
			if (field.isAnnotationPresent(ExcelPivotColumnFunction.class))
				listColumnFunction.add(field);
		}
		Collections.sort(listRow, new PivotRowComparator());
		Collections.sort(listColumn, new PivotColumnComparator());
		Collections.sort(listColumnFunction, new PivotColumnFunctionComparator());

		for (Field field : listRow) {
			int columnIndex = this.mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			pivotTable.addRowLabel(columnIndex);
		}
		for (Field field : listColumn) {
			int columnIndex = this.mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			pivotTable.addColLabel(columnIndex);
		}
		for (Field field : listColumnFunction) {
			int columnIndex = this.mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, field.getName())).getColumnNum();
			ExcelPivotColumnFunction excelPivotColumnFunction = field.getAnnotation(ExcelPivotColumnFunction.class);
			for (DataConsolidateFunction dataConsolidateFunction : excelPivotColumnFunction.dataConsolidateFunction())
				pivotTable.addColumnLabel(dataConsolidateFunction, columnIndex);

		}
		return indexRow;

	}

	/**
	 * Manage drop down.
	 *
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param firstRow    the first row
	 * @param lastRow     the last row
	 * @param firstCol    the first col
	 * @param lastCol     the last col
	 */
	protected void manageDropDown(Sheet sheet, SheetHeader sheetHeader, int firstRow, int lastRow, int firstCol, int lastCol, Integer indexRow) {
		DropDownCell dropDownCell = null;
		dropDownCell = new DropDownCell(sheet, sheetHeader, firstRow, lastRow, firstCol, lastCol, indexRow);
		try {
			this.addDropDown(dropDownCell);
		} catch (Exception e) {
			this.listDropDown.add(dropDownCell);
		}
	}

	/**
	 * Adds the drop down.
	 *
	 * @param dropDownCell the drop down cell
	 * @throws Exception the exception
	 */
	protected void addDropDown(DropDownCell dropDownCell) throws Exception {
		SheetHeader sheetHeader = dropDownCell.getSheetHeader();
		Sheet sheet = dropDownCell.getSheet();
		if (sheetHeader.getExcelDropDown() != null || (sheetHeader.getField() != null && sheetHeader.getValue() != null && DropDown.class.isAssignableFrom(sheetHeader.getField().getType()))) {
			DataValidationConstraint constraint = null;
			DataValidation dataValidation = null;
			DataValidationHelper validationHelper = sheet.getDataValidationHelper();
			CellRangeAddressList addressList = new CellRangeAddressList(dropDownCell.getFirstRow(), dropDownCell.getLastRow(), dropDownCell.getFirstCol(), dropDownCell.getLastCol());
			if (sheetHeader.getExcelDropDown() != null) {
				ExcelDropDown excelDropDown = sheetHeader.getExcelDropDown();
				String areaRange = excelDropDown.areaRange();
				areaRange = buildFunction(sheet, dropDownCell.getIndexRow(), areaRange, RowStartEndType.ROW_EMPTY, excelDropDown.alias());
				areaRange = buildFunction(sheet, null, areaRange, RowStartEndType.ROW_START, excelDropDown.alias());
				areaRange = buildFunction(sheet, null, areaRange, RowStartEndType.ROW_END, excelDropDown.alias());

				Pattern p = Pattern.compile(PATTERN);
				Matcher m = p.matcher(areaRange);
				if (m.find())
					throw new ExcelGeneratorException("The formula '" + areaRange + "' is not valid");
				constraint = validationHelper.createFormulaListConstraint(areaRange);
				dataValidation = validationHelper.createValidation(constraint, addressList);
				dataValidation.setSuppressDropDownArrow(excelDropDown.suppressDropDownArrow());
				if (excelDropDown.errorBox().show()) {
					dataValidation.setShowErrorBox(excelDropDown.errorBox().show());
					dataValidation.createErrorBox(excelDropDown.errorBox().title(), excelDropDown.errorBox().message());
					dataValidation.setErrorStyle(excelDropDown.errorBox().boxStyle().getValue());
				}

			} else {
				DropDown<?> dropDown = (DropDown<?>) sheetHeader.getValue();
				if (CollectionUtils.isNotEmpty(dropDown.getList())) {
					String[] list = new String[dropDown.getList().size()];
					int i = 0;
					SimpleDateFormat sdf = null;
					if (sheetHeader.getExcelDate() != null)
						sdf = new SimpleDateFormat(sheetHeader.getExcelDate().format().getValue());
					for (Object item : dropDown.getList()) {
						if (item instanceof Date)
							list[i] = sdf.format((Date) item);
						else if (item instanceof Calendar)
							list[i] = sdf.format(((Calendar) item).getTime());
						else if (item instanceof Timestamp)
							list[i] = sdf.format(new Date(((Timestamp) item).getTime()));
						else
							list[i] = item.toString();

						i++;
					}
					constraint = validationHelper.createExplicitListConstraint(list);
					dataValidation = validationHelper.createValidation(constraint, addressList);
					dataValidation.setSuppressDropDownArrow(dropDown.isSuppressDropDownArrow());
					if (dropDown.getBoxMessage() != null) {
						BoxMessage boxMessage = dropDown.getBoxMessage();
						dataValidation.setShowErrorBox(boxMessage.isShow());
						dataValidation.createErrorBox(boxMessage.getTitle(), boxMessage.getMessage());
						dataValidation.setErrorStyle(boxMessage.getBoxStyle().getValue());
					}

				}

			}

			sheet.addValidationData(dataValidation);

		}
	}

	/**
	 * Adds the image.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param cell        the cell
	 * @throws Exception the exception
	 */
	private void addImage(Workbook workbook, Sheet sheet, SheetHeader sheetHeader, Cell cell) throws Exception {
		ExcelImage excelImage = sheetHeader.getExcelImage();
		int pictureureIdx = workbook.addPicture((byte[]) sheetHeader.getValue(), excelImage.pictureType().nativeId);
		CreationHelper helper = workbook.getCreationHelper();
		Drawing<?> drawing = sheet.createDrawingPatriarch();

		ClientAnchor anchor = helper.createClientAnchor();

		anchor.setCol1(cell.getColumnIndex());
		anchor.setRow1(cell.getRowIndex());
		anchor.setCol2(cell.getColumnIndex() + 1);
		anchor.setRow2(cell.getRowIndex() + 1);
		anchor.setAnchorType(excelImage.anchorType());

		Picture pict = drawing.createPicture(anchor, pictureureIdx);
		pict.resize(excelImage.resizeWidth(), excelImage.resizeHeight());

	}

	/**
	 * Manage excel image.
	 *
	 * @param sheetHeader the sheet header
	 * @param value       the value
	 * @return the object
	 * @throws Exception             the exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException           Signals that an I/O exception has occurred.
	 */
	protected Object manageExcelImage(SheetHeader sheetHeader, Object value) throws Exception, FileNotFoundException, IOException {
		if (sheetHeader.getExcelImage() != null) {
			value = manageExcelAttachment(value);
		}
		return value;
	}

	/**
	 * Manage excel attachment.
	 *
	 * @param value the value
	 * @return the byte[]
	 * @throws Exception             the exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException           Signals that an I/O exception has occurred.
	 */
	private byte[] manageExcelAttachment(Object value) throws Exception, FileNotFoundException, IOException {
		byte[] file = null;
		if (value != null) {
			if (!(value instanceof String || value instanceof byte[]))
				throw new ExcelGeneratorException("The annotation ExcelImage can to be used only with fields String or byte[] type");
			if (value instanceof String) {
				InputStream inputStream = new FileInputStream((String) value);
				file = IOUtils.toByteArray(inputStream);
			} else
				file = (byte[]) value;
		}
		return file;
	}

	/**
	 * Adds the attachment.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param cell        the cell
	 * @throws Exception the exception
	 */
	private void addAttachment(Workbook workbook, Sheet sheet, SheetHeader sheetHeader, Cell cell) throws Exception {
		if (sheetHeader.getValue() != null && sheetHeader.getValue() instanceof ExcelAttachment<?>) {
			ExcelAttachment<?> excelAttachment = (ExcelAttachment<?>) sheetHeader.getValue();
			byte[] file = manageExcelAttachment(excelAttachment.getAttachment());
			String fileNameExtension = excelAttachment.getFileName() + excelAttachment.getAttachmentType().getFileExtension();
			int storageId = workbook.addOlePackage(file, fileNameExtension, fileNameExtension, fileNameExtension);
			byte[] image = IOUtils.toByteArray(getClass().getResourceAsStream(excelAttachment.getAttachmentType().getImage()));
			int iconId = workbook.addPicture(image, PictureType.JPEG.nativeId);

			Drawing<?> drawing = sheet.createDrawingPatriarch();

//			ClientAnchor anchor = helper.createClientAnchor();
//
//			anchor.setCol1(cell.getColumnIndex());
//			anchor.setRow1(cell.getRowIndex());
			ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, cell.getColumnIndex(), cell.getRowIndex(), cell.getColumnIndex() + 1, cell.getRowIndex() + 1);
			anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

			// ObjectData objectData =drawing.createObjectData(anchor, storageId,
			// pictureureIdx);
			drawing.createObjectData(anchor, storageId, iconId);

		}

	}

}