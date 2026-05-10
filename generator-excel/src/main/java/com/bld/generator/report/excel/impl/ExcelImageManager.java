/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.ExcelImageManager.java
*/
package com.bld.generator.report.excel.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.bld.common.spreadsheet.exception.ExcelGeneratorException;
import com.bld.generator.report.excel.ExcelAttachment;
import com.bld.generator.report.excel.annotation.ExcelImage;
import com.bld.generator.report.excel.data.SheetHeader;

/**
 * The Class ExcelImageManager.
 */
@Component
public class ExcelImageManager {

	/**
	 * Adds the image.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param cell        the cell
	 * @throws Exception the exception
	 */
	public void addImage(Workbook workbook, Sheet sheet, SheetHeader sheetHeader, Cell cell) throws Exception {
		ExcelImage excelImage = sheetHeader.getExcelImage();
		int pictureureIdx = workbook.addPicture((byte[]) sheetHeader.getValue(), excelImage.pictureType().nativeId);
		org.apache.poi.ss.usermodel.CreationHelper helper = workbook.getCreationHelper();
		Drawing<?> drawing = sheet.createDrawingPatriarch();

		ClientAnchor anchor = helper.createClientAnchor();

		anchor.setCol1(cell.getColumnIndex());
		anchor.setRow1(cell.getRowIndex());
		anchor.setCol2(cell.getColumnIndex() + 1);
		anchor.setRow2(cell.getRowIndex() + 1);
		anchor.setAnchorType(excelImage.anchorType());

		Picture pict = drawing.createPicture(anchor, pictureureIdx);
		pict.resize(excelImage.resizeWidth(), excelImage.resizeHeight());
	}

	/**
	 * Adds the attachment.
	 *
	 * @param workbook    the workbook
	 * @param sheet       the sheet
	 * @param sheetHeader the sheet header
	 * @param cell        the cell
	 * @throws Exception the exception
	 */
	public void addAttachment(Workbook workbook, Sheet sheet, SheetHeader sheetHeader, Cell cell) throws Exception {
		if (sheetHeader.getValue() != null && sheetHeader.getValue() instanceof ExcelAttachment<?>) {
			ExcelAttachment<?> excelAttachment = (ExcelAttachment<?>) sheetHeader.getValue();
			byte[] file = manageExcelAttachment(excelAttachment.getAttachment());
			String fileNameExtension = excelAttachment.getFileName() + excelAttachment.getAttachmentType().getFileExtension();
			int storageId = workbook.addOlePackage(file, fileNameExtension, fileNameExtension, fileNameExtension);
			byte[] image = IOUtils.toByteArray(getClass().getResourceAsStream(excelAttachment.getAttachmentType().getImage()));
			int iconId = workbook.addPicture(image, PictureType.JPEG.nativeId);

			Drawing<?> drawing = sheet.createDrawingPatriarch();

			ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, cell.getColumnIndex(), cell.getRowIndex(), cell.getColumnIndex() + 1, cell.getRowIndex() + 1);
			anchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);

			drawing.createObjectData(anchor, storageId, iconId);
		}
	}

	/**
	 * Manage excel image.
	 *
	 * @param sheetHeader the sheet header
	 * @param value       the value
	 * @return the object
	 * @throws Exception             the exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException           Signals that an I/O exception has occurred.
	 */
	public Object manageExcelImage(SheetHeader sheetHeader, Object value) throws Exception, FileNotFoundException, IOException {
		if (sheetHeader.getExcelImage() != null) {
			value = manageExcelAttachment(value);
		}
		return value;
	}

	/**
	 * Manage excel attachment.
	 *
	 * @param value the value
	 * @return the byte[]
	 * @throws Exception             the exception
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException           Signals that an I/O exception has occurred.
	 */
	public byte[] manageExcelAttachment(Object value) throws Exception, FileNotFoundException, IOException {
		byte[] file = null;
		if (value != null) {
			if (!(value instanceof String || value instanceof byte[]))
				throw new ExcelGeneratorException("The annotation ExcelImage can to be used only with fields String or byte[] type");
			if (value instanceof String) {
				InputStream inputStream = new FileInputStream((String) value);
				file = IOUtils.toByteArray(inputStream);
			} else
				file = (byte[]) value;
		}
		return file;
	}

}
