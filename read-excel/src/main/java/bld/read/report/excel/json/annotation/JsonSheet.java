/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.read.report.excel.json.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import bld.read.report.excel.domain.RowSheetRead;
import bld.read.report.excel.domain.SheetRead;

/**
 * The Interface JsonSheet.
 */
@Retention(RUNTIME)
public @interface JsonSheet {

	/**
	 * Name.
	 *
	 * @return the string
	 */
	public String name();
	
	/**
	 * Sheet class.
	 *
	 * @return the class sheet read
	 */
	public Class<? extends SheetRead<? extends RowSheetRead>> sheetClass();
	
}
