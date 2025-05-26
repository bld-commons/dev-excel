package com.bld.generator.report.excel.utility;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.PageMargin;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

import com.bld.common.spreadsheet.excel.annotation.ExcelDate;
import com.bld.common.spreadsheet.utils.ExcelUtils;
import com.bld.common.spreadsheet.utils.SpreadsheetUtils;
import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.excel.SheetComponent;
import com.bld.generator.report.excel.annotation.ExcelBorder;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelFont;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelNumberFormat;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;
import com.bld.generator.report.excel.annotation.ExcelSuperHeaderCell;
import com.bld.generator.report.excel.data.LayoutCell;
import com.bld.generator.report.excel.data.SheetHeader;

public class ExcelLayoutUtility {

	/** The Constant FLAT_ANGLE. */
	private static final int FLAT_ANGLE = 180;
	
	/**
	 * Gets the color.
	 *
	 * @param rgbColor the rgb color
	 * @return the color
	 */
	private static XSSFColor getColor(byte... rgbColor) {
		XSSFColor color = new XSSFColor();
		color.setRGB(rgbColor);
		return color;
	}
	
	public static XSSFColor color(byte red,byte green, byte blue) {
		return getColor(red,green,blue);
	}
	
	
	/**
	 * Creates the cell style.
	 *
	 * @param workbook the workbook
	 * @param layout   the layout
	 * @return the cell style
	 */
	public static CellStyle createCellStyle(Workbook workbook, ExcelHeaderCellLayout layout) {
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
			XSSFColor colorForeground = ExcelLayoutUtility.color(rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			XSSFColor colorFont = ExcelLayoutUtility.color(rgbFont.red(), rgbFont.green(), rgbFont.blue());
			((XSSFCellStyle) cellStyleHeader).setFillForegroundColor(colorForeground);
			((XSSFFont) font).setColor(colorFont);
		}
		cellStyleHeader.setRotation((short) (layout.rotation() % FLAT_ANGLE));
		cellStyleHeader.setFont(font);
		cellStyleHeader.setFillPattern(layout.fillPatternType());
		cellStyleHeader.setAlignment(layout.horizontalAlignment());
		cellStyleHeader.setVerticalAlignment(layout.verticalAlignment());
		cellStyleHeader.setWrapText(layout.wrap());
		cellStyleHeader.setLocked(layout.locked());
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
	public static CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout, Integer indexRow,ValueProps valueProps) throws Exception {
		return createCellStyle(workbook, layout, null, indexRow,valueProps);
	}

