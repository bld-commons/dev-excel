package com.bld.generator.report.excel.impl;

import java.util.Map;

import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FontFormatting;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.annotation.ExcelCellLayout;
import com.bld.generator.report.excel.annotation.ExcelConditionCellLayout;
import com.bld.generator.report.excel.annotation.ExcelConditionCellLayouts;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;
import com.bld.generator.report.excel.data.InfoColumn;
import com.bld.generator.report.excel.utility.ExcelBuildFunctionUtility;
import com.bld.generator.report.excel.utility.ExcelLayoutUtility;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.bld.common.spreadsheet.exception.ExcelGeneratorException;

@Component
public class ConditionalCellLayout {

	public void createConditionalCellLayout(ExcelConditionCellLayouts excelConditionCellLayouts, Sheet sheet, Map<String, InfoColumn> mapFieldColumn, Map<String, BaseSheet> mapSheet, Integer indexRow) throws Exception {

		for (ExcelConditionCellLayout excelConditionCellLayout : excelConditionCellLayouts.value()) {
			ExcelCellLayout excelCellLayout = excelConditionCellLayout.excelCellLayout();
			
			String condition = ExcelBuildFunctionUtility.buildFunction(sheet, null, excelConditionCellLayout.condition(), RowStartEndType.ROW_START, excelConditionCellLayout.alias(), mapFieldColumn, mapSheet);
			for (String column : excelConditionCellLayout.columns()) {
				SheetConditionalFormatting shettConditionFormatting = sheet.getSheetConditionalFormatting();
				ConditionalFormattingRule rule = shettConditionFormatting.createConditionalFormattingRule(condition);

				FontFormatting fontFormatting = rule.createFontFormatting();
				fontFormatting.setFontStyle(excelCellLayout.font().italic(), excelCellLayout.font().bold());
				fontFormatting.setUnderlineType(excelCellLayout.font().underline().getValue());
				PatternFormatting patternFmt = rule.createPatternFormatting();
				if (excelCellLayout.rgbForeground().length > 1)
					throw new ExcelGeneratorException("Only one record for rgbForeground");
				ExcelRgbColor excelRgbColor = excelCellLayout.rgbForeground()[0];
				patternFmt.setFillBackgroundColor(ExcelLayoutUtility.color(excelRgbColor.red(), excelRgbColor.green(), excelRgbColor.blue()));
				patternFmt.setFillPattern(excelCellLayout.fillPatternType().getCode());
				BorderFormatting borderFormatting = rule.createBorderFormatting();
				borderFormatting.setBorderBottom(excelCellLayout.border().bottom());
				borderFormatting.setBorderLeft(excelCellLayout.border().left());
				borderFormatting.setBorderRight(excelCellLayout.border().right());
				borderFormatting.setBorderTop(excelCellLayout.border().top());
				String field = column;
				if (!field.contains("."))
					field = sheet.getSheetName() + "." + field;
				InfoColumn infoColumn = mapFieldColumn.get(field);
				CellRangeAddress[] cellRangeAddress = new CellRangeAddress[] { new CellRangeAddress(infoColumn.getFirstRow(), indexRow, infoColumn.getColumnNum(), infoColumn.getColumnNum()) };
				shettConditionFormatting.addConditionalFormatting(cellRangeAddress, rule);

			}

		}
	}

}
