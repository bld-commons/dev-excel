/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelMergeRow.
 */
@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface ExcelMergeRow {

	/**
	 * Reference field.
	 *
	 * @return the string
	 */
	public String referenceField();	
}
