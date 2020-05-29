/**
* @author Francesco Baldi
* @mail francesco.baldi1987@gmail.com
* @class bld.generator.report.excel.data.ExtraColumn.java
*/
package bld.generator.report.excel.data;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtraColumn.<br>
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extraColumnAnnotation == null) ? 0 : extraColumnAnnotation.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExtraColumn other = (ExtraColumn) obj;
		if (extraColumnAnnotation == null) {
			if (other.extraColumnAnnotation != null)
				return false;
		} else if (!extraColumnAnnotation.equals(other.extraColumnAnnotation))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	
}
