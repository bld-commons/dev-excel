/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ScopeGenerateExcelImpl.java
*/
package com.bld.generator.report.excel.impl;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.BorderStyle;
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
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFBar3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFLine3DChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFLineChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTArea3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTAreaSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBar3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLine3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPie3DChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPieSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTRadarSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterChart;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTScatterSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.DynamicChart;
import com.bld.generator.report.excel.DynamicRowSheet;
import com.bld.generator.report.excel.FunctionsTotal;
import com.bld.generator.report.excel.MergeSheet;
import com.bld.generator.report.excel.QuerySheetData;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.ScopeGenerateExcel;
import com.bld.generator.report.excel.SheetComponent;
import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.SheetFunctionTotal;
import com.bld.generator.report.excel.SheetSummary;
import com.bld.generator.report.excel.annotation.ExcelAreaBorder;
import com.bld.generator.report.excel.annotation.ExcelBarChartData;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelChart;
import com.bld.generator.report.excel.annotation.ExcelChartCategory;
import com.bld.generator.report.excel.annotation.ExcelChartDataLabel;
import com.bld.generator.report.excel.annotation.ExcelCharts;
import com.bld.generator.report.excel.annotation.ExcelConditionCellLayouts;
import com.bld.generator.report.excel.annotation.ExcelFreezePane;
import com.bld.generator.report.excel.annotation.ExcelLabel;
import com.bld.generator.report.excel.annotation.ExcelPivot;
import com.bld.generator.report.excel.annotation.ExcelRowHeight;
import com.bld.generator.report.excel.annotation.ExcelSelectCell;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;
import com.bld.generator.report.excel.annotation.ExcelSubtotals;
import com.bld.generator.report.excel.annotation.ExcelSummary;
import com.bld.generator.report.excel.annotation.ExcelSuperHeaders;
import com.bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import com.bld.generator.report.excel.constant.BorderType;
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
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;
import com.bld.generator.report.excel.utility.ExcelLayoutUtility;

/**
 * The Class ScopeGenerateExcelImpl.<br>
 * ScopeGenerateExcelImpl is the heart of the generation of the xls or xlsx
 * files.
 */
@Component
@SuppressWarnings("unchecked")
@Scope("prototype")
public class ScopeGenerateExcelImpl extends SuperGenerateExcelImpl implements ScopeGenerateExcel {

	/** The Constant END. */
	private static final String END = "[end]";

	/** The Constant START. */
	private static final String START = "[start]";

	/** The number empty rows. */
	@Value("${com.bld.commons.number.empty.rows:2}")
	private int numberEmptyRows;

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ScopeGenerateExcelImpl.class);

	@Value("${com.bld.commons.resource.cover.path:}")
	private Resource cover;
	
	/** The Constant LIST_CHART_TYPES. */
	private final static List<ChartTypes> LIST_CHART_TYPES = listChartTypes();

	/** The excel query component. */
	@Autowired(required = false)
	private ExcelQueryComponent excelQueryComponent;

	private Map<String, Integer> mapSubTotals = new HashMap<>();
	
	@Autowired
	private ConditionalCellLayout conditionalCellLayout;

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
		ByteArrayOutputStream byteArrayOutputStream =null;
		byte[] byteExcel = null;
		HSSFWorkbook workbook = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			boolean isCover = true;
			if(!report.isIgnoreCover() && this.cover!=null) 
				workbook=new HSSFWorkbook(this.cover.getInputStream());			
			else {
				workbook = new HSSFWorkbook();
				isCover = false;
			}
			this.setCoverParameters(report, byteArrayOutputStream, workbook, isCover);
			byteExcel = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
			if(byteArrayOutputStream!=null)
				byteArrayOutputStream.close();
		}

		return byteExcel;
	}

	/**
	 * List chart types.
	 *
	 * @return the list
	 */
	private static List<ChartTypes> listChartTypes() {
		List<ChartTypes> list = new ArrayList<>();
		list.add(ChartTypes.PIE);
		list.add(ChartTypes.PIE3D);
		list.add(ChartTypes.DOUGHNUT);
		return Collections.unmodifiableList(list);
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
		ByteArrayOutputStream byteArrayOutputStream =null;
		byte[] byteExcel = null;
		XSSFWorkbook workbook = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			boolean isCover = true;
			if(!report.isIgnoreCover() && this.cover!=null) 
				workbook=new XSSFWorkbook(this.cover.getInputStream());			
			 else {
				workbook = new XSSFWorkbook();
				isCover = false;
			}

			this.setCoverParameters(report, byteArrayOutputStream, workbook, isCover);
			byteExcel = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
			if(byteArrayOutputStream!=null)
				byteArrayOutputStream.close();
		}
		return byteExcel;
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
		byte[] byteExcel = null;
		ByteArrayOutputStream byteArrayOutputStream =null;
		SXSSFWorkbook workbook = null;
		XSSFWorkbook coverWorkbook = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			// boolean isCover = true;
			if(!report.isIgnoreCover() && this.cover!=null) {
				coverWorkbook=new XSSFWorkbook(this.cover.getInputStream());
				updateCover(report, coverWorkbook);
				workbook = new SXSSFWorkbook(coverWorkbook);
			} else {
				workbook = new SXSSFWorkbook();
			}

			this.setCoverParameters(report, byteArrayOutputStream, workbook, false);
			byteExcel = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
			if(byteArrayOutputStream!=null)
				byteArrayOutputStream.close();
		}
		return byteExcel;
	}

	/**
	 * Sets the cover parameters.
	 *
	 * @param report                the report
	 * @param byteArrayOutputStream the byte array output stream
	 * @param workbook              the workbook
	 * @param isCopertina           the is copertina
	 * @throws Exception the exception
	 */
	private void setCoverParameters(ReportExcel report, ByteArrayOutputStream byteArrayOutputStream, Workbook workbook, boolean isCopertina) throws Exception {
		if (isCopertina) {
			updateCover(report, workbook);

		}

		workbook = createSheets(report, workbook);

		workbook.write(byteArrayOutputStream);

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
				Object value = PropertyUtils.getProperty(report, field.getName());
				ExcelDate excelDate = null;
				if (Date.class.isAssignableFrom(field.getType()) || Calendar.class.isAssignableFrom(field.getType()) || Timestamp.class.isAssignableFrom(field.getType())) {
					excelDate = SpreadsheetUtils.getAnnotation(field, ExcelDate.class);
					cellStyle = ExcelLayoutUtility.dateCellStyle(workbook, cellStyle, excelDate.value().getValue(),this.valueProps);
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
	 * {@link com.bld.generator.report.excel.BaseSheet} is equivalent to one sheet.<br>
	 *
	 * @param report   the report
	 * @param workbook the workbook
	 * @return the workbook
	 * @throws Exception the exception
	 */
	private Workbook createSheets(ReportExcel report, Workbook workbook) throws Exception {
		if (report.isEnableSheetMapping()) {
			this.sheetMapping = new SheetMappingSheet();
			report.addBaseSheet(this.sheetMapping);
		}

		List<BaseSheet> listSheet = report.getListBaseSheet();
		int indexSheetName = 0;

		this.mapCellStyle = new HashMap<>();
		this.mapCellHeaderStyle = new HashMap<>();
		this.mapFieldColumn = new HashMap<>();
		this.listFunctionCell = new ArrayList<>();
		this.listDropDown = new ArrayList<>();
		this.mapSheet = new HashMap<>();
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
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
			sheet.setForceFormulaRecalculation(true);
			if (baseSheet instanceof MergeSheet) {
				this.generateMergeSheet(workbook, sheet, (MergeSheet) baseSheet, formulaEvaluator);
			} else if (baseSheet instanceof SheetSummary) {
				this.generateSheetSummary(workbook, sheet, (SheetSummary) baseSheet, 0, formulaEvaluator);
			} else if (baseSheet instanceof SheetData) {
				this.generateSheetData(workbook, sheet, (SheetData<? extends RowSheet>) baseSheet, 0, false, formulaEvaluator);
			}

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

		for (int i = 0; i < workbook.getNumberOfSheets(); i++)
			workbook.setSheetName(i, workbook.getSheetName(i).replace(BaseSheet.APOS, "'"));

		for (DropDownCell dropDownCell : this.listDropDown)
			super.addDropDown(dropDownCell);

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
		for (SheetComponent sheetComponent : mergeSheet.getListSheet()) {
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
			CellStyle cellStyleHeader = ExcelLayoutUtility.getCellStyleHeader(workbook, sheet, sheetSummary, rowHeader,this.mapCellHeaderStyle);
			Cell cellHeader = rowHeader.createCell(excelSheetLayout.startColumn());
			cellHeader.setCellStyle(cellStyleHeader);
			String title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelSummary.title(), RowStartEndType.ROW_START,mapFieldColumn,mapSheet);
			title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, title, RowStartEndType.ROW_END,mapFieldColumn,mapSheet);
			title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, title, RowStartEndType.ROW_EMPTY,mapFieldColumn,mapSheet);
			title = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, title, RowStartEndType.ROW_HEADER,mapFieldColumn,mapSheet);
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
		if (this.excelQueryComponent != null && sheetData instanceof QuerySheetData)
			this.excelQueryComponent.executeQuery((QuerySheetData<? extends RowSheet>) sheetData);
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

