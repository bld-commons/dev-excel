package com.bld.generator.report.excel.data;

import org.apache.commons.lang3.StringUtils;

import com.bld.generator.report.excel.annotation.ExcelFormulaAlias;

public class KeyParameterAlias {

	
	private String sheet;
	
	private String coordinate;
	
	private boolean blockColumn;
	
	private boolean blockRow;
	
	private String keyParameter;
	
	public KeyParameterAlias(ExcelFormulaAlias excelFormulaAlias) {
		this.sheet=excelFormulaAlias.sheet();
		this.coordinate=excelFormulaAlias.coordinate();
		this.blockColumn=excelFormulaAlias.blockColumn();
		this.blockRow=excelFormulaAlias.blockRow();
		String sheetPoint = "";
		if (StringUtils.isNotEmpty(excelFormulaAlias.sheet()))
			sheetPoint = excelFormulaAlias.sheet() + ".";
		keyParameter = sheetPoint + excelFormulaAlias.coordinate();
	}
	

	public String getSheet() {
		return sheet;
	}

	public void setSheet(String sheet) {
		this.sheet = sheet;
	}

	public String getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}

	public boolean isBlockColumn() {
		return blockColumn;
	}

	public void setBlockColumn(boolean blockColumn) {
		this.blockColumn = blockColumn;
	}

	public boolean isBlockRow() {
		return blockRow;
	}

	public void setBlockRow(boolean blockRow) {
		this.blockRow = blockRow;
	}


	public String getKeyParameter() {
		return keyParameter;
	}


	public void setKeyParameter(String keyParameter) {
		this.keyParameter = keyParameter;
	}
	
	
	
}
