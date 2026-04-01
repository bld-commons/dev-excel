/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generatorator.report.utils.ExcelUtils.java
*/
package com.bld.common.spreadsheet.utils;

import java.lang.reflect.ParameterizedType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.WordUtils;
import org.apache.poi.ss.usermodel.Sheet;

import com.bld.common.spreadsheet.constant.RowStartEndType;
import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

/**
 * Static utility class with low-level helpers for Excel cell coordinate calculation,
 * column/row sizing, generic-type resolution, and mathematical expression evaluation.
 * <p>
 * All members are {@code static}; this class is not instantiable.
 * It is used internally by the {@code generator-excel} module and is not part of the
 * public API intended for application code.
 * </p>
 */
@SuppressWarnings("unchecked")
public class ExcelUtils {


	/** The Constant ANNOTATIONS. */
//	private final static Log logger = LogFactory.getLog(ExcelUtils.class);

	/** The Constant ANNOTATIONS. */
	public static final String ANNOTATIONS = "annotations";

	/** The Constant ANNOTATION_DATA. */
	public static final String ANNOTATION_DATA = "annotationData";


	/** The Constant AUTO_SIZE_HEIGHT. */
	public final static short AUTO_SIZE_HEIGHT = -1;
	
	/** The Constant ENV_SHEET_NAME. */
	public final static String ENV_SHEET_NAME="${sheetName}";



	/**
	 * Converts a SQL/JPQL parameter name (typically {@code SNAKE_CASE} or lowercase)
	 * to its camelCase JavaBean equivalent.
	 * <p>
	 * For example, {@code "FIRST_NAME"} becomes {@code "firstName"}.
	 * </p>
	 *
	 * @param parameter the raw parameter name
	 * @return the camelCase representation
	 */
	public static String getNameParameter(String parameter) {
		parameter = WordUtils.capitalize(parameter.replace("_", " ").toLowerCase()).replace(" ", "");
		return (parameter.charAt(0) + "").toLowerCase() + parameter.substring(1);
	}




	/**
	 * Converts zero-based row and column indices to an Excel cell address string,
	 * optionally using absolute references (i.e. prefixing with {@code $}).
	 * <p>
	 * For example, {@code coordinateCalculation(1, 0, true, true)} returns {@code "$A$1"}.
	 * </p>
	 *
	 * @param row           the 1-based row number (as used in Excel formulas)
	 * @param idColumn      the zero-based column index
	 * @param isBlockColumn {@code true} to prefix the column letter with {@code $}
	 * @param isBlockRow    {@code true} to prefix the row number with {@code $}
	 * @return the Excel cell address string (e.g. {@code "B3"} or {@code "$B$3"})
	 */
	public static String coordinateCalculation(int row, int idColumn, boolean isBlockColumn,boolean isBlockRow) {
		int mod = 0;
		int div = idColumn;
		String blockColumn = blockCoordinate(isBlockColumn);
		String blockRow = blockCoordinate(isBlockRow);
		String column = "";
		do {
			mod = div % 26;
			mod += 65;
			div = (div / 26) - 1;
			column = Character.toString((char) mod) + column;
		} while (div >= 0);
		column = blockColumn + column + blockRow + row;

		return column;
	}

	/**
	 *  The block coordinate.
	 *
	 * @param isBlockCoordinate the is block coordinate
	 * @return the string
	 */
	private static String blockCoordinate(boolean isBlockCoordinate) {
		String blockCoordinate="";
		if(isBlockCoordinate)
			blockCoordinate="$";
		return blockCoordinate;
	}
	
	
	/**
	 * Resolves a column key to its fully-qualified {@code sheetName.fieldName} form.
	 * <p>
	 * If {@code key} does not already contain a dot, the sheet name is prepended.
	 * The {@link com.bld.common.spreadsheet.constant.RowStartEndType#VALUE} placeholder
	 * is stripped from the key before processing.
	 * </p>
	 *
	 * @param sheet the POI {@link Sheet} whose name is used as prefix when missing
	 * @param key   the raw column key, possibly containing a sheet-name prefix
	 * @return the fully-qualified column key in the form {@code "sheetName.fieldName"}
	 */
	public static String getKeyColumn(Sheet sheet, String key) {
		key=key.replace(RowStartEndType.VALUE.getValue(), "");
		if (!key.contains("."))
			key = sheet.getSheetName() + "." + key;
		return key;
	}



