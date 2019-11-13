/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
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
	 * Gets the merge column by field.
	 *
	 * @return the merge column by field
	 */
	public abstract Map<String, List<String>> getMergeColumnByField();

}