/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.SuperGenerateExcelImpl.java
*/
package com.bld.generator.report.excel.impl;

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
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.sl.usermodel.PictureData.PictureType;
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
import org.apache.poi.xssf.usermodel.XSSFPivotTable;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.comparator.PivotColumnComparator;
import com.bld.generator.report.comparator.PivotColumnFunctionComparator;
import com.bld.generator.report.comparator.PivotRowComparator;
import com.bld.generator.report.comparator.SheetColumnComparator;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.DynamicColumn;
import com.bld.generator.report.excel.ExcelAttachment;
import com.bld.generator.report.excel.ExcelHyperlink;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.SheetSummary;
import com.bld.generator.report.excel.annotation.ExcelBoxMessage;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelColumn;
import com.bld.generator.report.excel.annotation.ExcelDataValidation;
import com.bld.generator.report.excel.annotation.ExcelDropDown;
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelFunctionMergeRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRow;
import com.bld.generator.report.excel.annotation.ExcelFunctionRows;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelImage;
import com.bld.generator.report.excel.annotation.ExcelPivot;
import com.bld.generator.report.excel.annotation.ExcelPivotColumn;
import com.bld.generator.report.excel.annotation.ExcelPivotColumnFunction;
import com.bld.generator.report.excel.annotation.ExcelPivotFilter;
import com.bld.generator.report.excel.annotation.ExcelPivotRow;
import com.bld.generator.report.excel.annotation.ExcelRowHeight;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;
import com.bld.generator.report.excel.annotation.ExcelSummary;
import com.bld.generator.report.excel.annotation.ExcelSuperHeader;
import com.bld.generator.report.excel.annotation.ExcelSuperHeaderCell;
import com.bld.generator.report.excel.annotation.ExcelSuperHeaders;
import com.bld.generator.report.excel.data.DropDownCell;
import com.bld.generator.report.excel.data.ExtraColumnAnnotation;
import com.bld.generator.report.excel.data.FunctionCell;
import com.bld.generator.report.excel.data.InfoColumn;
import com.bld.generator.report.excel.data.LayoutCell;
import com.bld.generator.report.excel.data.MergeCell;
import com.bld.generator.report.excel.data.SheetHeader;
import com.bld.generator.report.excel.dropdown.BoxMessage;
import com.bld.generator.report.excel.dropdown.DropDown;
import com.bld.generator.report.excel.sheet_mapping.SheetMappingSheet;
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;
import com.bld.generator.report.excel.utility.ExcelLayoutUtility;

/**
 * The Class SuperGenerateExcelImpl.
 */
public abstract class SuperGenerateExcelImpl {

	/** The Constant PATTERN. */
	private static final String PATTERN = "\\$\\{.*?}";

	// private static final String PATTERN_QUAD = "\\[.*?\\]";

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
		Set<Field> listField = SpreadsheetUtils.getListField(classRow);
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
				if (listTitle.contains(column.name()))
					logger.warn("Exist another equal column with columnName= \"" + column.name() + "\" for the same sheet!!!");
				listTitle.add(column.name());
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
				sheetHeader.setExcelNumberFormat(excelFunction.excelNumberFormat());
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
				sheetHeader.setExcelNumberFormat(excelFunctionMerge.excelNumberFormat());
				listSheetHeader.add(sheetHeader);

			}
		}

		Collections.sort(listSheetHeader, new SheetColumnComparator(this.valueProps));
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
		ExcelSummary excelSummary = SpreadsheetUtils.getAnnotation(sheetSummary.getClass(), ExcelSummary.class);
		LayoutCell layoutCellSummary = SpreadsheetUtils.reflectionAnnotation(new LayoutCell(), excelSummary.layout());
		short heightRow = ExcelUtils.AUTO_SIZE_HEIGHT;
		if (sheetHeader.getField() != null && sheetHeader.getField().isAnnotationPresent(ExcelRowHeight.class)) {
			ExcelRowHeight excelRowHeight = sheetHeader.getField().getAnnotation(ExcelRowHeight.class);
			heightRow = ExcelUtils.rowHeight(excelRowHeight.height());
		}
		row.setHeight(heightRow);
		CellStyle cellStyleColumn0 = ExcelLayoutUtility.createCellStyle(workbook, excelSummary.layout(), indexRow,this.valueProps);
		Cell cellColumn0 = row.createCell(excelSheetLayout.startColumn());
		ExcelLayoutUtility.setCellStyleExcel(cellStyleColumn0, cellColumn0, layoutCellSummary,this.mapCellStyle);
		cellColumn0.setCellValue(this.valueProps.valueProps(sheetHeader.getExcelColumn().name()));
		if (StringUtils.isNotBlank(sheetHeader.getExcelColumn().comment()))
			addComment(workbook, sheet, row, cellColumn0, sheetHeader.getExcelColumn().comment());
		ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
