/**
 * @author Francesco Baldi
 * @email francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.impl.ExcelChartDataLabelImpl.java
 * 
 */
package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelChartDataLabel;

/**
 * The Class ExcelChartDataLabelImpl.
 */
public class ExcelChartDataLabelImpl extends ExcelAnnotationImpl<ExcelChartDataLabel> {

	/** The enable. */
	private boolean enable;
	
	/** The show val. */
	private boolean showVal;
	
	/** The show legend key. */
	private boolean showLegendKey;
	
	/** The show cat name. */
	private boolean showCatName;
	
	/** The show ser name. */
	private boolean showSerName;
	
	
	

	/**
	 * Instantiates a new excel chart data label impl.
	 */
	public ExcelChartDataLabelImpl() {
		super();
		this.enable=false;
		this.showVal = true;
		this.showLegendKey = true;
		this.showCatName = true;
		this.showSerName = true;
	}

	
	
	/**
	 * Instantiates a new excel chart data label impl.
	 *
	 * @param enable the enable
	 * @param showVal the show val
	 * @param showLegendKey the show legend key
	 * @param showCatName the show cat name
	 * @param showSerName the show ser name
	 */
	public ExcelChartDataLabelImpl(boolean enable, boolean showVal, boolean showLegendKey, boolean showCatName, boolean showSerName) {
		super();
		this.enable = enable;
		this.showVal = showVal;
		this.showLegendKey = showLegendKey;
		this.showCatName = showCatName;
		this.showSerName = showSerName;
	}



	/**
	 * Checks if is enable.
	 *
	 * @return true, if is enable
	 */
	public boolean isEnable() {
		return enable;
	}

	/**
	 * Sets the enable.
	 *
	 * @param enable the new enable
	 */
	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	/**
	 * Checks if is show val.
	 *
	 * @return true, if is show val
	 */
	public boolean isShowVal() {
		return showVal;
	}

	/**
	 * Sets the show val.
	 *
	 * @param showVal the new show val
	 */
	public void setShowVal(boolean showVal) {
		this.showVal = showVal;
	}

	/**
	 * Checks if is show legend key.
	 *
	 * @return true, if is show legend key
	 */
	public boolean isShowLegendKey() {
		return showLegendKey;
	}

	/**
	 * Sets the show legend key.
	 *
	 * @param showLegendKey the new show legend key
	 */
	public void setShowLegendKey(boolean showLegendKey) {
		this.showLegendKey = showLegendKey;
	}

	/**
	 * Checks if is show cat name.
	 *
	 * @return true, if is show cat name
	 */
	public boolean isShowCatName() {
		return showCatName;
	}

	/**
	 * Sets the show cat name.
	 *
	 * @param showCatName the new show cat name
	 */
	public void setShowCatName(boolean showCatName) {
		this.showCatName = showCatName;
	}

	/**
	 * Checks if is show ser name.
	 *
	 * @return true, if is show ser name
	 */
	public boolean isShowSerName() {
		return showSerName;
	}

	/**
	 * Sets the show ser name.
	 *
	 * @param showSerName the new show ser name
	 */
	public void setShowSerName(boolean showSerName) {
		this.showSerName = showSerName;
	}
	
	
	
	
	
}
