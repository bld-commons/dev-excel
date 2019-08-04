package it.bld.generator.report.excel.constant;

// TODO: Auto-generated Javadoc
/**
 * The Enum CopertinaType.
 */
public enum CopertinaType {

	
	/** The copertina. */
	COPERTINA ("/excel/Copertina.xls"),
	
	/** The copertina xlsx. */
	COPERTINA_XLSX("/excel/Copertina.xlsx");
	
	/** The value. */
	private String value;

	/**
	 * Instantiates a new copertina type.
	 *
	 * @param value the value
	 */
	private CopertinaType(String value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	
	
}
