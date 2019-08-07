/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel.impl;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;

import bld.generator.report.excel.DynamicRowSheet;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.SheetComponent;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.SheetDynamicData;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelBorder;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelColumn;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelFont;
import bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import bld.generator.report.excel.annotation.ExcelFunctionRow;
import bld.generator.report.excel.annotation.ExcelFunctionRows;
import bld.generator.report.excel.annotation.ExcelHeaderLayout;
import bld.generator.report.excel.annotation.ExcelMarginSheet;
import bld.generator.report.excel.annotation.ExcelRgbColor;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSummary;
import bld.generator.report.excel.comparator.SheetColumnComparator;
import bld.generator.report.excel.constant.ColumnDateFormat;
import bld.generator.report.excel.data.ExtraColumnAnnotation;
import bld.generator.report.excel.data.LayoutCell;
import bld.generator.report.excel.data.MergeRow;
import bld.generator.report.excel.data.SheetHeader;
import bld.generator.report.utils.ExcelUtils;
import bld.generator.report.utils.ValueProps;

// TODO: Auto-generated Javadoc
/**
 * The Class SuperGenerateExcelImpl.
 */
@SuppressWarnings({ "deprecation", "unchecked" })
public class SuperGenerateExcelImpl {

	/** The Constant SDF. */
	protected static final SimpleDateFormat SDF = new SimpleDateFormat(ColumnDateFormat.DD_MM_YYYY.getValue());

	/** The merge calcolo cells. */
	protected CellStyle mergeCalcoloCells = null;

	/** The map cell style. */
	protected Map<LayoutCell, CellStyle> mapCellStyle = new HashMap<>();

	/** The map cell header style. */
	protected Map<LayoutCell, CellStyle> mapCellHeaderStyle = new HashMap<>();

	/** The logger. */
	private final static Log logger = LogFactory.getLog(SuperGenerateExcelImpl.class);

	/** The Constant WIDTH_CELL_STANDARD. */
	private static final int WIDTH_CELL_STANDARD = 22;

	/** The map field column. */
	protected Map<String, Integer> mapFieldColumn = new HashMap<>();
	
	
	/** The value props. */
	@Autowired
	protected ValueProps valueProps;

	/**
	 * Instantiates a new super generate excel
	 * impl.
	 */
	public SuperGenerateExcelImpl() {
		super();
	}

