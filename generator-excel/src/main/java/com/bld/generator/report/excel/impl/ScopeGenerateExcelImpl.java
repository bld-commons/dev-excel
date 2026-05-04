/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ScopeGenerateExcelImpl.java
*/
package com.bld.generator.report.excel.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.io.Resource;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.DynamicChart;
import com.bld.generator.report.excel.DynamicRowSheet;
import com.bld.generator.report.excel.FunctionsTotal;
import com.bld.generator.report.excel.LoadSheetData;
import com.bld.generator.report.excel.MergeSheet;
import com.bld.generator.report.excel.QuerySheetData;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.ScopeGenerateExcel;
import com.bld.generator.report.excel.SheetComponent;
import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.SheetFunctionTotal;
import com.bld.generator.report.excel.SheetSummary;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelChart;
import com.bld.generator.report.excel.annotation.ExcelChartCategory;
import com.bld.generator.report.excel.annotation.ExcelCharts;
import com.bld.generator.report.excel.annotation.ExcelClearRows;
import com.bld.generator.report.excel.annotation.ExcelConditionCellLayouts;
import com.bld.generator.report.excel.annotation.ExcelFreezePane;
import com.bld.generator.report.excel.annotation.ExcelLabel;
import com.bld.generator.report.excel.annotation.ExcelPivot;
import com.bld.generator.report.excel.annotation.ExcelRowHeight;
import com.bld.generator.report.excel.annotation.ExcelSelectCell;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;
import com.bld.generator.report.excel.annotation.ExcelSubtotals;
import com.bld.generator.report.excel.annotation.ExcelSummary;
import com.bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import com.bld.generator.report.excel.data.DropDownCell;
import com.bld.generator.report.excel.data.FunctionCell;
import com.bld.generator.report.excel.data.InfoChart;
import com.bld.generator.report.excel.data.InfoColumn;
import com.bld.generator.report.excel.data.LayoutCell;
import com.bld.generator.report.excel.data.MergeCell;
import com.bld.generator.report.excel.data.ReportExcel;
import com.bld.generator.report.excel.data.SheetHeader;
import com.bld.generator.report.excel.data.SubtotalRow;
import com.bld.generator.report.excel.query.ExcelQueryComponent;
import com.bld.generator.report.excel.sheet_mapping.SheetMappingRow;
import com.bld.generator.report.excel.sheet_mapping.SheetMappingSheet;
import com.bld.generator.report.excel.utility.ExcelAreaBorderUtility;
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;
import com.bld.generator.report.excel.utility.ExcelLayoutUtility;
import com.bld.generator.report.excel.utility.ExcelSheetLockUtility;

/**
 * The Class ScopeGenerateExcelImpl.<br>
 * ScopeGenerateExcelImpl is the heart of the generation of the xls or xlsx
 * files.
 */
