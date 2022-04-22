package bld.generator.report.excel.constant;

public enum BoxStyle {

	STOP(0x00),
	WARNING(0x01),
	INFO(0x02);
	
	private BoxStyle(int value) {
		this.value=value;
	}

	private int value;

	public int getValue() {
		return value;
	}


	
}
