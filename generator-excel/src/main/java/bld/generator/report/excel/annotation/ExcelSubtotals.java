/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelSubtotals.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelSubtotals.<br>
 * ExcelSubtotals and {@link bld.generator.report.excel.annotation.ExcelSubtotal} are used To calculate the subtotals grouped by the value of the first column, 
 * the function type is defined within of {@link bld.generator.report.excel.annotation.ExcelSubtotal}, here instead you set the "startLabel" and "endLabel" properties to be concatenated to the first column and to define the subtotal label that groups all rows.<br>
 * This annotation is defined within classes of type "{@link bld.generator.report.excel.RowSheet}".<br>
 * The property ExcelCellLayout define the cell layout instead the property sumForGroup enable the subtotals grouped by the value of the first column.<br>
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface ExcelSubtotals {

	/**
	 * Start label.
	 *
	 * @return the string
	 */
	public String startLabel() default "";
	
	/**
	 * Excel cell layout.
	 *
	 * @return the excel cell layout
	 */
	public ExcelCellLayout excelCellLayout() default @ExcelCellLayout(font = @ExcelFont(bold = true));
	
	/**
	 * End label.
	 *
	 * @return the string
	 */
	public String endLabel() default "";
	
	
	/**
	 * Label total group.
	 *
	 * @return the string
	 */
	public String labelTotalGroup();
	
	
	
	public String[] sumForGroup();
	
}