@SuppressWarnings("unchecked")
public class ScopeGenerateExcelImpl extends SuperGenerateExcelImpl implements ScopeGenerateExcel {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ScopeGenerateExcelImpl.class);

	/** The number empty rows. */
	private final int numberEmptyRows;

	/** The cover. */
	private final Resource cover;

	/** The excel query component. */
	private final ExcelQueryComponent excelQueryComponent;

	/** The map sub totals. */
	private Map<String, Integer> mapSubTotals = new HashMap<>();

	/** The conditional cell layout. */
	private final ConditionalCellLayout conditionalCellLayout;

	/** The excel chart builder. */
	private final ExcelChartBuilder excelChartBuilder;

	/** The excel subtotal writer. */
	private final ExcelSubtotalWriter excelSubtotalWriter;

	/**
	 * Instantiates a new scope generate excel impl.
	 *
	 * @param excelLayoutUtility      the excel layout utility
	 * @param valueProps              the value props
	 * @param excelImageManager       the excel image manager
	 * @param excelDropDownBuilder    the excel drop down builder
	 * @param excelPivotBuilder       the excel pivot builder
	 * @param excelSheetHeaderBuilder the excel sheet header builder
	 * @param excelQueryComponent     the excel query component
	 * @param conditionalCellLayout   the conditional cell layout
	 * @param excelChartBuilder       the excel chart builder
	 * @param excelSubtotalWriter     the excel subtotal writer
	 * @param numberEmptyRows         the number empty rows
	 * @param cover                   the cover
	 */
	public ScopeGenerateExcelImpl(ExcelLayoutUtility excelLayoutUtility, ValueProps valueProps,
			ExcelImageManager excelImageManager, ExcelDropDownBuilder excelDropDownBuilder,
			ExcelPivotBuilder excelPivotBuilder, ExcelSheetHeaderBuilder excelSheetHeaderBuilder,
			ExcelQueryComponent excelQueryComponent, ConditionalCellLayout conditionalCellLayout,
			ExcelChartBuilder excelChartBuilder, ExcelSubtotalWriter excelSubtotalWriter,
			int numberEmptyRows, Resource cover) {
		super(excelLayoutUtility, valueProps, excelImageManager, excelDropDownBuilder, excelPivotBuilder, excelSheetHeaderBuilder);
		this.excelQueryComponent = excelQueryComponent;
		this.conditionalCellLayout = conditionalCellLayout;
		this.excelChartBuilder = excelChartBuilder;
		this.excelSubtotalWriter = excelSubtotalWriter;
		this.numberEmptyRows = numberEmptyRows;
		this.cover = cover;
	}

	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	@Override
	public byte[] createFileXls(ReportExcel report) throws Exception {
		this.mergeCalcoloCells = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		HSSFWorkbook workbook = null;
		byte[] result = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			boolean isCover = true;
			if (!report.isIgnoreCover() && this.cover != null)
				workbook = new HSSFWorkbook(this.cover.getInputStream());
			else {
				workbook = new HSSFWorkbook();
				isCover = false;
			}
			this.setCoverParameters(report, byteArrayOutputStream, workbook, isCover);
			result = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
			if (byteArrayOutputStream != null)
				byteArrayOutputStream.close();
		}

		return result;
	}

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	@Override
	public byte[] createFileXlsx(ReportExcel report) throws Exception {
		this.mergeCalcoloCells = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		XSSFWorkbook workbook = null;
		byte[] result = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			boolean isCover = true;
			if (!report.isIgnoreCover() && this.cover != null)
				workbook = new XSSFWorkbook(this.cover.getInputStream());
			else {
				workbook = new XSSFWorkbook();
				isCover = false;
			}

			this.setCoverParameters(report, byteArrayOutputStream, workbook, isCover);
			result = byteArrayOutputStream.toByteArray();

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
			if (byteArrayOutputStream != null)
				byteArrayOutputStream.close();

		}
		return result;
	}

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * @throws Exception the exception
	 */
	@Override
	public byte[] createBigDataFileXlsx(ReportExcel report) throws Exception {
		this.mergeCalcoloCells = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		SXSSFWorkbook workbook = null;
		XSSFWorkbook coverWorkbook = null;
		byte[] result = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			// boolean isCover = true;
			if (!report.isIgnoreCover() && this.cover != null) {
				coverWorkbook = new XSSFWorkbook(this.cover.getInputStream());
				updateCover(report, coverWorkbook);
				workbook = new SXSSFWorkbook(coverWorkbook);
			} else {
				workbook = new SXSSFWorkbook();
			}
			this.setCoverParameters(report, byteArrayOutputStream, workbook, false);
			result = byteArrayOutputStream.toByteArray();

		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null) {

				workbook.close();
			}
			if (byteArrayOutputStream != null)
				byteArrayOutputStream.close();
		}
		return result;
	}

	/**
	 * Sets the cover parameters.
	 *
	 * @param report       the report
	 * @param outputStream the byte array output stream
	 * @param workbook     the workbook
	 * @param isCopertina  the is copertina
	 * @throws Exception the exception
	 */
	private void setCoverParameters(ReportExcel report, OutputStream outputStream, Workbook workbook, boolean isCopertina) throws Exception {
		if (isCopertina) {
			updateCover(report, workbook);

		}

		workbook = createSheets(report, workbook);

		workbook.write(outputStream);

	}

	/**
	 * Update cover.
	 *
	 * @param report   the report
	 * @param workbook the workbook
	 * @throws Exception the exception
	 */
	private void updateCover(ReportExcel report, Workbook workbook) throws Exception {
		Set<Field> listField = SpreadsheetUtils.getListField(report.getClass());
		Sheet sheet = workbook.getSheetAt(0);
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelSelectCell.class)) {
				ExcelSelectCell excelSelectCell = field.getAnnotation(ExcelSelectCell.class);
				CellReference cellReference = new CellReference(this.valueProps.valueProps(excelSelectCell.cellReference()));
				CellStyle cellStyle = sheet.getRow(cellReference.getRow()).getCell(cellReference.getCol()).getCellStyle();
				cellStyle.setWrapText(true);
				Cell cell = sheet.getRow(cellReference.getRow()).getCell(cellReference.getCol());
				Object value = new BeanWrapperImpl(report).getPropertyValue(field.getName());
				ExcelDate excelDate = null;
				if (Date.class.isAssignableFrom(field.getType()) || Calendar.class.isAssignableFrom(field.getType()) || Timestamp.class.isAssignableFrom(field.getType())) {
					excelDate = SpreadsheetUtils.getAnnotation(field, ExcelDate.class);
					cellStyle = this.excelLayoutUtility.dateCellStyle(workbook, cellStyle, excelDate.value().getValue());
					cell.setCellStyle(cellStyle);
				}
				if (value != null) {
					if (value instanceof Date)
						cell.setCellValue((Date) value);
					else if (value instanceof Calendar)
						cell.setCellValue((Calendar) value);
					else if (value instanceof String || value instanceof Character) {
						value = "" + value;
						cell.setCellValue((String) value);
					} else if (value instanceof Number)
						cell.setCellValue(((Number) value).doubleValue());
					else if (value instanceof Boolean)
						cell.setCellValue((Boolean) value);
				}

			}
		}
	}

	/**
	 * Creates the sheets.<br>
	 *
	 * It scores the {@link com.bld.generator.report.excel.BaseSheet} list, each
	 * {@link com.bld.generator.report.excel.BaseSheet} is equivalent to one
	 * sheet.<br>
	 *
	 * @param report   the report
	 * @param workbook the workbook
	 * @return the workbook
	 * @throws Exception the exception
	 */
	private Workbook createSheets(ReportExcel report, Workbook workbook) throws Exception {
		if (report.isEnableSheetMapping()) {
			this.sheetMapping = new SheetMappingSheet();
			report.addSheets(this.sheetMapping);
		}

		List<BaseSheet> listSheet = report.getSheets();
		int indexSheetName = 0;

		this.mapCellStyle = new HashMap<>();
		this.mapCellHeaderStyle = new HashMap<>();
		this.mapFieldColumn = new HashMap<>();
		this.listFunctionCell = new ArrayList<>();
		this.listDropDown = new ArrayList<>();
		this.mapSheet = new HashMap<>();
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
		for (BaseSheet baseSheet : listSheet) {
			Date startSheet = new Date();
			Sheet sheet = null;
			this.mapWidthColumn = new HashMap<>();
			if (baseSheet.getSheetName() != null) {
				if (workbook.getSheet(baseSheet.getSheetName()) == null && baseSheet.getSheetName().length() <= SpreadsheetUtils.SHEET_NAME_SIZE)
					sheet = workbook.createSheet(baseSheet.getSheetName().replace("/", ""));
				else {
					logger.warn("Sheet name exceeded the maximum limit " + SpreadsheetUtils.SHEET_NAME_SIZE + " characters");
					sheet = workbook.createSheet((indexSheetName++) + "-" + baseSheet.getSheetName().replace("/", ""));
				}

			} else
				sheet = workbook.createSheet();
			logger.info("Sheet name: " + sheet.getSheetName());
			this.mapSheet.put(sheet.getSheetName(), baseSheet);
			Footer footer = sheet.getFooter();
			footer.setRight("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());

			sheet.setForceFormulaRecalculation(true);
			if (baseSheet instanceof MergeSheet) {
				this.generateMergeSheet(workbook, sheet, (MergeSheet) baseSheet, formulaEvaluator);
			} else if (baseSheet instanceof SheetSummary) {
				this.generateSheetSummary(workbook, sheet, (SheetSummary) baseSheet, 0, formulaEvaluator);
			} else if (baseSheet instanceof SheetData) {
				this.generateSheetData(workbook, sheet, (SheetData<? extends RowSheet>) baseSheet, 0, false, formulaEvaluator);
			}
			ExcelSheetLayout excelSheetLayout = SpreadsheetUtils.getAnnotation(baseSheet.getClass(), ExcelSheetLayout.class);
			ExcelSheetLockUtility.applySheetLock(workbook, sheet, baseSheet, excelSheetLayout, this.valueProps);

			// formulaEvaluator.evaluateAll();
			Date endSheet = new Date();
			double time = (endSheet.getTime() - startSheet.getTime()) / 1000.0;
			logger.info("Time writing sheet \"" + baseSheet.getSheetName() + "\": " + time + "s");
		}

		for (FunctionCell functionCell : listFunctionCell) {
			Sheet sheet = functionCell.getCell().getSheet();
			if (functionCell.getMergeRow() != null)
				this.setCellFormulaAndEvaluate(sheet, functionCell.getMergeRow(), 0, functionCell.getFormulaEvaluator());
			else
				this.setCellFormulaAndEvaluateCell(functionCell.getCell(), functionCell.getCell().getCellStyle(), functionCell.getSheetHeader(), functionCell.getCell().getRowIndex(), sheet, functionCell.getFormulaEvaluator());

			// evaluateAllFormulaCells(workbook, sheet);
		}

		workbook.setForceFormulaRecalculation(true);

		for (int i = 0; i < workbook.getNumberOfSheets(); i++)
			workbook.setSheetName(i, workbook.getSheetName(i).replace(BaseSheet.APOS, "'"));

		for (DropDownCell dropDownCell : this.listDropDown)
			this.excelDropDownBuilder.addDropDown(dropDownCell, this.mapFieldColumn, this.mapSheet);

		return workbook;
	}

	/**
	 * Generate merge sheet.<br>
	 *
	 * To manage the {@link com.bld.generator.report.excel.MergeSheet} classes.
	 *
	 * @param workbook         the workbook
	 * @param worksheet        the worksheet
	 * @param mergeSheet       the merge sheet
	 * @param formulaEvaluator the formula evaluator
	 * @throws Exception the exception
	 */
	private void generateMergeSheet(Workbook workbook, Sheet worksheet, MergeSheet mergeSheet, FormulaEvaluator formulaEvaluator) throws Exception {
		Integer indexRow = Integer.valueOf(0);
		if (mergeSheet.getClass().isAnnotationPresent(ExcelFreezePane.class)) {
			ExcelFreezePane excelFreezePane = mergeSheet.getClass().getAnnotation(ExcelFreezePane.class);
			worksheet.createFreezePane(excelFreezePane.columnFreez(), excelFreezePane.rowFreez());
		}
		for (SheetComponent sheetComponent : mergeSheet.getSheets()) {
			sheetComponent.setSheetName(worksheet.getSheetName());
			if (sheetComponent instanceof SheetSummary)
				indexRow = this.generateSheetSummary(workbook, worksheet, (SheetSummary) sheetComponent, indexRow, formulaEvaluator);
			else if (sheetComponent instanceof SheetData)
				indexRow = this.generateSheetData(workbook, worksheet, (SheetData<? extends RowSheet>) sheetComponent, indexRow, true, formulaEvaluator);
			indexRow += 2;
		}
	}

	/**
	 * Generate sheet summary.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the worksheet
	 * @param sheetSummary     the sheet summary
	 * @param indexRow         the index row
	 * @param formulaEvaluator the formula evaluator
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer generateSheetSummary(Workbook workbook, Sheet sheet, SheetSummary sheetSummary, Integer indexRow, FormulaEvaluator formulaEvaluator) throws Exception {
		Class<? extends SheetSummary> classSheet = sheetSummary.getClass();
		ExcelSummary excelSummary = classSheet.getAnnotation(ExcelSummary.class);
		ExcelSheetLayout excelSheetLayout = SpreadsheetUtils.getAnnotation(sheetSummary.getClass(), ExcelSheetLayout.class);
		indexRow += excelSheetLayout.startRow();
		if (indexRow < 0)
			throw new ExcelGeneratorException("The row number cannot be negative");
		if (excelSheetLayout.showHeader() && excelSummary != null && StringUtils.isNotBlank(excelSummary.title())) {
			Row rowHeader = sheet.createRow(indexRow);
			CellStyle cellStyleHeader = this.excelLayoutUtility.getCellStyleHeader(workbook, sheet, sheetSummary, rowHeader, this.mapCellHeaderStyle);
			Cell cellHeader = rowHeader.createCell(excelSheetLayout.startColumn());
			cellHeader.setCellStyle(cellStyleHeader);
			String title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelSummary.title(), RowStartEndType.ROW_START, mapFieldColumn, mapSheet);
			title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, title, RowStartEndType.ROW_END, mapFieldColumn, mapSheet);
			title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, title, RowStartEndType.ROW_EMPTY, mapFieldColumn, mapSheet);
			title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, title, RowStartEndType.ROW_HEADER, mapFieldColumn, mapSheet);
			if (excelSummary.titleCellFormulta())
				cellHeader.setCellFormula(title);
			else {
				title = title.replace("\"", "").replace(BaseSheet.APOS, "'");
				cellHeader.setCellValue(title);
			}

			if (StringUtils.isNotBlank(excelSummary.comment()))
				addComment(workbook, sheet, rowHeader, cellHeader, excelSummary.comment());
			cellHeader = rowHeader.createCell(excelSheetLayout.startColumn() + 1);
			cellHeader.setCellStyle(cellStyleHeader);
			sheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, excelSheetLayout.startColumn(), excelSheetLayout.startColumn() + 1));
			setColumnWidth(sheet, excelSheetLayout.startColumn(), excelSummary.widthColumn1());
			setColumnWidth(sheet, excelSheetLayout.startColumn() + 1, excelSummary.widthColumn2());
			indexRow++;
		}
		List<SheetHeader> listSheetHeader = getListSheetHeader(classSheet, sheetSummary, sheet);
		Row row = null;
		for (SheetHeader sheetHeader : listSheetHeader) {
			row = sheet.createRow(indexRow);
			setCellSummary(excelSheetLayout, workbook, sheet, sheetSummary, sheetHeader, row, indexRow, formulaEvaluator);
			indexRow++;
		}
		return indexRow;

	}

	/**
	 * Generate sheet data.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param sheetData        the sheet data
	 * @param indexRow         the index row
	 * @param isMergeSheet     the is merge sheet
	 * @param formulaEvaluator the formula evaluator
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer generateSheetData(Workbook workbook, Sheet sheet, SheetData<? extends RowSheet> sheetData, Integer indexRow, boolean isMergeSheet, FormulaEvaluator formulaEvaluator) throws Exception {
		logger.info("SheetData: " + sheetData.getClass().getSimpleName());
		logger.info("RowSheet: " + sheetData.getRowClass().getSimpleName());
		if (this.excelQueryComponent != null && sheetData instanceof QuerySheetData querySheetData)
			this.excelQueryComponent.executeQuery(querySheetData);
		else if (sheetData instanceof LoadSheetData<?, ?> loadSheetData)
			loadSheetData.initRows();
		// this.mapFieldColumn = sheetData.getMapFieldColumn();
		ExcelSheetLayout excelSheetLayout = SpreadsheetUtils.getAnnotation(sheetData.getClass(), ExcelSheetLayout.class);

		indexRow += excelSheetLayout.startRow();
		SheetMappingRow sheetMappingRow = null;
		if (indexRow < 0)
			throw new ExcelGeneratorException("The row number cannot be negative");

		indexRow = writeLabel(workbook, sheet, sheetData, indexRow, formulaEvaluator);
		indexRow = indexRow + getSizeSuperHeader(sheetData);
		int startRowSheet = indexRow + 1;
		List<SheetHeader> listSheetHeader = generateHeaderSheetData(workbook, sheet, sheetData, indexRow);
		if (excelSheetLayout.showHeader())
			indexRow++;

		boolean start = true;
		// CellStyle cellStyle = null;
		Map<Integer, MergeCell> mapMergeRow = new HashMap<>();
		RowSheet lastRowSheet = null;

		Map<String, Map<String, InfoChart>> mapChart = new LinkedHashMap<>();

		// int i=0;
		if (!isMergeSheet && sheetData.getClass().isAnnotationPresent(ExcelFreezePane.class)) {
			ExcelFreezePane excelFreezePane = sheetData.getClass().getAnnotation(ExcelFreezePane.class);
			sheet.createFreezePane(excelFreezePane.columnFreez(), excelFreezePane.rowFreez());
		}
		Row row = null;
		int maxColumn = listSheetHeader.size() + excelSheetLayout.startColumn();
		short heightRow = ExcelUtils.AUTO_SIZE_HEIGHT;
		if (sheetData.getRowClass().isAnnotationPresent(ExcelRowHeight.class)) {
			ExcelRowHeight excelRowHeight = sheetData.getRowClass().getAnnotation(ExcelRowHeight.class);
			heightRow = ExcelUtils.rowHeight(excelRowHeight.height());
		}
		if (this.sheetMapping != null && sheetData.isEnableInfoSheet()) {
			sheetMappingRow = new SheetMappingRow();
			sheetMappingRow.setSheet(sheet.getSheetName());
			sheetMappingRow.setFirstRow(indexRow);
			sheetMappingRow.setFirstColumn(excelSheetLayout.startColumn());
			sheetMappingRow.setLastColumn(maxColumn);
			sheetMappingRow.setRowsNumber(sheetData.getRows().size());
			this.sheetMapping.addRows(sheetMappingRow);
		}
		List<SubtotalRow> emptyRows = new ArrayList<>();
		ExcelSubtotals excelSubtotals = sheetData.getRowClass().getAnnotation(ExcelSubtotals.class);
		final boolean enableSumForGroup = excelSubtotals != null && ArrayUtils.isNotEmpty(excelSubtotals.sumForGroup());
		List<String> sumForGroups = new ArrayList<>();
		Map<String, Object> mapValue = new HashMap<>();
		if (enableSumForGroup) {
			List<String> sfg = Arrays.asList(excelSubtotals.sumForGroup());
			for (int i = listSheetHeader.size() - 1; i >= 0; i--) {
				SheetHeader sheetHeader = listSheetHeader.get(i);
				String fieldName = getFieldName(sheetHeader);
				if (sfg.contains(fieldName))
					sumForGroups.add(fieldName);
			}
		}

		for (RowSheet rowSheet : sheetData.getRows()) {
			int splitRow = 0;
			BeanWrapperImpl beanWrapper = new BeanWrapperImpl(rowSheet);
			BeanWrapperImpl lastBeanWrapper = lastRowSheet != null ? new BeanWrapperImpl(lastRowSheet) : null;
			if (rowSheet.getClass().isAnnotationPresent(ExcelSubtotals.class)) {
				if (enableSumForGroup && lastRowSheet != null) {
					for (String fieldName : sumForGroups)
						if (!lastBeanWrapper.getPropertyValue(fieldName).equals(beanWrapper.getPropertyValue(fieldName)))
							splitRow = sumForGroups.indexOf(fieldName) + 1;
					for (int i = 0; i < splitRow; i++) {
						String fieldName = sumForGroups.get(i);
						Integer firstRow = startRowSheet;
						Integer lastRow = indexRow.intValue();
						if (!mapSubTotals.containsKey(fieldName))
							indexRow = this.excelSubtotalWriter.mapRowSubTotals(indexRow, lastRowSheet, emptyRows, fieldName, firstRow, lastRow, mapSubTotals);
						else {
							firstRow = mapSubTotals.get(fieldName);
							indexRow = this.excelSubtotalWriter.mapRowSubTotals(indexRow, lastRowSheet, emptyRows, fieldName, firstRow, lastRow, mapSubTotals);
						}

					}
				}
			}
			row = sheet.createRow(indexRow);
			mapValue.clear();
			CellStyle cellStyle = null;
			row.setHeight(heightRow);
			for (int numColumn = excelSheetLayout.startColumn(); numColumn < maxColumn; numColumn++) {
				int indexHeader = numColumn - excelSheetLayout.startColumn();
				SheetHeader sheetHeader = listSheetHeader.get(indexHeader);
				CellType cellType = sheetHeader.getCellType();
				Cell cell = row.createCell(numColumn, cellType);
				InfoColumn infoColumn = (InfoColumn) mapFieldColumn.get(sheetHeader.getKey());

				Field field = sheetHeader.getField();
				Object value = null;
				if (sheetHeader.getField() != null) {
					value = beanWrapper.getPropertyValue(field.getName());
					mapValue.put(field.getName(), value);
				} else if (StringUtils.isNotBlank(sheetHeader.getKeyMap())) {
					DynamicRowSheet dynamicRowSheet = (DynamicRowSheet) rowSheet;
					value = dynamicRowSheet.getMapValue().get(sheetHeader.getKeyMap());
					mapValue.put(sheetHeader.getKeyMap(), value);
				}
				value = this.excelImageManager.manageExcelImage(sheetHeader, value);

				sheetHeader.setValue(value);
				if (start) {
					ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
					LayoutCell layoutCell = sheetHeader.getLayoutCell(indexRow);
					int colorSize = excelCellLayout.rgbFont().length > excelCellLayout.rgbForeground().length ? excelCellLayout.rgbFont().length : excelCellLayout.rgbForeground().length;
					for (int colorModul = 0; colorModul < colorSize; colorModul++) {
						LayoutCell layoutCellTemp = sheetHeader.getLayoutCell(colorModul);
						if (!this.mapCellStyle.containsKey(layoutCellTemp))
							this.mapCellStyle.put(layoutCellTemp, this.excelLayoutUtility.createCellStyle(workbook, excelCellLayout, sheetHeader, colorModul));
					}
					cellStyle = this.mapCellStyle.get(layoutCell);
					infoColumn.setFirstRow(indexRow);
					infoColumn.setLastRow(indexRow + sheetData.getRows().size() - 1);
				} else
					infoColumn.incrementLastRow(splitRow);
				boolean repeat = true;

				do {
					MergeCell mergeRow = null;
					Object valueBefore = null;
					if (excelSheetLayout.notMerge() || !mapMergeRow.containsKey(numColumn)) {
						if (!excelSheetLayout.notMerge() && sheetHeader.getExcelMergeRow() != null) {
							mergeRow = new MergeCell();
							mergeRow.setRowStart(indexRow);
							mergeRow.setColumnFrom(numColumn);
							mergeRow.setColumnTo(numColumn);
							mergeRow.setSheetHeader((SheetHeader) sheetHeader.clone());
							if (sheetHeader.getExcelFunction() == null)
								mergeRow.getSheetHeader().setValue(value);
							mergeRow.setCellFrom(cell);
							mergeRow.setCellStyleFrom(cellStyle);
							infoColumn.setLastRowReference(indexRow);
							infoColumn.getMapRowMergeRow().put(indexRow, mergeRow);
							mapMergeRow.put(numColumn, mergeRow);
						} else {
							super.manageDropDown(sheet, sheetHeader, cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex(), indexRow);
							super.setCellValueExcel(workbook, sheet, cell, cellStyle, sheetHeader, indexRow, formulaEvaluator);
						}

						repeat = false;
					} else {
						int workRow = indexRow - splitRow;
						infoColumn.getMapRowMergeRow().put(workRow, infoColumn.getMergeCell());
						if (numColumn > excelSheetLayout.startColumn() && ArrayUtils.isEmpty(sheetHeader.getExcelMergeRow().referenceField()))
							throw new ExcelGeneratorException("Only first column can have the propertie \"referenceColumn\" is blank!!!");
						if (field != null)
							valueBefore = lastBeanWrapper.getPropertyValue(field.getName());
						if (ArrayUtils.isEmpty(sheetHeader.getExcelMergeRow().referenceField())) {
							if (!(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
								super.mergeRowAndRemoveMap(workbook, sheet, workRow, mapMergeRow, numColumn, formulaEvaluator);
							else
								repeat = super.setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader, workRow, sheet);

						} else if (ArrayUtils.isNotEmpty(sheetHeader.getExcelMergeRow().referenceField())) {
							if (checkMergeColumn(sheetHeader, rowSheet, lastRowSheet, valueBefore, listSheetHeader))
								super.mergeRowAndRemoveMap(workbook, sheet, workRow, mapMergeRow, numColumn, formulaEvaluator);
							else
								repeat = super.setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader, workRow, sheet);
						}

					}

				} while (repeat);

			}
			lastRowSheet = rowSheet;

			if (sheetData.getClass().isAnnotationPresent(ExcelCharts.class) || (sheetData instanceof DynamicChart && CollectionUtils.isNotEmpty(((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart()))) {
				List<ExcelChart> listExcelChart = this.excelChartBuilder.getExcelChart(sheetData);
				for (ExcelChart excelChart : listExcelChart) {
					for (ExcelChartCategory excelChartCategory : excelChart.excelChartCategories()) {
						String functionChart = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelChartCategory.function(), RowStartEndType.ROW_EMPTY, true, true, mapFieldColumn, mapSheet);
						String title = "";
						if (mapValue.containsKey(excelChartCategory.fieldName().replace("${", "").replace("}", ""))) {
							title = mapValue.get(excelChartCategory.fieldName().replace("${", "").replace("}", "")).toString();
							this.excelChartBuilder.configMapChart(mapChart, excelChart, excelChartCategory, functionChart, title, indexRow.intValue(), indexRow);

						} else {
							Integer rowRegexIndex = null;
							if (StringUtils.isNotEmpty(excelChartCategory.rowRegex())) {
								int lastRow = sheet.getLastRowNum() + 1;
								sheet.createRow(lastRow).createCell(0, CellType.FORMULA);
								Cell lastCell = sheet.getRow(lastRow).getCell(0);
								String rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelChartCategory.rowRegex(), RowStartEndType.ROW_EMPTY, true, true, mapFieldColumn, mapSheet);
								rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, rowRegex, RowStartEndType.ROW_HEADER, true, true, mapFieldColumn, mapSheet);
								rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, null, rowRegex, RowStartEndType.ROW_START, true, true, mapFieldColumn, mapSheet);
								rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, null, rowRegex, RowStartEndType.ROW_END, true, true, mapFieldColumn, mapSheet);
								lastCell.setCellFormula(rowRegex);
								CellType cellType = formulaEvaluator.evaluateFormulaCell(lastCell);
								if (!CellType.NUMERIC.equals(cellType))
									throw new ExcelGeneratorException("Row Regex: \"" + rowRegex + "\" need to return a numeric value");
								rowRegexIndex = (int) lastCell.getNumericCellValue();
								sheet.removeRow(sheet.getRow(lastRow));
							}
							String areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelChartCategory.fieldName(), RowStartEndType.ROW_EMPTY, true, true, mapFieldColumn, mapSheet);
							areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, areaFieldName, RowStartEndType.ROW_HEADER, true, true, mapFieldColumn, mapSheet);
							areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaFieldName, RowStartEndType.ROW_START, true, true, mapFieldColumn, mapSheet);
							areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaFieldName, RowStartEndType.ROW_END, true, true, mapFieldColumn, mapSheet);
							AreaReference areaReference = new AreaReference(areaFieldName, excelChart.spreadsheetVersion());
							for (CellReference cr : areaReference.getAllReferencedCells()) {
								Sheet appSheet = StringUtils.isNotEmpty(cr.getSheetName()) ? workbook.getSheet(cr.getSheetName()) : sheet;
								title = appSheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
								Integer firstRow = indexRow;
								if (!sheet.getSheetName().equals(appSheet.getSheetName())) {
									firstRow = cr.getRow();
									functionChart = ExcelBuildFunctionUtility.buildFunction(sheet, cr.getRow(), excelChartCategory.function(), RowStartEndType.ROW_EMPTY, true, true, mapFieldColumn, mapSheet);
								}

								if (rowRegexIndex == null)
									this.excelChartBuilder.configMapChart(mapChart, excelChart, excelChartCategory, functionChart, title, indexRow, indexRow);
								else if (rowRegexIndex.intValue() == firstRow.intValue())
									this.excelChartBuilder.configMapChart(mapChart, excelChart, excelChartCategory, functionChart, title, indexRow, indexRow);
							}

						}

					}

				}

			}

			start = false;
			indexRow++;
			// this.evaluateAllFormulaCells(workbook, sheet);
		}

		logger.info("rows completed");
		for (Integer numColumn : mapMergeRow.keySet())
			super.mergeRow(workbook, sheet, indexRow, mapMergeRow, numColumn, formulaEvaluator);

		if (sheetMappingRow != null)
			sheetMappingRow.setLastRow(indexRow);

		if (sheetData.getRowClass().isAnnotationPresent(ExcelSubtotals.class)) {
			if (enableSumForGroup) {
				for (String fieldName : sumForGroups) {
					Integer firstRow = mapSubTotals.get(fieldName);
					Integer lastRow = indexRow;
					indexRow = this.excelSubtotalWriter.mapRowSubTotals(indexRow, lastRowSheet, emptyRows, fieldName, firstRow, lastRow, mapSubTotals);
				}

			}
			emptyRows.add(new SubtotalRow(indexRow++, excelSubtotals.labelTotalGroup()));
			List<Integer> indexEmptyRow = new ArrayList<>();
			for (SubtotalRow emptyRow : emptyRows) {
				indexEmptyRow.add(emptyRow.getEmptyRow());
				row = sheet.createRow(emptyRow.getEmptyRow());
				CellStyle cellStyle = null;
				row.setHeight(heightRow);
				for (int numColumn = excelSheetLayout.startColumn(); numColumn < maxColumn; numColumn++) {
					int indexHeader = numColumn - excelSheetLayout.startColumn();
					Cell cell = row.createCell(numColumn);
					SheetHeader sheetHeader = (SheetHeader) listSheetHeader.get(indexHeader).clone();
					String nameField = getFieldName(sheetHeader);
					ExcelCellLayout excelCellLayout = null;
					Integer idEmptyRow = indexRow;
					if (indexHeader == 0) {

						if (emptyRows.size() - 1 > emptyRows.indexOf(emptyRow))
							sheetHeader.setValue(excelSubtotals.startLabel().trim() + " " + emptyRow.getLabel().trim() + " " + excelSubtotals.endLabel().trim());
						else
							sheetHeader.setValue(emptyRow.getLabel().trim());
						excelCellLayout = excelSubtotals.excelCellLayout();

						cellStyle = this.excelSubtotalWriter.getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), emptyRow, sheetHeader, excelCellLayout, this.mapCellStyle);
						sheetHeader.setExcelCellLayout(excelSubtotals.excelCellLayout());

					} else if (sheetHeader.getExcelSubtotal() != null && sheetHeader.getExcelSubtotal().enable()) {
						sheetHeader.setValue(null);
						excelCellLayout = sheetHeader.getExcelSubtotal().excelCellLayout();
						cellStyle = this.excelSubtotalWriter.getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), emptyRow, sheetHeader, excelCellLayout, this.mapCellStyle);
						String function = "subtotal(" + sheetHeader.getExcelSubtotal().dataConsolidateFunction().getValue() + "," + RowStartEndType.ROW_START.getParameter(nameField) + ":" + RowStartEndType.ROW_END.getParameter(nameField) + ")";
						Integer firstRowSubtotal = emptyRow.getFirstRow();
						Integer lastRowSubtotal = emptyRow.getLastRow();
						if (indexEmptyRow.contains(firstRowSubtotal))
							firstRowSubtotal++;
						if (emptyRows.size() - 1 == emptyRows.indexOf(emptyRow)) {
							firstRowSubtotal = startRowSheet;
							lastRowSubtotal = emptyRow.getEmptyRow() - 1;
						}

						function = ExcelBuildFunctionUtility.buildFunction(sheet, firstRowSubtotal, function, RowStartEndType.ROW_START, mapFieldColumn, mapSheet);
						function = ExcelBuildFunctionUtility.buildFunction(sheet, lastRowSubtotal, function, RowStartEndType.ROW_END, mapFieldColumn, mapSheet);
						ExcelFunctionImpl excelFuctionImpl = null;
						excelFuctionImpl = new ExcelFunctionImpl(function, nameField + "Function", false);
						sheetHeader.setExcelFunction(excelFuctionImpl.getAnnotation());
					} else if (sheetHeader.getExcelFunction() != null && sheetHeader.getExcelFunction().onSubTotalRow().value()) {
						idEmptyRow = emptyRow.getEmptyRow();
						excelCellLayout = sheetHeader.getExcelFunction().onSubTotalRow().excelCellLayout();
						cellStyle = this.excelSubtotalWriter.getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), emptyRow, sheetHeader, excelCellLayout, this.mapCellStyle);

					} else {
						sheetHeader.setValue(null);
						sheetHeader.setExcelFunction(null);

					}
					super.setCellValueExcel(workbook, sheet, cell, cellStyle, sheetHeader, idEmptyRow, formulaEvaluator);

				}
				;

			}
		}

		if (sheetData.getRowClass().isAnnotationPresent(ExcelConditionCellLayouts.class))
			this.conditionalCellLayout.createConditionalCellLayout(sheetData.getRowClass().getAnnotation(ExcelConditionCellLayouts.class), sheet, mapFieldColumn, mapSheet, indexRow - 1);

		if (excelSheetLayout.groupRow())
			sheet.groupRow(startRowSheet, indexRow - 1);

		if (excelSheetLayout.groupColumn())
			sheet.groupColumn(excelSheetLayout.startColumn(), maxColumn - 1);

		String rangeAddress = null;
		boolean enableAutoFilter = !isMergeSheet && excelSheetLayout.notMerge() && excelSheetLayout.sortAndFilter() && excelSheetLayout.showHeader();
		if (enableAutoFilter) {
			rangeAddress = ExcelUtils.coordinateCalculation(startRowSheet, excelSheetLayout.startColumn(), false, false) + ":" + ExcelUtils.coordinateCalculation(indexRow, listSheetHeader.size() + excelSheetLayout.startColumn() - 1, false, false);
			logger.info(rangeAddress);
			sheet.setAutoFilter(new CellRangeAddress(startRowSheet - 1, indexRow - 1, excelSheetLayout.startColumn(), listSheetHeader.size() + excelSheetLayout.startColumn() - 1));
		}

		if (sheetData instanceof FunctionsTotal) {
			FunctionsTotal<SheetFunctionTotal<? extends RowSheet>> functionsTotal = (FunctionsTotal<SheetFunctionTotal<? extends RowSheet>>) sheetData;
			if (functionsTotal.getSheetFunctionsTotal() != null) {
				SheetFunctionTotal<? extends RowSheet> functionSheetData = functionsTotal.getSheetFunctionsTotal();
				// functionSheetData.setMapFieldColumn(this.mapFieldColumn);
				indexRow += numberEmptyRows;
				indexRow = this.generateSheetData(workbook, sheet, functionSheetData, indexRow, isMergeSheet, formulaEvaluator);
			}
		}
		if (!isMergeSheet && sheet instanceof XSSFSheet
				&& (sheetData.getClass().isAnnotationPresent(ExcelCharts.class) || (sheetData instanceof DynamicChart && CollectionUtils.isNotEmpty(((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart())))) {
			List<ExcelChart> listExcelChart = this.excelChartBuilder.getExcelChart(sheetData);
			Set<String> ids = new HashSet<>();
			for (ExcelChart excelChart : listExcelChart) {
				String xAxis = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_EMPTY, true, true, mapFieldColumn, mapSheet);
				indexRow += 2;
				if (excelChart.group()) {
					if (!ids.contains(excelChart.id())) {
						ids.add(excelChart.id());
						boolean isVertical = xAxis.contains(RowStartEndType.ROW_START.getValue()) || xAxis.replace(" ", "").contains(ExcelChartBuilder.START) || xAxis.contains(RowStartEndType.ROW_END.getValue()) || xAxis.contains(ExcelChartBuilder.END);
						xAxis = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER, true, true, mapFieldColumn, mapSheet);
						xAxis = this.excelChartBuilder.setInfoColumnByMapCharts(xAxis, sheet, null, this.mapFieldColumn, this.mapSheet);
						indexRow = this.excelChartBuilder.generateChart((XSSFWorkbook) workbook, (XSSFSheet) sheet, excelChart, indexRow, xAxis, mapChart, isVertical && !excelSheetLayout.notMerge(), sheetData, this.mapFieldColumn, this.mapSheet);
					}
				} else if (MapUtils.isNotEmpty(mapChart)) {

					Set<String> keyCharts = mapChart.get(excelChart.id()).keySet();
					for (String keyChart : keyCharts) {
						InfoChart infoChart = mapChart.get(excelChart.id()).get(keyChart);
						if (infoChart != null) {
							String seriesChart = "";
							if (infoChart.getFunction().contains(RowStartEndType.ROW_START.getValue()) || infoChart.getFunction().replace(" ", "").contains(ExcelChartBuilder.START)) {
								seriesChart = this.excelChartBuilder.setInfoColumnByMapCharts(infoChart.getFunction(), sheet, infoChart, this.mapFieldColumn, this.mapSheet);
								xAxis = this.excelChartBuilder.setInfoColumnByMapCharts(excelChart.xAxis(), sheet, infoChart, this.mapFieldColumn, this.mapSheet);

							} else {
								xAxis = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER, true, true, mapFieldColumn, mapSheet);
								seriesChart = infoChart.getFunction();
							}

							indexRow = this.excelChartBuilder.generateChart((XSSFWorkbook) workbook, (XSSFSheet) sheet, infoChart.getTitle(), excelChart, indexRow, xAxis, seriesChart, sheetData);

						}
					}

				}
			}

		}

		if (sheet instanceof XSSFSheet && sheetData.getClass().isAnnotationPresent(ExcelPivot.class))
			indexRow = this.excelPivotBuilder.createPivot((XSSFSheet) sheet, sheetData, startRowSheet, excelSheetLayout.startColumn(), indexRow, listSheetHeader.size() + excelSheetLayout.startColumn() - 1, indexRow, this.mapFieldColumn);

		ExcelAreaBorderUtility.applyAreaBorders(workbook, sheet, sheetData, excelSheetLayout, mapFieldColumn, mapSheet);

		this.setAutoSizeColumn(listSheetHeader, sheet);

		if (sheetData.getClass().isAnnotationPresent(ExcelClearRows.class))
			sheetData.clear();

		return indexRow;

	}

	/**
	 * Gets the field name.
	 *
	 * @param sheetHeader the sheet header
	 * @return the field name
	 */
	private String getFieldName(SheetHeader sheetHeader) {
		String fieldName = null;
		if (sheetHeader.getField() != null)
			fieldName = sheetHeader.getField().getName();
		else if (sheetHeader.getKeyMap() != null)
			fieldName = sheetHeader.getKeyMap();
		else if (sheetHeader.getExcelFunction() != null)
			fieldName = sheetHeader.getExcelFunction().nameFunction();
		return fieldName;
	}

	/**
	 * Sets the auto size column.
	 *
	 * @param listSheetHeader the list sheet header
	 * @param sheet           the sheet
	 */
	private void setAutoSizeColumn(List<SheetHeader> listSheetHeader, Sheet sheet) {
		for (SheetHeader sheetHeader : listSheetHeader)
			if (sheetHeader.getExcelCellLayout().autoSizeColumn())
				sheet.autoSizeColumn(sheetHeader.getNumColumn());
	}

	/**
	 * Check merge column.
	 *
	 * @param sheetHeader     the sheet header
	 * @param rowSheet        the row sheet
	 * @param lastRowSheet    the last row sheet
	 * @param valueBefore     the value before
	 * @param listSheetHeader the list sheet header
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	private boolean checkMergeColumn(SheetHeader sheetHeader, RowSheet rowSheet, RowSheet lastRowSheet, Object valueBefore, List<SheetHeader> listSheetHeader) throws Exception {
		BeanWrapperImpl beanWrapper = new BeanWrapperImpl(rowSheet);
		BeanWrapperImpl lastBeanWrapper = new BeanWrapperImpl(lastRowSheet);
		for (String referenceField : sheetHeader.getExcelMergeRow().referenceField()) {
			if (StringUtils.isBlank(referenceField))
				throw new ExcelGeneratorException("@ExcelMergeRow referenceField contains a blank value - use @ExcelMergeRow without parameters for value-based merging");
			SheetHeader refHeader = listSheetHeader.stream()
				.filter(h -> (h.getField() != null && referenceField.equals(h.getField().getName())) || referenceField.equals(h.getKeyMap()))
				.findFirst()
				.orElseThrow(() -> new ExcelGeneratorException("@ExcelMergeRow referenceField \"" + referenceField + "\" does not match any field or function name in the sheet"));
			Object valueRefColumn = refHeader.getField() != null
				? beanWrapper.getPropertyValue(referenceField)
				: ((DynamicRowSheet) rowSheet).getMapValue().get(referenceField);
			Object valueRefColumnBefore = refHeader.getField() != null
				? lastBeanWrapper.getPropertyValue(referenceField)
				: ((DynamicRowSheet) lastRowSheet).getMapValue().get(referenceField);
			if ((valueRefColumn != null && valueRefColumnBefore != null && !valueRefColumn.equals(valueRefColumnBefore)) || !(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
				return true;
		}
		return false;
	}


	/**
	 * Write label.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param baseSheet        the base sheet
	 * @param indexRow         the index row
	 * @param formulaEvaluator the formula evaluator
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer writeLabel(Workbook workbook, Sheet sheet, BaseSheet baseSheet, Integer indexRow, FormulaEvaluator formulaEvaluator) throws Exception {
		Class<? extends BaseSheet> classSheet = baseSheet.getClass();
		Set<Field> listField = SpreadsheetUtils.getListField(classSheet);
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelLabel.class)) {
				Row row = sheet.createRow(indexRow);
				ExcelLabel excelLabel = field.getAnnotation(ExcelLabel.class);
				Object value = new BeanWrapperImpl(baseSheet).getPropertyValue(field.getName());
				if (value != null) {
					if (!(value instanceof String))
						throw new ExcelGeneratorException(field.getName() + " field type is not supported: required String");
					if (StringUtils.isNotBlank(value.toString())) {
						CellStyle cellStype = this.excelLayoutUtility.createCellStyle(workbook, excelLabel.labelLayout(), 0);
						SheetHeader sheetHeader = new SheetHeader();
						sheetHeader.setValue(value);
						sheetHeader.setExcelCellLayout(excelLabel.labelLayout());
						Cell cellStart = row.createCell(0);
						MergeCell mergeColumn = new MergeCell();
						mergeColumn.setCellFrom(cellStart);
						mergeColumn.setCellStyleFrom(cellStype);
						mergeColumn.setSheetHeader(sheetHeader);
						mergeColumn.setRowStart(indexRow);
						mergeColumn.setRowEnd(indexRow);
						mergeColumn.setColumnFrom(0);
						mergeColumn.setColumnTo(excelLabel.columnMerge() - 1);
						for (int index = 1; index < excelLabel.columnMerge(); index++) {
							Cell cell = row.createCell(index);
							cell.setCellStyle(cellStype);
						}
						runMergeCell(workbook, sheet, mergeColumn, formulaEvaluator);
						indexRow += 2;
					}
				}
				break;
			}
		}

		return indexRow;
	}

	@Override
	public void createFileXls(ReportExcel report, OutputStream outputStream) throws Exception {
		this.mergeCalcoloCells = null;
		HSSFWorkbook workbook = null;
		try {
			boolean isCover = true;
			if (!report.isIgnoreCover() && this.cover != null)
				workbook = new HSSFWorkbook(this.cover.getInputStream());
			else {
				workbook = new HSSFWorkbook();
				isCover = false;
			}
			this.setCoverParameters(report, outputStream, workbook, isCover);
		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
		}
	}

	@Override
	public void createFileXlsx(ReportExcel report, OutputStream outputStream) throws Exception {
		this.mergeCalcoloCells = null;
		XSSFWorkbook workbook = null;
		try {
			boolean isCover = true;
			if (!report.isIgnoreCover() && this.cover != null)
				workbook = new XSSFWorkbook(this.cover.getInputStream());
			else {
				workbook = new XSSFWorkbook();
				isCover = false;
			}

			this.setCoverParameters(report, outputStream, workbook, isCover);

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
		}
	}

	@Override
	public void createBigDataFileXlsx(ReportExcel report, OutputStream outputStream) throws Exception {
		this.mergeCalcoloCells = null;
		SXSSFWorkbook workbook = null;
		XSSFWorkbook coverWorkbook = null;
		try {
			// boolean isCover = true;
			if (!report.isIgnoreCover() && this.cover != null) {
				coverWorkbook = new XSSFWorkbook(this.cover.getInputStream());
				updateCover(report, coverWorkbook);
				workbook = new SXSSFWorkbook(coverWorkbook);
			} else {
				workbook = new SXSSFWorkbook();
			}
			this.setCoverParameters(report, outputStream, workbook, false);

		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
		}

	}

}
