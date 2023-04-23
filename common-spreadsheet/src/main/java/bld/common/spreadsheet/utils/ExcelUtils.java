/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.utils.ExcelUtils.java
*/
package bld.common.spreadsheet.utils;

import java.lang.reflect.ParameterizedType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.WordUtils;
import org.apache.poi.ss.usermodel.Sheet;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import com.fathzer.soft.javaluator.StaticVariableSet;

import bld.common.spreadsheet.constant.RowStartEndType;

// TODO: Auto-generated Javadoc
/**
 * The Class ExcelUtils.
 * 
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
	 * Gets the name parameter.
	 *
	 * @param parameter the parameter
	 * @return the name parameter
	 */
	public static String getNameParameter(String parameter) {
		parameter = WordUtils.capitalize(parameter.replace("_", " ").toLowerCase()).replace(" ", "");
		return (parameter.charAt(0) + "").toLowerCase() + parameter.substring(1);
	}




	/**
	 * Calcolo coordinate function.
	 *
	 * @param row the row
	 * @param idColumn the id column
	 * @param isBlockColumn the is block column
	 * @param isBlockRow the is block row
	 * @return the string
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
	 * Gets the key column.
	 *
	 * @param sheet the sheet
	 * @param key   the key
	 * @return the key column
	 */
	public static String getKeyColumn(Sheet sheet, String key) {
		key=key.replace(RowStartEndType.VALUE.getValue(), "");
		if (!key.contains("."))
			key = sheet.getSheetName() + "." + key;
		return key;
	}



	/**
	 * Width column.
	 *
	 * @param widthColumn the width column
	 * @return the short
	 */
	public static short widthColumn(int widthColumn) {
		return (short) (widthColumn * 1036);
	}

	/**
	 * Hight row.
	 *
	 * @param rowHeight the hight row
	 * @return the short
	 */
	public static short rowHeight(int rowHeight) {
		if (rowHeight != AUTO_SIZE_HEIGHT)
			rowHeight = rowHeight * 568;
		return (short) rowHeight;
	}

	/**
	 * Gets the t class.
	 *
	 * @param <T> the generic type
	 * @param entity the entity
	 * @return the t class
	 */
	public static <T> Class<T> getTClass(Object entity) {
		return getTClass(entity, 0);
	}

	/**
	 * Gets the t class.
	 *
	 * @param <T> the generic type
	 * @param entity the entity
	 * @param i the i
	 * @return the t class
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
	 * Evaluate.
	 *
	 * @param exprenssionIndex the exprenssion index
	 * @param param the param
	 * @param value the value
	 * @return the double
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
	 * Matcher.
	 *
	 * @param param the param
	 * @param function the function
	 * @return the matcher
	 */
	public static Matcher matcher(String param,String function) {
		Pattern pattern = Pattern.compile(param);
		return pattern.matcher(function);
	}
	
}
