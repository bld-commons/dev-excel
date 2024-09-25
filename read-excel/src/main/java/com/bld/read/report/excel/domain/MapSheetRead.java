/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.domain;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * The Class MapSheetRead.
 *
 * @param <ID> the generic type
 * @param <T>  the generic type
 */
public abstract class MapSheetRead<ID, T extends RowSheetRead> extends SheetRead<T> {

	/** The map rows. */
	private Map<ID, T> mapRows;

	/** The key field. */
	@NotNull
	private String keyField;

	/**
	 * Instantiates a new map sheet read.
	 *
	 * @param sheetName the sheet name
	 * @param keyField  the key field
	 */
	public MapSheetRead(@Size(max = 31) String sheetName, String keyField) {
		super(sheetName);
		this.mapRows = new HashMap<>();
		this.keyField = keyField;
	}

	/**
	 * Gets the row.
	 *
	 * @param id the id
	 * @return the row
	 */
	public T getRow(ID id) {
		return this.mapRows.get(id);
	}

	/**
	 * Adds the row sheet.
	 *
	 * @param t the t
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addRowSheet(T t) throws Exception {
		super.addRowSheet(t);
		this.mapRows.put((ID) PropertyUtils.getProperty(t, keyField), t);
	}

}
