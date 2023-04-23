/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.comparator.PivotColumnComparator.java
 */
package bld.generator.report.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;

import bld.generator.report.excel.annotation.ExcelPivotColumn;

/**
 * The Class PivotColumnComparator.
 */
public class PivotColumnComparator implements Comparator<Field> {

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(Field o1, Field o2) {
		int compare=0;
		if(o1.getAnnotation(ExcelPivotColumn.class).order()>o2.getAnnotation(ExcelPivotColumn.class).order())
			compare=1;
		else if(o1.getAnnotation(ExcelPivotColumn.class).order()<o2.getAnnotation(ExcelPivotColumn.class).order())
			compare=-1;
		return compare;
	}

	

}