//		String startKey = null;
//		String endKey = null;

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
			sheetMappingRow.setRowsNumber(sheetData.getListRowSheet().size());
			this.sheetMapping.addRowSheets(sheetMappingRow);
		}
		List<SubtotalRow> emptyRows = new ArrayList<>();
		ExcelSubtotals excelSubtotals = sheetData.getRowClass().getAnnotation(ExcelSubtotals.class);
		final boolean enableSumForGroup = excelSubtotals!=null && ArrayUtils.isNotEmpty(excelSubtotals.sumForGroup());
		List<String> sumForGroups = new ArrayList<>();
		if (enableSumForGroup) {
			List<String> sfg = Arrays.asList(excelSubtotals.sumForGroup());
				for (int i = listSheetHeader.size() - 1; i >= 0; i--) {
					SheetHeader sheetHeader = listSheetHeader.get(i);
					String fieldName = getFieldName(sheetHeader);
					if (sfg.contains(fieldName))
						sumForGroups.add(fieldName);
				}
		}
		

		for (RowSheet rowSheet : sheetData.getListRowSheet()) {
			int splitRow = 0;
			if (rowSheet.getClass().isAnnotationPresent(ExcelSubtotals.class)) {
				if (enableSumForGroup && lastRowSheet != null) {
					for (String fieldName : sumForGroups)
						if (!PropertyUtils.getProperty(lastRowSheet, fieldName).equals(PropertyUtils.getProperty(rowSheet, fieldName)))
							splitRow = sumForGroups.indexOf(fieldName) + 1;
					for (int i = 0; i < splitRow; i++) {
						String fieldName = sumForGroups.get(i);
						Integer firstRow = startRowSheet;
						Integer lastRow = indexRow.intValue();
						if (!mapSubTotals.containsKey(fieldName))
							indexRow = mapRowSubTotals(indexRow, lastRowSheet, emptyRows, fieldName, firstRow, lastRow);
						else {
							firstRow = mapSubTotals.get(fieldName);
							indexRow = mapRowSubTotals(indexRow, lastRowSheet, emptyRows, fieldName, firstRow, lastRow);
						}

					}
				}
			}
			row = sheet.createRow(indexRow);
			Map<String, Object> mapValue = new HashMap<>();
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
					value = PropertyUtils.getProperty(rowSheet, field.getName());
					mapValue.put(field.getName(), value);
				} else if (StringUtils.isNotBlank(sheetHeader.getKeyMap())) {
					DynamicRowSheet dynamicRowSheet = (DynamicRowSheet) rowSheet;
					value = dynamicRowSheet.getMapValue().get(sheetHeader.getKeyMap());
					mapValue.put(sheetHeader.getKeyMap(), value);
				}
				value = manageExcelImage(sheetHeader, value);

				sheetHeader.setValue(value);
				if (start) {
					ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
					LayoutCell layoutCell = sheetHeader.getLayoutCell(indexRow);
					int colorSize = excelCellLayout.rgbFont().length > excelCellLayout.rgbForeground().length ? excelCellLayout.rgbFont().length : excelCellLayout.rgbForeground().length;
					for (int colorModul = 0; colorModul < colorSize; colorModul++) {
						LayoutCell layoutCellTemp = sheetHeader.getLayoutCell(colorModul);
						if (!this.mapCellStyle.containsKey(layoutCellTemp))
							this.mapCellStyle.put(layoutCellTemp, ExcelLayoutUtility.createCellStyle(workbook, excelCellLayout, sheetHeader, colorModul,this.valueProps));
					}
					cellStyle = this.mapCellStyle.get(layoutCell);
					infoColumn.setFirstRow(indexRow);
					infoColumn.setLastRow(indexRow + sheetData.getListRowSheet().size() - 1);
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
							valueBefore = PropertyUtils.getProperty(lastRowSheet, field.getName());
						if (ArrayUtils.isEmpty(sheetHeader.getExcelMergeRow().referenceField())) {
							if (!(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
								super.mergeRowAndRemoveMap(workbook, sheet, workRow, mapMergeRow, numColumn, formulaEvaluator);
							else
								repeat = super.setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader, workRow, sheet);

						} else if (ArrayUtils.isNotEmpty(sheetHeader.getExcelMergeRow().referenceField())) {
							if (checkMergeColumn(sheetHeader, rowSheet, lastRowSheet, valueBefore))
								super.mergeRowAndRemoveMap(workbook, sheet, workRow, mapMergeRow, numColumn, formulaEvaluator);
							else
								repeat = super.setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader, workRow, sheet);
						}

					}

				} while (repeat);

			}
			lastRowSheet = rowSheet;
			

			
			if (sheetData.getClass().isAnnotationPresent(ExcelCharts.class) || (sheetData instanceof DynamicChart && CollectionUtils.isNotEmpty(((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart()))) {
				List<ExcelChart> listExcelChart = getExcelChart(sheetData);
				for (ExcelChart excelChart : listExcelChart) {
					for (ExcelChartCategory excelChartCategory : excelChart.excelChartCategories()) {
						String functionChart = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelChartCategory.function(), RowStartEndType.ROW_EMPTY, true, true,mapFieldColumn,mapSheet);
						String title = "";
						if (mapValue.containsKey(excelChartCategory.fieldName().replace("${", "").replace("}", ""))) {
							title = mapValue.get(excelChartCategory.fieldName().replace("${", "").replace("}", "")).toString();
							configMapChart(mapChart, excelChart, excelChartCategory, functionChart, title, indexRow.intValue(), indexRow);

						} else {
							Integer rowRegexIndex = null;
							if (StringUtils.isNotEmpty(excelChartCategory.rowRegex())) {
								int lastRow = sheet.getLastRowNum() + 1;
								sheet.createRow(lastRow).createCell(0, CellType.FORMULA);
								Cell lastCell = sheet.getRow(lastRow).getCell(0);
								String rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelChartCategory.rowRegex(), RowStartEndType.ROW_EMPTY, true, true,mapFieldColumn,mapSheet);
								rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, rowRegex, RowStartEndType.ROW_HEADER, true, true,mapFieldColumn,mapSheet);
								rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, null, rowRegex, RowStartEndType.ROW_START, true, true,mapFieldColumn,mapSheet);
								rowRegex = ExcelBuildFunctionUtility.buildFunction(sheet, null, rowRegex, RowStartEndType.ROW_END, true, true,mapFieldColumn,mapSheet);
								lastCell.setCellFormula(rowRegex);
								CellType cellType = formulaEvaluator.evaluateFormulaCell(lastCell);
								if (!CellType.NUMERIC.equals(cellType))
									throw new ExcelGeneratorException("Row Regex: \"" + rowRegex + "\" need to return a numeric value");
								rowRegexIndex = (int) lastCell.getNumericCellValue();
								sheet.removeRow(sheet.getRow(lastRow));
							}
							String areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, excelChartCategory.fieldName(), RowStartEndType.ROW_EMPTY, true, true,mapFieldColumn,mapSheet);
							areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, indexRow, areaFieldName, RowStartEndType.ROW_HEADER, true, true,mapFieldColumn,mapSheet);
							areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaFieldName, RowStartEndType.ROW_START, true, true,mapFieldColumn,mapSheet);
							areaFieldName = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaFieldName, RowStartEndType.ROW_END, true, true,mapFieldColumn,mapSheet);
							AreaReference areaReference = new AreaReference(areaFieldName, excelChart.spreadsheetVersion());
							for (CellReference cr : areaReference.getAllReferencedCells()) {
								Sheet appSheet = StringUtils.isNotEmpty(cr.getSheetName()) ? workbook.getSheet(cr.getSheetName()) : sheet;
								title = appSheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
								Integer firstRow = indexRow;
								if (!sheet.getSheetName().equals(appSheet.getSheetName())) {
									firstRow = cr.getRow();
									functionChart = ExcelBuildFunctionUtility.buildFunction(sheet, cr.getRow(), excelChartCategory.function(), RowStartEndType.ROW_EMPTY, true, true,mapFieldColumn,mapSheet);
								}

								if (rowRegexIndex == null)
									configMapChart(mapChart, excelChart, excelChartCategory, functionChart, title, indexRow, indexRow);
								else if (rowRegexIndex.intValue() == firstRow.intValue())
									configMapChart(mapChart, excelChart, excelChartCategory, functionChart, title, indexRow, indexRow);
							}

						}

					}

				}

			}

			start = false;
			indexRow++;
			// this.evaluateAllFormulaCells(workbook, sheet);
		}

		for (Integer numColumn : mapMergeRow.keySet())
			super.mergeRow(workbook, sheet, indexRow, mapMergeRow, numColumn, formulaEvaluator);

		if (sheetMappingRow != null)
			sheetMappingRow.setLastRow(indexRow);

		if (sheetData.getRowClass().isAnnotationPresent(ExcelSubtotals.class)) {
			if (enableSumForGroup) {
				for (String fieldName : sumForGroups) {
					Integer firstRow = mapSubTotals.get(fieldName);
					Integer lastRow = indexRow;
					indexRow = mapRowSubTotals(indexRow, lastRowSheet, emptyRows, fieldName, firstRow, lastRow);
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

						cellStyle = getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), emptyRow, sheetHeader, excelCellLayout);
						sheetHeader.setExcelCellLayout(excelSubtotals.excelCellLayout());

					} else if (sheetHeader.getExcelSubtotal() != null && sheetHeader.getExcelSubtotal().enable()) {
						sheetHeader.setValue(null);
						excelCellLayout = sheetHeader.getExcelSubtotal().excelCellLayout();
						cellStyle = getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), emptyRow, sheetHeader, excelCellLayout);
						String function = "subtotal(" + sheetHeader.getExcelSubtotal().dataConsolidateFunction().getValue() + "," + RowStartEndType.ROW_START.getParameter(nameField) + ":" + RowStartEndType.ROW_END.getParameter(nameField) + ")";
						Integer firstRowSubtotal = emptyRow.getFirstRow();
						Integer lastRowSubtotal = emptyRow.getLastRow();
						if (indexEmptyRow.contains(firstRowSubtotal))
							firstRowSubtotal++;
						if (emptyRows.size() - 1 == emptyRows.indexOf(emptyRow)) {
							firstRowSubtotal = startRowSheet;
							lastRowSubtotal = emptyRow.getEmptyRow() - 1;
						}

						function = ExcelBuildFunctionUtility.buildFunction(sheet, firstRowSubtotal, function, RowStartEndType.ROW_START,mapFieldColumn,mapSheet);
						function = ExcelBuildFunctionUtility.buildFunction(sheet, lastRowSubtotal, function, RowStartEndType.ROW_END,mapFieldColumn,mapSheet);
						ExcelFunctionImpl excelFuctionImpl = null;
						excelFuctionImpl = new ExcelFunctionImpl(function, nameField + "Function", false);
						sheetHeader.setExcelFunction(excelFuctionImpl.getAnnotation());
					} else if (sheetHeader.getExcelFunction() != null && sheetHeader.getExcelFunction().onSubTotalRow().value()) {
						idEmptyRow = emptyRow.getEmptyRow();
						excelCellLayout = sheetHeader.getExcelFunction().onSubTotalRow().excelCellLayout();
						cellStyle = getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), emptyRow, sheetHeader, excelCellLayout);

						// super.setCellValueExcel(workbook, sheet, cell, cellStyle, sheetHeader,
						// emptyRow.getEmptyRow(), formulaEvaluator);
					} else {
						sheetHeader.setValue(null);
						sheetHeader.setExcelFunction(null);

					}
					super.setCellValueExcel(workbook, sheet, cell, cellStyle, sheetHeader, idEmptyRow, formulaEvaluator);

				}
				;

			}
		}

		if(sheetData.getRowClass().isAnnotationPresent(ExcelConditionCellLayouts.class)) 
			this.conditionalCellLayout.createConditionalCellLayout(sheetData.getRowClass().getAnnotation(ExcelConditionCellLayouts.class), sheet, mapFieldColumn,mapSheet,indexRow-1);
		
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
		if (excelSheetLayout.locked().locked() || excelSheetLayout.hidden()) {
			if (sheet instanceof XSSFSheet && enableAutoFilter)
				((XSSFSheet) sheet).lockAutoFilter(false);
			sheet.protectSheet(this.valueProps.valueProps(excelSheetLayout.locked().value()));
			workbook.setSheetHidden(workbook.getSheetIndex(sheet), excelSheetLayout.hidden());
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
			List<ExcelChart> listExcelChart = getExcelChart(sheetData);
			Set<String> ids = new HashSet<>();
			for (ExcelChart excelChart : listExcelChart) {
				String xAxis = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_EMPTY, true, true,mapFieldColumn,mapSheet);
				indexRow += 2;
				if (excelChart.group()) {
					if (!ids.contains(excelChart.id())) {
						ids.add(excelChart.id());
						boolean isVertical = xAxis.contains(RowStartEndType.ROW_START.getValue()) || xAxis.replace(" ", "").contains(START) || xAxis.contains(RowStartEndType.ROW_END.getValue()) || xAxis.contains(END);
						xAxis = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER, true, true,mapFieldColumn,mapSheet);
						xAxis = setInfoColumnByMapCharts(xAxis, sheet, null);
						indexRow = generateChart((XSSFWorkbook) workbook, (XSSFSheet) sheet, excelChart, indexRow, xAxis, mapChart, isVertical && !excelSheetLayout.notMerge(), sheetData);
					}
				} else if (MapUtils.isNotEmpty(mapChart)) {
					for (String keyChart : mapChart.get(excelChart.id()).keySet()) {
						for (ExcelChartCategory excelChartCategory : excelChart.excelChartCategories()) {
							if (keyChart.endsWith(excelChartCategory.function())) {
								InfoChart infoChart = mapChart.get(excelChart.id()).get(keyChart);
								String seriesChart = "";
								if (infoChart.getFunction().contains(RowStartEndType.ROW_START.getValue()) || infoChart.getFunction().replace(" ", "").contains(START)) {
									seriesChart = setInfoColumnByMapCharts(infoChart.getFunction(), sheet, infoChart);
									xAxis = setInfoColumnByMapCharts(excelChart.xAxis(), sheet, infoChart);

								} else {
									xAxis = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER, true, true,mapFieldColumn,mapSheet);
									seriesChart = infoChart.getFunction();
								}

//								seriesChart=buildFunction(sheet, null, seriesChart, RowStartEndType.ROW_START);
//								seriesChart=buildFunction(sheet, null, seriesChart, RowStartEndType.ROW_END);
								indexRow = generateChart((XSSFWorkbook) workbook, (XSSFSheet) sheet, infoChart.getTitle(), excelChart, indexRow, xAxis, seriesChart, sheetData);
							}

						}

					}

				}

			}

		}

		if (sheet instanceof XSSFSheet && sheetData.getClass().isAnnotationPresent(ExcelPivot.class))
			indexRow = this.createPivot((XSSFSheet) sheet, sheetData, startRowSheet, excelSheetLayout.startColumn(), indexRow, listSheetHeader.size() + excelSheetLayout.startColumn() - 1, indexRow);

		for (ExcelAreaBorder areaBorder : excelSheetLayout.areaBorder()) {
			String areaRange = areaBorder.areaRange();
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_EMPTY,mapFieldColumn,mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_END,mapFieldColumn,mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_HEADER,mapFieldColumn,mapSheet);
			areaRange = ExcelBuildFunctionUtility.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_START,mapFieldColumn,mapSheet);
			CellRangeAddress region = CellRangeAddress.valueOf(areaRange);
			int firstRow = region.getFirstRow();
			int firstColumn = region.getFirstColumn();
			int lastRow = region.getLastRow();
			int lastColumn = region.getLastColumn();

			int rowSuperHeader = 0;
			if (areaBorder.includeSuperHeader() && sheetData.getClass().isAnnotationPresent(ExcelSuperHeaders.class)) {
				ExcelSuperHeaders excelSuperHeaders = sheetData.getClass().getAnnotation(ExcelSuperHeaders.class);
				rowSuperHeader = excelSuperHeaders.superHeaders().length;
			}
			if (sheet.getRow(firstRow - rowSuperHeader).getCell(firstColumn) != null && sheet.getRow(firstRow - rowSuperHeader).getCell(lastColumn) != null)
				firstRow = firstRow - rowSuperHeader;

			for (int count = firstRow; count <= lastRow; count++) {
				Cell cellLeft = sheet.getRow(count).getCell(firstColumn);
				Cell cellRight = sheet.getRow(count).getCell(lastColumn);
				setBorderArea(workbook, sheet, cellLeft, areaBorder.border().left(), BorderType.LEFT);
				setBorderArea(workbook, sheet, cellRight, areaBorder.border().right(), BorderType.RIGHT);
			}

			for (int count = firstColumn; count <= lastColumn; count++) {
				Cell cellTop = sheet.getRow(firstRow).getCell(count);
				Cell cellBottom = sheet.getRow(lastRow).getCell(count);
				setBorderArea(workbook, sheet, cellTop, areaBorder.border().top(), BorderType.TOP);
				setBorderArea(workbook, sheet, cellBottom, areaBorder.border().bottom(), BorderType.BOTTOM);
			}

		}

		this.setAutoSizeColumn(listSheetHeader, sheet);

		return indexRow;

	}

	private Integer mapRowSubTotals(Integer indexRow, RowSheet lastRowSheet, List<SubtotalRow> emptyRows, String fieldName, Integer firstRow, Integer lastRow) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		emptyRows.add(new SubtotalRow(indexRow++, BeanUtils.getProperty(lastRowSheet, fieldName), fieldName, firstRow, lastRow));
		firstRow = indexRow.intValue();
		mapSubTotals.put(fieldName, firstRow);
		return indexRow;
	}

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

	private void setAutoSizeColumn(List<SheetHeader> listSheetHeader, Sheet sheet) {
		for (SheetHeader sheetHeader : listSheetHeader)
			if (sheetHeader.getExcelCellLayout().autoSizeColumn())
				sheet.autoSizeColumn(sheetHeader.getNumColumn());
	}

	private boolean checkMergeColumn(SheetHeader sheetHeader, RowSheet rowSheet, RowSheet lastRowSheet, Object valueBefore) throws Exception {
		for (String referenceField : sheetHeader.getExcelMergeRow().referenceField()) {
			if (StringUtils.isNotBlank(referenceField)) {
				Object valueRefColumn = PropertyUtils.getProperty(rowSheet, referenceField);
				Object valueRefColumnBefore = PropertyUtils.getProperty(lastRowSheet, referenceField);
				if ((valueRefColumn != null && valueRefColumnBefore != null && !valueRefColumn.equals(valueRefColumnBefore)) || !(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
					return true;
			}
		}
		return false;

	}

	/**
	 * Config map chart.
	 *
	 * @param mapChart           the map chart
	 * @param excelChart         the excel chart
	 * @param excelChartCategory the excel chart category
	 * @param functionChart      the function chart
	 * @param title              the title
	 * @param firstRow           the first row
	 * @param lastRow            the last row
	 */
	private void configMapChart(Map<String, Map<String, InfoChart>> mapChart, ExcelChart excelChart, ExcelChartCategory excelChartCategory, String functionChart, String title, Integer firstRow, Integer lastRow) {
		String key = title + excelChartCategory.function();
		// String title = mapValue.get(excelChart.fieldName()).toString();
		if (!mapChart.containsKey(excelChart.id()))
			mapChart.put(excelChart.id(), new HashMap<>());
		if (!mapChart.get(excelChart.id()).containsKey(key)) {
			mapChart.get(excelChart.id()).put(key, new InfoChart(title, functionChart, firstRow.intValue()));
		} else {
			mapChart.get(excelChart.id()).get(key).setLastRow(lastRow.intValue());
		}
	}

	/**
	 * Sets the info column by map charts.
	 *
	 * @param function  the function
	 * @param sheet     the sheet
	 * @param infoChart the info chart
	 * @return the string
	 * @throws Exception the exception
	 */
	private String setInfoColumnByMapCharts(String function, Sheet sheet, InfoChart infoChart) throws Exception {
		Pattern p = Pattern.compile("\\$\\{.*?(RowStart|\\[start\\])}");
		Matcher m = p.matcher(function);
		if (m.find()) {
			String fieldName = ExcelUtils.getKeyColumn(sheet, m.group().replace(RowStartEndType.ROW_START.getValue(), "").replace("${", "").replace(START, "").replace("}", ""));
			if (infoChart != null) {
				mapFieldColumn.get(fieldName).setFirstRow(infoChart.getFirstRow());
				mapFieldColumn.get(fieldName).setLastRow(infoChart.getLastRow());
			}
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_START, true, true,mapFieldColumn,mapSheet);
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_END, true, true,mapFieldColumn,mapSheet);
		}
		logger.info("Function: " + function);
		return function;
	}

	/**
	 * Gets the cell style subtotal.
	 *
	 * @param workbook        the workbook
	 * @param indexRow        the index row
	 * @param emptyRow        the empty row
	 * @param sheetHeader     the sheet header
	 * @param excelCellLayout the excel cell layout
	 * @return the cell style subtotal
	 * @throws Exception the exception
	 */
	private CellStyle getCellStyleSubtotal(Workbook workbook, Integer indexRow, SubtotalRow emptyRow, SheetHeader sheetHeader, ExcelCellLayout excelCellLayout) throws Exception {
		CellStyle cellStyle = null;
		sheetHeader.setExcelCellLayout(excelCellLayout);
		LayoutCell layoutCell = SpreadsheetUtils.reflectionAnnotation(new LayoutCell(), sheetHeader.getExcelCellLayout());
		if (sheetHeader.getExcelNumberFormat() != null && StringUtils.isNotBlank(sheetHeader.getExcelNumberFormat().value()))
			layoutCell.setNumberFormat(sheetHeader.getExcelNumberFormat().value());
		layoutCell.setColor(indexRow);
		if (!this.mapCellStyle.containsKey(layoutCell)) {
			cellStyle = ExcelLayoutUtility.createCellStyle(workbook, sheetHeader.getExcelCellLayout(), null, emptyRow.getEmptyRow(),this.valueProps);
			if (sheetHeader.getExcelNumberFormat() != null && StringUtils.isNotBlank(sheetHeader.getExcelNumberFormat().value()))
				cellStyle = ExcelLayoutUtility.dateCellStyle(workbook, cellStyle, sheetHeader.getExcelNumberFormat().value(),this.valueProps);
			this.mapCellStyle.put(layoutCell, cellStyle);
		}
		
		cellStyle = this.mapCellStyle.get(layoutCell);
		return cellStyle;
	}

	/**
	 * Sets the border area.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param cell        the cell
	 * @param borderStyle the border style
	 * @param borderType  the border type
	 */
	private void setBorderArea(Workbook workbook, Sheet sheet, Cell cell, BorderStyle borderStyle, BorderType borderType) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.cloneStyleFrom(cell.getCellStyle());
		switch (borderType) {
		case BOTTOM:
			cellStyle.setBorderBottom(borderStyle);
			break;
		case LEFT:
			cellStyle.setBorderLeft(borderStyle);
			break;
		case RIGHT:
			cellStyle.setBorderRight(borderStyle);
			break;
		case TOP:
			cellStyle.setBorderTop(borderStyle);
			break;
		default:
			break;

		}
		cell.setCellStyle(cellStyle);

	}

	/**
	 * Gets the excel chart.
	 *
	 * @param sheetData the sheet data
	 * @return the excel chart
	 */
	private List<ExcelChart> getExcelChart(SheetData<? extends RowSheet> sheetData) {
		List<ExcelChart> listExcelChart = null;
		if (sheetData.getClass().isAnnotationPresent(ExcelCharts.class))
			listExcelChart = Arrays.asList(sheetData.getClass().getAnnotation(ExcelCharts.class).excelCharts());
		else
			listExcelChart = ((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart();
		return listExcelChart;
	}

	/**
	 * Generate chart.
	 *
	 * @param workbook           the workbook
	 * @param sheet              the sheet
	 * @param excelChart         the excel chart
	 * @param indexRow           the index row
	 * @param xAxis              the x axis
	 * @param mapAllChart        the map all chart
	 * @param isVerticalAndMerge the is vertical and merge
	 * @param sheetData          the sheet data
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer generateChart(XSSFWorkbook workbook, XSSFSheet sheet, ExcelChart excelChart, Integer indexRow, String xAxis, Map<String, Map<String, InfoChart>> mapAllChart, boolean isVerticalAndMerge, SheetData<? extends RowSheet> sheetData)
			throws Exception {
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		Integer startChart = indexRow;
		indexRow += excelChart.sizeRow();
		logger.debug("Start Chart: " + startChart);
		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, startChart, excelChart.sizeColumn(), indexRow);

		XSSFChart chart = drawing.createChart(anchor);
		chart.setTitleText(excelChart.title());
		chart.setTitleOverlay(false);
		XDDFChartLegend legend = chart.getOrAddLegend();
		legend.setPosition(excelChart.legendPosition());
		XDDFCategoryAxis categoryAxis = null;
		XDDFValueAxis valueAxis = null;
		AreaReference areaReference = null;
		if (!LIST_CHART_TYPES.contains(excelChart.chartTypes())) {
			categoryAxis = chart.createCategoryAxis(excelChart.categoryAxis());
			valueAxis = chart.createValueAxis(excelChart.valueAxis());

			XDDFShapeProperties leftAxisShapeProperties = valueAxis.getOrAddShapeProperties();
			leftAxisShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.axisLineColor()))));
			XDDFShapeProperties leftAxisGridLinesShapeProperties = valueAxis.getOrAddMajorGridProperties();
			leftAxisGridLinesShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.gridLineColor()))));
			valueAxis.setCrosses(excelChart.crosses());
			valueAxis.setCrossBetween(excelChart.crossBetween());
		}
		logger.debug("-----------------xAxis: " + xAxis);
		XDDFDataSource<String> xs = null;
		if (isVerticalAndMerge) {
			CellRangeAddress cellRangeAddress = CellRangeAddress.valueOf(xAxis);

			List<String> values = new ArrayList<>();
			xAxis = "";
			for (int idxRow = cellRangeAddress.getFirstRow(); idxRow <= cellRangeAddress.getLastRow(); idxRow++) {
				for (int idxColumn = cellRangeAddress.getFirstColumn(); idxColumn <= cellRangeAddress.getLastColumn(); idxColumn++) {
					XSSFCell cell = sheet.getRow(idxRow).getCell(idxColumn);
					switch (cell.getCellType()) {
					case BLANK:
						break;
					case BOOLEAN:
						break;
					case ERROR:
						break;
					case FORMULA:
						break;
					case NUMERIC:
						if (!values.contains("" + cell.getNumericCellValue())) {
							values.add("" + cell.getNumericCellValue());
							String cellAddress = ExcelUtils.coordinateCalculation(idxRow + 1, idxColumn, false, false);
							xAxis += "," + cellAddress;
						}

						break;
					case STRING:
						if (!values.contains(cell.getStringCellValue())) {
							values.add(cell.getStringCellValue());
							String cellAddress = ExcelUtils.coordinateCalculation(idxRow + 1, idxColumn, false, false);
							xAxis += "," + cellAddress;
						}

						break;
					case _NONE:
						break;
					default:
						break;
					}

				}
			}
			Collections.sort(values);
			if (StringUtils.isNotEmpty(xAxis))
				xs = XDDFDataSourcesFactory.fromArray(values.toArray(new String[0]));
			// .fromStringCellRange(sheet, CellRangeAddress.valueOf(xAxis.substring(1)));

		} else {

			areaReference = new AreaReference(xAxis, excelChart.spreadsheetVersion());
			xs = XDDFDataSourcesFactory.fromStringCellRange(StringUtils.isNotEmpty(areaReference.getFirstCell().getSheetName()) ? workbook.getSheet(areaReference.getFirstCell().getSheetName()) : sheet, CellRangeAddress.valueOf(xAxis));
		}

		XDDFChartData chartData = chart.createData(excelChart.chartTypes(), categoryAxis, valueAxis);
		XDDFChartData.Series series = null;
		int i = 0;
		Map<String, InfoChart> mapChart = mapAllChart.get(excelChart.id());
		if (MapUtils.isNotEmpty(mapChart)) {
			for (String keyChart : mapChart.keySet()) {
				InfoChart infoChart = mapChart.get(keyChart);
				String seriesChart = "";
				if (infoChart.getFunction().contains(RowStartEndType.ROW_START.getValue()) || infoChart.getFunction().replace(" ", "").contains(START)) {
					seriesChart = setInfoColumnByMapCharts(infoChart.getFunction(), sheet, infoChart);
					// xAxis = setInfoColumnByMapCharts(excelChart.xAxis(), sheet, infoChart);

				} else {
					// xAxis = ExcelBuildFunctionUtils.buildFunction(sheet, null, excelChart.xAxis(),
					// RowStartEndType.ROW_HEADER);
					seriesChart = infoChart.getFunction();
				}

				areaReference = new AreaReference(seriesChart, excelChart.spreadsheetVersion());
				XDDFNumericalDataSource<Double> numericalDataSource = XDDFDataSourcesFactory
						.fromNumericCellRange(StringUtils.isNotEmpty(areaReference.getFirstCell().getSheetName()) ? workbook.getSheet(areaReference.getFirstCell().getSheetName()) : sheet, CellRangeAddress.valueOf(seriesChart));
				series = chartData.addSeries(xs, numericalDataSource);
				series.setTitle(infoChart.getTitle(), null);
				series.setShowLeaderLines(excelChart.showLeaderLines());
				this.chartData(series, excelChart);
				solidLineSeries(series, excelChart.lineColor(), i++);
			}

		}

		chartData.setVaryColors(true);

		chartLabelData(chart, excelChart);

		chart.plot(chartData);

		barDirection(sheetData, chartData, excelChart);
		return indexRow;

	}

	/**
	 * Generate chart.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the worksheet
	 * @param title       the key chart
	 * @param excelChart  the excel chart
	 * @param indexRow    the index row
	 * @param xAxis       the x axis
	 * @param seriesChart the series chart
	 * @param sheetData   the sheet data
	 * @return the integer
	 */
	private Integer generateChart(XSSFWorkbook workbook, XSSFSheet sheet, String title, ExcelChart excelChart, Integer indexRow, String xAxis, String seriesChart, SheetData<? extends RowSheet> sheetData) {
		// ExcelChart
		// excelChart=sheetData.getClass().getAnnotation(ExcelChart.class)
		XSSFDrawing drawing = sheet.createDrawingPatriarch();
		Integer startChart = indexRow;
		indexRow += excelChart.sizeRow();
		logger.debug("Start Chart: " + startChart);
		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, startChart, excelChart.sizeColumn(), indexRow);

		XSSFChart chart = drawing.createChart(anchor);
		chart.setTitleText(title);
		chart.setTitleOverlay(false);
		XDDFChartLegend legend = chart.getOrAddLegend();
		legend.setPosition(excelChart.legendPosition());
		XDDFCategoryAxis categoryAxis = null;
		XDDFValueAxis valueAxis = null;
		if (!LIST_CHART_TYPES.contains(excelChart.chartTypes())) {
			categoryAxis = chart.createCategoryAxis(excelChart.categoryAxis());
			valueAxis = chart.createValueAxis(excelChart.valueAxis());
			XDDFShapeProperties leftAxisShapeProperties = valueAxis.getOrAddShapeProperties();
			leftAxisShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.axisLineColor()))));
			XDDFShapeProperties leftAxisGridLinesShapeProperties = valueAxis.getOrAddMajorGridProperties();
			leftAxisGridLinesShapeProperties.setLineProperties(new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.gridLineColor()))));
			valueAxis.setCrosses(excelChart.crosses());
			valueAxis.setCrossBetween(excelChart.crossBetween());
		}
		// xAxis = "'" + sheet.getSheetName().replace("'", "''") + "'!" + xAxis;
		logger.debug("-----------------xAxis: " + xAxis);
		AreaReference areaReference = new AreaReference(xAxis, excelChart.spreadsheetVersion());
		XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(StringUtils.isNotEmpty(areaReference.getFirstCell().getSheetName()) ? workbook.getSheet(areaReference.getFirstCell().getSheetName()) : sheet,
				CellRangeAddress.valueOf(xAxis));
		XDDFChartData chartData = chart.createData(excelChart.chartTypes(), categoryAxis, valueAxis);
		XDDFChartData.Series series = null;
		// seriesChart = "'" + sheet.getSheetName().replace("'", "''") + "'!" +
		// seriesChart;
		logger.debug("------------seriesChart: " + seriesChart);
		areaReference = new AreaReference(seriesChart, excelChart.spreadsheetVersion());
		XDDFNumericalDataSource<Double> numericalDataSource = XDDFDataSourcesFactory.fromNumericCellRange(StringUtils.isNotEmpty(areaReference.getFirstCell().getSheetName()) ? workbook.getSheet(areaReference.getFirstCell().getSheetName()) : sheet,
				CellRangeAddress.valueOf(seriesChart));
		series = chartData.addSeries(xs, numericalDataSource);
		series.setTitle(title, null);
		series.setShowLeaderLines(excelChart.showLeaderLines());
		this.chartData(series, excelChart);
		solidLineSeries(series, excelChart.lineColor(), 0);
		chartData.setVaryColors(true);
		chartLabelData(chart, excelChart);
		chart.plot(chartData);

		barDirection(sheetData, chartData, excelChart);

		return indexRow;

	}

	/**
	 * Bar direction.
	 *
	 * @param sheetData  the sheet data
	 * @param chartData  the chart data
	 * @param excelChart the excel chart
	 */
	private void barDirection(SheetData<? extends RowSheet> sheetData, XDDFChartData chartData, ExcelChart excelChart) {
		if (sheetData.getClass().isAnnotationPresent(ExcelBarChartData.class)) {
			ExcelBarChartData excelBarDirectionChart = sheetData.getClass().getAnnotation(ExcelBarChartData.class);
			if (ChartTypes.BAR.equals(excelChart.chartTypes())) {
				XDDFBarChartData bar = (XDDFBarChartData) chartData;
				bar.setBarDirection(excelBarDirectionChart.value());
			} else if (ChartTypes.BAR3D.equals(excelChart.chartTypes())) {
				XDDFBar3DChartData bar3D = (XDDFBar3DChartData) chartData;
				bar3D.setBarDirection(excelBarDirectionChart.value());
			}

		}
	}

	/**
	 * Chart data.
	 *
	 * @param series     the series
	 * @param excelChart the excel chart
	 */
	private void chartData(XDDFChartData.Series series, ExcelChart excelChart) {
		if (ChartTypes.LINE.equals(excelChart.chartTypes())) {
			XDDFLineChartData.Series seriesLine = (XDDFLineChartData.Series) series;
			seriesLine.setSmooth(excelChart.smooth());
		} else if (ChartTypes.LINE3D.equals(excelChart.chartTypes())) {
			XDDFLine3DChartData.Series seriesLine3D = (XDDFLine3DChartData.Series) series;
			seriesLine3D.setSmooth(excelChart.smooth());

		}
	}

	/**
	 * Solid line series.
	 *
	 * @param series the series
	 * @param color  the color
	 * @param i      the i
	 */
	private void solidLineSeries(XDDFChartData.Series series, PresetColor[] color, int i) {
		int index = i % color.length;
		XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(color[index]));
		XDDFLineProperties line = new XDDFLineProperties();
		line.setFillProperties(fill);
		XDDFShapeProperties properties = series.getShapeProperties();
		if (properties == null) {
			properties = new XDDFShapeProperties();
		}
		properties.setLineProperties(line);
		series.setShapeProperties(properties);
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
				Object value = PropertyUtils.getProperty(baseSheet, field.getName());
				if (value != null) {
					if (!(value instanceof String))
						throw new ExcelGeneratorException(field.getName() + " field type is not supported: required String");
					if (StringUtils.isNotBlank(value.toString())) {
						CellStyle cellStype = ExcelLayoutUtility.createCellStyle(workbook, excelLabel.labelLayout(), 0,this.valueProps);
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

	/**
	 * Chart label data.
	 *
	 * @param chart      the chart
	 * @param excelChart the excel chart
	 */
	private void chartLabelData(XSSFChart chart, ExcelChart excelChart) {
		ExcelChartDataLabel excelChartDataLabel = excelChart.excelChartDataLabel();
		if (excelChartDataLabel.enable()) {
			CTPlotArea plotArea = chart.getCTChart().getPlotArea();
			switch (excelChart.chartTypes()) {
			case AREA:
				for (CTAreaChart area : plotArea.getAreaChartArray()) {
					for (CTAreaSer ser : area.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case AREA3D:
				for (CTArea3DChart area3D : plotArea.getArea3DChartArray()) {
					for (CTAreaSer ser : area3D.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case BAR:
				for (CTBarChart bar : plotArea.getBarChartArray()) {
					for (CTBarSer ser : bar.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case BAR3D:
				for (CTBar3DChart bar3D : plotArea.getBar3DChartArray()) {
					for (CTBarSer ser : bar3D.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case DOUGHNUT:
				break;
			case LINE:
				for (CTLineChart line : plotArea.getLineChartArray()) {
					for (CTLineSer ser : line.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case LINE3D:
				for (CTLine3DChart line3D : plotArea.getLine3DChartArray()) {
					for (CTLineSer ser : line3D.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case PIE:
				for (CTPieChart pie : plotArea.getPieChartArray()) {
					for (CTPieSer ser : pie.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case PIE3D:
				for (CTPie3DChart pie3D : plotArea.getPie3DChartArray()) {
					for (CTPieSer ser : pie3D.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case RADAR:
				for (CTRadarChart radar : plotArea.getRadarChartArray()) {
					for (CTRadarSer ser : radar.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			case SCATTER:
				for (CTScatterChart scatter : plotArea.getScatterChartArray()) {
					for (CTScatterSer ser : scatter.getSerArray()) {
						CTDLbls dLbls = ser.getDLbls();
						dLbls.addNewShowVal().setVal(excelChartDataLabel.showVal());
						dLbls.addNewShowLegendKey().setVal(excelChartDataLabel.showLegendKey());
						dLbls.addNewShowCatName().setVal(excelChartDataLabel.showCatName());
						dLbls.addNewShowSerName().setVal(excelChartDataLabel.showSerName());
					}
				}
				break;
			default:
				break;

			}
		}
	}
}
