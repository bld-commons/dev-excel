/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetStatistica.java
*/
package bld.generator.report.excel;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface SheetStatistica.
 */
public interface SheetStatistica {

	/**
	 * Checks if is grafico.
	 *
	 * @return true, if is grafico
	 */
	public boolean isGrafico();

	/**
	 * Gets the map charts.
	 *
	 * @return the map charts
	 */
	public Map<String, List<String[]>> getMapCharts();

	/**
	 * Gets the asse X.
	 *
	 * @return the asse X
	 */
	public List<String> getAsseX();

	/**
	 * Gets the asse Y.
	 *
	 * @return the asse Y
	 */
	public String getAsseY();
}
