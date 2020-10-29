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
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
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

import bld.generator.report.excel.DynamicColumn;
import bld.generator.report.excel.ExcelHyperlink;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.SheetComponent;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelDropDown;
import bld.generator.report.excel.annotation.ExcelFont;
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
import bld.generator.report.excel.comparator.PivotColumnComparator;
import bld.generator.report.excel.comparator.PivotColumnFunctionComparator;
import bld.generator.report.excel.comparator.PivotRowComparator;
import bld.generator.report.excel.comparator.SheetColumnComparator;
import bld.generator.report.excel.constant.ColumnDateFormat;
import bld.generator.report.excel.constant.RowStartEndType;
import bld.generator.report.excel.data.DropDownCell;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.data.FunctionCell;
import bld.generator.report.excel.data.InfoColumn;
import bld.generator.report.excel.data.LayoutCell;
import bld.generator.report.excel.data.MergeCell;
import bld.generator.report.excel.data.SheetHeader;
import bld.generator.report.excel.dropdown.DropDown;
import bld.generator.report.utils.ExcelUtils;
import bld.generator.report.utils.ValueProps;

/**
 * The Class SuperGenerateExcelImpl.
 */
@SuppressWarnings({ "deprecation" })
public class SuperGenerateExcelImpl {

	/** The Constant FLAT_ANGLE. */
	private static final int FLAT_ANGLE = 180;

	/** The Constant PATTERN. */
	private static final String PATTERN = "\\$\\{.*?}";

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
	
