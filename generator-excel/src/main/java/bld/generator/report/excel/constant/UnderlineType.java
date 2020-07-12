/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.constant.UnderlineType.java
*/
package bld.generator.report.excel.constant;

/**
 * The Enum UnderlineType.
 */
public enum UnderlineType {

    /** The none. */
    NONE((byte)0),
    
    /** The single. */
    SINGLE((byte)1),
    
    /** The double. */
    DOUBLE((byte)2),
    
    /** The single accounting. */
    SINGLE_ACCOUNTING((byte)0x21),
    
    /** The double accounting. */
    DOUBLE_ACCOUNTING((byte)0x22);
	
	
	/** The value. */
	private byte value;

	/**
	 * Instantiates a new underline type.
	 *
	 * @param value the value
	 */
	private UnderlineType(byte value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public byte getValue() {
		return value;
	}

	
	
	
	
}
