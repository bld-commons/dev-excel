package bld.generator.report.excel.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ FIELD })
@Documented
public @interface ExcelLabel {

	public ExcelCellLayout labelLayout() default @ExcelCellLayout(font = @ExcelFont(bold = true),rgbFont = @ExcelRgbColor(red=(byte)190,green=(byte)72,blue=(byte)10),rgbForeground = @ExcelRgbColor(red=(byte)255,green=(byte)233,blue=(byte)148));
	
	public int columnMerge() default 1;

	
	
}
