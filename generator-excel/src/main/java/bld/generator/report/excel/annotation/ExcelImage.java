/*
 * @author Francesco Baldi
 * @mail francesco.baldi1987@gmail.com
 * @class bld.generator.report.excel.annotation.ExcelImage.java
 */
package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;

/**
 * The Interface ExcelImage.<br>
 * The ExcelImage is used to add image into cell, and it need to set the following properties.<br>
 * <ul>
 * <li>PictureType - to define the picture type</li>
 * <li>AnchorType - to define to anchor type</li>
 * <li>ResizeHeight - to define the resize in height</li>  
 * 	<ul>
 * 	<li>resize(1.0,1.0) - it keeps the original size.</li>
 *  <li>resize(0.5,0.5)</li> it resizes to 50% of the original.</li>
 *  <li>resize(2.0,2.0)</li> it resizes to 200% of the original.</li>
 * </ul>
 * <li>ResizeWidth - to define the resize in width</li>
 *  <ul>
 * 	<li>resize(1.0,1.0) - it keeps the original size.</li>
 *  <li>resize(0.5,0.5)</li> it resizes to 50% of the original.</li>
 *  <li>resize(2.0,2.0)</li> it resizes to 200% of the original.</li>
 * </ul>
 * </ul>
 * 
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelImage {

	/**
	 * Picture type.
	 *
	 * @return the picture type
	 */
	public PictureType pictureType() default PictureType.JPEG;
	
	/**
	 * Anchor type.
	 *
	 * @return the anchor type
	 */
	public AnchorType anchorType() default AnchorType.MOVE_AND_RESIZE;
	
	/**
	 * Resize height.
	 *
	 * @return the double
	 */
	public double resizeHeight() default 1.0;
	
	/**
	 * Resize width.
	 *
	 * @return the double
	 */
	public double resizeWidth() default 1.0;
	
	
	
}
