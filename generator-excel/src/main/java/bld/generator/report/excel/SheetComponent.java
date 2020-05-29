/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetComponent.java
*/
package bld.generator.report.excel;

// TODO: Auto-generated Javadoc
/**
 * The Interface SheetComponent.
 * <br>
 * SheetComponet is implemented from:<br>
 * <ol> 
 * <li>SheetData</li>
 * <li>SheetSummary</li>
 * </ol>
 * <br>
 * Its is used to merge different sheet through the class MergeSheet 
 */
public interface SheetComponent {

	/**
	 * Sets the sheet name.
	 *
	 * @param sheetName the new sheet name
	 */
	public void setSheetName(String sheetName);
}
