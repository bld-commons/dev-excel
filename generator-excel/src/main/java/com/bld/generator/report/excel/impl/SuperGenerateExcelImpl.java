/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.SuperGenerateExcelImpl.java
*/
package com.bld.generator.report.excel.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.common.spreadsheet.utils.ValueProps;
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
import com.bld.generator.report.excel.annotation.ExcelFunction;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
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
import com.bld.generator.report.excel.data.KeyParameterAlias;
import com.bld.generator.report.excel.data.LayoutCell;
import com.bld.generator.report.excel.data.MergeCell;
import com.bld.generator.report.excel.data.SheetHeader;
import com.bld.generator.report.excel.dropdown.DropDown;
import com.bld.generator.report.excel.sheet_mapping.SheetMappingSheet;
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;
import com.bld.generator.report.excel.utility.ExcelLayoutUtility;

/**
 * The Class SuperGenerateExcelImpl.
 */
public abstract class SuperGenerateExcelImpl {

	/** The merge calcolo cells. */
	protected CellStyle mergeCalcoloCells = null;

	/** The map cell style. */
	protected Map<LayoutCell, CellStyle> mapCellStyle = new HashMap<>();

	/** The map cell header style. */
	protected Map<LayoutCell, CellStyle> mapCellHeaderStyle = new HashMap<>();

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(SuperGenerateExcelImpl.class);

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

	protected ExcelLayoutUtility excelLayoutUtility;

	/** The value props. */
	protected ValueProps valueProps;

	/** The excel image manager. */
	protected ExcelImageManager excelImageManager;

	/** The excel drop down builder. */
	protected ExcelDropDownBuilder excelDropDownBuilder;

	/** The excel pivot builder. */
	protected ExcelPivotBuilder excelPivotBuilder;

	/** The excel sheet header builder. */
	protected ExcelSheetHeaderBuilder excelSheetHeaderBuilder;

	/**
	 * Instantiates a new super generate excel impl.
	 *
	 * @param excelLayoutUtility      the excel layout utility
	 * @param valueProps              the value props
	 * @param excelImageManager       the excel image manager
	 * @param excelDropDownBuilder    the excel drop down builder
	 * @param excelPivotBuilder       the excel pivot builder
	 * @param excelSheetHeaderBuilder the excel sheet header builder
	 */
	protected SuperGenerateExcelImpl(ExcelLayoutUtility excelLayoutUtility, ValueProps valueProps,
			ExcelImageManager excelImageManager, ExcelDropDownBuilder excelDropDownBuilder,
			ExcelPivotBuilder excelPivotBuilder, ExcelSheetHeaderBuilder excelSheetHeaderBuilder) {
		this.excelLayoutUtility = excelLayoutUtility;
		this.valueProps = valueProps;
		this.excelImageManager = excelImageManager;
		this.excelDropDownBuilder = excelDropDownBuilder;
		this.excelPivotBuilder = excelPivotBuilder;
		this.excelSheetHeaderBuilder = excelSheetHeaderBuilder;
	}

