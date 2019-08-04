package it.bld.generator.report.excel.constant;

// TODO: Auto-generated Javadoc
/**
 * The Enum ColumnDateFormat.
 */
public enum ColumnDateFormat {

    /** The dd mm yyyy. */
    DD_MM_YYYY("dd/MM/yyyy"),
	
	/** The dd mm yyyy hh mm ss. */
	DD_MM_YYYY_HH_MM_SS("dd/MM/yyyy HH:mm:ss"),
	
	/** The yyyy mm dd. */
	YYYY_MM_DD("yyyy/mm/dd"),
	
	/** The yyyy mm dd hh mm ss. */
	YYYY_MM_DD_HH_MM_SS("yyyy/mm/dd HH:mm:ss")
	;
	
	/** The value. */
	private String value;

	/**
	 * Instantiates a new column date format.
	 *
	 * @param value the value
	 */
	private ColumnDateFormat(String value) {
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
