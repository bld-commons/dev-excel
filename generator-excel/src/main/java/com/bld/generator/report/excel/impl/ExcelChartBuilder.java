/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ExcelChartBuilder.java
*/
package com.bld.generator.report.excel.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.DynamicChart;
import com.bld.generator.report.excel.DynamicRowSheet;
import com.bld.generator.report.excel.RowSheet;
import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelBarChartData;
import com.bld.generator.report.excel.annotation.ExcelChart;
import com.bld.generator.report.excel.annotation.ExcelChartDataLabel;
import com.bld.generator.report.excel.annotation.ExcelCharts;
import com.bld.generator.report.excel.data.InfoChart;
import com.bld.generator.report.excel.data.InfoColumn;
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;

/**
 * The Class ExcelChartBuilder.
 */
@Component
public class ExcelChartBuilder {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ExcelChartBuilder.class);

	/** The Constant END. */
	static final String END = "[end]";

	/** The Constant START. */
	static final String START = "[start]";

	/** The Constant LIST_CHART_TYPES. */
	private final static List<ChartTypes> LIST_CHART_TYPES = listChartTypes();

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
	public void configMapChart(Map<String, Map<String, InfoChart>> mapChart, com.bld.generator.report.excel.annotation.ExcelChart excelChart, com.bld.generator.report.excel.annotation.ExcelChartCategory excelChartCategory, String functionChart, String title, Integer firstRow, Integer lastRow) {
		String key = title + excelChartCategory.function();
		if (!mapChart.containsKey(excelChart.id()))
			mapChart.put(excelChart.id(), new java.util.HashMap<>());
		if (!mapChart.get(excelChart.id()).containsKey(key)) {
			mapChart.get(excelChart.id()).put(key, new InfoChart(title, functionChart, firstRow.intValue()));
		} else {
			mapChart.get(excelChart.id()).get(key).setLastRow(lastRow.intValue());
		}
	}

	/**
	 * Sets the info column by map charts.
	 *
	 * @param function       the function
	 * @param sheet          the sheet
	 * @param infoChart      the info chart
	 * @param mapFieldColumn the map field column
	 * @param mapSheet       the map sheet
	 * @return the string
	 * @throws Exception the exception
	 */
	public String setInfoColumnByMapCharts(String function, Sheet sheet, InfoChart infoChart, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet) throws Exception {
		Pattern p = Pattern.compile("\\$\\{.*?(RowStart|\\[start\\])}");
		Matcher m = p.matcher(function);
		if (m.find()) {
			String fieldName = ExcelUtils.getKeyColumn(sheet, m.group().replace(RowStartEndType.ROW_START.getValue(), "").replace("${", "").replace(START, "").replace("}", ""));
			if (infoChart != null) {
				mapFieldColumn.get(fieldName).setFirstRow(infoChart.getFirstRow());
				mapFieldColumn.get(fieldName).setLastRow(infoChart.getLastRow());
			}
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_START, true, true, mapFieldColumn, mapSheet);
			function = ExcelBuildFunctionUtility.buildFunction(sheet, null, function, RowStartEndType.ROW_END, true, true, mapFieldColumn, mapSheet);
		}
		logger.debug("Function: " + function);
		return function;
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
	 * @param mapFieldColumn     the map field column
	 * @param mapSheet           the map sheet
	 * @return the integer
	 * @throws Exception the exception
	 */
	public Integer generateChart(XSSFWorkbook workbook, XSSFSheet sheet, ExcelChart excelChart, Integer indexRow, String xAxis, Map<String, Map<String, InfoChart>> mapAllChart, boolean isVerticalAndMerge, SheetData<? extends RowSheet> sheetData, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet)
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
					seriesChart = setInfoColumnByMapCharts(infoChart.getFunction(), sheet, infoChart, mapFieldColumn, mapSheet);

				} else {
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
	public Integer generateChart(XSSFWorkbook workbook, XSSFSheet sheet, String title, ExcelChart excelChart, Integer indexRow, String xAxis, String seriesChart, SheetData<? extends RowSheet> sheetData) {
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
		logger.debug("-----------------xAxis: " + xAxis);
		AreaReference areaReference = new AreaReference(xAxis, excelChart.spreadsheetVersion());
		XDDFDataSource<String> xs = XDDFDataSourcesFactory.fromStringCellRange(StringUtils.isNotEmpty(areaReference.getFirstCell().getSheetName()) ? workbook.getSheet(areaReference.getFirstCell().getSheetName()) : sheet,
				CellRangeAddress.valueOf(xAxis));
		XDDFChartData chartData = chart.createData(excelChart.chartTypes(), categoryAxis, valueAxis);
		XDDFChartData.Series series = null;
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
	 * Chart label data.
	 *
	 * @param chart      the chart
	 * @param excelChart the excel chart
	 */
	public void chartLabelData(XSSFChart chart, ExcelChart excelChart) {
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

	/**
	 * Gets the excel chart.
	 *
	 * @param sheetData the sheet data
	 * @return the excel chart
	 */
	@SuppressWarnings("unchecked")
	public List<ExcelChart> getExcelChart(SheetData<? extends RowSheet> sheetData) {
		List<ExcelChart> listExcelChart = null;
		if (sheetData.getClass().isAnnotationPresent(ExcelCharts.class))
			listExcelChart = Arrays.asList(sheetData.getClass().getAnnotation(ExcelCharts.class).excelCharts());
		else
			listExcelChart = ((DynamicChart<? extends DynamicRowSheet>) sheetData).getListExcelChart();
		return listExcelChart;
	}

}
