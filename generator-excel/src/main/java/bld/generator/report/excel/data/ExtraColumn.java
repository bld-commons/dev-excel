/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 */
package bld.generator.report.excel.data;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtraColumn.
 */
public class ExtraColumn {
	
	/** The value. */
	private Object value;

	/** The extra column annotation. */
	private ExtraColumnAnnotation extraColumnAnnotation;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Gets the extra column annotation.
	 *
	 * @return the extra column annotation
	 */
	public ExtraColumnAnnotation getExtraColumnAnnotation() {
		return extraColumnAnnotation;
	}

	/**
	 * Sets the extra column annotation.
	 *
	 * @param extraColumnAnnotation the new extra column annotation
	 */
	public void setExtraColumnAnnotation(ExtraColumnAnnotation extraColumnAnnotation) {
		this.extraColumnAnnotation = extraColumnAnnotation;
	}

}
