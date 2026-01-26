/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class com.bld.generator.report.excel.impl.GenerateExcelImpl.java
*/
package com.bld.generator.report.excel.impl;

import java.io.OutputStream;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.bld.generator.report.excel.GenerateExcel;
import com.bld.generator.report.excel.data.ReportExcel;

/**
 * The Class GenerateExcelImpl.<br>
 * In the GenerateExcelImpl class starts the process to generate the xls or xlsx
 * file.<br>
 * Below is a summary table with the features of the functions for generating
 * excel files.<br>
 * 
 * <table style="width:50%; border: 1px solid #666;">
 * <tr>
 * <th style="width: 23%; border: 1px solid #666; text-align: center;"></th>
 * <th style="width: 23%; border: 1px solid #666; text-align: center;">HSSF</th>
 * <th style="width: 23%; border: 1px solid #666; text-align: center;">XSSF</th>
 * <th style="width: 23%; border: 1px solid #666; text-align:
 * center;">SXSSF</th>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Function</td>
 * <td style="width: 23%; border: 1px solid #666;text-align:
 * center;">createFileXls</td>
 * <td style="width: 23%; border: 1px solid #666;text-align:
 * center;">createFileXlsx</td>
 * <td style="width: 23%; border: 1px solid #666;text-align:
 * center;">createBigDataFileXlsx</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">CPU and memory
 * efficiency</td>
 * <td style="width: 23%; border: 1px solid #666;text-align:
 * center;">Varies</td>
 * <td style="width: 23%; border: 1px solid #666;text-align:
 * center;">varies</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Good</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Read Files</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Write Files</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Create sheets/rows/cells</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Delete sheets/rows/cells</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Styling cells</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Shift rows</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Cloning sheets</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Formula evaluation</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Cell comments</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * <tr>
 * <td style="width: 23%; border: 1px solid #666;">Picture</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">Yes</td>
 * <td style="width: 23%; border: 1px solid #666;text-align: center;">No</td>
 * </tr>
 * </table>
 * 
 * 
 */
@Component
public class GenerateExcelImpl implements GenerateExcel {

	private static final Logger logger = LoggerFactory.getLogger(GenerateExcelImpl.class);

	/** The application context. */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Creates the file xls.
	 *
	 * @param report the report
	 * @return the byte[]
	 * 
	 */
	@Override
	public byte[] createFileXls(ReportExcel report) {
		try {
			ScopeGenerateExcelImpl scopeGenerateExcelImpl = this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
			return scopeGenerateExcelImpl.createFileXls(report);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates the file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * 
	 */
	@Override
	public byte[] createFileXlsx(ReportExcel report) {
		try {
			ScopeGenerateExcelImpl scopeGenerateExcelImpl = this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
			return scopeGenerateExcelImpl.createFileXlsx(report);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report the report
	 * @return the byte[]
	 * 
	 */
	@Override
	public byte[] createBigDataFileXlsx(ReportExcel report) {
		try {
			ScopeGenerateExcelImpl scopeGenerateExcelImpl = this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
			return scopeGenerateExcelImpl.createBigDataFileXlsx(report);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates the file xls.
	 *
	 * @param report       the report
	 * @param outputStream the output stream
	 * 
	 */
	@Override
	public void createFileXls(ReportExcel report, OutputStream outputStream) {
		try {
			ScopeGenerateExcelImpl scopeGenerateExcelImpl = this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
			scopeGenerateExcelImpl.createFileXls(report, outputStream);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates the file xlsx.
	 *
	 * @param report       the report
	 * @param outputStream the output stream
	 * 
	 */
	@Override
	public void createFileXlsx(ReportExcel report, OutputStream outputStream) {
		try {
			ScopeGenerateExcelImpl scopeGenerateExcelImpl = this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
			scopeGenerateExcelImpl.createFileXlsx(report, outputStream);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

	/**
	 * Creates the big data file xlsx.
	 *
	 * @param report       the report
	 * @param outputStream the output stream
	 * 
	 */
	@Override
	public void createBigDataFileXlsx(ReportExcel report, OutputStream outputStream) {
		try {
			ScopeGenerateExcelImpl scopeGenerateExcelImpl = this.applicationContext.getBean(ScopeGenerateExcelImpl.class);
			scopeGenerateExcelImpl.createBigDataFileXlsx(report, outputStream);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
			throw new RuntimeException(e);
		}
	}

}
