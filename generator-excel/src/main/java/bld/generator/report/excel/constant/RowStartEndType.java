package bld.generator.report.excel.constant;

public enum RowStartEndType {

	ROW_START("RowStart"),
	ROW_END("RowEnd"),
	ROW_EMPTY("");
	
	
	private String value;

	public String getValue() {
		return value;
	}

	private RowStartEndType(String value) {
		this.value = value;
	}

	public String getParameter(String paramter) {
		return "${"+paramter+this.value+"}";
	}
	
}
