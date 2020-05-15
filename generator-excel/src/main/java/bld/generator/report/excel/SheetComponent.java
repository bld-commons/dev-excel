/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.SheetComponent.java
*/
package bld.generator.report.excel;

// TODO: Auto-generated Javadoc
/**
 * The Interface SheetComponent.
 * 
 * SheetComponet is implemented from: 
 * 1) SheetData
 * 2) SheetSummary
 * 
 * Its is used to merge different sheet through the class MergeSheet 
 */
public interface SheetComponent {

	/**
	 * Sets the name sheet.
	 *
	 * @param sheetName the new name sheet
	 */
	public void setSheetName(String sheetName);
}