	/**
	 * Gets the list sheet header.
	 *
	 * @param classRow  the class row
	 * @param baseSheet the base sheet
	 * @param sheet     the sheet
	 * @return the list sheet header
	 * @throws Exception the exception
	 */
	protected List<SheetHeader> getListSheetHeader(Class<?> classRow, BaseSheet baseSheet, Sheet sheet) throws Exception {
		return excelSheetHeaderBuilder.getListSheetHeader(classRow, baseSheet);
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
		this.setCellValueExcel(workbook, cell, cellStyle, sheetHeader, indexRow, sheet);
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
		this.excelDropDownBuilder.manageDropDown(sheet, mergeRow.getSheetHeader(), mergeRow.getRowStart(), mergeRow.getRowStart(), numColumn, numColumn, indexRow, this.listDropDown, this.mapFieldColumn, this.mapSheet);
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
		CellStyle cellStyleColumn0 = this.excelLayoutUtility.createCellStyle(workbook, excelSummary.layout(), indexRow);
		Cell cellColumn0 = row.createCell(excelSheetLayout.startColumn());
		this.excelLayoutUtility.setCellStyleExcel(cellStyleColumn0, cellColumn0, layoutCellSummary, this.mapCellStyle);
		cellColumn0.setCellValue(this.valueProps.valueProps(sheetHeader.getExcelColumn().name()));
		if (StringUtils.isNotBlank(sheetHeader.getExcelColumn().comment()))
			addComment(workbook, sheet, row, cellColumn0, sheetHeader.getExcelColumn().comment());
		ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
		CellStyle cellStyleColumn1 = this.excelLayoutUtility.createCellStyle(workbook, excelCellLayout, sheetHeader, indexRow);
		int column = excelSheetLayout.startColumn() + 1;
		Cell cellColumn1 = row.createCell(column);
		this.excelDropDownBuilder.manageDropDown(sheet, sheetHeader, indexRow, indexRow, column, column, indexRow, this.listDropDown, this.mapFieldColumn, this.mapSheet);
		setCellValueExcel(workbook, sheet, cellColumn1, cellStyleColumn1, sheetHeader, cellColumn1.getRowIndex(), formulaEvaluator);
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
		//formulaEvaluator.evaluateFormulaCell(cell);
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
		this.excelLayoutUtility.setCellStyleExcel(cellStyle, cell, layoutCell, this.mapCellStyle);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		Map<String, KeyParameterAlias> mapExcelFormulaAlias = sheetHeader.getMapFormulaAlias();
		if (mapExcelFormulaAlias == null) {
			mapExcelFormulaAlias = ExcelBuildFunctionUtility.mapExcelFormulaAlias(excelFunction.alias());
			sheetHeader.setMapFormulaAlias(mapExcelFormulaAlias);
		}
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_EMPTY, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		if (excelFunction.anotherTable()) {
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_START, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_END, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		}
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_START, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_END, mapExcelFormulaAlias, mapFieldColumn, mapSheet);