	/**
	 * Converts a logical column width (in characters) to the internal POI unit
	 * (1/256th of a character width), as expected by
	 * {@link org.apache.poi.ss.usermodel.Sheet#setColumnWidth(int, int)}.
	 *
	 * @param widthColumn the desired column width in characters
	 * @return the width expressed in POI units (widthColumn * 1036)
	 */
	public static short widthColumn(int widthColumn) {
		return (short) (widthColumn * 1036);
	}

	/**
	 * Converts a logical row height (in points) to the internal POI unit
	 * (1/20th of a point), as expected by
	 * {@link org.apache.poi.ss.usermodel.Row#setHeight(short)}.
	 * <p>
	 * Passing {@link #AUTO_SIZE_HEIGHT} ({@code -1}) leaves the value unchanged so
	 * that POI can auto-size the row.
	 * </p>
	 *
	 * @param rowHeight the desired row height in points, or {@link #AUTO_SIZE_HEIGHT}
	 * @return the height expressed in POI units, or {@link #AUTO_SIZE_HEIGHT} if auto-sizing
	 */
	public static short rowHeight(int rowHeight) {
		if (rowHeight != AUTO_SIZE_HEIGHT)
			rowHeight = rowHeight * 568;
		return (short) rowHeight;
	}

	/**
	 * Returns the first actual type argument of the generic superclass of {@code entity}.
	 * <p>
	 * Equivalent to {@link #getTClass(Object, int) getTClass(entity, 0)}.
	 * </p>
	 *
	 * @param <T>    the expected type
	 * @param entity the object whose generic superclass is inspected
	 * @return the {@link Class} corresponding to the first type argument
	 */
	public static <T> Class<T> getTClass(Object entity) {
		return getTClass(entity, 0);
	}

	/**
	 * Returns the actual type argument at position {@code i} of the generic superclass
	 * of {@code entity}.
	 * <p>
	 * For example, given {@code class MySheet extends SheetData<MyRow>}, calling
	 * {@code getTClass(mySheet, 0)} returns {@code MyRow.class}.
	 * </p>
	 *
	 * @param <T>    the expected type
	 * @param entity the object whose generic superclass is inspected
	 * @param i      the zero-based index of the type argument to retrieve
	 * @return the {@link Class} corresponding to the {@code i}-th type argument
	 */
	public static <T> Class<T> getTClass(Object entity, int i) {
		ParameterizedType parameterizedType = null;
		try {
			parameterizedType = (ParameterizedType) entity.getClass().getGenericSuperclass();
		} catch (Exception e) {
			parameterizedType = (ParameterizedType) entity.getClass().getSuperclass().getGenericSuperclass();
		}
		Class<T> classT = (Class<T>) parameterizedType.getActualTypeArguments()[i];
		return classT;
	}
	
	
	/**
	 * Evaluates a mathematical expression string by substituting a single variable.
	 * <p>
	 * Uses the <a href="https://javaluator.sourceforge.net">Javaluator</a> library.
	 * For example, {@code evaluate("n * 2", "n", 5)} returns {@code 10.0}.
	 * Returns {@code null} if the expression cannot be evaluated.
	 * </p>
	 *
	 * @param exprenssionIndex the mathematical expression (e.g. {@code "n + 1"})
	 * @param param            the variable name used in the expression
	 * @param value            the numeric value to bind to the variable
	 * @return the result as a {@link Double}, or {@code null} on evaluation error
	 */
	public static Double evaluate(String exprenssionIndex,String param,Number value) {
		Double evaluate=null;
		try {
			DoubleEvaluator eval = new DoubleEvaluator();
			StaticVariableSet<Double> variables = new StaticVariableSet<Double>();
			variables.set(param, value.doubleValue());
			evaluate=eval.evaluate(exprenssionIndex, variables);
		} catch (Exception e) {
			//logger.error(ExceptionUtils.getStackTrace(e));
		}
		return evaluate;
	}

	/**
	 * Compiles {@code param} as a regular expression and returns a {@link Matcher}
	 * for {@code function}.
	 *
	 * @param param    the regular expression pattern
	 * @param function the input string to match against
	 * @return a {@link Matcher} ready for use (not yet matched)
	 */
	public static Matcher matcher(String param,String function) {
		Pattern pattern = Pattern.compile(param);
		return pattern.matcher(function);
	}
	
}
