/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.impl.ScopeGenerateExcelImpl.java
*/
package bld.generator.report.excel.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.DynamicChart;
import bld.generator.report.excel.DynamicRowSheet;
import bld.generator.report.excel.FunctionsTotal;
import bld.generator.report.excel.MergeSheet;
import bld.generator.report.excel.QuerySheetData;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.ScopeGenerateExcel;
import bld.generator.report.excel.SheetComponent;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.SheetFunctionTotal;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelAreaBorder;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelChart;
import bld.generator.report.excel.annotation.ExcelCharts;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelFreezePane;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelPivot;
import bld.generator.report.excel.annotation.ExcelRowHeight;
import bld.generator.report.excel.annotation.ExcelSelectCell;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSubtotals;
import bld.generator.report.excel.annotation.ExcelSummary;
import bld.generator.report.excel.annotation.ExcelSuperHeaders;
import bld.generator.report.excel.annotation.impl.ExcelFunctionImpl;
import bld.generator.report.excel.constant.BorderType;
import bld.generator.report.excel.constant.ExcelConstant;
import bld.generator.report.excel.constant.RowStartEndType;
import bld.generator.report.excel.data.DropDownCell;
import bld.generator.report.excel.data.FunctionCell;
import bld.generator.report.excel.data.InfoChart;
import bld.generator.report.excel.data.InfoColumn;
import bld.generator.report.excel.data.LayoutCell;
import bld.generator.report.excel.data.MergeCell;
import bld.generator.report.excel.data.ReportExcel;
import bld.generator.report.excel.data.SheetHeader;
import bld.generator.report.excel.data.SubtotalRow;
import bld.generator.report.excel.query.ExcelQueryComponent;
import bld.generator.report.utils.ExcelUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ScopeGenerateExcelImpl.<br>
 * ScopeGenerateExcelImpl is the heart of the generation of the xls or xlsx
 * files.
 */
@Component
@SuppressWarnings("unchecked")
@Scope("prototype")
public class ScopeGenerateExcelImpl extends SuperGenerateExcelImpl implements ScopeGenerateExcel {

