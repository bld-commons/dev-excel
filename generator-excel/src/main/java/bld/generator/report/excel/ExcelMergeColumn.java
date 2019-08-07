/**
 * @author Francesco Baldi
 *
 * @mail francesco.baldi1987@gmail.com
 * @date 3-ago-2019
 */
package bld.generator.report.excel;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExcelMergeColumn.
 */
public interface ExcelMergeColumn {

	/**
	 * Gets the merge campo per totale.
	 *
	 * @return the merge campo per totale
	 */
	public abstract Map<String, List<String>> getMergeColumnByField();

}