//		ExcelDate excelDate = null;
//		if (sheetHeader.getField() != null && (Date.class.isAssignableFrom(sheetHeader.getField().getType()) || Calendar.class.isAssignableFrom(sheetHeader.getField().getType()) || Timestamp.class.isAssignableFrom(sheetHeader.getField().getType())))
//			excelDate = sheetHeader.getExcelDate();
		CellStyle cellStyleColumn1 = ExcelLayoutUtility.createCellStyle(workbook, excelCellLayout, sheetHeader, indexRow,this.valueProps);
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
		ExcelLayoutUtility.setCellStyleExcel(cellStyle, cell, layoutCell,this.mapCellStyle);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_EMPTY, excelFunction.alias(),mapFieldColumn,mapSheet);
		if (excelFunction.anotherTable()) {
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_START, excelFunction.alias(),mapFieldColumn,mapSheet);
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_END, excelFunction.alias(),mapFieldColumn,mapSheet);
		}
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_START, excelFunction.alias(),mapFieldColumn,mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_END, excelFunction.alias(),mapFieldColumn,mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_HEADER, excelFunction.alias(),mapFieldColumn,mapSheet);

		logger.debug("Function: " + function);
		if (StringUtils.isNotEmpty(function))
			cell.setCellFormula(function);
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
		ExcelLayoutUtility.setCellStyleExcel(cellStyle, cell, layoutCell,this.mapCellStyle);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_EMPTY, excelFunction.alias(),mapFieldColumn,mapSheet);
		if (excelFunction.anotherTable()) {
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_START, excelFunction.alias(),mapFieldColumn,mapSheet);
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_END, excelFunction.alias(),mapFieldColumn,mapSheet);
		}

		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_START, excelFunction.alias(),mapFieldColumn,mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowEnd(), function, RowStartEndType.ROW_END, excelFunction.alias(),mapFieldColumn,mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_HEADER, excelFunction.alias(),mapFieldColumn,mapSheet);
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

	private void dataValidation(Sheet sheet, Cell cell, SheetHeader sheetHeader) throws Exception {
		if (sheetHeader.getExcelDataValidation() != null) {
			ExcelDataValidation excelDataValidation = sheetHeader.getExcelDataValidation();
			ExcelBoxMessage errorBox = excelDataValidation.errorBox();
			String formula=excelDataValidation.value();
			formula = ExcelBuildFunctionUtility.buildFunction(sheet, cell.getRowIndex(), formula, RowStartEndType.ROW_EMPTY, excelDataValidation.alias(),mapFieldColumn,mapSheet);
			formula = ExcelBuildFunctionUtility.buildFunction(sheet, null, formula, RowStartEndType.ROW_START, excelDataValidation.alias(),mapFieldColumn,mapSheet);
			formula = ExcelBuildFunctionUtility.buildFunction(sheet, null, formula, RowStartEndType.ROW_END, excelDataValidation.alias(),mapFieldColumn,mapSheet);
			CellRangeAddressList addressList = new CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
			DataValidationHelper validationHelper = sheet.getDataValidationHelper();
			DataValidationConstraint customConstraint = validationHelper.createCustomConstraint(formula);
			DataValidation dataValidation = validationHelper.createValidation(customConstraint, addressList);
			if (errorBox.show()) {
				dataValidation.setShowErrorBox(errorBox.show());
				dataValidation.createErrorBox(errorBox.title(), errorBox.message());
				dataValidation.setErrorStyle(errorBox.boxStyle().getValue());
			}
			sheet.addValidationData(dataValidation);
		}
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
	protected void setCellValueExcel(Workbook workbook, Cell cell, CellStyle cellStyle, SheetHeader sheetHeader, Integer indexRow, Sheet sheet) throws Exception {
		LayoutCell layoutCell = sheetHeader.getLayoutCell(indexRow);

		if (cellStyle == null)
			cellStyle = this.mapCellStyle.get(layoutCell);
		ExcelLayoutUtility.setCellStyleExcel(cellStyle, cell, layoutCell,this.mapCellStyle);

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
				cell.setCellValue(value ? sheetHeader.getExcelBooleanText().enable() : sheetHeader.getExcelBooleanText().disable());

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
		
		this.dataValidation(sheet, cell, sheetHeader);

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

		ExcelSheetLayout excelSheetLayout = SpreadsheetUtils.getAnnotation(sheetData.getClass(), ExcelSheetLayout.class);
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
			Collections.sort(listSheetHeader, new SheetColumnComparator(this.valueProps));
		}
		Integer indexStartSuperHeader = indexRow - getSizeSuperHeader(sheetData);
		Row rowHeader = null;
		CellStyle cellStyleHeader = null;
		if (excelSheetLayout.showHeader()) {
			rowHeader = sheet.createRow(indexRow);
			cellStyleHeader = ExcelLayoutUtility.getCellStyleHeader(workbook, sheet, sheetData, rowHeader,this.mapCellHeaderStyle);

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
						layoutHeader = SpreadsheetUtils.getAnnotation(sheetHeader.getField(), ExcelHeaderCellLayout.class);
					CellStyle differentCellStyleHeader = ExcelLayoutUtility.manageCellStyleHeader(workbook, layoutHeader,this.mapCellHeaderStyle);
					cellHeader.setCellStyle(differentCellStyleHeader);
				} else
					cellHeader.setCellStyle(cellStyleHeader);

				ExcelColumn excelColumn = listSheetHeader.get(indexHeader).getExcelColumn();
				setColumnWidth(sheet, columnNum, sheetHeader.getExcelColumnWidth().width());
				listSheetHeader.get(indexHeader).setNumColumn(columnNum);
				cellHeader.setCellValue(this.valueProps.valueProps(excelColumn.name()));
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
						CellStyle cellSuperHeaderStyle = ExcelLayoutUtility.manageCellStyleHeader(workbook, headerGroup,this.mapCellHeaderStyle);
						cellSuperHeader.setCellStyle(cellSuperHeaderStyle);
						if (setCellValue)
							cellSuperHeader.setCellValue(this.valueProps.valueProps(headerGroup.columnName()));
						setCellValue = false;
					}

					String mergeCell = ExcelBuildFunctionUtility.buildFunction(sheet, indexStartSuperHeader, headerGroup.columnRange(), RowStartEndType.ROW_EMPTY,mapFieldColumn,mapSheet);
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
	 * @param sheet the sheet
	 * @param index the index column
	 * @param width the width
	 * @throws Exception the exception
	 */
	protected void setColumnWidth(Sheet sheet, Integer index, Integer width) throws Exception {
		if (!this.mapWidthColumn.containsKey(index) || this.mapWidthColumn.get(index) < width) {
			this.mapWidthColumn.put(Integer.valueOf(index), Integer.valueOf(width));
			sheet.setColumnWidth(index, ExcelUtils.widthColumn(width));
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
		Set<Field> listField = SpreadsheetUtils.getListField(sheetData.getRowClass());
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
		if (sheetHeader.isDropDown()) {
			DropDownCell dropDownCell = null;
			dropDownCell = new DropDownCell(sheet, sheetHeader, firstRow, lastRow, firstCol, lastCol, indexRow);
			try {
				this.addDropDown(dropDownCell);
			} catch (Exception e) {
				this.listDropDown.add(dropDownCell);
			}
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
		DataValidationConstraint constraint = null;
		DataValidation dataValidation = null;
		DataValidationHelper validationHelper = sheet.getDataValidationHelper();
		CellRangeAddressList addressList = new CellRangeAddressList(dropDownCell.getFirstRow(), dropDownCell.getLastRow(), dropDownCell.getFirstCol(), dropDownCell.getLastCol());
		if (sheetHeader.getExcelDropDown() != null) {
			ExcelDropDown excelDropDown = sheetHeader.getExcelDropDown();
			String areaRange = excelDropDown.areaRange();
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, dropDownCell.getIndexRow(), areaRange, RowStartEndType.ROW_EMPTY, excelDropDown.alias(),mapFieldColumn,mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_START, excelDropDown.alias(),mapFieldColumn,mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_END, excelDropDown.alias(),mapFieldColumn,mapSheet);

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
					sdf = new SimpleDateFormat(sheetHeader.getExcelDate().value().getValue());
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