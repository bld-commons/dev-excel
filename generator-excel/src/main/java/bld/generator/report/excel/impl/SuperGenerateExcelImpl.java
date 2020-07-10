/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.impl.SuperGenerateExcelImpl.java
*/
package bld.generator.report.excel.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
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
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelFunction;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import bld.generator.report.excel.annotation.ExcelSuperHeaderCell;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.annotation.ExcelRowHeight;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSummary;
import bld.generator.report.excel.annotation.ExcelSuperHeader;
import bld.generator.report.excel.annotation.ExcelSuperHeaders;
import bld.generator.report.excel.comparator.SheetColumnComparator;
import bld.generator.report.excel.constant.ColumnDateFormat;
import bld.generator.report.excel.constant.RowStartEndType;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.data.FunctionCell;
import bld.generator.report.excel.data.InfoColumn;
import bld.generator.report.excel.data.LayoutCell;
import bld.generator.report.excel.data.MergeCell;
import bld.generator.report.excel.data.SheetHeader;
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
			short colorFont = 20;

			paletteFont.setColorAtIndex(colorFont, rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((HSSFFont) font).setColor(colorFont);
			short colorForeground = 45;

			HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
			palette.setColorAtIndex(colorForeground, rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			cellStyleHeader.setFillForegroundColor(colorForeground);

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
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout) throws Exception {
		return createCellStyle(workbook, layout, null);
	}

	/**
	 * Creates the cell style.
	 *
	 * @param workbook  the workbook
	 * @param layout    the layout
	 * @param excelDate the excel date
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout, ExcelDate excelDate) throws Exception {
		CellStyle cellStyle = workbook.createCellStyle();
		ExcelRgbColor rgbFont = layout.rgbFont();
		ExcelRgbColor rgbForeground = layout.rgbForeground();
		Font font = getFont(workbook, layout.font());
		if (workbook instanceof HSSFWorkbook) {
			HSSFPalette paletteFont = ((HSSFWorkbook) workbook).getCustomPalette();
			short colorFont = 20;

			paletteFont.setColorAtIndex(colorFont, rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((HSSFFont) font).setColor(colorFont);
			short colorForeground = 45;

			HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
			palette.setColorAtIndex(colorForeground, rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			cellStyle.setFillForegroundColor(colorForeground);

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
	 * @param classRow  the class row
	 * @param entity    the entity
	 * @param worksheet the worksheet
	 * @return the list sheet header
	 * @throws Exception the exception
	 */
	protected List<SheetHeader> getListSheetHeader(Class<?> classRow, Object entity, Sheet worksheet) throws Exception {
		logger.debug("Row: " + classRow.getSimpleName());
		Set<String> listTitle = new HashSet<>();
		List<SheetHeader> listSheetHeader = new ArrayList<>();
		Set<Field> listField = ExcelUtils.getListField(classRow);
		for (Field field : listField) {
			ExcelColumn column = field.getAnnotation(ExcelColumn.class);
			if (column != null && !column.ignore()) {
				ExcelCellLayout excelCellLayout = ExcelUtils.getAnnotation(field, ExcelCellLayout.class);
				if (excelCellLayout.locked())
					worksheet.protectSheet("");
				Object value = null;
				if (entity != null)
					value = new PropertyDescriptor(field.getName(), classRow).getReadMethod().invoke(entity);
				listSheetHeader.add(new SheetHeader(field, value));
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
				listSheetHeader.add(sheetHeader);

			}
			for (ExcelFunctionMergeRow excelFunctionMerge : excelFunctionRows.excelFunctionMerges()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunctionMerge.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunctionMerge.excelCellsLayout());
				sheetHeader.setExcelFunction(excelFunctionMerge.excelFunction());
				sheetHeader.setExcelMergeRow(excelFunctionMerge.excelMergeRow());
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
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	protected boolean setCellValueWillMerged(Workbook workbook, CellStyle cellStyle, Cell cell, SheetHeader sheetHeader) throws Exception {
		this.setCellValueExcel(workbook, cell, cellStyle, sheetHeader); // writeCellEmpty(workbook,
																		// cellStyle,
																		// cell,
																		// sheetHeader);
		return false;
	}

	/**
	 * Merge row and remove map.
	 *
	 * @param workbook    the workbook
	 * @param worksheet   the worksheet
	 * @param indexRow    the index row
	 * @param mapMergeRow the map merge row
	 * @param numColumn   the num column
	 * @throws Exception the exception
	 */
	protected void mergeRowAndRemoveMap(Workbook workbook, Sheet worksheet, Integer indexRow, Map<Integer, MergeCell> mapMergeRow, int numColumn) throws Exception {
		mergeRow(workbook, worksheet, indexRow, mapMergeRow, numColumn);
		mapMergeRow.remove(numColumn);
	}

	/**
	 * Merge row.
	 *
	 * @param workbook    the workbook
	 * @param worksheet   the worksheet
	 * @param indexRow    the index row
	 * @param mapMergeRow the map merge row
	 * @param numColumn   the num column
	 * @throws Exception the exception
	 */
	protected void mergeRow(Workbook workbook, Sheet worksheet, Integer indexRow, Map<Integer, MergeCell> mapMergeRow, int numColumn) throws Exception {
		MergeCell mergeRow = mapMergeRow.get(numColumn);
		mergeRow.setRowEnd(indexRow - 1);
		// setCellValueExcel(mergeRow.getCellFrom(),
		// mergeRow.getCellStyleFrom(),
		// mergeRow.getSheetHeader());
		runMergeCell(workbook, worksheet, mergeRow);
	}

	/**
	 * Run merge cell.
	 *
	 * @param workbook  the workbook
	 * @param worksheet the worksheet
	 * @param mergeCell the merge cell
	 * @throws Exception the exception
	 */
	protected void runMergeCell(Workbook workbook, Sheet worksheet, MergeCell mergeCell) throws Exception {
		setCellValueExcel(workbook, worksheet, mergeCell);
		if (mergeCell.getRowStart() < mergeCell.getRowEnd() || mergeCell.getColumnFrom() < mergeCell.getColumnTo())
			worksheet.addMergedRegion(new CellRangeAddress(mergeCell.getRowStart(), mergeCell.getRowEnd(), mergeCell.getColumnFrom(), mergeCell.getColumnTo()));
	}

	/**
	 * Adds the comment.
	 *
	 * @param workbook   the workbook
	 * @param worksheet  the worksheet
	 * @param row        the row
	 * @param cellHeader the cell header
	 * @param commento   the commento
	 */
	protected void addComment(Workbook workbook, Sheet worksheet, Row row, Cell cellHeader, String commento) {
		Drawing<?> drawing = worksheet.createDrawingPatriarch();
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
	 * @param workbook     the workbook
	 * @param worksheet    the worksheet
	 * @param sheetSummary the sheet summary
	 * @param sheetHeader  the sheet header
	 * @param row          the row
	 * @throws Exception the exception
	 */
	protected void setCellSummary(Workbook workbook, Sheet worksheet, SheetSummary sheetSummary, SheetHeader sheetHeader, Row row) throws Exception {
		ExcelSummary excelSummary = ExcelUtils.getAnnotation(sheetSummary.getClass(), ExcelSummary.class);
		LayoutCell layoutCellSummary = ExcelUtils.reflectionAnnotation(new LayoutCell(), excelSummary.layout());
		short heightRow = ExcelUtils.AUTO_SIZE_HEIGHT;
		if (sheetHeader.getField() != null && sheetHeader.getField().isAnnotationPresent(ExcelRowHeight.class)) {
			ExcelRowHeight excelRowHeight = sheetHeader.getField().getAnnotation(ExcelRowHeight.class);
			heightRow = ExcelUtils.rowHeight(excelRowHeight.height());
		}
		row.setHeight(heightRow);
		CellStyle cellStyleColumn0 = createCellStyle(workbook, excelSummary.layout());
		Cell cellColumn0 = row.createCell(0);
		setCellStyleExcel(cellStyleColumn0, cellColumn0, layoutCellSummary);
		cellColumn0.setCellValue(this.valueProps.valueProps(sheetHeader.getExcelColumn().columnName()));
		if (StringUtils.isNotBlank(sheetHeader.getExcelColumn().comment()))
			addComment(workbook, worksheet, row, cellColumn0, sheetHeader.getExcelColumn().comment());
		ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
		ExcelDate excelDate = null;
		if (sheetHeader.getValue() instanceof Date || sheetHeader.getValue() instanceof Calendar)
			excelDate = sheetHeader.getExcelDate();
		CellStyle cellStyleColumn1 = this.createCellStyle(workbook, excelCellLayout, excelDate);
		Cell cellColumn1 = row.createCell(1);

		setCellValueExcel(workbook, worksheet, cellColumn1, cellStyleColumn1, sheetHeader, cellColumn1.getRowIndex());
		// setCellValueExcel(workbook, cellColumn1, cellStyleColumn1, sheetHeader);

	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param workbook    the workbook
	 * @param worksheet   the worksheet
	 * @param cell        the cell
	 * @param cellStyle   the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Workbook workbook, Sheet worksheet, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow) throws Exception {
		if (sheetHeader.getExcelFunction() != null) {
			try {
				setCellFormulaExcel(cell, cellStyle, sheetHeader, indexRow, worksheet);
			} catch (Exception e) {
				FunctionCell functionCell = new FunctionCell();
				functionCell.setWorksheet(worksheet);
				functionCell.setCell(cell);
				functionCell.setIndexRow(indexRow);
				functionCell.setSheetHeader(sheetHeader);
				listFunctionCell.add(functionCell);
			}
		} else
			setCellValueExcel(workbook, cell, cellStyle, sheetHeader);

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param cell        the cell
	 * @param cellStyle   the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @param worksheet   the worksheet
	 * @throws Exception the exception
	 */
	protected void setCellFormulaExcel(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, Sheet worksheet) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = makeFunction(worksheet, indexRow, function, RowStartEndType.ROW_EMPTY);
		if (excelFunction.anotherTable()) {
			function = makeFunction(worksheet, null, function, RowStartEndType.ROW_START);
			function = makeFunction(worksheet, null, function, RowStartEndType.ROW_END);
		}
		function = makeFunction(worksheet, indexRow, function, RowStartEndType.ROW_START);
		function = makeFunction(worksheet, indexRow, function, RowStartEndType.ROW_END);
		function = makeFunction(worksheet, indexRow, function, RowStartEndType.ROW_HEADER);
		logger.debug("Function: " + function);
		// cell.setCellType(CellType.FORMULA);
		cell.setCellFormula(function);

	}

	/**
	 * Make function.
	 *
	 * @param worksheet       the worksheet
	 * @param indexRow        the index row
	 * @param function        the function
	 * @param rowStartEndType the row start end type
	 * @return the string
	 */
	protected String makeFunction(Sheet worksheet, Integer indexRow, String function, RowStartEndType rowStartEndType) {
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(function);
		while (m.find()) {
			String parameter = m.group();
			String keyParameter = parameter.replace($, "").replace(rowStartEndType.getValue() + "}", "").trim();
			if (mapFieldColumn.containsKey(ExcelUtils.getKeyColumn(worksheet, keyParameter))) {
				InfoColumn infoColumn = (InfoColumn) mapFieldColumn.get(ExcelUtils.getKeyColumn(worksheet, keyParameter));
				Integer row = indexRow;

				if (RowStartEndType.ROW_HEADER.equals(rowStartEndType))
					row = infoColumn.getRowHeader();
				else if (RowStartEndType.ROW_EMPTY.equals(rowStartEndType) && !infoColumn.getMapRowMergeRow().isEmpty())
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
	 * @param workbook  the workbook
	 * @param worksheet the worksheet
	 * @param mergeRow  the merge row
	 * @throws Exception the exception
	 */
	private void setCellValueExcel(Workbook workbook, Sheet worksheet, MergeCell mergeRow) throws Exception {
		if (mergeRow.getSheetHeader().getExcelFunction() != null)
			try {
				setCellFormulaExcel(worksheet, mergeRow);
			} catch (Exception e) {
				FunctionCell functionCell = new FunctionCell();
				functionCell.setWorksheet(worksheet);
				functionCell.setMergeRow(mergeRow);
				listFunctionCell.add(functionCell);
			}
		else
			setCellValueExcel(workbook, mergeRow.getCellFrom(), mergeRow.getCellStyleFrom(), mergeRow.getSheetHeader());

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param worksheet the worksheet
	 * @param mergeRow  the merge row
	 * @throws Exception the exception
	 */
	protected void setCellFormulaExcel(Sheet worksheet, MergeCell mergeRow) throws Exception {
		SheetHeader sheetHeader = mergeRow.getSheetHeader();
		CellStyle cellStyle = mergeRow.getCellStyleFrom();
		Cell cell = mergeRow.getCellFrom();
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = makeFunction(worksheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_EMPTY);
		if (excelFunction.anotherTable()) {
			function = makeFunction(worksheet, null, function, RowStartEndType.ROW_START);
			function = makeFunction(worksheet, null, function, RowStartEndType.ROW_END);
		}

		function = makeFunction(worksheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_START);
		function = makeFunction(worksheet, mergeRow.getRowEnd(), function, RowStartEndType.ROW_END);
		function = makeFunction(worksheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_HEADER);
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
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Workbook workbook, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell);
		if (sheetHeader.getValue() instanceof Date)
			cell.setCellValue((Date) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Calendar)
			cell.setCellValue((Calendar) sheetHeader.getValue());
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
	 * @param workbook  the workbook
	 * @param worksheet the worksheet
	 * @param sheet     the sheet
	 * @param rowHeader the row header
	 * @return the cell style header
	 * @throws Exception the exception
	 */
	public CellStyle getCellStyleHeader(Workbook workbook, Sheet worksheet, SheetComponent sheet, Row rowHeader) throws Exception {
		ExcelHeaderLayout layoutHeader = ExcelUtils.getAnnotation(sheet.getClass(), ExcelHeaderLayout.class);
		ExcelMarginSheet excelMarginSheet = ExcelUtils.getAnnotation(sheet.getClass(), ExcelMarginSheet.class);
		ExcelSheetLayout layoutSheet = ExcelUtils.getAnnotation(sheet.getClass(), ExcelSheetLayout.class);
		worksheet.setMargin(Sheet.LeftMargin, excelMarginSheet.left());
		worksheet.setMargin(Sheet.RightMargin, excelMarginSheet.right());
		worksheet.setMargin(Sheet.TopMargin, excelMarginSheet.top());
		worksheet.setMargin(Sheet.BottomMargin, excelMarginSheet.bottom());
		worksheet.getPrintSetup().setLandscape(layoutSheet.landscape());
		rowHeader.setHeight(ExcelUtils.rowHeight(layoutHeader.rowHeight()));
		if (layoutHeader.excelHeaderCellLayout().locked())
			worksheet.protectSheet("");
//		worksheet.setDefaultColumnWidth(5 * layoutHeader.cmWidthCell());
//		worksheet.setDefaultRowHeight((short)(layoutHeader.cmHeightCell() * 568));
		if (layoutSheet.order() > -1)
			workbook.setSheetOrder(worksheet.getSheetName(), layoutSheet.order());
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
	 * @param worksheet the worksheet
	 * @param sheetData the sheet data
	 * @param indexRow  the index row
	 * @return the list
	 * @throws Exception the exception
	 */
	protected <T extends RowSheet> List<SheetHeader> generateHeaderSheetData(Workbook workbook, Sheet worksheet, SheetData<T> sheetData, Integer indexRow) throws Exception {

		ExcelSheetLayout excelSheetLayout = ExcelUtils.getAnnotation(sheetData.getClass(), ExcelSheetLayout.class);
		List<SheetHeader> listSheetHeader = this.getListSheetHeader(sheetData.getRowClass(), null, worksheet);
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
					if (extraColumnAnnotation.getExcelFunction() != null)
						sheetHeader.setExcelFunction(extraColumnAnnotation.getExcelFunction());

					sheetHeader.setKeyMap(keyMap);
					listSheetHeader.add(sheetHeader);
				}
			}
			Collections.sort(listSheetHeader, new SheetColumnComparator());
		}
		Integer indexStartSuperHeader = indexRow - getSizeSuperHeader(sheetData);

		Row rowHeader = worksheet.createRow(indexRow);
		
		CellStyle cellStyleHeader = getCellStyleHeader(workbook, worksheet, sheetData, rowHeader);
		int maxColumn = listSheetHeader.size() + excelSheetLayout.startColumn();

		for (int columnNum = excelSheetLayout.startColumn(); columnNum < maxColumn; columnNum++) {
			int indexHeader = columnNum - excelSheetLayout.startColumn();
			Cell cellHeader = rowHeader.createCell(columnNum);
			SheetHeader sheetHeader = listSheetHeader.get(indexHeader);
			if (sheetHeader.getField() != null && sheetHeader.getField().isAnnotationPresent(ExcelHeaderCellLayout.class) || sheetHeader.getExcelHeaderCellLayout() != null) {
				ExcelHeaderCellLayout layoutHeader = null;
				if (sheetHeader.getExcelHeaderCellLayout() != null)
					layoutHeader = sheetHeader.getExcelHeaderCellLayout();
				else
					layoutHeader = ExcelUtils.getAnnotation(sheetHeader.getField(), ExcelHeaderCellLayout.class);
				if (layoutHeader.locked())
					worksheet.protectSheet("");
				CellStyle differentCellStyleHeader = manageCellStyleHeader(workbook, layoutHeader);
				cellHeader.setCellStyle(differentCellStyleHeader);
			} else
				cellHeader.setCellStyle(cellStyleHeader);

			ExcelColumn excelColumn = listSheetHeader.get(indexHeader).getExcelColumn();
			setColumnWidth(worksheet, columnNum, sheetHeader.getExcelColumnWidth().width());
			listSheetHeader.get(indexHeader).setNumColumn(columnNum);
			cellHeader.setCellValue(this.valueProps.valueProps(excelColumn.columnName()));
			if (StringUtils.isNoneBlank(excelColumn.comment()))
				addComment(workbook, worksheet, rowHeader, cellHeader, excelColumn.comment());
			InfoColumn infoColumn = new InfoColumn(worksheet, sheetHeader, columnNum, indexRow);
			String key = null;
			if (sheetHeader.getField() != null)
				key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getField().getName());
			else if (StringUtils.isNotBlank(sheetHeader.getKeyMap()))
				key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getKeyMap());
			else if (sheetHeader.getExcelFunction() != null)
				key = ExcelUtils.getKeyColumn(worksheet, sheetHeader.getExcelFunction().nameFunction());
			sheetHeader.setKey(key);
			this.mapFieldColumn.put(key, infoColumn);
		}

		if (sheetData.getClass().isAnnotationPresent(ExcelSuperHeaders.class)) {
			ExcelSuperHeaders excelSuperHeaders = sheetData.getClass().getAnnotation(ExcelSuperHeaders.class);
			for (ExcelSuperHeader superHeader : excelSuperHeaders.superHeaders()) {
				Row rowSuperHeader = worksheet.createRow(indexStartSuperHeader);
				rowSuperHeader.setHeight(ExcelUtils.rowHeight(superHeader.rowHeight()));
				for (ExcelSuperHeaderCell headerGroup : superHeader.headerGroups()) {
					String function = headerGroup.columnRange().replace("${", "").replace("}", "");
					String[] columns = function.split(":");
					Cell cellSuperHeader = null;
					boolean setCellValue = true;
					for (String column : columns) {
						String key = ExcelUtils.getKeyColumn(worksheet, column);
						int columnNum = this.mapFieldColumn.get(key).getColumnNum();
						cellSuperHeader = rowSuperHeader.createCell(columnNum);
						CellStyle cellSuperHeaderStyle = manageCellStyleHeader(workbook, headerGroup);
						cellSuperHeader.setCellStyle(cellSuperHeaderStyle);
						if (setCellValue)
							cellSuperHeader.setCellValue(this.valueProps.valueProps(headerGroup.columnName()));
						setCellValue = false;
					}

					String mergeCell = this.makeFunction(worksheet, indexStartSuperHeader, headerGroup.columnRange(), RowStartEndType.ROW_EMPTY);
					worksheet.addMergedRegion(CellRangeAddress.valueOf(mergeCell));

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
	 * @param worksheet   the worksheet
	 * @param indexColumn the index column
	 * @param width       the width
	 * @throws Exception the exception
	 */
	protected void setColumnWidth(Sheet worksheet, Integer indexColumn, Integer width) throws Exception {
		if (!this.mapWidthColumn.containsKey(indexColumn) || this.mapWidthColumn.get(indexColumn) < width) {
			this.mapWidthColumn.put(new Integer(indexColumn), new Integer(width));
			worksheet.setColumnWidth(indexColumn, ExcelUtils.widthColumn(width));
		}
	}

}