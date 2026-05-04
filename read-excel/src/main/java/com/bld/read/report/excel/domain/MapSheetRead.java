/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package com.bld.read.report.excel.domain;

import java.util.HashMap;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.beans.BeanWrapperImpl;

/**
 * A {@link SheetRead} variant that indexes each parsed row by a key field,
 * enabling O(1) lookup by identifier after the sheet has been read.
 * <p>
 * Use this class instead of the plain {@link SheetRead} when you need to retrieve
 * individual rows by a unique key (e.g. a database ID or a code) rather than
 * iterating over the full list.  The key field is resolved via Spring's
 * {@link org.springframework.beans.BeanWrapperImpl} on each row object, so it must
 * match a readable JavaBean property name.
 * </p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * @ExcelReadSheet
 * public class ProductSheet extends MapSheetRead<Integer, ProductRow> {
 *     public ProductSheet() {
 *         super("Products", "id");   // "id" is the key field on ProductRow
 *     }
 * }
 *
 * // After reading:
 * ProductSheet sheet = excelRead.getSheet(ProductSheet.class, "Products");
 * ProductRow row = sheet.getRow(42);   // O(1) lookup by product id
 * }</pre>
 *
 * @param <ID> the type of the key used to index rows (must match the property type)
 * @param <T>  the row type, which must implement {@link RowSheetRead}
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
	 * Returns the row associated with the given key, or {@code null} if no row
	 * with that key was found in the sheet.
	 * <p>
	 * Lookup is performed in O(1) time via the internal {@link java.util.HashMap}.
	 * </p>
	 *
	 * @param id the key value to look up (must match the type parameter {@code ID})
	 * @return the matching row, or {@code null} if absent
	 */
	public T getRow(ID id) {
		return this.mapRows.get(id);
	}

	/**
	 * Adds a row to the underlying list (via {@link SheetRead#addRow(RowSheetRead)})
	 * and also inserts it into the internal index map using the value of the configured
	 * key field as the map key.
	 * <p>
	 * This method is called by the framework during sheet parsing and should not
	 * normally be invoked directly by application code.
	 * </p>
	 *
	 * @param t the row object to add; must not be {@code null}
	 * @throws Exception if the key field cannot be read from the row object
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addRow(T t) throws Exception {
		super.addRow(t);
		this.mapRows.put((ID) new BeanWrapperImpl(t).getPropertyValue(keyField), t);
	}

}
