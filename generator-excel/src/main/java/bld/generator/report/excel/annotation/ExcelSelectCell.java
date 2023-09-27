/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelSelectCell.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * The Interface ExcelSelectCell.<br>
 * ExcelSelectCell is use to select a cell within a cover file.<br> 
 * It is used on {@link bld.generator.report.excel.data.ReportExcel} classes.
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelSelectCell {

	/**
	 * Cell reference.
	 *
	 * @return the string
	 */
	public String cellReference();
	
	
}