	protected List<DropDownCell> listDropDown = new ArrayList<>();

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
	 * @param workbook  the workbook
	 * @param layout    the layout
	 * @param excelDate the excel date
	 * @param indexRow  the index row
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout, ExcelDate excelDate, Integer indexRow) throws Exception {
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
				ExcelCellLayout excelCellLayout = ExcelUtils.getAnnotation(field, ExcelCellLayout.class);
				if (excelCellLayout.locked())
					sheet.protectSheet("");
				Object value = null;
				if (entity != null)
					value = PropertyUtils.getProperty(entity, field.getName());
				SheetHeader sheetHeader = new SheetHeader(field, value);
				if(value !=null) {
					value=manageExcelImage(sheetHeader, value);
					sheetHeader.setValue(value);
				}
				
				if (field.isAnnotationPresent(ExcelDropDown.class) && field.getClass().isAssignableFrom(DropDown.class))
					throw new Exception("The following annotation @ExcelDropDown can not be assigned on fields of classes type DropDown");
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
	protected boolean setCellValueWillMerged(Workbook workbook, CellStyle cellStyle, Cell cell, SheetHeader sheetHeader, Integer indexRow,Sheet sheet) throws Exception {
		this.setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow,sheet); // writeCellEmpty(workbook,
		// cellStyle,
		// cell,
		// sheetHeader);
		return false;
	}

	/**
	 * Merge row and remove map.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param indexRow    the index row
	 * @param mapMergeRow the map merge row
	 * @param numColumn   the num column
	 * @throws Exception the exception
	 */
	protected void mergeRowAndRemoveMap(Workbook workbook, Sheet sheet, Integer indexRow, Map<Integer, MergeCell> mapMergeRow, int numColumn) throws Exception {
		mergeRow(workbook, sheet, indexRow, mapMergeRow, numColumn);
		mapMergeRow.remove(numColumn);
	}

	/**
	 * Merge row.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param indexRow    the index row
	 * @param mapMergeRow the map merge row
	 * @param numColumn   the num column
	 * @throws Exception the exception
	 */
	protected void mergeRow(Workbook workbook, Sheet sheet, Integer indexRow, Map<Integer, MergeCell> mapMergeRow, int numColumn) throws Exception {
		MergeCell mergeRow = mapMergeRow.get(numColumn);
		this.manageDropDown(sheet, mergeRow.getSheetHeader(), mergeRow.getRowStart(), mergeRow.getRowStart(), numColumn, numColumn);
		mergeRow.setRowEnd(indexRow - 1);
		// setCellValueExcel(mergeRow.getCellFrom(),
		// mergeRow.getCellStyleFrom(),
		// mergeRow.getSheetHeader());
		runMergeCell(workbook, sheet, mergeRow);
	}

	/**
	 * Run merge cell.
	 *
	 * @param workbook  the workbook
	 * @param sheet     the sheet
	 * @param mergeCell the merge cell
	 * @throws Exception the exception
	 */
	protected void runMergeCell(Workbook workbook, Sheet sheet, MergeCell mergeCell) throws Exception {
		setCellValueExcel(workbook, sheet, mergeCell);
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
	 * @param excelSheetLayout 
	 *
	 * @param workbook     the workbook
	 * @param sheet        the sheet
	 * @param sheetSummary the sheet summary
	 * @param sheetHeader  the sheet header
	 * @param row          the row
	 * @param indexRow     the index row
	 * @throws Exception the exception
	 */
	protected void setCellSummary(ExcelSheetLayout excelSheetLayout, Workbook workbook, Sheet sheet, SheetSummary sheetSummary, SheetHeader sheetHeader, Row row, Integer indexRow) throws Exception {
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
		setCellStyleExcel(cellStyleColumn0, cellColumn0, layoutCellSummary, indexRow);
		cellColumn0.setCellValue(this.valueProps.valueProps(sheetHeader.getExcelColumn().columnName()));
		if (StringUtils.isNotBlank(sheetHeader.getExcelColumn().comment()))
			addComment(workbook, sheet, row, cellColumn0, sheetHeader.getExcelColumn().comment());
		ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
		ExcelDate excelDate = null;
		if (sheetHeader.getField() != null && (Date.class.isAssignableFrom(sheetHeader.getField().getType()) || Calendar.class.isAssignableFrom(sheetHeader.getField().getType()) || Timestamp.class.isAssignableFrom(sheetHeader.getField().getType())))
			excelDate = sheetHeader.getExcelDate();
		CellStyle cellStyleColumn1 = this.createCellStyle(workbook, excelCellLayout, excelDate, indexRow);
		int column=excelSheetLayout.startColumn()+1;
		Cell cellColumn1 = row.createCell(column);
		manageDropDown(sheet, sheetHeader, indexRow, indexRow, column, column);
		setCellValueExcel(workbook, sheet, cellColumn1, cellStyleColumn1, sheetHeader, cellColumn1.getRowIndex());
		// setCellValueExcel(workbook, cellColumn1, cellStyleColumn1, sheetHeader);

	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param cell        the cell
	 * @param cellStyle   the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Workbook workbook, Sheet sheet, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow) throws Exception {
		if (sheetHeader.getExcelFunction() != null) {
			try {
				setCellFormulaExcel(cell, cellStyle, sheetHeader, indexRow, sheet);
			} catch (Exception e) {
				FunctionCell functionCell = new FunctionCell();
				functionCell.setWorksheet(sheet);
				functionCell.setCell(cell);
				functionCell.setIndexRow(indexRow);
				functionCell.setSheetHeader(sheetHeader);
				this.listFunctionCell.add(functionCell);
			}
		} else
			setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow,sheet);

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param cell        the cell
	 * @param cellStyle   the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @param sheet       the sheet
	 * @throws Exception the exception
	 */
	protected void setCellFormulaExcel(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, Sheet sheet) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell, indexRow);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = makeFunction(sheet, indexRow, function, RowStartEndType.ROW_EMPTY);
		if (excelFunction.anotherTable()) {
			function = makeFunction(sheet, null, function, RowStartEndType.ROW_START);
			function = makeFunction(sheet, null, function, RowStartEndType.ROW_END);
		}
		function = makeFunction(sheet, indexRow, function, RowStartEndType.ROW_START);
		function = makeFunction(sheet, indexRow, function, RowStartEndType.ROW_END);
		function = makeFunction(sheet, indexRow, function, RowStartEndType.ROW_HEADER);
		logger.debug("Function: " + function);
		// cell.setCellType(CellType.FORMULA);
		cell.setCellFormula(function);

	}

	/**
	 * Make function.
	 *
	 * @param sheet           the sheet
	 * @param indexRow        the index row
	 * @param function        the function
	 * @param rowStartEndType the row start end type
	 * @return the string
	 * @throws Exception the exception
	 */
	protected String makeFunction(Sheet sheet, Integer indexRow, String function, RowStartEndType rowStartEndType) throws Exception {
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(function);
		while (m.find()) {
			String parameter = m.group();
			String keyParameter = parameter.replace($, "").replace(rowStartEndType.getValue() + "}", "").trim();
			if (mapFieldColumn.containsKey(ExcelUtils.getKeyColumn(sheet, keyParameter))) {
				InfoColumn infoColumn = (InfoColumn) mapFieldColumn.get(ExcelUtils.getKeyColumn(sheet, keyParameter));
				Integer row = indexRow;

				if (RowStartEndType.ROW_HEADER.equals(rowStartEndType)) {
					row = infoColumn.getRowHeader();
					if (row == null)
						throw new Exception("The header not exist");
				} else if (RowStartEndType.ROW_EMPTY.equals(rowStartEndType) && !infoColumn.getMapRowMergeRow().isEmpty())
					row = infoColumn.getMapRowMergeRow().get(indexRow).getRowStart();
				else if (row == null && RowStartEndType.ROW_START.equals(rowStartEndType))
					row = infoColumn.getFirstRow();
				else if (row == null && RowStartEndType.ROW_END.equals(rowStartEndType))
					row = infoColumn.getLastRow();
				if (keyParameter.contains(".")) {
					String sheetName = keyParameter.substring(0, keyParameter.lastIndexOf("."));
					function = function.replace(parameter, "'" + sheetName.replace("'", "''") + "'!" + ExcelUtils.calcoloCoordinateFunction(row + 1, infoColumn.getColumnNum()));
				} else
					function = function.replace(parameter, ExcelUtils.calcoloCoordinateFunction(row + 1, infoColumn.getColumnNum()));
			}

		}
		return function;
	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param workbook the workbook
	 * @param sheet    the sheet
	 * @param mergeRow the merge row
	 * @throws Exception the exception
	 */
	private void setCellValueExcel(Workbook workbook, Sheet sheet, MergeCell mergeRow) throws Exception {
		if (mergeRow.getSheetHeader().getExcelFunction() != null)
			try {
				setCellFormulaExcel(sheet, mergeRow, 0);
			} catch (Exception e) {
				FunctionCell functionCell = new FunctionCell();
				functionCell.setWorksheet(sheet);
				functionCell.setMergeRow(mergeRow);
				listFunctionCell.add(functionCell);
			}
		else
			setCellValueExcel(workbook, mergeRow.getCellFrom(), mergeRow.getCellStyleFrom(), mergeRow.getSheetHeader(), 0,sheet);

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param sheet    the sheet
	 * @param mergeRow the merge row
	 * @param indexRow the index row
	 * @throws Exception the exception
	 */
	protected void setCellFormulaExcel(Sheet sheet, MergeCell mergeRow, Integer indexRow) throws Exception {
		SheetHeader sheetHeader = mergeRow.getSheetHeader();
		CellStyle cellStyle = mergeRow.getCellStyleFrom();
		Cell cell = mergeRow.getCellFrom();
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell, indexRow);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = makeFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_EMPTY);
		if (excelFunction.anotherTable()) {
			function = makeFunction(sheet, null, function, RowStartEndType.ROW_START);
			function = makeFunction(sheet, null, function, RowStartEndType.ROW_END);
		}

		function = makeFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_START);
		function = makeFunction(sheet, mergeRow.getRowEnd(), function, RowStartEndType.ROW_END);
		function = makeFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_HEADER);
		logger.debug("Function: " + function);

		// cell.setCellType(CellType.FORMULA);
		cell.setCellFormula(function);
	}

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
	protected void setCellValueExcel(Workbook workbook, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow,Sheet sheet) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		layoutCell.setColor(indexRow);
		if (cellStyle == null) {
			if (this.mapCellStyle.containsKey(layoutCell))
				cellStyle = this.mapCellStyle.get(layoutCell);
			else
				cellStyle = createCellStyle(workbook, sheetHeader.getExcelCellLayout(), sheetHeader.getExcelDate(), indexRow);
		}
		setCellStyleExcel(cellStyle, cell, layoutCell, indexRow);
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
		else if (sheetHeader.getValue() instanceof Boolean)
			cell.setCellValue((Boolean) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof ExcelHyperlink) {
			ExcelHyperlink excelHyperlink = (ExcelHyperlink) sheetHeader.getValue();
			CreationHelper createHelper = workbook.getCreationHelper();
			if (excelHyperlink.getHyperlinkType() == null)
				throw new Exception("The field hyperlinkType is null");
			if (StringUtils.isEmpty(excelHyperlink.getAddress()))
				throw new Exception("The field address is null or is empty");
			Hyperlink hyperlink = createHelper.createHyperlink(excelHyperlink.getHyperlinkType());
			String address = excelHyperlink.getAddress();
			if (HyperlinkType.DOCUMENT.equals(excelHyperlink.getHyperlinkType()))
				address = excelHyperlink.getAddressDocument();
			hyperlink.setAddress(address);
			cell.setHyperlink(hyperlink);
			cell.setCellValue(excelHyperlink.getValue());
		} else if (sheetHeader.getValue() instanceof byte[])
			this.addImage(workbook, sheet, sheetHeader, cell);
		else if (sheetHeader.getValue() instanceof DropDown<?>) {
			DropDown<?> dropDown = (DropDown<?>) sheetHeader.getValue();
			sheetHeader.setValue(dropDown.getValue());
			setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow,sheet);
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
	 * @param indexRow   the index row
	 */
	protected void setCellStyleExcel(CellStyle cellStyle, Cell cell, LayoutCell layoutCell, Integer indexRow) {
		layoutCell.setColor(indexRow);
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
		sheet.getPrintSetup().setLandscape(layoutSheet.landscape());
		rowHeader.setHeight(ExcelUtils.rowHeight(layoutHeader.rowHeight()));
		if (layoutHeader.excelHeaderCellLayout().locked())
			sheet.protectSheet("");
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
			for (String keyMap : sheetDynamicData.getMapExtraColumnAnnotation().keySet()) {

				ExtraColumnAnnotation extraColumnAnnotation = sheetDynamicData.getMapExtraColumnAnnotation().get(keyMap);
				if (!extraColumnAnnotation.getExcelColumn().ignore()) {
					SheetHeader sheetHeader = new SheetHeader();
					if (extraColumnAnnotation.getExcelCellLayout() == null)
						throw new Exception("Annotation " + ExcelCellLayout.class.getSimpleName() + " is not presented on " + ExtraColumnAnnotation.class.getSimpleName());
					sheetHeader.setExcelCellLayout(extraColumnAnnotation.getExcelCellLayout());
					if (extraColumnAnnotation.getExcelColumn() == null)
						throw new Exception("Annotation " + ExcelColumn.class.getSimpleName() + " is not presented on " + ExtraColumnAnnotation.class.getSimpleName());
					sheetHeader.setExcelColumn(extraColumnAnnotation.getExcelColumn());
					sheetHeader.setExcelDate(extraColumnAnnotation.getExcelDate());
					sheetHeader.setExcelMergeRow(extraColumnAnnotation.getExcelMergeRow());
					sheetHeader.setExcelHeaderCellLayout(extraColumnAnnotation.getExcelHeaderCellLayout());
					sheetHeader.setExcelColumnWidth(extraColumnAnnotation.getExcelColumnWidth());
					if (extraColumnAnnotation.getExcelDropDown() != null)
						sheetHeader.setExcelDropDown(extraColumnAnnotation.getExcelDropDown());
					if (extraColumnAnnotation.getExcelFunction() != null)
						sheetHeader.setExcelFunction(extraColumnAnnotation.getExcelFunction());

					sheetHeader.setKeyMap(keyMap);
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
					if (layoutHeader.locked())
						sheet.protectSheet("");
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

					String mergeCell = this.makeFunction(sheet, indexStartSuperHeader, headerGroup.columnRange(), RowStartEndType.ROW_EMPTY);
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
			this.mapWidthColumn.put(new Integer(indexColumn), new Integer(width));
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
		String startCell = ExcelUtils.calcoloCoordinateFunction(firstRow, firstColumn);
		String endCell = ExcelUtils.calcoloCoordinateFunction(lastRow, lastColumn);
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
	
	protected void manageDropDown(Sheet sheet, SheetHeader sheetHeader, int firstRow, int lastRow, int firstCol, int lastCol) {
		DropDownCell dropDownCell=null;
		dropDownCell=new DropDownCell(sheet, sheetHeader, firstRow, lastRow, firstCol, lastCol);
		try {
			this.addDropDown(dropDownCell);
		} catch (Exception e) {
			this.listDropDown.add(dropDownCell);
		}
	}
	

	/**
	 * Adds the drop down.
	 *
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param firstRow    the first row
	 * @param lastRow     the last row
	 * @param firstCol    the first col
	 * @param lastCol     the last col
	 * @throws Exception the exception
	 */
	protected void addDropDown(DropDownCell dropDownCell) throws Exception {
		SheetHeader sheetHeader=dropDownCell.getSheetHeader();
		Sheet sheet=dropDownCell.getSheet();
		if (sheetHeader.getExcelDropDown() != null || (sheetHeader.getField() != null && sheetHeader.getValue() != null && DropDown.class.isAssignableFrom(sheetHeader.getField().getType()))) {
			DataValidationConstraint constraint = null;
			DataValidation dataValidation = null;
			DataValidationHelper validationHelper = sheet.getDataValidationHelper();
			CellRangeAddressList addressList = new CellRangeAddressList(dropDownCell.getFirstRow(), dropDownCell.getLastRow(), dropDownCell.getFirstCol(), dropDownCell.getLastCol());
			if (sheetHeader.getExcelDropDown() != null) {
				String areaRange = sheetHeader.getExcelDropDown().areaRange();
				areaRange = makeFunction(sheet, null, areaRange, RowStartEndType.ROW_START);
				areaRange = makeFunction(sheet, null, areaRange, RowStartEndType.ROW_END);
				Pattern p = Pattern.compile(PATTERN);
				Matcher m = p.matcher(areaRange);
				if (m.find())
					throw new Exception("The formula '"+areaRange+"' is not valid");
				constraint = validationHelper.createFormulaListConstraint(areaRange);
				dataValidation = validationHelper.createValidation(constraint, addressList);
				dataValidation.setSuppressDropDownArrow(sheetHeader.getExcelDropDown().suppressDropDownArrow());
			} else {
				DropDown<?> dropDown = (DropDown<?>) sheetHeader.getValue();
				if (CollectionUtils.isNotEmpty(dropDown.getList())) {
					String[] list = new String[dropDown.getList().size()];
					int i = 0;
					SimpleDateFormat sdf = new SimpleDateFormat(sheetHeader.getExcelDate().format().getValue());
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
	private void addImage(Workbook workbook, Sheet sheet, SheetHeader sheetHeader, Cell cell) throws Exception{
		
		ExcelImage excelImage=sheetHeader.getExcelImage();
		
		int pictureureIdx = workbook.addPicture((byte[])sheetHeader.getValue(), excelImage.pictureType().nativeId);
		CreationHelper helper = workbook.getCreationHelper();
		Drawing<?> drawing = sheet.createDrawingPatriarch();

		ClientAnchor anchor = helper.createClientAnchor();
        
		anchor.setCol1(cell.getColumnIndex());
		anchor.setRow1(cell.getRowIndex());
		anchor.setAnchorType(excelImage.anchorType());
		
		Picture pict = drawing.createPicture(anchor, pictureureIdx);
		pict.resize( excelImage.resizeWidth(),excelImage.resizeHeight());
		
	}
	
	protected Object manageExcelImage(SheetHeader sheetHeader, Object value) throws Exception, FileNotFoundException, IOException {
		if(sheetHeader.getExcelImage()!=null) {
			if(!(value instanceof String || value instanceof byte[]))
				throw new Exception("The annotation ExcelImage can to be used only with fields String or byte[] type");
			if(value instanceof String) {
				InputStream inputStream=new FileInputStream((String)value);
				value=IOUtils.toByteArray(inputStream);
			}
		}
		return value;
	}

}