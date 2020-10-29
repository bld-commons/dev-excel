/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelSubtotal.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.DataConsolidateFunction;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 * The Interface ExcelSubtotal.
 * ExcelSubtotal and {@link bld.generator.report.excel.annotation.ExcelSubtotals} are used To calculate the subtotals grouped by the value of the first column, the function type is defined by the "dataConsolidateFunction" property.<br>
 * This annotation is added to fields within classes of type "{@link bld.generator.report.excel.RowSheet}".<br>
 * The property ExcelCellLayout define the cell layout.
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelSubtotal {

	/**
	 * Data consolidate function.
	 *
	 * @return the data consolidate function
	 */
	public DataConsolidateFunction dataConsolidateFunction();
	
	
	/**
	 * Excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout excelCellLayout() default @ExcelCellLayout(horizontalAlignment = HorizontalAlignment.RIGHT,font = @ExcelFont(bold = true));
}