		function = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, function, RowStartEndType.ROW_HEADER, mapExcelFormulaAlias, mapFieldColumn, mapSheet);

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
		setCellFormula(sheet, mergeRow, indexRow);
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
		this.excelLayoutUtility.setCellStyleExcel(cellStyle, cell, layoutCell, this.mapCellStyle);
		ExcelFunction excelFunction = sheetHeader.getExcelFunction();
		String function = excelFunction.function();
		Map<String, KeyParameterAlias> mapExcelFormulaAlias = sheetHeader.getMapFormulaAlias();
		if (mapExcelFormulaAlias == null) {
			mapExcelFormulaAlias = ExcelBuildFunctionUtility.mapExcelFormulaAlias(excelFunction.alias());
			sheetHeader.setMapFormulaAlias(mapExcelFormulaAlias);
		}
		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_EMPTY, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		if (excelFunction.anotherTable()) {
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_START, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_END, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		}

		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_START, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowEnd(), function, RowStartEndType.ROW_END, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		function = ExcelBuildFunctionUtility.buildFunction(sheet, mergeRow.getRowStart(), function, RowStartEndType.ROW_HEADER, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
		logger.debug("Function: " + function);
		cell.setCellFormula(function);
		return cell;
	}

	private void dataValidation(Sheet sheet, Cell cell, SheetHeader sheetHeader) throws Exception {
		if (sheetHeader.getExcelDataValidation() != null) {
			ExcelDataValidation excelDataValidation = sheetHeader.getExcelDataValidation();
			ExcelBoxMessage errorBox = excelDataValidation.errorBox();
			String formula = excelDataValidation.value();
			Map<String, KeyParameterAlias> mapExcelFormulaAlias = ExcelBuildFunctionUtility.mapExcelFormulaAlias(excelDataValidation.alias());
			formula = ExcelBuildFunctionUtility.buildFunction(sheet, cell.getRowIndex(), formula, RowStartEndType.ROW_EMPTY, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
			formula = ExcelBuildFunctionUtility.buildFunction(sheet, null, formula, RowStartEndType.ROW_START, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
			formula = ExcelBuildFunctionUtility.buildFunction(sheet, null, formula, RowStartEndType.ROW_END, mapExcelFormulaAlias, mapFieldColumn, mapSheet);
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
		this.excelLayoutUtility.setCellStyleExcel(cellStyle, cell, layoutCell, this.mapCellStyle);

		if (sheetHeader.getValue() instanceof Date)
			cell.setCellValue((Date) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Calendar)
			cell.setCellValue((Calendar) sheetHeader.getValue());
		else if (sheetHeader.getValue() instanceof Timestamp)
			cell.setCellValue(new Date(((Timestamp) sheetHeader.getValue()).getTime()));
		else if (sheetHeader.getValue() instanceof LocalDate) {
			ZoneId zone = SpreadsheetUtils.resolveZone(sheetHeader.getExcelDate(), this.valueProps);
			cell.setCellValue(Date.from(((LocalDate) sheetHeader.getValue()).atStartOfDay(zone).toInstant()));
		} else if (sheetHeader.getValue() instanceof LocalDateTime) {
			ZoneId zone = SpreadsheetUtils.resolveZone(sheetHeader.getExcelDate(), this.valueProps);
			cell.setCellValue(Date.from(((LocalDateTime) sheetHeader.getValue()).atZone(zone).toInstant()));
		} else if (sheetHeader.getValue() instanceof Instant) {
			cell.setCellValue(Date.from((Instant) sheetHeader.getValue()));
		} else if (sheetHeader.getValue() instanceof OffsetDateTime) {
			ZoneId zone = SpreadsheetUtils.resolveZone(sheetHeader.getExcelDate(), this.valueProps);
			cell.setCellValue(Date.from(((OffsetDateTime) sheetHeader.getValue()).atZoneSameInstant(zone).toInstant()));
		} else if (sheetHeader.getValue() instanceof String || sheetHeader.getValue() instanceof Character) {
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
			this.excelImageManager.addAttachment(workbook, sheet, sheetHeader, cell);
		} else if (sheetHeader.getValue() instanceof byte[])
			this.excelImageManager.addImage(workbook, sheet, sheetHeader, cell);
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
		List<SheetHeader> listSheetHeader = this.excelSheetHeaderBuilder.getListSheetHeader(sheetData.getRowClass(), null);
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
					BeanUtils.copyProperties(extraColumnAnnotation, sheetHeader);
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
			cellStyleHeader = this.excelLayoutUtility.getCellStyleHeader(workbook, sheet, sheetData, rowHeader, this.mapCellHeaderStyle);

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
					CellStyle differentCellStyleHeader = this.excelLayoutUtility.manageCellStyleHeader(workbook, layoutHeader, this.mapCellHeaderStyle);
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

					for (int columnNum = startColumn; columnNum <= endColumn; columnNum++) {
						cellSuperHeader = rowSuperHeader.createCell(columnNum);
						CellStyle cellSuperHeaderStyle = this.excelLayoutUtility.manageCellStyleHeader(workbook, headerGroup, this.mapCellHeaderStyle);
						cellSuperHeader.setCellStyle(cellSuperHeaderStyle);
						if (setCellValue)
							cellSuperHeader.setCellValue(this.valueProps.valueProps(headerGroup.columnName()));
						setCellValue = false;
					}

					String mergeCell = ExcelBuildFunctionUtility.buildFunction(sheet, indexStartSuperHeader, headerGroup.columnRange(), RowStartEndType.ROW_EMPTY, mapFieldColumn, mapSheet);
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
	 * Manage drop down.
	 *
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param firstRow    the first row
	 * @param lastRow     the last row
	 * @param firstCol    the first col
	 * @param lastCol     the last col
	 * @param indexRow    the index row
	 */
	protected void manageDropDown(Sheet sheet, SheetHeader sheetHeader, int firstRow, int lastRow, int firstCol, int lastCol, Integer indexRow) {
		this.excelDropDownBuilder.manageDropDown(sheet, sheetHeader, firstRow, lastRow, firstCol, lastCol, indexRow, this.listDropDown, this.mapFieldColumn, this.mapSheet);
	}

}
