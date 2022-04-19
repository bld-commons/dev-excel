/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.annotation.ExcelSummary.java
*/
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import bld.generator.report.excel.constant.ExcelConstant;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelSummary.<br>
 * ExcelSummary is used to define the following properties:
 * <ul>
 * <li>Title - to write the title of table</li>
 * <li>Comment - to write a comment on header</li>
 * <li>Layout - to set the cell style on cells of the first column</li>
 * <li>widthColumn1 - to set the width on the first column</li>
 * <li>widthColumn2 - to set the width on the second column</li>
 * </ul> 
 * 
 * It must be to used on {@link bld.generator.report.excel.SheetSummary} classes
 * 
 */
@Retention(RUNTIME)
@Target(TYPE)
@Documented
public @interface ExcelSummary {
	/**
	 * Title.
	 *
	 * @return the string
	 */
	public String title();
	
	
	/**
	 * Title cell formulta.
	 *
	 * @return true, if successful
	 */
	public boolean titleCellFormulta() default false; 
	
	/**
	 * Comment.
	 *
	 * @return the string
	 */
	public String comment() default "";
	
	/**
	 * Layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout layout() default @ExcelCellLayout;
	
	/**
	 * Width column 1.
	 *
	 * @return the int
	 */
	public int widthColumn1() default ExcelConstant.DEFAULT_WIDTH_COLUMN;
	
	/**
	 * Width column 2.
	 *
	 * @return the int
	 */
	public int widthColumn2() default ExcelConstant.DEFAULT_WIDTH_COLUMN;
	
}
