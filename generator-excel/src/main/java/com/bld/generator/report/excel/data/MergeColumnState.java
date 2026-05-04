package com.bld.generator.report.excel.data;

/**
 * Per-column state tracked across rows to drive merge decisions in
 * {@link com.bld.generator.report.excel.impl.ScopeGenerateExcelImpl}.
 *
 * <p>One instance exists per column that carries {@link com.bld.generator.report.excel.annotation.ExcelMergeRow}.
 * On each row the engine reads the column's current value, compares it against
 * {@code previousValue}, and decides whether to close the current merge region
 * and open a new one.</p>
 *
 * <p><b>Field semantics:</b></p>
 * <ul>
 *   <li>{@code previousValue} — value written on the previous row; used to detect
 *       equality breaks without re-wrapping the previous bean.</li>
 *   <li>{@code changed} — {@code true} when this column's value changed on the
 *       current row; dependent columns read this flag instead of inspecting their own
 *       driver's value directly.</li>
 *   <li>{@code initialized} — {@code false} only before the very first row is processed.
 *       Distinguishes the "first row" case from a legitimate {@code null} value (which
 *       matters for formula columns whose value is always {@code null}).</li>
 * </ul>
 */
public class MergeColumnState {

	/** Value from the previous row used to detect merge boundary. */
	private Object previousValue;

	/** {@code true} when the current row caused a value change for this column. */
	private boolean changed;

	/**
	 * {@code false} until the first row has been processed.
	 * Used to distinguish the initial state from a genuine {@code null} value —
	 * critical for formula columns whose value is always {@code null}.
	 */
	private boolean initialized;

	/**
	 * Returns the value recorded from the previous row.
	 *
	 * @return previous cell value, or {@code null} on the first row or for formula columns
	 */
	public Object getPreviousValue() {
		return previousValue;
	}

	/**
	 * Records the value written on the current row so it can be compared on the next row.
	 *
	 * @param previousValue the cell value just written
	 */
	public void setPreviousValue(Object previousValue) {
		this.previousValue = previousValue;
	}

	/**
	 * Returns whether this column's merge driver changed on the current row.
	 *
	 * @return {@code true} if a merge boundary was detected
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets the change flag for the current row.
	 *
	 * @param changed {@code true} if a merge boundary was detected
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	/**
	 * Returns whether at least one row has been processed for this column.
	 *
	 * @return {@code false} only before the first row
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Marks this state as having processed at least one row.
	 *
	 * @param initialized {@code true} after the first row is written
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

}
