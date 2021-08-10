package bld.generator.report.excel.annotation.impl;

import bld.generator.report.excel.annotation.ExcelChartDataLabel;

public class ExcelChartDataLabelImpl extends ExcelAnnotationImpl<ExcelChartDataLabel> {

	private boolean enable;
	
	private boolean showVal;
	
	private boolean showLegendKey;
	
	private boolean showCatName;
	
	private boolean showSerName;
	
	
	

	public ExcelChartDataLabelImpl() {
		super();
		this.enable=false;
		this.showVal = true;
		this.showLegendKey = true;
		this.showCatName = true;
		this.showSerName = true;
	}

	
	
	public ExcelChartDataLabelImpl(boolean enable, boolean showVal, boolean showLegendKey, boolean showCatName, boolean showSerName) {
		super();
		this.enable = enable;
		this.showVal = showVal;
		this.showLegendKey = showLegendKey;
		this.showCatName = showCatName;
		this.showSerName = showSerName;
	}



	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isShowVal() {
		return showVal;
	}

	public void setShowVal(boolean showVal) {
		this.showVal = showVal;
	}

	public boolean isShowLegendKey() {
		return showLegendKey;
	}

	public void setShowLegendKey(boolean showLegendKey) {
		this.showLegendKey = showLegendKey;
	}

	public boolean isShowCatName() {
		return showCatName;
	}

	public void setShowCatName(boolean showCatName) {
		this.showCatName = showCatName;
	}

	public boolean isShowSerName() {
		return showSerName;
	}

	public void setShowSerName(boolean showSerName) {
		this.showSerName = showSerName;
	}
	
	
	
	
	
}
