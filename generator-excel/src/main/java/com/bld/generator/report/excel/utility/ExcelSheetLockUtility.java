/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.utility.ExcelSheetLockUtility.java
*/
package com.bld.generator.report.excel.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.bld.common.spreadsheet.utils.ValueProps;
import com.bld.generator.report.excel.BaseSheet;
import com.bld.generator.report.excel.LockedSheet;
import com.bld.generator.report.excel.annotation.ExcelLocked;
import com.bld.generator.report.excel.annotation.ExcelSheetLayout;

/**
 * The Class ExcelSheetLockUtility.
 */
public final class ExcelSheetLockUtility {


	/**
	 * Apply sheet lock.
	 *
	 * @param workbook         the workbook
	 * @param sheet            the sheet
	 * @param baseSheet        the base sheet
	 * @param excelSheetLayout the excel sheet layout
	 * @param valueProps       the value props
	 */
	public static void applySheetLock(Workbook workbook, Sheet sheet, BaseSheet baseSheet, ExcelSheetLayout excelSheetLayout, ValueProps valueProps) {
		boolean isLocked = (baseSheet instanceof LockedSheet) || baseSheet.getClass().isAnnotationPresent(ExcelLocked.class);
		if (isLocked || excelSheetLayout.hidden()) {
			if (isLocked) {
				if (sheet instanceof XSSFSheet)
					((XSSFSheet) sheet).lockAutoFilter(false);
				String password = resolvePassword(baseSheet, valueProps);
				sheet.protectSheet(StringUtils.isNotBlank(password) ? password : "");
			}
			workbook.setSheetHidden(workbook.getSheetIndex(sheet), excelSheetLayout.hidden());
		}
	}

	/**
	 * Resolve password.
	 *
	 * @param baseSheet  the base sheet
	 * @param valueProps the value props
	 * @return the string
	 */
	private static String resolvePassword(BaseSheet baseSheet, ValueProps valueProps) {
		if (baseSheet instanceof LockedSheet sheet)
			return sheet.password();
		ExcelLocked excelLocked = baseSheet.getClass().getAnnotation(ExcelLocked.class);
		if (excelLocked != null)
			return valueProps.valueProps(excelLocked.value());
		return null;
	}

}
