package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.sl.usermodel.PictureData.PictureType;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface ExcelImage {

	public PictureType pictureType() default PictureType.JPEG;
	
	public AnchorType anchorType() default AnchorType.MOVE_AND_RESIZE;
	
	public double resizeHeight() default 100.0;
	
	public double resizeWidth() default 100.0;
	
	
	
}
