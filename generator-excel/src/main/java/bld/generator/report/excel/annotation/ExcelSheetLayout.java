/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelSheetLayout.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;




/**
 * The Interface ExcelSheetLayout.<br>
 * ExcelSheetLayout is used to configure the sheet layout through:
 * <ul>
 * <li>Landscape - to enable/disable landscape</li>
 * <li>NotMerge - to enable/disable merged cells</li>
 * <li>SortAndFilter - to enable/disable sort and filter</li>
 * <li>StartColumn - to set the column start</li>
 * <li>StartRow - to set the row start</li>
 * <li>Order - to set index on sheets list</li>
 * <li>AreaBorder - to define the border areas</li>
 * <li>showHeader - to enable/disable the writing of the header</li>
 * </ul>
 * 
 * It is used on {@link bld.generator.report.excel.SheetData} and {@link bld.generator.report.excel.SheetSummary} classes.
 * 
 */
@Retention(RUNTIME)
@Target({TYPE})
@Documented
public @interface ExcelSheetLayout {

	/**
	 * Landscape.
	 *
	 * @return true, if successful
	 */
	public boolean landscape() default true;
	
	/**
	 * Not merge.
	 *
	 * @return true, if successful
	 */
	public boolean notMerge() default true;
	
	/**
	 * Sort and filter.
	 *
	 * @return true, if successful
	 */
	public boolean sortAndFilter() default true;
	
	/**
	 * Start column.
	 *
	 * @return the int
	 */
	public int startColumn()default 0;
	
	/**
	 * Start row.
	 *
	 * @return the int
	 */
	public int startRow()default 0;
	
	/**
	 * Order.
	 *
	 * @return the int
	 */
	public int order()default -1;
	
	
	/**
	 * Area border.
	 *
	 * @return the excel area border[]
	 */
	public ExcelAreaBorder[] areaBorder() default {};
	
	/**
	 * Show header.
	 *
	 * @return true, if successful
	 */
	public boolean showHeader() default true;
	
	
	/**
	 * Group row.
	 *
	 * @return true, if successful
	 */
	public boolean groupRow() default false;
	
	/**
	 * Group column.
	 *
	 * @return true, if successful
	 */
	public boolean groupColumn() default false;
	
	
	/**
	 * Paper size.
	 *
	 * @return the paper size
	 */
	public short scale() default 100;
	
	/**
	 * Locked.
	 *
	 * @return true, if successful
	 */
	public boolean locked() default false;
	
}
