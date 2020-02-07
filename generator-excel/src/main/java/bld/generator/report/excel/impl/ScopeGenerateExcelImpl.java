/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.impl;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import bld.generator.report.excel.BaseSheet;
import bld.generator.report.excel.DynamicChart;
import bld.generator.report.excel.DynamicRowSheet;
import bld.generator.report.excel.FunctionsTotal;
import bld.generator.report.excel.MergeSheet;
import bld.generator.report.excel.RowSheet;
import bld.generator.report.excel.ScopeGenerateExcel;
import bld.generator.report.excel.SheetComponent;
import bld.generator.report.excel.SheetData;
import bld.generator.report.excel.SheetFunctionTotal;
import bld.generator.report.excel.SheetSummary;
import bld.generator.report.excel.annotation.ExcelCellLayout;
import bld.generator.report.excel.annotation.ExcelChart;
import bld.generator.report.excel.annotation.ExcelCharts;
import bld.generator.report.excel.annotation.ExcelDate;
import bld.generator.report.excel.annotation.ExcelFreezePane;
import bld.generator.report.excel.annotation.ExcelLabel;
import bld.generator.report.excel.annotation.ExcelSheetLayout;
import bld.generator.report.excel.annotation.ExcelSummary;
import bld.generator.report.excel.data.LayoutCell;
import bld.generator.report.excel.data.MergeCell;
import bld.generator.report.excel.data.SheetHeader;
import bld.generator.report.utils.ExcelUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ScopeGenerateExcelImpl.
 */
@Component
@SuppressWarnings("unchecked")
@Scope("prototype")
public class ScopeGenerateExcelImpl extends SuperGenerateExcelImpl implements ScopeGenerateExcel {

	/** The Constant logger. */
	private final static Log logger = LogFactory.getLog(ScopeGenerateExcelImpl.class);

	/** The path copertina xls. */
	@Value("${path.xls:}")
	private String pathCopertinaXls;

	/** The path copertina xlsx. */
	@Value("${path.xlsx:}")
	private String pathCopertinaXlsx;

	/** The resource path copertina xls. */
	@Value("${resource.path.xls:}")
	private String resourcePathCopertinaXls;

