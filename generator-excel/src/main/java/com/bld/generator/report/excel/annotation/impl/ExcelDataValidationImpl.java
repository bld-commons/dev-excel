package com.bld.generator.report.excel.annotation.impl;

import java.util.Arrays;

import com.bld.generator.report.excel.annotation.ExcelBoxMessage;
import com.bld.generator.report.excel.annotation.ExcelDataValidation;
import com.bld.generator.report.excel.annotation.ExcelFormulaAlias;

public class ExcelDataValidationImpl extends ExcelAnnotationImpl<ExcelDataValidation>{

	
	private String value;

	/** The alias. */
	private ExcelFormulaAlias[] alias;

	/** The error box. */
	private ExcelBoxMessage errorBox;

	public ExcelDataValidationImpl() {
		super();
	}
	
	

	public ExcelDataValidationImpl(String value) {
		super();
		this.value = value;
	}



	public ExcelDataValidationImpl(String value, ExcelFormulaAlias[] alias, ExcelBoxMessage errorBox) {
		super();
		this.value = value;
		this.alias = alias;
		this.errorBox = errorBox;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the alias
	 */
	public ExcelFormulaAlias[] getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(ExcelFormulaAlias[] alias) {
		this.alias = alias;
	}

	/**
	 * @return the errorBox
	 */
	public ExcelBoxMessage getErrorBox() {
		return errorBox;
	}

	/**
	 * @param errorBox the errorBox to set
	 */
	public void setErrorBox(ExcelBoxMessage errorBox) {
		this.errorBox = errorBox;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(alias);
		result = prime * result + ((errorBox == null) ? 0 : errorBox.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ExcelDataValidationImpl)) {
			return false;
		}
		ExcelDataValidationImpl other = (ExcelDataValidationImpl) obj;
		if (!Arrays.equals(alias, other.alias)) {
			return false;
		}
		if (errorBox == null) {
			if (other.errorBox != null) {
				return false;
			}
		} else if (!errorBox.equals(other.errorBox)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	
	
}