	/**
	 * Creates the cell style.
	 *
	 * @param workbook    the workbook
	 * @param layout      the layout
	 * @param sheetHeader the sheet header
	 * @param indexRow    the index row
	 * @return the cell style
	 * @throws Exception the exception
	 */
	public static CellStyle createCellStyle(Workbook workbook, ExcelCellLayout layout, SheetHeader sheetHeader, Integer indexRow,ValueProps valueProps) throws Exception {
		ExcelDate excelDate = null;
		ExcelNumberFormat excelNumberFormat = null;
		if (sheetHeader != null) {
			excelDate = sheetHeader.getExcelDate();
			excelNumberFormat = sheetHeader.getExcelNumberFormat();
		}

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
			XSSFColor colorForeground = ExcelLayoutUtility.color(rgbForeground.red(), rgbForeground.green(), rgbForeground.blue());
			XSSFColor colorFont = ExcelLayoutUtility.color(rgbFont.red(), rgbFont.green(), rgbFont.blue());
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
			cellStyle = dateCellStyle(workbook, cellStyle, excelDate.value().getValue(),valueProps);
		else if (excelNumberFormat != null && StringUtils.isNotBlank(excelNumberFormat.value()))
			cellStyle = dateCellStyle(workbook, cellStyle, excelNumberFormat.value(),valueProps);
		else if (layout.precision() > -1 || layout.percent()) {
			String format = "0";
			if (layout.precision() > 0)
				format += ".";
			for (int i = 0; i < layout.precision(); i++)
				format += "0";
			if (layout.percent())
				format += "%";
			cellStyle = dateCellStyle(workbook, cellStyle, format,valueProps);
		} else if (sheetHeader != null && sheetHeader.getField() != null && String.class.isAssignableFrom(sheetHeader.getField().getType())) {
			DataFormat fmt = workbook.createDataFormat();
			cellStyle.setDataFormat(fmt.getFormat("text"));
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
	private static Font getFont(Workbook workbook, ExcelFont excelFont) {
		Font font = workbook.createFont();
		font.setBold(excelFont.bold());
		font.setFontName(excelFont.font().getValue());
		font.setItalic(excelFont.italic());
		font.setUnderline(excelFont.underline().getValue());
		font.setFontHeight((short) (excelFont.size() * 20));
		return font;
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
	public static CellStyle dateCellStyle(Workbook workbook, CellStyle cellStyle, String format,ValueProps valueProps) throws Exception {
		CreationHelper helper = workbook.getCreationHelper();
		cellStyle.setDataFormat(helper.createDataFormat().getFormat(valueProps.valueProps(format)));
		return cellStyle;
	}

	/**
	 * Sets the cell style excel.
	 *
	 * @param cellStyle  the cell style
	 * @param cell       the cell
	 * @param layoutCell the layout cell
	 */
	public static void setCellStyleExcel(CellStyle cellStyle, Cell cell, LayoutCell layoutCell,Map<LayoutCell, CellStyle> mapCellStyle) {
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
	public static CellStyle getCellStyleHeader(Workbook workbook, Sheet sheet, SheetComponent sheetComponent, Row rowHeader,Map<LayoutCell, CellStyle> mapCellHeaderStyle) throws Exception {
		ExcelHeaderLayout layoutHeader = SpreadsheetUtils.getAnnotation(sheetComponent.getClass(), ExcelHeaderLayout.class);
		ExcelMarginSheet excelMarginSheet = SpreadsheetUtils.getAnnotation(sheetComponent.getClass(), ExcelMarginSheet.class);
		ExcelSheetLayout layoutSheet = SpreadsheetUtils.getAnnotation(sheetComponent.getClass(), ExcelSheetLayout.class);
		sheet.setMargin(PageMargin.LEFT, excelMarginSheet.left());
		sheet.setMargin(PageMargin.RIGHT, excelMarginSheet.right());
		sheet.setMargin(PageMargin.TOP, excelMarginSheet.top());
		sheet.setMargin(PageMargin.BOTTOM, excelMarginSheet.bottom());
		if (layoutSheet.scale() != (short) 100)
			sheet.getPrintSetup().setScale(layoutSheet.scale());
		sheet.getPrintSetup().setLandscape(layoutSheet.landscape());
		rowHeader.setHeight(ExcelUtils.rowHeight(layoutHeader.rowHeight()));

//		worksheet.setDefaultColumnWidth(5 * layoutHeader.cmWidthCell());
//		worksheet.setDefaultRowHeight((short)(layoutHeader.cmHeightCell() * 568));
		if (layoutSheet.order() > -1)
			workbook.setSheetOrder(sheet.getSheetName(), layoutSheet.order());
		return manageCellStyleHeader(workbook, layoutHeader,mapCellHeaderStyle);
	}

	/**
	 * Manage cell style header.
	 *
	 * @param workbook     the workbook
	 * @param layoutHeader the layout header
	 * @return the cell style
	 */
	private static CellStyle manageCellStyleHeader(Workbook workbook, ExcelHeaderLayout layoutHeader,Map<LayoutCell, CellStyle> mapCellHeaderStyle) {
		return manageCellStyleHeader(workbook, layoutHeader.excelHeaderCellLayout(),mapCellHeaderStyle);
	}
	
	/**
	 * Manage cell style header.
	 *
	 * @param workbook              the workbook
	 * @param excelHeaderCellLayout the excel header cell layout
	 * @return the cell style
	 */
	public static CellStyle manageCellStyleHeader(Workbook workbook, ExcelHeaderCellLayout excelHeaderCellLayout,Map<LayoutCell, CellStyle> mapCellHeaderStyle) {
		LayoutCell layoutCellHeader = SpreadsheetUtils.reflectionAnnotation(new LayoutCell(), excelHeaderCellLayout);
		if (!mapCellHeaderStyle.containsKey(layoutCellHeader))
			mapCellHeaderStyle.put(layoutCellHeader, createCellStyle(workbook, excelHeaderCellLayout));
		CellStyle cellStyleHeader = mapCellHeaderStyle.get(layoutCellHeader);
		return cellStyleHeader;
	}

	/**
	 * Manage cell style header.
	 *
	 * @param workbook     the workbook
	 * @param layoutHeader the layout header
	 * @return the cell style
	 */
	public static CellStyle manageCellStyleHeader(Workbook workbook, ExcelSuperHeaderCell layoutHeader,Map<LayoutCell, CellStyle> mapCellHeaderStyle) {
		return manageCellStyleHeader(workbook, layoutHeader.excelHeaderCellLayout(),mapCellHeaderStyle);
	}
	
	/**
	 * Gets the border.
	 *
	 * @param cellStyle   the cell style
	 * @param excelBorder the excel border
	 * @return the border
	 */
	private static CellStyle getBorder(CellStyle cellStyle, ExcelBorder excelBorder) {
		cellStyle.setBorderLeft(excelBorder.left());
		cellStyle.setBorderRight(excelBorder.right());
		cellStyle.setBorderTop(excelBorder.top());
		cellStyle.setBorderBottom(excelBorder.bottom());
		return cellStyle;

	}
	
}