	/**
	 * Gets the font and color.
	 *
	 * @param workbook        the workbook
	 * @param layout          the layout
	 * @return the font and color
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelHeaderLayout layout) {
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
	 * @param layout the layout
	 * @return the cell style
	 * @throws Exception the exception
	 */
	protected CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout) throws Exception {
		return createCellStyle(workbook, layout, null);
	}

	/**
	 * Creates the cell style.
	 *
	 * @param workbook the workbook
	 * @param layout the layout
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
	 * @param workbook the workbook
	 * @param excelFont the excel font
	 * @return the font
	 */
	protected Font getFont(Workbook workbook, ExcelFont excelFont) {
		Font font = workbook.createFont();
		font.setBold(excelFont.bold());
		font.setFontName(excelFont.font());
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
	 * @param classSheet the class sheet
	 * @param entity the entity
	 * @return the list sheet header
	 * @throws Exception the exception
	 */
	protected List<SheetHeader> getListSheetHeader(Class<?> classSheet, Object entity) throws Exception {
		logger.info("Sheet: " + classSheet.getSimpleName());
		Set<String> listTitolo = new HashSet<>();
		List<SheetHeader> listSheetHeader = new ArrayList<>();
		List<Field> listField = new ArrayList<>();
		if (classSheet.getDeclaredFields().length > 0)
			listField.addAll(Arrays.asList(classSheet.getDeclaredFields()));
		if (classSheet.getSuperclass().getDeclaredFields().length > 0)
			listField.addAll(Arrays.asList(classSheet.getSuperclass().getDeclaredFields()));
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelColumn.class)) {
				logger.info(field.getName());
				ExcelColumn sheetColumn = field.getAnnotation(ExcelColumn.class);
				Object value = null;
				if (entity != null)
					value = new PropertyDescriptor(field.getName(), classSheet).getReadMethod().invoke(entity);
				listSheetHeader.add(new SheetHeader(field, value));
				if (listTitolo.contains(sheetColumn.nameColumn()))
					logger.warn("Exist another equal column with nameColum= \"" + sheetColumn.nameColumn() + "\" for the same sheet!!!");
				listTitolo.add(sheetColumn.nameColumn());
			}
		}
		if (classSheet.isAnnotationPresent(ExcelFunctionRows.class)) {
			ExcelFunctionRows excelFunctionRows = classSheet.getAnnotation(ExcelFunctionRows.class);
			for (ExcelFunctionRow excelFunction : excelFunctionRows.excelFunctions()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunction.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunction.excelCellsLayout());
				sheetHeader.setFunction(excelFunction.excelFunction().function());
				sheetHeader.setNameFunction(excelFunction.excelFunction().nameFunction());
				listSheetHeader.add(sheetHeader);

			}
			for (ExcelFunctionMergeRow excelFunctionMerge : excelFunctionRows.excelFunctionMerges()) {
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelColumn(excelFunctionMerge.excelColumn());
				sheetHeader.setExcelCellLayout(excelFunctionMerge.excelCellsLayout());
				sheetHeader.setFunction(excelFunctionMerge.excelFunction().function());
				sheetHeader.setExcelMergeColumn(excelFunctionMerge.excelMergeRow());
				sheetHeader.setNameFunction(excelFunctionMerge.excelFunction().nameFunction());
				listSheetHeader.add(sheetHeader);

			}
		}

		Collections.sort(listSheetHeader, new SheetColumnComparator());
		return listSheetHeader;
	}

	/**
	 * Write cell empty.
	 *
	 * @param workbook the workbook
	 * @param cellStyle the cell style
	 * @param cell the cell
	 * @param sheetHeader the sheet header
	 * @return true, if successful
	 * @throws CloneNotSupportedException the clone not supported exception
	 * @throws Exception the exception
	 */
	protected boolean writeCellEmpty(Workbook workbook, CellStyle cellStyle, Cell cell, SheetHeader sheetHeader) throws CloneNotSupportedException, Exception {
		boolean repeat;
		SheetHeader sheetHeaderTemp = (SheetHeader) sheetHeader.clone();
		sheetHeaderTemp.setValue(null);
		setCellValueExcel(cell, cellStyle, sheetHeaderTemp);
		repeat = false;
		return repeat;
	}

	/**
	 * Merge row and remove map.
	 *
	 * @param workbook the workbook
	 * @param worksheet the worksheet
	 * @param indexRow the index row
	 * @param mapMergeRow the map merge row
	 * @param numColumn the num column
	 * @throws Exception the exception
	 */
	protected void mergeRowAndRemoveMap(Workbook workbook, Sheet worksheet, Integer indexRow, Map<Integer, MergeRow> mapMergeRow, int numColumn) throws Exception {
		mergeRow(workbook, worksheet, indexRow, mapMergeRow, numColumn);
		mapMergeRow.remove(numColumn);
	}

	/**
	 * Merge row.
	 *
	 * @param workbook the workbook
	 * @param worksheet the worksheet
	 * @param indexRow the index row
	 * @param mapMergeRow the map merge row
	 * @param numColumn the num column
	 * @throws Exception the exception
	 */
	protected void mergeRow(Workbook workbook, Sheet worksheet, Integer indexRow, Map<Integer, MergeRow> mapMergeRow, int numColumn) throws Exception {
		MergeRow mergeRow = mapMergeRow.get(numColumn);
		mergeRow.setRowTo(indexRow - 1);
		// setCellValueExcel(mergeRow.getCellFrom(),
		// mergeRow.getCellStyleFrom(),
		// mergeRow.getSheetHeader());
		setCellValueExcel(mergeRow);
		if (mergeRow.getRowFrom() < mergeRow.getRowTo())
			worksheet.addMergedRegion(new CellRangeAddress(mergeRow.getRowFrom(), mergeRow.getRowTo(), mergeRow.getColumnFrom(), mergeRow.getColumnTo()));
	}

	/**
	 * Adds the comment.
	 *
	 * @param workbook the workbook
	 * @param worksheet the worksheet
	 * @param row the row
	 * @param cellHeader the cell header
	 * @param commento the commento
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
	 * Sets the cell sommario.
	 *
	 * @param workbook      the workbook
	 * @param worksheet the worksheet
	 * @param sheetSummary the sheet summary
	 * @param sheetHeader the sheet header
	 * @param row           the row
	 * @throws Exception the exception
	 */
	protected void setCellSommario(Workbook workbook, Sheet worksheet, SheetSummary sheetSummary, SheetHeader sheetHeader, Row row) throws Exception {
		ExcelSummary excelSummary = ExcelUtils.getAnnotation(sheetSummary.getClass(), ExcelSummary.class);
		LayoutCell layoutCellSummary = ExcelUtils.reflectionAnnotation(new LayoutCell(), excelSummary.layout());
		CellStyle cellStyleColumn0 = createCellStyle(workbook, excelSummary.layout());
		Cell cellColumn0 = row.createCell(0);
		setCellStyleExcel(cellStyleColumn0, cellColumn0, layoutCellSummary);
		cellColumn0.setCellValue(this.valueProps.valueProps(sheetHeader.getExcelColumn().nameColumn()));
		if (StringUtils.isNotBlank(sheetHeader.getExcelColumn().comment()))
			addComment(workbook, worksheet, row, cellColumn0, sheetHeader.getExcelColumn().comment());
		ExcelCellLayout excelCellLayout = ExcelUtils.getAnnotation(sheetHeader.getField(), ExcelCellLayout.class);
		ExcelDate excelDate = null;
		if (sheetHeader.getValue() instanceof Date || sheetHeader.getValue() instanceof Calendar)
			excelDate = sheetHeader.getExcelDate();
		CellStyle cellStyleColumn1 = this.createCellStyle(workbook, excelCellLayout, excelDate);
		Cell cellColumn1 = row.createCell(1);
		setCellValueExcel(cellColumn1, cellStyleColumn1, sheetHeader);

	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param cell the cell
	 * @param cellStyle the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow the index row
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow) throws Exception {
		if (StringUtils.isNotBlank(sheetHeader.getFunction())) {
			setCellFormulaExcel(cell, cellStyle, sheetHeader, indexRow);
		} else
			setCellValueExcel(cell, cellStyle, sheetHeader);

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param cell the cell
	 * @param cellStyle the cell style
	 * @param sheetHeader the sheet header
	 * @param indexRow the index row
	 * @throws Exception the exception
	 */
	private void setCellFormulaExcel(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell);
		Pattern p = Pattern.compile("\\$.*?From");
		String function = sheetHeader.getFunction();
		Matcher m = p.matcher(function);
		while (m.find()) {
			String keyParameter = m.group().replace("$", "").replace("From", "").trim();
			function = function.replace(m.group(), calcoloCoordinateFunction(indexRow + 1, mapFieldColumn.get(keyParameter)));
		}

		p = Pattern.compile("\\$.*?To");
		m = p.matcher(function);
		while (m.find()) {
			String keyParameter = m.group().replace("$", "").replace("To", "").trim();
			function = function.replace(m.group(), calcoloCoordinateFunction(indexRow + 1, mapFieldColumn.get(keyParameter)));
		}

		//cell.setCellType(CellType.FORMULA);
		cell.setCellFormula(function);

	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param mergeRow the new cell value excel
	 * @throws Exception the exception
	 */
	private void setCellValueExcel(MergeRow mergeRow) throws Exception {
		if (StringUtils.isNotBlank(mergeRow.getSheetHeader().getFunction()))
			setCellFormulaExcel(mergeRow);
		else
			setCellValueExcel(mergeRow.getCellFrom(), mergeRow.getCellStyleFrom(), mergeRow.getSheetHeader());

	}

	/**
	 * Sets the cell formula excel.
	 *
	 * @param mergeRow the new cell formula excel
	 * @throws Exception the exception
	 */
	private void setCellFormulaExcel(MergeRow mergeRow) throws Exception {
		SheetHeader sheetHeader = mergeRow.getSheetHeader();
		CellStyle cellStyle = mergeRow.getCellStyleFrom();
		Cell cell = mergeRow.getCellFrom();
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell);
		Pattern p = Pattern.compile("\\$.*?From");
		String function = sheetHeader.getFunction();
		Matcher m = p.matcher(function);
		while (m.find()) {
			String keyParameter = m.group().replace("$", "").replace("From", "").trim();
			function = function.replace(m.group(), calcoloCoordinateFunction(mergeRow.getRowFrom() + 1, mapFieldColumn.get(keyParameter)));
		}
		p = Pattern.compile("\\$.*?To");
		m = p.matcher(function);
		while (m.find()) {
			String keyParameter = m.group().replace("$", "").replace("To", "").trim();
			function = function.replace(m.group(), calcoloCoordinateFunction(mergeRow.getRowTo() + 1, mapFieldColumn.get(keyParameter)));
		}

	//	cell.setCellType(CellType.FORMULA);
		cell.setCellFormula(function);
	}

	/**
	 * Sets the cell value excel.
	 *
	 * @param cell      the cell
	 * @param cellStyle the cell style
	 * @param sheetHeader the sheet header
	 * @throws Exception the exception
	 */
	protected void setCellValueExcel(Cell cell, CellStyle cellStyle, SheetHeader sheetHeader) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell();
		setCellStyleExcel(cellStyle, cell, layoutCell);
		if (sheetHeader.getValue() instanceof Date)
			cell.setCellValue((Date) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Calendar)
			cell.setCellValue((Calendar) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof String)
			cell.setCellValue((String) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Number)
			cell.setCellValue(((Number) sheetHeader.getValue()).doubleValue());
		else if (sheetHeader.getValue() instanceof Boolean)
			cell.setCellValue((Boolean) sheetHeader.getValue());

	}

	/**
	 * Date cell style.
	 *
	 * @param workbook the workbook
	 * @param cellStyle the cell style
	 * @param format the format
	 * @return the cell style
	 * @throws Exception the exception
	 */
	private CellStyle dateCellStyle(Workbook workbook, CellStyle cellStyle, String format) throws Exception {
		CreationHelper helper = workbook.getCreationHelper();
		cellStyle.setDataFormat(helper.createDataFormat().getFormat(format));
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
	 * @param workbook the workbook
	 * @param worksheet the worksheet
	 * @param sheet the sheet
	 * @return the cell style header
	 * @throws Exception the exception
	 */
	public CellStyle getCellStyleHeader(Workbook workbook, Sheet worksheet, SheetComponent sheet) throws Exception {
		ExcelHeaderLayout layoutHeader = ExcelUtils.getAnnotation(sheet.getClass(), ExcelHeaderLayout.class);
		ExcelMarginSheet excelMarginSheet = ExcelUtils.getAnnotation(sheet.getClass(), ExcelMarginSheet.class);
		ExcelSheetLayout layoutSheet = ExcelUtils.getAnnotation(sheet.getClass(), ExcelSheetLayout.class);
		worksheet.setMargin(Sheet.LeftMargin, excelMarginSheet.left());
		worksheet.setMargin(Sheet.RightMargin, excelMarginSheet.right());
		worksheet.setMargin(Sheet.TopMargin, excelMarginSheet.top());
		worksheet.setMargin(Sheet.BottomMargin, excelMarginSheet.bottom());
		worksheet.getPrintSetup().setLandscape(layoutSheet.landscape());
		worksheet.setDefaultColumnWidth(5*layoutHeader.cmWidthCell());
		worksheet.setDefaultRowHeight((short)(layoutHeader.cmHeightCell()*568));
		return manageCellStyleHeader(workbook, layoutHeader);
	}

	/**
	 * Manage cell style header.
	 *
	 * @param workbook the workbook
	 * @param layoutHeader the layout header
	 * @return the cell style
	 */
	private CellStyle manageCellStyleHeader(Workbook workbook, ExcelHeaderLayout layoutHeader) {
		CellStyle cellStyleHeader = null;
		LayoutCell layoutCellHeader = ExcelUtils.reflectionAnnotation(new LayoutCell(), layoutHeader);
		if (this.mapCellHeaderStyle.containsKey(layoutCellHeader))
			cellStyleHeader = this.mapCellHeaderStyle.get(layoutCellHeader);
		else {
			cellStyleHeader = createCellStyle(workbook, layoutHeader);
			this.mapCellHeaderStyle.put(layoutCellHeader, cellStyleHeader);
		}

		return cellStyleHeader;
	}

	/**
	 * Sets the header excel dati.
	 *
	 * @param <T> the generic type
	 * @param workbook  the workbook
	 * @param worksheet the worksheet
	 * @param rowHeader the row header
	 * @param sheetData the sheet data
	 * @param indexRow the index row
	 * @return the list
	 * @throws Exception the exception
	 */
	protected <T extends RowSheet> List<SheetHeader> generateHeaderSheetData(Workbook workbook, Sheet worksheet, Row rowHeader, SheetData<T> sheetData, Integer indexRow) throws Exception {
		List<SheetHeader> listSheetHeader = this.getListSheetHeader(sheetData.getRowClass(), null);
		if (sheetData instanceof SheetDynamicData) {
			SheetDynamicData<DynamicRowSheet> sheetDynamicData = (SheetDynamicData<DynamicRowSheet>) sheetData;
			for (String keyMap : sheetDynamicData.getMapExtraColumnAnnotation().keySet()) {
				ExtraColumnAnnotation extraColumnAnnotation = sheetDynamicData.getMapExtraColumnAnnotation().get(keyMap);
				SheetHeader sheetHeader = new SheetHeader();
				sheetHeader.setExcelCellLayout(extraColumnAnnotation.getExcelCellLayout());
				sheetHeader.setExcelColumn(extraColumnAnnotation.getExcelColumn());
				sheetHeader.setExcelDate(extraColumnAnnotation.getExcelDate());
				sheetHeader.setKeyMap(keyMap);
				listSheetHeader.add(sheetHeader);
			}
			Collections.sort(listSheetHeader, new SheetColumnComparator());
		}
		int idRowHeader = indexRow + 1;
		worksheet.setRepeatingRows(CellRangeAddress.valueOf(idRowHeader + ":" + idRowHeader));
		CellStyle cellStyleHeader = getCellStyleHeader(workbook, worksheet, sheetData);
		for (int numColumn = 0; numColumn < listSheetHeader.size(); numColumn++) {
			Cell cellHeader = rowHeader.createCell(numColumn);
			SheetHeader sheetHeader = listSheetHeader.get(numColumn);
			if(sheetHeader.getField()!=null && sheetHeader.getField().isAnnotationPresent(ExcelHeaderLayout.class)) {
				ExcelHeaderLayout layoutHeader=ExcelUtils.getAnnotation(sheetHeader.getField(), ExcelHeaderLayout.class);
				CellStyle differentCellStyleHeader = manageCellStyleHeader(workbook, layoutHeader);
				cellHeader.setCellStyle(differentCellStyleHeader);
			}else 
				cellHeader.setCellStyle(cellStyleHeader);
			
			ExcelColumn excelColumn = listSheetHeader.get(numColumn).getExcelColumn();
			listSheetHeader.get(numColumn).setNumColumn(numColumn);
			cellHeader.setCellValue(this.valueProps.valueProps(excelColumn.nameColumn()));
			if (StringUtils.isNoneBlank(excelColumn.comment()))
				addComment(workbook, worksheet, rowHeader, cellHeader, excelColumn.comment());
			if (sheetHeader.getField() != null)
				this.mapFieldColumn.put(sheetHeader.getField().getName(), numColumn);
			else if(StringUtils.isNotBlank(sheetHeader.getKeyMap())) 
				this.mapFieldColumn.put(sheetHeader.getKeyMap(), numColumn);
			else if(StringUtils.isNotBlank(sheetHeader.getNameFunction()))
				this.mapFieldColumn.put(sheetHeader.getNameFunction(), numColumn);
		}
		return listSheetHeader;
	}

	/**
	 * Calcolo cordinate function.
	 *
	 * @param row     the row
	 * @param column  the column
	 * @return the string
	 */
	protected String calcoloCoordinateFunction(int row, int column) {
		int mod = 0;
		int div = column;
		String coordinata = "";
		do {
			mod = div % 26;
			mod += 65;
			div = (div / 26) - 1;
			coordinata = Character.toString((char) mod) + coordinata;
		} while (div >= 0);
		coordinata = coordinata + row;

		return coordinata;
	}
	
	
	
	

}