	/** The number empty rows. */
	@Value("${bld.commons.number.empty.rows:2}")
	private int numberEmptyRows;

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ScopeGenerateExcelImpl.class);

	/** The path copertina xls. */
	@Value("${bld.commons.path.xls:}")
	private String pathCopertinaXls;

	/** The path copertina xlsx. */
	@Value("${bld.commons.path.xlsx:}")
	private String pathCoverXlsx;

	/** The resource path copertina xls. */
	@Value("${bld.commons.resource.path.xls:}")
	private String resourcePathCopertinaXls;

	/** The resource path copertina xlsx. */
	@Value("${bld.commons.resource.path.xlsx:}")
	private String resourcePathCoverXlsx;

	/** The Constant LIST_CHART_TYPES. */
	private final static List<ChartTypes> LIST_CHART_TYPES = listChartTypes();

	/** The excel query component. */
	@Autowired(required = false)
	private ExcelQueryComponent excelQueryComponent;

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
		byte[] byteExcel = null;
		HSSFWorkbook workbook = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			boolean isCover = true;
			if (StringUtils.isNotBlank(this.pathCopertinaXls)) {
				InputStream inputstream = new FileInputStream(this.pathCopertinaXls);
				workbook = new HSSFWorkbook(inputstream);
			} else if (StringUtils.isNotBlank(this.resourcePathCopertinaXls)) {
				InputStream inputstream = getClass().getResourceAsStream(this.resourcePathCopertinaXls);
				workbook = new HSSFWorkbook(inputstream);
			} else {
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
		byte[] byteExcel = null;
		XSSFWorkbook workbook = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			boolean isCover = true;
			if (StringUtils.isNotBlank(this.pathCoverXlsx)) {
				InputStream inputstream = new FileInputStream(this.pathCoverXlsx);
				workbook = new XSSFWorkbook(inputstream);
			} else if (StringUtils.isNotBlank(this.resourcePathCoverXlsx)) {
				InputStream inputstream = getClass().getResourceAsStream(this.resourcePathCoverXlsx);
				workbook = new XSSFWorkbook(inputstream);
			} else {
				workbook = new XSSFWorkbook();
				isCover = false;
			}

			this.setCoverParameters(report, byteArrayOutputStream, workbook, isCover);
			byteExcel = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
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
		SXSSFWorkbook workbook = null;
		XSSFWorkbook coverWorkbook = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			// boolean isCover = true;
			if (StringUtils.isNotBlank(this.pathCoverXlsx)) {
				InputStream inputstream = new FileInputStream(this.pathCoverXlsx);
				coverWorkbook = new XSSFWorkbook(inputstream);
				updateCover(report, coverWorkbook);
				workbook = new SXSSFWorkbook(coverWorkbook);
			} else if (StringUtils.isNotBlank(this.resourcePathCoverXlsx)) {
				InputStream inputstream = getClass().getResourceAsStream(this.resourcePathCoverXlsx);
				coverWorkbook = new XSSFWorkbook(inputstream);
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

	private void updateCover(ReportExcel report, Workbook workbook) throws Exception {
		Set<Field> listField = ExcelUtils.getListField(report.getClass());
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
					excelDate = ExcelUtils.getAnnotation(field, ExcelDate.class);
					cellStyle = dateCellStyle(workbook, cellStyle, excelDate.format().getValue());
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
	 * It scores the {@link bld.generator.report.excel.BaseSheet} list, each
	 * {@link bld.generator.report.excel.BaseSheet} is equivalent to one sheet.<br>
	 *
	 * @param report   the report
	 * @param workbook the workbook
	 * @return the workbook
	 * @throws Exception the exception
	 */
	private Workbook createSheets(ReportExcel report, Workbook workbook) throws Exception {
		List<BaseSheet> listSheet = report.getListBaseSheet();
		int indexSheetName = 0;

		this.mapCellStyle = new HashMap<>();
		this.mapCellHeaderStyle = new HashMap<>();
		this.mapFieldColumn = new HashMap<>();
		this.listFunctionCell = new ArrayList<>();
		this.listDropDown = new ArrayList<>();
		
		for (BaseSheet baseSheet : listSheet) {
			Date startSheet = new Date();
			Sheet sheet = null;
			this.mapWidthColumn = new HashMap<>();
			if (baseSheet.getSheetName() != null) {
				if (workbook.getSheet(baseSheet.getSheetName()) == null && baseSheet.getSheetName().length() <= ExcelConstant.SHEET_NAME_SIZE)
					sheet = workbook.createSheet(baseSheet.getSheetName().replace("/", ""));
				else {
					logger.warn("Sheet name exceeded the maximum limit " + ExcelConstant.SHEET_NAME_SIZE + " characters");
					sheet = workbook.createSheet((indexSheetName++) + "-" + baseSheet.getSheetName().replace("/", ""));
				}

			} else
				sheet = workbook.createSheet("Undefined " + (indexSheetName++));
			logger.info("Sheet name: "+sheet.getSheetName());
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
		//	formulaEvaluator.evaluateAll();
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
		for (DropDownCell dropDownCell : this.listDropDown)
			super.addDropDown(dropDownCell);

		return workbook;
	}

	/**
	 * Generate merge sheet.<br>
	 * 
	 * To manage the {@link bld.generator.report.excel.MergeSheet} classes.
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
	 * @param worksheet        the worksheet
	 * @param sheetSummary     the sheet summary
	 * @param indexRow         the index row
	 * @param formulaEvaluator the formula evaluator
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer generateSheetSummary(Workbook workbook, Sheet worksheet, SheetSummary sheetSummary, Integer indexRow, FormulaEvaluator formulaEvaluator) throws Exception {
		Class<? extends SheetSummary> classSheet = sheetSummary.getClass();
		ExcelSummary excelSummary = classSheet.getAnnotation(ExcelSummary.class);
		ExcelSheetLayout excelSheetLayout = ExcelUtils.getAnnotation(sheetSummary.getClass(), ExcelSheetLayout.class);
		indexRow += excelSheetLayout.startRow();
		if (indexRow < 0)
			throw new Exception("The row number cannot be negative");
		if (excelSheetLayout.showHeader() && excelSummary != null && StringUtils.isNotBlank(excelSummary.title())) {
			Row rowHeader = worksheet.createRow(indexRow);
			CellStyle cellStyleHeader = getCellStyleHeader(workbook, worksheet, sheetSummary, rowHeader);
			Cell cellHeader = rowHeader.createCell(excelSheetLayout.startColumn());
			cellHeader.setCellStyle(cellStyleHeader);
			cellHeader.setCellValue(excelSummary.title());
			if (StringUtils.isNotBlank(excelSummary.comment()))
				addComment(workbook, worksheet, rowHeader, cellHeader, excelSummary.comment());
			cellHeader = rowHeader.createCell(excelSheetLayout.startColumn() + 1);
			cellHeader.setCellStyle(cellStyleHeader);
			worksheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, excelSheetLayout.startColumn(), excelSheetLayout.startColumn() + 1));
			setColumnWidth(worksheet, excelSheetLayout.startColumn(), excelSummary.widthColumn1());
			setColumnWidth(worksheet, excelSheetLayout.startColumn() + 1, excelSummary.widthColumn2());
			indexRow++;
		}
		List<SheetHeader> listSheetHeader = getListSheetHeader(classSheet, sheetSummary, worksheet);
		Row row = null;
		for (SheetHeader sheetHeader : listSheetHeader) {
			row = worksheet.createRow(indexRow);
			setCellSummary(excelSheetLayout, workbook, worksheet, sheetSummary, sheetHeader, row, indexRow, formulaEvaluator);
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
		logger.info("SheetData: "+sheetData.getClass().getSimpleName());
		logger.info("RowSheet: "+sheetData.getRowClass().getSimpleName());
		if (this.excelQueryComponent != null && sheetData instanceof QuerySheetData)
			this.excelQueryComponent.executeQuery((QuerySheetData<? extends RowSheet>) sheetData);
		// this.mapFieldColumn = sheetData.getMapFieldColumn();
		ExcelSheetLayout excelSheetLayout = ExcelUtils.getAnnotation(sheetData.getClass(), ExcelSheetLayout.class);
		indexRow += excelSheetLayout.startRow();
		if (indexRow < 0)
			throw new Exception("The row number cannot be negative");
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

		Map<String, InfoChart> mapChart = new LinkedHashMap<>();

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

		List<SubtotalRow> emptyRows = new ArrayList<>();
		for (RowSheet rowSheet : sheetData.getListRowSheet()) {
			int splitRow = 0;
			if(rowSheet.getClass().isAnnotationPresent(ExcelSubtotals.class)) {
				String nameField = listSheetHeader.get(0).getField().getName();
				if (rowSheet.getClass().getAnnotation(ExcelSubtotals.class).sumForGroup() && lastRowSheet != null
						&& !PropertyUtils.getProperty(lastRowSheet, nameField).equals(PropertyUtils.getProperty(rowSheet, nameField))) {
					splitRow = 1;
					emptyRows.add(new SubtotalRow(indexRow++, BeanUtils.getProperty(lastRowSheet, nameField)));
				}
			}
			row = sheet.createRow(indexRow);
			Map<String, Object> mapValue = new HashMap<>();
			CellStyle cellStyle = null;
			row.setHeight(heightRow);
			for (int numColumn = excelSheetLayout.startColumn(); numColumn < maxColumn; numColumn++) {
				int indexHeader = numColumn - excelSheetLayout.startColumn();
				SheetHeader sheetHeader = listSheetHeader.get(indexHeader);
				CellType cellType = CellType.BLANK;
				if (sheetHeader.getExcelFunction() != null)
					cellType = CellType.FORMULA;
				Cell cell = row.createCell(numColumn, cellType);
				InfoColumn infoColumn = (InfoColumn) mapFieldColumn.get(sheetHeader.getKey());

				Field field = listSheetHeader.get(indexHeader).getField();
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
							this.mapCellStyle.put(layoutCellTemp, createCellStyle(workbook, excelCellLayout, sheetHeader.getExcelDate(), colorModul));
					}
					cellStyle = this.mapCellStyle.get(layoutCell);
					infoColumn.setFirstRow(indexRow);
					infoColumn.setLastRow(indexRow + sheetData.getListRowSheet().size() - 1);
				}
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
							super.manageDropDown(sheet, sheetHeader, cell.getRowIndex(), cell.getRowIndex(), cell.getColumnIndex(), cell.getColumnIndex());
							super.setCellValueExcel(workbook, sheet, cell, cellStyle, sheetHeader, indexRow, formulaEvaluator);
						}

						repeat = false;
					} else {
						int workRow = indexRow - splitRow;
						infoColumn.getMapRowMergeRow().put(workRow, infoColumn.getMergeCell());
						if (numColumn > excelSheetLayout.startColumn() && StringUtils.isBlank(sheetHeader.getExcelMergeRow().referenceField()))
							throw new Exception("Only first column can have the propertie \"referenceColumn\" is blank!!!");
						if (field != null)
							valueBefore = PropertyUtils.getProperty(lastRowSheet, field.getName());
						if (StringUtils.isBlank(sheetHeader.getExcelMergeRow().referenceField())) {
							if (!(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
								super.mergeRowAndRemoveMap(workbook, sheet, workRow, mapMergeRow, numColumn, formulaEvaluator);
							else
								repeat = super.setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader, workRow, sheet);

						} else if (StringUtils.isNotBlank(sheetHeader.getExcelMergeRow().referenceField())) {
							String referenceField = sheetHeader.getExcelMergeRow().referenceField();
							Object valueRefColumn = PropertyUtils.getProperty(rowSheet, referenceField);
							Object valueRefColumnBefore = PropertyUtils.getProperty(lastRowSheet, referenceField);
							if ((valueRefColumn != null && valueRefColumnBefore != null && !valueRefColumn.equals(valueRefColumnBefore)) || !(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
								super.mergeRowAndRemoveMap(workbook, sheet, workRow, mapMergeRow, numColumn, formulaEvaluator);
							else {
								repeat = super.setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader, workRow, sheet);
							}
						}

					}

				} while (repeat);

			}
			lastRowSheet = rowSheet;
			if (sheetData.getClass().isAnnotationPresent(ExcelCharts.class) || (sheetData instanceof DynamicChart && CollectionUtils.isNotEmpty(((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart()))) {
				List<ExcelChart> listExcelChart = getExcelChart(sheetData);
				for (ExcelChart excelChart : listExcelChart) {
					String functionChart = buildFunction(sheet, indexRow, excelChart.function(), RowStartEndType.ROW_EMPTY,true);
					String title="";
					if(mapValue.containsKey(excelChart.fieldName().replace("${", "").replace("}", ""))) 
						title=mapValue.get(excelChart.fieldName().replace("${", "").replace("}", "")).toString();
					else {
						String cellFieldName=buildFunction(sheet, indexRow, excelChart.fieldName(), RowStartEndType.ROW_EMPTY,true);
						cellFieldName=buildFunction(sheet, indexRow, cellFieldName, RowStartEndType.ROW_HEADER,true);
						CellReference cr = new CellReference(cellFieldName);
						title=sheet.getRow(cr.getRow()).getCell(cr.getCol()).getStringCellValue();
					}
					
					String key=title+excelChart.function();
					//String title = mapValue.get(excelChart.fieldName()).toString();
					if(!mapChart.containsKey(key)) {
						mapChart.put(key, new InfoChart(title,functionChart, indexRow.intValue()));
					}else {
						mapChart.get(key).setLastRow(indexRow.intValue());
					}
					
				}

			}

			start = false;
			indexRow++;
			// this.evaluateAllFormulaCells(workbook, sheet);
		}
		for (Integer numColumn : mapMergeRow.keySet())
			super.mergeRow(workbook, sheet, indexRow, mapMergeRow, numColumn, formulaEvaluator);
		

		if (sheetData.getRowClass().isAnnotationPresent(ExcelSubtotals.class)) {
			ExcelSubtotals excelSubtotals = sheetData.getRowClass().getAnnotation(ExcelSubtotals.class);
			if (excelSubtotals.sumForGroup())
				emptyRows.add(new SubtotalRow(indexRow++, BeanUtils.getProperty(lastRowSheet, listSheetHeader.get(0).getField().getName())));
			emptyRows.add(new SubtotalRow(indexRow++, excelSubtotals.labelTotalGroup()));
			int lastRowSubtotal = startRowSheet;
			for (SubtotalRow emptyRow : emptyRows) {
				row = sheet.createRow(emptyRow.getEmptyRow());
				CellStyle cellStyle = null;
				row.setHeight(heightRow);
				for (int numColumn = excelSheetLayout.startColumn(); numColumn < maxColumn; numColumn++) {
					int indexHeader = numColumn - excelSheetLayout.startColumn();
					Cell cell = row.createCell(numColumn);
					SheetHeader sheetHeader = (SheetHeader) listSheetHeader.get(indexHeader).clone();
					String nameField = null;
					if (sheetHeader.getField() != null)
						nameField = sheetHeader.getField().getName();
					else if (sheetHeader.getExcelFunction() != null)
						nameField = sheetHeader.getExcelFunction().nameFunction();
					ExcelCellLayout excelCellLayout = null;
					if (indexHeader == 0) {

						if (emptyRows.size() - 1 > emptyRows.indexOf(emptyRow))
							sheetHeader.setValue(excelSubtotals.startLabel().trim() + " " + emptyRow.getLabel().trim() + " " + excelSubtotals.endLabel().trim());
						else
							sheetHeader.setValue(emptyRow.getLabel().trim());
						excelCellLayout = excelSubtotals.excelCellLayout();

						cellStyle = getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), excelSubtotals, emptyRow, sheetHeader, excelCellLayout);
						sheetHeader.setExcelCellLayout(excelSubtotals.excelCellLayout());

					} else if (sheetHeader.getExcelSubtotal() != null && sheetHeader.getExcelSubtotal().enable()) {
						sheetHeader.setValue(null);
						excelCellLayout = sheetHeader.getExcelSubtotal().excelCellLayout();
						cellStyle = getCellStyleSubtotal(workbook, emptyRow.getEmptyRow(), excelSubtotals, emptyRow, sheetHeader, excelCellLayout);
						String function = "subtotal(" + sheetHeader.getExcelSubtotal().dataConsolidateFunction().getValue() + "," + RowStartEndType.ROW_START.getParameter(nameField) + ":" + RowStartEndType.ROW_END.getParameter(nameField) + ")";
						if (emptyRows.size() - 1 == emptyRows.indexOf(emptyRow))
							lastRowSubtotal = startRowSheet;
						function = buildFunction(sheet, lastRowSubtotal, function, RowStartEndType.ROW_START);
						function = buildFunction(sheet, emptyRow.getEmptyRow() - 1, function, RowStartEndType.ROW_END);
						ExcelFunctionImpl excelFuctionImpl = null;
						excelFuctionImpl = new ExcelFunctionImpl(function, nameField + "Function", false);
						sheetHeader.setExcelFunction(excelFuctionImpl.getAnnotation());

					} else {
						sheetHeader.setValue(null);
						sheetHeader.setExcelFunction(null);
					}

					super.setCellValueExcel(workbook, sheet, cell, cellStyle, sheetHeader, indexRow, formulaEvaluator);

				}
				lastRowSubtotal = emptyRow.getEmptyRow() + 1;

			}
		}

		if (excelSheetLayout.groupRow())
			sheet.groupRow(startRowSheet, indexRow - 1);

		if (excelSheetLayout.groupColumn())
			sheet.groupColumn(excelSheetLayout.startColumn(), maxColumn - 1);

		if (!isMergeSheet && excelSheetLayout.notMerge() && excelSheetLayout.sortAndFilter() && excelSheetLayout.showHeader())
			sheet.setAutoFilter(new CellRangeAddress(startRowSheet - 1, indexRow - 1, excelSheetLayout.startColumn(), listSheetHeader.size() + excelSheetLayout.startColumn() - 1));

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
			for (ExcelChart excelChart : listExcelChart) {
				String xAxis = buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_EMPTY,true);
				indexRow += 2;
				if (excelChart.group()) {
					xAxis = buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER,true);
					xAxis=setInfoColumnByMapCharts(xAxis, sheet, null);
					indexRow = generateChart((XSSFSheet) sheet, excelChart, indexRow, xAxis, mapChart);
				} else {
					for (String keyChart : mapChart.keySet()) {
						if(keyChart.endsWith(excelChart.function())) {
							InfoChart infoChart=mapChart.get(keyChart);
							String seriesChart="";
							if(infoChart.getFunction().contains(RowStartEndType.ROW_START.getValue())) {
								seriesChart=setInfoColumnByMapCharts(infoChart.getFunction(), sheet, infoChart);
								xAxis=setInfoColumnByMapCharts(excelChart.xAxis(), sheet, infoChart);
								
							}else {
								xAxis = buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER,true);
								seriesChart=infoChart.getFunction();
							}
								
							
//							seriesChart=buildFunction(sheet, null, seriesChart, RowStartEndType.ROW_START);
//							seriesChart=buildFunction(sheet, null, seriesChart, RowStartEndType.ROW_END);
							indexRow = generateChart((XSSFSheet) sheet, infoChart.getTitle(), excelChart, indexRow, xAxis, seriesChart);
						}
						
					}
						
				}

			}

		}

		if (sheet instanceof XSSFSheet && sheetData.getClass().isAnnotationPresent(ExcelPivot.class))
			indexRow = this.createPivot((XSSFSheet) sheet, sheetData, startRowSheet, excelSheetLayout.startColumn(), indexRow, listSheetHeader.size() + excelSheetLayout.startColumn() - 1, indexRow);

		for (ExcelAreaBorder areaBorder : excelSheetLayout.areaBorder()) {
			String areaRange = areaBorder.areaRange();
			areaRange = this.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_EMPTY);
			areaRange = this.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_END);
			areaRange = this.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_HEADER);
			areaRange = this.buildFunction(sheet, null, areaRange, RowStartEndType.ROW_START);
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

		return indexRow;

	}
	
	
	private String setInfoColumnByMapCharts(String function,Sheet sheet,InfoChart infoChart) throws Exception {
		Pattern p = Pattern.compile("\\$\\{.*?RowStart}");
		Matcher m =p.matcher(function);
		if(m.find()) {
			String fieldName=ExcelUtils.getKeyColumn(sheet, m.group().replace(RowStartEndType.ROW_START.getValue()+"}", "").replace("${",""));
			if(infoChart!=null) {
				mapFieldColumn.get(fieldName).setFirstRow(infoChart.getFirstRow());
				mapFieldColumn.get(fieldName).setLastRow(infoChart.getLastRow());
			}
			function=buildFunction(sheet, null, function, RowStartEndType.ROW_START,true);
			function=buildFunction(sheet, null, function, RowStartEndType.ROW_END,true);
		}
		return function;
	}

	/**
	 * Gets the cell style subtotal.
	 *
	 * @param workbook           the workbook
	 * @param indexRow           the index row
	 * @param excelLabelSubtotal the excel label subtotal
	 * @param emptyRow           the empty row
	 * @param sheetHeader        the sheet header
	 * @param excelCellLayout    the excel cell layout
	 * @return the cell style subtotal
	 * @throws Exception the exception
	 */
	private CellStyle getCellStyleSubtotal(Workbook workbook, Integer indexRow, ExcelSubtotals excelLabelSubtotal, SubtotalRow emptyRow, SheetHeader sheetHeader, ExcelCellLayout excelCellLayout) throws Exception {
		CellStyle cellStyle = null;
		sheetHeader.setExcelCellLayout(excelCellLayout);
		LayoutCell layoutCell = ExcelUtils.reflectionAnnotation(new LayoutCell(), sheetHeader.getExcelCellLayout());
		layoutCell.setColor(indexRow);
		if (!this.mapCellStyle.containsKey(layoutCell))
			this.mapCellStyle.put(layoutCell, createCellStyle(workbook, sheetHeader.getExcelCellLayout(), null, emptyRow.getEmptyRow()));
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
	 * @param sheet      the sheet
	 * @param excelChart the excel chart
	 * @param indexRow   the index row
	 * @param xAxis      the x axis
	 * @param mapChart   the map chart
	 * @return the integer
	 * @throws Exception 
	 */
	private Integer generateChart(XSSFSheet sheet, ExcelChart excelChart, Integer indexRow, String xAxis, Map<String, InfoChart> mapChart) throws Exception {
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
		XDDFCategoryAxis bottomAxis = null;
		XDDFValueAxis leftAxis = null;
		if (!LIST_CHART_TYPES.contains(excelChart.chartTypes())) {
			bottomAxis = chart.createCategoryAxis(excelChart.categoryAxis());
			leftAxis = chart.createValueAxis(excelChart.valueAxis());
			
			XDDFShapeProperties leftAxisShapeProperties = leftAxis.getOrAddShapeProperties();
			leftAxisShapeProperties.setLineProperties(
					new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.axisLineColor()))));
			// major gridlines of values axis are the cobweb ring lines
			XDDFShapeProperties leftAxisGridLinesShapeProperties = leftAxis.getOrAddMajorGridProperties();
			leftAxisGridLinesShapeProperties.setLineProperties(
					new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.gridLineColor()))));
			leftAxis.setCrosses(excelChart.crosses());
			leftAxis.setCrossBetween(excelChart.crossBetween());
		}
		logger.debug("-----------------xAxis: " + xAxis);
		XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(sheet, CellRangeAddress.valueOf(xAxis));
		XDDFChartData chartData = chart.createData(excelChart.chartTypes(), bottomAxis, leftAxis);
		XDDFChartData.Series series = null;
		int i=0;
		for (String keyChart : mapChart.keySet()) {
			InfoChart infoChart=mapChart.get(keyChart);
			String seriesChart="";
			if(infoChart.getFunction().contains(RowStartEndType.ROW_START.getValue())) {
				seriesChart=setInfoColumnByMapCharts(infoChart.getFunction(), sheet, infoChart);
				xAxis=setInfoColumnByMapCharts(excelChart.xAxis(), sheet, infoChart);
				
			}else {
				xAxis = buildFunction(sheet, null, excelChart.xAxis(), RowStartEndType.ROW_HEADER);
				seriesChart=infoChart.getFunction();
			}

			logger.debug("------------seriesChart: " + seriesChart);
			series = chartData.addSeries(xs, XDDFDataSourcesFactory.fromNumericCellRange(sheet, CellRangeAddress.valueOf(seriesChart)));
			series.setTitle(infoChart.getTitle(), null);
			series.setShowLeaderLines(true);
			solidLineSeries(series,excelChart.lineColor(),i++);
		}

		chartData.setVaryColors(true);
		chart.plot(chartData);

		return indexRow;

	}

	/**
	 * Generate chart.
	 *
	 * @param sheet       the worksheet
	 * @param title       the key chart
	 * @param excelChart  the excel chart
	 * @param indexRow    the index row
	 * @param xAxis       the x axis
	 * @param seriesChart the series chart
	 * @return the integer
	 */
	private Integer generateChart(XSSFSheet sheet, String title, ExcelChart excelChart, Integer indexRow, String xAxis, String seriesChart) {
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
		XDDFCategoryAxis bottomAxis = null;
		XDDFValueAxis leftAxis = null;
		if (!LIST_CHART_TYPES.contains(excelChart.chartTypes())) {
			bottomAxis = chart.createCategoryAxis(excelChart.categoryAxis());
			leftAxis = chart.createValueAxis(excelChart.valueAxis());
			XDDFShapeProperties leftAxisShapeProperties = leftAxis.getOrAddShapeProperties();
			leftAxisShapeProperties.setLineProperties(
					new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.axisLineColor()))));
			XDDFShapeProperties leftAxisGridLinesShapeProperties = leftAxis.getOrAddMajorGridProperties();
			leftAxisGridLinesShapeProperties.setLineProperties(
					new XDDFLineProperties(new XDDFSolidFillProperties(XDDFColor.from(excelChart.gridLineColor()))));
			leftAxis.setCrosses(excelChart.crosses());
			leftAxis.setCrossBetween(excelChart.crossBetween());
		}
		xAxis=sheet.getSheetName()+"!"+xAxis;
		logger.debug("-----------------xAxis: " + xAxis);
		XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(sheet, CellRangeAddress.valueOf(xAxis));
		XDDFChartData chartData = chart.createData(excelChart.chartTypes(), bottomAxis, leftAxis);
		XDDFChartData.Series series = null;
		seriesChart=sheet.getSheetName()+"!"+seriesChart;
		logger.debug("------------seriesChart: " + seriesChart);
		series = chartData.addSeries(xs, XDDFDataSourcesFactory.fromNumericCellRange(sheet, CellRangeAddress.valueOf(seriesChart)));
		series.setTitle(title, null);
		series.setShowLeaderLines(true);
		solidLineSeries(series, excelChart.lineColor(),0);
		chartData.setVaryColors(true);

		chart.plot(chartData);

		return indexRow;

	}
	
	private void solidLineSeries(XDDFChartData.Series series, PresetColor[] color,int i) {
		int index=i%color.length;
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
		Set<Field> listField = ExcelUtils.getListField(classSheet);
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelLabel.class)) {
				Row row = sheet.createRow(indexRow);
				ExcelLabel excelLabel = field.getAnnotation(ExcelLabel.class);
				Object value = PropertyUtils.getProperty(baseSheet, field.getName());
				if (value != null) {
					if (!(value instanceof String))
						throw new Exception(field.getName() + " field type is not supported: required String");
					if (StringUtils.isNotBlank(value.toString())) {
						CellStyle cellStype = createCellStyle(workbook, excelLabel.labelLayout(), 0);
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
						for (int indexColumn = 1; indexColumn < excelLabel.columnMerge(); indexColumn++) {
							Cell cell = row.createCell(indexColumn);
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

}