	/** The resource path copertina xlsx. */
	@Value("${resource.path.xlsx:}")
	private String resourcePathCopertinaXlsx;

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
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] byteExcel = null;
		boolean isCopertina = true;
		Workbook workbook = null;
		if (StringUtils.isNotBlank(this.pathCopertinaXls)) {
			InputStream inputstream = new FileInputStream(this.pathCopertinaXls);
			workbook = new HSSFWorkbook(inputstream);
		} else if (StringUtils.isNotBlank(this.resourcePathCopertinaXls)) {
			InputStream inputstream = getClass().getResourceAsStream(this.resourcePathCopertinaXls);
			workbook = new HSSFWorkbook(inputstream);
		} else {
			workbook = new HSSFWorkbook();
			isCopertina = false;
		}
		setParametriCopertina(report, byteArrayOutputStream, workbook, isCopertina);
		byteExcel = byteArrayOutputStream.toByteArray();

		return byteExcel;
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
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] byteExcel = null;
		boolean isCopertina = true;
		Workbook workbook = null;
		if (StringUtils.isNotBlank(this.pathCopertinaXlsx)) {
			InputStream inputstream = new FileInputStream(this.pathCopertinaXlsx);
			// getClass().getResourceAsStream();
			workbook = new XSSFWorkbook(inputstream);
		} else if (StringUtils.isNotBlank(this.resourcePathCopertinaXlsx)) {
			InputStream inputstream = getClass().getResourceAsStream(this.resourcePathCopertinaXlsx);
			workbook = new XSSFWorkbook(inputstream);
		} else {
			workbook = new XSSFWorkbook();
			isCopertina = false;
		}

		setParametriCopertina(report, byteArrayOutputStream, workbook, isCopertina);
		byteExcel = byteArrayOutputStream.toByteArray();

		return byteExcel;
	}

	/**
	 * Sets the parametri copertina.
	 *
	 * @param report                the report
	 * @param byteArrayOutputStream the byte array output stream
	 * @param workbook              the workbook
	 * @param isCopertina           the is copertina
	 * @throws Exception the exception
	 */
	private void setParametriCopertina(ReportExcel report, ByteArrayOutputStream byteArrayOutputStream, Workbook workbook, boolean isCopertina) throws Exception {
		if (isCopertina) {
			Sheet worksheet = workbook.getSheetAt(0);
			CellStyle style = workbook.getSheetAt(0).getColumnStyle(0);
			style.setWrapText(true);
			Cell cell = worksheet.getRow(11).getCell(0);
			cell.setCellValue(report.getTitolo());
			cell = worksheet.getRow(17).getCell(0);
			cell.setCellValue(SDF.format(new Date()));
		}

		// if (CommonUtil.isNotNullAndIsNotEmpty(report.getListBaseSheet()) &&
		// report.getListBaseSheet().get(0) instanceof ExcelSommario) {
		// cell = worksheet.getRow(7).getCell(0);
		// cell.setCellValue(((ExcelSommario)
		// report.getListBaseSheet().get(0)).getListCampi().get(0));
		// }
		workbook = createSheet(report, workbook);
		workbook.write(byteArrayOutputStream);
		workbook.close();
	}

	/**
	 * Creates the sheet.
	 *
	 * @param report   the report
	 * @param workbook the workbook
	 * @return the workbook
	 * @throws Exception the exception
	 */
	private Workbook createSheet(ReportExcel report, Workbook workbook) throws Exception {

		List<BaseSheet> listSheet = report.getListBaseSheet();
		int indiceNomeSheet = 0;

		this.mapCellStyle = new HashMap<>();
		this.mapCellHeaderStyle = new HashMap<>();
		this.mapFieldColumn = new HashMap<>();
		for (BaseSheet sheet : listSheet) {
			Sheet worksheet = null;
			if (sheet.getNameSheet() != null) {
				if (workbook.getSheet(sheet.getNameSheet()) == null && sheet.getNameSheet().length() <= 30)
					worksheet = workbook.createSheet(sheet.getNameSheet().replace("/", ""));
				else
					worksheet = workbook.createSheet((indiceNomeSheet++) + sheet.getNameSheet().replace("/", ""));
			} else
				worksheet = workbook.createSheet("Don't defined" + (indiceNomeSheet++));

			Footer footer = worksheet.getFooter();
			footer.setRight("Page " + HeaderFooter.page() + " of " + HeaderFooter.numPages());
			if (sheet instanceof MergeSheet) {
				this.generateMergeSheet(workbook, worksheet, (MergeSheet) sheet);
			} else if (sheet instanceof SheetSummary) {
				this.generateSheetSommario(workbook, worksheet, (SheetSummary) sheet, 0);
			} else if (sheet instanceof SheetData) {
				this.generateSheetData(workbook, worksheet, (SheetData<? extends RowSheet>) sheet, 0, false);
			}
			workbook.getCreationHelper().createFormulaEvaluator().evaluateAll();
			worksheet.setForceFormulaRecalculation(true);
		}

		return workbook;
	}

	/**
	 * Generate merge sheet.
	 *
	 * @param workbook   the workbook
	 * @param worksheet  the worksheet
	 * @param mergeSheet the merge sheet
	 * @throws Exception the exception
	 */
	private void generateMergeSheet(Workbook workbook, Sheet worksheet, MergeSheet mergeSheet) throws Exception {
		Integer indexRow = new Integer(0);
		for (SheetComponent sheetComponent : mergeSheet.getListSheet()) {
			sheetComponent.setNameSheet(worksheet.getSheetName());
			if (sheetComponent instanceof SheetSummary)
				indexRow = this.generateSheetSommario(workbook, worksheet, (SheetSummary) sheetComponent, indexRow);
			else if (sheetComponent instanceof SheetData)
				indexRow = this.generateSheetData(workbook, worksheet, (SheetData<? extends RowSheet>) sheetComponent, indexRow, true);
			indexRow += 2;
		}
	}

	/**
	 * Generate sheet sommario.
	 *
	 * @param workbook     the workbook
	 * @param worksheet    the worksheet
	 * @param sheetSummary the sheet summary
	 * @param indexRow     the index row
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer generateSheetSommario(Workbook workbook, Sheet worksheet, SheetSummary sheetSummary, Integer indexRow) throws Exception {
		Class<? extends SheetSummary> classSheet = sheetSummary.getClass();
		ExcelSummary titleSummary = classSheet.getAnnotation(ExcelSummary.class);

		if (titleSummary != null && StringUtils.isNotBlank(titleSummary.title())) {
			CellStyle cellStyleHeader = getCellStyleHeader(workbook, worksheet, sheetSummary);
			Row rowHeader = worksheet.createRow(indexRow);
			Cell cellHeader = rowHeader.createCell(0);
			cellHeader.setCellStyle(cellStyleHeader);
			cellHeader.setCellValue(titleSummary.title());
			if (StringUtils.isNotBlank(titleSummary.comment()))
				addComment(workbook, worksheet, rowHeader, cellHeader, titleSummary.comment());
			cellHeader = rowHeader.createCell(1);
			cellHeader.setCellStyle(cellStyleHeader);
			worksheet.addMergedRegion(new CellRangeAddress(indexRow, indexRow, 0, 1));
			indexRow++;
		}
		List<SheetHeader> listSheetHeader = getListSheetHeader(classSheet, sheetSummary);
		Row row = null;

		for (SheetHeader sheetHeader : listSheetHeader) {
			row = worksheet.createRow(indexRow);
			setCellSommario(workbook, worksheet, sheetSummary, sheetHeader, row);
			indexRow++;
		}
		return indexRow;

	}

	/**
	 * Generate sheet data.
	 *
	 * @param workbook     the workbook
	 * @param worksheet    the worksheet
	 * @param sheetData    the sheet data
	 * @param indexRow     the index row
	 * @param isMergeSheet the is merge sheet
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer generateSheetData(Workbook workbook, Sheet worksheet, SheetData<? extends RowSheet> sheetData, Integer indexRow, boolean isMergeSheet) throws Exception {
		// this.mapFieldColumn = sheetData.getMapFieldColumn();
		indexRow = writeLabel(workbook, worksheet, sheetData, indexRow);
		int startRowSheet = indexRow + 1;
		Row rowHeader = worksheet.createRow(indexRow);
		List<SheetHeader> listSheetHeader = generateHeaderSheetData(workbook, worksheet, rowHeader, sheetData, indexRow);
		indexRow++;
		ExcelSheetLayout excelSheetLayout = ExcelUtils.getAnnotation(sheetData.getClass(), ExcelSheetLayout.class);
		boolean start = true;
		// CellStyle cellStyle = null;
		Map<Integer, MergeCell> mapMergeRow = new HashMap<>();
		RowSheet lastRowSheet = null;

		Map<String, String> mapChart = new LinkedHashMap<>();

		String startKey = null;
		String endKey = null;

		Integer calRowStart = null;
		Integer calRowEnd = null;

		if (sheetData instanceof SheetFunctionTotal) {
			SheetFunctionTotal<? extends RowSheet> functionSheetData = (SheetFunctionTotal<? extends RowSheet>) sheetData;
			calRowStart = functionSheetData.getCalRowStart();
			calRowEnd = functionSheetData.getCalRowEnd();
		}

		// int i=0;
		if (!isMergeSheet && sheetData.getClass().isAnnotationPresent(ExcelFreezePane.class)) {
			ExcelFreezePane excelFreezePane = sheetData.getClass().getAnnotation(ExcelFreezePane.class);
			worksheet.createFreezePane(excelFreezePane.columnFreez(), excelFreezePane.rowFreez());
		}
		Row row = null;
		int maxColumn = listSheetHeader.size() + excelSheetLayout.startColumn();

		for (RowSheet rowSheet : sheetData.getListRowSheet()) {
			row = worksheet.createRow(indexRow);
			Map<String, Object> mapValue = new HashMap<>();
			CellStyle cellStyle = null;
			for (int numColumn = excelSheetLayout.startColumn(); numColumn < maxColumn; numColumn++) {
				int indexHeader = numColumn - excelSheetLayout.startColumn();
				Cell cell = row.createCell(numColumn);
				SheetHeader sheetHeader = listSheetHeader.get(indexHeader);
				Field field = listSheetHeader.get(indexHeader).getField();
				Object value = null;
				if (sheetHeader.getField() != null) {
					value = new PropertyDescriptor(field.getName(), rowSheet.getClass()).getReadMethod().invoke(rowSheet);
					mapValue.put(field.getName(), value);
				} else if (StringUtils.isNotBlank(sheetHeader.getKeyMap())) {
					DynamicRowSheet dynamicRowSheet = (DynamicRowSheet) rowSheet;
					value = dynamicRowSheet.getMapValue().get(sheetHeader.getKeyMap());
					mapValue.put(sheetHeader.getKeyMap(), value);
				}
				sheetHeader.setValue(value);
				if (start) {
					ExcelCellLayout excelCellLayout = sheetHeader.getExcelCellLayout();
					ExcelDate excelDate = null;
					LayoutCell layoutCell = ExcelUtils.reflectionAnnotation(new LayoutCell(), excelCellLayout);
					if (field != null && (Date.class.isAssignableFrom(field.getType()) || Calendar.class.isAssignableFrom(field.getType()))) {
						excelDate = sheetHeader.getExcelDate();
						layoutCell = ExcelUtils.reflectionAnnotation(layoutCell, excelDate);
					}
					if (!this.mapCellStyle.containsKey(layoutCell))
						this.mapCellStyle.put(layoutCell, createCellStyle(workbook, excelCellLayout, excelDate));
					cellStyle = this.mapCellStyle.get(layoutCell);

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
							if (StringUtils.isBlank(sheetHeader.getFunction()))
								mergeRow.getSheetHeader().setValue(value);
							mergeRow.setCellFrom(cell);
							mergeRow.setCellStyleFrom(cellStyle);
							if (sheetData instanceof SheetFunctionTotal) {
								mergeRow.setCalRowStart(calRowStart);
								mergeRow.setCalRowEnd(calRowEnd);
							}

							mapMergeRow.put(numColumn, mergeRow);
						} else
							super.setCellValueExcel(workbook, worksheet, cell, cellStyle, sheetHeader, indexRow, calRowStart, calRowEnd);
						repeat = false;
					} else {
						if (numColumn > excelSheetLayout.startColumn() && StringUtils.isBlank(sheetHeader.getExcelMergeRow().referenceField()))
							throw new Exception("Only first column can have the propetie \"referenceColumn\" is blank!!!");
						if (field != null)
							valueBefore = new PropertyDescriptor(field.getName(), lastRowSheet.getClass()).getReadMethod().invoke(lastRowSheet);
						if (StringUtils.isBlank(sheetHeader.getExcelMergeRow().referenceField())) {
							if (!(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
								super.mergeRowAndRemoveMap(workbook, worksheet, indexRow, mapMergeRow, numColumn);
							else
								repeat = setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader);

						} else if (StringUtils.isNotBlank(sheetHeader.getExcelMergeRow().referenceField())) {
							String referenceField = sheetHeader.getExcelMergeRow().referenceField();
							Object valueRefColumn = new PropertyDescriptor(referenceField, rowSheet.getClass()).getReadMethod().invoke(rowSheet);
							Object valueRefColumnBefore = new PropertyDescriptor(referenceField, lastRowSheet.getClass()).getReadMethod().invoke(lastRowSheet);
							if ((valueRefColumn != null && valueRefColumnBefore != null && !valueRefColumn.equals(valueRefColumnBefore)) || !(sheetHeader.getValue() == valueBefore || sheetHeader.getValue().equals(valueBefore)))
								mergeRowAndRemoveMap(workbook, worksheet, indexRow, mapMergeRow, numColumn);
							else {
								repeat = setCellValueWillMerged(workbook, cellStyle, cell, sheetHeader);
							}
						}

					}
				} while (repeat);
			}
			lastRowSheet = rowSheet;
			if (sheetData.getClass().isAnnotationPresent(ExcelCharts.class) || (sheetData instanceof DynamicChart && CollectionUtils.isNotEmpty(((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart()))) {
				List<ExcelChart> listExcelChart = getExcelChart(sheetData);
				for (ExcelChart excelChart : listExcelChart) {
					startKey = ExcelUtils.calcoloCoordinateFunction(indexRow + 1, this.mapFieldColumn.get(ExcelUtils.getKeyColumn(worksheet, excelChart.startKeyChart())).getColumnNum());
					endKey = ExcelUtils.calcoloCoordinateFunction(indexRow + 1, this.mapFieldColumn.get(ExcelUtils.getKeyColumn(worksheet, excelChart.endKeyChart())).getColumnNum());
					String keyChart = mapValue.get(excelChart.fieldTitle()).toString();
					mapChart.put(keyChart, startKey + ":" + endKey);
				}

			}

			start = false;
			indexRow++;
			// i++;
		}
		for (Integer numColumn : mapMergeRow.keySet())
			super.mergeRow(workbook, worksheet, indexRow, mapMergeRow, numColumn);

		if (excelSheetLayout.notMerge() && excelSheetLayout.sortAndFilter()) {
			String startCell = ExcelUtils.calcoloCoordinateFunction(startRowSheet, excelSheetLayout.startColumn());
			String endCell = ExcelUtils.calcoloCoordinateFunction(indexRow, listSheetHeader.size() + excelSheetLayout.startColumn() - 1);
			logger.debug(sheetData.getClass().getSimpleName() + "- " + startCell + ":" + endCell);
			worksheet.setAutoFilter(CellRangeAddress.valueOf(startCell + ":" + endCell));
		}

		if (sheetData instanceof FunctionsTotal) {
			FunctionsTotal<SheetFunctionTotal<? extends RowSheet>> functionsTotal = (FunctionsTotal<SheetFunctionTotal<? extends RowSheet>>) sheetData;
			if (functionsTotal.getSheetFunctionsTotal() != null) {
				SheetFunctionTotal<? extends RowSheet> functionSheetData = functionsTotal.getSheetFunctionsTotal();
				// functionSheetData.setMapFieldColumn(this.mapFieldColumn);
				functionSheetData.setCalRowStart(startRowSheet);
				functionSheetData.setCalRowEnd(indexRow - 1);
				indexRow += 2;
				indexRow = this.generateSheetData(workbook, worksheet, functionSheetData, indexRow, isMergeSheet);
			}
		}

		if (!isMergeSheet && worksheet instanceof XSSFSheet
				&& (sheetData.getClass().isAnnotationPresent(ExcelCharts.class) || (sheetData instanceof DynamicChart && CollectionUtils.isNotEmpty(((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart())))) {
			List<ExcelChart> listExcelChart = getExcelChart(sheetData);
			for (ExcelChart excelChart : listExcelChart) {
				startKey = ExcelUtils.calcoloCoordinateFunction(startRowSheet, this.mapFieldColumn.get(ExcelUtils.getKeyColumn(worksheet, excelChart.startKeyChart())).getColumnNum());
				endKey = ExcelUtils.calcoloCoordinateFunction(startRowSheet, this.mapFieldColumn.get(ExcelUtils.getKeyColumn(worksheet, excelChart.endKeyChart())).getColumnNum());
				String xAxis = startKey + ":" + endKey;
				indexRow += 2;
				indexRow = generateChart((XSSFSheet) worksheet, mapChart, excelChart, indexRow, xAxis);
			}

		}

		return indexRow;

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
	 * @param worksheet  the worksheet
	 * @param mapChart   the map chart
	 * @param excelChart the excel chart
	 * @param indexRow   the index row
	 * @param xAxis      the x axis
	 * @return the integer
	 */
	private Integer generateChart(XSSFSheet worksheet, Map<String, String> mapChart, ExcelChart excelChart, Integer indexRow, String xAxis) {
		// ExcelChart
		// excelChart=sheetData.getClass().getAnnotation(ExcelChart.class)
		XSSFDrawing drawing = worksheet.createDrawingPatriarch();
		Integer startChart = indexRow;
		indexRow += excelChart.sizeRow();
		logger.debug("Start Chart: " + startChart);
		XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, startChart, excelChart.sizeColumn(), indexRow);
		
		XSSFChart chart = drawing.createChart(anchor);

		XDDFChartLegend legend = chart.getOrAddLegend();
		legend.setPosition(excelChart.legendPosition());
		XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
		XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		logger.debug("xAxis: " + xAxis);
		XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(worksheet, CellRangeAddress.valueOf(xAxis));
		XDDFChartData lineChartData = chart.createData(excelChart.chartTypes(), bottomAxis, leftAxis);

		XDDFChartData.Series series = null;

		for (String keyChart : mapChart.keySet()) {
			String seriesChart = mapChart.get(keyChart);
			logger.debug("seriesChart: " + seriesChart);
			series = lineChartData.addSeries(xs, XDDFDataSourcesFactory.fromNumericCellRange(worksheet, CellRangeAddress.valueOf(seriesChart)));
			series.setTitle(keyChart, null);
			series.setShowLeaderLines(true);
		}
		
		lineChartData.setVaryColors(true);
		
		chart.plot(lineChartData);

		return indexRow;

	}

	/**
	 * Write label.
	 *
	 * @param workbook  the workbook
	 * @param worksheet the worksheet
	 * @param sheet     the sheet
	 * @param indexRow  the index row
	 * @return the integer
	 * @throws Exception the exception
	 */
	private Integer writeLabel(Workbook workbook, Sheet worksheet, BaseSheet sheet, Integer indexRow) throws Exception {
		List<Field> listField = new ArrayList<>();
		Class<? extends BaseSheet> classSheet = sheet.getClass();
		if (classSheet.getDeclaredFields().length > 0)
			listField.addAll(Arrays.asList(classSheet.getDeclaredFields()));
		if (classSheet.getSuperclass().getDeclaredFields().length > 0)
			listField.addAll(Arrays.asList(classSheet.getSuperclass().getDeclaredFields()));
		for (Field field : listField) {
			if (field.isAnnotationPresent(ExcelLabel.class)) {
				Row row = worksheet.createRow(indexRow);
				ExcelLabel excelLabel = field.getAnnotation(ExcelLabel.class);
				Object value = new PropertyDescriptor(field.getName(), classSheet).getReadMethod().invoke(sheet);
				if (!(value instanceof String))
					throw new Exception(field.getName() + " field type is not supported: required String");
				CellStyle cellStype = createCellStyle(workbook, excelLabel.labelLayout(), null);
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
				runMergeCell(workbook, worksheet, mergeColumn);
				indexRow += 2;
				break;
			}
		}

		return indexRow;
	}

}