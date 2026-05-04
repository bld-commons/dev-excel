package com.bld.generator.report.excel.data;

/**
 * Per-column state tracked across rows to drive merge decisions.
 * <p>
 * {@code previousValue} holds the cell value written on the previous row,
 * removing the need to re-wrap the previous row bean via {@code BeanWrapperImpl}.
 * {@code changed} is set to {@code true} when the value transitions during the
 * current row, allowing dependent columns to propagate the change without
 * inspecting the data themselves.
 * </p>
 */
public class MergeColumnState {

	private Object previousValue;
	private boolean changed;
	private boolean initialized;

	public Object getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(Object previousValue) {
		this.previousValue = previousValue;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

}
