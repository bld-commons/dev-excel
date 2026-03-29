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
 * Facade Spring component that exposes the public API for Excel and CSV file generation.
 * <p>
 * This class is a singleton Spring bean that delegates every generation request to a
 * prototype-scoped {@link ScopeGenerateExcelImpl} instance retrieved from the
 * {@link org.springframework.context.ApplicationContext}. Using a prototype bean ensures
 * that the stateful Apache POI {@code Workbook} is never shared across concurrent calls.
 * </p>
 *
 * <p>Three generation modes are available:</p>
 * <ul>
 *   <li>{@link #createFileXls(ReportExcel)} — HSSF engine, produces {@code .xls} files (max 65,535 rows)</li>
 *   <li>{@link #createFileXlsx(ReportExcel)} — XSSF engine, produces {@code .xlsx} files with full feature support</li>
 *   <li>{@link #createBigDataFileXlsx(ReportExcel)} — SXSSF streaming engine, produces {@code .xlsx} files with lower memory consumption</li>
 * </ul>
 *
 * <p>Each method is also available in an {@link java.io.OutputStream} variant that writes
 * directly to the provided stream instead of returning a byte array.</p>
 *
 * <table style="width:70%; border: 1px solid #666;">
 * <caption>Feature comparison by generation mode</caption>
 * <tr>
 * <th style="border: 1px solid #666; text-align: center;">Feature</th>
 * <th style="border: 1px solid #666; text-align: center;">HSSF (.xls)</th>
 * <th style="border: 1px solid #666; text-align: center;">XSSF (.xlsx)</th>
 * <th style="border: 1px solid #666; text-align: center;">SXSSF (big data)</th>
 * </tr>
 * <tr><td style="border: 1px solid #666;">CPU and memory efficiency</td><td style="border: 1px solid #666; text-align: center;">Varies</td><td style="border: 1px solid #666; text-align: center;">Varies</td><td style="border: 1px solid #666; text-align: center;">Good</td></tr>
 * <tr><td style="border: 1px solid #666;">Read files</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Write files</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td></tr>
 * <tr><td style="border: 1px solid #666;">Create sheets / rows / cells</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td></tr>
 * <tr><td style="border: 1px solid #666;">Delete sheets / rows / cells</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Cell styling</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td></tr>
 * <tr><td style="border: 1px solid #666;">Shift rows</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Clone sheets</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Formula evaluation</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Cell comments</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Images</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Charts</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * <tr><td style="border: 1px solid #666;">Pivot tables</td><td style="border: 1px solid #666; text-align: center;">No</td><td style="border: 1px solid #666; text-align: center;">Yes</td><td style="border: 1px solid #666; text-align: center;">No</td></tr>
 * </table>
 *
 * @author Francesco Baldi
 * @see ScopeGenerateExcelImpl
 * @see ReportExcel
 */
@Component
public class GenerateExcelImpl implements GenerateExcel {

	private static final Logger logger = LoggerFactory.getLogger(GenerateExcelImpl.class);

	/** The application context. */
	@Autowired
	private ApplicationContext applicationContext;

	/**
	 * Generates an XLS file using the HSSF engine and returns it as a byte array.
	 *
	 * @param report the {@link ReportExcel} descriptor containing sheets and configuration
	 * @return the generated {@code .xls} file as a byte array
	 * @throws RuntimeException if any error occurs during generation
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
	 * Generates an XLSX file using the XSSF engine and returns it as a byte array.
	 * <p>Supports the full feature set: charts, pivot tables, formula evaluation, images, and cell comments.</p>
	 *
	 * @param report the {@link ReportExcel} descriptor containing sheets and configuration
	 * @return the generated {@code .xlsx} file as a byte array
	 * @throws RuntimeException if any error occurs during generation
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
	 * Generates an XLSX file using the SXSSF streaming engine and returns it as a byte array.
	 * <p>Optimised for large datasets: writes rows to disk as they are produced, keeping memory
	 * consumption low. Features such as formula evaluation, charts, images, and cell comments
	 * are not supported in this mode.</p>
	 *
	 * @param report the {@link ReportExcel} descriptor containing sheets and configuration
	 * @return the generated {@code .xlsx} file as a byte array
	 * @throws RuntimeException if any error occurs during generation
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
	 * Generates an XLS file using the HSSF engine and writes it to the provided {@link OutputStream}.
	 *
	 * @param report       the {@link ReportExcel} descriptor containing sheets and configuration
	 * @param outputStream the output stream to write the generated file to
	 * @throws RuntimeException if any error occurs during generation
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
	 * Generates an XLSX file using the XSSF engine and writes it to the provided {@link OutputStream}.
	 *
	 * @param report       the {@link ReportExcel} descriptor containing sheets and configuration
	 * @param outputStream the output stream to write the generated file to
	 * @throws RuntimeException if any error occurs during generation
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
	 * Generates an XLSX file using the SXSSF streaming engine and writes it to the provided {@link OutputStream}.
	 * <p>Optimised for large datasets. Features such as formula evaluation, charts, images, and cell comments
	 * are not supported in this mode.</p>
	 *
	 * @param report       the {@link ReportExcel} descriptor containing sheets and configuration
	 * @param outputStream the output stream to write the generated file to
	 * @throws RuntimeException if any error occurs during generation
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
