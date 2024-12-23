package com.bld.generator.report.excel.sheet_mapping;

import com.bld.generator.report.excel.SheetData;
import com.bld.generator.report.excel.annotation.ExcelFreezePane;
import com.bld.generator.report.excel.annotation.ExcelHeaderCellLayout;
import com.bld.generator.report.excel.annotation.ExcelHeaderLayout;
import com.bld.generator.report.excel.annotation.ExcelLocked;
import com.bld.generator.report.excel.annotation.ExcelMarginSheet;
import com.bld.generator.report.excel.annotation.ExcelRgbColor;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

@ExcelSheetLayout(hidden = true,locked = @ExcelLocked)
@ExcelHeaderLayout(excelHeaderCellLayout = @ExcelHeaderCellLayout(locked = true,
rgbFont = @ExcelRgbColor(red = (byte) 0, green = (byte) 0, blue = (byte) 0),rgbForeground = @ExcelRgbColor(red = (byte) 255, green = (byte) 255, blue = (byte) 255)))
@ExcelMarginSheet(bottom = 1.5, left = 1.5, right = 1.5, top = 1.5)
@ExcelFreezePane(columnFreez = 1)
public class SheetMappingSheet extends SheetData<SheetMappingRow> {
	
	
	public static final String SHEET_NAME = "Sheet Mapping";

	public SheetMappingSheet() {
		super(SHEET_NAME);
		super.setEnableInfoSheet(false);
	}

}
