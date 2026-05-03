/**
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class com.bld.generator.report.excel.LockedSheet.java
 */
package com.bld.generator.report.excel;

/**
 * The Interface LockedSheet.<br>
 * Implement this interface on a {@link BaseSheet} subclass to enable dynamic sheet protection at runtime.<br>
 * The sheet is always locked; a password is applied only when {@link #password()} returns a non-blank value.
 */
public interface LockedSheet {

	/**
	 * Returns the password used to protect the sheet.<br>
	 * Return null or an empty string to lock the sheet without a password.
	 *
	 * @return the password
	 */
	String password();

}
