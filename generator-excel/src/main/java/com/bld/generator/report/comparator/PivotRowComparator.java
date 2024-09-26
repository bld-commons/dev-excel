/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generatorator.report.excel.comparator.PivotRowComparator.java
 */
package com.bld.generator.report.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;

import com.bld.generator.report.excel.annotation.ExcelPivotRow;

/**
 * The Class PivotRowComparator.
 */
public class PivotRowComparator implements Comparator<Field> {

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
		if(o1.getAnnotation(ExcelPivotRow.class).order()>o2.getAnnotation(ExcelPivotRow.class).order())
			compare=1;
		else if(o1.getAnnotation(ExcelPivotRow.class).order()<o2.getAnnotation(ExcelPivotRow.class).order())
			compare=-1;
		return compare;
	}

	

}
