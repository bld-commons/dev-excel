package bld.generator.report.excel.utility;

import org.apache.poi.xssf.usermodel.XSSFColor;

public class ExcelLayoutUtility {

	/**
	 * Gets the color.
	 *
	 * @param rgbColor the rgb color
	 * @return the color
	 */
	private static XSSFColor getColor(byte... rgbColor) {
		XSSFColor color = new XSSFColor();
		color.setRGB(rgbColor);
		return color;
	}
	
	public static XSSFColor color(byte red,byte green, byte blue) {
		return getColor(red,green,blue);
	}
	
}
